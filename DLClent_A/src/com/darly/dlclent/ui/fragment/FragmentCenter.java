/**
 * 下午2:15:05
 * @author zhangyh2
 * $
 * FragmentMain.java
 * TODO
 */
package com.darly.dlclent.ui.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.FragmentCenterAdapter;
import com.darly.dlclent.adapter.FragmentCenterSecAdapter;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseFragment;
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.CleanCache;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.HttpUploadFile;
import com.darly.dlclent.common.ImageLoaderUtil;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.ECUserFriends;
import com.darly.dlclent.model.SecMenuModel;
import com.darly.dlclent.model.UserInfoData;
import com.darly.dlclent.ui.MainActivity;
import com.darly.dlclent.ui.VersionActivity;
import com.darly.dlclent.ui.address.AddressActivity;
import com.darly.dlclent.ui.collect.CollectActivity;
import com.darly.dlclent.ui.comment.CommentWithFloorActivity;
import com.darly.dlclent.ui.order.MyOrderActivity;
import com.darly.dlclent.ui.resetuserinfo.ResetInfoActivity;
import com.darly.dlclent.ui.resetuserinfo.ResetPasswordAcitvity;
import com.darly.dlclent.ui.sweetalert.SweetAlertActivity;
import com.darly.dlclent.ui.web.WebViewActivity;
import com.darly.dlclent.widget.image.PhotoPop;
import com.darly.dlclent.widget.listview.WholeListView;
import com.darly.dlclent.widget.load.ProgressDialogUtil;
import com.darly.dlclent.widget.loginout.LoginOutDialg;
import com.darly.dlclent.widget.roundedimage.RoundedImageView;
import com.darly.im.common.utils.ToastUtil;
import com.darly.im.core.ClientUser;
import com.darly.im.storage.ContactSqlManager;
import com.darly.im.ui.SDKCoreHelper;
import com.darly.im.ui.contact.ECContacts;
import com.darly.im.ui.settings.SettingsActivity;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
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
	private WholeListView lv;
	@ViewInject(R.id.fragment_center_sec_list)
	private WholeListView seclv;

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

	/**
	 * 上午9:29:04 TODO 调出选项的POP窗口，主要为相机，相册，取消
	 */
	private PhotoPop pop;

	private LoginOutDialg dialg;

	private ProgressDialogUtil util;

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
				.getLen() / 6, APPEnum.WIDTH.getLen() / 6));
		model = new BaseModelPaser<UserInfoData>().paserJson(
				SharePreferHelp.getValue(APPEnum.USERINFO.getDec(), null),
				new TypeToken<UserInfoData>() {
				});
		if (model.getData().getIcon() != null
				&& model.getData().getIcon().length() > 0) {
			ImageLoaderUtil.getInstance().loadImageNor(
					model.getData().getIcon().trim(), header_icon);
		} else {
			header_icon.setImageResource(R.drawable.icon);
		}
		header_name.setText(model.getData().getName());

		pop = new PhotoPop(getActivity());

		model = new BaseModelPaser<UserInfoData>().paserJson(
				SharePreferHelp.getValue(APPEnum.USERINFO.getDec(), null),
				new TypeToken<UserInfoData>() {
				});
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
		data.add("物流中心 ");
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
		header_icon.setOnClickListener(this);
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
				// 手机号码 暂时不能进行修改，后台自动进行修改
				// intent.putExtra("tel", model.getData().getTel());
				// intent.putExtra("requestCode", APPEnum.CENTER_TEL);
				// startActivityForResult(intent, APPEnum.CENTER_TEL);
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
				startActivity(new Intent(getActivity(), CollectActivity.class));
				break;
			case 2:
				// 礼品卡
				startActivity(new Intent(getActivity(),
						SweetAlertActivity.class));
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
				clean.setContent("是否清除缓存?");
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
				// 消息设置 跳转消息设置页面
				startActivity(new Intent(getActivity(), SettingsActivity.class));
				break;
			case 4:
				// 版本更新
				startActivity(new Intent(getActivity(), VersionActivity.class));
				break;
			case 5:
				// 退出登录
				dialg = new LoginOutDialg(getActivity());
				dialg.setTitle("温馨提示");
				dialg.setContent("是否确认退出应用?");
				dialg.setSure("确认");
				dialg.getSure().setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						loginOut();
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

	/**
	 * 下午1:53:51
	 * 
	 * @author zhangyh2 TODO 用户退出登录的操作
	 */
	protected void loginOut() {
		// TODO Auto-generated method stub
		if (!APP.isNetworkConnected(getActivity())) {
			ToastApp.showToast(R.string.neterror);
		} else {
			util = new ProgressDialogUtil(getActivity());
			util.setMessage("正在退出登录...");
			util.show();
			RequestParams params = new RequestParams();
			params.addQueryStringParameter("tel", model.getData().getTel());
			HttpClient.get(getActivity(), ConsHttpUrl.USERLOGINOUT, params,
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							// TODO Auto-generated method stub

							util.dismiss();
							// 这里需要添加返回错误的情况
							if (arg0.result == null) {
								ToastApp.showToast(R.string.neterror);
								return;
							} else {
								LogUtils.i(arg0.result);
								BaseModel<UserInfoData> data = new BaseModelPaser<UserInfoData>()
										.paserJson(arg0.result,
												new TypeToken<UserInfoData>() {
												});
								if (data.getCode() == 200) {
									leftTorightAnim(lv);
									leftTorightAnim(seclv);
									// 退出前先将几个状态进行修改。
									SharePreferHelp.putValue(
											APPEnum.ISLOGIN.getDec(), false);
									SharePreferHelp.remove(APPEnum.USERVOIP
											.getDec());
									SharePreferHelp.remove(APPEnum.USERINFO
											.getDec());
									SharePreferHelp.remove(APPEnum.TOKEN
											.getDec());
									SharePreferHelp.remove(APPEnum.USERTEL
											.getDec());
									dialg.cancel();
									// 调回首页
									// 用户退出，关闭云通讯退出。
									SDKCoreHelper.logout(false);
									Intent intent = new Intent(getActivity(),
											MainActivity.class);
									intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(intent);
								} else {
									ToastApp.showToast(data.getMsg());
								}

							}

						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub
							util.dismiss();
							ToastApp.showToast(R.string.neterror);
						}
					});
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
			startActivity(new Intent(getActivity(), MyOrderActivity.class));
			break;
		case 3:
			// 物流中心 跳转页面
			Intent intent = new Intent(getActivity(), WebViewActivity.class);
			intent.putExtra("url", ConsHttpUrl.TEST_JSP);
			startActivity(intent);
			break;
		case 4:
			// 浏览记录 跳转页面
			startActivity(new Intent(getActivity(),
					CommentWithFloorActivity.class));
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
			data.add(new SecMenuModel("消息设置", null));
			data.add(new SecMenuModel("版本更新", null));
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
		switch (v.getId()) {
		case R.id.center_header_icon:
			// 替换顶部图片
			ToastApp.showToast("替换顶部图片");
			pop.show(v);
			break;

		default:
			break;
		}
	}

	private String transformIDcard(String iDcard) {
		if (iDcard == null) {
			return "";
		}
		if (iDcard.length() != 18) {
			return iDcard;
		}
		String str1 = iDcard.substring(0, 6);
		String str2 = "********";
		String str3 = iDcard.substring(14);
		return str1 + str2 + str3;
	}

	private String transformMobile(String mobilephone) {
		if (mobilephone == null) {
			return "";
		}
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
		LogUtils.i("onActivityResult");
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
		} else if (requestCode == APPEnum.REQUESTCODE_CUT) {
			// 裁剪
			if (data != null) {
				Bundle extras = data.getExtras();
				Bitmap head = extras.getParcelable("data");
				header_icon.setImageBitmap(head);
				// 发送到服务器，并获取图片地址
				uploadImage(head);
			}
		} else if (requestCode == APPEnum.REQUESTCODE_CAM
				|| requestCode == APPEnum.REQUESTCODE_CAP) {

			// 拍照或相册
			String head_path = null;
			if (data == null) {
				if (pop == null) {
					head_path = APPEnum.capUri;
				} else {
					head_path = pop.PopStringActivityResult(null,
							APPEnum.REQUESTCODE_CAP);
				}
			} else {
				head_path = pop.PopStringActivityResult(data,
						APPEnum.REQUESTCODE_CAM);
			}
			if (head_path == null) {
				return;
			}
			LogUtils.i(head_path);
			File temp = new File(head_path);
			pop.cropPhoto(Uri.fromFile(temp));// 裁剪图片
		}

	}

	/**
	 * 下午2:25:57
	 * 
	 * @author zhangyh2 TODO 获取bitmap 将BitMap整理为二进制编码进行传递到服务端。
	 * @param head
	 */
	private void uploadImage(Bitmap head) {
		// TODO Auto-generated method stub
		util = new ProgressDialogUtil(getActivity());
		util.setMessage("正在更新图像...");
		util.show();
		saveBitmap(head);
		final File file = new File(APPEnum.HEAD);
		if (file.exists()) {
			new Asy(file).execute();
		}
	}

	class Asy extends AsyncTask<Object, Object, String> {

		private File file;

		public Asy(File file) {
			this.file = file;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
		 */
		@Override
		protected String doInBackground(Object... params) {
			// TODO Auto-generated method stub
			List<File> files = new ArrayList<File>();
			files.add(file);
			return HttpUploadFile.uploadFiles(ConsHttpUrl.UPLOADICON, model
					.getData().getTel(), files);/*
												 * HttpUploadFile.uploadFile(file
												 * )
												 */
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			LogUtils.i(result);
			util.cancel();
			BaseModel<UserInfoData> data = new BaseModelPaser<UserInfoData>()
					.paserJson(result, new TypeToken<UserInfoData>() {
					});
			if (data.getCode() == 200) {
				SharePreferHelp.putValue(APPEnum.USERINFO.getDec(), result);
				ToastApp.showToast("上传成功");
				// 获取用户信息
				RequestParams params = new RequestParams();
				params.addQueryStringParameter("friendName", model.getData()
						.getName());
				HttpClient.get(getActivity(), ConsHttpUrl.SEARCHUSER, params,
						new RequestCallBack<String>() {

							@Override
							public void onSuccess(ResponseInfo<String> arg0) {
								// TODO Auto-generated method stub
								upDataUserInfo(arg0.result);
							}

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								// TODO Auto-generated method stub
								util.dismiss();
								ToastUtil.showMessage(R.string.neterror);
							}
						});

			} else {
				ToastApp.showToast("上传失败");
			}
		}
	}

	/**
	 * 上午11:09:09
	 * 
	 * @author zhangyh2 TODO 根据服务端接口，获取到用户资料。可能为一条记录，也可能为多条记录。
	 */
	protected void upDataUserInfo(String result) {
		// TODO Auto-generated method stub
		util.dismiss();
		if (result == null) {
			return;
		}
		// 开始解析轮播
		LogUtils.i(result);
		BaseModel<List<ECUserFriends>> data = new BaseModelPaser<List<ECUserFriends>>()
				.paserJson(result, new TypeToken<List<ECUserFriends>>() {
				});
		if (data != null && data.getCode() == 200) {
			// 设置轮播
			for (ECUserFriends friend : data.getData()) {
				// 将搜索到的好友，展示到列表页面
				ClientUser clientUser = new ClientUser(friend.getTel());
				clientUser.setUserId(friend.getVoipAccount());
				clientUser.setPassword(friend.getVoipPwd());
				clientUser.setAppKey(ConsHttpUrl.APPKEY);
				clientUser.setAppToken(ConsHttpUrl.APPTOKEN);
				clientUser.setUserName(friend.getName());
				ECContacts cos = new ECContacts();
				cos.setNickname(friend.getName());
				cos.setContactid(friend.getVoipAccount());
				cos.setClientUser(clientUser, friend.getTel(), friend.getIcon());
				ContactSqlManager.insertContact(cos,
						("男".equals(friend.getSex()) ? 1 : 0), true);
			}
		} else {
			ToastApp.showToast(data.getMsg());
		}
	}

	/** 保存方法 */
	public void saveBitmap(Bitmap bm) {
		File f = new File(APPEnum.HEAD);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
