/**
 * 上午11:20:38
 * @author zhangyh2
 * $
 * Welcome.java
 * TODO
 */
package com.hellen.flower.wel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.hellen.base.APP;
import com.hellen.base.BaseActivity;
import com.hellen.base.BasePresenter;
import com.hellen.base.ConsMVP;
import com.hellen.biz.WelComeInterface;
import com.hellen.common.LogApp;
import com.hellen.common.SharePreferHelp;
import com.hellen.flower.MainActivity;
import com.hellen.flower.R;
import com.hellen.presenter.WelComePresenter;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 Welcome $ 上午11:20:38 TODO
 */
@ContentView(R.layout.view_welcome)
public class WelcomeActivity extends BaseActivity implements AnimationListener,
		WelComeInterface {

	private String tag = getClass().getSimpleName();
	@ViewInject(R.id.welcome_iv)
	private ImageView view;
	@ViewInject(R.id.welcome_tv)
	private TextView tv;

	private WelComePresenter welComePresenter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.oop.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		welComePresenter = new WelComePresenter(this);
		welComePresenter.startMainService(this);
		welComePresenter.getBackGround();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.oop.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.oop.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseView#setPersenter(com.hellen.base.BasePresenter)
	 */
	@Override
	public void setPersenter(BasePresenter persenter) {
		// TODO Auto-generated method stub
		LogApp.i(tag, "setPersenter" + persenter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.WelComeInterface#setVersion(java.lang.String)
	 */
	@Override
	public void setVersion(String version) {
		// TODO Auto-generated method stub
		LogApp.i(tag, "setVersion" + version);
		tv.setText(APP.getInstance().getVersion());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.WelComeInterface#setBack(android.graphics.Bitmap)
	 */
	@Override
	public void setBack(Bitmap bitmap) {
		// TODO Auto-generated method stub
		LogApp.i(tag, "setBack" + bitmap);
		if (bitmap == null) {
			view.setImageResource(R.drawable.ic_welcome);
		} else {
			view.setImageBitmap(bitmap);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.WelComeInterface#startAnim()
	 */
	@Override
	public void startAnim() {
		// TODO Auto-generated method stub
		LogApp.i(tag, "startAnim");
		Animation animation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animation.setDuration(3000);
		animation.setFillAfter(true);
		view.setAnimation(animation);
		animation.setAnimationListener(this);
	}

	/**
	 * 
	 * 下午3:37:03
	 * 
	 * @author zhangyh2 GuideAnim.java TODO
	 */
	private void intoMain() {
		// TODO Auto-generated method stub
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

	/**
	 * 
	 * 上午11:23:10
	 * 
	 * @author zhangyh2 Welcome.java TODO
	 */
	private void intoGuide() {
		// TODO Auto-generated method stub
		SharePreferHelp.putValue(ConsMVP.ISFIRSTCOME.getDec()
				+ APP.getInstance().getVersion(), false);
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.animation.Animation.AnimationListener#onAnimationStart(android
	 * .view.animation.Animation)
	 */
	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.animation.Animation.AnimationListener#onAnimationEnd(android
	 * .view.animation.Animation)
	 */
	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		boolean isFirstCome = SharePreferHelp.getValue(
				ConsMVP.ISFIRSTCOME.getDec() + APP.getInstance().getVersion(),
				true);
		if (isFirstCome) {
			// 第一次使用
			intoGuide();
		} else {
			// 直接进入MainActivity
			intoMain();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.animation.Animation.AnimationListener#onAnimationRepeat(
	 * android.view.animation.Animation)
	 */
	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}
}
