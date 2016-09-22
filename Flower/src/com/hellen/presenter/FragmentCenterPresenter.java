/**下午1:34:46
 * @author zhangyh2
 * FragmentCenterPresenter.java
 * TODO
 */
package com.hellen.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.hellen.biz.CenterInterface;
import com.hellen.flower.R;
import com.hellen.flower.userinfo.ChangeUserInfoActivity;

/**
 * @author zhangyh2 FragmentCenterPresenter 下午1:34:46 TODO
 */
public class FragmentCenterPresenter {

	private CenterInterface view;

	private VersionUpdatingPresenter versionPresenter;

	public FragmentCenterPresenter(CenterInterface views) {
		super();
		this.view = views;
		view.initTitle();
		view.initUser();
	}

	/**
	 * @param versionPresenter
	 *            the versionPresenter to set
	 */
	public void setVersionPresenter(VersionUpdatingPresenter versionPresenter) {
		this.versionPresenter = versionPresenter;
	}

	/**
	 * 下午1:39:51
	 * 
	 * @author zhangyh2 TODO
	 * @param context
	 */
	public void onClickDown(Context context, View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.center_userview:
			// 用户信息页面
			toUserInfoPage(context);
			break;
		case R.id.center_baogao:
			// 报告页面
			toBaoGaoPage(context);
			break;
		case R.id.center_yuyue:
			// 预约页面
			toYuYuePage(context);
			break;
		case R.id.center_yijian:
			// 意见页面
			toYiJianPage(context);
			break;
		case R.id.center_shezhi:
			// 设置页面
			toSheZhiPage(context);
			break;
		case R.id.center_gengxin:
			// 更新页面
			toGengXinShow(context);
			break;
		case R.id.center_guanyu:
			// 关于页面
			toAboutUsPage(context);
			break;

		default:
			break;
		}
	}

	/**
	 * 下午1:44:56
	 * 
	 * @author zhangyh2 TODO
	 */
	private void toUserInfoPage(Context context) {
		// TODO Auto-generated method stub
		context.startActivity(new Intent(context, ChangeUserInfoActivity.class));
	}

	/**
	 * 下午1:47:24
	 * 
	 * @author zhangyh2 TODO
	 */
	private void toBaoGaoPage(Context context) {
		// TODO Auto-generated method stub

	}

	/**
	 * 下午1:47:22
	 * 
	 * @author zhangyh2 TODO
	 */
	private void toYuYuePage(Context context) {
		// TODO Auto-generated method stub

	}

	/**
	 * 下午1:47:18
	 * 
	 * @author zhangyh2 TODO
	 */
	private void toYiJianPage(Context context) {
		// TODO Auto-generated method stub

	}

	/**
	 * 下午1:47:16
	 * 
	 * @author zhangyh2 TODO
	 */
	private void toSheZhiPage(Context context) {
		// TODO Auto-generated method stub

	}

	/**
	 * 下午1:47:15
	 * 
	 * @author zhangyh2 TODO <a>版本更新</a>
	 */
	private void toGengXinShow(Context context) {
		// TODO Auto-generated method stub
		versionPresenter.handUpdateVersion();
	}

	/**
	 * 下午1:47:13
	 * 
	 * @author zhangyh2 TODO
	 */
	private void toAboutUsPage(Context context) {
		// TODO Auto-generated method stub

	}

}
