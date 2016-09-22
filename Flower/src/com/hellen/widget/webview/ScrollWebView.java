/**上午10:47:52
 * @author zhangyh2
 * ScrollWebView.java
 * TODO
 */
package com.hellen.widget.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * @author zhangyh2 ScrollWebView 上午10:47:52 TODO
 */
public class ScrollWebView extends WebView {

	public ScrollWebView(Context context) {
		super(context);
	}

	public ScrollWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ScrollWebView(final Context context, final AttributeSet attrs,
			final int defStyle) {
		super(context, attrs, defStyle);
	}

	long last_time = 0;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			long current_time = System.currentTimeMillis();
			long d_time = current_time - last_time;
			System.out.println(d_time);
			;
			if (d_time < 300) {
				last_time = current_time;
				return true;
			} else {
				last_time = current_time;
			}
			break;
		}
		return super.onTouchEvent(event);
	}

	private OnScrollChangedCallback mOnScrollChangedCallback;

	@Override
	protected void onScrollChanged(final int l, final int t, final int oldl,
			final int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);

		if (mOnScrollChangedCallback != null) {
			mOnScrollChangedCallback.onScroll(l, t, oldl, oldt);
		}
	}

	public OnScrollChangedCallback getOnScrollChangedCallback() {
		return mOnScrollChangedCallback;
	}

	public void setOnScrollChangedCallback(
			final OnScrollChangedCallback onScrollChangedCallback) {
		mOnScrollChangedCallback = onScrollChangedCallback;
	}

	/**
	 * Impliment in the activity/fragment/view that you want to listen to the
	 * webview
	 */
	public static interface OnScrollChangedCallback {
		public void onScroll(int x, int y, int olx, int oly);
	}

}
