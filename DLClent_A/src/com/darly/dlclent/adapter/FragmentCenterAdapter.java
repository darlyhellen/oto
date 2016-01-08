/**
 * 下午3:33:23
 * @author zhangyh2
 * $
 * FragmentCenterAdapter.java
 * TODO
 */
package com.darly.dlclent.adapter;

import java.util.List;

import com.darly.dlclent.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author zhangyh2 FragmentCenterAdapter $ 下午3:33:23 TODO
 */
public class FragmentCenterAdapter extends ParentAdapter<String> {

	/**
	 * @param data
	 * @param resID
	 * @param context
	 *            下午3:33:35
	 * @author zhangyh2 FragmentCenterAdapter.java TODO
	 */
	public FragmentCenterAdapter(List<String> data, int resID, Context context) {
		super(data, resID, context);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.adapter.ParentAdapter#HockView(int,
	 * android.view.View, android.view.ViewGroup, int, android.content.Context,
	 * java.lang.Object)
	 */
	@Override
	public View HockView(int position, View view, ViewGroup parent, int resID,
			Context context, String t) {
		// TODO Auto-generated method stub
		ViewHocker hocker = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(resID, null);
			hocker = new ViewHocker();
			hocker.icon = (ImageView) view.findViewById(R.id.center_item_icon);
			hocker.title = (TextView) view.findViewById(R.id.center_item_title);
			view.setTag(hocker);
		} else {
			hocker = (ViewHocker) view.getTag();
		}

		hocker.icon.setImageResource(R.drawable.ic_back);
		hocker.title.setText(t);
		return view;
	}

	class ViewHocker {
		public ImageView icon;
		public TextView title;
	}

}
