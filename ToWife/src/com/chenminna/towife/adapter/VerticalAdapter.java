package com.chenminna.towife.adapter;

import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenminna.towife.R;
import com.chenminna.towife.bean.LoveData;
import com.chenminna.towife.widget.pathanim.FllowerAnimation;
import com.chenminna.towife.widget.vpager.PagerAdapter;
import com.lidroid.xutils.util.LogUtils;

public class VerticalAdapter extends PagerAdapter {

	private Random mRandom = new Random();

	private List<LoveData> data;

	public VerticalAdapter(List<LoveData> data) {
		this.data = data;
	}

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LogUtils.i("instantiateItem" + position);
		LoveData love = data.get(position);
		switch (love.getLogo()) {
		case 0:
			// 正常展示效果图。
			View v = showBackGroud(container.getContext(), love);
			container.addView(v);
			return v;
		case 1:

			return null;
		case 2:

			return null;

		default:
			TextView tv = new TextView(container.getContext());
			tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			tv.setGravity(Gravity.CENTER);
			tv.setTextSize(30);
			tv.setBackgroundColor(Color.rgb(mRandom.nextInt(255),
					mRandom.nextInt(255), mRandom.nextInt(255)));
			tv.setTextColor(Color.WHITE);
			tv.setText("Pager: " + position);
			container.addView(tv);
			return tv;
		}
	}

	/**
	 * 下午2:23:33
	 * 
	 * @author zhangyh2 TODO 展示页面
	 * @param context
	 * @param container
	 */
	private View showBackGroud(Context context, LoveData love) {
		// TODO Auto-generated method stub
		View v = LayoutInflater.from(context).inflate(
				R.layout.action_show_view, null);
		v.setBackgroundResource(R.drawable.ic_guide_one_bg);

		TextView title = (TextView) v.findViewById(R.id.action_show_title);
		final ImageView icon = (ImageView) v
				.findViewById(R.id.action_show_icon);
		TextView desc = (TextView) v.findViewById(R.id.action_show_desc);
		RelativeLayout rel = (RelativeLayout) v
				.findViewById(R.id.action_show_rel);
		if (TextUtils.isEmpty(love.getTitle())) {
			title.setVisibility(View.GONE);
		} else {
			title.setVisibility(View.VISIBLE);
			title.setText(love.getTitle());
			// 添加动画效果
			AnimationSet set = new AnimationSet(true);
			ScaleAnimation scale = new ScaleAnimation(1f, 2f, 1f, 2f,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			scale.setFillAfter(true);
			scale.setDuration(3000);
			set.addAnimation(scale);
			TranslateAnimation trans = new TranslateAnimation(0,
					Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF);
			trans.setFillAfter(true);
			trans.setDuration(3000);
			set.addAnimation(trans);
			title.setAnimation(set);
		}
		if (TextUtils.isEmpty(love.getDesc())) {
			desc.setVisibility(View.GONE);
		} else {
			desc.setVisibility(View.VISIBLE);
			desc.setText(love.getDesc());
			// 添加动画效果
			AnimationSet set = new AnimationSet(true);
			ScaleAnimation scale = new ScaleAnimation(1f, 2f, 1f, 2f,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			scale.setFillAfter(true);
			scale.setDuration(3000);
			set.addAnimation(scale);
			TranslateAnimation trans = new TranslateAnimation(0,
					Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF);
			trans.setFillAfter(true);
			trans.setDuration(3000);
			set.addAnimation(trans);
			desc.setAnimation(set);

		}
		if (TextUtils.isEmpty(love.getAction())) {
			icon.setVisibility(View.GONE);
		} else {
			icon.setVisibility(View.GONE);
			// 添加动画效果
			// TranslateAnimation trans = new TranslateAnimation(0,
			// APPEnum.WIDTH.getLen(), 0, 0);
			//
			// trans.setInterpolator(new CycleInterpolator(1.5f));
			// trans.setFillAfter(true);
			// trans.setDuration(3000);
			// icon.setAnimation(trans);

			FllowerAnimation fllowerAnimation = new FllowerAnimation(context);
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			fllowerAnimation.setLayoutParams(params);
			rel.addView(fllowerAnimation);
			fllowerAnimation.startAnimation();
		}
		return v;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		LogUtils.i("destroyItem" + position);
		container.removeView((View) object);
	}

}
