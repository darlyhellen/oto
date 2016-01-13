/**上午11:12:19
 * @author zhangyh2
 * AddressActivity.java
 * TODO
 */
package com.darly.dlclent.ui.address;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.AddressAdapter;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.JsonUtil;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.AddressModel;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 AddressActivity 上午11:12:19 TODO 地址列表功能模块。
 */
@ContentView(R.layout.activity_address)
public class AddressActivity extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_btn)
	private Button btn;
	@ViewInject(R.id.address_lv)
	private ListView lv;
	@ViewInject(R.id.address_addnew)
	private Button addnew;

	private List<AddressModel> data;

	private AddressAdapter adapter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		title.setText("地址列表");
		back.setVisibility(View.VISIBLE);
		btn.setVisibility(View.VISIBLE);
		btn.setText("编辑");
		addnew.setText("新增地址");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		adapter = new AddressAdapter(data, R.layout.item_activity_address, this);
		lv.setAdapter(adapter);

		String url = "";
		if (url != null && url.length() > 0) {
			// 进行网络请求
			JSONObject param = new JSONObject();
			try {
				param.put("use", "");
				param.put("page", 1);
			} catch (Exception e) {
				// TODO: handle exception
			}
			HttpClient.post(url, param.toString(),
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							// TODO Auto-generated method stub
							setList(arg0.result);
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub

						}
					});

		} else {
			// 制造假数据
			String jsonString = null;
			List<AddressModel> lis = new ArrayList<AddressModel>();
			if (new Random().nextBoolean()) {
				AddressModel user = new AddressModel(1, "阿三", "13891431441",
						"河北");
				lis.add(user);
				AddressModel users = new AddressModel(2, "阿三2", "13891431441",
						"河南");
				lis.add(users);
				BaseModel<List<AddressModel>> mo = new BaseModel<List<AddressModel>>(
						200, "", lis);
				jsonString = JsonUtil.pojo2Json(mo);
			} else {
				BaseModel<List<AddressModel>> mo = new BaseModel<List<AddressModel>>(
						110, "地址获取失败", lis);
				jsonString = JsonUtil.pojo2Json(mo);
			}
			LogUtils.i(jsonString);
			setList(jsonString);
		}
	}

	/**
	 * 上午11:27:05
	 * 
	 * @author zhangyh2 TODO 设置地址列表
	 */
	private void setList(String json) {
		// TODO Auto-generated method stub
		if (json == null) {
			return;
		}
		BaseModel<List<AddressModel>> base = new BaseModelPaser<List<AddressModel>>()
				.paserJson(json, new TypeToken<List<AddressModel>>() {
				});
		if (base != null && base.getCode() == 200) {
			data = base.getData();
			adapter.setData(data);
		} else {
			ToastApp.showToast(base.getMsg());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		back.setOnClickListener(this);
		addnew.setOnClickListener(this);
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
		case R.id.header_back:
			finish();
			break;
		case R.id.address_addnew:
			// 新增地址。
			break;
		default:
			break;
		}
	}

}
