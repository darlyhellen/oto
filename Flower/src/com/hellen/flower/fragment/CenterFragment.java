/**上午10:29:57
 * @author zhangyh2
 * OrnamentalFlowerFragment.java
 * TODO
 */
package com.hellen.flower.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hellen.base.BaseFragment;
import com.hellen.base.BasePresenter;
import com.hellen.base.ConsMVP;
import com.hellen.bean.BaseModel;
import com.hellen.bean.BaseModelPaser;
import com.hellen.bean.UserInfo;
import com.hellen.biz.CenterInterface;
import com.hellen.biz.VersionUpdatingInterface;
import com.hellen.common.ImageLoaderUtil;
import com.hellen.common.LogApp;
import com.hellen.common.SharePreferHelp;
import com.hellen.flower.R;
import com.hellen.flower.view.CenterUserView;
import com.hellen.presenter.FragmentCenterPresenter;
import com.hellen.presenter.VersionUpdatingPresenter;
import com.hellen.widget.header.HeaderView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 OrnamentalFlowerFragment 上午10:29:57 TODO 奇异花草
 */
public class CenterFragment extends BaseFragment implements CenterInterface,
		OnClickListener, VersionUpdatingInterface {
	private String TAG = getClass().getSimpleName();
	private View rootView;
	@ViewInject(R.id.center_headerview)
	private HeaderView headerView;
	@ViewInject(R.id.center_userview)
	private CenterUserView user_view;
	@ViewInject(R.id.center_baogao)
	private TextView baogao;
	@ViewInject(R.id.center_yuyue)
	private TextView yuyue;
	@ViewInject(R.id.center_yijian)
	private TextView yijian;
	@ViewInject(R.id.center_shezhi)
	private TextView shezhi;
	@ViewInject(R.id.center_gengxin)
	private TextView gengxin;
	@ViewInject(R.id.center_guanyu)
	private TextView guanyu;

	private FragmentCenterPresenter presenters;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.fragment_main_center, container,
				false);
		ViewUtils.inject(this, rootView); // 注入view和事件
		LogApp.i(TAG, "onCreateView");
		return rootView;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		presenters = new FragmentCenterPresenter(this);
		presenters.setVersionPresenter(new VersionUpdatingPresenter(
				getActivity()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseFragment#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseFragment#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		user_view.setOnClickListener(this);
		baogao.setOnClickListener(this);
		yuyue.setOnClickListener(this);
		yijian.setOnClickListener(this);
		shezhi.setOnClickListener(this);
		gengxin.setOnClickListener(this);
		guanyu.setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.base.BaseView#setPersenter(com.hellen.base.BasePresenter)
	 */
	@Override
	public void setPersenter(BasePresenter persenter) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.CenterInterface#initTitle()
	 */
	@Override
	public void initTitle() {
		// TODO Auto-generated method stub
		headerView.onSetTitle(R.string.center);
		headerView.onDisBack(true);
		headerView.getBg().getBackground().setAlpha(255);
		headerView.getBg().setBackgroundResource(R.color.red);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.biz.CenterInterface#initUser()
	 */
	@Override
	public void initUser() {
		// TODO Auto-generated method stub
		// 用户已经登录，故而能跳到此页面
		BaseModel<UserInfo> data = new BaseModelPaser<UserInfo>().paserJson(
				SharePreferHelp.getValue(ConsMVP.USERINFO.getDec(), null),
				new TypeToken<UserInfo>() {
				});
		if (data != null && data.getCode() == 200) {
			if (data.getData() != null) {
				LogApp.i(TAG, data.getData().getIcon() + "");
				ImageLoaderUtil.getInstance().loadImage(
						data.getData().getIcon(), user_view.user_icon);
				LogApp.i(TAG, data.getData().getName() + "");
				user_view.user_name.setText(data.getData().getName());
				LogApp.i(TAG, data.getData().getTel() + "");
				user_view.user_phone.setText(data.getData().getTel());
				LogApp.i(TAG, data.getData().getSex() + "");
				user_view.setUserSex(data.getData().getSex());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		LogApp.i(TAG, "onClick");
		presenters.onClickDown(getActivity(), v);
	}

}
