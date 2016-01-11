package com.darly.dlclent.ui;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.SDCardUtils;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.ui.fragment.FragmentAct;
import com.darly.dlclent.ui.fragment.FragmentCenter;
import com.darly.dlclent.ui.fragment.FragmentList;
import com.darly.dlclent.ui.fragment.FragmentMain;
import com.darly.dlclent.ui.login.LoginActivity;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements OnClickListener,
		OnCheckedChangeListener {

	@ViewInject(R.id.main_footer_group)
	private RadioGroup rb_rg;
	@ViewInject(R.id.main_footer_main)
	private RadioButton rb_main;
	@ViewInject(R.id.main_footer_list)
	private RadioButton rb_list;
	@ViewInject(R.id.main_footer_act)
	private RadioButton rb_act;
	@ViewInject(R.id.main_footer_center)
	private RadioButton rb_center;

	/**
	 * 下午2:19:21 TODO 首页
	 */
	private FragmentMain main;

	/**
	 * 下午2:19:29 TODO 列表页
	 */
	private FragmentList list;

	/**
	 * 下午2:19:42 TODO 活动页
	 */
	private FragmentAct act;

	/**
	 * 下午2:19:51 TODO 设置页
	 */
	private FragmentCenter center;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		new CheckVersonHelper(this).checkVerson();

		getRadioGroup();
		timest();

	}

	/**
	 * 
	 * 下午6:01:34
	 * 
	 * @author zhangyh2 MainActivity.java TODO
	 */
	private void timest() {
		Calendar cal = Calendar.getInstance();//  当前日期
		int hour = cal.get(Calendar.HOUR_OF_DAY);//  获取小时
		int minute = cal.get(Calendar.MINUTE);//  获取分钟
		int minuteOfDay = hour * 60 + minute;//  从0:00分开是到目前为止的分钟数
		final int start = 17 * 60 + 20;//  起始时间 17:20的分钟数
		final int end = 19 * 60;//  结束时间 19:00的分钟数
		if (minuteOfDay >= start && minuteOfDay <= end) {
			System.out.println("在外围内");
		} else {
			System.out.println("在外围外");
		}
	}

	/**
	 * 
	 * 上午11:44:43
	 * 
	 * @author zhangyh2 MainActivity.java TODO 进入APP首页先获取一组文件信息。并对文件进行下载。
	 *         加载本地数据，还是加载网络数据。<code>false</code>加载本地。<code>true</code>加载网络
	 */
	private boolean getRadioGroup() {
		// TODO Auto-generated method stub
		// 首先判断SD卡
		if (SDCardUtils.isSDCardEnable()) {
			// 判断内存是否大于1M
			if (SDCardUtils.getSDCardAllSize() > 1024 * 1024 * 1024) {
				return true;
			} else {
				ToastApp.showToast("手机内存已满，请清理内存");
				return false;
			}
		} else {
			ToastApp.showToast("SD卡不存在，请检查手机SD卡");
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		rb_rg.setOnCheckedChangeListener(this);
		rb_main.setChecked(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.RadioGroup.OnCheckedChangeListener#onCheckedChanged(android
	 * .widget.RadioGroup, int)
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		rb_main.setTextColor(getResources().getColor(R.color.pop_back));
		rb_list.setTextColor(getResources().getColor(R.color.pop_back));
		rb_act.setTextColor(getResources().getColor(R.color.pop_back));
		rb_center.setTextColor(getResources().getColor(R.color.pop_back));
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		hideFragments(ft);
		switch (checkedId) {
		case R.id.main_footer_main:
			rb_main.setTextColor(getResources().getColor(R.color.white));
			if (main != null) {
				if (main.isVisible())
					return;
				ft.show(main);
			} else {
				main = new FragmentMain();
				ft.add(R.id.main_frame, main);
			}
			break;
		case R.id.main_footer_list:
			rb_list.setTextColor(getResources().getColor(R.color.white));
			if (list != null) {
				if (list.isVisible())
					return;
				ft.show(list);
			} else {
				list = new FragmentList();
				ft.add(R.id.main_frame, list);
			}
			break;
		case R.id.main_footer_act:
			rb_act.setTextColor(getResources().getColor(R.color.white));
			if (act != null) {
				if (act.isVisible())
					return;
				ft.show(act);
			} else {
				act = new FragmentAct();
				ft.add(R.id.main_frame, act);
			}
			break;
		case R.id.main_footer_center:
			if (!SharePreferHelp.getValue(APPEnum.ISLOGIN.getDec(), false)) {
				startActivity(new Intent(this, LoginActivity.class));
				rb_main.setChecked(true);
			} else {
				rb_center.setTextColor(getResources().getColor(R.color.white));
				if (center != null) {
					if (center.isVisible())
						return;
					ft.show(center);
				} else {
					center = new FragmentCenter();
					ft.add(R.id.main_frame, center);
				}
			}
			break;
		default:
			break;
		}
		ft.commit();

	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (main != null) {
			transaction.hide(main);
		}
		if (list != null) {
			transaction.hide(list);
		}
		if (act != null) {
			transaction.hide(act);
		}
		if (center != null) {
			transaction.hide(center);
		}
	}

}
