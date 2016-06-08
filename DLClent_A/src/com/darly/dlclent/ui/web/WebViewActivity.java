/**下午1:57:38
 * @author zhangyh2
 * WebViewActivity.java
 * TODO
 */
package com.darly.dlclent.ui.web;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.ui.login.LoginActivity;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 WebViewActivity 下午1:57:38 TODO
 */
@ContentView(R.layout.activity_webview)
public class WebViewActivity extends BaseActivity implements OnClickListener {

	private String url;
	@ViewInject(R.id.webview_view)
	private WebView wv;
	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		title.setText("网页加载");
		back.setVisibility(View.VISIBLE);
		other.setVisibility(View.INVISIBLE);
		url = getIntent().getStringExtra("url");
		initWeb();
	}

	/**
	 * 下午2:22:43
	 * 
	 * @author zhangyh2 TODO
	 */
	@SuppressLint("SetJavaScriptEnabled")
	@SuppressWarnings("deprecation")
	private void initWeb() {
		// TODO Auto-generated method stub
		WebSettings webSettings = wv.getSettings();
		// 设置WebView属性，能够执行Javascript脚本
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		//
		webSettings.setUseWideViewPort(true);// 关键点
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setAppCacheEnabled(true);
		webSettings.setAppCacheMaxSize(8 * 1024 * 1024); // 8MB
		// webSettings.setAppCachePath(Constants.WEBVIEW_CACHE_DIR );
		String appCacheDir = this.getApplicationContext()
				.getDir("cache", Context.MODE_PRIVATE).getPath();
		webSettings.setAppCachePath(appCacheDir);
		webSettings.setAllowFileAccess(true);
		webSettings.setDomStorageEnabled(true);
		webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
		// js调用安卓方法
		wv.addJavascriptInterface(this, "javajs");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		if (url != null) {
			HashMap<String, String> hashmap = new HashMap<String, String>();
			hashmap.put("token",
					SharePreferHelp.getValue(APPEnum.TOKEN.getDec(), null));
			LogUtils.i(hashmap.toString());
			wv.loadUrl(url, hashmap);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		back.setOnClickListener(this);
		wv.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				if (url.contains("login")) {
					Intent intent = new Intent(WebViewActivity.this,
							LoginActivity.class);
					startActivity(intent);
					finish();
				} else {
					wv.loadUrl(url);
				}
				LogUtils.i("shouldOverrideUrlLoading" + url);
				return true;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
				LogUtils.i("onPageStarted" + url);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				LogUtils.i("onPageFinished" + url);
			}

			@Override
			public void onLoadResource(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onLoadResource(view, url);
				LogUtils.i("onLoadResource" + url);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// TODO Auto-generated method stub
				super.onReceivedError(view, errorCode, description, failingUrl);
				LogUtils.i("onReceivedError" + description);
			}
		});
	}

	@JavascriptInterface
	protected void initLoad() {
		// wv.loadUrl("javascript:init()");
		LogUtils.i("initLoad 被调用");
		wv.loadUrl("javascript:javaCtrJS()");
		wv.loadUrl("javascript:jsCtrJava()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.header_back:
			if (wv.canGoBack()) {
				wv.goBack();
			} else {
				finish();
			}
			break;

		default:
			break;
		}
	}

}
