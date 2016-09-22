package com.hellen.widget.circlemenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.hellen.base.ConsMVP;
import com.hellen.flower.R;
import com.hellen.widget.circlemenu.CircleMenuLayout.OnMenuItemClickListener;

/**
 * @author zhangyh2 CirclePouple 下午4:42:28 TODO
 */
public class CirclePouple extends PopupWindow implements
		OnMenuItemClickListener {

	private CircleMenuLayout circle;

	private String[] mItemTexts = new String[] { "安全中心 ", "特色服务", "投资理财",
			"转账汇款", "我的账户", "信用卡" };
	private int[] mItemImgs = new int[] { R.drawable.home_mbank_1_normal,
			R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
			R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal,
			R.drawable.home_mbank_6_normal };

	private PoupleClickListener pouple;

	/**
	 * 下午5:11:19
	 * 
	 * @author zhangyh2 默认的POP
	 */
	public CirclePouple(Context context) {
		super(context);
		initView(context, 0);
	}

	/**
	 * 下午5:11:00
	 * 
	 * @author zhangyh2 修改菜单的pop
	 */
	public CirclePouple(String[] mItemTexts, int[] mItemImgs, Context context,
			int resid) {
		super();
		this.mItemTexts = mItemTexts;
		this.mItemImgs = mItemImgs;
		initView(context, resid);
	}

	@SuppressWarnings("deprecation")
	private void initView(Context context, int resid) {
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.circle_pouple, null);
		circle = (CircleMenuLayout) rootView
				.findViewById(R.id.id_pouple_menulayout);
		circle.setCenterImageRes(resid);
		circle.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
		circle.setOnMenuItemClickListener(this);
		setContentView(rootView);
		setWidth(ConsMVP.WIDTH.getLen() / 2);
		setHeight(ConsMVP.WIDTH.getLen() / 2);
		setFocusable(true);
		setBackgroundDrawable(new BitmapDrawable());
		setTouchable(true);
		showAtLocation(((Activity) context).getWindow().getDecorView(),
				Gravity.CENTER, 0, 0);
		this.setOutsideTouchable(true);
	}

	/**
	 * @param pouple
	 *            the pouple to set
	 */
	public void setPouple(PoupleClickListener pouple) {
		this.pouple = pouple;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hellen.widget.circlemenu.CircleMenuLayout.OnMenuItemClickListener
	 * #itemClick(android.view.View, int)
	 */
	@Override
	public void itemClick(View view, int pos, String title) {
		// TODO Auto-generated method stub
		pouple.itemClick(view, pos, title);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hellen.widget.circlemenu.CircleMenuLayout.OnMenuItemClickListener
	 * #itemCenterClick(android.view.View)
	 */
	@Override
	public void itemCenterClick(View view) {
		// TODO Auto-generated method stub
		pouple.itemCenterClick(view);

	}

	public interface PoupleClickListener {
		void itemClick(View view, int pos, String title);

		void itemCenterClick(View view);
	}
}
