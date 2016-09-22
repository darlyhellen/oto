/**上午10:07:48
 * @author zhangyh2
 * UserException.java
 * TODO
 */
package com.hellen.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;

import com.hellen.base.APP;
import com.hellen.base.ConsHttpUrl;
import com.hellen.base.ConsMVP;

/**
 * @author zhangyh2 UserException 上午10:07:48 TODO
 */
public class UserException extends Exception implements
		UncaughtExceptionHandler {

	private String tag = getClass().getSimpleName();

	private static final long serialVersionUID = 1L;

	private Map<String, String> info = new HashMap<String, String>();// 用来存储设备信息和异常信息
	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");// 用于格式化日期,作为日志文件名的一部分

	private Thread.UncaughtExceptionHandler ucaught;

	private static UserException instance;

	private UserException() {
		// TODO Auto-generated constructor stub
		ucaught = Thread.getDefaultUncaughtExceptionHandler();
	}

	/**
	 * @return the instance
	 */
	public static UserException getInstance() {
		if (instance == null) {
			instance = new UserException();
		}
		return instance;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
		LogApp.i(getClass().getSimpleName(), ex.getMessage());
		if (!isCacheException(ex) && ucaught != null) {
			ucaught.uncaughtException(thread, ex);
		}
	}

	/**
	 * 上午10:16:25
	 * 
	 * @author zhangyh2 TODO 是否对异常进行处理
	 * @return true:处理了该异常信息;否则返回false
	 */
	private boolean isCacheException(Throwable ex) {
		if (ex == null) {
			return false;
		}
		collectDeviceInfo(APP.getInstance());
		final File file = saveCrashInfo2File(ex);

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Looper.prepare();
				if (file != null) {
					List<File> files = new ArrayList<File>();
					files.add(file);
					HttpUploadFile.uploadFiles(ConsHttpUrl.EXCEPTIONUPLOAD,
							files);
				}
				new AlertDialog.Builder(UserExceptionManager.getAppManager()
						.currentContext()).setTitle("提示").setCancelable(false)
						.setMessage("亲，程序出现数据错误问题！")
						.setNeutralButton("提交给厂家", new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// 这块可以进行文件上传服务器。上传完毕后关闭应用
								UserExceptionManager.getAppManager()
										.sendFileTServ();

							}
						}).create().show();
				Looper.loop();
			}
		}).start();

		return true;
	}

	/**
	 * 上午10:14:26
	 * 
	 * @author zhangyh2 TODO 获取APP异常崩溃处理对象
	 */
	public static UserException getExceptionHandler() {
		return new UserException();
	}

	/**
	 * 收集设备参数信息
	 * 
	 * @param context
	 */
	public void collectDeviceInfo(Context context) {
		try {
			PackageManager pm = context.getPackageManager();// 获得包管理器
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(),
					PackageManager.GET_ACTIVITIES);// 得到该应用的信息，即主Activity
			if (pi != null) {
				String versionName = pi.versionName == null ? "null"
						: pi.versionName;
				String versionCode = pi.versionCode + "";
				info.put("versionName", versionName);
				info.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		Field[] fields = Build.class.getDeclaredFields();// 反射机制
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				info.put(field.getName(), field.get("").toString());
				LogApp.d(tag, field.getName() + ":" + field.get(""));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private File saveCrashInfo2File(Throwable ex) {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : info.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key + "=" + value + "\r\n");
		}
		Writer writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		ex.printStackTrace(pw);
		Throwable cause = ex.getCause();
		// 循环着把所有的异常信息写入writer中
		while (cause != null) {
			cause.printStackTrace(pw);
			cause = cause.getCause();
		}
		pw.close();// 记得关闭
		String result = writer.toString();
		sb.append(result);
		// 保存文件
		long timetamp = System.currentTimeMillis();
		String time = format.format(new Date());
		String fileName = "crash-" + time + "-" + timetamp + ".log";
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				File dir = new File(ConsMVP.ROOT + "crash");
				LogApp.i("CrashHandler", dir.toString());
				if (!dir.exists())
					dir.mkdir();
				File fri = new File(dir, fileName);
				FileOutputStream fos = new FileOutputStream(fri);
				fos.write(sb.toString().getBytes());
				fos.close();
				return fri;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
