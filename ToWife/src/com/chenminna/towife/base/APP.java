/**
 * 下午2:27:28
 * @author zhangyh2
 * $
 * APP.java
 * TODO
 */
package com.chenminna.towife.base;

import java.math.BigInteger;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.WindowManager;

import com.lidroid.xutils.util.LogUtils;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

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
		if (APPEnum.WIDTH.getLen() == 0 || APPEnum.HEIGHT.getLen() == 0) {
			getParamsWithWH();
		}
		// 创建文件夹
		// 初始化数据库。创建完成数据库。当数据库存在且版本为变化时，此方法执行无效。
		// DBUtilsHelper.getInstance().getDb();
		// 初始化Imageloader
		initImageLoader();

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

	/**
	 * 
	 * 上午10:44:40
	 * 
	 * @author Zhangyuhui AppStack.java TODO初始化单例模式下的ImageLoader
	 */
	private void initImageLoader() {
		// TODO Auto-generated method stub

		@SuppressWarnings("deprecation")
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this)
				.memoryCacheExtraOptions(480, 800)
				// maxwidth, max height，即保存的每个缓存文件的最大长宽
				.threadPoolSize(3)
				// 线程池内加载的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
				// You can pass your own memory cache
				// implementation/你可以通过自己的内存缓存实现
				.memoryCacheSize(2 * 1024 * 1024)
				.discCacheSize(50 * 1024 * 1024)
				// 将保存的时候的URI名称用MD5 加密
				// 将保存的时候的URI名称用MD5 加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCacheFileCount(100)
				// 缓存的文件数量
				// 自定义缓存路径
				// 自定义缓存路径
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(
						new BaseImageDownloader(this, 5 * 1000, 30 * 1000))
				.writeDebugLogs() // Remove for releaseapp
				.build();// 开始构建
		ImageLoader.getInstance().init(config);
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

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

}
