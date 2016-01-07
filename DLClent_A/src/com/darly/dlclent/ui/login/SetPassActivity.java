/**
 * 上午10:28:00
 * @author zhangyh2
 * $
 * SetPassActivity.java
 * TODO
 */
package com.darly.dlclent.ui.login;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.widget.clearedit.LoginClearEdit;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 SetPassActivity $ 上午10:28:00 TODO
 */
@ContentView(R.layout.activity_setpass)
public class SetPassActivity extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.act_pass_setpass)
	private LoginClearEdit pass;
	@ViewInject(R.id.act_pass_repass)
	private LoginClearEdit repass;
	@ViewInject(R.id.act_pass_register)
	private Button register;

	private String tel;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		title.setText(R.string.register_pass);
		pass.setTarget("密         码", "请输入6-16位密码");
		repass.setTarget("二次密码", "请重新输入密码");
		register.setText(R.string.regest);

		if (getIntent() != null) {
			tel = getIntent().getStringExtra("tel");
		}
		if (tel == null || tel.length() == 0) {
			tel = savedInstanceState.getString("tel");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.FragmentActivity#onSaveInstanceState(android.os
	 * .Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putString("tel", tel);
		super.onSaveInstanceState(outState);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		register.setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.act_pass_register:

			break;

		default:
			break;
		}
	}

}
