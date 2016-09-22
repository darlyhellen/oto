/**下午2:53:23
 * @author zhangyh2
 * WelComeBiz.java
 * TODO
 */
package com.hellen.biz.imp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.hellen.base.APP;
import com.hellen.base.BaseListener;
import com.hellen.biz.WelComeInterface;
import com.hellen.common.ImageLoaderUtil;
import com.hellen.common.ImageLoaderUtil.Loading;
import com.hellen.common.LogApp;
import com.hellen.common.ToastApp;
import com.hellen.flower.R;
import com.nostra13.universalimageloader.core.assist.FailReason;

/**
 * @author zhangyh2 WelComeBiz 下午2:53:23 TODO
 */
public class WelCome implements WelComeInterface.WelComeBiz {

	private String tag = getClass().getSimpleName();

	private String url = "http://img4q.duitang.com/uploads/item/201410/02/20141002112810_4PBsZ.thumb.700_0.jpeg";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BasePresenter#onStart()
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		LogApp.i(tag, "onStart");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hellen.biz.WelComeInterface.WelComeBiz#findBackGround(com.hellen.
	 * base.BaseListener)
	 */
	@Override
	public void findBackGround(final BaseListener<Bitmap> listener) {
		// TODO Auto-generated method stub
		onStart();
		LogApp.i(tag, "findBackGround");
		if (!APP.isNetworkConnected(APP.getInstance())) {
			ToastApp.showToast(R.string.neterror);
			Bitmap bm = BitmapFactory.decodeResource(APP.getInstance()
					.getResources(), R.drawable.ic_page_backgroud);
			listener.onSucces(bm);
			return;
		}
		ImageLoaderUtil.getInstance().load(url, new Loading() {

			@Override
			public void onStarted(String arg0, View arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFailed(String arg0, View arg1, FailReason arg2) {
				// TODO Auto-generated method stub
				listener.onFaild(0, arg0);
			}

			@Override
			public void onComplete(String arg0, View arg1, Bitmap arg2) {
				// TODO Auto-generated method stub
				listener.onSucces(arg2);
			}

			@Override
			public void onCancelled(String arg0, View arg1) {
				// TODO Auto-generated method stub
				listener.onFaild(0, arg0);
			}

		});

	}

}
