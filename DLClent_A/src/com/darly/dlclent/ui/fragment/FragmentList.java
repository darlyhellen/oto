/**
 * 下午2:15:05
 * @author zhangyh2
 * $
 * FragmentMain.java
 * TODO
 */
package com.darly.dlclent.ui.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.ListActAdapter;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseFragment;
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.ActThemeModel;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.widget.xlistview.XListView;
import com.darly.dlclent.widget.xlistview.XListView.IXListViewListener;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 FragmentMain $ 下午2:15:05 TODO 商品活动列表
 */
public class FragmentList extends BaseFragment implements IXListViewListener,
		OnItemClickListener {

	private View rootView;
	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	@ViewInject(R.id.list_fragment_xlist)
	private XListView xlist;
	private ListActAdapter adapter;
	// 没有网络情况下，展示页面
	@ViewInject(R.id.not_net_fresh)
	private LinearLayout fresh;
	// 有网络情况下，展示页面
	@ViewInject(R.id.list_fragment_content)
	private LinearLayout content;

	private int page = 1;
	/**
	 * 下午2:22:41 TODO 多页加载数据集合。
	 */
	private List<ActThemeModel> bigMap = null;


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
		rootView = inflater.inflate(R.layout.fragment_list, container, false);
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
		title.setText(R.string.footer_list);
		// 获取整个活动列表活动主题的情况
		if (!APP.isNetworkConnected(getActivity())) {
			fresh.setVisibility(View.VISIBLE);
			content.setVisibility(View.GONE);
			ToastApp.showToast(R.string.neterror);
		} else {
			showActList(0);
			fresh.setVisibility(View.GONE);
			content.setVisibility(View.VISIBLE);
		}

		adapter = new ListActAdapter(null, R.layout.item_act_list,
				getActivity());
		xlist.setAdapter(adapter);
		// 设置xlistview可以加载、刷新
		xlist.setPullLoadEnable(true);
		xlist.setPullRefreshEnable(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		xlist.setXListViewListener(this);
		xlist.setOnItemClickListener(this);
	}

	/**
	 * 上午10:12:40
	 * 
	 * @author zhangyh2 TODO 请求服务端，获取整个活动的主题。形成列表
	 *         <b>s</b>的值为0/1这三类。0表示初次加载，1表示加载更多
	 */
	private void showActList(final int s) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("page", page + "");
		HttpClient.get(getActivity(), ConsHttpUrl.ACTTHEME, params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						// 获取成功。对数据进行解析。并更新列表
						loadList(arg0.result, s);
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						// 获取失败，加载缓存数据
						loadList(SharePreferHelp.getValue(
								APPEnum.THEME.getDec(), null), s);
					}
				});
	}

	/**
	 * 上午10:16:43
	 * 
	 * @author zhangyh2 TODO
	 * @param result
	 * @param s
	 */
	private void loadList(String result, int s) {
		// TODO Auto-generated method stub
		if (result == null) {
			return;
		}
		LogUtils.i(result);
		BaseModel<List<ActThemeModel>> data = new BaseModelPaser<List<ActThemeModel>>()
				.paserJson(result, new TypeToken<List<ActThemeModel>>() {
				});

		if (data != null && data.getCode() == 200) {
			// 设置轮播
			switch (s) {
			case 0:
				SharePreferHelp.putValue(APPEnum.THEME.getDec(), result);
				adapter.setData(data.getData());
				bigMap = new ArrayList<ActThemeModel>();
				bigMap.addAll(data.getData());
				break;
			case 1:
				bigMap.addAll(data.getData());
				adapter.setData(bigMap);
				break;
			default:
				break;
			}
			// 设置xlistview可以加载、刷新
			xlist.setPullLoadEnable(true);
			xlist.setPullRefreshEnable(true);
		} else {
			ToastApp.showToast(data.getMsg());
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
		if (parent.getItemAtPosition(position) instanceof ActThemeModel) {
			ActThemeModel model = (ActThemeModel) parent
					.getItemAtPosition(position);
			LogUtils.i(model.getDescription());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.darly.dlclent.widget.xlistview.XListView.IXListViewListener#onRefresh
	 * ()
	 */
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				page = 1;
				showActList(0);
				xlist.stopRefresh();
				xlist.setPullLoadEnable(true);
				onLoad();
			}
		}, 500);
	}

	/**
	 * 下午2:13:51
	 * 
	 * @author zhangyh2 TODO
	 */
	@SuppressLint("SimpleDateFormat")
	protected void onLoad() {
		// TODO Auto-generated method stub
		xlist.stopRefresh();
		xlist.stopLoadMore();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		xlist.setRefreshTime(format.format(new Date()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.darly.dlclent.widget.xlistview.XListView.IXListViewListener#onLoadMore
	 * ()
	 */
	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				page++;
				showActList(1);
				xlist.stopLoadMore();
				xlist.setPullLoadEnable(false);
			}
		}, 500);
	}


}
