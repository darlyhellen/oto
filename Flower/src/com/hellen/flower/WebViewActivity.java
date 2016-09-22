/**下午3:59:47
 * @author zhangyh2
 * WebViewActivity.java
 * TODO
 */
package com.hellen.flower;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hellen.base.BaseActivity;
import com.hellen.base.BasePresenter;
import com.hellen.biz.WebViewInterface;
import com.hellen.common.LogApp;
import com.hellen.presenter.WebViewPresenter;
import com.hellen.widget.header.HeaderView;
import com.hellen.widget.webview.ScrollWebView;
import com.hellen.widget.webview.ScrollWebView.OnScrollChangedCallback;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 WebViewActivity 下午3:59:47 TODO 网页的活动页面
 */
@ContentView(R.layout.activity_webview)
public class WebViewActivity extends BaseActivity implements WebViewInterface,
		OnClickListener, OnScrollChangedCallback {
	private String TAG = getClass().getSimpleName();

	@ViewInject(R.id.web_header_view)
	private HeaderView header;
	@ViewInject(R.id.web_webview)
	private ScrollWebView wv;

	private WebViewPresenter presenters;

	private String url;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		url = getIntent().getStringExtra("loadURL");
		presenters = new WebViewPresenter(this);
		presenters.init();
		presenters.isShowHeader(true, header);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseView#setPersenter(com.hellen.base.BasePresenter)
	 */
	@Override
	public void setPersenter(BasePresenter persenter) {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "setPersenter" + persenter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.WebViewInterface#initHeader()
	 */
	@Override
	public void initHeader() {
		// TODO Auto-generated method stub
		// tintManager.setStatusBarTintResource(R.color.red, 255);
		header.onSetTitle(R.string.app_name);
		header.onDisBack(false);
		header.getBack().setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.WebViewInterface#initWeb()
	 */
	@Override
	public void initWeb() {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "initWeb+实例化WebView");
		presenters.setWeb(wv, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "loadData" + url);
		wv.loadUrl(url);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		wv.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onReceivedTitle(WebView view, String title) {
				// TODO Auto-generated method stub
				super.onReceivedTitle(view, title);
				LogUtils.i("onReceivedTitle" + title);
				if (title != null && title.length() < 30) {
					header.onSetTitle(title);
				}
			}

			@Override
			public void onGeolocationPermissionsShowPrompt(String origin,
					Callback callback) {
				// TODO Auto-generated method stub
				callback.invoke(origin, true, false);
				super.onGeolocationPermissionsShowPrompt(origin, callback);
			}

		});
		wv.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url.startsWith("tel:")) {
					Intent intent = new Intent(Intent.ACTION_DIAL, Uri
							.parse(url));
					startActivity(intent);
					view.reload();
					return true;
				}
				return false;
			}

			@Override
			public void onLoadResource(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onLoadResource(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				LogApp.i(TAG, "onPageFinished" + url);
				if (view.getTitle() != null && view.getTitle().length() < 30) {
					header.onSetTitle(view.getTitle());
				}
			}
		});
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
		case R.id.view_header_iv:
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onKeyDown(int,
	 * android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			LogApp.i(TAG, "==onKeyDown==");
			if (wv.canGoBack()) {
				wv.goBack();
			} else {
				finish();
			}
		}
		return false;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		wv.loadUrl("about:blank");
		wv.stopLoading();
		wv.setWebChromeClient(null);
		wv.setWebViewClient(null);
		wv.destroy();
		wv = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hellen.widget.webview.ScrollWebView.OnScrollChangedCallback#onScroll
	 * (int, int, int, int) TODO监听webview的上下滑动状态
	 */
	@Override
	public void onScroll(int x, int y, int olx, int oly) {
		// TODO Auto-generated method stub
		LogApp.i(TAG, header.getHeight() + "__" + y);
	}

}
