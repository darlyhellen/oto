/**下午3:13:41
 * @author zhangyh2
 * NewAddressActivity.java
 * TODO
 */
package com.darly.dlclent.ui.address;

import java.io.IOException;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.PaserProvice;
import com.darly.dlclent.model.CityModel;
import com.darly.dlclent.model.DistrictModel;
import com.darly.dlclent.model.ProvinceModel;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 NewAddressActivity 下午3:13:41 TODO 新增地址页面。 修改地址页面。
 */
@ContentView(R.layout.activity_newaddress)
public class NewAddressActivity extends BaseActivity {
	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_btn)
	private Button btn;

	private boolean newAddress;

	private List<ProvinceModel> province;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		newAddress = getIntent().getBooleanExtra("NewAddressActivity", false);
		if (newAddress) {
			// 新增地址
			title.setText("新增地址");
		} else {
			// 修改地址
			title.setText("修改地址");
		}
		back.setVisibility(View.VISIBLE);

		btn.setVisibility(View.VISIBLE);

		btn.setText("保存");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

		try {
			province = PaserProvice.paserPro(getResources().getAssets().open(
					"province_data.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (ProvinceModel provinceModel : province) {
			LogUtils.i(provinceModel.getName());
			for (CityModel i : provinceModel.getCity()) {
				LogUtils.i(i.getName());
				for (DistrictModel t : i.getDistrict()) {
					LogUtils.i(t.getName() + t.getZipcode() + "区");
				}
			}
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

	}

}
