/**上午11:08:05
 * @author zhangyh2
 * VersionUpdatingPresenter.java
 * TODO
 */
package com.hellen.presenter;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hellen.base.APP;
import com.hellen.base.BaseListener;
import com.hellen.base.ConsMVP;
import com.hellen.bean.BaseModel;
import com.hellen.bean.BaseModelPaser;
import com.hellen.bean.VersionModel;
import com.hellen.biz.VersionUpdatingInterface.VersionUpdatingBiz;
import com.hellen.biz.imp.VersionUpdating;
import com.hellen.common.LogApp;
import com.hellen.common.SharePreferHelp;
import com.hellen.flower.MainActivity;
import com.hellen.flower.R;
import com.hellen.flower.service.UpdateService;

/**
 * @author zhangyh2 VersionUpdatingPresenter 上午11:08:05 TODO
 */
public class VersionUpdatingPresenter {

	private String TAG = getClass().getSimpleName();

	private VersionUpdatingBiz biz;

	private Context context;

	private AlertDialog isinWifi;

	private AlertDialog alertDialog;

	private AlertDialog heightVersion;

	public VersionUpdatingPresenter(Context context) {
		super();
		this.context = context;
		biz = new VersionUpdating();
	}

	/**
	 * 上午10:55:45
	 * 
	 * @author zhangyh2 TODO 自动更新
	 */
	public void autoUpdateVersion() {
		if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) >= SharePreferHelp
				.getValue(ConsMVP.NOTUPDATE.getDec(), 0)) {
			// TODO Auto-generated method stub
			biz.versionUpdate(new BaseListener<String>() {

				@Override
				public void onSucces(String result) {
					// TODO Auto-generated method stub
					autoparseJson(result);
				}

				@Override
				public void onFaild(int code, String info) {
					// TODO Auto-generated method stub

				}
			});
		}
	}

	/**
	 * 上午10:01:39
	 * 
	 * @author zhangyh2 TODO 解析数据
	 */
	private void autoparseJson(String jsonStr) {
		if (jsonStr == null) {
			return;
		}
		BaseModel<VersionModel> data = new BaseModelPaser<VersionModel>()
				.paserJson(jsonStr, new TypeToken<VersionModel>() {
				});
		if (data != null) {
			if (data.getCode() == 200 && data.getData() != null) {
				// 进行更新提示
				if (!TextUtils.isEmpty(data.getData().getUrl())) {
					int versionCode = APP.getInstance().getVersionCode();
					if (data.getData().getVersion() - versionCode > 0) {
						LogApp.i(TAG, "弹窗提示更新");
						showUpdateDialog(data.getData().getUrl(), data
								.getData().getCompareStr(), data.getData()
								.getVersion(),
								data.getData().getDownloadType(), data
										.getData().getDescription(), data
										.getData().isForced());
					}
				} else {
					// url为空不进行更新提示
				}
			} else {
				// 服务端返回失败不进行更新提示
			}
		} else {
			// 解析数据为空不进行更新提示
		}
	}

	/**
	 * 上午11:01:04
	 * 
	 * @author zhangyh2 TODO {@link MainActivity}调用的方案
	 */
	private void showUpdateDialog(final String lastestUrl, final String desMd5,
			final int version, final int type, String description,
			final boolean isQZupdate) {
		if (alertDialog != null && alertDialog.isShowing()) {
			return;
		}
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					if (isQZupdate) {
						SharePreferHelp.putValue(
								ConsMVP.CHECKISUPDATE.getDec(), true);
						System.exit(0);
					} else {
						// 计算间隔几天进行重新检验更新。
						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.DAY_OF_YEAR,
								calendar.get(Calendar.DAY_OF_YEAR) + 3);
						SharePreferHelp.putValue(ConsMVP.NOTUPDATE.getDec(),
								calendar.get(Calendar.DAY_OF_YEAR));
					}
				}
				return false;
			}
		});
		alertDialog.show();
		WindowManager.LayoutParams params = alertDialog.getWindow()
				.getAttributes();
		params.width = (int) (ConsMVP.WIDTH.getLen() * 0.86);
		alertDialog.getWindow().setAttributes(params);
		alertDialog.setCanceledOnTouchOutside(false);
		Window window = alertDialog.getWindow();
		window.setContentView(R.layout.update);// 设置对话框的布局
		TextView descripttitle = (TextView) window
				.findViewById(R.id.description_title);
		descripttitle.setText("版本升级");
		TextView descriptiontv = (TextView) window
				.findViewById(R.id.description);
		if (description != null) {
			String desc = description.replace("#", "\r\n");
			descriptiontv.setMovementMethod(ScrollingMovementMethod
					.getInstance());
			descriptiontv.setText(desc);
		}
		Button sure = (Button) window.findViewById(R.id.sure);
		sure.setText("立即更新");
		sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				alertDialog.dismiss();
				if (lastestUrl != null && lastestUrl.length() > 0) {
					// the service will give keyword to check,how to do;
					if (type == 0) {
						// 根据服务端返回的参数，决定跳转位置
						// 打开对应应用市场
						String channel = APP.getInstance().getChannelName();
						if ("华为应用市场".equals(channel)) {
							try {
								// 跳转到华为应用市场
								Intent intent = new Intent();
								intent.setAction("com.huawei.appmarket.intent.action.AppDetail");
								intent.putExtra("APP_PACKAGENAME",
										"com.ytdinfo.keephealth");
								context.startActivity(intent);
							} catch (Exception e) {
								Intent it = new Intent(Intent.ACTION_VIEW, Uri
										.parse(lastestUrl));
								it.setClassName("com.android.browser",
										"com.android.browser.BrowserActivity");
								context.startActivity(it);
							}
						} else if ("魅族应用市场".equals(channel)) {
							// 跳转到魅族应用市场
							try {
								// 跳转到魅族应用市场
								Intent intent = new Intent();
								intent.setAction("android.intent.action.VIEW");
								intent.setData(Uri
										.parse("market://search?q=pname:com.ytdinfo.keephealth"));
								context.startActivity(intent);
							} catch (Exception e) {
								Intent it = new Intent(Intent.ACTION_VIEW, Uri
										.parse(lastestUrl));
								it.setClassName("com.android.browser",
										"com.android.browser.BrowserActivity");
								context.startActivity(it);
							}
						}

					} else if (type == 1) {
						// 启动本地下载
						gotoDownLoadFile(lastestUrl, desMd5, version);
					} else {
						// 使用网页打开
						Intent it = new Intent(Intent.ACTION_VIEW, Uri
								.parse(lastestUrl));
						it.setClassName("com.android.browser",
								"com.android.browser.BrowserActivity");
						context.startActivity(it);
					}
				}
				if (isQZupdate) {
					((Activity) context).finish();
				}
			}
		});

		Button notsure = (Button) window.findViewById(R.id.notsure);
		notsure.setText("暂不更新");
		if (isQZupdate) {
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
					SharePreferHelp.putValue(ConsMVP.NOTUPDATE.getDec(),
							calendar.get(Calendar.DAY_OF_YEAR));
				}
			});
		}
	}

	/**
	 * 上午11:14:42
	 * 
	 * @author zhangyh2 TODO 开始自动版本更新
	 */
	public void handUpdateVersion() {

		// TODO Auto-generated method stub
		biz.versionUpdate(new BaseListener<String>() {

			@Override
			public void onSucces(String result) {
				// TODO Auto-generated method stub
				parseJson(result);
			}

			@Override
			public void onFaild(int code, String info) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 上午10:01:39
	 * 
	 * @author zhangyh2 TODO 解析数据
	 */
	private void parseJson(String jsonStr) {
		if (jsonStr == null) {
			return;
		}
		BaseModel<VersionModel> data = new BaseModelPaser<VersionModel>()
				.paserJson(jsonStr, new TypeToken<VersionModel>() {
				});
		if (data != null) {
			if (data.getCode() == 200 && data.getData() != null) {
				// 进行更新提示
				if (!TextUtils.isEmpty(data.getData().getUrl())) {
					int versionCode = APP.getInstance().getVersionCode();
					if (data.getData().getVersion() - versionCode > 0) {
						LogApp.i(TAG, "弹窗提示更新");
						showUpdate(data.getData().getUrl(), data.getData()
								.getCompareStr(), data.getData().getVersion(),
								data.getData().getDownloadType(), data
										.getData().getDescription());
					} else {
						LogApp.i(TAG, "弹窗提示已经是最新版本");
						if (heightVersion != null && heightVersion.isShowing()) {
							return;
						}
						heightVersion = new AlertDialog.Builder(context)
								.create();
						heightVersion.show();
						WindowManager.LayoutParams params = heightVersion
								.getWindow().getAttributes();
						params.width = (int) (ConsMVP.WIDTH.getLen() * 0.86);
						heightVersion.getWindow().setAttributes(params);
						heightVersion.setCanceledOnTouchOutside(false);
						Window window = heightVersion.getWindow();
						window.setContentView(R.layout.update);// 设置对话框的布局
						TextView descripttitle = (TextView) window
								.findViewById(R.id.description_title);
						descripttitle.setText("提示");
						TextView descriptiontv = (TextView) window
								.findViewById(R.id.description);
						descriptiontv.setText("当前版本已是最新版本！");
						Button sure = (Button) window.findViewById(R.id.sure);
						sure.setText("我知道了");
						sure.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								heightVersion.dismiss();
							}
						});
						Button notsure = (Button) window
								.findViewById(R.id.notsure);
						notsure.setVisibility(View.GONE);
					}
				} else {
					// url为空不进行更新提示
				}
			} else {
				// 服务端返回失败不进行更新提示
			}
		} else {
			// 解析数据为空不进行更新提示
		}
	}

	/**
	 * 上午10:04:51
	 * 
	 * @author zhangyh2 TODO 弹出对话框，是否升级解决方案
	 */
	protected void showUpdate(final String lastestUrl, final String desMd5,
			final int version, final int type, String description) {
		if (alertDialog != null && alertDialog.isShowing()) {
			return;
		}
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		WindowManager.LayoutParams params = alertDialog.getWindow()
				.getAttributes();
		params.width = (int) (ConsMVP.WIDTH.getLen() * 0.86);
		alertDialog.getWindow().setAttributes(params);
		alertDialog.setCanceledOnTouchOutside(false);
		Window window = alertDialog.getWindow();
		window.setContentView(R.layout.update);// 设置对话框的布局
		TextView descripttitle = (TextView) window
				.findViewById(R.id.description_title);
		descripttitle.setText("版本升级");
		TextView descriptiontv = (TextView) window
				.findViewById(R.id.description);
		if (description != null) {
			String desc = description.replace("#", "\r\n");
			descriptiontv.setMovementMethod(ScrollingMovementMethod
					.getInstance());
			descriptiontv.setText(desc);
		}
		Button sure = (Button) window.findViewById(R.id.sure);
		sure.setText("立即更新");
		sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				alertDialog.dismiss();
				if (lastestUrl != null && lastestUrl.length() > 0) {
					// the service will give keyword to check,how to do;
					if (type == 0) {
						// 根据服务端返回的参数，决定跳转位置
						// 打开对应应用市场
						String channel = APP.getInstance().getChannelName();
						if ("华为应用市场".equals(channel)) {
							try {
								// 跳转到华为应用市场
								Intent intent = new Intent();
								intent.setAction("com.huawei.appmarket.intent.action.AppDetail");
								intent.putExtra("APP_PACKAGENAME",
										"com.ytdinfo.keephealth");
								context.startActivity(intent);
							} catch (Exception e) {
								Intent it = new Intent(Intent.ACTION_VIEW, Uri
										.parse(lastestUrl));
								it.setClassName("com.android.browser",
										"com.android.browser.BrowserActivity");
								context.startActivity(it);
							}
						} else if ("魅族应用市场".equals(channel)) {
							// 跳转到魅族应用市场
							try {
								// 跳转到魅族应用市场
								Intent intent = new Intent();
								intent.setAction("android.intent.action.VIEW");
								intent.setData(Uri
										.parse("market://search?q=pname:com.ytdinfo.keephealth"));
								context.startActivity(intent);
							} catch (Exception e) {
								Intent it = new Intent(Intent.ACTION_VIEW, Uri
										.parse(lastestUrl));
								it.setClassName("com.android.browser",
										"com.android.browser.BrowserActivity");
								context.startActivity(it);
							}
						}
					} else if (type == 1) {
						// 启动本地下载
						gotoDownLoadFile(lastestUrl, desMd5, version);
					} else {
						// 使用网页打开
						Intent it = new Intent(Intent.ACTION_VIEW, Uri
								.parse(lastestUrl));
						it.setClassName("com.android.browser",
								"com.android.browser.BrowserActivity");
						context.startActivity(it);
					}
				}
			}
		});

		Button notsure = (Button) window.findViewById(R.id.notsure);
		notsure.setText("暂不更新");
		notsure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.dismiss();
			}
		});
	}

	/**
	 * 上午11:23:05
	 * 
	 * @author zhangyh2 TODO 用户为使用WIFI，使用4G给用户提示。
	 */
	private void gotoDownLoadFile(final String lastestUrl, final String desMd5,
			final int version) {
		if (isWifi()) {
			toServiceDown(context, lastestUrl, desMd5, version);
		} else {
			// dialog to see

			if (isinWifi != null && isinWifi.isShowing()) {
				return;
			}
			isinWifi = new AlertDialog.Builder(context).create();
			isinWifi.setOnKeyListener(new OnKeyListener() {

				@Override
				public boolean onKey(DialogInterface dialog, int keyCode,
						KeyEvent event) {
					// TODO Auto-generated method
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						isinWifi.dismiss();
					}
					return false;
				}
			});
			isinWifi.show();
			WindowManager.LayoutParams params = isinWifi.getWindow()
					.getAttributes();
			params.width = (int) (ConsMVP.WIDTH.getLen() * 0.86);
			isinWifi.getWindow().setAttributes(params);
			isinWifi.setCanceledOnTouchOutside(false);
			Window window = isinWifi.getWindow();
			window.setContentView(R.layout.update);// 设置对话框的布局
			TextView descripttitle = (TextView) window
					.findViewById(R.id.description_title);
			descripttitle.setText("提示");
			TextView descriptiontv = (TextView) window
					.findViewById(R.id.description);
			descriptiontv.setText("非WiFi环境下更新版本将产生流量费用，确定继续？");
			Button sure = (Button) window.findViewById(R.id.sure);
			sure.setText("下载");
			sure.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					isinWifi.dismiss();
					Intent intent = new Intent(context, UpdateService.class);
					intent.putExtra("url", lastestUrl);
					intent.putExtra("version", version);
					intent.putExtra("desMd5", desMd5);
					context.startService(intent);
				}
			});

			Button notsure = (Button) window.findViewById(R.id.notsure);
			notsure.setText("取消");
			isinWifi.setCancelable(true);
			notsure.setVisibility(View.VISIBLE);
			notsure.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					isinWifi.dismiss();
				}
			});
		}
	}

	/**
	 * make true current connect service is wifi
	 * 
	 * @param mContext
	 * @return
	 */
	private boolean isWifi() {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	/**
	 * 下午4:15:17
	 * 
	 * @author zhangyh2 TODO take go to download file;
	 */
	private void toServiceDown(Context context, String url, String desMd5,
			int version) {
		Intent intent = new Intent(context, UpdateService.class);
		intent.putExtra("url", url);
		intent.putExtra("version", version);
		intent.putExtra("desMd5", desMd5);
		context.startService(intent);
	}

}
