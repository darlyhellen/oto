/**上午9:40:15
 * @author zhangyh2
 * ResetPasswordAcitvity.java
 * TODO
 */
package com.darly.dlclent.ui.resetuserinfo;

import java.util.Random;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.JsonUtil;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.ResetPassWord;
import com.darly.dlclent.ui.login.LoginActivity;
import com.darly.dlclent.widget.clearedit.ClearEditText;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 ResetPasswordAcitvity 上午9:40:15 TODO 重置密码
 */
@ContentView(R.layout.activity_resetpass)
public class ResetPasswordAcitvity extends BaseActivity implements
		OnClickListener {

	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	@ViewInject(R.id.reset_pass_old)
	private ClearEditText old;
	@ViewInject(R.id.reset_pass_new)
	private ClearEditText news;
	@ViewInject(R.id.reset_pass_renew)
	private ClearEditText renew;
	@ViewInject(R.id.reset_pass_btn)
	private Button btn;

	private boolean isOld;
	private boolean isNew;
	private boolean isRenew;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		title.setText("重置密码");
		old.setHint("请输入原密码");
		old.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		news.setHint("请输入6-20位新密码");
		news.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		renew.setHint("请确认新密码");
		renew.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		btn.setText(R.string.yes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		back.setVisibility(View.VISIBLE);
		other.setVisibility(View.INVISIBLE);
		btn.setBackgroundResource(R.drawable.btn_normal);
		btn.setTextColor(getResources().getColor(R.color.pop_back));
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

		old.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (s != null && !"".equals(s.toString())) {
					isOld = true;
					if (isOld && isNew && isRenew) {
						btn.setBackgroundResource(R.drawable.btn_select);
						btn.setTextColor(getResources().getColor(R.color.white));
						btn.setClickable(true);
					}
				} else {
					isOld = false;
					btn.setBackgroundResource(R.drawable.btn_normal);
					btn.setTextColor(getResources().getColor(R.color.pop_back));
					btn.setClickable(false);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s != null && s.toString().contains(" ")) {
					ToastApp.showToast(R.string.pass_contain_null);
					isOld = false;
					btn.setBackgroundResource(R.drawable.btn_normal);
					btn.setTextColor(getResources().getColor(R.color.pop_back));
					btn.setClickable(false);
				}
			}
		});

		news.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (s != null && !"".equals(s.toString())) {
					isNew = true;
					if (isOld && isNew && isRenew) {
						btn.setBackgroundResource(R.drawable.btn_select);
						btn.setTextColor(getResources().getColor(R.color.white));
						btn.setClickable(true);
					}
				} else {
					isNew = false;
					btn.setBackgroundResource(R.drawable.btn_normal);
					btn.setTextColor(getResources().getColor(R.color.pop_back));
					btn.setClickable(false);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s != null && s.toString().contains(" ")) {
					ToastApp.showToast(R.string.pass_contain_null);
					isNew = false;
					btn.setBackgroundResource(R.drawable.btn_normal);
					btn.setTextColor(getResources().getColor(R.color.pop_back));
					btn.setClickable(false);
				}
			}
		});

		renew.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (s != null && !"".equals(s.toString())) {
					isRenew = true;
					if (isOld && isNew && isRenew) {
						btn.setBackgroundResource(R.drawable.btn_select);
						btn.setTextColor(getResources().getColor(R.color.white));
						btn.setClickable(true);
					}
				} else {
					isRenew = false;
					btn.setBackgroundResource(R.drawable.btn_normal);
					btn.setTextColor(getResources().getColor(R.color.pop_back));
					btn.setClickable(false);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s != null && s.toString().contains(" ")) {
					ToastApp.showToast(R.string.pass_contain_null);
					isRenew = false;
					btn.setBackgroundResource(R.drawable.btn_normal);
					btn.setTextColor(getResources().getColor(R.color.pop_back));
					btn.setClickable(false);
				}
			}
		});
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
		case R.id.reset_pass_btn:
			// 获取新旧密码进行多次对比，然后请求修改密码。
			String oldPass = old.getText().toString().trim();

			String newPass = news.getText().toString().trim();

			String rePass = renew.getText().toString().trim();

			resetPassWord(oldPass, newPass, rePass);
			break;

		default:
			break;
		}
	}

	/**
	 * 上午10:20:52
	 * 
	 * @author zhangyh2 TODO 判断是否进行密码修改
	 */
	private void resetPassWord(String oldPass, String newPass, String rePass) {
		// TODO Auto-generated method stub
		if (oldPass == null || oldPass.length() < 6 || oldPass.length() > 20) {
			ToastApp.showToast(R.string.pass_contain_wrong);
			return;
		}
		if (newPass == null || newPass.length() < 6 || newPass.length() > 20) {
			ToastApp.showToast(R.string.pass_contain_wrong);
			return;
		}
		if (rePass == null || rePass.length() < 6 || rePass.length() > 20) {
			ToastApp.showToast(R.string.pass_contain_wrong);
			return;
		}
		if (!newPass.equals(rePass)) {
			ToastApp.showToast(R.string.pass_compare_wrong);
			return;
		}
		if (!APP.isNetworkConnected(this)) {
			ToastApp.showToast(R.string.neterror);
			return;
		}
		btn.setClickable(false);
		String url = "";
		if (url != null && url.length() > 0) {
			JSONObject param = new JSONObject();
			try {
				param.put("new", newPass);
			} catch (Exception e) {
				// TODO: handle exception
			}

			HttpClient.post(url, param.toString(),
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							// TODO Auto-generated method stub
							isReset(arg0.result);
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub
							btn.setClickable(true);
						}
					});
		} else {
			String jsonString = null;
			if (new Random().nextBoolean()) {
				ResetPassWord user = new ResetPassWord("密码重置成功",
						new Random().nextBoolean(), null);
				BaseModel<ResetPassWord> mo = new BaseModel<ResetPassWord>(200,
						"", user);
				jsonString = JsonUtil.pojo2Json(mo);
			} else {
				BaseModel<ResetPassWord> mo = new BaseModel<ResetPassWord>(110,
						"密码重置失败", null);
				jsonString = JsonUtil.pojo2Json(mo);
			}
			LogUtils.i(jsonString);
			isReset(jsonString);

		}

	}

	/**
	 * 上午10:30:55
	 * 
	 * @author zhangyh2 TODO 开启密码修改完成逻辑。
	 */
	private void isReset(String json) {
		// TODO Auto-generated method stub
		if (json == null) {
			btn.setClickable(true);
			return;
		}
		BaseModel<ResetPassWord> data = new BaseModelPaser<ResetPassWord>()
				.paserJson(json, new TypeToken<ResetPassWord>() {
				});
		if (data != null && data.getCode() == 200) {
			ToastApp.showToast(data.getData().getMsg());
			if (data.getData().getReLogin()) {
				Intent intent =  new Intent(this, LoginActivity.class);
				intent.putExtra("ResetPass", true);
				startActivity(intent);
			}
			finish();
		} else {
			btn.setClickable(true);
			ToastApp.showToast(data.getMsg());
		}
	}
}
