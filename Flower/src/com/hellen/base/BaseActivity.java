/**
 * 下午2:33:01
 * @author zhangyh2
 * $
 * BaseActivity.java
 * TODO
 */
package com.hellen.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;
import android.view.WindowManager;

import com.hellen.common.UserException;
import com.hellen.common.UserExceptionManager;
import com.hellen.flower.R;
import com.hellen.flower.broadcast.BaseCastReceiver;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

/**
 * @author zhangyh2 BaseActivity $ 下午2:33:01 TODO
 */
public abstract class BaseActivity extends FragmentActivity {

	public SystemBarTintManager tintManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@TargetApi(19)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initSystemBar(this);
		// 此方法与统计分析sdk中统计日活的方法无关！请务必调用此方法！
		PushAgent.getInstance(this).onAppStart();

		initGlobalVariable();

		initView(savedInstanceState);

		loadData();

		initListener();

	}

	public void initSystemBar(Activity activity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(activity, true);
		}
		tintManager = new SystemBarTintManager(activity);
		tintManager.setStatusBarTintEnabled(true);
		// 使用颜色资源
		tintManager.setStatusBarTintResource(R.color.red, 255);
	}

	@TargetApi(19)
	private static void setTranslucentStatus(Activity activity, boolean on) {
		Window win = activity.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	/**
	 * 
	 * 下午2:36:27
	 * 
	 * @author zhangyh2 BaseActivity.java TODO
	 *         初始化全局的一些变量。而且做好的静态变量。每个Activity里面的变量由自己来进行定义。
	 */
	private void initGlobalVariable() {
		// TODO Auto-generated method stub
		LogUtils.customTagPrefix = "DLClient_A"; // 方便调试时过滤 adb logcat 输出
		LogUtils.allowI = true; // 关闭 LogUtils.i(...) 的 adb log 输出
		ViewUtils.inject(this);// 注入view和事件
		if (APP.UPDATEEXCEPTION) {
			// 注册默认的未捕捉异常处理类
			Thread.setDefaultUncaughtExceptionHandler(UserException
					.getExceptionHandler());
			UserExceptionManager.getAppManager().addContext(this);
		}
	}

	/**
	 * @param savedInstanceState
	 *            下午2:34:08
	 * @author zhangyh2 BaseActivity.java TODO 初始化控件
	 */
	protected abstract void initView(Bundle savedInstanceState);

	/**
	 * 
	 * 下午2:34:10
	 * 
	 * @author zhangyh2 BaseActivity.java TODO 加载数据
	 */
	protected abstract void loadData();

	/**
	 * 
	 * 下午2:42:02
	 * 
	 * @author zhangyh2 BaseFragment.java TODO 初始化坚挺事件
	 */
	protected abstract void initListener();

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onResume(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPause(this);
	}

	public FragmentManager fragmentManager;
	public String TAGSS = "";

	/*
	 * 在fragment的管理类中，我们要实现这部操作，而他的主要作用是，当D这个activity回传数据到
	 * 这里碎片管理器下面的fragnment中时，往往会经过这个管理器中的onActivityResult的方法。
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/* 在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment */
		Fragment f = fragmentManager.findFragmentByTag(TAGSS);
		/* 然后在碎片中调用重写的onActivityResult方法 */
		if (f == null) {
			onActivityResult(requestCode, resultCode, data);
		} else {
			f.onActivityResult(requestCode, resultCode, data);
		}
		LogUtils.i("onActivityResult");
	}

	/**
	 * 下午3:50:58
	 * 
	 * @author zhangyh2 TODO 给Activity注册广播
	 */
	public void receiverRegister(String[] args, BaseCastReceiver receiver) {
		if (args == null || receiver == null) {
			return;
		}
		IntentFilter filter = new IntentFilter();
		for (String action : args) {
			filter.addAction(action);
		}
		registerReceiver(receiver, filter);
	}

	public void receiverConsel(BaseCastReceiver receiver) {
		if (receiver == null) {
			return;
		}
		unregisterReceiver(receiver);
	}

}
