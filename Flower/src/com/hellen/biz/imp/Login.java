/**下午3:42:16
 * @author zhangyh2
 * LoginUser.java
 * TODO
 */
package com.hellen.biz.imp;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.telephony.TelephonyManager;

import com.google.gson.reflect.TypeToken;
import com.hellen.base.APP;
import com.hellen.base.BaseListener;
import com.hellen.base.ConsHttpUrl;
import com.hellen.base.ConsMVP;
import com.hellen.bean.BaseModel;
import com.hellen.bean.BaseModelPaser;
import com.hellen.bean.UserInfo;
import com.hellen.biz.LoginInterface;
import com.hellen.common.HttpClient;
import com.hellen.common.LogApp;
import com.hellen.common.SharePreferHelp;
import com.hellen.common.ToastApp;
import com.hellen.flower.R;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;

/**
 * @author zhangyh2 LoginUser 下午3:42:16 TODO 用户登录获取数据传递给了接口
 */
public class Login implements LoginInterface {

	private String tag = getClass().getSimpleName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BasePresenter#onStart()
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.MainInterface#initPush(android.content.Context)
	 */
	@Override
	public void initPush(Context context) {
		// TODO Auto-generated method stub
		LogApp.i(tag, "initPush");
		PushAgent mPushAgent = PushAgent.getInstance(context);
		mPushAgent.setDebugMode(true);
		mPushAgent.setPushCheck(true);
		// sdk开启通知声音
		mPushAgent
				.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
		// 开启推送并设置注册的回调处理
		mPushAgent.enable(new IUmengRegisterCallback() {

			@Override
			public void onRegistered(final String registrationId) {
				new Handler().post(new Runnable() {
					@Override
					public void run() {
						// onRegistered方法的参数registrationId即是device_token
						LogApp.i(tag, "device_token---->>" + registrationId);
					}
				});
			}
		});

		// 消息推送SDK里，有一个类UmengMessageHandler，是负责消息的处理的。该类拥有两个成员函数，分别为：
		mPushAgent.setMessageHandler(new UmengMessageHandler() {

			// 负责处理自定义消息，需由用户处理。
			// 若开发者需要处理自定义消息，则需要将方法dealWithCustomMessage()重写，自定义消息的内容则存放在UMessage.custom里。
			@Override
			public void dealWithCustomMessage(Context context,
					final UMessage msg) {
				// TODO Auto-generated method stub
				new Handler(context.getMainLooper()).post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// 对自定义消息的处理方式，点击或者忽略
						boolean isClickOrDismissed = true;
						if (isClickOrDismissed) {
							// 自定义消息的点击统计
							UTrack.getInstance(APP.getInstance())
									.trackMsgClick(msg);
						} else {
							// 自定义消息的忽略统计
							UTrack.getInstance(APP.getInstance())
									.trackMsgDismissed(msg);
						}
						ToastApp.showToast(msg.custom);
						LogApp.i(tag, msg.custom);
					}
				});
			}

			// 负责处理通知消息，该方法已经由消息推送SDK 完成；
			@Override
			public void dealWithNotificationMessage(Context context,
					UMessage msg) {
				// TODO Auto-generated method stub
				super.dealWithNotificationMessage(context, msg);
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.LoginPresent#login(java.lang.String,
	 * java.lang.String, com.hellen.biz.OnLoginListener)
	 */
	@Override
	public void login_in(Context context, String username, String password,
			final BaseListener<UserInfo> listener) {
		// TODO Auto-generated method stub
		if (!APP.isNetworkConnected(APP.getInstance())) {
			ToastApp.showToast(R.string.neterror);
			return;
		}
		if (username == null || username.length() == 0 || password == null
				|| password.length() == 0) {
			ToastApp.showToast("用户名密码不为空");
			return;
		}
		if (!APP.isNetworkConnected(APP.getInstance())) {
			ToastApp.showToast(R.string.neterror);
			return;
		}
		JSONObject ob = new JSONObject();
		try {
			ob.put("username", username);
			ob.put("password", password);
			ob.put("sim", getTelNum(context));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpClient.post(ConsHttpUrl.LOGIN_URl, ob.toString(),
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						LogApp.i(arg0.result.toString());

						BaseModel<UserInfo> data = new BaseModelPaser<UserInfo>()
								.paserJson(arg0.result.toString(),
										new TypeToken<UserInfo>() {
										});

						if (data != null && data.getCode() == 200) {
							if (data.getData() != null) {
								listener.onSucces(data.getData());
								// 对其进行解析。当登录成功时
								SharePreferHelp.putValue(
										ConsMVP.USERINFO.getDec(),
										arg0.result.toString());
								SharePreferHelp.putValue(
										ConsMVP.TOKEN.getDec(), data.getData()
												.getToken());
								SharePreferHelp.putValue(
										ConsMVP.ISLOGIN.getDec(), true);
							} else {
								listener.onFaild(0, data.getMsg());
							}

						} else {
							listener.onFaild(0, "解析错误");
						}
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						listener.onFaild(0, arg1);
					}
				});
	}

	private String getTelNum(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);// 取得相关系统服务
		return tm.getLine1Number();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.LoginInterface#login_out(java.lang.String,
	 * java.lang.String, com.hellen.base.BaseListener)
	 */
	@Override
	public void login_out(String username, String password,
			BaseListener<UserInfo> listener) {
		// TODO Auto-generated method stub
		SharePreferHelp.remove(ConsMVP.TOKEN.getDec());
	}
}
