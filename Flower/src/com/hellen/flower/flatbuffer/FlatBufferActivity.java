/**上午11:02:17
 * @author zhangyh2
 * FlatBufferActivity.java
 * TODO
 */
package com.hellen.flower.flatbuffer;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.hellen.base.BaseActivity;
import com.hellen.base.BasePresenter;
import com.hellen.biz.FlatBufferInterface;
import com.hellen.flower.R;
import com.hellen.presenter.FlatBufferPresenter;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 FlatBufferActivity 上午11:02:17 TODO
 */
@ContentView(R.layout.activity_flatbuffer)
public class FlatBufferActivity extends BaseActivity implements
		FlatBufferInterface, OnClickListener {

	@ViewInject(R.id.flat_flat)
	private Button flat;
	@ViewInject(R.id.flat_json)
	private Button json;
	@ViewInject(R.id.flat_flat_time)
	private TextView flat_time;
	@ViewInject(R.id.flat_json_time)
	private TextView json_time;

	private FlatBufferPresenter presenters;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		presenters = new FlatBufferPresenter(this);
		presenters.init();
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
		flat.setOnClickListener(this);
		json.setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseView#setPersenter(com.hellen.base.BasePresenter)
	 */
	@Override
	public void setPersenter(BasePresenter persenter) {
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
		presenters.clickDown(v,flat_time,json_time,this);
	}

}
