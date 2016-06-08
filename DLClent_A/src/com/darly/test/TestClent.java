/**下午3:50:59
 * @author zhangyh2
 * TestClent.java
 * TODO
 */
package com.darly.test;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.HttpClient;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * @author zhangyh2 TestClent 下午3:50:59 TODO
 */
public class TestClent extends AndroidTestCase {
	private int i1;
	private int i2;
	static final String LOG_TAG = "MathTest";

	@Override
	protected void setUp() throws Exception {
		i1 = 2;
		i2 = 3;
	}

	public void testAdd() {
		assertTrue("testAdd failed", ((i1 + i2) == 5));
	}

	public void testDec() {
		assertTrue("testDec failed", ((i2 - i1) == 1));
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Override
	public void testAndroidTestCaseSetupProperly() {
		super.testAndroidTestCaseSetupProperly();
	}

	/**
	 * 下午4:54:06
	 * 
	 * @author zhangyh2 TODO 测试网络连接
	 */
	@Test
	public void testUit() {
		Log.i(LOG_TAG, "testUit start");
		JSONObject ob = new JSONObject();
		try {
			ob.put("tel", "13891431454");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpClient.post(ConsHttpUrl.FRIEND, ob.toString(),
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						Log.i(LOG_TAG, arg0.result);
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						Log.i(LOG_TAG, arg1);
					}
				});
		Log.i(LOG_TAG, "testUit end");

	}
}
