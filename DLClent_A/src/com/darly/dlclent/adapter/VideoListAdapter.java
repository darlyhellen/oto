/**上午11:30:16
 * @author zhangyh2
 * AddressAdapter.java
 * TODO
 */
package com.darly.dlclent.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.common.ImageLoaderUtil;
import com.darly.dlclent.model.ClientVideo;

/**
 * @author zhangyh2 AddressAdapter 上午11:30:16 TODO
 */
public class VideoListAdapter extends ParentAdapter<ClientVideo> {

	/**
	 * 上午11:30:44
	 * 
	 * @author zhangyh2
	 */
	public VideoListAdapter(List<ClientVideo> data, int resID, Context context) {
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
			Context context, ClientVideo t) {
		// TODO Auto-generated method stub
		ViewHocker hocker = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(resID, null);
			hocker = new ViewHocker();
			hocker.url = (ImageView) view.findViewById(R.id.item_video_iv);
			hocker.url.setLayoutParams(new LayoutParams(APPEnum.WIDTH.getLen(),
					(int) (APPEnum.WIDTH.getLen() / 2.6)));
			hocker.name = (TextView) view.findViewById(R.id.item_video_name);
			hocker.desc = (TextView) view.findViewById(R.id.item_video_desc);

			view.setTag(hocker);
		} else {
			hocker = (ViewHocker) view.getTag();
		}

		hocker.name.setText(t.getVideoName());
		hocker.desc.setText(t.getVideoDescripe());
		ImageLoaderUtil.getInstance().loadImageNor(t.getVideoImage(),
				hocker.url);
		return view;
	}

	class ViewHocker {
		ImageView url;
		TextView name;
		TextView desc;
	}

}
