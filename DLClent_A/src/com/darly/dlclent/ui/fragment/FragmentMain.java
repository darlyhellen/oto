/**
 * 下午2:15:05
 * @author zhangyh2
 * $
 * FragmentMain.java
 * TODO
 */
package com.darly.dlclent.ui.fragment;

import java.lang.ref.WeakReference;
import java.util.List;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.GridAdapter;
import com.darly.dlclent.adapter.XListAdapter;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseFragment;
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.MainCarouselModel;
import com.darly.dlclent.model.MainMessageBase;
import com.darly.dlclent.model.MainMessageModel;
import com.darly.dlclent.ui.detail.GoodsDetailActivity;
import com.darly.dlclent.ui.video.VideoListActivity;
import com.darly.dlclent.ui.web.WebViewActivity;
import com.darly.dlclent.widget.carousel.Carousel;
import com.darly.dlclent.widget.carousel.Carousel.ClickCarouselistener;
import com.darly.dlclent.widget.carousel.ImageHandler;
import com.darly.dlclent.widget.grid.GridViewInList;
import com.darly.dlclent.widget.load.ProgressDialogUtil;
import com.darly.dlclent.widget.share.CustomShareBoard;
import com.darly.dlclent.widget.xlistview.XListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.socialize.bean.SocializeConfig;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

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
	@ViewInject(R.id.header_btn)
	private Button btn;
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

	/**
	 * TODO轮播开始循环使用的Handler
	 */
	WeakReference<FragmentMain> weak = new WeakReference<FragmentMain>(this);
	public ImageHandler<FragmentMain> imagehandler = new ImageHandler<FragmentMain>(
			weak);

	private UMSocialService mController = UMServiceFactory
			.getUMSocialService(APPEnum.STORAGE_ROOT_DIR.getDec());

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
		btn.setVisibility(View.VISIBLE);
		btn.setText("分享");
		back.setVisibility(View.INVISIBLE);
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

		loadingCorsel();

		loadingMsg();

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
				R.layout.item_main_fragment_header_grid, getActivity());

		header_grid.setAdapter(grid_adapter);

		adapter = new XListAdapter(null, R.layout.item_main_fragment_xlist,
				getActivity());
		xlist.setAdapter(adapter);
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
		header_grid.setOnItemClickListener(this);
		btn.setOnClickListener(this);
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
		case R.id.header_btn:
			// 进行分享设置
			CustomShareBoard board = new CustomShareBoard(getActivity());
			board.showAtLocation(getActivity().getWindow().getDecorView(),
					Gravity.BOTTOM, 0, 0);
			addWXPlatform();
			setShareContent();
			break;
		default:
			break;
		}
	}

	/**
	 * @功能描述 : 添加微信平台分享
	 * @return
	 */
	private void addWXPlatform() {
		// 注意：在微信授权的时候，必须传递appSecret
		// wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
		String appId = "wx5933eee9aa4d543c";
		String appSecret = "7c4930e15f74011c4ab2916a29555e28";
		// 添加微信平台

		UMWXHandler wxHandler = new UMWXHandler(getActivity(), appId, appSecret);
		wxHandler.addToSocialSDK();
		wxHandler.showCompressToast(false);
		// 支持微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(getActivity(), appId,
				appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		LogUtils.i("onActivityResult");
		UMSsoHandler ssoHandler = SocializeConfig.getSocializeConfig()
				.getSsoHandler(requestCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}

	/**
	 * 根据不同的平台设置不同的分享内容</br>
	 */
	private void setShareContent() {

		UMImage urlImage = new UMImage(getActivity(),
				"http://www.umeng.com/images/pic/social/integrated_3.png");

		// 微信分享
		WeiXinShareContent weixinContent = new WeiXinShareContent();
		weixinContent
				.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能-微信。http://www.umeng.com/social");
		weixinContent.setTitle("友盟社会化分享组件-微信");
		weixinContent.setTargetUrl("http://www.umeng.com/social");
		weixinContent.setShareMedia(urlImage);
		mController.setShareMedia(weixinContent);

		// 设置微信圈分享的内容
		CircleShareContent circleMedia = new CircleShareContent();
		circleMedia
				.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能-朋友圈。http://www.umeng.com/social");
		circleMedia.setTitle("友盟社会化分享组件-朋友圈");
		circleMedia.setShareMedia(urlImage);
		circleMedia.setTargetUrl("http://www.umeng.com/social");
		mController.setShareMedia(circleMedia);

		UMImage image = new UMImage(getActivity(),
				BitmapFactory.decodeResource(getResources(), R.drawable.icon));
		image.setTitle("thumb title");
		image.setThumb("http://www.umeng.com/images/pic/social/integrated_3.png");
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

			// List<BasicNameValuePair> params = new
			// ArrayList<BasicNameValuePair>();
			// params.add(new BasicNameValuePair("bannar", "1"));
			// OKClient.getAsyn(params,ConsHttpUrl.USERHOME, new
			// ResultCallback<String>() {
			//
			// @Override
			// public void onError(Request request, Exception e) {
			// // TODO Auto-generated method stub
			// loadC = true;
			// loadCoursel(SharePreferHelp.getValue(
			// APPEnum.CARSOUL.getDec(), null));
			// }
			//
			// @Override
			// public void onResponse(String response) {
			// // TODO Auto-generated method stub
			// loadC = true;
			// loadCoursel(response);
			// }
			// });

			RequestParams params = new RequestParams();
			params.addQueryStringParameter("bannar", "1");
			// 网络请求
			HttpClient.get(getActivity(), ConsHttpUrl.HOMEBANNARNEW/*
																	 * ConsHttpUrl.
																	 * USERHOME
																	 */,
					params, new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							// TODO Auto-generated method stub
							loadC = true;
							LogUtils.i("new");
							loadCoursel(arg0.result);
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub
							loadC = true;
							LogUtils.i("old");
							loadCoursel(SharePreferHelp.getValue(
									APPEnum.CARSOUL.getDec(), null));
						}
					});

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
					getActivity(), data.getData(), imagehandler);
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
			// 网络请求
			RequestParams params = new RequestParams();
			params.addQueryStringParameter("goods", "1");
			params.addQueryStringParameter("page", "1");
			HttpClient.get(getActivity(), ConsHttpUrl.HOMEGOODSNEW/*
																 * ConsHttpUrl.
																 * USERHOME
																 */, params,
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							// TODO Auto-generated method stub
							loadM = true;
							LogUtils.i("new");
							loadMsg(arg0.result);
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub
							loadM = true;
							LogUtils.i("old");
							loadMsg(SharePreferHelp.getValue(
									APPEnum.MAINMMSG.getDec(), null));
						}
					});

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
		startActivity(new Intent(getActivity(), VideoListActivity.class));
		// HttpClient.post(ConsHttpUrl.USERHOME, "",
		// new RequestCallBack<String>() {
		//
		// @Override
		// public void onSuccess(ResponseInfo<String> arg0) {
		// // TODO Auto-generated method stub
		// LogUtils.i(arg0.result);
		// }
		//
		// @Override
		// public void onFailure(HttpException arg0, String arg1) {
		// // TODO Auto-generated method stub
		// arg0.printStackTrace();
		// LogUtils.i(arg1);
		// ToastApp.showToast(R.string.neterror_norespanse);
		// }
		// });
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
		if (parent instanceof ListView) {
			MainMessageModel mainMessageModel = (MainMessageModel) parent
					.getItemAtPosition(position);
			if (mainMessageModel == null) {
				return;
			}
			Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
			intent.putExtra("CommodityID", mainMessageModel.getCommodityID());
			startActivity(intent);
		}
		if (parent instanceof GridView) {
			Intent intent = new Intent(getActivity(), WebViewActivity.class);
			if (position % 2 == 0) {
				intent.putExtra("url", ConsHttpUrl.SHOW_LOAD);
			} else {
				intent.putExtra("url", ConsHttpUrl.SHOW_LOAD1);
			}
			startActivity(intent);
		}

	}
}
