package com.darly.dlclent.widget.carousel;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.common.ImageLoaderUtil;
import com.darly.dlclent.model.MainCarouselModel;

/**
 * @author zhangyuhui 此类生成与20150629，主要功能为，首页轮播效果图。当然为区分首页轮播效果的图片张数。
 *         为1张图片时，只是存放一张ImageView。不进行轮播。
 *         为2张图片时，由于轮播效果为左右，2张无法满足，故而将两张的图片数据，变更为4张的图片数据。从而满足其状态。
 *         图片为空或没有图片时。不进行加载或加载空。 图片为多张时。正常加载。
 */
public class Carousel<T> implements OnPageChangeListener, OnClickListener {

	public interface ClickCarouselistener {

		public void clickCarousel(MainCarouselModel url);

	}

	private ClickCarouselistener clickCarouselistener;

	private Context context;
	public static ViewPager pager;
	private ImageView one;
	private LinearLayout layout;

	public View view;

	private List<MainCarouselModel> data;

	public static int contents;

	private ImageHandler<T> imagehandler;

	private ArrayList<ImageView> viewlist = new ArrayList<ImageView>();
	private ArrayList<ImageView> slide = new ArrayList<ImageView>();

	private LayoutParams lp;
	/**
	 * 图片给定尺寸的宽高比例。
	 */
	private static final double ASPECTRATIO = 2.66;

	public Carousel(Context context, List<MainCarouselModel> data,
			ImageHandler<T> imagehandler) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.data = data;
		this.imagehandler = imagehandler;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		// 获取到的XML轮播页面布局。
		view = LayoutInflater.from(context).inflate(
				R.layout.carousel_viewpager, null);
		lp = new RelativeLayout.LayoutParams(APPEnum.WIDTH.getLen(),
				(int) (APPEnum.WIDTH.getLen() / ASPECTRATIO));
		pager = (ViewPager) view.findViewById(R.id.carousel_viewpager);
		pager.setLayoutParams(lp);
		one = (ImageView) view.findViewById(R.id.carousel_onlyone);
		one.setLayoutParams(lp);
		one.setScaleType(ScaleType.FIT_XY);
		one.setClickable(true);
		layout = (LinearLayout) view.findViewById(R.id.carousel_slide);
		setData();
	}

	private void setData() {
		// TODO Auto-generated method stub
		// 设置轮播个数：
		int lent = 0;
		if (data == null) {
			pager.setVisibility(View.GONE);
			layout.setVisibility(View.GONE);
			one.setVisibility(View.VISIBLE);
			ImageLoaderUtil.getInstance().loadImageNor(null, one);
			return;
		} else {
			lent = data.size();
			contents = lent * 50;
		}

		if (lent <= 1) {
			// 当轮播个数是一个，就不进行轮播。底下的豆豆也不显示。
			pager.setVisibility(View.GONE);
			layout.setVisibility(View.GONE);
			one.setVisibility(View.VISIBLE);
			if (lent == 1) {
				ImageLoaderUtil.getInstance().loadImageNor(
						data.get(lent - 1).getIcon(), one);
				one.setOnClickListener(this);
			} else {
				ImageLoaderUtil.getInstance().loadImageNor(null, one);
			}

		} else if (lent == 2) {
			// 当轮播个数是两个，左右不能连接，所以要将其进行左右连接。
			pager.setVisibility(View.VISIBLE);
			layout.setVisibility(View.VISIBLE);
			one.setVisibility(View.GONE);
			// 当两个图片时，循环加载一次。
			ArrayList<MainCarouselModel> datas = new ArrayList<MainCarouselModel>();
			for (int i = 0, l = data.size(); i < l; i++) {
				for (MainCarouselModel home : data) {
					datas.add(home);
				}
			}
			setCarou(datas);
			pager.setAdapter(new ImageAdapter(viewlist));
			pager.setOnPageChangeListener(this);

			imagehandler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE,
					ImageHandler.MSG_DELAY);
			pager.setCurrentItem(contents);// 默认在中间，使用户看不到边界
		} else {
			// 当轮播个数两个以上，则正常进行轮播。
			pager.setVisibility(View.VISIBLE);
			layout.setVisibility(View.VISIBLE);
			one.setVisibility(View.GONE);
			setCarou(data);
			pager.setAdapter(new ImageAdapter(viewlist));
			pager.setOnPageChangeListener(this);

			imagehandler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE,
					ImageHandler.MSG_DELAY);
			pager.setCurrentItem(contents);// 默认在中间，使用户看不到边界

		}

	}

	private void setCarou(List<MainCarouselModel> data) {
		// TODO Auto-generated method stub

		View homeView = LayoutInflater.from(context).inflate(
				R.layout.item_header_viewpager, null);
		LinearLayout linearLayout = (LinearLayout) homeView
				.findViewById(R.id.viewpager_item_image);
		pager.addView(linearLayout);
		for (MainCarouselModel url : data) {
			ImageView iv = new ImageView(context);
			iv.setScaleType(ScaleType.FIT_XY);
			iv.setClickable(true);
			ImageLoaderUtil.getInstance().loadImageNor(url.getIcon(), iv);
			linearLayout.addView(iv);
			viewlist.add(iv);
			ImageView slid = new ImageView(context);
			LinearLayout.LayoutParams lsp = new LinearLayout.LayoutParams(
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			lsp.setMargins(10, 10, 10, 20);
			slid.setLayoutParams(lsp);
			slid.setBackgroundResource(R.drawable.ic_banner_button);
			layout.addView(slid);
			slide.add(slid);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.carousel_onlyone:
			// 一个轮播的点击事件。
			clickCarouselistener.clickCarousel(data.get(0));
			break;

		default:
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

		// 当第一个页面的时候显示选中效果。
		if (slide.size() > 1) {
			switch (arg0) {

			case ViewPager.SCROLL_STATE_DRAGGING:
				imagehandler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
				break;
			case ViewPager.SCROLL_STATE_IDLE:
				imagehandler.sendEmptyMessageDelayed(
						ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
				break;

			default:
				break;
			}
		}

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	private int a = 0;

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		if (slide.size() > 1) {
			imagehandler.sendMessage(Message.obtain(imagehandler,
					ImageHandler.MSG_PAGE_CHANGED, position, 0));
			if (data.size() == 2) {
				a = position % 2;
			} else {
				a = position % slide.size();
			}
			// 更改豆豆色彩
			for (int i = 0; i < slide.size(); i++) {
				if (data.size() == 2) {
					if (i > 1) {
						slide.get(i).setVisibility(View.GONE);
					} else {
						slide.get(i).setVisibility(View.VISIBLE);
					}
					if (i == a) {
						slide.get(i).setBackgroundResource(
								R.drawable.ic_banner_button_press);
					} else {
						slide.get(i).setBackgroundResource(
								R.drawable.ic_banner_button);
					}
				} else {
					if (i == a) {
						slide.get(i).setBackgroundResource(
								R.drawable.ic_banner_button_press);
					} else {
						slide.get(i).setBackgroundResource(
								R.drawable.ic_banner_button);
					}
				}
			}
		} else {
			a = 0;
		}
		final ImageView image = viewlist.get(position % slide.size());

		image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 点击事件。
				clickCarouselistener.clickCarousel(data.get(a));
			}
		});
	}

	public void setClickCarouselistener(
			ClickCarouselistener clickCarouselistener) {
		this.clickCarouselistener = clickCarouselistener;
	}

}
