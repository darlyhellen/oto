/**
 * 下午3:15:42
 * @author zhangyh2
 * $
 * LoginActivity.java
 * TODO
 */
package com.darly.dlclent.ui.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.JsonUtil;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.UserInfoData;
import com.darly.dlclent.ui.MainActivity;
import com.darly.dlclent.widget.clearedit.LoginClearEdit;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 LoginActivity $ 下午3:15:42 TODO 登录页面
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements OnClickListener {

	/**
	 * 下午3:01:44 TODO 用户名
	 */
	@ViewInject(R.id.act_login_name)
	private LoginClearEdit name;
	/**
	 * 下午3:01:51 TODO 用户密码
	 */
	@ViewInject(R.id.act_login_pass)
	private LoginClearEdit pass;

	/**
	 * 下午3:08:38 TODO 登陆
	 */
	@ViewInject(R.id.act_login_login)
	private Button login;

	/**
	 * 上午10:04:52 TODO 注册
	 */
	@ViewInject(R.id.act_login_regest)
	private Button regest;

	private boolean isUserName;

	private boolean isPassWord;
	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	private boolean resetPass;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.activities.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	public void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		back.setVisibility(View.VISIBLE);
		title.setText(R.string.login);
		other.setVisibility(View.INVISIBLE);

		// 设置用户名
		name.setTarget("用户名称", "手机号/邮箱/QQ");
		// 设置密码
		pass.setTarget("用户密码", "请输入您的密码");

		login.setText(R.string.login);
		login.setBackgroundResource(R.drawable.btn_normal);
		login.setTextColor(getResources().getColor(R.color.pop_back));
		login.setClickable(false);

		regest.setText(R.string.regest);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		resetPass = getIntent().getBooleanExtra("ResetPass", false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		regest.setOnClickListener(this);
		back.setOnClickListener(this);
		name.getText().addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (s != null && !"".equals(s.toString())) {
					isUserName = true;
					if (isUserName && isPassWord) {
						login.setBackgroundResource(R.drawable.btn_select);
						login.setTextColor(getResources().getColor(
								R.color.white));
						login.setClickable(true);
						login.setOnClickListener(LoginActivity.this);
					}
				} else {
					isUserName = false;
					login.setBackgroundResource(R.drawable.btn_normal);
					login.setTextColor(getResources()
							.getColor(R.color.pop_back));
					login.setClickable(false);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s != null && s.toString().contains(" ")) {
					ToastApp.showToast(R.string.login_contain_null);
					isUserName = false;
					login.setBackgroundResource(R.drawable.btn_normal);
					login.setTextColor(getResources()
							.getColor(R.color.pop_back));
					login.setClickable(false);
				}
				Pattern pattern = Pattern
						.compile("([^\\._\\w\\u4e00-\\u9fa5])*");
				Matcher matcher = pattern.matcher(s);
				if (matcher.matches()) {
					ToastApp.showToast(R.string.login_contain_mode);
					isUserName = false;
					login.setBackgroundResource(R.drawable.btn_normal);
					login.setTextColor(getResources()
							.getColor(R.color.pop_back));
					login.setClickable(false);
				}
			}
		});

		pass.getText().setInputType(
				InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		pass.getText().addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (s != null && !"".equals(s.toString())) {
					isPassWord = true;
					if (isUserName && isPassWord) {
						login.setBackgroundResource(R.drawable.btn_select);
						login.setTextColor(getResources().getColor(
								R.color.white));
						login.setClickable(true);
						login.setOnClickListener(LoginActivity.this);
					}
				} else {
					isPassWord = false;
					login.setBackgroundResource(R.drawable.btn_normal);
					login.setTextColor(getResources()
							.getColor(R.color.pop_back));
					login.setClickable(false);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s != null && s.toString().contains(" ")) {
					ToastApp.showToast(R.string.login_contain_null);
					isPassWord = false;
					login.setBackgroundResource(R.drawable.btn_normal);
					login.setTextColor(getResources()
							.getColor(R.color.pop_back));
					login.setClickable(false);
				}
			}
		});
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
		case R.id.act_login_login:
			String username = name.getText().getText().toString();
			String paseword = pass.getText().getText().toString();
			if (!APP.isNetworkConnected(this)) {
				ToastApp.showToast(R.string.neterror);
				return;
			}
			if (username == null || username.length() == 0 || paseword == null
					|| paseword.length() == 0) {
				ToastApp.showToast("用户名密码不为空");
				return;
			}
			if (!APP.isNetworkConnected(this)) {
				ToastApp.showToast(R.string.neterror);
				return;
			}
			login.setClickable(false);
			String url = "";
			if (url == null || url.length() == 0) {
				String jsonString = null;
				if (new Random().nextBoolean()) {
					UserInfoData user = new UserInfoData(
							username,
							"http://pic13.nipic.com/20110424/818468_090858462000_2.jpg",
							"13891431454", "男", "610123198610036773", "70.0",
							paseword);
					BaseModel<UserInfoData> mo = new BaseModel<UserInfoData>(
							200, "", user);
					jsonString = JsonUtil.pojo2Json(mo);
				} else {
					BaseModel<UserInfoData> mo = new BaseModel<UserInfoData>(
							110, "用户名或密码错误", null);
					jsonString = JsonUtil.pojo2Json(mo);
				}
				LogUtils.i(jsonString);
				isLogin(jsonString);
			} else {
				List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
				params.add(new BasicNameValuePair("username", username));
				params.add(new BasicNameValuePair("paseword", paseword));
				HttpClient.post("", params.toString(),
						new RequestCallBack<String>() {

							@Override
							public void onSuccess(ResponseInfo<String> arg0) {
								// TODO Auto-generated method stub
								login.setClickable(true);
								isLogin(arg0.result);
							}

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								// TODO Auto-generated method stub
								login.setClickable(true);
								ToastApp.showToast(R.string.neterror_norespanse);
							}
						});
			}
			break;
		case R.id.act_login_regest:
			startActivity(new Intent(this, RegisterActivity.class));
			finish();
			break;
		case R.id.header_back:
			finish();
			break;
		default:
			break;
		}
	}

	/**
	 * 下午3:06:27
	 * 
	 * @author zhangyh2 TODO 判断用户是否成功登录，数据是否返回
	 */
	private void isLogin(String datas) {
		// TODO Auto-generated method stub
		if (datas == null) {
			login.setClickable(true);
			return;
		}
		BaseModel<UserInfoData> data = new BaseModelPaser<UserInfoData>()
				.paserJson(datas, new TypeToken<UserInfoData>() {
				});
		if (data != null && data.getCode() == 200) {
			LogUtils.i(data.toString());
			// 登录成功
			SharePreferHelp.putValue(APPEnum.ISLOGIN.getDec(), true);
			SharePreferHelp.putValue(APPEnum.USERINFO.getDec(), datas);
			// 结束Login
			if (resetPass) {
				Intent intent = new Intent(this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}
			finish();
		} else {
			login.setClickable(true);
			ToastApp.showToast(data.getMsg());
		}
	}

}
