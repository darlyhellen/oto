/**
 * 下午2:15:05
 * @author zhangyh2
 * $
 * FragmentMain.java
 * TODO
 */
package com.darly.dlclent.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.FragmentCenterAdapter;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseFragment;
import com.darly.dlclent.ui.MainActivity;
import com.darly.dlclent.widget.loginout.LoginOutDialg;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 FragmentMain $ 下午2:15:05 TODO
 */
public class FragmentCenter extends BaseFragment implements
		OnItemClickListener, OnClickListener {
	private View rootView;
	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;
	@ViewInject(R.id.fragment_center_list)
	private ListView lv;
	@ViewInject(R.id.fragment_center_sec_list)
	private ListView seclv;

	private FragmentCenterAdapter adapter;
	private FragmentCenterAdapter secAdapter;
	@ViewInject(R.id.center_header_bg)
	private LinearLayout header_bg;
	@ViewInject(R.id.center_header_icon)
	private ImageView header_icon;
	@ViewInject(R.id.center_header_name)
	private TextView header_name;

	private int outListSelect;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.fragment_center, container, false);
		ViewUtils.inject(this, rootView); // 注入view和事件
		return rootView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		title.setText(R.string.footer_center);
		header_bg.setLayoutParams(new LinearLayout.LayoutParams(APPEnum.WIDTH
				.getLen(), (int) (APPEnum.WIDTH.getLen() / 2.66)));
		header_icon.setImageResource(R.drawable.icon);
		header_name.setText("Admin");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

		LayoutParams lp = new LayoutParams(APPEnum.WIDTH.getLen(),
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		lv.setLayoutParams(lp);
		seclv.setLayoutParams(lp);
		adapter = new FragmentCenterAdapter(getData(),
				R.layout.fragment_center_item, getActivity());
		lv.setAdapter(adapter);

		secAdapter = new FragmentCenterAdapter(new ArrayList<String>(),
				R.layout.fragment_center_item, getActivity());
		seclv.setAdapter(secAdapter);

		ViewPropertyAnimator animator = seclv.animate();
		animator.setStartDelay(0);
		animator.setDuration(0);
		animator.translationXBy(APPEnum.WIDTH.getLen());
		animator.start();

	}

	/**
	 * 下午3:31:29
	 * 
	 * @author zhangyh2 FragmentCenter.java TODO
	 */
	private List<String> getData() {
		// TODO Auto-generated method stub
		List<String> data = new ArrayList<String>();
		data.add("账户信息");
		data.add("地址管理");
		data.add("我的订单");
		data.add("物流中心");
		data.add("浏览记录");
		data.add("我的收藏");
		data.add("我的钱包");
		data.add("设置");
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseFragment#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		back.setOnClickListener(this);
		lv.setOnItemClickListener(this);
		seclv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String name = (String) parent.getItemAtPosition(position);
				thrOutListToCheckMenu(name, position);
			}
		});
	}

	/**
	 * @param name
	 * @param position
	 *            上午9:58:37
	 * @author zhangyh2 FragmentCenter.java TODO 通过外层选框，来确定内层ListView选项，进行跳转页面。
	 */
	protected void thrOutListToCheckMenu(String name, int position) {
		// TODO Auto-generated method stub
		switch (outListSelect) {
		case 0:
			// 账户信息
			switch (position) {
			case 0:
				leftTorightAnim(lv);
				leftTorightAnim(seclv);
				break;
			case 1:
				// 头像
				break;
			case 2:
				// 姓名
				break;
			case 3:
				// 手机号码
				break;
			case 4:
				// 性别
				break;
			case 5:
				// 身份证号码
				break;
			default:
				break;
			}
			break;
		case 5:
			// 我的收藏
			switch (position) {
			case 0:
				leftTorightAnim(lv);
				leftTorightAnim(seclv);
				break;
			case 1:
				// 收藏
				break;
			case 2:
				// 礼品卡
				break;
			default:
				break;
			}
			break;
		case 6:
			// 我的钱包
			switch (position) {
			case 0:
				leftTorightAnim(lv);
				leftTorightAnim(seclv);
				break;
			case 1:
				// 余额
				break;
			case 2:
				// 现金券
				break;
			default:
				break;
			}
			break;
		case 7:
			// 设置
			switch (position) {
			case 0:
				leftTorightAnim(lv);
				leftTorightAnim(seclv);
				break;
			case 1:
				// 修改密码
				break;
			case 2:
				// 清空缓存
				break;
			case 3:
				// 退出登录
				final LoginOutDialg dialg = new LoginOutDialg(getActivity());
				dialg.setTitle("温馨提示");
				dialg.setContent("是否确认退出应用?");
				dialg.setSure("确认");
				dialg.getSure().setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						leftTorightAnim(lv);
						leftTorightAnim(seclv);
						dialg.cancel();
						// 调回首页

						Intent intent = new Intent(getActivity(),
								MainActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);

					}
				});
				dialg.setConsel("取消");
				break;
			default:
				break;
			}
			break;

		default:
			break;
		}

		LogUtils.i(name);
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
		// 点击进行横向移动并弹出菜单。

		List<String> data = new ArrayList<String>();
		switch (position) {
		case 0:
			// 账户信息
			rightToleftAnim(lv);
			rightToleftAnim(seclv);
			data.add("返回上层菜单");
			data.add("图像");
			data.add("姓名");
			data.add("手机号码");
			data.add("性别");
			data.add("身份证号");
			secAdapter.setData(data);
			outListSelect = position;
			break;

		case 1:
			// 地址管理 跳转页面

			break;
		case 2:
			// 我的订单 跳转页面

			break;
		case 3:
			// 物流中心 跳转页面

			break;
		case 4:
			// 浏览记录 跳转页面

			break;
		case 5:
			// 我的收藏
			rightToleftAnim(lv);
			rightToleftAnim(seclv);
			data.add("返回上层菜单");
			data.add("收藏夹");
			data.add("礼品卡");
			secAdapter.setData(data);
			outListSelect = position;
			break;
		case 6:
			// 我的钱包
			rightToleftAnim(lv);
			rightToleftAnim(seclv);
			data.add("返回上层菜单");
			data.add("余额");
			data.add("现金券");
			secAdapter.setData(data);
			outListSelect = position;
			break;

		case 7:
			// 设置
			rightToleftAnim(lv);
			rightToleftAnim(seclv);
			data.add("返回上层菜单");
			data.add("修改密码");
			data.add("清空缓存");
			data.add("退出登录");
			secAdapter.setData(data);
			outListSelect = position;
			break;

		default:
			break;
		}

	}

	/**
	 * 上午10:30:09
	 * 
	 * @author zhangyh2 TODO
	 */
	private void rightToleftAnim(View view) {
		ViewPropertyAnimator animator = view.animate();
		animator.setStartDelay(50);
		animator.setDuration(250);
		animator.translationXBy((int) (-(float) view.getWidth()));
		animator.start();
	}

	/**
	 * 下午5:04:50
	 * 
	 * @author zhangyh2 FragmentCenter.java TODO
	 */
	private void leftTorightAnim(View view) {
		ViewPropertyAnimator animator = view.animate();
		animator.setStartDelay(50);
		animator.setDuration(250);
		animator.translationXBy((int) (view.getWidth()));
		animator.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
