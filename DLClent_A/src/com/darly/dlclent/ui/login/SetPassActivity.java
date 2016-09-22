/**
 * 上午10:28:00
 * @author zhangyh2
 * $
 * SetPassActivity.java
 * TODO
 */
package com.darly.dlclent.ui.login;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.UserInfoData;
import com.darly.dlclent.widget.clearedit.LoginClearEdit;
import com.darly.dlclent.widget.load.ProgressDialogUtil;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
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

	private ProgressDialogUtil util;

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
			// 调用接口注册完成。
			completeRegest();
			break;

		default:
			break;
		}
	}

	/**
	 * 下午2:22:21
	 * 
	 * @author zhangyh2 TODO
	 */
	private void completeRegest() {
		// TODO Auto-generated method stub
		String paseword = pass.getText().getText().toString();
		if (!APP.isNetworkConnected(this)) {
			ToastApp.showToast(R.string.neterror);
			return;
		}
		if (paseword == null || paseword.length() == 0) {
			ToastApp.showToast("密码不为空");
			return;
		}
		if (!APP.isNetworkConnected(this)) {
			ToastApp.showToast(R.string.neterror);
			return;
		}
		JSONObject ob = new JSONObject();
		try {
			ob.put("tel", tel);
			ob.put("pass", paseword);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpClient.post(ConsHttpUrl.USERREGISTER, ob.toString(),
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						util.cancel();
						isRegest(arg0.result);
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						arg0.printStackTrace();
						util.cancel();
						ToastApp.showToast(R.string.neterror_norespanse);
					}
				});
	}

	/**
	 * 下午2:28:20
	 * 
	 * @author zhangyh2 TODO
	 */
	protected void isRegest(String result) {
		// TODO Auto-generated method stub
		if (result == null) {
			return;
		}
		LogUtils.i(result);
		BaseModel<UserInfoData> data = new BaseModelPaser<UserInfoData>()
				.paserJson(result, new TypeToken<UserInfoData>() {
				});
		if (data != null && data.getCode() == 200) {
			// 结束Login
			ToastApp.showToast(data.getMsg());
			finish();
		} else {
			ToastApp.showToast(data.getMsg());
		}
	}

}
