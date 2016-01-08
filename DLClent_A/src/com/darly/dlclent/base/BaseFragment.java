/**
 * 下午2:33:01
 * @author zhangyh2
 * $
 * BaseActivity.java
 * TODO
 */
package com.darly.dlclent.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @author zhangyh2 BaseActivity $ 下午2:33:01 TODO
 */
public abstract class BaseFragment extends Fragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		initView(savedInstanceState);

		loadData();

		initListener();

	}

	/**
	 * @param savedInstanceState
	 *            下午2:34:08
	 * @author zhangyh2 BaseActivity.java TODO 初始化控件
	 */
	protected abstract void initView(Bundle savedInstanceState);

	/**
	 * 
	 * 下午2:34:10
	 * 
	 * @author zhangyh2 BaseActivity.java TODO 加载数据
	 */
	protected abstract void loadData();

	/**
	 * 
	 * 下午2:42:02
	 * 
	 * @author zhangyh2 BaseFragment.java TODO 初始化坚挺事件
	 */
	protected abstract void initListener();

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onResume()
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onPause()
	 */
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
