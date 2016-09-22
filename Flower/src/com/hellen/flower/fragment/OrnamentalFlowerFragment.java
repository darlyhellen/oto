/**上午10:29:57
 * @author zhangyh2
 * OrnamentalFlowerFragment.java
 * TODO
 */
package com.hellen.flower.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hellen.adapter.WholeListAdapterM;
import com.hellen.base.BaseFragment;
import com.hellen.base.BasePresenter;
import com.hellen.biz.OrnamentalFlowerInterface;
import com.hellen.common.LogApp;
import com.hellen.flower.MainActivity;
import com.hellen.flower.R;
import com.hellen.presenter.FragmentOrnamentalPresenter;
import com.hellen.widget.header.HeaderView;
import com.hellen.widget.listview.WholeListView;
import com.hellen.widget.xlistview.XListView;
import com.umeng.socialize.UMShareAPI;

/**
 * @author zhangyh2 OrnamentalFlowerFragment 上午10:29:57 TODO 观赏花
 */
public class OrnamentalFlowerFragment extends BaseFragment implements
		OrnamentalFlowerInterface, OnClickListener, OnItemClickListener {
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

	private FragmentOrnamentalPresenter mainPresenter;

	private WholeListView wlv;

	private XListView xlv;

	private WholeListAdapterM adapter;

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
		rootView = inflater.inflate(R.layout.fragment_main_ornamental,
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
		header = (LinearLayout) rootView.findViewById(R.id.orn_header);
		text = (LinearLayout) rootView.findViewById(R.id.orn_text);
		boolean show = true;
		try {
			// 融合到首页中。传递参数
			show = getArguments().getBoolean("header");
		} catch (Exception e) {
			// 首页跳转，没有传递参数
			show = true;
		}
		mainPresenter = new FragmentOrnamentalPresenter(this, show);
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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseFragment#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseView#setPersenter(com.hellen.base.BasePresenter)
	 */
	@Override
	public void setPersenter(BasePresenter persenter) {
		// TODO Auto-generated method stub
		LogApp.i(TAG, persenter.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.OrnamentalFlowerInterface#isShowTitle(boolean)
	 */
	@Override
	public void isShowTitle(boolean show) {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "isShowTitle" + show);
		adapter = new WholeListAdapterM(null, R.layout.item_act_list,
				getActivity());
		if (show) {
			// 顶部标题栏进行展示
			header.setVisibility(View.VISIBLE);
			text.setVisibility(View.GONE);
			headerView = (HeaderView) rootView
					.findViewById(R.id.orn_headerview);
			headerView.onSetTitle(R.string.ornamental);
			headerView.onDisBack(true);
			headerView.getBg().getBackground().setAlpha(255);
			headerView.getBg().setBackgroundResource(R.color.red);
			((MainActivity) getActivity()).tintManager
					.setStatusBarTintResource(R.color.red, 255);
			xlv = (XListView) rootView.findViewById(R.id.orn_xlv);
			xlv.setAdapter(adapter);
			xlv.setOnItemClickListener(this);
		} else {
			// 定必标题栏进行隐藏
			text.setVisibility(View.VISIBLE);
			header.setVisibility(View.GONE);
			tv = (TextView) rootView.findViewById(R.id.orn_tv);
			tv.setOnClickListener(this);
			wlv = (WholeListView) rootView.findViewById(R.id.orn_wlv);
			wlv.setAdapter(adapter);
			wlv.setOnItemClickListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.OrnamentalFlowerInterface#onSucces()
	 */
	@Override
	public void onSucces() {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "onSucces");
		mainPresenter.setAdapterData(adapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.OrnamentalFlowerInterface#onFaild()
	 */
	@Override
	public void onFaild() {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "onFaild");
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
		case R.id.orn_tv:
			LogApp.i(TAG, "R.id.orn_tv 准备跳入一组Fragment");
			((MainActivity) getActivity()).swichTo(R.id.main_footer_list);
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
		mainPresenter.itemClickDown(getActivity(), parent, view, position, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode,
				data);
	}
}
