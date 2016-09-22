/**上午11:09:07
 * @author zhangyh2
 * WholeListAdapter.java
 * TODO
 */
package com.hellen.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.hellen.base.ConsMVP;
import com.hellen.bean.Main_Flower;
import com.hellen.common.ImageLoaderUtil;
import com.hellen.common.LogApp;
import com.hellen.flower.R;

/**
 * @author zhangyh2 WholeListAdapter 上午11:09:07 TODO
 */
public class WholeListAdapterM extends ParentAdapter<Main_Flower> {
	private String TAG = getClass().getSimpleName();

	/**
	 * 上午11:09:37
	 * 
	 * @author zhangyh2
	 */
	public WholeListAdapterM(List<Main_Flower> data, int resID, Context context) {
		super(data, resID, context);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.adapter.ParentAdapter#HockView(int, android.view.View,
	 * android.view.ViewGroup, int, android.content.Context, java.lang.Object)
	 */
	@Override
	public View HockView(int position, View view, ViewGroup parent, int resID,
			Context context, Main_Flower t) {
		// TODO Auto-generated method stub
		ViewHocker hocker = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(resID, null);
			hocker = new ViewHocker();
			hocker.iv = (ImageView) view
					.findViewById(R.id.act_list_round_image);
			LayoutParams lp = new LayoutParams(ConsMVP.WIDTH.getLen(),
					(int) (ConsMVP.WIDTH.getLen() / 2.6));
			hocker.iv.setLayoutParams(lp);
			hocker.descrip = (TextView) view
					.findViewById(R.id.act_list_descrip);
			view.setTag(hocker);
		} else {
			hocker = (ViewHocker) view.getTag();
		}
		ImageLoaderUtil.getInstance().loadImageNor(t.getIcon(), hocker.iv);
		hocker.descrip.setText(t.getName());
		LogApp.i(TAG, t.getIcon() + "" + t.getName());
		return view;
	}

	class ViewHocker {

		ImageView iv;

		TextView descrip;
	}
}
