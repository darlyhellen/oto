/**下午3:44:02
 * @author zhangyh2
 * MainPresenter.java
 * TODO
 */
package com.hellen.presenter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.hellen.adapter.WholeListAdapter;
import com.hellen.base.BaseFragment;
import com.hellen.base.BaseListener;
import com.hellen.base.ConsMVP;
import com.hellen.bean.Bannar;
import com.hellen.biz.FragmentMainInterface;
import com.hellen.biz.imp.FragmentMain;
import com.hellen.common.LogApp;
import com.hellen.common.SharePreferHelp;
import com.hellen.flower.R;
import com.hellen.flower.fragment.HomeFragment;
import com.hellen.flower.login.LoginActivity;
import com.hellen.flower.pay.PayPop;
import com.hellen.widget.carousel.Carousel;
import com.hellen.widget.carousel.Carousel.CarouselModel;
import com.hellen.widget.carousel.ImageHandler;
import com.hellen.widget.circlemenu.CirclePouple;
import com.hellen.widget.dialog.ShowLoading;

/**
 * @author zhangyh2 MainPresenter 下午3:44:02 TODO
 */
public class FragmentMainPresenter implements CirclePouple.PoupleClickListener {

	private String tag = getClass().getSimpleName();

	private FragmentMainInterface view;

	private FragmentMainInterface.MainBiz biz;

	private ShowLoading loading;

	/**
	 * TODO轮播开始循环使用的Handler
	 */
	private WeakReference<HomeFragment> weak;
	private Carousel<CarouselModel> bannarCars;
	private List<CarouselModel> bannarData;
	
	private PayPop pays;

	public FragmentMainPresenter(FragmentMainInterface views) {
		this.view = views;
		biz = new FragmentMain();
		view.setPersenter(biz);
		view.setImageViewSize();
		loading = new ShowLoading(((BaseFragment) views).getActivity());
		loading.setMessage(R.string.loading);
		pays = new PayPop(((BaseFragment) views).getActivity());
	}

	public void findBannarData() {
		biz.onStart();
		if (!loading.isShowing()) {
			loading.show();
		}
		biz.main_bannar(new BaseListener<List<CarouselModel>>() {

			@Override
			public void onSucces(List<CarouselModel> result) {
				// TODO Auto-generated method stub
				bannarData = result;
				view.onBannarSuccess();
				loading.cancel();
			}

			@Override
			public void onFaild(int code, String info) {
				// TODO Auto-generated method stub
				view.onBannarFailed();
				loading.cancel();
			}
		});
	}

	public void initCarousel(HomeFragment context, RelativeLayout carousel) {
		if (carousel == null || bannarData == null) {
			return;
		}
		weak = new WeakReference<HomeFragment>(context);
		bannarCars = new Carousel<CarouselModel>(context.getActivity(),
				bannarData, new ImageHandler(weak));
		carousel.addView(bannarCars.view);
		bannarCars.setClickCarouselistener(context);
	}

	public void carouselDown(Object bannar, Context context) {
		pays.show(((HomeFragment) view).rootView);
//		if (bannar instanceof Bannar && checkUserLogin(context)) {
//			Bannar bannas = (Bannar) bannar;
//			if (bannas.getType() != null) {
//				LogApp.i(tag,
//						"carouselDown" + bannas.getTitle() + bannas.getType());
//				try {
//					Class<?> clazz = Class.forName(bannas.getType());
//					Intent intent = new Intent(context, clazz);
//					intent.putExtra("loadURL", bannas.getUrl());
//					context.startActivity(intent);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		}
	}

	public boolean checkUserLogin(Context context) {
		// TODO Auto-generated method stub
		LogApp.i(tag, "checkUserLogin()");
		if (null != SharePreferHelp.getValue(ConsMVP.TOKEN.getDec(), null)) {
			return true;
		} else {
			Intent i11 = new Intent();
			i11.setClass(context, LoginActivity.class);
			context.startActivity(i11);
			return false;
		}
	}

	private String[] mItemTexts = new String[] { "安全中心 ", "特色服务", "投资理财",
			"转账汇款" };
	private int[] mItemImgs = new int[] { R.drawable.home_mbank_1_normal,
			R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
			R.drawable.home_mbank_4_normal };

	/**
	 * 下午3:32:00
	 * 
	 * @author zhangyh2 TODO
	 */
	public void clickDown(View v, Context context) {
		// TODO Auto-generated method stub
		new CirclePouple(mItemTexts, mItemImgs, context,
				R.drawable.home_mbank_6_normal).setPouple(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hellen.widget.circlemenu.CirclePouple.PoupleClickListener#itemClick
	 * (android.view.View, int, java.lang.String)
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
	 * com.hellen.widget.circlemenu.CirclePouple.PoupleClickListener#itemCenterClick
	 * (android.view.View)
	 */
	@Override
	public void itemCenterClick(View view) {
		// TODO Auto-generated method stub
		LogApp.i(view.toString());

	}

	public void setWholeList(WholeListAdapter adapter) {
		if (bannarData != null && adapter != null) {
			List<Bannar> list = new ArrayList<Bannar>();
			for (CarouselModel carouselModel : bannarData) {
				if (carouselModel instanceof Bannar) {
					list.add((Bannar) carouselModel);
				}
			}
			adapter.setData(list);
		}
	}

}
