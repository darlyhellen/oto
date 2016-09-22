/**下午4:44:14
 * @author zhangyh2
 * MainActivity.java
 * TODO
 */
package com.hellen.flower;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.hellen.base.BaseActivity;
import com.hellen.base.ConsMVP;
import com.hellen.common.SDCardUtils;
import com.hellen.common.SharePreferHelp;
import com.hellen.common.ToastApp;
import com.hellen.flower.fragment.CenterFragment;
import com.hellen.flower.fragment.HomeFragment;
import com.hellen.flower.fragment.OrnamentalFlowerFragment;
import com.hellen.flower.fragment.SucculentFragment;
import com.hellen.flower.fragment.VenusaurFragment;
import com.hellen.flower.login.LoginActivity;
import com.hellen.presenter.VersionUpdatingPresenter;
import com.hellen.widget.theme.ButtonRadio;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements OnClickListener {

	@ViewInject(R.id.main_footer_main)
	private ButtonRadio rb_main;
	@ViewInject(R.id.main_footer_list)
	private ButtonRadio rb_list;
	@ViewInject(R.id.main_footer_conversion)
	private ButtonRadio rb_conversion;
	@ViewInject(R.id.main_footer_act)
	private ButtonRadio rb_act;
	@ViewInject(R.id.main_footer_center)
	private ButtonRadio rb_center;

	/**
	 * 下午2:19:21 TODO 首页
	 */
	// private FragmentMain main;

	private HomeFragment main;
	/**
	 * 下午2:19:29 TODO 列表页
	 */
	private OrnamentalFlowerFragment list;

	/**
	 * 上午10:19:18 TODO 消息列表
	 */
	private SucculentFragment conver;

	/**
	 * 下午2:19:42 TODO 活动页
	 */
	private VenusaurFragment act;

	/**
	 * 下午2:19:51 TODO 设置页
	 */
	private CenterFragment center;

	private VersionUpdatingPresenter versionPresenter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// new CheckVersonHelper(this).checkVerson();
		if (getRadioGroup()) {
			timest();
			checkTheme();

		}
		rb_main.setInfo(R.string.footer_main, R.drawable.ic_index);
		rb_act.setInfo(R.string.footer_act, R.drawable.ic_index);
		rb_conversion.setInfo(R.string.footer_msg, R.drawable.ic_index);
		rb_list.setInfo(R.string.footer_list, R.drawable.ic_index);
		rb_center.setInfo(R.string.footer_center, R.drawable.ic_index);
		versionPresenter = new VersionUpdatingPresenter(this);
		versionPresenter.autoUpdateVersion();
	}

	/**
	 * 上午11:14:42
	 * 
	 * @author zhangyh2 TODO 查看网络是否有新的主题
	 */
	private void checkTheme() {
		// TODO Auto-generated method stub
		// HttpClient.get(this, ConsHttpUrl.THEMEVERSION, new RequestParams(),
		// new RequestCallBack<String>() {
		//
		// @Override
		// public void onSuccess(ResponseInfo<String> arg0) {
		// // TODO Auto-generated method stub
		// if (SharePreferHelp.getValue(
		// APPEnum.THEMEVERSION.getDec(), null) != null) {
		// if (SharePreferHelp.getValue(
		// APPEnum.THEMEVERSION.getDec(), null)
		// .equals(arg0.result)) {
		// LogUtils.i("old");
		// loadTheme(SharePreferHelp.getValue(
		// APPEnum.THEMECHANGE.getDec(), null));
		// } else {
		// SharePreferHelp.putValue(
		// APPEnum.THEMEVERSION.getDec(),
		// arg0.result);
		// getVersionTheme();
		// }
		// } else {
		// SharePreferHelp.putValue(
		// APPEnum.THEMEVERSION.getDec(), arg0.result);
		// getVersionTheme();
		// }
		// }
		//
		// @Override
		// public void onFailure(HttpException arg0, String arg1) {
		// // TODO Auto-generated method stub
		// getVersionTheme();
		// }
		// });

	}

	/**
	 * 上午10:27:40
	 * 
	 * @author zhangyh2 TODO 解析返回的首页轮播
	 */
	protected void loadTheme(String result) {
		// TODO Auto-generated method stub
		// if (result == null) {
		// // 没有网络数据
		// return;
		// }
		// // 开始解析轮播
		// LogUtils.i(result);
		// BaseModel<List<ClientTheme>> data = new
		// BaseModelPaser<List<ClientTheme>>()
		// .paserJson(result, new TypeToken<List<ClientTheme>>() {
		// });
		// if (data != null && data.getCode() == 200) {
		// // 新的主题
		// SharePreferHelp.putValue(APPEnum.THEMECHANGE.getDec(), result);
		// for (ClientTheme cl : data.getData()) {
		//
		// if ("main".endsWith(cl.getTitle())) {
		// ImageLoaderUtil.getInstance().loadImage(cl.getUrl(),
		// rb_main.getIv());
		// rb_main.getTv().setText(cl.getName());
		//
		// ImageLoaderUtil.getInstance().load(cl.getTheme(),
		// new Loading() {
		//
		// @Override
		// public void onStarted(String arg0, View arg1) {
		// // TODO Auto-generated method stub
		// rb_rg.setBackgroundColor(getResources()
		// .getColor(
		// R.color.app_main_header_bg));
		// }
		//
		// @Override
		// public void onFailed(String arg0, View arg1,
		// FailReason arg2) {
		// // TODO Auto-generated method stub
		// rb_rg.setBackgroundColor(getResources()
		// .getColor(
		// R.color.app_main_header_bg));
		// }
		//
		// @Override
		// public void onComplete(String arg0, View arg1,
		// Bitmap arg2) {
		// // TODO Auto-generated method stub
		// rb_rg.setBackground(new BitmapDrawable(
		// getResources(), arg2));
		// }
		//
		// @Override
		// public void onCancelled(String arg0, View arg1) {
		// // TODO Auto-generated method stub
		// rb_rg.setBackgroundColor(getResources()
		// .getColor(
		// R.color.app_main_header_bg));
		// }
		// });
		// }
		// if ("list".endsWith(cl.getTitle())) {
		// ImageLoaderUtil.getInstance().loadImage(cl.getUrl(),
		// rb_list.getIv());
		// rb_list.getTv().setText(cl.getName());
		// }
		// if ("act".endsWith(cl.getTitle())) {
		// ImageLoaderUtil.getInstance().loadImage(cl.getUrl(),
		// rb_act.getIv());
		// rb_act.getTv().setText(cl.getName());
		// }
		// if ("center".endsWith(cl.getTitle())) {
		// ImageLoaderUtil.getInstance().loadImage(cl.getUrl(),
		// rb_center.getIv());
		// rb_center.getTv().setText(cl.getName());
		// }
		//
		// }
		// } else {
		// // 旧的主题
		// loadTheme(SharePreferHelp.getValue(APPEnum.THEMECHANGE.getDec(),
		// null));
		// }
	}

	/**
	 * 
	 * 下午6:01:34
	 * 
	 * @author zhangyh2 MainActivity.java TODO
	 */
	private boolean timest() {
		Calendar cal = Calendar.getInstance();//  当前日期
		int hour = cal.get(Calendar.HOUR_OF_DAY);//  获取小时
		int minute = cal.get(Calendar.MINUTE);//  获取分钟
		int minuteOfDay = hour * 60 + minute;//  从0:00分开是到目前为止的分钟数
		final int start = 17 * 60 + 20;//  起始时间 17:20的分钟数
		final int end = 19 * 60;//  结束时间 19:00的分钟数
		if (minuteOfDay >= start && minuteOfDay <= end) {
			// LogUtils.i("在时间区间内");
			return true;
		} else {
			// LogUtils.i("在时间区间外");
			return false;
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
			if (SDCardUtils.getSDCardAllSize() > 1024 * 1024 * 8) {
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
		rb_main.setOnClickListener(this);
		rb_list.setOnClickListener(this);
		rb_conversion.setOnClickListener(this);
		rb_act.setOnClickListener(this);
		rb_center.setOnClickListener(this);
		swichTo(R.id.main_footer_main);
	}

	/**
	 * 下午2:22:23
	 * 
	 * @author zhangyh2 TODO
	 */
	private void toFirstPage() {
		// TODO Auto-generated method stub
		rb_main.getTv().setTextColor(getResources().getColor(R.color.pop_back));
		rb_list.getTv().setTextColor(getResources().getColor(R.color.pop_back));
		rb_conversion.getTv().setTextColor(
				getResources().getColor(R.color.pop_back));
		rb_act.getTv().setTextColor(getResources().getColor(R.color.pop_back));
		rb_center.getTv().setTextColor(
				getResources().getColor(R.color.pop_back));
		fragmentManager = getSupportFragmentManager();
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		hideFragments(ft);
		TAGSS = HomeFragment.class.getSimpleName();
		rb_main.getTv().setTextColor(getResources().getColor(R.color.white));
		if (main != null) {
			if (main.isVisible())
				return;
			ft.show(main);
		} else {
			main = new HomeFragment();
			ft.add(R.id.main_frame, main, TAGSS);
		}
		ft.commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		swichTo(v.getId());
	}

	/**
	 * 下午3:41:54
	 * 
	 * @author zhangyh2 TODO
	 */
	public void swichTo(int resid) {
		rb_main.getTv().setTextColor(getResources().getColor(R.color.pop_back));
		rb_list.getTv().setTextColor(getResources().getColor(R.color.pop_back));
		rb_conversion.getTv().setTextColor(
				getResources().getColor(R.color.pop_back));
		rb_act.getTv().setTextColor(getResources().getColor(R.color.pop_back));
		rb_center.getTv().setTextColor(
				getResources().getColor(R.color.pop_back));
		fragmentManager = getSupportFragmentManager();
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		switch (resid) {
		case R.id.main_footer_main:
			hideFragments(ft);
			TAGSS = HomeFragment.class.getSimpleName();
			rb_main.getTv()
					.setTextColor(getResources().getColor(R.color.white));
			if (main != null) {
				if (main.isVisible())
					return;
				ft.show(main);
			} else {
				main = new HomeFragment();
				ft.add(R.id.main_frame, main, TAGSS);
			}
			break;
		case R.id.main_footer_act:
			// 观花类
			hideFragments(ft);
			TAGSS = VenusaurFragment.class.getSimpleName();
			rb_act.getTv().setTextColor(getResources().getColor(R.color.white));
			if (act != null) {
				if (act.isVisible())
					return;
				ft.show(act);
			} else {
				act = new VenusaurFragment();
				ft.add(R.id.main_frame, act, TAGSS);
			}

			break;
		case R.id.main_footer_list:
			// 芳香类
			hideFragments(ft);
			TAGSS = OrnamentalFlowerFragment.class.getSimpleName();
			rb_list.getTv()
					.setTextColor(getResources().getColor(R.color.white));
			if (list != null) {
				if (list.isVisible())
					return;
				ft.show(list);
			} else {
				list = new OrnamentalFlowerFragment();
				ft.add(R.id.main_frame, list, TAGSS);
			}
			break;
		case R.id.main_footer_conversion:
			// 观果类
			hideFragments(ft);
			TAGSS = SucculentFragment.class.getSimpleName();
			rb_conversion.getTv().setTextColor(
					getResources().getColor(R.color.white));
			if (conver != null) {
				if (conver.isVisible())
					return;
				ft.show(conver);
			} else {
				conver = new SucculentFragment();
				ft.add(R.id.main_frame, conver, TAGSS);
			}
			break;
		case R.id.main_footer_center:
			if (!SharePreferHelp.getValue(ConsMVP.ISLOGIN.getDec(), false)) {
				startActivity(new Intent(this, LoginActivity.class));
				// rb_main.setChecked(true);
				toFirstPage();
				return;
			} else {
				hideFragments(ft);
				TAGSS = HomeFragment.class.getSimpleName();
				rb_center.getTv().setTextColor(
						getResources().getColor(R.color.white));
				if (center != null) {
					if (center.isVisible())
						return;
					ft.show(center);
				} else {
					center = new CenterFragment();
					ft.add(R.id.main_frame, center, TAGSS);
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
		if (conver != null) {
			transaction.hide(conver);
		}
		if (act != null) {
			transaction.hide(act);
		}
		if (center != null) {
			transaction.hide(center);
		}
	}

	private long time = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onKeyDown(int,
	 * android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (KeyEvent.KEYCODE_BACK == keyCode) {
			long dely = System.currentTimeMillis();
			if (dely - time < 2000) {
				System.exit(0);
			} else {
				ToastApp.showToast("再次点击退出程序");
				time = dely;
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
