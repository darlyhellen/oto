/**下午4:07:45
 * @author zhangyh2
 * WebViewPresenter.java
 * TODO
 */
package com.hellen.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;

import com.hellen.biz.WebViewInterface;
import com.hellen.biz.imp.Web;
import com.hellen.common.LogApp;
import com.hellen.flower.WebViewActivity;
import com.hellen.widget.header.HeaderView;

/**
 * @author zhangyh2 WebViewPresenter 下午4:07:45 TODO
 */
public class WebViewPresenter {

	private String TAG = getClass().getSimpleName();

	private WebViewInterface.WebViewBiz biz;

	private WebViewInterface view;

	public WebViewPresenter(WebViewActivity views) {
		this.view = views;
		this.biz = new Web();
	}

	public void init() {
		view.setPersenter(biz);
		view.initHeader();
		view.initWeb();
	}

	public void isShowHeader(boolean show, HeaderView header) {
		if (show) {
			header.setVisibility(View.VISIBLE);
		} else {
			header.setVisibility(View.GONE);
		}
	}

	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
	@SuppressWarnings("deprecation")
	public void setWeb(WebView wv, Context context) {
		LogApp.i(TAG, "setWeb()");
		WebSettings webSettings = wv.getSettings();
		// 设置可以访问文件
		webSettings.setAllowFileAccess(true);
		webSettings.setPluginState(PluginState.ON);
		// 设置WebView属性，能够执行Javascript脚本
		// 广播没有加载注册完成引起崩溃
		// webSettings.setBuiltInZoomControls(true);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setUseWideViewPort(true);// 关键点
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setAppCacheEnabled(true);

		webSettings.setAppCacheMaxSize(8 * 1024 * 1024); // 8MB
		// webSettings.setAppCachePath(Constants.WEBVIEW_CACHE_DIR );
		String appCacheDir = context.getApplicationContext()
				.getDir("cache", Context.MODE_PRIVATE).getPath();
		webSettings.setAppCachePath(appCacheDir);
		webSettings.setDomStorageEnabled(true);
		// 启用数据库
		webSettings.setDatabaseEnabled(true);
		// 设置定位的数据库路径
		String dir = context.getApplicationContext()
				.getDir("database", Context.MODE_PRIVATE).getPath();
		webSettings.setGeolocationDatabasePath(dir);
		// 启用地理定位
		webSettings.setGeolocationEnabled(true);

		webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
		// js调用安卓方法
		wv.addJavascriptInterface(this, "HellenDown");
	}

}
