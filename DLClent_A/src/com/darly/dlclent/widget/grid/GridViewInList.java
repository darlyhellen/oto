package com.darly.dlclent.widget.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @ClassName: ListViewItem_GridView
 * @Description: TODO(放置与ListViewItem中的GridView可以完美展示)
 * @author 张宇辉 zhangyuhui@octmami.com
 * @date 2015年2月5日 下午4:47:58
 *
 */
public class GridViewInList extends GridView {

	public GridViewInList(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public GridViewInList(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public GridViewInList(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, height);
	}
}