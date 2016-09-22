/**下午3:14:49
 * @author zhangyh2
 * SetPass.java
 * TODO
 */
package com.hellen.biz.imp;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.hellen.base.APP;
import com.hellen.base.BaseListener;
import com.hellen.base.ConsHttpUrl;
import com.hellen.bean.BaseModel;
import com.hellen.bean.BaseModelPaser;
import com.hellen.bean.UserInfo;
import com.hellen.biz.SetPassInterface.SetPassBiz;
import com.hellen.common.HttpClient;
import com.hellen.common.LogApp;
import com.hellen.common.ToastApp;
import com.hellen.flower.R;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @author zhangyh2 SetPass 下午3:14:49 TODO
 */
public class SetPass implements SetPassBiz {

	private String tag = getClass().getSimpleName();

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		LogApp.i(tag, "onStart");

	}

	@Override
	public void registuser(Context context, String username, String password,
			final BaseListener<UserInfo> listener) {
		// TODO Auto-generated method stub
		LogApp.i(tag, "registuser");
		if (!APP.isNetworkConnected(context)) {
			ToastApp.showToast(R.string.neterror);
			return;
		}
		if (TextUtils.isEmpty(password)) {
			ToastApp.showToast("密码不为空");
			return;
		}
		JSONObject ob = new JSONObject();
		try {
			ob.put("tel", username);
			ob.put("pass", password);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpClient.post(ConsHttpUrl.USERREGISTER, ob.toString(),
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						BaseModel<UserInfo> data = new BaseModelPaser<UserInfo>()
								.paserJson(arg0.result.toString(),
										new TypeToken<UserInfo>() {
										});
						LogApp.i(tag, arg0.result.toString());
						if (data != null && data.getCode() == 200) {
							listener.onSucces(data.getData());
						} else {
							listener.onFaild(1, data.getMsg());
						}
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						listener.onFaild(0, arg1);
					}
				});
	}

}
