/**上午11:30:16
 * @author zhangyh2
 * AddressAdapter.java
 * TODO
 */
package com.darly.dlclent.adapter;

import java.util.List;

import com.darly.dlclent.R;
import com.darly.dlclent.model.AddressModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author zhangyh2 AddressAdapter 上午11:30:16 TODO
 */
public class AddressAdapter extends ParentAdapter<AddressModel> {

	/**
	 * 上午11:30:44
	 * 
	 * @author zhangyh2
	 */
	public AddressAdapter(List<AddressModel> data, int resID, Context context) {
		super(data, resID, context);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.adapter.ParentAdapter#HockView(int,
	 * android.view.View, android.view.ViewGroup, int, android.content.Context,
	 * java.lang.Object)
	 */
	@Override
	public View HockView(int position, View view, ViewGroup parent, int resID,
			Context context, AddressModel t) {
		// TODO Auto-generated method stub
		ViewHocker hocker = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(resID, null);
			hocker = new ViewHocker();
			hocker.name = (TextView) view.findViewById(R.id.item_address_name);
			hocker.tel = (TextView) view.findViewById(R.id.item_address_tel);
			hocker.addr = (TextView) view.findViewById(R.id.item_address_addr);

			view.setTag(hocker);
		} else {
			hocker = (ViewHocker) view.getTag();
		}

		hocker.name.setText(t.getName());
		hocker.tel.setText(t.getTel());
		hocker.addr.setText(t.getAddr().getProvince() + t.getAddr().getCity()
				+ t.getAddr().getDistrict());

		return view;
	}

	class ViewHocker {
		TextView name;
		TextView tel;
		TextView addr;
	}

}
