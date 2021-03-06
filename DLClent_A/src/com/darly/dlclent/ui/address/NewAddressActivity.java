/**下午3:13:41
 * @author zhangyh2
 * NewAddressActivity.java
 * TODO
 */
package com.darly.dlclent.ui.address;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.PaserProvice;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.db.DBUtilsHelper;
import com.darly.dlclent.model.AddressModel;
import com.darly.dlclent.model.ProvinceModel;
import com.darly.dlclent.model.UserAddress;
import com.darly.dlclent.widget.clearedit.ClearEditText;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 NewAddressActivity 下午3:13:41 TODO 新增地址页面。 修改地址页面。
 */
@ContentView(R.layout.activity_newaddress)
public class NewAddressActivity extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_btn)
	private Button btn;

	private boolean newAddress;

	private List<ProvinceModel> province;

	@ViewInject(R.id.new_addr_name)
	private ClearEditText name;
	@ViewInject(R.id.new_addr_tel)
	private ClearEditText tel;
	@ViewInject(R.id.new_addr_code)
	private TextView code;
	@ViewInject(R.id.new_addr_addr)
	private TextView addr;

	private ProvinceCity popCity;

	private View view;

	private UserAddress address;

	private AddressModel admodel;

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

		admodel = (AddressModel) getIntent().getSerializableExtra(
				"CHAGEADDRESS");
		if (admodel != null) {
			address = new UserAddress(admodel.getProvince(), admodel.getCity(),
					admodel.getDistrict(), admodel.getZipcode());
			name.setText(admodel.getName());
			tel.setText(admodel.getTel());
			addr.setText(admodel.getProvince() + admodel.getCity()
					+ admodel.getDistrict());
			code.setText(admodel.getZipcode());
		} else {
			name.setHint("请输入姓名");
			tel.setHint("请输入手机号码");
			addr.setHint("请选择地区");
			code.setHint("请输入区号");
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
		try {
			province = PaserProvice.paserPro(getResources().getAssets().open(
					"province_data.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		btn.setOnClickListener(this);
		addr.setClickable(true);
		addr.setOnClickListener(this);
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
		case R.id.header_btn:
			// 保存。
			saveAddress();
			finish();
			break;
		case R.id.new_addr_addr:
			// 点击出现选轮。
			((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(this.getCurrentFocus()
							.getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
			if (address == null) {
				popCity = new ProvinceCity(this, province);
			} else {
				popCity = new ProvinceCity(this, province, address);
			}

			view = popCity.getPOPView();
			view.findViewById(R.id.id_submit).setOnClickListener(this);
			break;
		case R.id.id_submit:
			address = getAddress();
			popCity.pop.dismiss();
			addr.setText(address.getProvince() + address.getCity()
					+ address.getDistrict());
			code.setText(address.getZipcode());
			break;
		default:
			break;
		}
	}

	/**
	 * 上午11:10:15
	 * 
	 * @author zhangyh2 TODO 保存新增或者修改的地址。
	 */
	private void saveAddress() {
		// TODO Auto-generated method stub
		String sName = name.getText().toString().trim();
		if (sName == null || sName.length() == 0) {
			ToastApp.showToast("请输入姓名");
			return;
		}
		String sTel = tel.getText().toString().trim();
		if (sTel == null || sTel.length() != 11) {
			ToastApp.showToast("请输入正确的手机号");
			return;
		}
		if (address == null) {
			ToastApp.showToast("请选择省市区");
			return;
		}
		String id = "";
		if (admodel != null) {
			id = admodel.getId();
		}
		if (!APP.isNetworkConnected(this)) {
			ToastApp.showToast(R.string.neterror);
			return;
		} else {
			// 缺少上传服务器请求代码块
			JSONObject ob = new JSONObject();
			try {
				if (newAddress) {
					ob.put("flag", "0");
				} else {
					ob.put("flag", "1");
				}
				ob.put("_id", id);
				ob.put("id", id);
				ob.put("name", sName);
				ob.put("tel", sTel);
				ob.put("province", address.getProvince());
				ob.put("city", address.getCity());
				ob.put("district", address.getDistrict());
				ob.put("zipcode", address.getZipcode());
				ob.put("usertel", SharePreferHelp.getValue(
						APPEnum.USERTEL.getDec(), null));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpClient.post(ConsHttpUrl.USERADDRESS, ob.toString(), null);
		}

		AddressModel model = new AddressModel(id, sName, sTel,
				address.getProvince(), address.getCity(),
				address.getDistrict(), address.getZipcode());
		try {
			DBUtilsHelper.getInstance().getDb()
					.createTableIfNotExist(AddressModel.class);
			Intent intent = new Intent(this, AddressActivity.class);
			intent.putExtra("AddressModel", model);
			if (newAddress) {
				// 新增地址
				setResult(APPEnum.ADDRESS_NEW, intent);
				DBUtilsHelper.getInstance().getDb().save(model);
			} else {
				// 修改地址
				setResult(APPEnum.ADDRESS_CHA, intent);
				DBUtilsHelper.getInstance().getDb().update(model);
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 上午10:57:23
	 * 
	 * @author zhangyh2 TODO 从选中的省市区，生成UserAddress对象
	 */
	private UserAddress getAddress() {
		String province = null;
		String city = null;
		String district = null;
		String zipcode = null;
		StringBuilder data = new StringBuilder();
		if (popCity.mCurrentProviceName.contains("+")) {
			data.append(popCity.mCurrentProviceName);
			province = popCity.mCurrentProviceName.substring(0,
					popCity.mCurrentProviceName.lastIndexOf("+"));
			zipcode = popCity.mCurrentProviceName.substring(
					popCity.mCurrentProviceName.lastIndexOf("+") + 1,
					popCity.mCurrentProviceName.length());
		} else {
			province = popCity.mCurrentProviceName;
			if (popCity.mCurrentCityName.contains("+")) {
				data.append(popCity.mCurrentProviceName
						+ popCity.mCurrentCityName);
				city = popCity.mCurrentCityName.substring(0,
						popCity.mCurrentCityName.lastIndexOf("+"));
				zipcode = popCity.mCurrentCityName.substring(
						popCity.mCurrentCityName.lastIndexOf("+") + 1,
						popCity.mCurrentCityName.length());
			} else {
				data.append(popCity.mCurrentProviceName
						+ popCity.mCurrentCityName + popCity.mCurrentAreaName);
				city = popCity.mCurrentCityName;
				district = popCity.mCurrentAreaName.substring(0,
						popCity.mCurrentAreaName.lastIndexOf("+"));
				zipcode = popCity.mCurrentAreaName.substring(
						popCity.mCurrentAreaName.lastIndexOf("+") + 1,
						popCity.mCurrentAreaName.length());
			}
		}
		LogUtils.i(data.toString());
		return new UserAddress(province, city, district, zipcode);
	}

}
