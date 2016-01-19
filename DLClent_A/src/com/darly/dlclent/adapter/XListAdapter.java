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
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.model.MainMessageModel;
import com.darly.dlclent.widget.roundedimage.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author zhangyh2 XListAdapter 下午2:00:32 TODO
 */
public class XListAdapter extends ParentAdapter<MainMessageModel> {
	private ImageLoader imageLoader;

	private DisplayImageOptions options;

	/**
	 * 下午2:01:36
	 * 
	 * @author zhangyh2
	 */
	public XListAdapter(List<MainMessageModel> data, int resID, Context context) {
		super(data, resID, context);
		// TODO Auto-generated constructor stub
	}

	public XListAdapter(List<MainMessageModel> data, int resID,
			Context context, ImageLoader imageLoader,
			DisplayImageOptions options) {
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
			Context context, MainMessageModel t) {
		// TODO Auto-generated method stub
		ViewHocker hocker = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(resID, null);
			hocker = new ViewHocker();
			hocker.title = (LinearLayout) view.findViewById(R.id.xlist_title);
			hocker.title_name = (TextView) view
					.findViewById(R.id.xlist_title_name);

			hocker.content = (LinearLayout) view
					.findViewById(R.id.xlist_content);
			hocker.iv = (RoundedImageView) view
					.findViewById(R.id.xlist_round_image);
			LayoutParams lp = new LayoutParams(APPEnum.WIDTH.getLen() / 6,
					APPEnum.WIDTH.getLen() / 6);
			hocker.iv.setLayoutParams(lp);
			hocker.name = (TextView) view.findViewById(R.id.xlist_name);
			hocker.descrip = (TextView) view.findViewById(R.id.xlist_descrip);
			hocker.price = (TextView) view.findViewById(R.id.xlist_price);
			hocker.original = (TextView) view.findViewById(R.id.xlist_original);
			view.setTag(hocker);
		} else {
			hocker = (ViewHocker) view.getTag();
		}
		if ("标题".equals(t.getType())) {
			hocker.title.setVisibility(View.VISIBLE);
			hocker.content.setVisibility(View.GONE);
			hocker.title_name.setText(t.getTitle());
		} else {
			hocker.title.setVisibility(View.GONE);
			hocker.content.setVisibility(View.VISIBLE);
			imageLoader.displayImage(t.getUrl(), hocker.iv, options);
			hocker.name.setText(t.getName());
			hocker.descrip.setText(t.getDescription());
			hocker.price.setText(t.getPrice() + "¥");
			hocker.original.setText(t.getOriginal() + "¥");
		}
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.BaseAdapter#isEnabled(int)
	 */
	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		if ("标题".equals(getItem(position).getType())) {
			return false;
		}
		return super.isEnabled(position);
	}

	class ViewHocker {
		LinearLayout title;

		TextView title_name;

		LinearLayout content;

		RoundedImageView iv;

		TextView name;

		TextView descrip;

		TextView price;

		TextView original;

		TextView tv;
	}

}
