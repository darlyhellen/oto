/**上午11:14:51
 * @author zhangyh2
 * LogApp.java
 * TODO
 */
package com.hellen.common;

import android.util.Log;

/**
 * @author zhangyh2 LogApp 上午11:14:51 TODO
 */
public class LogApp {

	private static String TAG = "LogApp";

	public static boolean isShow = true;

	public static void i(String msg) {
		if (!isShow)
			return;
		Log.i(TAG, msg);
	}

	public static void i(String tag, String msg) {
		if (!isShow)
			return;
		Log.i(tag, msg);
	}

	public static void e(String msg) {
		if (!isShow)
			return;
		Log.e(TAG, msg);
	}

	public static void d(String msg) {
		if (!isShow)
			return;
		Log.d(TAG, msg);
	}

	public static void d(String tag, String msg) {
		if (!isShow)
			return;
		Log.d(tag, msg);
	}

	public static void w(String msg) {
		if (!isShow)
			return;
		Log.w(TAG, msg);
	}

}
