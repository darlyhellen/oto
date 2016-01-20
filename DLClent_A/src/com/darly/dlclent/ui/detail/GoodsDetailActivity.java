/**上午9:53:51
 * @author zhangyh2
 * GoodsDetailActivity.java
 * TODO
 */
package com.darly.dlclent.ui.detail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.DetailsGoodsAdapter;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.JsonUtil;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.DetailsGoodsPro;
import com.darly.dlclent.model.DetailsGoodsProperty;
import com.darly.dlclent.model.DetailsGoodsShow;
import com.darly.dlclent.model.MainMessageModel;
import com.darly.dlclent.widget.load.ProgressDialogUtil;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 GoodsDetailActivity 上午9:53:51 TODO 商品详情展示页面，并附加评论
 */
@ContentView(R.layout.activity_goods_details)
public class GoodsDetailActivity extends BaseActivity implements
		OnClickListener, OnPageChangeListener {

	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	// 图片集合
	@ViewInject(R.id.details_images)
	private RelativeLayout imagecontent;
	@ViewInject(R.id.details_viewpager)
	private ViewPager detail_vp;

	private List<ImageView> imageData = new ArrayList<ImageView>();
	private DetailsGoodsAdapter adapter;
	@ViewInject(R.id.details_ll)
	private LinearLayout detail_tap;
	private List<ImageView> slide = new ArrayList<ImageView>();

	@ViewInject(R.id.details_info_name)
	private TextView name;
	@ViewInject(R.id.details_info_descrip)
	private TextView description;
	@ViewInject(R.id.details_info_price)
	private TextView price;
	@ViewInject(R.id.details_info_original)
	private TextView original;

	@ViewInject(R.id.goods_card_cards)
	private ImageView card;
	@ViewInject(R.id.goods_card_collect)
	private ImageView love;
	private boolean isCollect;
	@ViewInject(R.id.goods_card_buy)
	private Button shop;

	private ProgressDialogUtil load;

	private long commodityID;

	// 一组顶部图片展示图
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

		commodityID = getIntent().getLongExtra("CommodityID", 0);
		load = new ProgressDialogUtil(this);

		back.setVisibility(View.VISIBLE);
		// other预留的是分享按钮
		other.setVisibility(View.INVISIBLE);
		title.setText("商品详情");
		imagecontent.setLayoutParams(new LayoutParams(APPEnum.WIDTH.getLen(),
				(int) (APPEnum.WIDTH.getLen() / 2.6)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO 根据商品ID请求整个商品参数
		adapter = new DetailsGoodsAdapter(imageData);
		detail_vp.setAdapter(adapter);
		getGoodsPar();
	}

	/**
	 * 上午10:10:03
	 * 
	 * @author zhangyh2 TODO 根据商品ID请求整个商品参数
	 */
	private void getGoodsPar() {
		// TODO Auto-generated method stub
		if (!APP.isNetworkConnected(this)) {
			ToastApp.showToast(R.string.neterror);
		} else {
			load.setMessage(R.string.xlistview_header_hint_loading);
			load.show();
			String url = "";
			if (url != null && url.length() > 0) {
				// 进行网络请求
				JSONObject ob = new JSONObject();
				try {
					ob.put("commodityID", commodityID);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestParams params = new RequestParams(ob.toString());
				HttpClient.get(this, url, params,
						new RequestCallBack<String>() {

							@Override
							public void onSuccess(ResponseInfo<String> arg0) {
								// TODO Auto-generated method stub
								load.dismiss();
								loadGoodsDetail(arg0.result);
							}

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								// TODO Auto-generated method stub
								load.dismiss();
							}
						});
			} else {
				// 制造假数据
				List<DetailsGoodsShow> menu = new ArrayList<DetailsGoodsShow>();
				List<DetailsGoodsPro> prs = new ArrayList<DetailsGoodsPro>();
				String json = null;
				if (new Random().nextBoolean()) {
					for (int i = 0; i < IMAGES.length; i++) {
						menu.add(new DetailsGoodsShow(IMAGES[i], "123" + i));
					}
					for (int i = 0; i < 2; i++) {
						if (i == 0) {
							List<DetailsGoodsProperty> prop = new ArrayList<DetailsGoodsProperty>();
							for (int j = 0; j < IMAGES.length; j++) {
								prop.add(new DetailsGoodsProperty("色彩" + i, i));
							}
							DetailsGoodsPro pro = new DetailsGoodsPro("色彩",
									prop);
							prs.add(pro);
						} else {
							List<DetailsGoodsProperty> prop = new ArrayList<DetailsGoodsProperty>();
							for (int j = 0; j < IMAGES.length; j++) {
								prop.add(new DetailsGoodsProperty("样式" + i, i));
							}
							DetailsGoodsPro pro = new DetailsGoodsPro("样式",
									prop);
							prs.add(pro);
						}
					}
					MainMessageModel data = new MainMessageModel(100023,
							"宝贝详情", "至尊画笔", "产于华山之巅，吸食日月精华，有长生之效。", null,
							90000, 99999, 35551, null,
							new Random().nextBoolean() + "", menu, prs);

					BaseModel<MainMessageModel> base = new BaseModel<MainMessageModel>(
							200, "", data);
					json = JsonUtil.pojo2Json(base);
				} else {
					BaseModel<MainMessageModel> base = new BaseModel<MainMessageModel>(
							110, "网络数据不存在", null);
					json = JsonUtil.pojo2Json(base);
				}
				load.dismiss();
				loadGoodsDetail(json);
			}
		}
	}

	/**
	 * 上午10:15:57
	 * 
	 * @author zhangyh2 TODO 对返回参数进行解析，并进行布局
	 */
	private void loadGoodsDetail(String json) {
		// TODO Auto-generated method stub
		if (json == null) {
			return;
		}
		// 开始解析
		LogUtils.i(json);
		BaseModel<MainMessageModel> data = new BaseModelPaser<MainMessageModel>()
				.paserJson(json, new TypeToken<MainMessageModel>() {
				});
		if (data != null && data.getCode() == 200) {
			// 设置用户主界面
			title.setText(data.getData().getTitle());
			name.setText(data.getData().getName());
			description.setText(data.getData().getDescription());
			price.setText("现价:" + data.getData().getPrice() + "¥");
			original.setText("原价:" + data.getData().getOriginal() + "¥");

			int cout = data.getData().getShowinfo().size();
			for (DetailsGoodsShow i : data.getData().getShowinfo()) {
				ImageView iv = new ImageView(this);
				imageLoader.displayImage(i.getUrl(), iv, options);
				iv.setScaleType(ScaleType.FIT_XY);
				imageData.add(iv);
				if (cout > 1) {
					ImageView slid = new ImageView(this);
					LinearLayout.LayoutParams lsp = new LinearLayout.LayoutParams(
							android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
							android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
					lsp.setMargins(10, 10, 10, 20);
					slid.setLayoutParams(lsp);
					slid.setBackgroundResource(R.drawable.ic_banner_button);
					detail_tap.addView(slid);
					slide.add(slid);
				}
			}
			if (detail_tap.getChildCount() > 0) {
				detail_tap.getChildAt(0).setBackgroundResource(
						R.drawable.ic_banner_button_press);
			}
			adapter.setViewlist(imageData);

			// 设置底部菜单
			if ("true".equals(data.getData().getCollect())) {
				isCollect = true;
				love.setImageResource(R.drawable.ic_collect);
			} else {
				isCollect = false;
				love.setImageResource(R.drawable.ic_uncollect);
			}
			card.setImageResource(R.drawable.shoping_cart_select);
			shop.setText("购买商品");
		} else {
			ToastApp.showToast(data.getMsg());
		}
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
		detail_vp.setOnPageChangeListener(this);

		card.setOnClickListener(this);
		love.setOnClickListener(this);
		shop.setOnClickListener(this);
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
		case R.id.goods_card_cards:
			// 点击跳转到购物车
			LogUtils.i("goods_card_cards");
			break;
		case R.id.goods_card_collect:
			love.setClickable(false);
			if (!APP.isNetworkConnected(this)) {
				ToastApp.showToast(R.string.neterror);
				love.setClickable(true);
				return;
			}
			// 点击假如收藏
			if (isCollect) {
				// 取消收藏逻辑
				conselCollect();
			} else {
				// 添加收藏逻辑
				sureCollect();
			}
			break;
		case R.id.goods_card_buy:
			// 点击加入购物车
			LogUtils.i("goods_card_buy");
			break;

		default:
			break;
		}
	}

	/**
	 * 上午11:32:23
	 * 
	 * @author zhangyh2 TODO 添加收藏逻辑
	 */
	private void sureCollect() {
		// TODO Auto-generated method stub
		String url = "";
		if (url != null && url.length() > 0) {
			// 网络请求
			RequestParams params = new RequestParams();
			params.addBodyParameter("commodityID", commodityID + "");
			HttpClient.get(this, url, null, new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					// TODO Auto-generated method stub
					collectTrue(arg0.result);
				}

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					love.setClickable(true);
					ToastApp.showToast(R.string.neterror);
				}
			});

		} else {
			// 轮播假数据
			String json = null;
			if (new Random().nextBoolean()) {
				BaseModel<Object> model = new BaseModel<Object>(200, "添加收藏成功",
						"");
				json = JsonUtil.pojo2Json(model);
			} else {
				BaseModel<Object> model = new BaseModel<Object>(110, "添加收藏失败",
						null);
				json = JsonUtil.pojo2Json(model);
			}
			collectTrue(json);
		}
	}

	/**
	 * 上午11:43:39
	 * 
	 * @author zhangyh2 TODO 收藏成功解析
	 */
	protected void collectTrue(String result) {
		// TODO Auto-generated method stub
		love.setClickable(true);
		if (result == null) {
			return;
		}
		BaseModel<Object> model = new BaseModelPaser<Object>().paserJson(
				result, new TypeToken<Object>() {
				});
		ToastApp.showToast(model.getMsg());
		if (model.getCode() == 200) {
			// 收藏
			love.setImageResource(R.drawable.ic_collect);
			isCollect = true;
		}
	}

	/**
	 * 上午11:32:19
	 * 
	 * @author zhangyh2 TODO取消收藏逻辑
	 */
	private void conselCollect() {
		// TODO Auto-generated method stub
		String url = "";
		if (url != null && url.length() > 0) {
			// 网络请求
			RequestParams params = new RequestParams();
			params.addBodyParameter("commodityID", commodityID + "");
			HttpClient.get(this, url, null, new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					// TODO Auto-generated method stub
					uncollectTrue(arg0.result);
				}

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					ToastApp.showToast(R.string.neterror);
					love.setClickable(true);
				}
			});

		} else {
			// 轮播假数据
			String json = null;
			if (new Random().nextBoolean()) {
				BaseModel<Object> model = new BaseModel<Object>(200, "取消收藏成功",
						"");
				json = JsonUtil.pojo2Json(model);
			} else {
				BaseModel<Object> model = new BaseModel<Object>(110, "取消收藏失败",
						null);
				json = JsonUtil.pojo2Json(model);
			}
			uncollectTrue(json);
		}
	}

	/**
	 * 上午11:44:29
	 * 
	 * @author zhangyh2 TODO 取消收藏網絡請求成功。
	 */
	protected void uncollectTrue(String result) {
		// TODO Auto-generated method stub
		love.setClickable(true);
		if (result == null) {
			return;
		}
		BaseModel<Object> model = new BaseModelPaser<Object>().paserJson(
				result, new TypeToken<Object>() {
				});
		ToastApp.showToast(model.getMsg());
		if (model.getCode() == 200) {
			// 没哟收藏
			isCollect = false;
			love.setImageResource(R.drawable.ic_uncollect);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#
	 * onPageScrollStateChanged(int)
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled
	 * (int, float, int)
	 */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected
	 * (int)
	 */
	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		// 更改豆豆色彩
		for (int i = 0; i < slide.size(); i++) {
			if (i == position % slide.size()) {
				slide.get(i).setBackgroundResource(
						R.drawable.ic_banner_button_press);
			} else {
				slide.get(i).setBackgroundResource(R.drawable.ic_banner_button);
			}
		}
	}

}
