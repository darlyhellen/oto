/**
 * 下午2:15:05
 * @author zhangyh2
 * $
 * FragmentMain.java
 * TODO
 */
package com.darly.dlclent.ui.fragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.GridAdapter;
import com.darly.dlclent.adapter.XListAdapter;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseFragment;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.JsonUtil;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.MainCarouselModel;
import com.darly.dlclent.model.MainMenuModel;
import com.darly.dlclent.model.MainMessageBase;
import com.darly.dlclent.model.MainMessageModel;
import com.darly.dlclent.ui.detail.GoodsDetailActivity;
import com.darly.dlclent.widget.carousel.Carousel;
import com.darly.dlclent.widget.carousel.Carousel.ClickCarouselistener;
import com.darly.dlclent.widget.carousel.ImageHandler;
import com.darly.dlclent.widget.grid.GridViewInList;
import com.darly.dlclent.widget.load.ProgressDialogUtil;
import com.darly.dlclent.widget.xlistview.XListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 FragmentMain $ 下午2:15:05 TODO
 */
public class FragmentMain extends BaseFragment implements OnClickListener,
		ClickCarouselistener, OnItemClickListener {

	private View rootView;
	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;
	// 没有网络情况下，展示页面
	@ViewInject(R.id.not_net_fresh)
	private LinearLayout fresh;
	@ViewInject(R.id.not_net_view)
	private LinearLayout view;
	@ViewInject(R.id.not_net_iv)
	private ImageView iv;
	@ViewInject(R.id.not_net_tv)
	private TextView tv;
	// 有网络情况下，展示页面
	@ViewInject(R.id.main_fragment_content)
	private LinearLayout content;
	/**
	 * 下午2:34:12 TODO 顶部Header
	 */
	private View headerView;
	/**
	 * 下午2:34:22 TODO 轮播外框
	 */
	private RelativeLayout header_cousel;
	/**
	 * 下午2:34:37 TODO 菜单集合
	 */
	private GridViewInList header_grid;
	private GridAdapter grid_adapter;

	@ViewInject(R.id.main_fragment_xlist)
	private XListView xlist;
	private XListAdapter adapter;

	private ProgressDialogUtil load;

	private boolean loadC;

	private boolean loadM;

	private static final String[] IMAGES = new String[] {
			"http://pic2.ooopic.com/01/01/17/53bOOOPIC4e.jpg",
			"http://pic2.ooopic.com/01/01/17/39bOOOPICe8.jpg",
			"http://pic13.nipic.com/20110424/818468_090858462000_2.jpg",
			"http://thumbs.dreamstime.com/z/%C9%BD%C2%B7%BE%B6-20729104.jpg",
			"http://image.72xuan.com/cases/100305/600_600/1003051017041241.jpg",
			"http://pica.nipic.com/2007-11-14/20071114114452315_2.jpg",
			"http://md.cuncun8.com/media/cc8/upload/68192031/0c67e362be347607a877697f46c5f773/101104142242_2026.jpg",
			"http://pic16.nipic.com/20110824/8169416_135754121000_2.jpg",
			"http://b.hiphotos.bdimg.com/album/w%3D2048/sign=79f7b0c594cad1c8d0bbfb274b066509/5366d0160924ab18de9241dd34fae6cd7a890b57.jpg",
			"http://pic2.ooopic.com/01/01/18/42bOOOPIC6c.jpg" };

	/**
	 * TODO轮播开始循环使用的Handler
	 */
	WeakReference<FragmentMain> weak = new WeakReference<FragmentMain>(this);
	public ImageHandler<FragmentMain> imagehandler = new ImageHandler<FragmentMain>(
			weak);

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
		headerView = inflater.inflate(R.layout.item_main_fragment_header, null);
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
		title.setText(R.string.footer_main);
		load = new ProgressDialogUtil(getActivity());
		// 设置参数
		iv.setImageResource(R.drawable.ic_me_press);
		tv.setText("网络未连接，点击刷新");

		header_cousel = (RelativeLayout) headerView
				.findViewById(R.id.fragment_main_header_cars);
		header_grid = (GridViewInList) headerView
				.findViewById(R.id.fragment_main_header_grid);

		LayoutParams cou = new LayoutParams(APPEnum.WIDTH.getLen(),
				(int) (APPEnum.WIDTH.getLen() / 2.5));

		header_cousel.setLayoutParams(cou);

		xlist.addHeaderView(headerView);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		grid_adapter = new GridAdapter(null,
				R.layout.item_main_fragment_header_grid, getActivity(),
				imageLoader, options);

		header_grid.setAdapter(grid_adapter);

		adapter = new XListAdapter(null, R.layout.item_main_fragment_xlist,
				getActivity(), imageLoader, options);

		xlist.setAdapter(adapter);

		loadingCorsel();

		loadingMsg();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		view.setOnClickListener(this);
		xlist.setOnItemClickListener(this);
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
		case R.id.not_net_view:
			loadC = false;
			loadM = false;
			loadingCorsel();
			loadingMsg();
			break;

		default:
			break;
		}
	}

	/**
	 * 上午10:04:24
	 * 
	 * @author zhangyh2 TODO 网络请求首页轮播
	 */
	private void loadingCorsel() {
		// 首次进来后，进行网络请求轮播信息。
		if (!APP.isNetworkConnected(getActivity())) {
			fresh.setVisibility(View.VISIBLE);
			content.setVisibility(View.GONE);
			ToastApp.showToast(R.string.neterror);
		} else {
			if (load != null && !load.isShowing()) {
				load.setMessage(R.string.xlistview_header_hint_loading);
				load.show();
			}
			fresh.setVisibility(View.GONE);
			content.setVisibility(View.VISIBLE);
			String url = "";
			if (url != null && url.length() > 0) {
				// 网络请求
				HttpClient.get(getActivity(), url, null,
						new RequestCallBack<String>() {

							@Override
							public void onSuccess(ResponseInfo<String> arg0) {
								// TODO Auto-generated method stub
								loadC = true;
								loadCoursel(arg0.result);
							}

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								// TODO Auto-generated method stub
								loadC = true;
							}
						});

			} else {
				// 轮播假数据
				List<MainCarouselModel> list = new ArrayList<MainCarouselModel>();
				String json = null;
				if (new Random().nextBoolean()) {
					for (int i = 0; i < IMAGES.length; i++) {
						list.add(new MainCarouselModel(i, url, "show" + i,
								IMAGES[i]));
					}
					BaseModel<List<MainCarouselModel>> model = new BaseModel<List<MainCarouselModel>>(
							200, "", list);
					json = JsonUtil.pojo2Json(model);
				} else {
					BaseModel<List<MainCarouselModel>> model = new BaseModel<List<MainCarouselModel>>(
							110, "网络数据不存在", list);
					json = JsonUtil.pojo2Json(model);
				}
				loadC = true;
				loadCoursel(json);
			}

		}
	}

	/**
	 * 上午10:27:40
	 * 
	 * @author zhangyh2 TODO 解析返回的首页轮播
	 */
	protected void loadCoursel(String result) {
		// TODO Auto-generated method stub
		if (loadC && loadM && load != null && load.isShowing()) {
			load.cancel();
		}
		if (result == null) {
			return;
		}
		// 开始解析轮播
		LogUtils.i(result);
		BaseModel<List<MainCarouselModel>> data = new BaseModelPaser<List<MainCarouselModel>>()
				.paserJson(result, new TypeToken<List<MainCarouselModel>>() {
				});
		if (data != null && data.getCode() == 200) {
			// 设置轮播
			SharePreferHelp.putValue(APPEnum.CARSOUL.getDec(), result);
			Carousel<FragmentMain> carous = new Carousel<FragmentMain>(
					getActivity(), data.getData(), imageLoader, options,
					imagehandler);
			header_cousel.addView(carous.view);
			carous.setClickCarouselistener(this);
		} else {
			loadCoursel(SharePreferHelp
					.getValue(APPEnum.CARSOUL.getDec(), null));
			ToastApp.showToast(data.getMsg());
		}
	}

	/**
	 * 上午10:28:48
	 * 
	 * @author zhangyh2 TODO 网络请求数据
	 */
	private void loadingMsg() {
		// TODO Auto-generated method stub
		// 首次进来后，进行网络请求商品列表，以及8个菜单。
		if (!APP.isNetworkConnected(getActivity())) {
			fresh.setVisibility(View.VISIBLE);
			content.setVisibility(View.GONE);
			ToastApp.showToast(R.string.neterror);
		} else {
			if (load != null && !load.isShowing()) {
				load.setMessage(R.string.xlistview_header_hint_loading);
				load.show();
			}
			fresh.setVisibility(View.GONE);
			content.setVisibility(View.VISIBLE);
			String url = "";
			if (url != null && url.length() > 0) {
				// 网络请求
				HttpClient.get(getActivity(), url, null,
						new RequestCallBack<String>() {

							@Override
							public void onSuccess(ResponseInfo<String> arg0) {
								// TODO Auto-generated method stub
								loadM = true;
								loadCoursel(arg0.result);
							}

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								// TODO Auto-generated method stub
								loadM = true;
							}
						});

			} else {
				// 假数据
				List<MainMessageModel> data = new ArrayList<MainMessageModel>();
				List<MainMenuModel> menu = new ArrayList<MainMenuModel>();
				String json = null;
				if (new Random().nextBoolean()) {
					for (int i = 0; i < IMAGES.length; i++) {
						if (i == 0) {
							data.add(new MainMessageModel(i, "特卖", null, null,
									null, 0, 0, 0, "标题"));
						} else if (i == 5) {
							data.add(new MainMessageModel(i, "本周商品", null,
									null, null, 0, 0, 0, "标题"));
						} else {
							data.add(new MainMessageModel(i, null, "商品" + i,
									"描述商品信息" + i, IMAGES[i], i * 110, i * 100,
									i, "商品"));
						}
						menu.add(new MainMenuModel(i, "菜单" + i, url, IMAGES[i]));
					}
					MainMessageBase base = new MainMessageBase(200, "", data,
							menu);
					json = JsonUtil.pojo2Json(base);
				} else {
					MainMessageBase base = new MainMessageBase(110, "网络数据不存在",
							data, menu);
					json = JsonUtil.pojo2Json(base);
				}
				loadM = true;
				loadMsg(json);
			}

		}
	}

	/**
	 * 上午10:27:40
	 * 
	 * @author zhangyh2 TODO 解析返回的数据
	 */
	protected void loadMsg(String result) {
		// TODO Auto-generated method stub
		if (loadC && loadM && load != null && load.isShowing()) {
			load.cancel();
		}
		if (result == null) {
			return;
		}
		// 开始解析
		LogUtils.i(result);
		MainMessageBase data = new Gson().fromJson(result,
				MainMessageBase.class);
		if (data != null && data.getCode() == 200) {
			SharePreferHelp.putValue(APPEnum.MAINMMSG.getDec(), result);
			// 设置用户主界面
			if (data.getMenu() != null) {
				header_grid.setNumColumns(data.getMenu().size() / 2);
				// 设置菜单
				grid_adapter.setData(data.getMenu());
			}
			if (data.getMsg() != null) {
				// 设置商品
				adapter.setData(data.getData());
			}
		} else {
			ToastApp.showToast(data.getMsg());
			loadMsg(SharePreferHelp.getValue(APPEnum.MAINMMSG.getDec(), null));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.darly.dlclent.widget.carousel.Carousel.ClickCarouselistener#clickCarousel
	 * (com.darly.dlclent.model.MainCarouselModel)
	 */
	@Override
	public void clickCarousel(MainCarouselModel url) {
		// TODO Auto-generated method stub
		LogUtils.i(url.getTitle());
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
		// TODO 商品条目点击事件
		MainMessageModel mainMessageModel = (MainMessageModel) parent
				.getItemAtPosition(position);
		Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
		intent.putExtra("CommodityID", mainMessageModel.getCommodityID());
		startActivity(intent);
	}

}
