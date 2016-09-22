/**上午10:58:02
 * @author zhangyh2
 * AutoNav.java
 * TODO
 */
package com.hellen.biz.imp;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.hellen.base.APP;
import com.hellen.base.BaseListener;
import com.hellen.biz.AutoNavInterface;
import com.hellen.common.LogApp;

/**
 * @author zhangyh2 AutoNav 上午10:58:02 TODO
 */
public class AutoNav implements AutoNavInterface.AutoNavBiz {

	// 声明AMapLocationClient类对象
	public AMapLocationClient mLocationClient = null;

	// 声明mLocationOption对象
	public AMapLocationClientOption mLocationOption = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BasePresenter#onStart()
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		LogApp.i(getClass().getSimpleName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hellen.biz.AutoNavInterface.AutoNavPresenter#location(com.hellen.
	 * biz.AutoNavInterface.AutoNavListener)
	 */
	@Override
	public void location(final BaseListener<AMapLocation> listener) {
		// TODO Auto-generated method stub
		// 初始化定位
		mLocationClient = new AMapLocationClient(APP.getInstance());
		// 初始化定位参数
		mLocationOption = new AMapLocationClientOption();
		// 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
		mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
		// 设置是否返回地址信息（默认返回地址信息）
		mLocationOption.setNeedAddress(true);
		// 设置是否只定位一次,默认为false
		mLocationOption.setOnceLocation(true);

		if (mLocationOption.isOnceLocationLatest()) {
			mLocationOption.setOnceLocationLatest(true);
			// 设置setOnceLocationLatest(boolean
			// b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
			// 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会。
		}
		// 设置是否强制刷新WIFI，默认为强制刷新
		mLocationOption.setWifiActiveScan(true);
		// 设置是否允许模拟位置,默认为false，不允许模拟位置
		mLocationOption.setMockEnable(true);
		// 设置定位间隔,单位毫秒,默认为2000ms
		mLocationOption.setInterval(2000);
		// 给定位客户端对象设置定位参数
		mLocationClient.setLocationOption(mLocationOption);
		// 启动定位
		mLocationClient.startLocation();
		// 设置定位回调监听
		mLocationClient.setLocationListener(new AMapLocationListener() {
			@Override
			public void onLocationChanged(AMapLocation amapLocation) {
				// TODO Auto-generated method stub
				if (amapLocation != null) {
					if (amapLocation.getErrorCode() == 0) {
						listener.onSucces(amapLocation);
						// 定位成功回调信息，设置相关消息
						amapLocation.getLocationType();// 获取当前定位结果来源，如网络定位结果，详见定位类型表
						amapLocation.getLatitude();// 获取纬度
						amapLocation.getLongitude();// 获取经度
						amapLocation.getAccuracy();// 获取精度信息
						amapLocation.getAddress();// 地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
						amapLocation.getCountry();// 国家信息
						amapLocation.getProvince();// 省信息
						amapLocation.getCity();// 城市信息
						amapLocation.getDistrict();// 城区信息
						amapLocation.getStreet();// 街道信息
						amapLocation.getStreetNum();// 街道门牌号信息
						amapLocation.getCityCode();// 城市编码
						amapLocation.getAdCode();// 地区编码
						amapLocation.getAoiName();// 获取当前定位点的AOI信息
					} else {
						// 显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
						listener.onFaild(amapLocation.getErrorCode(),
								amapLocation.getErrorInfo());
						LogApp.d("AmapError" + "location Error, ErrCode:"
								+ amapLocation.getErrorCode() + ", errInfo:"
								+ amapLocation.getErrorInfo());
					}
				}
			}
		});
	}

}
