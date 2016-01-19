/**上午11:31:08
 * @author zhangyh2
 * DetailsGoodsAdapter.java
 * TODO
 */
package com.darly.dlclent.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

/**
 * @author zhangyh2 DetailsGoodsAdapter 上午11:31:08 TODO
 */
public class DetailsGoodsAdapter extends PagerAdapter {

	private List<ImageView> viewlist;

	public DetailsGoodsAdapter(List<ImageView> viewlist) {
		this.viewlist = viewlist;
	}

	@Override
	public int getCount() {
		// 设置成最大，使用户看不到边界
		return viewlist == null ? 0 : viewlist.size();
	}

	/**
	 * @param viewlist
	 *            the viewlist to set
	 */
	public void setViewlist(List<ImageView> viewlist) {
		this.viewlist = viewlist;
		notifyDataSetChanged();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// Warning：不要在这里调用removeView
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// 对ViewPager页号求模取出View列表中要显示的项
		if (viewlist == null) {
			return null;
		}
		position %= viewlist.size();
		if (position < 0) {
			position = viewlist.size() + position;
		}
		ImageView view = viewlist.get(position);
		// 如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
		ViewParent vp = view.getParent();
		if (vp != null) {
			ViewGroup parent = (ViewGroup) vp;
			parent.removeView(view);
		}
		container.addView(view);
		return view;
	}
}
