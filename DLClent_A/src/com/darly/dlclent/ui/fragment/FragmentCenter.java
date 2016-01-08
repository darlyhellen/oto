/**
 * 下午2:15:05
 * @author zhangyh2
 * $
 * FragmentMain.java
 * TODO
 */
package com.darly.dlclent.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.FragmentCenterAdapter;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 FragmentMain $ 下午2:15:05 TODO
 */
public class FragmentCenter extends BaseFragment implements
		OnItemClickListener, OnClickListener {
	private View rootView;
	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;
	@ViewInject(R.id.fragment_center_list)
	private ListView lv;
	@ViewInject(R.id.fragment_center_sec_list)
	private ListView seclv;

	private FragmentCenterAdapter adapter;
	private FragmentCenterAdapter secAdapter;
	@ViewInject(R.id.center_header_icon)
	private ImageView header_icon;
	@ViewInject(R.id.center_header_name)
	private TextView header_name;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.fragment_center, container, false);
		ViewUtils.inject(this, rootView); // 注入view和事件
		return rootView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		title.setText(R.string.footer_center);
		header_icon.setImageResource(R.drawable.icon);
		header_name.setText("Admin");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

		LayoutParams lp = new LayoutParams(APPEnum.WIDTH.getLen(),
				LayoutParams.WRAP_CONTENT);
		lv.setLayoutParams(lp);
		seclv.setLayoutParams(lp);
		adapter = new FragmentCenterAdapter(getData(),
				R.layout.fragment_center_item, getActivity());
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		secAdapter = new FragmentCenterAdapter(getSecData("test", 10),
				R.layout.fragment_center_item, getActivity());
		seclv.setAdapter(secAdapter);

		ViewPropertyAnimator animator = seclv.animate();
		animator.setStartDelay(0);
		animator.setDuration(0);
		animator.translationXBy(APPEnum.WIDTH.getLen());
		animator.start();

	}

	/**
	 * 下午3:31:29
	 * 
	 * @author zhangyh2 FragmentCenter.java TODO
	 */
	private List<String> getData() {
		// TODO Auto-generated method stub
		List<String> data = new ArrayList<String>();
		data.add("账户信息");
		data.add("地址管理");
		data.add("我的订单");
		data.add("物流中心");
		data.add("浏览记录");
		data.add("收藏夹");
		data.add("礼品卡");
		data.add("余额");
		data.add("现金券");
		return data;
	}

	/**
	 * 下午3:31:29
	 * 
	 * @author zhangyh2 FragmentCenter.java TODO
	 */
	private List<String> getSecData(String name, int len) {
		// TODO Auto-generated method stub
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < len; i++) {
			data.add(name + i);
		}
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		back.setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
	 * .AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		// 点击进行横向移动并弹出菜单。
		back.setVisibility(View.VISIBLE);
		String name = (String) parent.getItemAtPosition(position);
		rightToleftAnim(lv);
		rightToleftAnim(seclv);
		secAdapter.setData(getSecData(name, position));
	}

	/**
	 * 
	 * 下午5:04:50
	 * 
	 * @author zhangyh2 FragmentCenter.java TODO
	 */
	private void rightToleftAnim(View view) {
		ViewPropertyAnimator animator = view.animate();
		animator.setStartDelay(100);
		animator.setDuration(500);
		animator.translationXBy((int) (-(float) view.getWidth()));
		animator.start();
	}

	/**
	 * 
	 * 下午5:04:50
	 * 
	 * @author zhangyh2 FragmentCenter.java TODO
	 */
	private void leftTorightAnim(View view) {
		ViewPropertyAnimator animator = view.animate();
		animator.setStartDelay(100);
		animator.setDuration(500);
		animator.translationXBy((int) ((float) view.getWidth()));
		animator.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		leftTorightAnim(lv);
		leftTorightAnim(seclv);
		back.setVisibility(View.INVISIBLE);
	}

}
