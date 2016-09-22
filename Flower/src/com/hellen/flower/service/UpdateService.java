package com.hellen.flower.service;

import java.io.File;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;

import com.hellen.base.APP;
import com.hellen.base.BasePresenter;
import com.hellen.biz.UpdateServiceInterface;
import com.hellen.common.LogApp;
import com.hellen.common.ToastApp;
import com.hellen.flower.MainActivity;
import com.hellen.flower.R;
import com.hellen.presenter.UpdateServicePresenter;

/**
 * @author zhangyh2 a $ 下午2:12:42 TODO
 */
public class UpdateService extends Service implements UpdateServiceInterface {
	private String tag = getClass().getSimpleName();

	private NotificationManager nm;
	private Notification notification;
	private RemoteViews views;
	private int notificationId = 1234;

	private boolean isfirstDown;

	private UpdateServicePresenter presents;

	private Intent oldIntent;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(final Intent intent, int flags, int startId) {
		// to check wifi.
		LogApp.i(tag, "onStartCommand");
		if (intent != null) {
			if (APP.isNetworkConnected(APP.getInstance())) {
				presents = new UpdateServicePresenter(this, intent);
				// 启动线程开始执行下载任务
				if (!isfirstDown) {
					isfirstDown = true;

					if (intent.getStringExtra("url") == null) {
						LogApp.i(oldIntent.getStringExtra("url") + "启动Service进行下载");
						presents.setDownLoadFile(oldIntent.getStringExtra("url"),
								oldIntent.getIntExtra("version", 0),
								oldIntent.getStringExtra("desMd5"));
					}else {
						oldIntent = intent;
						LogApp.i(intent.getStringExtra("url") + "启动Service进行下载");
						presents.setDownLoadFile(intent.getStringExtra("url"),
								intent.getIntExtra("version", 0),
								intent.getStringExtra("desMd5"));
					}
				}
			} else {
				restartService();
			}

		}
		return START_REDELIVER_INTENT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseView#setPersenter(com.hellen.base.BasePresenter)
	 */
	@Override
	public void setPersenter(BasePresenter persenter) {
		// TODO Auto-generated method stub
		LogApp.i(tag, persenter + "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.UpdateServiceInterface#init()
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void init(Intent intent) {
		// TODO Auto-generated method stub
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notification = new Notification();
		notification.icon = android.R.drawable.stat_sys_download;
		// notification.icon=android.R.drawable.stat_sys_download_done;
		notification.tickerText = getString(R.string.app_name) + "更新";
		notification.when = System.currentTimeMillis();
		notification.defaults = Notification.DEFAULT_LIGHTS;

		// 设置任务栏中下载进程显示的views
		views = new RemoteViews(getPackageName(), R.layout.update_service);
		notification.contentView = views;

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);
		notification.setLatestEventInfo(this, "", "", contentIntent);

		// 将下载任务添加到任务栏中
		nm.notify(notificationId, notification);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.UpdateServiceInterface#installAPK()
	 */
	@Override
	public void installAPK(Message msg) {
		// TODO Auto-generated method stub
		nm.cancel(notificationId);
		Instanll((File) msg.obj, this);
		// 停止掉当前的服务
		stopSelf();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.UpdateServiceInterface#progressNotification()
	 */
	@Override
	public void progressNotification(int precent) {
		// TODO Auto-generated method stub
		// 更新状态栏上的下载进度信息
		views.setTextViewText(R.id.tvProcess, "已下载" + precent + "%");
		views.setProgressBar(R.id.pbDownload, 100, precent, false);
		notification.contentView = views;
		nm.notify(notificationId, notification);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.UpdateServiceInterface#FailedAPK()
	 */
	@Override
	public void FailedAPK(int type, Message msg) {
		// TODO Auto-generated method stub
		isfirstDown = false;
		switch (type) {
		case 1:
			// 下载完成，检验完整性
			nm.cancel(notificationId);
			ToastApp.showToast(msg.obj.toString());
			stopSelf();
			break;
		case 4:
			// 下载未完成，网络连接断开
			ToastApp.showToast(msg.obj.toString());
			restartService();
			break;
		default:
			break;
		}

	}

	/**
	 * 上午10:27:29
	 * 
	 * @author zhangyh2 TODO 重新启动本服务进行判断网络是否正常
	 */
	private void restartService() {
		// TODO Auto-generated method stub
		Intent sevice = new Intent(this, UpdateService.class);
		this.startService(sevice);
	}

	// 安装下载后的apk文件
	private void Instanll(File file, Context context) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
}