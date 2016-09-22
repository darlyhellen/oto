/**上午10:09:33
 * @author zhangyh2
 * ResetInfoActivity.java
 * TODO
 */
package com.darly.dlclent.ui.resetuserinfo;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.IDCardUtils;
import com.darly.dlclent.common.JsonUtil;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.UserInfoData;
import com.darly.dlclent.widget.clearedit.ClearEditText;
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

	@ViewInject(R.id.reset_info_result)
	private ClearEditText result;
	@ViewInject(R.id.reset_info_rg)
	private RadioGroup rg;
	@ViewInject(R.id.reset_info_boy)
	private RadioButton boy;
	@ViewInject(R.id.reset_info_girl)
	private RadioButton girl;
	private String data;

	private int requestCode;

	private UserInfoData odInfo;

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

		BaseModel<UserInfoData> info = new BaseModelPaser<UserInfoData>()
				.paserJson(SharePreferHelp.getValue(APPEnum.USERINFO.getDec(),
						null), new TypeToken<UserInfoData>() {
				});
		odInfo = info.getData();
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
			data = getIntent().getStringExtra("name");
			result.setText(data);
			result.setVisibility(View.VISIBLE);
			rg.setVisibility(View.GONE);
			break;
		case APPEnum.CENTER_TEL:
			title.setText("修改手机号码");
			data = getIntent().getStringExtra("tel");
			result.setText(data);
			result.setVisibility(View.VISIBLE);
			rg.setVisibility(View.GONE);
			break;
		case APPEnum.CENTER_SEX:
			title.setText("修改用户性别");
			data = getIntent().getStringExtra("sex");
			result.setVisibility(View.GONE);
			rg.setVisibility(View.VISIBLE);
			if (data.equals("男")) {
				boy.setChecked(true);
			} else {
				girl.setChecked(true);
			}
			break;
		case APPEnum.CENTER_CARD:
			title.setText("修改身份证号");
			data = getIntent().getStringExtra("card");
			result.setText(data);
			result.setVisibility(View.VISIBLE);
			rg.setVisibility(View.GONE);
			break;

		default:
			break;
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
			String resul = result.getText().toString().trim();
			switch (requestCode) {
			case APPEnum.CENTER_NAME:
				Pattern pattern = Pattern
						.compile("([^\\._\\w\\u4e00-\\u9fa5])*");
				Matcher matcher = pattern.matcher(resul);
				String newName = matcher.replaceAll("");
				result.setText(newName);
				if (newName.length() > 6 || newName.length() == 0) {
					ToastApp.showToast("请输入6位以下的姓名");
				} else {
					saveData();
				}
				break;
			case APPEnum.CENTER_TEL:
				if (resul.length() != 11) {
					ToastApp.showToast("请输入正确的手机号");
				} else {
					saveData();
				}
				break;
			case APPEnum.CENTER_SEX:
				saveData();
				break;
			case APPEnum.CENTER_CARD:
				if (!IDCardUtils.IDCardValidate(resul).equals("")) {
					ToastApp.showToast("请输入正确的身份证号");
				} else {
					saveData();
				}
				break;
			default:
				break;
			}
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
		if (!APP.isNetworkConnected(this)) {
			ToastApp.showToast(R.string.neterror);
			return;
		}
		String resul = result.getText().toString().trim();
		btn.setClickable(false);
		String url = ConsHttpUrl.RESETUSERINFO;
		if (url != null && url.length() > 0) {
			JSONObject ob = new JSONObject();

			try {
				ob.put("tel", odInfo.getTel());
				switch (requestCode) {
				case APPEnum.CENTER_NAME:
					ob.put("name", resul);
					break;
				case APPEnum.CENTER_TEL:
					break;
				case APPEnum.CENTER_SEX:
					switch (rg.getCheckedRadioButtonId()) {
					case R.id.reset_info_boy:
						ob.put("sex", boy.getText().toString());
						break;
					case R.id.reset_info_girl:
						ob.put("sex", girl.getText().toString());
						break;

					default:
						break;
					}
					break;
				case APPEnum.CENTER_CARD:
					ob.put("card", resul);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			HttpClient.post(url, ob.toString(), new RequestCallBack<String>() {

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
			String jsonString = null;
			if (new Random().nextBoolean()) {
				UserInfoData user = null;
				switch (requestCode) {
				case APPEnum.CENTER_NAME:
					user = new UserInfoData(resul, odInfo.getIcon(),
							odInfo.getTel(), odInfo.getSex(),
							odInfo.getIdCard(), odInfo.getMoney(),
							odInfo.getToken(), "true");
					break;
				case APPEnum.CENTER_TEL:
					user = new UserInfoData(odInfo.getName(), odInfo.getIcon(),
							resul, odInfo.getSex(), odInfo.getIdCard(),
							odInfo.getMoney(), odInfo.getToken(), "true");

					break;
				case APPEnum.CENTER_SEX:
					switch (rg.getCheckedRadioButtonId()) {
					case R.id.reset_info_boy:
						user = new UserInfoData(odInfo.getName(),
								odInfo.getIcon(), odInfo.getTel(), boy
										.getText().toString(),
								odInfo.getIdCard(), odInfo.getMoney(),
								odInfo.getToken(), "true");
						break;
					case R.id.reset_info_girl:
						user = new UserInfoData(odInfo.getName(),
								odInfo.getIcon(), odInfo.getTel(), girl
										.getText().toString(),
								odInfo.getIdCard(), odInfo.getMoney(),
								odInfo.getToken(), "true");
						break;

					default:
						break;
					}
					break;
				case APPEnum.CENTER_CARD:
					user = new UserInfoData(odInfo.getName(), odInfo.getIcon(),
							odInfo.getTel(), odInfo.getSex(), resul,
							odInfo.getMoney(), odInfo.getToken(), "true");
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
		LogUtils.i(json);
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
