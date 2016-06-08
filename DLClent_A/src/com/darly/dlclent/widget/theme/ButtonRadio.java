/**下午2:04:54
 * @author zhangyh2
 * ButtonRadio.java
 * TODO
 */
package com.darly.dlclent.widget.theme;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.darly.dlclent.R;

/**
 * @author zhangyh2 ButtonRadio 下午2:04:54 TODO
 */
public class ButtonRadio extends LinearLayout {

	private ImageView iv;
	private TextView tv;

	public ButtonRadio(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ButtonRadio(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ButtonRadio(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * 下午2:05:42
	 * 
	 * @author zhangyh2 TODO
	 * @param context
	 */
	private void init(Context context) {
		// TODO Auto-generated method stub
		View.inflate(context, R.layout.view_button_radio, this);
		iv = (ImageView) findViewById(R.id.button_radio_iv);
		tv = (TextView) findViewById(R.id.button_radio_tv);
	}

	public ImageView getIv() {
		return iv;
	}

	public TextView getTv() {
		return tv;
	}

	public void setInfo(int text, int image) {
		iv.setImageResource(image);
		tv.setText(text);
	}
}
