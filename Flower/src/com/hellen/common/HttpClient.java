package com.hellen.common;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;

import android.content.Context;

import com.hellen.base.APP;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class HttpClient {
	private static int VersionCode = APP.getInstance().getVersionCode();
	private static final String APPSYS_STRING = "Android_";

	private HttpClient() {

	}

	private static HttpUtils httpUtils = new HttpUtils();

	public static void post(String url, String s,
			RequestCallBack<String> callBack) {
		if (!NetworkReachabilityUtil.isNetworkConnected(APP.getInstance())) {
			ToastApp.showToast("网络未连接...");
		} else {
			LogApp.i("接口地址--------->>>" + url);
			LogApp.i("param----------->>>" + s.toString());
			// httpUtils.configCurrentHttpCacheExpiry(1000*10);
			RequestParams params = new RequestParams();
			params.addHeader("Content-Type", "application/json;charset=UTF-8");
			params.addHeader("Accept", "application/json");
			params.addHeader("charset", "utf-8");
			if (VersionCode == 0) {
				VersionCode = APP.getInstance().getVersionCode();
			}
			params.addHeader("version", APPSYS_STRING + VersionCode);
			// params.addBodyParameter("param", s);
			try {
				StringEntity se = new StringEntity(s, "utf-8");
				params.setBodyEntity(se);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			httpUtils.configTimeout(5 * 1000);
			httpUtils.configRequestRetryCount(1);
			httpUtils.configRequestThreadPoolSize(3);
			httpUtils.configResponseTextCharset("utf-8");
			httpUtils.send(HttpMethod.POST, url, params, callBack);

		}
	}

	public static void get(Context context, String url, RequestParams params,
			RequestCallBack<String> callBack) {
		if (!NetworkReachabilityUtil.isNetworkConnected(context)) {
			ToastApp.showToast("网络未连接...");
		} else {
			LogApp.i("接口地址----------->>>" + url);
			if (null != params.getQueryStringParams()) {
				LogApp.i("params------------>>>"
						+ params.getQueryStringParams().toString());
			}
			params.addHeader("Content-Type", "application/json");
			params.addHeader("Accept", "application/json");
			if (VersionCode == 0) {
				VersionCode = APP.getInstance().getVersionCode();
			}
			params.addHeader("version", APPSYS_STRING + VersionCode);
			httpUtils.configTimeout(5 * 1000);
			httpUtils.configResponseTextCharset("utf-8");
			httpUtils.configCurrentHttpCacheExpiry(500);
			httpUtils.send(HttpMethod.GET, url, params, callBack);
		}
	}

}
