/**
 * 下午2:27:28
 * @author zhangyh2
 * $
 * APP.java
 * TODO
 */
package com.hellen.base;

import java.io.File;
import java.math.BigInteger;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.WindowManager;

import com.hellen.common.ImageLoaderUtil;
import com.hellen.common.LogApp;
import com.umeng.analytics.MobclickAgent;
import com.umeng.comm.core.impl.CommunityFactory;
import com.umeng.socialize.PlatformConfig;

/**
 * @author zhangyh2 APP $ 下午2:27:28 TODO 系统入口程序。开始初始化的东西。
 */
public class APP extends Application {

	private static APP instance;

	public static boolean UPDATEEXCEPTION = false;

	// 各个平台的配置，建议放在全局Application或者程序入口
	{
		// 微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
		PlatformConfig.setWeixin("wx967daebe835fbeac",
				"5bb696d9ccd75a38c8a0bfe0675559b3");
	}

	public static APP getInstance() {
		if (instance == null) {
			LogApp.i("系统初始化错误...");
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

		if (ConsMVP.WIDTH.getLen() == 0 || ConsMVP.HEIGHT.getLen() == 0) {
			getParamsWithWH();
		}

		configCommunity();

		ImageLoaderUtil.getInstance();
		MobclickAgent.setCheckDevice(false);
		// 创建文件夹
		creatFile();

	}

	/**
	 * 下午3:27:06
	 * 
	 * @author zhangyh2 TODO 配置微社区
	 */
	private void configCommunity() {
		// TODO Auto-generated method stub
		CommunityFactory.getCommSDK(this).initSDK(this);
	}

	@SuppressWarnings("deprecation")
	private void getParamsWithWH() {
		// TODO Auto-generated method stub
		WindowManager wm = (WindowManager) instance
				.getSystemService(Context.WINDOW_SERVICE);
		ConsMVP.WIDTH.setLen(wm.getDefaultDisplay().getWidth());
		ConsMVP.HEIGHT.setLen(wm.getDefaultDisplay().getHeight());
	}

	/**
	 * @param context
	 * @return 上午9:23:50
	 * @author Zhangyuhui AppStack.java TODO 判断网络连接状态
	 */
	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * 
	 * 下午6:02:54
	 * 
	 * @author Zhangyuhui BaseActivity.java TODO 建立文件夹
	 */
	private void creatFile() {
		// TODO Auto-generated method stub
		File boot = new File(ConsMVP.ROOT);
		if (!boot.exists()) {
			boot.mkdir();
		}
	}

	/**
	 * 获得当前进程的名字
	 *
	 * @param context
	 * @return 进程号
	 */
	public static String getCurProcessName(Context context) {

		int pid = android.os.Process.myPid();

		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);

		for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
				.getRunningAppProcesses()) {

			if (appProcess.pid == pid) {
				return appProcess.processName;
			}
		}
		return null;
	}

	/**
	 * @param context
	 * @return 下午1:53:21
	 * @author Zhangyuhui AppStack.java TODO 友盟获取设备信息的方法体。
	 */
	public String getDeviceInfo(Context context) {
		try {
			org.json.JSONObject json = new org.json.JSONObject();
			android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			String device_id = tm.getDeviceId();
			android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);

			String mac = wifi.getConnectionInfo().getMacAddress();
			json.put("mac", mac);
			if (TextUtils.isEmpty(device_id)) {
				device_id = mac;
			}
			if (TextUtils.isEmpty(device_id)) {
				device_id = android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
			}
			json.put("device_id", device_id);
			/** 通过包管理器获得指定包名包含签名的包信息 **/
			PackageInfo packageInfo = getPackageManager().getPackageInfo(
					getPackageName(), PackageManager.GET_SIGNATURES);
			/******* 通过返回的包信息获得签名数组 *******/
			Signature[] signatures = packageInfo.signatures;
			/******* 循环遍历签名数组拼接应用签名 *******/
			for (Signature signature : signatures) {
				json.put("device_signature",
						new BigInteger(signature.toByteArray()).toString(16));
			}
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @return 下午3:23:16
	 * @author zhangyh2 APP.java TODO 获取XML文件中的版本信息，用于展示页面。
	 */
	public String getVersion() {
		String version = "0.0.0";
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(
					getPackageName(), 0);
			version = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return version;
	}

	/**
	 * @return 下午3:24:51
	 * @author zhangyh2 APP.java TODO 获取XML文件件中的版本号。
	 */
	public int getVersionCode() {
		int version = 0;
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(
					getPackageName(), 0);
			version = packageInfo.versionCode;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}

	/**
	 * 上午9:49:43
	 * 
	 * @author zhangyh2 TODO 获取渠道名称
	 */
	public String getChannelName() {
		// TODO Auto-generated method stub
		String channel = "Default";
		try {
			ApplicationInfo appInfo = getPackageManager().getApplicationInfo(
					getPackageName(), PackageManager.GET_META_DATA);
			channel = appInfo.metaData.getString("UMENG_CHANNEL");
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return channel;
	}

	/**
	 * 返回配置文件的日志开关
	 * 
	 * @return
	 */
	public boolean getLoggingSwitch() {
		try {
			ApplicationInfo appInfo = getPackageManager().getApplicationInfo(
					getPackageName(), PackageManager.GET_META_DATA);
			boolean b = appInfo.metaData.getBoolean("LOGGING");
			LogApp.w("[ECApplication - getLogging] logging is: " + b);
			return b;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean getAlphaSwitch() {
		try {
			ApplicationInfo appInfo = getPackageManager().getApplicationInfo(
					getPackageName(), PackageManager.GET_META_DATA);
			boolean b = appInfo.metaData.getBoolean("ALPHA");
			LogApp.w("[ECApplication - getAlpha] Alpha is: " + b);
			return b;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

}
