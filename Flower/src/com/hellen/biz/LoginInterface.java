/**上午10:40:51
 * @author zhangyh2
 * UserLogin.java
 * TODO
 */
package com.hellen.biz;

import android.content.Context;

import com.hellen.base.BaseListener;
import com.hellen.base.BasePresenter;
import com.hellen.base.BaseView;
import com.hellen.bean.UserInfo;

/**
 * @author zhangyh2 UserLogin 上午10:40:51 TODO 业务逻辑接口
 */
public interface LoginInterface extends BasePresenter {

	void initPush(Context context);

	void login_in(Context context, String username, String password,
			BaseListener<UserInfo> listener);

	void login_out(String username, String password,
			BaseListener<UserInfo> listener);

	interface MainView extends BaseView {

		void setDisableClick();

		void setEnableClick();

		void onLoginSuccess();

		void onLoginFailed();

		String getUserName();

		String getPassword();
	}
}
