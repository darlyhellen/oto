/**
 * 下午3:15:42
 * @author zhangyh2
 * $
 * LoginActivity.java
 * TODO
 */
package com.darly.dlclent.ui.login;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.SharePreferCache;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.ECLoginUser;
import com.darly.dlclent.ui.MainActivity;
import com.darly.dlclent.ui.verify.VerifyActivity;
import com.darly.dlclent.widget.clearedit.LoginClearEdit;
import com.darly.dlclent.widget.load.ProgressDialogUtil;
import com.darly.im.common.CCPAppManager;
import com.darly.im.common.utils.ECPreferenceSettings;
import com.darly.im.common.utils.ECPreferences;
import com.darly.im.core.ClientUser;
import com.darly.im.core.ContactsCache;
import com.darly.im.storage.ContactSqlManager;
import com.darly.im.ui.SDKCoreHelper;
import com.darly.im.ui.contact.ContactLogic;
import com.darly.im.ui.contact.ECContacts;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECInitParams;
import com.yuntongxun.ecsdk.SdkErrorCode;

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

	private ProgressDialogUtil util;
	// 云通讯调用广播
	private InternalReceiver internalReceiver;

	/**
	 * 下午3:05:20 TODO 用户登录信息
	 */
	private BaseModel<ECLoginUser> data;

	private String username;
	private String paseword;

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
			username = name.getText().getText().toString();
			paseword = pass.getText().getText().toString();
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
			util.show();
			JSONObject ob = new JSONObject();
			try {
				ob.put("username", username);
				ob.put("password", paseword);
				ob.put("sim", getTelNum());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpClient.post(ConsHttpUrl.USERLOGIN, ob.toString(),
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							// TODO Auto-generated method stub
							login.setClickable(true);
							isLogin(arg0.result);
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub
							arg0.printStackTrace();
							util.cancel();
							login.setClickable(true);
							ToastApp.showToast(R.string.neterror_norespanse);
						}
					});
			break;
		case R.id.act_login_regest:
			startActivity(new Intent(this, RegisterActivity.class));
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
			util.cancel();
			return;
		}
		LogUtils.i(datas);
		data = new BaseModelPaser<ECLoginUser>().paserJson(datas,
				new TypeToken<ECLoginUser>() {
				});
		if (data != null && data.getCode() == 200) {
			LogUtils.i(data.toString());
			// 登录成功
			SharePreferHelp.putValue(APPEnum.USERINFO.getDec(), datas);
			SharePreferHelp.putValue(APPEnum.TOKEN.getDec(), data.getData()
					.getToken());
			SharePreferHelp.putValue(APPEnum.USERTEL.getDec(), data.getData()
					.getTel());
			SharePreferHelp.putValue(APPEnum.USERVOIP.getDec(), data.getData()
					.getVoipAccount());
			loginEC(data.getData());
		} else {
			login.setClickable(true);
			util.cancel();
			ToastApp.showToast(data.getMsg());
		}
	}

	private String getTelNum() {
		TelephonyManager tm = (TelephonyManager) this
				.getSystemService(TELEPHONY_SERVICE);// 取得相关系统服务
		return tm.getLine1Number();
	}

	ClientUser clientUser;

	private void loginEC(ECLoginUser ecLoginUser) {
		// 用户的云通讯登录。
		registerReceiver(new String[] { SDKCoreHelper.ACTION_SDK_CONNECT });
		// 广播注册
		clientUser = new ClientUser(ecLoginUser.getTel());
		clientUser.setUserName(ecLoginUser.getName());
		clientUser.setUserId(ecLoginUser.getVoipAccount());
		clientUser.setPassword(ecLoginUser.getVoipPwd());
		clientUser.setAppKey(ConsHttpUrl.APPKEY);
		clientUser.setAppToken(ConsHttpUrl.APPTOKEN);
		clientUser.setLoginAuthType(ECInitParams.LoginAuthType.PASSWORD_AUTH);
		CCPAppManager.setClientUser(clientUser);
		SDKCoreHelper.init(this, ECInitParams.LoginMode.FORCE_LOGIN);
		SDKCoreHelper.release();

		ECContacts contacts = new ECContacts();
		contacts.setNickname(ecLoginUser.getName());
		contacts.setClientUser(clientUser, ecLoginUser.getTel(),
				ecLoginUser.getIcon());
		ContactSqlManager.insertContact(contacts,
				("男".equals(ecLoginUser.getSex()) ? 1 : 0), true);

		JSONObject ob = new JSONObject();
		try {
			ob.put("tel", ecLoginUser.getTel());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpClient.post(ConsHttpUrl.FRIEND, ob.toString(),
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						LogUtils.i(arg0.result);
						SharePreferCache.putValue(
								SharePreferHelp.getValue(
										APPEnum.USERVOIP.getDec(), null)
										+ SharePreferCache.CACHE,
								APPEnum.ECCONTACTS.getDec(), arg0.result);
						ContactsCache.getInstance().load();
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub

					}
				});
		doLauncherAction();
		util.cancel();
	}

	protected final void registerReceiver(String[] actionArray) {
		if (actionArray == null) {
			return;
		}
		IntentFilter intentfilter = new IntentFilter();
		intentfilter.addAction(SDKCoreHelper.ACTION_KICK_OFF);
		for (String action : actionArray) {
			intentfilter.addAction(action);
		}
		if (internalReceiver == null) {
			internalReceiver = new InternalReceiver();
		}
		registerReceiver(internalReceiver, intentfilter);
	}

	/**
	 * @author zhangyh2 InternalReceiver 下午2:57:16 TODO 自定义广播。注册时调用此广播
	 */
	private class InternalReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent == null || intent.getAction() == null) {
				return;
			}
			handleReceiver(context, intent);
		}
	}

	/**
	 * 如果子界面需要拦截处理注册的广播 需要实现该方法
	 * 
	 * @param context
	 * @param intent
	 */
	@SuppressWarnings("deprecation")
	protected void handleReceiver(Context context, Intent intent) {
		// 广播处理
		if (intent == null) {
			return;
		}
		if (SDKCoreHelper.ACTION_KICK_OFF.equals(intent.getAction())) {
			finish();
		}
		// 处理注册完成的信息
		int error = intent.getIntExtra("error", -1);
		if (SDKCoreHelper.ACTION_SDK_CONNECT.equals(intent.getAction())) {
			// 初始注册结果，成功或者失败
			if (SDKCoreHelper.getConnectState() == ECDevice.ECConnectState.CONNECT_SUCCESS
					&& error == SdkErrorCode.REQUEST_SUCCESS) {
				try {
					saveAccount();
				} catch (InvalidClassException e) {
					e.printStackTrace();
				}
				return;
			}
			if (intent.hasExtra("error")) {
				if (SdkErrorCode.CONNECTTING == error) {
					return;
				}
				if (error == -1) {
					LogUtils.i("请检查登陆参数是否正确[" + error + "]");
				} else {
				}
				LogUtils.i("登陆失败，请稍后重试[" + error + "]");
				SDKCoreHelper.init(this, ECInitParams.LoginMode.FORCE_LOGIN);
			}
		}
	}

	/**
	 * 下午3:07:31
	 * 
	 * @author zhangyh2 TODO 保存云通讯信息
	 */
	private void saveAccount() throws InvalidClassException {
		ECPreferences.savePreference(ECPreferenceSettings.SETTINGS_REGIST_AUTO,
				clientUser.toString(), true);
		// ContactSqlManager.insertContacts(contacts);
		ArrayList<ECContacts> objects = ContactLogic.initContacts();
		objects = ContactLogic.converContacts(objects);
		ContactSqlManager.insertContacts(objects);
	}

	/**
	 * 下午3:10:34
	 * 
	 * @author zhangyh2 TODO 成功进行跳转
	 */
	private void doLauncherAction() {
		// 结束Login
		if (!data.getData().getSame().equals("true")) {
			// 手机信息变更，则进行提示,进入验证登录页面
			SharePreferHelp.putValue(APPEnum.ISLOGIN.getDec(), false);
			Intent intents = new Intent(this, VerifyActivity.class);
			intents.putExtra("tel", username);
			intents.putExtra("pass", paseword);
			startActivity(intents);
			finish();
		} else {
			// 直接进入MainActivity
			SharePreferHelp.putValue(APPEnum.ISLOGIN.getDec(), true);
			if (resetPass) {
				Intent intent = new Intent(this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			finish();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#finish()
	 */
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		if (internalReceiver != null) {
			try {
				unregisterReceiver(internalReceiver);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
}
