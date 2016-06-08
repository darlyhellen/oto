/**下午2:01:04
 * @author zhangyh2
 * TimeCons.java
 * TODO
 */
package com.darly.dlclent.common;

import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.ConsHttpUrl;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author zhangyh2 TimeCons 下午2:01:04 TODO
 */
public class TimeCons {

	private static TimeCons instance;

	public static long SERVERTIME;

	public static long SYSTEMTIME;

	/**
	 * 下午2:08:35
	 * 
	 * @author zhangyh2
	 */
	private TimeCons() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the instance
	 */
	public static TimeCons getInstance() {
		if (instance == null) {
			instance = new TimeCons();
		}
		return instance;
	}

	public void init() {
		getTime();

	};

	private static void getTime() {
		SYSTEMTIME = System.currentTimeMillis();
		SERVERTIME = SYSTEMTIME;
		HttpClient.get(APP.getInstance(), ConsHttpUrl.SERVICETIME,
				new RequestParams(), new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						LogUtils.i(arg0.result);
						if (arg0.result!=null) {
							SERVERTIME = Long.parseLong(arg0.result);
						}
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub

					}
				});
	}
}
