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
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.common.ImageLoaderUtil;
import com.darly.dlclent.model.ClientCompHomePage;

/**
 * @author zhangyh2 GridAdapter 下午2:36:55 TODO
 */
public class CompListAdapter extends ParentAdapter<ClientCompHomePage> {


	/**
	 * 下午2:37:17
	 * 
	 * @author zhangyh2
	 */
	public CompListAdapter(List<ClientCompHomePage> data, int resID,
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
			hocker.iv = (ImageView) view.findViewById(R.id.comp_image);
			LayoutParams lp = new LayoutParams(APPEnum.WIDTH.getLen() / 8,
					APPEnum.WIDTH.getLen() / 8);
			hocker.iv.setLayoutParams(lp);
			hocker.title = (TextView) view.findViewById(R.id.comp_title);
			hocker.sectitle = (TextView) view.findViewById(R.id.comp_sec_title);
			hocker.free = (TextView) view.findViewById(R.id.comp_isfree);
			hocker.content = (LinearLayout) view
					.findViewById(R.id.comp_content);
			LayoutParams lps = new LayoutParams(APPEnum.WIDTH.getLen(),
					APPEnum.WIDTH.getLen() / 4);
			hocker.content.setPadding(APPEnum.WIDTH.getLen() / 16, 0,
					APPEnum.WIDTH.getLen() / 16, 0);
			hocker.content.setLayoutParams(lps);
			view.setTag(hocker);
		} else {
			hocker = (ViewHocker) view.getTag();
		}

		ImageLoaderUtil.getInstance().loadImageNor(t.getCompIcon(), hocker.iv);
		hocker.title.setText(t.getCompFirstname());
		hocker.sectitle.setText(t.getCompSecname());
		if (t.getCompDescription() == 0) {
			hocker.free.setVisibility(View.VISIBLE);
		} else {
			hocker.free.setVisibility(View.GONE);
		}
		return view;
	}

	class ViewHocker {
		ImageView iv;
		TextView title;
		TextView sectitle;
		TextView free;
		LinearLayout content;
	}

}
