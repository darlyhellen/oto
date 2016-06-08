/**下午2:40:21
 * @author zhangyh2
 * MyOrderActivity.java
 * TODO
 */
package com.darly.dlclent.ui.order;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.widget.springindicator.SpringIndicator;
import com.darly.dlclent.widget.springindicator.viewpager.ModelPagerAdapter;
import com.darly.dlclent.widget.springindicator.viewpager.PagerModelManager;
import com.darly.dlclent.widget.springindicator.viewpager.ScrollerViewPager;
import com.darly.dlclent.widget.welcome_anim.CubeTransformer;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 MyOrderActivity 下午2:40:21 TODO
 */
@ContentView(R.layout.activity_user_order)
public class MyOrderActivity extends BaseActivity implements OnClickListener {

	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	@ViewInject(R.id.order_viewpager)
	public ScrollerViewPager viewPager;
	@ViewInject(R.id.order_indicator)
	private SpringIndicator springIndicator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		title.setText("订单中心");
		back.setVisibility(View.VISIBLE);
		other.setVisibility(View.INVISIBLE);

		PagerModelManager manager = new PagerModelManager();
		manager.addCommonFragment(GuideFragment.class, getBgRes(), getTitles());
		ModelPagerAdapter adapter = new ModelPagerAdapter(
				getSupportFragmentManager(), manager);
		// 设置适配器
		viewPager.setAdapter(adapter);
		viewPager.fixScrollSpeed();

		viewPager.setPageTransformer(true, new CubeTransformer());
		// 设置页卡切换监听
		springIndicator.setViewPager(viewPager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

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
	}

	private List<String> getTitles() {
		List<String> data = new ArrayList<String>();
		data.add("全部");
		data.add("未完成");
		data.add("已完成");
		data.add("已收货");
		return data;
	}

	private List<Integer> getBgRes() {
		List<Integer> data = new ArrayList<Integer>();
		data.add(R.drawable.bg1);
		data.add(R.drawable.bg2);
		data.add(R.drawable.bg3);
		data.add(R.drawable.bg4);
		return data;
	}

	public ArrayList<View> getList(Context context) {
		ArrayList<View> list = new ArrayList<View>();
		View viewShow = View.inflate(context, R.layout.activity_guide_v1, null);
		View viewSec = View.inflate(context, R.layout.activity_guide_v2, null);
		View viewDown = View.inflate(context, R.layout.activity_guide_v3, null);
		list.add(viewShow);
		list.add(viewSec);
		list.add(viewDown);
		return list;
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.header_back:
			finish();
			break;

		default:
			break;
		}
	};
}
