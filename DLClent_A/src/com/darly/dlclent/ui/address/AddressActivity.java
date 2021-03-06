/**上午11:12:19
 * @author zhangyh2
 * AddressActivity.java
 * TODO
 */
package com.darly.dlclent.ui.address;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.AddressAdapter;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.db.DBUtilsHelper;
import com.darly.dlclent.model.AddressModel;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.widget.loginout.LoginOutDialg;
import com.darly.dlclent.widget.pulltozoomlistsimple.PullToZoomListView;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 AddressActivity 上午11:12:19 TODO 地址列表功能模块。
 */
@ContentView(R.layout.activity_address)
public class AddressActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener {
	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_btn)
	private Button btn;
	@ViewInject(R.id.address_lv)
	private PullToZoomListView lv;
	@ViewInject(R.id.address_addnew)
	private Button addnew;

	private List<AddressModel> data;

	private AddressAdapter adapter;

	private boolean choseAddress;

	private int changePosition;

	private boolean btnSelect;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		title.setText("地址列表");
		back.setVisibility(View.VISIBLE);
		btn.setVisibility(View.VISIBLE);
		choseAddress = getIntent().getBooleanExtra("choseAddress", false);
		if (choseAddress) {
			// 选择地址
			btn.setText("选择");
		} else {
			// 编辑地址
			btn.setText("编辑");
		}
		addnew.setText("新增地址");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		data = new ArrayList<AddressModel>();
		adapter = new AddressAdapter(data, R.layout.item_activity_address, this);
		lv.setAdapter(adapter);

		lv.getHeaderView().setImageResource(R.drawable.ic_login_table_bg);
		lv.getHeaderView().setScaleType(ImageView.ScaleType.CENTER_CROP);

		if (!APP.isNetworkConnected(this)) {
			ToastApp.showToast(R.string.neterror);
			return;
		}
		// 进行网络请求
		RequestParams param = new RequestParams();
		param.addQueryStringParameter("page", 1 + "");
		param.addQueryStringParameter("tel",
				SharePreferHelp.getValue(APPEnum.USERTEL.getDec(), null));
		HttpClient.get(this, ConsHttpUrl.USERADDRESS, param,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						setList(arg0.result);
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub

					}
				});

	}

	/**
	 * 上午11:27:05
	 * 
	 * @author zhangyh2 TODO 设置地址列表
	 */
	private void setList(String json) {
		// TODO Auto-generated method stub
		if (json == null) {
			return;
		}
		LogUtils.i(json);
		BaseModel<List<AddressModel>> base = new BaseModelPaser<List<AddressModel>>()
				.paserJson(json, new TypeToken<List<AddressModel>>() {
				});
		if (base != null && base.getCode() == 200) {
			data = base.getData();
			adapter.setData(data);
		} else {
			ToastApp.showToast(base.getMsg());
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
		addnew.setOnClickListener(this);
		lv.setOnItemClickListener(this);
		btn.setOnClickListener(this);
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
		case R.id.address_addnew:
			// 新增地址。
			Intent intent = new Intent(this, NewAddressActivity.class);
			intent.putExtra("NewAddressActivity", true);
			startActivityForResult(intent, APPEnum.ADDRESS);
			break;
		case R.id.header_btn:
			// 进行页面编辑，还是进行页面选择
			if (choseAddress) {
				// 选择。编辑
				if (!btnSelect) {
					// 选择->编辑 ,用户点击列表进行修改。
					btn.setText("编辑");
					btnSelect = true;
				} else {
					// 编辑->选择 用户点击列表进行选择
					btn.setText("选择");
					btnSelect = false;
				}
			} else {
				// 编辑。删除
				if (!btnSelect) {
					// 编辑->删除
					btn.setText("删除");
					btnSelect = true;
				} else {
					// 删除->编辑
					btn.setText("编辑");
					btnSelect = false;
				}
			}
			break;
		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
	 * .AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		final AddressModel model = (AddressModel) parent
				.getItemAtPosition(position);
		changePosition = position;
		if (choseAddress) {
			if (!btnSelect) {
				// 选择地址
				Intent intent = new Intent(this, NewAddressActivity.class);
				intent.putExtra("CHAGEADDRESS", model);
				setResult(APPEnum.ADDRESS, intent);
				finish();
			} else {
				// 编辑地址
				Intent intent = new Intent(this, NewAddressActivity.class);
				intent.putExtra("CHAGEADDRESS", model);
				intent.putExtra("NewAddressActivity", false);
				startActivityForResult(intent, APPEnum.ADDRESS);
			}
		} else {
			if (!btnSelect) {
				// 编辑地址
				Intent intent = new Intent(this, NewAddressActivity.class);
				intent.putExtra("CHAGEADDRESS", model);
				intent.putExtra("NewAddressActivity", false);
				startActivityForResult(intent, APPEnum.ADDRESS);
			} else {
				// 进行地址删除
				final LoginOutDialg clean = new LoginOutDialg(this);
				clean.setTitle("温馨提示");
				clean.setContent("是否删除此地址?");
				clean.setSure("确认");
				clean.getSure().setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						try {
							DBUtilsHelper.getInstance().getDb().delete(model);
							data = DBUtilsHelper.getInstance().getDb()
									.findAll(AddressModel.class);
							adapter.setData(data);
						} catch (DbException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						clean.cancel();
					}
				});
				clean.setConsel("取消");
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent datas) {
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, datas);
		if (resultCode == APPEnum.ADDRESS_NEW) {
			// 新增地址
			AddressModel addressModel = (AddressModel) datas
					.getSerializableExtra("AddressModel");
			if (data.size() == 0 && choseAddress) {
				Intent intent = new Intent(this, NewAddressActivity.class);
				intent.putExtra("CHAGEADDRESS", addressModel);
				setResult(APPEnum.ADDRESS, intent);
				finish();
			} else {
				data.add(data.size(), addressModel);
				adapter.setData(data);
			}
		} else if (resultCode == APPEnum.ADDRESS_CHA) {
			AddressModel addressModel = (AddressModel) datas
					.getSerializableExtra("AddressModel");
			data.remove(changePosition);
			data.add(changePosition, addressModel);
			adapter.setData(data);
		}
	}
}
