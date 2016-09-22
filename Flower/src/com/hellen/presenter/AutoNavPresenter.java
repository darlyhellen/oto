/**上午11:09:43
 * @author zhangyh2
 * AutoNavPresenter.java
 * TODO
 */
package com.hellen.presenter;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.hellen.base.BaseListener;
import com.hellen.biz.AutoNavInterface;
import com.hellen.biz.AutoNavInterface.AutoNavView;
import com.hellen.biz.imp.AutoNav;
import com.hellen.common.LogApp;
import com.hellen.flower.R;
import com.hellen.widget.dialog.ShowLoading;

/**
 * @author zhangyh2 AutoNavPresenter 上午11:09:43 TODO
 */
public class AutoNavPresenter {

	private String TAG = getClass().getSimpleName();

	private AutoNavInterface.AutoNavView view;

	private AutoNavInterface.AutoNavBiz biz;

	private ShowLoading loading;

	public AutoNavPresenter(AutoNavView view) {
		super();
		this.view = view;
		this.biz = new AutoNav();
		view.setPersenter(biz);
		loading = new ShowLoading((Context)view);
		loading.setMessage(R.string.loading);
	}

	public void findLocal() {
		biz.onStart();
		loading.show();
		
		view.setDisableClick();
		biz.location(new BaseListener<AMapLocation>() {

			@Override
			public void onSucces(AMapLocation result) {
				// TODO Auto-generated method stub
				view.setTextView(result.getLatitude() + "=="
						+ result.getLongitude());
				view.setEnableClick();
				LogApp.i(TAG, TAG + Thread.currentThread());
				loading.cancel();
			}

			@Override
			public void onFaild(int errorCode, String erroInfo) {
				// TODO Auto-generated method stub
				view.setTextView(errorCode + erroInfo);
				view.setEnableClick();
				LogApp.i(TAG, TAG + Thread.currentThread());
				loading.cancel();
			}
		});
	}

}
