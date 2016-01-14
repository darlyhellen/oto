/**上午9:47:17
 * @author zhangyh2
 * ProvinceCity.java
 * TODO
 */
package com.darly.dlclent.ui.address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.darly.dlclent.R;
import com.darly.dlclent.model.CityModel;
import com.darly.dlclent.model.DistrictModel;
import com.darly.dlclent.model.ProvinceModel;
import com.darly.dlclent.model.UserAddress;
import com.darly.dlclent.widget.wheel.OnWheelChangedListener;
import com.darly.dlclent.widget.wheel.WheelView;
import com.darly.dlclent.widget.wheel.adapter.ArrayWheelAdapter;

/**
 * @author zhangyh2 ProvinceCity 上午9:47:17 TODO
 */
public class ProvinceCity implements OnWheelChangedListener {

	private View view;
	private Context context;
	public PopupWindow pop;
	/**
	 * 省的WheelView控件
	 */
	private WheelView province;
	/**
	 * 市的WheelView控件
	 */
	private WheelView city;
	/**
	 * 区的WheelView控件
	 */
	private WheelView area;
	/**
	 * 所有省
	 */
	private String[] mProvinceDatas;
	/**
	 * key - 省 value - 市s
	 */
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	/**
	 * key - 市 values - 区s
	 */
	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();
	/**
	 * 当前省的名称
	 */
	public String mCurrentProviceName;
	/**
	 * 当前市的名称
	 */
	public String mCurrentCityName;
	/**
	 * 当前区的名称
	 */
	public String mCurrentAreaName = "";

	private int _prove = 0;
	private int _city = 0;
	private int _district = 0;

	/**
	 * 上午10:02:29 TODO 传递进来的省市区所有参数。
	 */
	private List<ProvinceModel> provinceList;

	/**
	 * 上午10:12:39 TODO 用户当前的地址
	 */
	private UserAddress address;

	public ProvinceCity(Context context, List<ProvinceModel> provinceList) {
		this.context = context;
		this.provinceList = provinceList;
	}

	public ProvinceCity(Context context, List<ProvinceModel> provinceList,
			UserAddress address) {
		this.context = context;
		this.provinceList = provinceList;
		this.address = address;
	}

	public View getPOPView() {
		view = LayoutInflater.from(context).inflate(R.layout.wheel_view, null,
				false);
		initView();
		initData();
		initListener();
		return view;
	}

