/**上午10:45:34
 * @author zhangyh2
 * AutoNavActivity.java
 * TODO
 */
package com.hellen.flower;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.hellen.base.BaseActivity;
import com.hellen.base.BasePresenter;
import com.hellen.biz.AutoNavInterface;
import com.hellen.common.LogApp;
import com.hellen.common.NetworkReachabilityUtil;
import com.hellen.common.ToastApp;
import com.hellen.presenter.AutoNavPresenter;
import com.hellen.widget.header.HeaderView;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 AutoNavActivity 上午10:45:34 TODO
 */
@ContentView(R.layout.activity_autonav)
public class AutoNavActivity extends BaseActivity implements
		AutoNavInterface.AutoNavView, OnClickListener {

	private String TAG = getClass().getSimpleName();

	@ViewInject(R.id.autonav_title)
	private HeaderView header;
	@ViewInject(R.id.autonav_btn)
	private Button btn;
	@ViewInject(R.id.autonav_tv)
	private TextView tv;

	private AutoNavPresenter present;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		header.onSetTitle(R.string.app_name);
		header.onDisBack(false);
		present = new AutoNavPresenter(this);
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
		btn.setOnClickListener(this);
		header.getBack().setOnClickListener(this);
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
		case R.id.autonav_btn:
			if (NetworkReachabilityUtil.isNetworkConnected(this)) {
				present.findLocal();
			} else {
				ToastApp.showToast(R.string.neterror);
			}
			break;
		case R.id.view_header_iv:
			finish();
			break;
		default:
			break;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hellen.biz.AutoNavInterface.AutoNavView#setTextView(java.lang.String)
	 */
	@Override
	public void setTextView(String text) {
		// TODO Auto-generated method stub
		tv.setText(text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.AutoNavInterface.AutoNavView#setDisableClick()
	 */
	@Override
	public void setDisableClick() {
		// TODO Auto-generated method stub
		btn.setClickable(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.AutoNavInterface.AutoNavView#setEnableClick()
	 */
	@Override
	public void setEnableClick() {
		// TODO Auto-generated method stub
		btn.setClickable(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseView#setPersenter(com.hellen.base.BasePresenter)
	 */
	@Override
	public void setPersenter(BasePresenter persenter) {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "setPersenter" + persenter);
	}
}
