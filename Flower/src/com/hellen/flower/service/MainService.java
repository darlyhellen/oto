/**下午3:05:40
 * @author zhangyh2
 * MainService.java
 * TODO
 */
package com.hellen.flower.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.hellen.common.LogApp;
import com.hellen.flower.broadcast.BaseCastReceiver;

/**
 * @author zhangyh2 MainService 下午3:05:40 TODO 服务进行运行实时进行检测
 */
public class MainService extends Service {

	private String tag = getClass().getSimpleName();

	private Timer timer;

	private int i = 0;

	// private Watcher watcher;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		LogApp.i(tag, "onBind");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		setBroadCaster();
		// watcher = new Watcher(this);
		// watcher.createAppMonitor(android.os.Process.myPid() + "");
		LogApp.i(tag, "onCreate");
	}

	/**
	 * 下午3:47:45
	 * 
	 * @author zhangyh2 TODO 注册服务的监听广播
	 */
	private void setBroadCaster() {
		// TODO Auto-generated method stub
		IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
		BaseCastReceiver receiver = new BaseCastReceiver();
		registerReceiver(receiver, filter);
		LogApp.i(tag, "-注册-setBroadCaster");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		startRun();
		// Notification notification = new Notification(R.drawable.ic_launcher,
		// getString(R.string.app_name), System.currentTimeMillis());
		//
		// PendingIntent pendingintent = PendingIntent.getActivity(this, 0,
		// new Intent(this, MainActivity.class), 0);
		// notification.setLatestEventInfo(this, "uploadservice", "请保持程序在后台运行",
		// pendingintent);
		// startForeground(0x111, notification);
		return START_STICKY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.ContextWrapper#stopService(android.content.Intent)
	 */
	@Override
	public boolean stopService(Intent name) {
		// TODO Auto-generated method stub
		LogApp.i(tag, "stopService");
		return super.stopService(name);
	}

	/**
	 * 下午3:23:08
	 * 
	 * @author zhangyh2 TODO 启动Timer进行计时
	 */
	private void startRun() {
		// TODO Auto-generated method stub
		if (timer == null) {
			timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					i++;
					LogApp.e(i + "startRun");
				}
			}, 1000, 10000);
		}
	}

	@Override
	public void onTrimMemory(int level) {
		// TODO Auto-generated method stub
		LogApp.i(tag, "onTrimMemory");
		// System.exit(0);
		super.onTrimMemory(level);
	}

	@Override
	public void onTaskRemoved(Intent rootIntent) {
		// TODO Auto-generated method stub
		LogApp.i(tag, "onTaskRemoved");
		startMainService();
		// 用户手动杀死进程
		super.onTaskRemoved(rootIntent);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		LogApp.i(tag, "onDestroy");
		startMainService();
		super.onDestroy();
	}

	/**
	 * 上午11:10:58
	 * 
	 * @author zhangyh2 TODO
	 */
	private void startMainService() {
		// TODO Auto-generated method stub
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		// 方案一，采用BroadCast结合Service进行重启
		stopForeground(true);
		// 方案二，直接重启Service
		Intent sevice = new Intent(this, MainService.class);
		this.startService(sevice);
		LogApp.i(tag, "startMainService");
	}

}
