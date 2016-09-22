/**下午2:36:55
 * @author zhangyh2
 * GridAdapter.java
 * TODO
 */
package com.darly.dlclent.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.common.ImageLoaderUtil;
import com.darly.dlclent.model.MainMenuModel;

/**
 * @author zhangyh2 GridAdapter 下午2:36:55 TODO
 */
public class GridAdapter extends ParentAdapter<MainMenuModel> {


	/**
	 * 下午2:37:17
	 * 
	 * @author zhangyh2
	 */
	public GridAdapter(List<MainMenuModel> data, int resID, Context context) {
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
			Context context, MainMenuModel t) {
		// TODO Auto-generated method stub
		ViewHocker hocker = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(resID, null);
			hocker = new ViewHocker();
			hocker.iv = (ImageView) view
					.findViewById(R.id.grid_header_image);
			LayoutParams lp = new LayoutParams(APPEnum.WIDTH.getLen() / 8,
					APPEnum.WIDTH.getLen() / 8);
			hocker.iv.setLayoutParams(lp);
			hocker.tv = (TextView) view.findViewById(R.id.grid_header_text);
			view.setTag(hocker);
		} else {
			hocker = (ViewHocker) view.getTag();
		}
		ImageLoaderUtil.getInstance().loadImageNor(t.getIcon(), hocker.iv);
		hocker.tv.setText(t.getTitle());
		return view;
	}

	class ViewHocker {
		ImageView iv;
		TextView tv;
	}

}
