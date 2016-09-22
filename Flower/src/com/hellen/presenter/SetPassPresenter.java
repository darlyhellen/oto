/**下午3:23:42
 * @author zhangyh2
 * SetPassPresenter.java
 * TODO
 */
package com.hellen.presenter;

import android.content.Context;
import android.content.Intent;

import com.hellen.base.BaseListener;
import com.hellen.bean.UserInfo;
import com.hellen.biz.SetPassInterface;
import com.hellen.biz.SetPassInterface.SetPassBiz;
import com.hellen.biz.imp.SetPass;
import com.hellen.common.LogApp;
import com.hellen.common.ToastApp;
import com.hellen.flower.MainActivity;
import com.hellen.flower.R;
import com.hellen.flower.login.SetPassActivity;
import com.hellen.widget.dialog.ShowLoading;

/**
 * @author zhangyh2 SetPassPresenter 下午3:23:42 TODO
 */
public class SetPassPresenter {

	private String tag = getClass().getSimpleName();

	private SetPassBiz biz;

	private SetPassInterface view;

	private ShowLoading util;

	public SetPassPresenter(SetPassInterface views) {
		super();
		this.view = views;
		this.biz = new SetPass();
		view.setPersenter(biz);
		util = new ShowLoading((SetPassActivity) views);
		util.setMessage(R.string.xlistview_header_hint_loading);
	}

	public void register(final Context context) {
		LogApp.i(tag, "register");
		biz.onStart();
		if (!util.isShowing()) {
			util.show();
		}
		view.setDisableClick();
		biz.registuser(context, view.findUserName(), view.findUserPass(),
				new BaseListener<UserInfo>() {

					@Override
					public void onSucces(UserInfo result) {
						// TODO Auto-generated method stub
						view.setEnableClick();
						util.dismiss();
						ToastApp.showToast("用户注册成功");
						setintoMain(context);
					}

					@Override
					public void onFaild(int code, String info) {
						// TODO Auto-generated method stub
						view.setEnableClick();
						util.dismiss();
						ToastApp.showToast(info);
						view.actEnd();
					}
				});
	}

	/**
	 * 下午3:38:14
	 * 
	 * @author zhangyh2 TODO
	 */
	protected void setintoMain(Context context) {
		// TODO Auto-generated method stub
		view.actEnd();
		Intent intent = new Intent(context, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}

}
