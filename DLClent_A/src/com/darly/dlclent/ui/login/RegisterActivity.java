/**
 * 上午10:08:06
 * @author zhangyh2
 * $
 * RegisterActivity.java
 * TODO
 */
package com.darly.dlclent.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.widget.clearedit.LoginClearEdit;
import com.darly.dlclent.widget.load.ProgressDialogUtil;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 RegisterActivity $ 上午10:08:06 TODO
 */
@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.act_register_tel)
	private LoginClearEdit tel;
	@ViewInject(R.id.act_register_code)
	private LoginClearEdit code;
	@ViewInject(R.id.act_register_register)
	private Button register;
	@ViewInject(R.id.act_register_clickcode)
	private TextView getcode;

	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	private ProgressDialogUtil util;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		back.setVisibility(View.VISIBLE);
		title.setText(R.string.regest);
		other.setVisibility(View.INVISIBLE);

		tel.setTarget("手机号", "手机号");
		// 设置密码
		code.setTarget("验证码", "请输入您手机收到的验证码");
		getcode.setText(R.string.register_code);

		register.setText(R.string.register_next);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		util = new ProgressDialogUtil(this);
		util.setMessage(R.string.xlistview_header_hint_loading);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		back.setOnClickListener(this);
		register.setOnClickListener(this);
		getcode.setOnClickListener(this);
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
		case R.id.act_register_register:
			util.show();
			Intent intent = new Intent(this, SetPassActivity.class);
			intent.putExtra("tel", tel.getText().toString().trim());
			startActivity(intent);
			util.cancel();
			finish();
			break;
		case R.id.header_back:
			finish();
			break;
		case R.id.act_register_clickcode:
			getcode.setClickable(false);
			handler.sendEmptyMessage(APPEnum.DB_SELECT);
			break;

		default:
			break;
		}
	}

	private int time = 60;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case APPEnum.DB_SELECT:
				// 点击获取验证码，修改显示文字。
				if (time > 0) {
					getcode.setText(time + "s");
					handler.sendEmptyMessageDelayed(APPEnum.DB_INSERT, 1000);
				} else {
					getcode.setText(R.string.register_code_two);
					getcode.setClickable(true);
					time = 60;
				}
				break;
			case APPEnum.DB_INSERT:
				time--;
				handler.sendEmptyMessage(APPEnum.DB_SELECT);
				break;

			default:
				break;
			}
		}

	};

}
