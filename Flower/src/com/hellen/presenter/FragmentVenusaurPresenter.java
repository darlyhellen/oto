/**下午2:24:34
 * @author zhangyh2
 * FragmentOrnamentalPresenter.java
 * TODO
 */
package com.hellen.presenter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.hellen.adapter.WholeListAdapter;
import com.hellen.base.BaseListener;
import com.hellen.bean.Bannar;
import com.hellen.biz.VenusaurInterface;
import com.hellen.biz.VenusaurInterface.VenusaurBiz;
import com.hellen.biz.imp.Venusaur;

/**
 * @author zhangyh2 FragmentOrnamentalPresenter 下午2:24:34 TODO
 */
public class FragmentVenusaurPresenter {
	private VenusaurBiz biz;

	private VenusaurInterface view;

	private List<Bannar> bannars;

	public FragmentVenusaurPresenter(VenusaurInterface views, boolean show) {
		super();
		this.view = views;
		biz = new Venusaur();
		view.setPersenter(biz);
		view.isShowTitle(show);
	}

	public void findData() {
		biz.findVenusaur(new BaseListener<List<Bannar>>() {

			@Override
			public void onSucces(List<Bannar> result) {
				// TODO Auto-generated method stub
				bannars = result;
				view.onSucces();
			}

			@Override
			public void onFaild(int code, String info) {
				// TODO Auto-generated method stub
				view.onFaild();
			}
		});
	}

	public void setAdapterData(WholeListAdapter adapter) {
		if (bannars == null || adapter == null) {
			return;
		}
		adapter.setData(bannars);
	}

	public void itemClickDown(Context context, AdapterView<?> parent,
			View view, int position, long id) {
	}
}
