/**下午4:07:12
 * @author zhangyh2
 * VersionActivity.java
 * TODO
 */
package com.darly.dlclent.ui;

import java.util.Random;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.JsonUtil;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.CheckUpdata;
import com.darly.dlclent.service.UpdateService;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 VersionActivity 下午4:07:12 TODO 版本更新页面
 */
@ContentView(R.layout.activity_version)
public class VersionActivity extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;
	@ViewInject(R.id.version_tv)
	private TextView tv;
	@ViewInject(R.id.version_ver)
	private TextView ver;
	@ViewInject(R.id.version_msg)
	private TextView msg;

	private AlertDialog alertDialog;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		title.setText("版本更新");
		back.setVisibility(View.VISIBLE);
		other.setVisibility(View.INVISIBLE);
		tv.setText("温馨提示！");
		ver.setText("您当前的版本为:" + APP.getInstance().getVersion());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		if (!APP.isNetworkConnected(this)) {
			ToastApp.showToast(R.string.neterror);
			return;
		}
		String url = "";

		if (url != null && url.length() > 0) {
			RequestParams params = new RequestParams();
			HttpClient.get(this, url, params, new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> result) {
					isUpdata(result.result.toString());
				}

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					ToastApp.showToast(R.string.neterror_norespanse);
				}
			});
		} else {
			// 假数据
			String jsonString = null;
			if (new Random().nextBoolean()) {
				CheckUpdata re = new CheckUpdata(
						"http://gdown.baidu.com/data/wisegame/d3fb5622fe942850/QQ_320.apk",
						4,
						"版本还行，\r\n弄个测试作为全职PHPer偶尔需要客串下Androider,最近公司的一个项目需要Android的客户端\r\n(主要图片特效处理及其上传)，自己就客串下Androider.之前有过Android开发经验所以做这个挺顺手的，\r\n几乎所有东西直接github中拿过来改改就用，不过在处理图片上传的时候选择了xUtils这个",
						false);

				BaseModel<CheckUpdata> da = new BaseModel<CheckUpdata>(200, "",
						re);
				jsonString = JsonUtil.pojo2Json(da);
			} else {
				BaseModel<CheckUpdata> da = new BaseModel<CheckUpdata>(110,
						"恭喜您，您使用的是最新版本！", null);
				jsonString = JsonUtil.pojo2Json(da);
			}
			LogUtils.i(jsonString);
			isUpdata(jsonString);
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
		LogUtils.i(datas);
		BaseModel<CheckUpdata> data = new BaseModelPaser<CheckUpdata>()
				.paserJson(datas, new TypeToken<CheckUpdata>() {
				});
		if (data != null && data.getCode() == 200) {
			LogUtils.i(data.toString());
			int versionCode = APP.getInstance().getVersionCode();
			if (data.getData().version != 0
					&& data.getData().version - versionCode > 0) {
				// 需要更新
				showUpdateDialog(data.getData());
			}
		} else {
			tv.setText("温馨提示！");
			ver.setText("您当前的版本为:" + APP.getInstance().getVersion());
			msg.setText(data.getMsg());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		back.setOnClickListener(this);
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
				Intent intent = new Intent(VersionActivity.this,
						UpdateService.class);
				intent.putExtra("url", data.LastestUrl);
				startService(intent);
				LogUtils.i("UpdateService is run");
				if (data.Force) {
					System.exit(0);
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
		switch (v.getId()) {
		case R.id.header_back:
			finish();
			break;

		default:
			break;
		}
	}
}
