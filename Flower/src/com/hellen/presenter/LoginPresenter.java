/**上午10:57:39
 * @author zhangyh2
 * s.java
 * TODO
 */
package com.hellen.presenter;

import java.util.Map;

import android.content.Context;
import android.content.Intent;

import com.hellen.base.BaseListener;
import com.hellen.bean.UserInfo;
import com.hellen.biz.LoginInterface;
import com.hellen.biz.imp.Login;
import com.hellen.common.LogApp;
import com.hellen.common.ToastApp;
import com.hellen.flower.R;
import com.hellen.flower.login.LoginActivity;
import com.hellen.widget.dialog.ShowLoading;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * @author zhangyh2 s 上午10:57:39 TODO 控制器 页面动作操作
 */
public class LoginPresenter {

	private String TAG = getClass().getSimpleName();
	private LoginInterface userBiz;
	private LoginInterface.MainView main;
	private ShowLoading loading;

	private UMShareAPI api;

	public LoginPresenter(LoginActivity main) {
		this.main = main;
		this.userBiz = new Login();
		main.setPersenter(userBiz);
		loading = new ShowLoading(main);
		loading.setMessage(R.string.loading);
		userBiz.initPush(main);

		api = UMShareAPI.get(main);
	}

	public void login(Context context) {
		LogApp.i(TAG, "login");
		userBiz.onStart();
		if (!loading.isShowing()) {
			loading.show();
		}
		main.setDisableClick();
		userBiz.login_in(context, main.getUserName(), main.getPassword(),
				new BaseListener<UserInfo>() {

					@Override
					public void onSucces(final UserInfo result) {
						// TODO Auto-generated method stub
						main.onLoginSuccess();
						main.setEnableClick();
						loading.cancel();
					}

					@Override
					public void onFaild(int code, String info) {
						// TODO Auto-generated method stub
						main.onLoginFailed();
						main.setEnableClick();
						loading.cancel();
						loading.cancel();
					}
				});
	}

	/**
	 * 下午2:07:59
	 * 
	 * @author zhangyh2 TODO
	 */
	public void thirdLogin(LoginActivity context, SHARE_MEDIA platform) {
		// TODO Auto-generated method stub
		api.doOauthVerify(context, platform, new UMAuthListener() {

			@Override
			public void onError(SHARE_MEDIA platform, int action, Throwable t) {
				// TODO Auto-generated method stub
				ToastApp.showToast("登录成功");
			}

			@Override
			public void onComplete(SHARE_MEDIA platform, int action,
					Map<String, String> data) {
				// TODO Auto-generated method stub
				ToastApp.showToast("登录失败");
			}

			@Override
			public void onCancel(SHARE_MEDIA platform, int action) {
				// TODO Auto-generated method stub
				ToastApp.showToast("取消登录");
			}
		});
	}

	/** 下午2:29:54
	 * @author zhangyh2
	 * TODO
	 */
	public void initAPI(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (api!=null) {
			api.onActivityResult(requestCode, resultCode, data);
		}
	}
}