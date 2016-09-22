/**下午2:23:55
 * @author zhangyh2
 * Succulent.java
 * TODO
 */
package com.hellen.biz.imp;

import java.util.List;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.hellen.base.APP;
import com.hellen.base.BaseListener;
import com.hellen.base.ConsHttpUrl;
import com.hellen.bean.Bannar;
import com.hellen.bean.BaseModel;
import com.hellen.bean.BaseModelPaser;
import com.hellen.biz.SucculentInterface.SucculentBiz;
import com.hellen.common.HttpClient;
import com.hellen.common.LogApp;
import com.hellen.common.SharePreferHelp;
import com.hellen.common.ToastApp;
import com.hellen.flower.R;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @author zhangyh2 Succulent 下午2:23:55 TODO
 */
public class Succulent implements SucculentBiz {

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
	 * @see
	 * com.hellen.biz.SucculentInterface.SucculentBiz#findSucculent(com.hellen
	 * .base.BaseListener)
	 */
	@Override
	public void findSucculent(final BaseListener<List<Bannar>> listener) {
		// TODO Auto-generated method stub
		if (!APP.isNetworkConnected(APP.getInstance())) {
			ToastApp.showToast(R.string.neterror);
			// 用户没有网络，查看是否有缓存。有缓存则进行加载
			String resultBanner = SharePreferHelp.getValue(ConsHttpUrl.BANNER,
					null);
			if (TextUtils.isEmpty(resultBanner)) {
				return;
			}
			paserSucc(listener, resultBanner);
			return;
		}
		RequestParams requestParams = new RequestParams();
		requestParams.addQueryStringParameter("bannar", "1");
		HttpClient.get(APP.getInstance(), ConsHttpUrl.BANNER, requestParams,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						SharePreferHelp.putValue(ConsHttpUrl.BANNER,
								arg0.result.toString());
						paserSucc(listener, arg0.result.toString());
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						listener.onFaild(0, arg1);
						String resultBanner = SharePreferHelp.getValue(
								ConsHttpUrl.BANNER, null);
						if (TextUtils.isEmpty(resultBanner)) {
							return;
						}
						paserSucc(listener, resultBanner);
					}
				});
	}

	/**
	 * 上午11:01:42
	 * 
	 * @author zhangyh2 TODO <a>对用户数据进行解析
	 * @param listener
	 */
	private void paserSucc(BaseListener<List<Bannar>> listener, String result) {
		BaseModel<List<Bannar>> data = new BaseModelPaser<List<Bannar>>()
				.paserJsonImp(result, new TypeToken<List<Bannar>>() {
				});
		if (data != null && data.getCode() == 200) {
			LogApp.i(result);
			if (data.getData() != null) {
				listener.onSucces(data.getData());
			} else {
				listener.onFaild(0, "bannars is null!");
			}
		} else {
			ToastApp.showToast(data.getMsg() + "");
			listener.onFaild(1, "parser json wrong");
		}
	}
}
