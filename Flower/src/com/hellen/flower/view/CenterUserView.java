package com.hellen.flower.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hellen.flower.R;

/**
 * @author zhangyh2 CenterUserView 下午2:10:05 TODO
 */
public class CenterUserView extends RelativeLayout {
	public ImageView user_icon;
	public TextView user_name;
	public TextView user_phone;

	/**
	 * 带有2个参数的构造方法,布局文件的时候调用
	 * 
	 * @param context
	 * @param attrs
	 */
	public CenterUserView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	/**
	 * 初始化布局文件
	 * 
	 * @param context
	 */
	private void initView(Context context) {
		// 把一个布局文件---转换成一个View,并且加载在UserInfoItem_1View
		View.inflate(context, R.layout.view_center_user, this);
		user_icon = (ImageView) this.findViewById(R.id.view_center_user_image);
		user_name = (TextView) this.findViewById(R.id.view_center_user_name);
		user_phone = (TextView) this.findViewById(R.id.view_center_user_phone);
	}

	public void setUserSex(String sex) {
		if ("女".equals(sex)) {
			Drawable img_off = getResources().getDrawable(R.drawable.ic_girl);
			// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
			img_off.setBounds(0, 0, img_off.getMinimumWidth(),
					img_off.getMinimumHeight());
			user_name.setCompoundDrawables(null, null, img_off, null); // 设置右图标
		} else {
			Drawable img_off = getResources().getDrawable(R.drawable.ic_boy);
			// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
			img_off.setBounds(0, 0, img_off.getMinimumWidth(),
					img_off.getMinimumHeight());
			user_name.setCompoundDrawables(null, null, img_off, null); // 设置右图标
		}

	}

}
