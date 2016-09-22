/**下午5:01:38
 * @author zhangyh2
 * UpdateServicePresenter.java
 * TODO
 */
package com.hellen.presenter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.hellen.base.ConsMVP;
import com.hellen.biz.UpdateServiceInterface;
import com.hellen.common.DigestUtils;
import com.hellen.common.LogApp;
import com.hellen.flower.service.UpdateService;

/**
 * @author zhangyh2 UpdateServicePresenter 下午5:01:38 TODO {@link UpdateService
 *         has the Presenter}
 */
public class UpdateServicePresenter {

	private String tag = getClass().getSimpleName();

	private UpdateServiceInterface service;
	private File tempFile = null;

	private int download_precent = 0;

	public UpdateServicePresenter(UpdateServiceInterface service, Intent intent) {
		super();
		this.service = service;
		this.service.init(intent);
		// 初始化下载任务内容views
		Message message = handler.obtainMessage(3, 0);
		handler.sendMessage(message);
	}

	public Handler handler = new Handler(Looper.myLooper()) {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (msg != null) {
				switch (msg.what) {
				case 1:
					service.FailedAPK(1, msg);
					break;
				case 2:
					// 下载完成后清除所有下载信息，执行安装提示
					download_precent = 0;
					service.installAPK(msg);
					break;
				case 3:
					service.progressNotification(download_precent);
					break;
				case 4:
					service.FailedAPK(4, msg);
					break;
				}
			}
		}

	};

	public void setDownLoadFile(final String url, final int version,
			final String md5) {
		if (url == null) {
			return;
		}
		new Thread() {
			@Override
			public void run() {
				// 先获取文件长度然后在获取文件流,文件长度获取失败，则不进行下载
				try {
					URL urls = new URL(url);
					HttpURLConnection connection = (HttpURLConnection) urls
							.openConnection();
					connection.setRequestMethod("GET");
					int fileSize = connection.getContentLength();
					connection.disconnect();
					download(url, version, fileSize, md5);
				} catch (Exception e) {
					Message message = handler.obtainMessage(4, "版本更新失败，请检查网络。");
					handler.sendMessage(message);
				}
			}
		}.start();
	}

	/**
	 * 上午9:45:16
	 * 
	 * @author zhangyh2 TODO
	 */
	private void download(String url, int version, int length, String desMd5) {
		HttpURLConnection connection = null;
		try {
			File rootFile = new File(ConsMVP.ROOT, "download");
			if (!rootFile.exists() && !rootFile.isDirectory())
				rootFile.mkdir();
			tempFile = new File(ConsMVP.ROOT, "/download/" + version
					+ "comytdinfokeephealth.apk");
			long count = 0;
			int precent = 0;

			if (!tempFile.exists()) {
				deleteFolderFile(rootFile.getAbsolutePath(), false);
				tempFile.createNewFile();
			}
			count = tempFile.length();
			if (count > length) {
				deleteFolderFile(rootFile.getAbsolutePath(), false);
				tempFile.createNewFile();
				count = 0;
			} else if (count == length) {
				// 判断本地生成的MD5是否和服务器一致。一致则进行安装，否则提示下载失败？还是重新下载？
				String md5 = DigestUtils.getFileMD5(tempFile);
				LogApp.i(tag, md5 + "" + desMd5);
				if (md5.equals(desMd5)) {
					Message message = handler.obtainMessage(2, tempFile);
					handler.sendMessage(message);
				} else {
					Message message = handler.obtainMessage(1,
							"安装包完整性检验失败，请重新下载。");
					handler.sendMessage(message);
					tempFile.delete();
				}
				return;
			}
			// 直接点击下载后，用户可以查看已经下载的比例
			int et = (int) (((double) count / length) * 100);
			Message etm = handler.obtainMessage(3, et);
			handler.sendMessage(etm);
			LogApp.i(tag, "新版本文件长度" + length);
			URL connetURL = new URL(url);
			connection = (HttpURLConnection) connetURL.openConnection();
			connection.setRequestMethod("GET");

			// 设置范围，格式为Range：bytes x-y;
			connection.setRequestProperty("Range", "bytes=" + count + "-"
					+ length);
			InputStream is = connection.getInputStream();
			if (is != null) {

				RandomAccessFile raf = new RandomAccessFile(tempFile, "rw");
				LogApp.i(tag, count + "--" + length);
				raf.seek(count);
				int temp = 0;
				byte[] buffer = new byte[2048];
				while ((temp = is.read(buffer)) != -1) {
					raf.write(buffer, 0, temp);
					count += temp;
					precent = (int) (((double) count / length) * 100);

					// 每下载完成5%就通知任务栏进行修改下载进度
					if (precent - download_precent >= 1) {
						download_precent = precent;
						Message message = handler.obtainMessage(3, precent);
						handler.sendMessage(message);
					}
				}
				raf.close();
				is.close();
			}
			// 判断本地生成的MD5是否和服务器一致。一致则进行安装，否则提示下载失败？还是重新下载？
			String md5 = DigestUtils.getFileMD5(tempFile);
			LogApp.i(tag, md5 + "" + desMd5);
			if (md5.equals(desMd5)) {
				Message message = handler.obtainMessage(2, tempFile);
				handler.sendMessage(message);
			} else {
				Message message = handler.obtainMessage(1, "安装包完整性检验失败，请重新下载。");
				handler.sendMessage(message);
				tempFile.delete();
			}
		} catch (ClientProtocolException e) {
			Message message = handler.obtainMessage(4, "版本更新失败，请检查网络。");
			handler.sendMessage(message);
		} catch (IOException e) {
			Message message = handler.obtainMessage(4, "版本更新失败，请检查网络。");
			handler.sendMessage(message);
		} catch (Exception e) {
			Message message = handler.obtainMessage(4, "版本更新失败，请检查网络。");
			handler.sendMessage(message);
		}
	}

	/**
	 * 删除指定目录下文件及目录
	 * 
	 * @param deleteThisPath
	 * @param filepath
	 * @return
	 */
	public void deleteFolderFile(String filePath, boolean deleteThisPath) {
		if (!TextUtils.isEmpty(filePath)) {
			try {
				File file = new File(filePath);
				if (file.isDirectory()) {// 处理目录
					File files[] = file.listFiles();
					for (int i = 0; i < files.length; i++) {
						deleteFolderFile(files[i].getAbsolutePath(), true);
					}
				}
				if (deleteThisPath) {
					if (!file.isDirectory()) {// 如果是文件，删除
						file.delete();
					} else {// 目录
						if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
							file.delete();
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
