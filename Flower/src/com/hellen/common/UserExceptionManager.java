/**上午10:23:58
 * @author zhangyh2
 * UserExceptionManager.java
 * TODO
 */
package com.hellen.common;

import java.util.Stack;

import android.app.ActivityManager;
import android.content.Context;

/**
 * 
 * @Description: Activity管理类：用于管理Activity和退出程序
 * 
 */
public class UserExceptionManager {

	private static Stack<Context> activityStack;
	private static UserExceptionManager instance;

	private UserExceptionManager() {
	}

	/**
	 * 单一实例
	 */
	public static UserExceptionManager getAppManager() {
		if (instance == null) {
			instance = new UserExceptionManager();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addContext(Context activity) {
		if (activityStack == null) {
			activityStack = new Stack<Context>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Context currentContext() {
		Context activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 退出应用程序
	 */
	public void exitApp(Context context) {
		try {
			ActivityManager activityMgr = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.killBackgroundProcesses(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
		}
	}

	public void sendFileTServ() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}