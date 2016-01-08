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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.FragmentCenterAdapter;
import com.darly.dlclent.base.BaseFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 FragmentMain $ 下午2:15:05 TODO
 */
public class FragmentCenter extends BaseFragment implements OnItemClickListener {
	private View rootView;
	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;
	@ViewInject(R.id.fragment_center_list)
	private ListView lv;

	private FragmentCenterAdapter adapter;

	private View header;

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
		header = inflater.inflate(R.layout.fragment_center_header, null);
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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		ImageView icon = (ImageView) header
				.findViewById(R.id.center_header_icon);
		icon.setImageResource(R.drawable.icon);
		TextView name = (TextView) header.findViewById(R.id.center_header_name);
		name.setText("Admin");
		adapter = new FragmentCenterAdapter(getData(),
				R.layout.fragment_center_item, getActivity());
		lv.addHeaderView(header);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub

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
		LogUtils.i(position + "");
	}

}
