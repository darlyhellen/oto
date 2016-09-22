/**
 * 下午2:27:28
 * @author zhangyh2
 * $
 * APP.java
 * TODO
 */
package com.darly.dlclent.base;

import java.io.File;
import java.io.InvalidClassException;
import java.math.BigInteger;

import org.json.JSONException;
import org.json.JSONObject;

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

import com.darly.dlclent.R;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.ImageLoaderUtil;
import com.darly.dlclent.common.SharePreferCache;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.TimeCons;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.ECLoginUser;
import com.darly.im.common.CCPAppManager;
import com.darly.im.common.utils.ECPreferenceSettings;
import com.darly.im.common.utils.ECPreferences;
import com.darly.im.common.utils.FileAccessor;
import com.darly.im.core.ClientUser;
import com.darly.im.ui.SDKCoreHelper;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.yuntongxun.ecsdk.ECInitParams;

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
		creatFile();
		// 初始化数据库。创建完成数据库。当数据库存在且版本为变化时，此方法执行无效。
		// DBUtilsHelper.getInstance().getDb();
		// 初始化Imageloader
		ImageLoaderUtil.getInstance();
		// 各个平台的配置，建议放在全局Application或者程序入口
		// 微信 appid appsecret
		CCPAppManager.setContext(instance);
		FileAccessor.initFileAccess();
		setChattingContactId();
		// SDKInitializer.initialize(instance);
		// 云通讯用户直接登录。开启APP后用户已经处于登录时，确保云通讯也登录。
		if (SharePreferHelp.getValue(APPEnum.USERINFO.getDec(), null) != null) {
			BaseModel<ECLoginUser> data = new BaseModelPaser<ECLoginUser>()
					.paserJson(SharePreferHelp.getValue(
							APPEnum.USERINFO.getDec(), null),
							new TypeToken<ECLoginUser>() {
							});
			if (data != null && data.getData() != null) {
				loginEC(data.getData());
			}
		}
		// 每次APP打开时，提取网络时间。和本地时间
		TimeCons.getInstance().init();
		// LogUtils.i(getDeviceInfo(instance));

	}

	/**
	 * 下午3:10:24
	 * 
	 * @author zhangyh2 TODO 云通讯进行登录 并且获取用户的朋友列表
	 */
	private void loginEC(ECLoginUser ecLoginUser) {
		// 用户的云通讯登录。
		ClientUser clientUser = new ClientUser(ecLoginUser.getTel());
		clientUser.setUserId(ecLoginUser.getVoipAccount());
		clientUser.setPassword(ecLoginUser.getVoipPwd());
		clientUser.setAppKey(ConsHttpUrl.APPKEY);
		clientUser.setAppToken(ConsHttpUrl.APPTOKEN);
		clientUser.setLoginAuthType(ECInitParams.LoginAuthType.PASSWORD_AUTH);
		CCPAppManager.setClientUser(clientUser);
		SDKCoreHelper.init(this, ECInitParams.LoginMode.AUTO);

		if (!APP.isNetworkConnected(this)) {
			ToastApp.showToast(R.string.neterror);
			return;
		} else {
			JSONObject ob = new JSONObject();
			try {
				ob.put("tel", ecLoginUser.getTel());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpClient.post(ConsHttpUrl.FRIEND, ob.toString(),
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							// TODO Auto-generated method stub
							LogUtils.i(arg0.result);
							SharePreferCache.putValue(
									SharePreferHelp.getValue(
											APPEnum.USERVOIP.getDec(), null)
											+ SharePreferCache.CACHE,
									APPEnum.ECCONTACTS.getDec(), arg0.result);
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub

						}
					});
		}
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

	/**
	 * 
	 * 下午6:02:54
	 * 
	 * @author Zhangyuhui BaseActivity.java TODO 建立文件夹
	 */
	private void creatFile() {
		// TODO Auto-generated method stub
		File boot = new File(APPEnum.ROOT);
		if (!boot.exists()) {
			boot.mkdir();
		}
		File radio = new File(APPEnum.MAINRADIO);
		if (!radio.exists()) {
			radio.mkdir();
		}
		File image = new File(APPEnum.IMAGE);
		if (!image.exists()) {
			image.mkdir();
		}
		File log = new File(APPEnum.LOG);
		if (!log.exists()) {
			log.mkdir();
		}
	}

	/**
	 * 保存当前的聊天界面所对应的联系人、方便来消息屏蔽通知
	 */
	private void setChattingContactId() {
		try {
			ECPreferences.savePreference(
					ECPreferenceSettings.SETTING_CHATTING_CONTACTID, "", true);
		} catch (InvalidClassException e) {
			e.printStackTrace();
		}
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
			LogUtils.w("[ECApplication - getLogging] logging is: " + b);
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
			LogUtils.w("[ECApplication - getAlpha] Alpha is: " + b);
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
