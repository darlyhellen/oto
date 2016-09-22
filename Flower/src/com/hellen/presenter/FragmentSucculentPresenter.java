/**下午2:24:34
 * @author zhangyh2
 * FragmentOrnamentalPresenter.java
 * TODO
 */
package com.hellen.presenter;

import java.util.List;

import com.hellen.adapter.WholeListAdapter;
import com.hellen.base.BaseListener;
import com.hellen.bean.Bannar;
import com.hellen.biz.SucculentInterface;
import com.hellen.biz.SucculentInterface.SucculentBiz;
import com.hellen.biz.imp.Succulent;

/** @author zhangyh2
 * FragmentOrnamentalPresenter
 * 下午2:24:34
 * TODO
 */
public class FragmentSucculentPresenter {
	private SucculentBiz biz;

	private SucculentInterface view;
	
	private List<Bannar> bannars;

	public FragmentSucculentPresenter(SucculentInterface views, boolean show) {
		super();
		this.view = views;
		biz = new Succulent();
		view.setPersenter(biz);
		view.isShowTitle(show);
	}

	public void findData() {
		biz.findSucculent(new BaseListener<List<Bannar>>() {

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
	
	
	public void setAdapterData(WholeListAdapter adapter){
		if (bannars==null||adapter==null) {
			return;
		}
		adapter.setData(bannars);
	}
}
