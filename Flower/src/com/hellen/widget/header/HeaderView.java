/**下午4:11:51
 * @author zhangyh2
 * HeaderView.java
 * TODO
 */
package com.hellen.widget.header;

import com.hellen.flower.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author zhangyh2 HeaderView 下午4:11:51 TODO
 */
public class HeaderView extends RelativeLayout {

	private ImageView back;

	private TextView title;

	private RelativeLayout bg;

	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public HeaderView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * 下午4:14:06
	 * 
	 * @author zhangyh2 TODO
	 */
	private void init(Context context) {
		// TODO Auto-generated method stub
		View.inflate(context, R.layout.view_header, this);
		back = (ImageView) this.findViewById(R.id.view_header_iv);
		title = (TextView) this.findViewById(R.id.view_header_tv);
		bg = (RelativeLayout) this.findViewById(R.id.view_header_bg);
	}

	/**
	 * 下午4:20:31
	 * 
	 * @author zhangyh2 TODO 设置标题名
	 */
	public void onSetTitle(String name) {
		title.setText(name + "");
	}

	public void onSetTitle(int id) {
		title.setText(id);
	}

	/**
	 * 下午4:19:20
	 * 
	 * @author zhangyh2 TODO <a>disBack</a>值为true 则进行隐藏
	 */
	public void onDisBack(boolean disBack) {
		if (disBack) {
			back.setVisibility(View.GONE);
		} else {
			back.setVisibility(View.VISIBLE);
		}
	}

	public ImageView getBack() {
		return back;
	}

	public TextView getTitle() {
		return title;
	}

	public RelativeLayout getBg() {
		return bg;
	}

}
