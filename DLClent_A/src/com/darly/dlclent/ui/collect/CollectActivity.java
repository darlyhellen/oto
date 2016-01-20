/**上午9:58:12
 * @author zhangyh2
 * CollectActivity.java
 * TODO
 */
package com.darly.dlclent.ui.collect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.XListAdapter;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.JsonUtil;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.MainMessageBase;
import com.darly.dlclent.model.MainMessageModel;
import com.darly.dlclent.ui.detail.GoodsDetailActivity;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 CollectActivity 上午9:58:12 TODO 收藏夹列表页面
 */
@ContentView(R.layout.activity_collect)
public class CollectActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener {

	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	@ViewInject(R.id.collect_lv)
	private ListView lv;

	private XListAdapter adapter;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		title.setText("收藏夹");
		back.setVisibility(View.VISIBLE);
		other.setVisibility(View.INVISIBLE);
	}

	/**
	 * 上午11:56:56
	 * 
	 * @author zhangyh2 TODO
	 */
	private void getCollections() {
		// TODO Auto-generated method stub
		if (!APP.isNetworkConnected(this)) {
			ToastApp.showToast(R.string.neterror);
			return;
		}
		String url = "";
		if (url != null && url.length() > 0) {
			// 网络请求
			RequestParams params = new RequestParams();
			HttpClient.get(this, url, params, new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					// TODO Auto-generated method stub
					showCollect(arg0.result);
				}

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					ToastApp.showToast(R.string.neterror);
				}
			});

		} else {
			// 轮播假数据
			String json = null;
			List<MainMessageModel> data = new ArrayList<MainMessageModel>();
			if (new Random().nextBoolean()) {
				for (int i = 0; i < IMAGES.length; i++) {
					data.add(new MainMessageModel(i, null, "商品" + i, "描述商品信息"
							+ i, IMAGES[i], i * 110, i * 100, i, "商品"));
				}
				MainMessageBase base = new MainMessageBase(200, "", data, null);
				json = JsonUtil.pojo2Json(base);
			} else {
				MainMessageBase base = new MainMessageBase(110, "网络数据不存在",
						null, null);
				json = JsonUtil.pojo2Json(base);
			}
			showCollect(json);
		}
	}

	/**
	 * 上午11:58:23
	 * 
	 * @author zhangyh2 TODO
	 */
	private void showCollect(String json) {
		// TODO Auto-generated method stub
		if (json == null) {
			return;
		}
		LogUtils.i(json);
		// 开始解析
		MainMessageBase data = new Gson().fromJson(json, MainMessageBase.class);
		if (data != null && data.getCode() == 200) {
			if (data.getMsg() != null) {
				// 设置商品
				adapter.setData(data.getData());
			}
		} else {
			ToastApp.showToast(data.getMsg());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		adapter = new XListAdapter(null, R.layout.item_main_fragment_xlist,
				this, imageLoader, options);
		lv.setAdapter(adapter);
		// 进来后获取数据
		getCollections();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		back.setOnClickListener(this);
		lv.setOnItemClickListener(this);
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
		case R.id.header_back:
			finish();
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
		MainMessageModel mainMessageModel = (MainMessageModel) parent
				.getItemAtPosition(position);
		Intent intent = new Intent(this, GoodsDetailActivity.class);
		intent.putExtra("CommodityID", mainMessageModel.getCommodityID());
		startActivity(intent);
	}

}
