package com.darly.dlclent.ui;

import java.util.Calendar;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.ImageLoaderUtil;
import com.darly.dlclent.common.ImageLoaderUtil.Loading;
import com.darly.dlclent.common.SDCardUtils;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.ClientTheme;
import com.darly.dlclent.ui.fragment.FragmentAct;
import com.darly.dlclent.ui.fragment.FragmentCenter;
import com.darly.dlclent.ui.fragment.FragmentComp;
import com.darly.dlclent.ui.fragment.FragmentList;
import com.darly.dlclent.ui.fragment.FragmentMain;
import com.darly.dlclent.ui.login.LoginActivity;
import com.darly.dlclent.widget.theme.ButtonRadio;
import com.darly.im.ui.ConversationListFragment;
import com.darly.im.ui.ConversationListFragment.OnUpdateMsgUnreadCountsListener;
import com.darly.im.ui.contact.MobileContactActivity;
import com.darly.im.ui.contact.MobileContactActivity.MobileContactFragment;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengDialogButtonListener;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements OnClickListener,
		OnCheckedChangeListener, OnUpdateMsgUnreadCountsListener {

	@ViewInject(R.id.main_footer_group)
	private RadioGroup rb_rg;
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

	private FragmentComp main;

	/**
	 * 下午2:19:29 TODO 列表页
	 */
	private/* FragmentAct */MobileContactActivity.MobileContactFragment list;

	/**
	 * 上午10:19:18 TODO 消息列表
	 */
	private ConversationListFragment conver;

	/**
	 * 下午2:19:42 TODO 活动页
	 */
	private FragmentList act;

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
		// new CheckVersonHelper(this).checkVerson();

		startCheck();

		if (getRadioGroup()) {
			timest();
			checkTheme();

		}
		rb_main.setInfo(R.string.footer_main, R.drawable.ic_index);
		rb_act.setInfo(R.string.footer_act, R.drawable.ic_me_press);
		rb_conversion.setInfo(R.string.footer_msg, R.drawable.ic_conversation);
		rb_list.setInfo(R.string.footer_list, R.drawable.ic_me);
		rb_center.setInfo(R.string.footer_center, R.drawable.ic_set_press);
	}

	/**
	 * 上午11:14:42
	 * 
	 * @author zhangyh2 TODO 查看网络是否有新的主题
	 */
	private void checkTheme() {
		// TODO Auto-generated method stub
		HttpClient.get(this, ConsHttpUrl.THEMEVERSION, new RequestParams(),
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						if (SharePreferHelp.getValue(
								APPEnum.THEMEVERSION.getDec(), null) != null) {
							if (SharePreferHelp.getValue(
									APPEnum.THEMEVERSION.getDec(), null)
									.equals(arg0.result)) {
								LogUtils.i("old");
								loadTheme(SharePreferHelp.getValue(
										APPEnum.THEMECHANGE.getDec(), null));
							} else {
								SharePreferHelp.putValue(
										APPEnum.THEMEVERSION.getDec(),
										arg0.result);
								getVersionTheme();
							}
						} else {
							SharePreferHelp.putValue(
									APPEnum.THEMEVERSION.getDec(), arg0.result);
							getVersionTheme();
						}
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						getVersionTheme();
					}
				});

	}

	/**
	 * 上午11:09:14
	 * 
	 * @author zhangyh2 TODO
	 */
	private void getVersionTheme() {
		// 分为三类，第一：新主题。第二：旧主题.第三：本地主题。
		// 网络请求
		HttpClient.get(this, ConsHttpUrl.THEMECHANGE, new RequestParams(),
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						LogUtils.i("new");
						loadTheme(arg0.result);
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						LogUtils.i("old");
						loadTheme(SharePreferHelp.getValue(
								APPEnum.THEMECHANGE.getDec(), null));
					}
				});
	}

	/**
	 * 上午10:27:40
	 * 
	 * @author zhangyh2 TODO 解析返回的首页轮播
	 */
	protected void loadTheme(String result) {
		// TODO Auto-generated method stub
		if (result == null) {
			// 没有网络数据
			return;
		}
		// 开始解析轮播
		LogUtils.i(result);
		BaseModel<List<ClientTheme>> data = new BaseModelPaser<List<ClientTheme>>()
				.paserJson(result, new TypeToken<List<ClientTheme>>() {
				});
		if (data != null && data.getCode() == 200) {
			// 新的主题
			SharePreferHelp.putValue(APPEnum.THEMECHANGE.getDec(), result);
			for (ClientTheme cl : data.getData()) {

				if ("main".endsWith(cl.getTitle())) {
					ImageLoaderUtil.getInstance().loadImage(cl.getUrl(),
							rb_main.getIv());
					rb_main.getTv().setText(cl.getName());
					
					ImageLoaderUtil.getInstance().load(cl.getTheme(), new Loading() {
						
						@Override
						public void onStarted(String arg0, View arg1) {
							// TODO Auto-generated method stub
							rb_rg.setBackgroundColor(getResources()
									.getColor(
											R.color.app_main_header_bg));
						}
						
						@Override
						public void onFailed(String arg0, View arg1, FailReason arg2) {
							// TODO Auto-generated method stub
							rb_rg.setBackgroundColor(getResources()
									.getColor(
											R.color.app_main_header_bg));
						}
						
						@Override
						public void onComplete(String arg0, View arg1, Bitmap arg2) {
							// TODO Auto-generated method stub
							rb_rg.setBackground(new BitmapDrawable(
									getResources(), arg2));
						}
						
						@Override
						public void onCancelled(String arg0, View arg1) {
							// TODO Auto-generated method stub
							rb_rg.setBackgroundColor(getResources()
									.getColor(
											R.color.app_main_header_bg));
						}
					});
				}
				if ("list".endsWith(cl.getTitle())) {
					ImageLoaderUtil.getInstance().loadImage(cl.getUrl(),
							rb_list.getIv());
					rb_list.getTv().setText(cl.getName());
				}
				if ("act".endsWith(cl.getTitle())) {
					ImageLoaderUtil.getInstance().loadImage(cl.getUrl(),
							rb_act.getIv());
					rb_act.getTv().setText(cl.getName());
				}
				if ("center".endsWith(cl.getTitle())) {
					ImageLoaderUtil.getInstance().loadImage(cl.getUrl(),
							rb_center.getIv());
					rb_center.getTv().setText(cl.getName());
				}

			}
		} else {
			// 旧的主题
			loadTheme(SharePreferHelp.getValue(APPEnum.THEMECHANGE.getDec(),
					null));
		}
	}

	/**
	 * 下午3:01:16
	 * 
	 * @author zhangyh2 TODO 版本更新
	 */
	protected void startCheck() {
		// TODO Auto-generated method stub
		// 考虑到用户流量的限制，目前我们默认在Wi-Fi接入情况下才进行自动提醒。如需要在任意网络环境下都进行更新自动提醒，
		// 则请在update调用之前添加以下代码：UmengUpdateAgent.setUpdateOnlyWifi(false)。
		// 特别提示：针对机顶盒等可能不支持或者没有无线网络的设备， 请同样添加上述代码。
		UmengUpdateAgent.setUpdateOnlyWifi(false);

		String upgrade_mode = MobclickAgent.getConfigParams(this, "updata");

		// OnlineConfigAgent.getInstance().updateOnlineConfig(this);
		// OnlineConfigAgent.getInstance().setDebugMode(true);
		// String upgrade_mode =
		// OnlineConfigAgent.getInstance().getConfigParams(
		// this, "updata");
		LogUtils.i("获取的参数" + upgrade_mode);
		if (TextUtils.isEmpty(upgrade_mode)) {
			return;
		}
		UmengUpdateAgent.setUpdateOnlyWifi(false);
		UmengUpdateAgent.update(this);
		UmengUpdateAgent.forceUpdate(this);// 这行如果是强制更新就一定加上
		int versionCode = APP.getInstance().getVersionCode();
		if (Integer.parseInt(upgrade_mode) > versionCode) {
			// 进入强制更新
			UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {

				@Override
				public void onUpdateReturned(int updateStatus,
						UpdateResponse updateResponse) {

				}
			});
			UmengUpdateAgent.setDialogListener(new UmengDialogButtonListener() {
				@Override
				public void onClick(int status) {

					switch (status) {
					case UpdateStatus.Update:
					default:
						// 退出应用
						ToastApp.showToast("请等待升级完成后再次使用，谢谢合作！");
						MainActivity.this.finish();
					}
				}
			});
		} else {
			UmengUpdateAgent.update(this);
		}
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
		// rb_rg.setOnCheckedChangeListener(this);
		// rb_main.setChecked(true);
		rb_main.setOnClickListener(this);
		rb_list.setOnClickListener(this);
		rb_conversion.setOnClickListener(this);
		rb_act.setOnClickListener(this);
		rb_center.setOnClickListener(this);
		toFirstPage();
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
		TAG = FragmentMain.class.getSimpleName();
		rb_main.getTv().setTextColor(getResources().getColor(R.color.white));
		if (main != null) {
			if (main.isVisible())
				return;
			ft.show(main);
		} else {
			main = new FragmentComp();
			ft.add(R.id.main_frame, main, TAG);
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

		switch (v.getId()) {
		case R.id.main_footer_main:
			hideFragments(ft);
			TAG = FragmentMain.class.getSimpleName();
			rb_main.getTv()
					.setTextColor(getResources().getColor(R.color.white));
			if (main != null) {
				if (main.isVisible())
					return;
				ft.show(main);
			} else {
				main = new FragmentComp();
				ft.add(R.id.main_frame, main, TAG);
			}
			break;
		case R.id.main_footer_act:
			hideFragments(ft);
			TAG = FragmentAct.class.getSimpleName();
			rb_act.getTv().setTextColor(getResources().getColor(R.color.white));
			if (act != null) {
				if (act.isVisible())
					return;
				ft.show(act);
			} else {
				act = new FragmentList();
				ft.add(R.id.main_frame, act, TAG);
			}
			break;
		case R.id.main_footer_list:
			if (!SharePreferHelp.getValue(APPEnum.ISLOGIN.getDec(), false)) {
				startActivity(new Intent(this, LoginActivity.class));
				// rb_main.setChecked(true);
				toFirstPage();
				return;
			} else {
				hideFragments(ft);
				TAG = MobileContactFragment.class.getSimpleName();
				rb_list.getTv().setTextColor(
						getResources().getColor(R.color.white));
				list = new MobileContactFragment(); /* new FragmentList() */
				ft.add(R.id.main_frame, list, TAG);
			}
			break;
		case R.id.main_footer_conversion:
			if (!SharePreferHelp.getValue(APPEnum.ISLOGIN.getDec(), false)) {
				startActivity(new Intent(this, LoginActivity.class));
				// rb_main.setChecked(true);
				toFirstPage();
				return;
			} else {
				hideFragments(ft);
				TAG = ConversationListFragment.class.getSimpleName();
				rb_conversion.getTv().setTextColor(
						getResources().getColor(R.color.white));
				conver = new ConversationListFragment();
				ft.add(R.id.main_frame, conver, TAG);
			}
			break;
		case R.id.main_footer_center:
			if (!SharePreferHelp.getValue(APPEnum.ISLOGIN.getDec(), false)) {
				startActivity(new Intent(this, LoginActivity.class));
				// rb_main.setChecked(true);
				toFirstPage();
				return;
			} else {
				hideFragments(ft);
				TAG = FragmentCenter.class.getSimpleName();
				rb_center.getTv().setTextColor(
						getResources().getColor(R.color.white));
				if (center != null) {
					if (center.isVisible())
						return;
					ft.show(center);
				} else {
					center = new FragmentCenter();
					ft.add(R.id.main_frame, center, TAG);
				}
			}
			break;
		default:
			break;
		}
		ft.commit();
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
		// rb_main.setTextColor(getResources().getColor(R.color.pop_back));
		// rb_list.setTextColor(getResources().getColor(R.color.pop_back));
		// rb_conversion.setTextColor(getResources().getColor(R.color.pop_back));
		// rb_act.setTextColor(getResources().getColor(R.color.pop_back));
		// rb_center.setTextColor(getResources().getColor(R.color.pop_back));
		// fragmentManager = getSupportFragmentManager();
		// FragmentManager fm = getSupportFragmentManager();
		// FragmentTransaction ft = fm.beginTransaction();
		//
		// switch (checkedId) {
		// case R.id.main_footer_main:
		// hideFragments(ft);
		// TAG = FragmentMain.class.getSimpleName();
		// rb_main.setTextColor(getResources().getColor(R.color.white));
		// if (main != null) {
		// if (main.isVisible())
		// return;
		// ft.show(main);
		// } else {
		// main = new FragmentMain();
		// ft.add(R.id.main_frame, main, TAG);
		// }
		// break;
		// case R.id.main_footer_act:
		// hideFragments(ft);
		// TAG = FragmentAct.class.getSimpleName();
		// rb_act.setTextColor(getResources().getColor(R.color.white));
		// if (act != null) {
		// if (act.isVisible())
		// return;
		// ft.show(act);
		// } else {
		// act = new FragmentAct();
		// ft.add(R.id.main_frame, act, TAG);
		// }
		// break;
		// case R.id.main_footer_list:
		// if (!SharePreferHelp.getValue(APPEnum.ISLOGIN.getDec(), false)) {
		// startActivity(new Intent(this, LoginActivity.class));
		// rb_main.setChecked(true);
		// } else {
		// hideFragments(ft);
		// TAG = MobileContactFragment.class.getSimpleName();
		// rb_list.setTextColor(getResources().getColor(R.color.white));
		// list = new MobileContactFragment(); /* new FragmentList() */
		// ft.add(R.id.main_frame, list, TAG);
		// }
		// break;
		// case R.id.main_footer_conversion:
		// if (!SharePreferHelp.getValue(APPEnum.ISLOGIN.getDec(), false)) {
		// startActivity(new Intent(this, LoginActivity.class));
		// rb_main.setChecked(true);
		// } else {
		// hideFragments(ft);
		// TAG = ConversationListFragment.class.getSimpleName();
		// rb_conversion.setTextColor(getResources().getColor(
		// R.color.white));
		// conver = new ConversationListFragment();
		// ft.add(R.id.main_frame, conver, TAG);
		// }
		// break;
		// case R.id.main_footer_center:
		// if (!SharePreferHelp.getValue(APPEnum.ISLOGIN.getDec(), false)) {
		// startActivity(new Intent(this, LoginActivity.class));
		// rb_main.setChecked(true);
		// } else {
		// hideFragments(ft);
		// TAG = FragmentCenter.class.getSimpleName();
		// rb_center.setTextColor(getResources().getColor(R.color.white));
		// if (center != null) {
		// if (center.isVisible())
		// return;
		// ft.show(center);
		// } else {
		// center = new FragmentCenter();
		// ft.add(R.id.main_frame, center, TAG);
		// }
		// }
		// break;
		// default:
		// break;
		// }
		// ft.commit();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.darly.im.ui.ConversationListFragment.OnUpdateMsgUnreadCountsListener
	 * #OnUpdateMsgUnreadCounts()
	 */
	@Override
	public void OnUpdateMsgUnreadCounts() {
		// TODO Auto-generated method stub
	}

}
