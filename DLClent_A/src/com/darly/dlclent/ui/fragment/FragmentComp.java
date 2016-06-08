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

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.CompGridAdapter;
import com.darly.dlclent.adapter.CompListAdapter;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseFragment;
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseHomePageModel;
import com.darly.dlclent.model.ClientCompHomePage;
import com.darly.dlclent.model.MainCarouselModel;
import com.darly.dlclent.ui.web.WebViewActivity;
import com.darly.dlclent.widget.carousel.Carousel;
import com.darly.dlclent.widget.carousel.Carousel.ClickCarouselistener;
import com.darly.dlclent.widget.carousel.ImageHandler;
import com.darly.dlclent.widget.listview.WholeGridView;
import com.darly.dlclent.widget.listview.WholeListView;
import com.darly.dlclent.widget.load.ProgressDialogUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.socialize.bean.SocializeConfig;
import com.umeng.socialize.sso.UMSsoHandler;

/**
 * @author zhangyh2 FragmentMain $ 下午2:15:05 TODO
 */
public class FragmentComp extends BaseFragment implements OnClickListener,
		ClickCarouselistener, OnItemClickListener {

	private View rootView;
	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_btn)
	private Button btn;

	private WholeListView lv;
	private LinearLayout centers;
	private WholeGridView gv;

	private CompListAdapter ladapter;
	private CompGridAdapter gadapter;

	/**
	 * 下午2:34:22 TODO 轮播外框
	 */
	private RelativeLayout header_cousel;

	private ProgressDialogUtil load;

	/**
	 * TODO轮播开始循环使用的Handler
	 */
	WeakReference<FragmentComp> weak = new WeakReference<FragmentComp>(this);
	public ImageHandler<FragmentComp> imagehandler = new ImageHandler<FragmentComp>(
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
		rootView = inflater.inflate(R.layout.fragment_comp, container, false);
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
		btn.setVisibility(View.GONE);
		back.setVisibility(View.GONE);
		load = new ProgressDialogUtil(getActivity());
		load.setMessage(R.string.xlistview_header_hint_loading);
		load.show();
		// 初始化控件
		lv = (WholeListView) rootView.findViewById(R.id.comp_list);
		centers = (LinearLayout) rootView.findViewById(R.id.comp_l_grid);
		gv = (WholeGridView) rootView.findViewById(R.id.comp_grid);
		header_cousel = (RelativeLayout) rootView
				.findViewById(R.id.comp_cousel);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		// 绑定数据
		LayoutParams cou = new LayoutParams(APPEnum.WIDTH.getLen(),
				(int) (APPEnum.WIDTH.getLen() / 2.66));
		header_cousel.setLayoutParams(cou);
		ladapter = new CompListAdapter(null, R.layout.item_comp_list,
				getActivity());
		lv.setAdapter(ladapter);
		gadapter = new CompGridAdapter(null, R.layout.item_comp_grid,
				getActivity());
		gv.setAdapter(gadapter);

		// 检查服务端首页版本
		HttpClient.get(getActivity(), ConsHttpUrl.COMPVERSION,
				new RequestParams(), new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						if (SharePreferHelp.getValue(
								APPEnum.COMPVERSION.getDec(), null) != null) {
							if (SharePreferHelp.getValue(
									APPEnum.COMPVERSION.getDec(), null).equals(
									arg0.result)) {
								load.dismiss();
								LogUtils.i("old");
								paserCompMain(SharePreferHelp.getValue(
										APPEnum.COMPHOMEPAGE.getDec(), null));
							} else {
								SharePreferHelp.putValue(
										APPEnum.COMPVERSION.getDec(),
										arg0.result);
								showHomePage();
							}
						} else {
							SharePreferHelp.putValue(
									APPEnum.COMPVERSION.getDec(), arg0.result);
							showHomePage();
						}
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						showHomePage();
					}
				});
	}

	/**
	 * 下午2:22:22
	 * 
	 * @author zhangyh2 TODO
	 */
	private void showHomePage() {
		HttpClient.get(getActivity(), ConsHttpUrl.COMPHOMEPAGE,
				new RequestParams(), new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						load.dismiss();
						LogUtils.i("new");
						// 解析首页
						paserCompMain(arg0.result);
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						load.dismiss();
						LogUtils.i("old");
						ToastApp.showToast(R.string.neterror);
						// 调用缓存
						paserCompMain(SharePreferHelp.getValue(
								APPEnum.COMPHOMEPAGE.getDec(), null));
					}
				});
	}

	/**
	 * 上午10:34:07
	 * 
	 * @author zhangyh2 TODO 解析首页数据
	 */
	protected void paserCompMain(String result) {
		// TODO Auto-generated method stub
		if (result == null) {
			return;
		}
		// 开始解析
		LogUtils.i(result);
		BaseHomePageModel data = new Gson().fromJson(result,
				BaseHomePageModel.class);
		if (data != null && data.getCode() == 200) {
			SharePreferHelp.putValue(APPEnum.COMPHOMEPAGE.getDec(), result);
			// 设置顶部菜单
			if (data.getTop() != null && data.getTop().size() > 0) {
				ladapter.setData(data.getTop());
			}
			// 设置中部菜单
			if (data.getCenter() != null && data.getCenter().size() > 0) {
				centers.setVisibility(View.VISIBLE);
				gadapter.setData(data.getCenter());
			} else {
				centers.setVisibility(View.GONE);
			}
			// 设置轮播
			if (data.getBottom() != null && data.getBottom().size() > 0) {
				header_cousel.setVisibility(View.VISIBLE);
				List<MainCarouselModel> carousel = new ArrayList<MainCarouselModel>();
				for (ClientCompHomePage homepage : data.getBottom()) {
					carousel.add(new MainCarouselModel(homepage.getId(),
							homepage.getCompUrl(), homepage.getCompFirstname(),
							homepage.getCompIcon()));
				}
				Carousel<FragmentComp> carous = new Carousel<FragmentComp>(
						getActivity(), carousel,
						imagehandler);
				header_cousel.addView(carous.view);
				carous.setClickCarouselistener(this);
			} else {
				header_cousel.setVisibility(View.GONE);
			}

		} else {
			ToastApp.showToast(data.getMsg());
			paserCompMain(SharePreferHelp.getValue(
					APPEnum.COMPHOMEPAGE.getDec(), null));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		lv.setOnItemClickListener(this);
		gv.setOnItemClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
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
		goWhere(url.getUrl());
	}

	/**
	 * 下午4:38:25
	 * 
	 * @author zhangyh2 TODO
	 */
	private void goWhere(String url) {
		if (url != null) {
			if (url.startsWith("http")) {
				// 直接连接模式的话，跳转Webview
				LogUtils.i(url);
				Intent intent = new Intent(getActivity(), WebViewActivity.class);
				intent.putExtra("url", url);
				startActivity(intent);
			} else {
				try {
					Class<?> s = Class.forName(url);
					startActivity(new Intent(getActivity(), s));
				} catch (Exception e) {
					// TODO: handle exception
					ToastApp.showToast("该功能还未启用，请选择其他功能。");
				}
			}
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
		ClientCompHomePage home = (ClientCompHomePage) parent
				.getItemAtPosition(position);
		if (home != null) {
			goWhere(home.getCompUrl());
		}
	}
}
