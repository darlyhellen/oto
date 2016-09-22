/**下午3:49:06
 * @author zhangyh2
 * a.java
 * TODO
 */
package com.hellen.flower.broadcast;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hellen.common.LogApp;
import com.hellen.flower.service.MainService;

/**
 * @author zhangyh2 a 下午3:49:06 TODO 综合广播
 */
public class BaseCastReceiver extends BroadcastReceiver {

	public static final String ACTION_TEST = "com.hellen.mvplogin";
	public static final String ACTION_LOGIN = "com.hellen.mvplogin.LoginActivity";
	public static final String ACTION_NAV = "com.hellen.mvplogin.AutoNavActivity";
	public static final String SERVICE_RESTART = "com.hellen.mvplogin.service.MainService";
	private String tag = getClass().getSimpleName();
	private boolean isServiceRunning = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent == null) {
			return;
		}
		LogApp.i(tag, "onReceive" + intent.getAction());
		if (intent.getAction().equals(Intent.ACTION_TIME_TICK)
				|| intent.getAction().equals(SERVICE_RESTART)) {
			// 启动服务
			ActivityManager manager = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			for (RunningServiceInfo service : manager
					.getRunningServices(Integer.MAX_VALUE)) {
				if ("com.hellen.mvplogin.service.MainService"
						.equals(service.service.getClassName())) {
					isServiceRunning = true;
				}
			}
			if (!isServiceRunning) {
				Intent i = new Intent(context, MainService.class);
				context.startService(i);
			}
		} else {
			onReceiveBroadcast(intent);
		}
	}

	/**
	 * 下午3:07:57
	 * 
	 * @author zhangyh2 TODO
	 */
	public void onReceiveBroadcast(Intent intent) {
		// TODO Auto-generated method stub

	}

}
