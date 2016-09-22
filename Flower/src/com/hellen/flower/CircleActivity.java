/**下午2:40:28
 * @author zhangyh2
 * CircleActivity.java
 * TODO
 */
package com.hellen.flower;

import android.os.Bundle;
import android.view.View;

import com.hellen.base.BaseActivity;
import com.hellen.common.LogApp;
import com.hellen.widget.circlemenu.CircleMenuLayout;
import com.hellen.widget.circlemenu.CircleMenuLayout.OnMenuItemClickListener;
import com.hellen.widget.circlemenu.CirclePouple;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 CircleActivity 下午2:40:28 TODO
 */
@ContentView(R.layout.activity_circle)
public class CircleActivity extends BaseActivity implements
		OnMenuItemClickListener {

	@ViewInject(R.id.id_menulayout)
	private CircleMenuLayout circle;

	private String[] mItemTexts = new String[] { "安全中心 ", "特色服务", "投资理财",
			"转账汇款", "我的账户", "信用卡" };
	private int[] mItemImgs = new int[] { R.drawable.home_mbank_1_normal,
			R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
			R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal,
			R.drawable.home_mbank_6_normal };

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		circle.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
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
		circle.setOnMenuItemClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hellen.widget.circlemenu.CircleMenuLayout.OnMenuItemClickListener
	 * #itemClick(android.view.View, int)
	 */
	@Override
	public void itemClick(View view, int pos, String title) {
		// TODO Auto-generated method stub
		LogApp.i(title + pos + view.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hellen.widget.circlemenu.CircleMenuLayout.OnMenuItemClickListener
	 * #itemCenterClick(android.view.View)
	 */
	@Override
	public void itemCenterClick(View view) {
		// TODO Auto-generated method stub
		new CirclePouple(this);
	}

}
