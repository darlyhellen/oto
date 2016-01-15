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
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.model.MainMenuModel;
import com.darly.dlclent.widget.roundedimage.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author zhangyh2 GridAdapter 下午2:36:55 TODO
 */
public class GridAdapter extends ParentAdapter<MainMenuModel> {

	private ImageLoader imageLoader;

	private DisplayImageOptions options;

	/**
	 * 下午2:37:17
	 * 
	 * @author zhangyh2
	 */
	public GridAdapter(List<MainMenuModel> data, int resID, Context context) {
		super(data, resID, context);
		// TODO Auto-generated constructor stub
	}

	public GridAdapter(List<MainMenuModel> data, int resID, Context context,
			ImageLoader imageLoader, DisplayImageOptions options) {
		super(data, resID, context);
		this.imageLoader = imageLoader;
		this.options = options;
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
			hocker.iv = (RoundedImageView) view
					.findViewById(R.id.grid_header_image);
			LayoutParams lp = new LayoutParams(APPEnum.WIDTH.getLen() / 8,
					APPEnum.WIDTH.getLen() / 8);
			hocker.iv.setLayoutParams(lp);
			hocker.tv = (TextView) view.findViewById(R.id.grid_header_text);
			view.setTag(hocker);
		} else {
			hocker = (ViewHocker) view.getTag();
		}

		imageLoader.displayImage(t.getIcon(), hocker.iv, options);
		hocker.tv.setText(t.getTitle());
		return view;
	}

	class ViewHocker {
		RoundedImageView iv;

		TextView tv;
	}

}