	/**
	 * 上午9:48:40
	 * 
	 * @author zhangyh2 TODO 初始化控件
	 */
	private void initView() {
		// TODO Auto-generated method stub
		pop = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, true);
		// 设置动画效果
		// pop.setAnimationStyle(R.style.AnimationFade);
		// 这里是位置显示方式,在屏幕的左侧
		pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);

		province = (WheelView) view.findViewById(R.id.id_province);
		city = (WheelView) view.findViewById(R.id.id_city);
		area = (WheelView) view.findViewById(R.id.id_area);

		// */ 初始化默认选中的省、市、区
		if (provinceList != null && !provinceList.isEmpty()) {
			if (address == null) {
				mCurrentProviceName = provinceList.get(0).getName();
				List<CityModel> cityList = provinceList.get(0).getCity();
				if (cityList != null && !cityList.isEmpty()) {
					mCurrentCityName = cityList.get(0).getName();
					List<DistrictModel> districtList = cityList.get(0)
							.getDistrict();
					if (districtList != null && !districtList.isEmpty()) {
						mCurrentAreaName = districtList.get(0).getName();
					}
				}
			} else {
				for (int i = 0; i < provinceList.size(); i++) {
					if (provinceList.get(i).getName()
							.equals(address.getProvince())) {
						_prove = i;
						mCurrentProviceName = provinceList.get(i).getName();
						List<CityModel> cityList = provinceList.get(i)
								.getCity();
						if (cityList != null && !cityList.isEmpty()) {
							for (int j = 0; j < cityList.size(); j++) {
								if (cityList.get(j).getName()
										.equals(address.getCity())) {
									_city = j;
									mCurrentCityName = cityList.get(j)
											.getName();
									List<DistrictModel> districtList = cityList
											.get(j).getDistrict();
									if (districtList != null
											&& !districtList.isEmpty()) {
										for (int k = 0; k < districtList.size(); k++) {

											if (districtList
													.get(k)
													.getName()
													.equals(address
															.getDistrict())) {
												_district = k;
												mCurrentAreaName = districtList
														.get(k).getName();
											}
										}

									}

								}
							}
						}

					}
				}

			}
		}

	}

	/**
	 * 上午9:48:44
	 * 
	 * @author zhangyh2 TODO
	 */
	private void initData() {
		// TODO Auto-generated method stub
		mProvinceDatas = new String[provinceList.size()];
		for (int i = 0; i < provinceList.size(); i++) {// 省
			ProvinceModel region = provinceList.get(i);// 北京
			mProvinceDatas[i] = region.getName();
			if (region.getCity() != null) {// 市
				String[] mCitiesDatas = new String[region.getCity().size()];
				for (int j = 0; j < region.getCity().size(); j++) {
					CityModel childRegion = region.getCity().get(j);// 北京市
					if (childRegion.getDistrict() != null) {// 区
						mCitiesDatas[j] = childRegion.getName();
						String[] mAreasDatas = new String[childRegion
								.getDistrict().size()];
						for (int k = 0; k < childRegion.getDistrict().size(); k++) {
							DistrictModel baseRegion = childRegion
									.getDistrict().get(k);// 东城区
							mAreasDatas[k] = baseRegion.getName() + "+"
									+ baseRegion.getZipcode();
						}
						mAreaDatasMap.put(childRegion.getName(), mAreasDatas);
					} else {
						mCitiesDatas[j] = childRegion.getName();

					}
				}
				mCitisDatasMap.put(region.getName(), mCitiesDatas);
			}
		}

		province.setViewAdapter(new ArrayWheelAdapter<String>(context,
				mProvinceDatas));
		province.setCurrentItem(_prove);
		_prove = 0;
		// 添加change事件
		province.addChangingListener(this);
		// 添加change事件
		city.addChangingListener(this);
		// 添加change事件
		area.addChangingListener(this);

		province.setVisibleItems(7);
		city.setVisibleItems(7);
		area.setVisibleItems(7);
		updateCities();
	}

	/**
	 * 上午9:52:01
	 * 
	 * @author zhangyh2 TODO 添加触碰事件
	 */
	private void initListener() {
		// TODO Auto-generated method stub
		view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (pop != null && pop.isShowing()) {
					pop.dismiss();
					pop = null;
				}
				return false;
			}
		});
	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private String[] areas;

	private void updateAreas() {
		int pCurrent = city.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		areas = mAreaDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
			mCurrentAreaName = "";
		} else {
			mCurrentAreaName = areas[0];
		}
		area.setViewAdapter(new ArrayWheelAdapter<String>(context, areas));
		if (areas != null) {
			area.setCurrentItem(_district);
		}
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = province.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		String[] ct = mAreaDatasMap.get(cities[0]);
		if (ct == null) {
			mCurrentAreaName = "";
		} else {
			mCurrentAreaName = ct[0];// 通过省名得到市名列表，通过收个市得到地区
		}
		city.setViewAdapter(new ArrayWheelAdapter<String>(context, cities));
		city.setCurrentItem(_city);
		updateAreas();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.darly.dlclent.widget.wheel.OnWheelChangedListener#onChanged(com.darly
	 * .dlclent.widget.wheel.WheelView, int, int)
	 */
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		// TODO Auto-generated method stub
		if (wheel == province) {
			_city = 0;
			_district = 0;
			updateCities();
		} else if (wheel == city) {
			updateAreas();
		} else if (wheel == area) {
			if (areas.length < newValue) {
				return;
			}
			mCurrentAreaName = areas[newValue]/*
											 * mAreaDatasMap.get(mCurrentCityName
											 * )[newValue]
											 */;
		}
	}

}
