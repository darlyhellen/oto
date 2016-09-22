/**
 * 上午10:28:00
 * @author zhangyh2
 * $
 * SetPassActivity.java
 * TODO
 */
package com.hellen.flower.login;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hellen.base.BaseActivity;
import com.hellen.base.BasePresenter;
import com.hellen.biz.SetPassInterface;
import com.hellen.common.LogApp;
import com.hellen.flower.R;
import com.hellen.presenter.SetPassPresenter;
import com.hellen.widget.clearedit.LoginClearEdit;
import com.hellen.widget.header.HeaderView;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 SetPassActivity $ 上午10:28:00 TODO
 */
@ContentView(R.layout.activity_setpass)
public class SetPassActivity extends BaseActivity implements SetPassInterface,
		OnClickListener {

	private String tag = getClass().getSimpleName();
	@ViewInject(R.id.act_pass_setpass)
	private LoginClearEdit pass;
	@ViewInject(R.id.act_pass_repass)
	private LoginClearEdit repass;
	@ViewInject(R.id.act_pass_register)
	private Button register;

	private String tel;
	@ViewInject(R.id.setpass_header_view)
	private HeaderView header;

	private SetPassPresenter passPresenter;

	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		header.onSetTitle(R.string.register_pass);
		pass.setTarget("密         码", "请输入6-16位密码");
		pass.getText().setInputType(
				InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		repass.setTarget("二次密码", "请重新输入密码");
		repass.getText().setInputType(
				InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		register.setText(R.string.regest);

		if (getIntent() != null) {
			tel = getIntent().getStringExtra("tel");
		}
		if (tel == null || tel.length() == 0) {
			tel = savedInstanceState.getString("tel");
		}
		passPresenter = new SetPassPresenter(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putString("tel", tel);
		super.onSaveInstanceState(outState);

	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.act_pass_register:
			// 调用接口注册完成。
			passPresenter.register(this);
			break;

		default:
			break;
		}
	}

	@Override
	public void setPersenter(BasePresenter persenter) {
		// TODO Auto-generated method stub
		LogApp.i(tag, "setPersenter" + persenter);
	}

	@Override
	public String findUserName() {
		// TODO Auto-generated method stub
		return tel;
	}

	@Override
	public String findUserPass() {
		// TODO Auto-generated method stub
		return pass.getText().toString().trim();
	}

	@Override
	public void setDisableClick() {
		// TODO Auto-generated method stub
		register.setClickable(false);
	}

	@Override
	public void setEnableClick() {
		// TODO Auto-generated method stub
		register.setClickable(true);
	}

	@Override
	public void actEnd() {
		// TODO Auto-generated method stub
		finish();
	}

}
