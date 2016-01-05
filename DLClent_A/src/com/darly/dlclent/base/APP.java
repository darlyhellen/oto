/**
 * 下午2:27:28
 * @author zhangyh2
 * $
 * APP.java
 * TODO
 */
package com.darly.dlclent.base;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

import com.lidroid.xutils.util.LogUtils;

/**
 * @author zhangyh2 APP $ 下午2:27:28 TODO 系统入口程序。开始初始化的东西。
 */
public class APP extends Application {

	private static APP instance;

	public static APP getInstance() {
		if (instance == null) {
			LogUtils.i("系统初始化错误...");
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		LogUtils.allowI = true; // 关闭 LogUtils.i(...) 的 adb log 输出
		if (APPEnum.WIDTH.getLen() == 0 || APPEnum.HEIGHT.getLen() == 0) {
			getParamsWithWH();
		}

		// 初始化数据库。
	}

	/**
	 * 
	 * 下午1:53:28
	 * 
	 * @author zhangyh2 APP.java TODO
	 */
	@SuppressWarnings("deprecation")
	private void getParamsWithWH() {
		// TODO Auto-generated method stub
		WindowManager wm = (WindowManager) instance
				.getSystemService(Context.WINDOW_SERVICE);
		APPEnum.WIDTH.setLen(wm.getDefaultDisplay().getWidth());
		APPEnum.HEIGHT.setLen(wm.getDefaultDisplay().getHeight());
	}

}
