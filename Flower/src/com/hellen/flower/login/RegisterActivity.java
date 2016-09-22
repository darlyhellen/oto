/**
 * 上午10:08:06
 * @author zhangyh2
 * $
 * RegisterActivity.java
 * TODO
 */
package com.hellen.flower.login;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.hellen.base.BaseActivity;
import com.hellen.base.BasePresenter;
import com.hellen.biz.RegisterInterface;
import com.hellen.common.LogApp;
import com.hellen.flower.R;
import com.hellen.presenter.RegisterPresenter;
import com.hellen.widget.clearedit.LoginClearEdit;
import com.hellen.widget.header.HeaderView;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 RegisterActivity $ 上午10:08:06 TODO
 */
@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity implements
		RegisterInterface, OnClickListener {

	private String tag = getClass().getSimpleName();
	@ViewInject(R.id.act_register_tel)
	private LoginClearEdit tel;
	@ViewInject(R.id.act_register_code)
	private LoginClearEdit code;
	@ViewInject(R.id.act_register_register)
	private Button register;
	@ViewInject(R.id.act_register_clickcode)
	private TextView getcode;
	@ViewInject(R.id.regist_header_view)
	private HeaderView header;
	private RegisterPresenter registPresenter;

	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		registPresenter = new RegisterPresenter(this);
		registPresenter.setTimer();
	}

	@Override
	public void setPersenter(BasePresenter persenter) {
		// TODO Auto-generated method stub
		LogApp.i(tag, "setPersenter" + persenter);
	}

	@Override
	public void setView() {
		// TODO Auto-generated method stub
		LogApp.i(tag, "setView");
		header.onSetTitle(R.string.regest);
		header.onDisBack(false);
		tel.setTarget("手机号", "手机号");
		// 设置密码
		code.setTarget("验证码", "请输入您手机收到的验证码");
		getcode.setText(R.string.register_code);
		register.setText(R.string.register_next);
	}

	@Override
	public String findTel() {
		// TODO Auto-generated method stub
		return tel.getText().getText().toString().trim();
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		header.getBack().setOnClickListener(this);
		register.setOnClickListener(this);
		getcode.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		registPresenter.onClickDown(this, v);
	}

	@Override
	public void gtoText(String msg) {
		// TODO Auto-generated method stub
		getcode.setText(msg);
	}

	@Override
	public void gtoText(int res) {
		// TODO Auto-generated method stub
		getcode.setText(res);
	}

	@Override
	public void disClicked() {
		// TODO Auto-generated method stub
		getcode.setClickable(false);
	}

	@Override
	public void enableClicked() {
		// TODO Auto-generated method stub
		getcode.setClickable(true);
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		finish();
	}

}
