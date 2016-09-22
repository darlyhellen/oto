package com.chenminna.towife;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.chenminna.towife.adapter.VerticalAdapter;
import com.chenminna.towife.base.BaseActivity;
import com.chenminna.towife.bean.LoveData;
import com.chenminna.towife.widget.loginout.LoginOutDialg;
import com.chenminna.towife.widget.vpager.VerticalViewPager;
import com.chenminna.towife.widget.vpager.VerticalViewPager.OnPageChangeListener;
import com.chenminna.towife.widget.vpager.anim.CubeTransformer;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements OnPageChangeListener {

	@ViewInject(R.id.wife_verpager)
	private VerticalViewPager viewpage;

	private VerticalAdapter adapter;

	private List<LoveData> vies;

	@ViewInject(R.id.wife_main_up)
	private ImageView upimage;
	@ViewInject(R.id.wife_main_down)
	private ImageView downimage;
	private ScaleAnimation animation;

	private int theday = 20;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chenminna.towife.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		animation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animation.setDuration(300);
		animation.setRepeatCount(Animation.INFINITE);
		downimage.setAnimation(animation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chenminna.towife.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		vies = new ArrayList<LoveData>();
		for (int i = 0; i < theday; i++) {
			Calendar c = Calendar.getInstance();
			int mCurrentDay = c.get(Calendar.DAY_OF_MONTH);
			c.set(Calendar.DAY_OF_MONTH, mCurrentDay - theday + i);
			int mDay = c.get(Calendar.DAY_OF_MONTH);

			String time = c.get(Calendar.YEAR) + "年"
					+ (c.get(Calendar.MONTH) + 1) + "月" + mDay + "日";
			vies.add(new LoveData(0, time, i + "", "我们插肩而过"));
		}
		vies.add(new LoveData(4, null, null, null));
		viewpage.setPageTransformer(true, new CubeTransformer());
		adapter = new VerticalAdapter(vies);
		viewpage.setAdapter(adapter);
		// 页面打开。则首次顶部不进行展示。
		upimage.setVisibility(View.INVISIBLE);
		LogUtils.i(viewpage.getOffscreenPageLimit() + "预加载几个页面");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chenminna.towife.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		viewpage.setOnPageChangeListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chenminna.towife.widget.VerticalViewPager.OnPageChangeListener#
	 * onPageScrolled(int, float, int)
	 */
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chenminna.towife.widget.VerticalViewPager.OnPageChangeListener#
	 * onPageSelected(int)
	 */
	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		if (position >= 1) {
			upimage.setVisibility(View.VISIBLE);
			upimage.setAnimation(animation);
		} else {
			upimage.clearAnimation();
			upimage.setVisibility(View.INVISIBLE);
		}
		if (position >= vies.size() - 1) {
			downimage.clearAnimation();
			downimage.setVisibility(View.INVISIBLE);
		} else {
			downimage.setVisibility(View.VISIBLE);
			downimage.setAnimation(animation);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chenminna.towife.widget.VerticalViewPager.OnPageChangeListener#
	 * onPageScrollStateChanged(int)
	 */
	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#finish()
	 */
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		LoginOutDialg dialg = new LoginOutDialg(this);
		dialg.setTitle("温馨提示");
		dialg.setContent("是否确认退出应用?");
		dialg.setSure("确认");
		dialg.getSure().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				android.os.Process.killProcess(android.os.Process.myPid());
				MainActivity.super.finish();
			}
		});
		dialg.setConsel("取消");

	}

}
