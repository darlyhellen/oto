/**上午10:29:57
 * @author zhangyh2
 * OrnamentalFlowerFragment.java
 * TODO
 */
package com.hellen.flower.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hellen.adapter.WholeListAdapter;
import com.hellen.base.BaseFragment;
import com.hellen.base.BasePresenter;
import com.hellen.biz.SucculentInterface;
import com.hellen.common.LogApp;
import com.hellen.flower.MainActivity;
import com.hellen.flower.R;
import com.hellen.presenter.FragmentSucculentPresenter;
import com.hellen.widget.header.HeaderView;
import com.hellen.widget.listview.WholeListView;
import com.umeng.comm.core.impl.CommunityFactory;

/**
 * @author zhangyh2 OrnamentalFlowerFragment 上午10:29:57 TODO 多肉植物
 */
public class SucculentFragment extends BaseFragment implements
		SucculentInterface, OnClickListener, OnItemClickListener {
	private String TAG = getClass().getSimpleName();
	private View rootView;

	/**
	 * 下午3:11:48 TODO 顶部标题栏
	 */
	private LinearLayout header;

	/**
	 * 下午3:12:06 TODO 顶部的独立标签
	 */
	private LinearLayout text;

	private HeaderView headerView;

	private TextView tv;

	private FragmentSucculentPresenter mainPresenter;

	private WholeListView wlv;

	private WholeListAdapter adapter;

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
		rootView = inflater.inflate(R.layout.fragment_main_succulent,
				container, false);
		return rootView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseFragment#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		header = (LinearLayout) rootView.findViewById(R.id.suc_header);
		text = (LinearLayout) rootView.findViewById(R.id.suc_text);
		headerView = (HeaderView) rootView.findViewById(R.id.suc_headerview);
		tv = (TextView) rootView.findViewById(R.id.suc_tv);
		wlv = (WholeListView) rootView.findViewById(R.id.suc_wlv);
		adapter = new WholeListAdapter(null, R.layout.item_act_list,
				getActivity());
		wlv.setAdapter(adapter);
		boolean show = true;
		try {
			// 融合到首页中。传递参数
			show = getArguments().getBoolean("header");
		} catch (Exception e) {
			// 首页跳转，没有传递参数
			show = true;
		}
		mainPresenter = new FragmentSucculentPresenter(this, show);
		mainPresenter.findData();
		LogApp.i(TAG, "initView" + show);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseFragment#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		headerView.onSetTitle(R.string.succulent);
		headerView.onDisBack(true);
		headerView.getBg().getBackground().setAlpha(255);
		headerView.getBg().setBackgroundResource(R.color.red);	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseFragment#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		tv.setOnClickListener(this);
		wlv.setOnItemClickListener(this);
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
	 * @see com.hellen.biz.SucculentInterface#isShowTitle(boolean)
	 */
	@Override
	public void isShowTitle(boolean show) {
		// TODO Auto-generated method stub
		if (show) {
			// 顶部标题栏进行展示
			header.setVisibility(View.VISIBLE);
			text.setVisibility(View.GONE);
		} else {
			// 定必标题栏进行隐藏
			text.setVisibility(View.VISIBLE);
			header.setVisibility(View.GONE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.SucculentInterface#onSucces()
	 */
	@Override
	public void onSucces() {
		// TODO Auto-generated method stub
		mainPresenter.setAdapterData(adapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.SucculentInterface#onFaild()
	 */
	@Override
	public void onFaild() {
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
		switch (v.getId()) {
		case R.id.suc_tv:
			LogApp.i(TAG, "R.id.suc_tv 准备跳入一组Fragment");
			((MainActivity) getActivity()).swichTo(R.id.main_footer_conversion);
			break;

		default:
			break;
		}
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
		LogApp.i(TAG, "onItemClick");
		CommunityFactory.getCommSDK(getActivity()).openCommunity(getActivity());
	}

}
