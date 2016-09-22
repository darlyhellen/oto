/**上午10:17:35
 * @author zhangyh2
 * ChangeUserInfoActivity.java
 * TODO
 */
package com.hellen.flower.userinfo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.gson.reflect.TypeToken;
import com.hellen.base.BaseActivity;
import com.hellen.base.BasePresenter;
import com.hellen.base.ConsMVP;
import com.hellen.bean.BaseModel;
import com.hellen.bean.BaseModelPaser;
import com.hellen.bean.UserInfo;
import com.hellen.biz.ChangeUserInterface;
import com.hellen.common.SharePreferHelp;
import com.hellen.flower.R;
import com.hellen.flower.view.UserInfoView;
import com.hellen.presenter.ChangeUserInfoPresenter;
import com.hellen.widget.header.HeaderView;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 ChangeUserInfoActivity 上午10:17:35 TODO
 */
@ContentView(R.layout.activity_change_userinfo)
public class ChangeUserInfoActivity extends BaseActivity implements
		OnClickListener, ChangeUserInterface {

	@ViewInject(R.id.userinfo_title)
	private HeaderView header;
	@ViewInject(R.id.userinfo_icon)
	private UserInfoView icon;
	@ViewInject(R.id.userinfo_name)
	private UserInfoView name;
	@ViewInject(R.id.userinfo_tel)
	private UserInfoView tel;
	@ViewInject(R.id.userinfo_sex)
	private UserInfoView sex;
	@ViewInject(R.id.userinfo_idcard)
	private UserInfoView idcard;

	private ChangeUserInfoPresenter presents;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		presents = new ChangeUserInfoPresenter(this);
	}

	@Override
	public void setPersenter(BasePresenter persenter) {
		// TODO Auto-generated method stub
	}

	@Override
	public void initHeader() {
		// TODO Auto-generated method stub
		header.onSetTitle(R.string.app_name);
		header.onDisBack(false);
		header.getBack().setOnClickListener(this);
	}

	@Override
	public void initInfo() {
		// TODO Auto-generated method stub
		BaseModel<UserInfo> data = new BaseModelPaser<UserInfo>().paserJson(
				SharePreferHelp.getValue(ConsMVP.USERINFO.getDec(), null),
				new TypeToken<UserInfo>() {
				});
		if (data != null && data.getCode() == 200) {
			if (data.getData() != null) {
				icon.showUserInfo("图像", null, data.getData().getIcon());
				name.showUserInfo("名称", data.getData().getName(), null);
				tel.showUserInfo("电话", ChangeUserInfoPresenter
						.transformMobile(data.getData().getTel()), null);
				sex.showUserInfo("性別", data.getData().getSex(), null);
				idcard.showUserInfo("身份證", ChangeUserInfoPresenter
						.transformIDcard(data.getData().getIdcard()), null);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		presents.clickDown(v, this);
	}

}
