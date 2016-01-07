package com.darly.dlclent.ui;

import java.util.Calendar;
import java.util.Random;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.JsonUtil;
import com.darly.dlclent.common.SDCardUtils;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.CheckUpdata;
import com.darly.dlclent.model.CheckUpdataBase;
import com.darly.dlclent.service.UpdateService;
import com.darly.dlclent.ui.fragment.FragmentAct;
import com.darly.dlclent.ui.fragment.FragmentCenter;
import com.darly.dlclent.ui.fragment.FragmentList;
import com.darly.dlclent.ui.fragment.FragmentMain;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
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
		checkVerson();

		if (getRadioGroup()) {

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
		rb_rg.setOnCheckedChangeListener(this);
		rb_main.setChecked(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub

	}

	private AlertDialog alertDialog;
	int Version = 8;

	/**
	 * 下午4:00:34
	 * 
	 * @author zhangyh2 MainActivity.java TODO 检查版本更新
	 */
	private void checkVerson() {
		// TODO Auto-generated method stub
		if (SharePreferHelp.getValue(APPEnum.NOTUPDATE.getDec(), 0) <= Calendar
				.getInstance().get(Calendar.DAY_OF_YEAR)) {

			if (!APP.isNetworkConnected(this)) {
				ToastApp.showToast(R.string.neterror);
				return;
			}

			String url = "";

			if (url != null && url.length() > 0) {
				RequestParams params = new RequestParams();
				HttpClient.get(this, url, params,
						new RequestCallBack<String>() {

							@Override
							public void onSuccess(ResponseInfo<String> result) {
								isUpdata(result.result.toString());
							}

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								ToastApp.showToast(R.string.neterror_norespanse);
							}
						});
			} else {
				String jsonString = null;
				if (new Random().nextBoolean()) {
					CheckUpdata re = new CheckUpdata(
							"",
							4,
							"版本还行，\r\n弄个测试作为全职PHPer偶尔需要客串下Androider,最近公司的一个项目需要Android的客户端\r\n(主要图片特效处理及其上传)，自己就客串下Androider.之前有过Android开发经验所以做这个挺顺手的，\r\n几乎所有东西直接github中拿过来改改就用，不过在处理图片上传的时候选择了xUtils这个",
							false);

					CheckUpdataBase da = new CheckUpdataBase(200, "", re);
					jsonString = JsonUtil.pojo2Json(da);
				} else {
					CheckUpdataBase da = new CheckUpdataBase(110, "用户信息出错",
							null);
					jsonString = JsonUtil.pojo2Json(da);
				}
				LogUtils.i(jsonString);
				isUpdata(jsonString);
			}

		}
	}

	/**
	 * @param string
	 *            下午4:20:11
	 * @author zhangyh2 MainActivity.java TODO 是否需要升级
	 */
	protected void isUpdata(String datas) {
		// TODO Auto-generated method stub
		if (datas == null) {
			return;
		}
		BaseModel<CheckUpdata> data = new BaseModelPaser<CheckUpdata>()
				.paserJson(datas, new TypeToken<CheckUpdata>() {
				});
		if (data != null && data.getCode() == 200) {
			LogUtils.i(data.toString());
			int versionCode = APP.getInstance().getVersionCode();
			if (data.getDataT().version != 0
					&& data.getDataT().version - versionCode > 0) {
				// 需要更新
				showUpdateDialog(data.getDataT());
			}
		} else {
			ToastApp.showToast(data.getMsg());
		}
	}

	public void showUpdateDialog(final CheckUpdata data) {
		if (alertDialog != null && alertDialog.isShowing()) {
			return;
		}
		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					if (data.Force) {
						System.exit(0);
					} else {
						// 计算间隔几天进行重新检验更新。
						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.DAY_OF_YEAR,
								calendar.get(Calendar.DAY_OF_YEAR) + 3);
						SharePreferHelp.putValue(APPEnum.NOTUPDATE.getDec(),
								calendar.get(Calendar.DAY_OF_YEAR));
					}
				}
				return false;
			}
		});
		alertDialog.show();
		alertDialog.setCanceledOnTouchOutside(false);
		Window window = alertDialog.getWindow();
		window.setContentView(R.layout.update_dialog);// 设置对话框的布局

		TextView descriptiontv = (TextView) window
				.findViewById(R.id.description);
		descriptiontv.setLayoutParams(new LayoutParams(APPEnum.WIDTH.getLen(),
				APPEnum.WIDTH.getLen() / 3));
		descriptiontv.setMovementMethod(ScrollingMovementMethod.getInstance());
		if (data.description != null) {
			descriptiontv.setText(data.description);
		}
		Button sure = (Button) window.findViewById(R.id.sure);
		sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				alertDialog.dismiss();
				// checkISupdate();
				// update(lastestUrl);
				Intent intent = new Intent(MainActivity.this,
						UpdateService.class);
				intent.putExtra("url", data.LastestUrl);
				MainActivity.this.startService(intent);
				if (data.Force) {
					finish();
				}
			}
		});

		Button notsure = (Button) window.findViewById(R.id.notsure);
		if (data.Force) {
			alertDialog.setCancelable(false);
			notsure.setVisibility(View.GONE);
		} else {
			alertDialog.setCancelable(true);
			notsure.setVisibility(View.VISIBLE);
			notsure.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					alertDialog.dismiss();
					// 计算间隔几天进行重新检验更新。
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.DAY_OF_YEAR,
							calendar.get(Calendar.DAY_OF_YEAR) + 3);
					SharePreferHelp.putValue(APPEnum.NOTUPDATE.getDec(),
							calendar.get(Calendar.DAY_OF_YEAR));
				}
			});
		}
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
		rb_main.setTextColor(getResources().getColor(R.color.set_list_line));
		rb_list.setTextColor(getResources().getColor(R.color.set_list_line));
		rb_act.setTextColor(getResources().getColor(R.color.set_list_line));
		rb_center.setTextColor(getResources().getColor(R.color.set_list_line));
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		hideFragments(ft);
		switch (checkedId) {
		case R.id.main_footer_main:
			LogUtils.i("main_footer_main");
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
			LogUtils.i("main_footer_list");
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
			LogUtils.i("main_footer_act");
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
			LogUtils.i("main_footer_center");
			rb_center.setTextColor(getResources().getColor(R.color.white));
			if (center != null) {
				if (center.isVisible())
					return;
				ft.show(center);
			} else {
				center = new FragmentCenter();
				ft.add(R.id.main_frame, center);
			}
			break;
		default:
			break;
		}
		ft.commitAllowingStateLoss();

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
