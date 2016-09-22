/**上午10:47:17
 * @author zhangyh2
 * ChangeUserInfoPresenter.java
 * TODO
 */
package com.hellen.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.hellen.biz.ChangeUserInterface;
import com.hellen.flower.R;
import com.hellen.flower.userinfo.ChangeUserInfoActivity;

/**
 * @author zhangyh2 ChangeUserInfoPresenter 上午10:47:17 TODO
 */
public class ChangeUserInfoPresenter {

	private ChangeUserInterface view;

	public ChangeUserInfoPresenter(ChangeUserInterface views) {
		super();
		this.view = views;
		view.initHeader();
		view.initInfo();
	}

	/**
	 * 上午11:17:32
	 * 
	 * @author zhangyh2 TODO
	 */
	public void clickDown(View v, Context context) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.view_header_iv:
			((ChangeUserInfoActivity) context).finish();
			break;

		default:
			break;
		}
	}
	
	
	
	public static String transformIDcard(String iDcard) {
		if (TextUtils.isEmpty(iDcard)) {
			return "";
		}
		if (iDcard.length() != 18) {
			return iDcard;
		}
		String str1 = iDcard.substring(0, 6);
		String str2 = "********";
		String str3 = iDcard.substring(14);
		return str1 + str2 + str3;
	}

	public static String transformMobile(String mobilephone) {
		if (TextUtils.isEmpty(mobilephone)) {
			return "";
		}
		if (mobilephone.length() != 11) {
			return mobilephone;
		}
		String str1 = mobilephone.substring(0, 3);
		String str2 = "****";
		String str3 = mobilephone.substring(7);
		return str1 + str2 + str3;
	}
}
