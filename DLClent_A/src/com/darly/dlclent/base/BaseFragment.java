/**
 * 下午2:33:01
 * @author zhangyh2
 * $
 * BaseActivity.java
 * TODO
 */
package com.darly.dlclent.base;

import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.darly.dlclent.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author zhangyh2 BaseActivity $ 下午2:33:01 TODO
 */
public abstract class BaseFragment extends Fragment {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	protected DisplayImageOptions options;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		initGlobe();

		initView(savedInstanceState);

		loadData();

		initListener();

	}

	/**
	 * 下午4:06:44
	 * 
	 * @author zhangyh2 TODO
	 */
	@SuppressWarnings("deprecation")
	private void initGlobe() {
		// TODO Auto-generated method stub
		// 设置参数，加载每个图片的详细参数和是否存储、缓存的问题。
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
				.bitmapConfig(Config.RGB_565).cacheOnDisc(true).build();
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
