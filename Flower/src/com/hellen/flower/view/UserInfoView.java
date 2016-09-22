/**上午10:19:39
 * @author zhangyh2
 * UserInfoView.java
 * TODO
 */
package com.hellen.flower.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hellen.base.ConsMVP;
import com.hellen.common.ImageLoaderUtil;
import com.hellen.flower.R;

/**
 * @author zhangyh2 UserInfoView 上午10:19:39 TODO userinfo to showing view
 */
public class UserInfoView extends LinearLayout {

	private TextView label;
	private TextView show;

	private ImageView icon;

	/**
	 * 上午10:21:31
	 * 
	 * @author zhangyh2
	 */
	public UserInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	/**
	 * 上午10:22:43
	 * 
	 * @author zhangyh2 TODO
	 */
	private void initView(Context context) {
		// TODO Auto-generated method stub
		// 把一个布局文件---转换成一个View,并且加载在UserInfoItem_1View
		View.inflate(context, R.layout.view_user_info, this);
		label = (TextView) this.findViewById(R.id.view_user_info_label);
		show = (TextView) this.findViewById(R.id.view_user_info_show);
		icon = (ImageView) this.findViewById(R.id.view_user_info_icon);
	}

	public void showUserInfo(String slabel, String sShow, String imageUrl) {
		label.setText(slabel);
		if (TextUtils.isEmpty(imageUrl)) {
			icon.setVisibility(View.GONE);
			show.setVisibility(View.VISIBLE);
			show.setText(sShow);
		} else {
			icon.setVisibility(View.VISIBLE);
			show.setVisibility(View.GONE);
			icon.setLayoutParams(new LayoutParams(ConsMVP.WIDTH.getLen() / 6,
					ConsMVP.WIDTH.getLen() / 6));
			ImageLoaderUtil.getInstance().loadImage(imageUrl, icon);
		}
	}

}
