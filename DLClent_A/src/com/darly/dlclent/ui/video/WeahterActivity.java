package com.darly.dlclent.ui.video;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.model.Weather;
import com.darly.dlclent.model.WeatherInfo;
import com.darly.dlclent.widget.gif.GifView;
import com.darly.dlclent.widget.load.ProgressDialogUtil;
import com.darly.dlclent.widget.snow.FlowerView;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_weather)
public class WeahterActivity extends BaseActivity implements OnClickListener {

	// http://wthrcdn.etouch.cn/weather_mini?city=北京
	// 通过城市名字获得天气数据，json数据
	// http://wthrcdn.etouch.cn/weather_mini?citykey=101010100
	// 通过城市id获得天气数据，json数据

	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	public static String[] citycode;
	@ViewInject(R.id.weather_bg)
	private RelativeLayout bg;
	@ViewInject(R.id.weather_flower)
	private FlowerView flowerView;
	@ViewInject(R.id.weather_gif)
	private GifView gifView;
	@ViewInject(R.id.weather_city)
	private TextView weather_city;
	@ViewInject(R.id.weather_isRadar)
	private TextView weather_isRadar;
	@ViewInject(R.id.weather_SD)
	private TextView weather_SD;
	@ViewInject(R.id.weather_temp)
	private TextView weather_temp;
	@ViewInject(R.id.weather_time)
	private TextView weather_time;

	@ViewInject(R.id.weather_WSE)
	private TextView weather_WSE;
	@ViewInject(R.id.weather_WD)
	private TextView weather_WD;
	@ViewInject(R.id.weather_WS)
	private TextView weather_WS;

	private ProgressDialogUtil loading;

	private String url;

	// 使用正则表达式进行截取Value
	private static String getValue(String str, String reg) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		return m.replaceAll("").toString().trim();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		title.setText("天气展示");
		back.setVisibility(View.VISIBLE);
		other.setVisibility(View.INVISIBLE);

		citycode = getResources().getStringArray(R.array.citycode);
		loading = new ProgressDialogUtil(this);
		gifView.setLayoutParams(new LayoutParams(APPEnum.WIDTH.getLen(),
				APPEnum.WIDTH.getLen()));
		gifView.setMovieResource(R.drawable.zhen);
		flowerView.setNum(100);
		flowerView.setWH(APPEnum.WIDTH.getLen(), APPEnum.HEIGHT.getLen(), 50);
		flowerView.loadFlower(R.drawable.personal_msg_tab_background);
		flowerView.addRect();

		String city = citycode[new Random().nextInt(citycode.length - 1)];
		String cityID = getValue(city, "[^0-9]");
		Log.i(city, cityID);
		// 假如要在这里请求数据。
		url = "http://www.weather.com.cn/data/sk/" + cityID + ".html";
		loading.setMessage(R.string.xlistview_header_hint_loading);
		loading.show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

		HttpClient.get(this, url, new RequestParams(),
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						loading.dismiss();

						if (arg0.result == null) {
							return;
						}
						WeatherInfo info = new Gson().fromJson(arg0.result,
								WeatherInfo.class);
						Weather weather = info.weatherinfo;
						weather_city.setText(weather.city);
						weather_isRadar.setText(weather.isRadar);
						weather_SD.setText(weather.SD);
						weather_temp.setText(weather.temp);
						weather_time.setText(weather.time);
						weather_WD.setText(weather.WD);
						weather_WS.setText(weather.WS);
						weather_WSE.setText(weather.WSE);

						bg.setBackgroundResource(R.drawable.ic_guide_four_bg);
						flowerView.loadFlower(R.drawable.ic_banner_button);
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						loading.dismiss();
					}
				});

		Timer timer = new Timer();
		// 计时器。从0开始，每过500MS进行一次。
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				he.sendEmptyMessage(0);
			}
		}, 0, 100);
	}

	@SuppressLint("HandlerLeak")
	private Handler he = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			flowerView.inva();

		};
	};

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
