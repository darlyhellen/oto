/**下午3:11:24
 * @author zhangyh2
 * SetPassInterface.java
 * TODO
 */
package com.hellen.biz;

import android.content.Context;

import com.hellen.base.BaseListener;
import com.hellen.base.BasePresenter;
import com.hellen.base.BaseView;
import com.hellen.bean.UserInfo;

/**
 * @author zhangyh2 SetPassInterface 下午3:11:24 TODO
 */
public interface SetPassInterface extends BaseView {

	String findUserName();

	String findUserPass();
	
	void setDisableClick();

	void setEnableClick();
	
	void actEnd();

	public interface SetPassBiz extends BasePresenter {
		void registuser(Context context, String username, String password,
				BaseListener<UserInfo> listener);
	}
}
