/**
 * 下午3:33:23
 * @author zhangyh2
 * $
 * FragmentCenterAdapter.java
 * TODO
 */
package com.darly.dlclent.adapter;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.model.SecMenuModel;

/**
 * @author zhangyh2 FragmentCenterAdapter $ 下午3:33:23 TODO
 */
public class FragmentCenterSecAdapter extends ParentAdapter<SecMenuModel> {

	/**
	 * @param data
	 * @param resID
	 * @param context
	 *            下午3:33:35
	 * @author zhangyh2 FragmentCenterAdapter.java TODO
	 */
	public FragmentCenterSecAdapter(List<SecMenuModel> data, int resID,
			Context context) {
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
			Context context, SecMenuModel t) {
		// TODO Auto-generated method stub
		ViewHocker hocker = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(resID, null);
			hocker = new ViewHocker();
			hocker.name = (TextView) view
					.findViewById(R.id.center_item_sec_name);
			hocker.title = (TextView) view
					.findViewById(R.id.center_item_sec_title);
			hocker.icon = (ImageView) view
					.findViewById(R.id.center_item_sec_icon);
			view.setTag(hocker);
		} else {
			hocker = (ViewHocker) view.getTag();
		}

		if (position == 0||"exit".equals(t.getValue())) {
			hocker.name.setText(t.getName());
			hocker.name.setGravity(Gravity.CENTER);
			hocker.title.setVisibility(View.GONE);
			hocker.icon.setVisibility(View.INVISIBLE);
		} else {
			hocker.name.setText(t.getName());
			hocker.title.setText(t.getValue());
			hocker.name.setGravity(Gravity.LEFT);
			hocker.title.setVisibility(View.VISIBLE);
			hocker.icon.setVisibility(View.VISIBLE);
		}

		return view;
	}

	class ViewHocker {
		public TextView name;
		public TextView title;
		public ImageView icon;
	}
}
