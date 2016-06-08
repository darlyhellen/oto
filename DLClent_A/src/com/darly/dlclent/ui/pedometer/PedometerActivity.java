/**下午2:43:49
 * @author zhangyh2
 * PedometerActivity.java
 * TODO
 */
package com.darly.dlclent.ui.pedometer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.widget.circularload.FitChart;
import com.darly.dlclent.widget.circularload.FitChartValue;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.comm.core.CommunitySDK;
import com.umeng.comm.core.impl.CommunityFactory;

/**
 * @author zhangyh2 PedometerActivity 下午2:43:49 TODO
 */
@ContentView(R.layout.activity_pedometer)
public class PedometerActivity extends BaseActivity implements OnClickListener {

	private String TAG = getClass().getSimpleName();

	@ViewInject(R.id.fitChart)
	private FitChart fit;

	@ViewInject(R.id.add)
	private Button add;

	@ViewInject(R.id.stop)
	private Button stop;

	@ViewInject(R.id.step_text)
	private TextView step;

	@ViewInject(R.id.step_oldtext)
	private TextView oldstep;

	@ViewInject(R.id.pedometer_te)
	private TextView pedo;

	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	private int oldStep = 100;// 以往最大数值

	private int newStep = 0;

	private Timer timer;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			newStep = StepDetector.CURRENT_SETP;
			step.setText(newStep + " steps for now!");
			Collection<FitChartValue> values = new ArrayList<FitChartValue>();
			values.add(new FitChartValue(newStep, getResources()
					.getColor(R.color.chart_value_2)));
			fit.setValues(values);
			if (newStep > oldStep) {
				pedo.setText("您已经超越自己" + (newStep - oldStep) + "步");
			}
		}

	};

	// 判断是否是在今天每次打开页面，对比日期。数字不同则重新赋值，数字相同的条件下，

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		if (day != SharePreferHelp.getValue(TAG, 0)) {
			SharePreferHelp.putValue(TAG, day);
			SharePreferHelp.putValue(TAG + "newStep", 0);// 新的一天新基数
			LogUtils.i(day + "保存的日期和今天不同");
		}
		back.setVisibility(View.VISIBLE);
		other.setVisibility(View.INVISIBLE);
		title.setText("计步器");

		// 开启服务，后台执行。
		Intent intent = new Intent(this, StepService.class);
		startService(intent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		if (SharePreferHelp.getValue(TAG + "oldStep", 0) < 100) {
			oldStep = 100;// 设置保存数据的最大数字
		} else {
			oldStep = SharePreferHelp.getValue(TAG + "oldStep", 0);// 设置保存数据的最大数字
		}
		fit.setMinValue(0f);
		fit.setMaxValue(oldStep);
		if (SharePreferHelp.getValue(TAG + "newStep", 0) > 0) {
			step.setText(SharePreferHelp.getValue(TAG + "newStep", 0)
					+ " steps for now!");
			Collection<FitChartValue> values = new ArrayList<FitChartValue>();
			values.add(new FitChartValue(SharePreferHelp.getValue(TAG
					+ "newStep", 0), getResources().getColor(
					R.color.chart_value_2)));
			fit.setValues(values);
		}
		oldstep.setText("Your goal is " + oldStep + " steps.");
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
		add.setVisibility(View.VISIBLE);
		add.setOnClickListener(this);
		stop.setVisibility(View.INVISIBLE);
		startWalk();
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
		case R.id.add:
			//开启微社区
			// 获取CommunitySDK实例, 参数1为Context类型
			CommunitySDK mCommSDK = CommunityFactory.getCommSDK(this);
			// 打开微社区的接口, 参数1为Context类型
			mCommSDK.openCommunity(this);
			break;
		default:
			break;
		}
	}

	/**
	 * 下午3:00:26
	 * 
	 * @author zhangyh2 TODO 开始散步方法体
	 */
	private void startWalk() {
		// 首先呢散步情况为一步一步走路，故而计步为递增情况。默认步子为上次走过的最大步子。
		newStep = SharePreferHelp.getValue(TAG + "newStep", 0);// 保存的数值
		LogUtils.i(newStep + "新旧步子" + oldStep);
		timer = new Timer();
		fit.setMaxValue(oldStep);// 设置最大值
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (StepService.flag) {
					// newStep++;
					handler.sendEmptyMessage(0);
				}
			}
		}, 0, 500);
	}

	/**
	 * 下午3:10:32
	 * 
	 * @author zhangyh2 TODO 停止散步，停止计时
	 */
	private void stopWalk() {
		// TODO Auto-generated method stub
		if (timer != null) {
			timer.cancel();
			SharePreferHelp.putValue(TAG + "newStep", newStep);// 更新newStep
			// 更新最大数值
			if (newStep > SharePreferHelp.getValue(TAG + "oldStep", 0)) {
				SharePreferHelp.putValue(TAG + "oldStep", newStep);
				oldstep.setText("Your goal is " + newStep + " steps.");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#finish()
	 */
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		stopWalk();
		super.finish();
	}
}
