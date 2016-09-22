/**下午3:42:28
 * @author zhangyh2
 * Main.java
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
import com.hellen.biz.FragmentMainInterface;
import com.hellen.common.HttpClient;
import com.hellen.common.LogApp;
import com.hellen.common.SharePreferHelp;
import com.hellen.common.ToastApp;
import com.hellen.flower.R;
import com.hellen.widget.carousel.Carousel.CarouselModel;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @author zhangyh2 Main 下午3:42:28 TODO
 */
public class FragmentMain implements FragmentMainInterface.MainBiz {

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
	 * com.hellen.biz.MainInterface.MainBiz#main_bannar(com.hellen.base.BaseListener
	 * )
	 */
	@Override
	public void main_bannar(final BaseListener<List<CarouselModel>> listener) {
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
	private void paserSucc(BaseListener<List<CarouselModel>> listener,
			String result) {
		BaseModel<List<CarouselModel>> data = new BaseModelPaser<List<CarouselModel>>()
				.paserJsonImp(result, new TypeToken<List<Bannar>>() {
				});
		if (data != null && data.getCode() == 200) {
			LogApp.i(result);
			if (data.getData() != null) {
				listener.onSucces(data.getData());
			} else {
				listener.onFaild(1, "bannars is null!");
			}
		} else {
			// 给用户提示错误原因
			ToastApp.showToast(data.getMsg() + "");
			listener.onFaild(1, "parser json wrong");
		}
	}
}
