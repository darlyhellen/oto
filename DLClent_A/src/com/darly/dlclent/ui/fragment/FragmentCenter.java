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
import com.darly.dlclent.adapter.FragmentCenterSecAdapter;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseFragment;
import com.darly.dlclent.common.CleanCache;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.SecMenuModel;
import com.darly.dlclent.model.UserInfoData;
import com.darly.dlclent.ui.MainActivity;
import com.darly.dlclent.ui.address.AddressActivity;
import com.darly.dlclent.ui.resetuserinfo.ResetInfoActivity;
import com.darly.dlclent.ui.resetuserinfo.ResetPasswordAcitvity;
import com.darly.dlclent.widget.loginout.LoginOutDialg;
import com.darly.dlclent.widget.roundedimage.RoundedImageView;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
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
	private FragmentCenterSecAdapter secAdapter;
	@ViewInject(R.id.center_header_bg)
	private LinearLayout header_bg;
	@ViewInject(R.id.center_header_icon)
	private RoundedImageView header_icon;
	@ViewInject(R.id.center_header_name)
	private TextView header_name;

	private int outListSelect;

	private BaseModel<UserInfoData> model;

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

		header_icon.setLayoutParams(new LinearLayout.LayoutParams(APPEnum.WIDTH
				.getLen() / 5, APPEnum.WIDTH.getLen() / 5));
		model = new BaseModelPaser<UserInfoData>().paserJson(
				SharePreferHelp.getValue(APPEnum.USERINFO.getDec(), null),
				new TypeToken<UserInfoData>() {
				});
		if (model.getData().getIcon() != null
				&& model.getData().getIcon().length() > 0) {
			imageLoader.displayImage(model.getData().getIcon().trim(),
					header_icon, options);
		} else {
			header_icon.setImageResource(R.drawable.icon);
		}
		header_name.setText(model.getData().getName());
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

		secAdapter = new FragmentCenterSecAdapter(
				new ArrayList<SecMenuModel>(),
				R.layout.fragment_center_sec_item, getActivity());
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
				thrOutListToCheckMenu(position);
			}
		});
	}

	/**
	 * @param name
	 * @param position
	 *            上午9:58:37
	 * @author zhangyh2 FragmentCenter.java TODO 通过外层选框，来确定内层ListView选项，进行跳转页面。
	 * @param model2
	 */
	protected void thrOutListToCheckMenu(int position) {
		// TODO Auto-generated method stub
		switch (outListSelect) {
		case 0:
			// 账户信息
			Intent intent = new Intent(getActivity(), ResetInfoActivity.class);
			switch (position) {
			case 0:
				leftTorightAnim(lv);
				leftTorightAnim(seclv);
				break;
			case 1:
				// 姓名
				intent.putExtra("name", model.getData().getName());
				intent.putExtra("requestCode", APPEnum.CENTER_NAME);
				startActivityForResult(intent, APPEnum.CENTER_NAME);
				break;
			case 2:
				// 手机号码
				intent.putExtra("tel", model.getData().getTel());
				intent.putExtra("requestCode", APPEnum.CENTER_TEL);
				startActivityForResult(intent, APPEnum.CENTER_TEL);
				break;
			case 3:
				// 性别
				intent.putExtra("sex", model.getData().getSex());
				intent.putExtra("requestCode", APPEnum.CENTER_SEX);
				startActivityForResult(intent, APPEnum.CENTER_SEX);
				break;
			case 4:
				// 身份证号码
				intent.putExtra("card", model.getData().getIdCard());
				intent.putExtra("requestCode", APPEnum.CENTER_CARD);
				startActivityForResult(intent, APPEnum.CENTER_CARD);
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
				startActivity(new Intent(getActivity(),
						ResetPasswordAcitvity.class));
				break;
			case 2:
				// 清空缓存
				final LoginOutDialg clean = new LoginOutDialg(getActivity());
				clean.setTitle("温馨提示");
				clean.setContent("是否确认退出应用?");
				clean.setSure("确认");
				clean.getSure().setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						CleanCache.cleanCach();
						clean.cancel();
					}
				});
				clean.setConsel("取消");
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
						// 退出前先将几个状态进行修改。
						SharePreferHelp.putValue(APPEnum.ISLOGIN.getDec(),
								false);
						SharePreferHelp.remove(APPEnum.USERINFO.getDec());
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

		List<SecMenuModel> data = new ArrayList<SecMenuModel>();
		switch (position) {
		case 0:
			// 账户信息
			rightToleftAnim(lv);
			rightToleftAnim(seclv);
			data.add(new SecMenuModel("返回上层菜单", null));
			// 这组数据应该从网络上获取下来。
			data.add(new SecMenuModel("姓名", model.getData().getName()));
			data.add(new SecMenuModel("手机号码", transformMobile(model.getData()
					.getTel())));
			data.add(new SecMenuModel("性别", model.getData().getSex()));
			data.add(new SecMenuModel("身份证号", transformIDcard(model.getData()
					.getIdCard())));
			secAdapter.setData(data);
			outListSelect = position;
			break;

		case 1:
			// 地址管理 跳转页面
			startActivity(new Intent(getActivity(), AddressActivity.class));
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
			data.add(new SecMenuModel("返回上层菜单", null));
			data.add(new SecMenuModel("收藏夹", null));
			data.add(new SecMenuModel("礼品卡", null));
			secAdapter.setData(data);
			outListSelect = position;
			break;
		case 6:
			// 我的钱包
			rightToleftAnim(lv);
			rightToleftAnim(seclv);
			// 这组数据应该从网络上获取下来。
			data.add(new SecMenuModel("返回上层菜单", null));
			data.add(new SecMenuModel("余额", model.getData().getMoney() + "¥"));
			data.add(new SecMenuModel("现金券", null));
			secAdapter.setData(data);
			outListSelect = position;
			break;

		case 7:
			// 设置
			rightToleftAnim(lv);
			rightToleftAnim(seclv);
			data.add(new SecMenuModel("返回上层菜单", null));
			data.add(new SecMenuModel("修改密码", null));
			data.add(new SecMenuModel("清空缓存", null));
			data.add(new SecMenuModel("退出登录", "exit"));
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
		animator.translationXBy((view.getWidth()));
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

	private String transformIDcard(String iDcard) {
		if (iDcard.length() != 18) {
			return iDcard;
		}
		String str1 = iDcard.substring(0, 6);
		String str2 = "********";
		String str3 = iDcard.substring(14);
		return str1 + str2 + str3;
	}

	private String transformMobile(String mobilephone) {
		if (mobilephone.length() != 11) {
			return mobilephone;
		}
		String str1 = mobilephone.substring(0, 3);
		String str2 = "****";
		String str3 = mobilephone.substring(7);
		return str1 + str2 + str3;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == APPEnum.CENTER_CHANGE) {
			model = new BaseModelPaser<UserInfoData>().paserJson(
					SharePreferHelp.getValue(APPEnum.USERINFO.getDec(), null),
					new TypeToken<UserInfoData>() {
					});
			List<SecMenuModel> datasList = new ArrayList<SecMenuModel>();
			datasList.add(new SecMenuModel("返回上层菜单", null));
			// 这组数据应该从网络上获取下来。
			datasList.add(new SecMenuModel("姓名", model.getData().getName()));
			datasList.add(new SecMenuModel("手机号码", transformMobile(model
					.getData().getTel())));
			datasList.add(new SecMenuModel("性别", model.getData().getSex()));
			datasList.add(new SecMenuModel("身份证号", transformIDcard(model
					.getData().getIdCard())));
			secAdapter.setData(datasList);
			// 修改标题位置的用户信息。
			header_name.setText(model.getData().getName());
		}

	}

}
