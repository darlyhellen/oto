/**上午10:09:33
 * @author zhangyh2
 * ResetInfoActivity.java
 * TODO
 */
package com.darly.dlclent.ui.resetuserinfo;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.JsonUtil;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.UserInfoData;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 ResetInfoActivity 上午10:09:33 TODO
 */
@ContentView(R.layout.activity_resetinfo)
public class ResetInfoActivity extends BaseActivity implements OnClickListener {

	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_btn)
	private Button btn;

	@ViewInject(R.id.reset_info_cart)
	private TextView cart;
	@ViewInject(R.id.reset_info_result)
	private EditText result;
	private String data;

	private int requestCode;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		back.setVisibility(View.VISIBLE);
		btn.setVisibility(View.VISIBLE);
		btn.setText("保存");
		requestCode = getIntent().getIntExtra("requestCode", 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case APPEnum.CENTER_NAME:
			title.setText("修改用户名");
			cart.setText("姓名");
			data = getIntent().getStringExtra("name");
			break;
		case APPEnum.CENTER_TEL:
			title.setText("修改手机号码");
			cart.setText("手机号码");
			data = getIntent().getStringExtra("tel");
			break;
		case APPEnum.CENTER_SEX:
			title.setText("修改用户性别");
			cart.setText("性别");
			data = getIntent().getStringExtra("sex");
			break;
		case APPEnum.CENTER_CARD:
			title.setText("修改身份证号");
			cart.setText("身份证号");
			data = getIntent().getStringExtra("card");
			break;

		default:
			break;
		}
		result.setText(data);
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
		btn.setOnClickListener(this);
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
			// 后退
			finish();
			break;
		case R.id.header_btn:
			// 保存
			btn.setClickable(false);
			saveData();
			break;

		default:
			break;
		}
	}

	/**
	 * 上午10:42:45
	 * 
	 * @author zhangyh2 TODO 请求网络数据，进行保存替换数据。
	 */
	private void saveData() {
		// TODO Auto-generated method stub
		String resul = result.getText().toString().trim();
		String url = "";
		JSONObject param = new JSONObject();
		try {
			switch (requestCode) {
			case APPEnum.CENTER_NAME:
				param.put("name", resul);
				break;
			case APPEnum.CENTER_TEL:
				param.put("tel", resul);
				break;
			case APPEnum.CENTER_SEX:
				param.put("sex", resul);
				break;
			case APPEnum.CENTER_CARD:
				param.put("card", resul);
				break;
			default:
				break;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (url != null && url.length() > 0) {
			HttpClient.post(url, param.toString(),
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							// TODO Auto-generated method stub
							saveOK(arg0.result);
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub

						}
					});
		} else {
			// 造假数据
			BaseModel<UserInfoData> base = new BaseModelPaser<UserInfoData>()
					.paserJson(SharePreferHelp.getValue(
							APPEnum.USERINFO.getDec(), null),
							new TypeToken<UserInfoData>() {
							});
			String jsonString = null;
			if (new Random().nextBoolean()) {
				UserInfoData user = null;
				switch (requestCode) {
				case APPEnum.CENTER_NAME:
					user = new UserInfoData(resul, base.getData().getIcon(),
							base.getData().getTel(), base.getData().getSex(),
							base.getData().getIdCard(), base.getData()
									.getMoney(), base.getData().getToken());
					break;
				case APPEnum.CENTER_TEL:
					user = new UserInfoData(base.getData().getName(), base
							.getData().getIcon(), resul, base.getData()
							.getSex(), base.getData().getIdCard(), base
							.getData().getMoney(), base.getData().getToken());
					break;
				case APPEnum.CENTER_SEX:
					user = new UserInfoData(base.getData().getName(), base
							.getData().getIcon(), base.getData().getTel(),
							resul, base.getData().getIdCard(), base.getData()
									.getMoney(), base.getData().getToken());
					break;
				case APPEnum.CENTER_CARD:
					user = new UserInfoData(base.getData().getName(), base
							.getData().getIcon(), base.getData().getTel(), base
							.getData().getSex(), resul, base.getData()
							.getMoney(), base.getData().getToken());
					break;
				default:
					break;
				}

				BaseModel<UserInfoData> mo = new BaseModel<UserInfoData>(200,
						"", user);
				jsonString = JsonUtil.pojo2Json(mo);
			} else {
				BaseModel<UserInfoData> mo = new BaseModel<UserInfoData>(110,
						"修改错误", null);
				jsonString = JsonUtil.pojo2Json(mo);
			}
			LogUtils.i(jsonString);
			saveOK(jsonString);
		}

	}

	/**
	 * 上午10:52:53
	 * 
	 * @author zhangyh2 TODO 获取返回的正确数据
	 */
	private void saveOK(String json) {
		// TODO Auto-generated method stub
		if (json == null) {
			btn.setClickable(true);
			return;
		}
		BaseModel<UserInfoData> data = new BaseModelPaser<UserInfoData>()
				.paserJson(json, new TypeToken<UserInfoData>() {
				});
		if (data != null && data.getCode() == 200) {
			LogUtils.i(data.toString());
			// 登录成功
			SharePreferHelp.putValue(APPEnum.USERINFO.getDec(), json);
			setResult(APPEnum.CENTER_CHANGE);
			finish();
		} else {
			btn.setClickable(true);
			ToastApp.showToast(data.getMsg());
		}

	}

}
