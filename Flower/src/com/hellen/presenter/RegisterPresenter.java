/**下午2:46:49
 * @author zhangyh2
 * RegisterPresenter.java
 * TODO
 */
package com.hellen.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.hellen.base.ConsMVP;
import com.hellen.biz.RegisterInterface;
import com.hellen.biz.RegisterInterface.RegisterBiz;
import com.hellen.biz.imp.Register;
import com.hellen.common.LogApp;
import com.hellen.flower.R;
import com.hellen.flower.login.SetPassActivity;

/**
 * @author zhangyh2 RegisterPresenter 下午2:46:49 TODO
 */
public class RegisterPresenter {

	private String tag = getClass().getSimpleName();

	private RegisterBiz biz;

	private RegisterInterface view;

	public RegisterPresenter(RegisterInterface views) {
		super();
		this.view = views;
		this.biz = new Register();
		view.setPersenter(biz);
		view.setView();
	}

	public void setTimer() {
		LogApp.i(tag, "setTimer");
	}

	public void onClickDown(Context context, View v) {
		switch (v.getId()) {
		case R.id.act_register_register:
			Intent intent = new Intent(context, SetPassActivity.class);
			intent.putExtra("tel", view.findTel());
			context.startActivity(intent);
			view.end();
			break;
		case R.id.view_header_iv:
			view.end();
			break;
		case R.id.act_register_clickcode:
			view.disClicked();
			handler.sendEmptyMessage(ConsMVP.DB_SELECT);
			break;

		default:
			break;
		}
	}

	private int time = 60;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case ConsMVP.DB_SELECT:
				// 点击获取验证码，修改显示文字。
				if (time > 0) {
					view.gtoText(time + "s");
					handler.sendEmptyMessageDelayed(ConsMVP.DB_INSERT, 1000);
				} else {
					view.gtoText(R.string.register_code_two);
					view.enableClicked();
					time = 60;
				}
				break;
			case ConsMVP.DB_INSERT:
				time--;
				handler.sendEmptyMessage(ConsMVP.DB_SELECT);
				break;

			default:
				break;
			}
		}

	};
}
