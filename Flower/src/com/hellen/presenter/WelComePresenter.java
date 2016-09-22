/**下午2:56:36
 * @author zhangyh2
 * WelComePresenter.java
 * TODO
 */
package com.hellen.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.hellen.base.APP;
import com.hellen.base.BaseListener;
import com.hellen.biz.WelComeInterface;
import com.hellen.biz.imp.WelCome;
import com.hellen.flower.service.MainService;
import com.hellen.flower.wel.WelcomeActivity;

/**
 * @author zhangyh2 WelComePresenter 下午2:56:36 TODO
 */
public class WelComePresenter {

	private WelComeInterface.WelComeBiz biz;

	private WelComeInterface view;

	public WelComePresenter(WelcomeActivity views) {
		this.view = views;
		this.biz = new WelCome();
		view.setPersenter(biz);
		view.setVersion(APP.getInstance().getVersion());
	}

	public void getBackGround() {
		biz.findBackGround(new BaseListener<Bitmap>() {

			@Override
			public void onSucces(Bitmap result) {
				// TODO Auto-generated method stub
				view.setBack(result);
				view.startAnim();
			}

			@Override
			public void onFaild(int code, String info) {
				// TODO Auto-generated method stub
				view.setBack(null);
				view.startAnim();
			}
		});
	}

	public void startMainService(final Context context) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, MainService.class);
				context.startService(intent);
			}
		}).start();

	}

}
