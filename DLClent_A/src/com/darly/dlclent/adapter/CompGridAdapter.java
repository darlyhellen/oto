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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.common.ImageLoaderUtil;
import com.darly.dlclent.model.ClientCompHomePage;

/**
 * @author zhangyh2 GridAdapter 下午2:36:55 TODO
 */
public class CompGridAdapter extends ParentAdapter<ClientCompHomePage> {


	/**
	 * 下午2:37:17
	 * 
	 * @author zhangyh2
	 */
	public CompGridAdapter(List<ClientCompHomePage> data, int resID,
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
			Context context, ClientCompHomePage t) {
		// TODO Auto-generated method stub
		ViewHocker hocker = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(resID, null);
			hocker = new ViewHocker();
			hocker.line = (ImageView) view.findViewById(R.id.comp_line);
			hocker.iv = (ImageView) view.findViewById(R.id.comp_header_image);
			LayoutParams lp = new LayoutParams(APPEnum.WIDTH.getLen() / 10,
					APPEnum.WIDTH.getLen() / 10);
			hocker.iv.setLayoutParams(lp);
			hocker.tv = (TextView) view.findViewById(R.id.comp_header_text);
			hocker.content = (RelativeLayout) view
					.findViewById(R.id.comp_content);
			LayoutParams lps = new LayoutParams(APPEnum.WIDTH.getLen() / 2,
					APPEnum.WIDTH.getLen() / 4);
			hocker.content.setLayoutParams(lps);
			view.setTag(hocker);
		} else {
			hocker = (ViewHocker) view.getTag();
		}

		if (position % 2 == 0) {
			hocker.line.setVisibility(View.VISIBLE);
		} else {
			hocker.line.setVisibility(View.GONE);
		}
		ImageLoaderUtil.getInstance().loadImageNor(t.getCompIcon(), hocker.iv);
		hocker.tv.setText(t.getCompFirstname());
		return view;
	}

	class ViewHocker {
		ImageView iv;
		TextView tv;
		RelativeLayout content;
		ImageView line;
	}

}
