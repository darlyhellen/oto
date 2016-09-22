package com.hellen.flower.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hellen.base.BaseActivity;
import com.hellen.base.BasePresenter;
import com.hellen.biz.LoginInterface;
import com.hellen.common.LogApp;
import com.hellen.common.NetworkReachabilityUtil;
import com.hellen.common.ToastApp;
import com.hellen.flower.CircleActivity;
import com.hellen.flower.R;
import com.hellen.presenter.LoginPresenter;
import com.hellen.widget.clearedit.ClearEditText;
import com.hellen.widget.header.HeaderView;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.socialize.bean.SHARE_MEDIA;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements
		LoginInterface.MainView, OnClickListener {

	private String TAG = getClass().getSimpleName();

	@ViewInject(R.id.id_header_view)
	public HeaderView header;
	@ViewInject(R.id.id_et_username)
	public ClearEditText name;
	@ViewInject(R.id.id_et_password)
	public ClearEditText pass;
	@ViewInject(R.id.id_btn_login)
	public Button mBtnLogin;
	@ViewInject(R.id.id_btn_clear)
	public Button mBtnClear;
	@ViewInject(R.id.id_main_tv)
	public TextView tv;

	@ViewInject(R.id.wechat)
	private ImageButton wx;

	private LoginPresenter mUserLoginPresenter;

	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		header.onSetTitle(R.string.app_name);
		header.onDisBack(true);
		mUserLoginPresenter = new LoginPresenter(this);
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		name.setHint("手机号");
		name.setHintTextColor(getResources().getColor(
				R.color.white_color_disable));
		pass.setHint("密码");
		pass.setHintTextColor(getResources().getColor(
				R.color.white_color_disable));
	}

	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		mBtnLogin.setOnClickListener(this);
		mBtnClear.setOnClickListener(this);
		wx.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.id_btn_login:
			if (NetworkReachabilityUtil.isNetworkConnected(this)) {
				mUserLoginPresenter.login(this);
			} else {
				ToastApp.showToast(R.string.neterror);
			}
			LogApp.i("onClick--mUserLoginPresenter.login()");
			break;
		case R.id.id_btn_clear:
			startActivity(new Intent(this, RegisterActivity.class));
			finish();
			LogApp.i("onClick--mUserLoginPresenter.clear()");
			break;
		case R.id.wechat:
			LogApp.i("onClick--R.id.wechat");
			mUserLoginPresenter.thirdLogin(this,SHARE_MEDIA.WEIXIN);
			break;
			
		default:
			break;
		}
	}

	@Override
	public void onLoginFailed() {
		LogApp.i(TAG, "onLoginFailed");
		startActivity(new Intent(this, CircleActivity.class));
	}

	@Override
	public void onLoginSuccess() {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "onLoginSuccess");
		finish();
		// startActivity(new Intent(this, WebViewActivity.class));
	}

	@Override
	public void setDisableClick() {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "setDisableClick");
		mBtnClear.setClickable(false);
		mBtnLogin.setClickable(false);
	}

	@Override
	public void setEnableClick() {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "setEnableClick");
		mBtnClear.setClickable(true);
		mBtnLogin.setClickable(true);
	}

	@Override
	public void setPersenter(BasePresenter persenter) {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "setPersenter" + persenter);
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return name.getText().toString().trim();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return pass.getText().toString().trim();
	}
	
	/* (non-Javadoc)
	 * @see com.hellen.base.BaseActivity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		mUserLoginPresenter.initAPI(requestCode, resultCode, data);
	}

}
