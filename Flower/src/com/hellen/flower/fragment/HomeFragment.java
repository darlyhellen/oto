/**下午3:27:48
 * @author zhangyh2
 * MainActivity.java
 * TODO
 */
package com.hellen.flower.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hellen.base.BaseFragment;
import com.hellen.base.BasePresenter;
import com.hellen.biz.FragmentMainInterface;
import com.hellen.common.LogApp;
import com.hellen.flower.MainActivity;
import com.hellen.flower.R;
import com.hellen.presenter.FragmentMainPresenter;
import com.hellen.widget.carousel.Carousel.ClickCarouselistener;
import com.hellen.widget.header.HeaderView;
import com.hellen.widget.scroll.ScrollHeaderView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 MainActivity 下午3:27:48 TODO
 */
public class HomeFragment extends BaseFragment implements
		FragmentMainInterface, ClickCarouselistener,
		ScrollHeaderView.OnScrollChangedListener, OnClickListener {

	private String TAG = getClass().getSimpleName();
	public View rootView;
	@ViewInject(R.id.id_main_headerview)
	private HeaderView header;
	@ViewInject(R.id.main_carousel)
	private RelativeLayout carousel;
	@ViewInject(R.id.main_scroll_view)
	private ScrollHeaderView scroll;

	private FragmentMainPresenter presenters;

	private FragmentManager fragmentManager;

	private OrnamentalFlowerFragment ornamental;

	private SucculentFragment succulent;

	private VenusaurFragment venusaur;

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
		rootView = inflater.inflate(R.layout.fragment_main, container, false);
		ViewUtils.inject(this, rootView); // 注入view和事件
		// 注入多肉植物Fragment
		initFragments(succulent, SucculentFragment.class, R.id.main_succulent);
		// 注入奇异花草Fragment
		initFragments(venusaur, VenusaurFragment.class, R.id.main_venusaur);
		// 注入观赏花Fragment
		initFragments(ornamental, OrnamentalFlowerFragment.class,
				R.id.main_ornamental);
		return rootView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		header.onSetTitle(R.string.app_name);
		header.onDisBack(true);
		header.getBg().setBackgroundResource(R.color.red);
		header.getBg().getBackground().setAlpha(25);
		((MainActivity) getActivity()).tintManager.setStatusBarTintResource(
				R.color.red, 25);
		presenters = new FragmentMainPresenter(this);
		presenters.findBannarData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated metdhod stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		scroll.setOnScrollChangedListener(this);
		scroll.smoothScrollTo(0, 0);
	}

	private void initFragments(Fragment fragment, Class<?> cls, int resId) {
		fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		if (fragment == null) {
			try {
				fragment = (Fragment) cls.newInstance();
				transaction.add(resId, fragment);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (fragment.isVisible())
				return;
			transaction.show(fragment);
		}
		Bundle args = new Bundle();
		args.putBoolean("header", false);
		fragment.setArguments(args);

		transaction.commit();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.MainInterface#setImageViewSize()
	 */
	@Override
	public void setImageViewSize() {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hellen.widget.carousel.Carousel.ClickCarouselistener#clickCarousel
	 * (com.hellen.bean.Bannar)
	 */
	@Override
	public void clickCarousel(Object url) {
		// TODO Auto-generated method stub
		presenters.carouselDown(url, getActivity());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.MainInterface#onBannarSuccess()
	 */
	@Override
	public void onBannarSuccess() {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "MainInterface run onBannarSuccess");
		presenters.initCarousel(this, carousel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.MainInterface#onBannarFailed()
	 */
	@Override
	public void onBannarFailed() {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "MainInterface run onBannarFailed");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.widget.scroll.ScrollHeaderView.OnScrollChangedListener#
	 * onScrollChanged(com.hellen.widget.scroll.ScrollHeaderView, int, int, int,
	 * int)
	 */
	@Override
	public void onScrollChanged(ScrollHeaderView view, int x, int y, int oldx,
			int oldy) {
		// TODO Auto-generated method stub
		LogApp.i(TAG, header.getHeight() + "__" + y);
		int height = header.getHeight() * 2;
		if (y < height) {
			header.getBg().setBackgroundResource(R.color.red);
			header.getBg().getBackground().setAlpha(y * 230 / height + 25);
			((MainActivity) getActivity()).tintManager
					.setStatusBarTintResource(R.color.red, y * 230 / height
							+ 25);
		} else {
			header.getBg().getBackground().setAlpha(255);
			header.getBg().setBackgroundResource(R.color.red);
			((MainActivity) getActivity()).tintManager
					.setStatusBarTintResource(R.color.red, 255);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		presenters.clickDown(v, getActivity());
	}

}
