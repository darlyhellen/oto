/*
 *  Copyright (c) 2015 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.yuntongxun.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */package com.darly.im.ui.contact;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.ImageLoaderUtil;
import com.darly.dlclent.common.SharePreferCache;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.ECLoginUser;
import com.darly.dlclent.widget.load.ProgressDialogUtil;
import com.darly.im.common.CCPAppManager;
import com.darly.im.common.utils.ToastUtil;
import com.darly.im.core.ClientUser;
import com.darly.im.storage.ContactSqlManager;
import com.darly.im.ui.ECSuperActivity;
import com.darly.im.ui.chatting.base.EmojiconTextView;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by Jorstin on 2015/3/18.
 */
public class ContactDetailActivity extends ECSuperActivity implements
		View.OnClickListener {

	public final static String RAW_ID = "raw_id";
	public final static String MOBILE = "mobile";
	public final static String DISPLAY_NAME = "display_name";

	private ImageView mPhotoView;
	private EmojiconTextView mUsername;
	private TextView mNumber;

	private ECContacts mContacts;

	private Button addfriend;

	private ProgressDialogUtil util;
	private BaseModel<ECLoginUser> user;

	private View.OnClickListener onClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			if (mContacts == null) {
				return;
			}
			// 由用户列表进入此页面的时候，已经判断是否是好友，不是好友才进入此界面，故而直接修改。
			util.setMessage(R.string.xlistview_header_hint_loading);
			util.show();
			doFriends();
		}
	};

	@Override
	protected int getLayoutId() {
		return R.layout.layout_contact_detail;
	}

	/**
	 * 下午1:47:03
	 * 
	 * @author zhangyh2 TODO 点击按钮加载并进行对话按钮后调用的方案。
	 */
	protected void doFriends() {
		// TODO Auto-generated method stub
		user = new BaseModelPaser<ECLoginUser>().paserJson(
				SharePreferHelp.getValue(APPEnum.USERINFO.getDec(), null),
				new TypeToken<ECLoginUser>() {
				});
		JSONObject ob = new JSONObject();
		try {
			if (user != null && user.getCode() == 200) {
				ob.put("userTel", user.getData().getTel());
			}
			ob.put("voipAccount", mContacts.getContactid());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpClient.post(ConsHttpUrl.ADDFRIEND, ob.toString(),
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						friends(arg0.result);
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						util.dismiss();
						ToastUtil.showMessage(R.string.neterror);
					}
				});
	}

	/**
	 * 下午2:07:03
	 * 
	 * @author zhangyh2 TODO 用户添加好友成功，跳转会话页面
	 */
	protected void friends(String result) {
		// TODO Auto-generated method stub
		if (result == null) {
			util.dismiss();
			return;
		}
		// 开始解析轮播
		LogUtils.i(result);
		BaseModel<ECLoginUser> data = new BaseModelPaser<ECLoginUser>()
				.paserJson(result, new TypeToken<ECLoginUser>() {
				});
		if (data != null && data.getCode() == 200) {
			// 在此处添加服务端的用户资料
			if (!APP.isNetworkConnected(this)) {
				ToastApp.showToast(R.string.neterror);
				util.dismiss();
				return;
			} else {
				JSONObject ob = new JSONObject();
				try {
					ob.put("tel", user.getData().getTel());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				HttpClient.post(ConsHttpUrl.FRIEND, ob.toString(),
						new RequestCallBack<String>() {

							@Override
							public void onSuccess(ResponseInfo<String> arg0) {
								// TODO Auto-generated method stub
								util.dismiss();
								LogUtils.i(arg0.result);
								SharePreferCache.putValue(
										SharePreferHelp.getValue(
												APPEnum.USERVOIP.getDec(), null)
												+ SharePreferCache.CACHE,
										APPEnum.ECCONTACTS.getDec(),
										arg0.result);
								CCPAppManager.startChattingAction(
										ContactDetailActivity.this,
										mContacts.getContactid(),
										mContacts.getNickname(), true);
								setResult(RESULT_OK);
								finish();
							}

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								// TODO Auto-generated method stub
								util.dismiss();
							}
						});
			}
			ECLoginUser user = data.getData();
			ClientUser clientUser = new ClientUser(user.getTel());
			clientUser.setUserName(user.getName());
			clientUser.setUserId(user.getVoipAccount());
			clientUser.setPassword(user.getVoipPwd());
			clientUser.setAppKey(ConsHttpUrl.APPKEY);
			clientUser.setAppToken(ConsHttpUrl.APPTOKEN);
			ECContacts contacts = new ECContacts();
			contacts.setNickname(user.getName());
			contacts.setClientUser(clientUser, user.getTel(), user.getIcon());
			ContactSqlManager.insertContact(contacts,
					("男".equals(user.getSex()) ? 1 : 0), true);

		} else {
			util.dismiss();
			ToastApp.showToast(data.getMsg());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		util = new ProgressDialogUtil(this);
		initView();
		initActivityState(savedInstanceState);
		getTopBarView().setTopBarToStatus(1, R.drawable.topbar_back_bt, -1,
				R.string.contact_contactDetail, this);
	}

	/**
	 * @param savedInstanceState
	 */
	private void initActivityState(Bundle savedInstanceState) {
		long rawId = getIntent().getLongExtra(RAW_ID, -1);
		if (rawId == -1) {
			String mobile = getIntent().getStringExtra(MOBILE);
			String displayname = getIntent().getStringExtra(DISPLAY_NAME);
			mContacts = ContactSqlManager.getCacheContact(mobile);
			if (mContacts == null) {
				mContacts = new ECContacts(mobile);
				mContacts.setNickname(displayname);
			}
		}

		if (mContacts == null && rawId != -1) {
			mContacts = ContactSqlManager.getContact(rawId);
		}

		if (mContacts == null) {
			ToastUtil.showMessage(R.string.contact_none);
			finish();
			return;
		}

		ImageLoaderUtil.getInstance().loadImageNor(mContacts.getRemark(),
				mPhotoView);
		// mPhotoView.setImageBitmap(ContactLogic.getPhoto(mContacts.getRemark()));
		mUsername
				.setText(TextUtils.isEmpty(mContacts.getNickname()) ? mContacts
						.getContactid() : mContacts.getNickname());
		mNumber.setText(mContacts.getContactid());
	}

	/**
     *
     */
	private void initView() {
		mPhotoView = (ImageView) findViewById(R.id.desc);
		mUsername = (EmojiconTextView) findViewById(R.id.contact_nameTv);
		mNumber = (TextView) findViewById(R.id.contact_numer);
		addfriend = (Button) findViewById(R.id.entrance_chat);

		addfriend.setOnClickListener(onClickListener);

		findViewById(R.id.entrance_voip).setVisibility(View.GONE);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mPhotoView != null) {
			mPhotoView.setImageDrawable(null);
		}
		onClickListener = null;
		mContacts = null;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_left:
			hideSoftKeyboard();
			finish();
			break;

		default:
			break;
		}
	}
}
