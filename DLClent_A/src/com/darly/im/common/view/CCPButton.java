/*
 *  Copyright (c) 2013 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.cloopen.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */package com.darly.im.common.view;

import android.content.Context;

import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;

/**
 * 
 * 
* <p>Title: CCPButton.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: http://www.cloopen.com</p>
* @author Jorstin Chan
* @date 2013-12-2
* @version 3.6
 */
public class CCPButton extends CCPImageButton {

	public CCPButton(Context context) {
		this(context , null , 0);
	}

	public CCPButton(Context context, AttributeSet attrs) {
		this(context, attrs , 0);
	}

	public CCPButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		init();
		
	}

	private void init() {
		FrameLayout.LayoutParams fLayoutParams = new FrameLayout.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT
				,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		fLayoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
		
		mImageView.setLayoutParams(fLayoutParams);
	}
	

	/**
	 * 
	 * @param resId
	 */
	public final void setCCPButtonBackground(int resId) {
		if(resId < 0) {
			return;
		}
		setBackgroundResource(resId);
	}
	
	/**
	 * 
	 * @param resId
	 */
	public final void setCCPButtonImageResource(int resId) {
		Drawable drawable = getResources().getDrawable(resId);
		setCCPButtonImageDrawable(drawable);
	}
	
	public final void setCCPButtonImageDrawable(Drawable drawable) {
		if(mImageView != null) {
			mImageView.setImageDrawable(drawable);
		}
	}
	
}
