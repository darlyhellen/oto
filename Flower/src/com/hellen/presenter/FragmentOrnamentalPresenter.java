/**下午2:24:34
 * @author zhangyh2
 * FragmentOrnamentalPresenter.java
 * TODO
 */
package com.hellen.presenter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.hellen.adapter.WholeListAdapterM;
import com.hellen.base.BaseListener;
import com.hellen.bean.Main_Flower;
import com.hellen.biz.OrnamentalFlowerInterface;
import com.hellen.biz.OrnamentalFlowerInterface.OrnamentalFlowerBiz;
import com.hellen.biz.imp.Ornamental;
import com.hellen.common.LogApp;
import com.hellen.widget.umengshare.ShareBoard;

/**
 * @author zhangyh2 FragmentOrnamentalPresenter 下午2:24:34 TODO
 */
public class FragmentOrnamentalPresenter {

	private String tag = getClass().getSimpleName();

	private OrnamentalFlowerBiz biz;

	private OrnamentalFlowerInterface view;

	private List<Main_Flower> bannars;

	private boolean show;

	public FragmentOrnamentalPresenter(OrnamentalFlowerInterface views,
			boolean show) {
		super();
		this.view = views;
		this.show = show;
		biz = new Ornamental();
		view.setPersenter(biz);
		view.isShowTitle(show);
	}

	public void findData() {
		biz.findOrnamental(new BaseListener<List<Main_Flower>>() {

			@Override
			public void onSucces(List<Main_Flower> result) {
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

	public void setAdapterData(WholeListAdapterM adapter) {
		if (bannars == null || adapter == null) {
			return;
		}
		if (show) {
			adapter.setData(bannars);
		} else {
			// 首页展示4个标签
			List<Main_Flower> flower = new ArrayList<Main_Flower>();
			for (int i = 0; i < 4; i++) {
				flower.add(bannars.get(i));
			}
			adapter.setData(flower);
		}

	}

	/**
	 * 下午5:35:42
	 * 
	 * @author zhangyh2 TODO
	 */
	public void itemClickDown(Context context, AdapterView<?> parent,
			View view, int position, long id) {
		// TODO Auto-generated method stub
		Main_Flower flower = (Main_Flower) parent.getItemAtPosition(position);
		LogApp.i(tag, flower.getName() + flower.getUrl());
		// 测试开启微信分享
		ShareBoard board = new ShareBoard(context);
		board.setContent(flower.getName(), flower.getDescription(),
				flower.getIcon(), flower.getUrl());
		board.showBoard();
	}
}
