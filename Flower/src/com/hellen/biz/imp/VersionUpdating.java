/**上午11:00:57
 * @author zhangyh2
 * VersionUpdating.java
 * TODO
 */
package com.hellen.biz.imp;

import com.hellen.base.APP;
import com.hellen.base.BaseListener;
import com.hellen.base.ConsHttpUrl;
import com.hellen.biz.VersionUpdatingInterface.VersionUpdatingBiz;
import com.hellen.common.HttpClient;
import com.hellen.common.LogApp;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @author zhangyh2 VersionUpdating 上午11:00:57 TODO
 */
public class VersionUpdating implements VersionUpdatingBiz {

	private String TAG = getClass().getSimpleName();

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "onStart");
	}

	@Override
	public void versionUpdate(final BaseListener<String> listener) {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "versionUpdate");

		int versionCode = APP.getInstance().getVersionCode();
		String channel = APP.getInstance().getChannelName();
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("version", versionCode+"");
		params.addQueryStringParameter("type", "0");
		params.addQueryStringParameter("channel", channel);
		HttpClient.get(APP.getInstance(), ConsHttpUrl.VERSIONUPDATE, params,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						if (arg0.result != null) {
							LogApp.i(TAG, arg0.result.toString());
							listener.onSucces(arg0.result.toString());
						} else {
							listener.onFaild(1, "arg0 is null");
						}
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						listener.onFaild(0, arg1);
					}
				});
	}

}
