package com.darly.dlclent.ui;

import java.util.Calendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.JsonUtil;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.CheckUpdata;
import com.darly.dlclent.model.CheckUpdataBase;
import com.darly.dlclent.service.UpdateService;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		checkVerson();
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
								ToastApp.showToast(R.string.neterror);
							}
						});
			} else {
				CheckUpdata re = new CheckUpdata("", 8, "", true);
				CheckUpdataBase da = new CheckUpdataBase(200, "", re);
				String jsonString = JsonUtil.pojo2Json(da);
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
		CheckUpdataBase check = new Gson().fromJson(datas,
				CheckUpdataBase.class);

		if (check.code == 200) {
			int versionCode = APP.getInstance().getVersionCode();
			if (check.data.version != 0 && check.data.version - versionCode > 0) {
				// 需要更新
				showUpdateDialog(check.data);
			}
		} else {
			ToastApp.showToast(check.msg);
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

}
