/**下午2:00:32
 * @author zhangyh2
 * XListAdapter.java
 * TODO
 */
package com.darly.dlclent.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.common.ImageLoaderUtil;
import com.darly.dlclent.model.ActThemeModel;
import com.darly.dlclent.widget.roundedimage.RoundedImageView;

/**
 * @author zhangyh2 XListAdapter 下午2:00:32 TODO 第二个页面，活动页面的适配器。只有一种样式
 */
public class ListActAdapter extends ParentAdapter<ActThemeModel> {

	/**
	 * 下午2:01:36
	 * 
	 * @author zhangyh2
	 */
	public ListActAdapter(List<ActThemeModel> data, int resID, Context context) {
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
			Context context, ActThemeModel t) {
		// TODO Auto-generated method stub
		ViewHocker hocker = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(resID, null);
			hocker = new ViewHocker();
			hocker.iv = (RoundedImageView) view
					.findViewById(R.id.act_list_round_image);
			LayoutParams lp = new LayoutParams(
					android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					APPEnum.WIDTH.getLen() / 3);
			hocker.iv.setLayoutParams(lp);
			hocker.descrip = (TextView) view
					.findViewById(R.id.act_list_descrip);
			view.setTag(hocker);
		} else {
			hocker = (ViewHocker) view.getTag();
		}
		ImageLoaderUtil.getInstance().loadImageNor(t.getImagePath(), hocker.iv);
		hocker.descrip.setText(t.getDescription());

		return view;
	}

	class ViewHocker {

		RoundedImageView iv;

		TextView descrip;
	}

}
