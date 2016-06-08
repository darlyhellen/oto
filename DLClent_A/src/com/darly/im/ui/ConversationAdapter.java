/*
 *  Copyright (c) 2013 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.yuntongxun.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */
package com.darly.im.ui;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.ImageLoaderUtil;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.ECLoginUser;
import com.darly.dlclent.model.Onlines;
import com.darly.im.common.CCPAppManager;
import com.darly.im.common.utils.DateUtil;
import com.darly.im.common.utils.DemoUtils;
import com.darly.im.common.utils.ResourceHelper;
import com.darly.im.core.ClientUser;
import com.darly.im.storage.ContactSqlManager;
import com.darly.im.storage.ConversationSqlManager;
import com.darly.im.storage.GroupMemberSqlManager;
import com.darly.im.storage.GroupNoticeSqlManager;
import com.darly.im.ui.chatting.base.EmojiconTextView;
import com.darly.im.ui.chatting.model.Conversation;
import com.darly.im.ui.contact.ContactLogic;
import com.darly.im.ui.contact.ECContacts;
import com.darly.im.ui.group.GroupNoticeHelper;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.yuntongxun.ecsdk.ECMessage;

/**
 * @author zhangyh2 ConversationAdapter 上午10:37:26 TODO 消息列表页面。
 */
public class ConversationAdapter extends CCPListAdapter<Conversation> {

	private OnListAdapterCallBackListener mCallBackListener;
	int padding;
	private ColorStateList[] colorStateLists;

	/**
	 * @param ctx
	 */
	public ConversationAdapter(Context ctx,
			OnListAdapterCallBackListener listener) {
		super(ctx, new Conversation());
		mCallBackListener = listener;
		padding = ctx.getResources()
				.getDimensionPixelSize(R.dimen.OneDPPadding);
		colorStateLists = new ColorStateList[] {
				ResourceHelper
						.getColorStateList(ctx, R.color.normal_text_color),
				ResourceHelper.getColorStateList(ctx,
						R.color.ccp_list_textcolor_three) };
	}

	@Override
	protected Conversation getItem(Conversation t, Cursor cursor) {
		Conversation conversation = new Conversation();
		conversation.setCursor(cursor);
		if (conversation.getUsername() != null
				&& conversation.getUsername().endsWith("@priategroup.com")) {
			ArrayList<String> member = GroupMemberSqlManager
					.getGroupMemberID(conversation.getSessionId());
			if (member != null) {
				ArrayList<String> contactName = ContactSqlManager
						.getContactName(member.toArray(new String[] {}));
				if (contactName != null && !contactName.isEmpty()) {
					String chatroomName = DemoUtils.listToString(contactName,
							",");
					conversation.setUsername(chatroomName);
				}
			}
		}
		return conversation;
	}

	/**
	 * 会话时间
	 * 
	 * @param conversation
	 * @return
	 */
	protected final CharSequence getConversationTime(Conversation conversation) {
		if (conversation.getSendStatus() == ECMessage.MessageStatus.SENDING
				.ordinal()) {
			return mContext.getString(R.string.conv_msg_sending);
		}
		if (conversation.getDateTime() <= 0) {
			return "";
		}
		return DateUtil.getDateString(conversation.getDateTime(),
				DateUtil.SHOW_TYPE_CALL_LOG).trim();
	}

	/**
	 * 根据消息类型返回相应的主题描述
	 * 
	 * @param conversation
	 * @return
	 */
	protected final CharSequence getConversationSnippet(
			Conversation conversation) {
		if (conversation == null) {
			return "";
		}
		if (GroupNoticeSqlManager.CONTACT_ID
				.equals(conversation.getSessionId())) {
			return GroupNoticeHelper
					.getNoticeContent(conversation.getContent());
		}

		String fromNickName = "";
		if (conversation.getSessionId().toUpperCase().startsWith("G")) {
			if (conversation.getContactId() != null
					&& CCPAppManager.getClientUser() != null
					&& !conversation.getContactId().equals(
							CCPAppManager.getClientUser().getUserId())) {
				ECContacts contact = ContactSqlManager.getContact(conversation
						.getContactId());
				if (contact != null && contact.getNickname() != null) {
					fromNickName = contact.getNickname() + ": ";
				} else {
					fromNickName = conversation.getContactId() + ": ";
				}
			}
		}

		if (conversation.getMsgType() == ECMessage.Type.VOICE.ordinal()) {
			return fromNickName + mContext.getString(R.string.app_voice);
		} else if (conversation.getMsgType() == ECMessage.Type.FILE.ordinal()) {
			return fromNickName + mContext.getString(R.string.app_file);
		} else if (conversation.getMsgType() == ECMessage.Type.IMAGE.ordinal()) {
			return fromNickName + mContext.getString(R.string.app_pic);
		} else if (conversation.getMsgType() == ECMessage.Type.VIDEO.ordinal()) {
			return fromNickName + mContext.getString(R.string.app_video);
		} else if (conversation.getMsgType() == ECMessage.Type.LOCATION
				.ordinal()) {
			return fromNickName + mContext.getString(R.string.app_location);

		}
		return fromNickName + conversation.getContent();
	}

	/**
	 * 根据消息发送状态处理
	 * 
	 * @param context
	 * @param conversation
	 * @return
	 */
	public static Drawable getChattingSnippentCompoundDrawables(
			Context context, Conversation conversation) {
		if (conversation.getSendStatus() == ECMessage.MessageStatus.FAILED
				.ordinal()) {
			return DemoUtils.getDrawables(context, R.drawable.msg_state_failed);
		} else if (conversation.getSendStatus() == ECMessage.MessageStatus.SENDING
				.ordinal()) {
			return DemoUtils
					.getDrawables(context, R.drawable.msg_state_sending);
		} else {
			return null;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view;
		final ViewHolder mViewHolder;
		if (convertView == null || convertView.getTag() == null) {
			view = View.inflate(mContext, R.layout.conversation_item, null);
			mViewHolder = new ViewHolder();
			mViewHolder.user_avatar = (ImageView) view
					.findViewById(R.id.avatar_iv);
			mViewHolder.user_avatar_online = (ImageView) view
					.findViewById(R.id.avatar_iv_online);
			mViewHolder.prospect_iv = (ImageView) view
					.findViewById(R.id.avatar_prospect_iv);
			mViewHolder.nickname_tv = (EmojiconTextView) view
					.findViewById(R.id.nickname_tv);
			mViewHolder.tipcnt_tv = (TextView) view
					.findViewById(R.id.tipcnt_tv);
			mViewHolder.update_time_tv = (TextView) view
					.findViewById(R.id.update_time_tv);
			mViewHolder.last_msg_tv = (EmojiconTextView) view
					.findViewById(R.id.last_msg_tv);
			mViewHolder.image_input_text = (ImageView) view
					.findViewById(R.id.image_input_text);
			mViewHolder.image_mute = (ImageView) view
					.findViewById(R.id.image_mute);
			view.setTag(mViewHolder);
		} else {
			view = convertView;
			mViewHolder = (ViewHolder) view.getTag();
		}

		final Conversation conversation = getItem(position);
		if (conversation != null) {
			if (TextUtils.isEmpty(conversation.getUsername())) {
				mViewHolder.nickname_tv.setText(conversation.getSessionId());
			} else {
				// LogUtils.i(conversation.getUsername() +
				// "已经可以获取到用户名称了。getView");
				mViewHolder.nickname_tv.setText(conversation.getUsername());
			}
			handleDisplayNameTextColor(mViewHolder.nickname_tv,
					conversation.getSessionId());
			mViewHolder.last_msg_tv
					.setText(getConversationSnippet(conversation));
			mViewHolder.last_msg_tv
					.setCompoundDrawables(
							getChattingSnippentCompoundDrawables(mContext,
									conversation), null, null, null);
			// 未读提醒设置
			setConversationUnread(mViewHolder, conversation);
			mViewHolder.image_input_text.setVisibility(View.GONE);
			mViewHolder.update_time_tv
					.setText(getConversationTime(conversation));
			if (conversation.getSessionId().toUpperCase().startsWith("G")) {
				Bitmap bitmap = ContactLogic.getChatroomPhoto(conversation
						.getSessionId());
				if (bitmap != null) {
					mViewHolder.user_avatar.setImageBitmap(bitmap);
					mViewHolder.user_avatar.setPadding(padding, padding,
							padding, padding);
					mViewHolder.user_avatar.setBackgroundColor(Color
							.parseColor("#d5d5d5"));
				} else {
					mViewHolder.user_avatar
							.setImageResource(R.drawable.group_head);
					mViewHolder.user_avatar.setPadding(0, 0, 0, 0);
					mViewHolder.user_avatar.setBackgroundDrawable(null);
				}
			} else {
				mViewHolder.user_avatar.setBackgroundDrawable(null);
				if (conversation.getSessionId().equals(
						GroupNoticeSqlManager.CONTACT_ID)) {
					mViewHolder.user_avatar
							.setImageResource(R.drawable.ic_launcher);
				} else {
					ECContacts contact = ContactSqlManager
							.getContact(conversation.getSessionId());
					ImageLoaderUtil.getInstance().loadImageNor(
							contact.getRemark(), mViewHolder.user_avatar);
				}
			}
			// 添加用户是否在线展示功能模块，用户离线情况下图标可见度降低。
			mViewHolder.image_mute
					.setVisibility(isNotice(conversation) ? View.GONE
							: View.VISIBLE);
			// LogUtils.i(conversation.getSessionId() + "回话的id");

			if (TextUtils.isEmpty(conversation.getUsername())
					|| conversation.getSessionId().equals(
							conversation.getUsername())) {
				// 通过会话的SessionID可以在本地服务端上获取到对应用户的姓名，性别等资料。然后可以根据这些资料，修改contacts表的内容。
				if (!APP.isNetworkConnected(mContext)) {
					ToastApp.showToast(R.string.neterror);
				} else {
					RequestParams params = new RequestParams();

					params.addQueryStringParameter("voipAccount",
							conversation.getSessionId());
					HttpClient.get(mContext, ConsHttpUrl.GETUSER, params,
							new RequestCallBack<String>() {

								@Override
								public void onSuccess(ResponseInfo<String> arg0) {
									// TODO Auto-generated method stub
									// 修改用户展示资料。
									LogUtils.i(arg0.result);
									BaseModel<ECLoginUser> data = new BaseModelPaser<ECLoginUser>()
											.paserJson(
													arg0.result,
													new TypeToken<ECLoginUser>() {
													});
									if (data != null && data.getCode() == 200) {
										ECLoginUser info = data.getData();
										ClientUser clientUser = new ClientUser(
												info.getTel());
										clientUser.setUserId(info
												.getVoipAccount());
										clientUser.setPassword(info
												.getVoipPwd());
										clientUser
												.setAppKey(ConsHttpUrl.APPKEY);
										clientUser
												.setAppToken(ConsHttpUrl.APPTOKEN);
										clientUser.setUserName(info.getName());
										ECContacts contacts = new ECContacts();
										contacts.setNickname(info.getName());
										contacts.setClientUser(clientUser,
												info.getTel(), info.getIcon());
										ContactSqlManager.insertContact(
												contacts, ("男".equals(info
														.getSex()) ? 1 : 0),
												true);
										mViewHolder.nickname_tv.setText(info
												.getName());

										ImageLoaderUtil
												.getInstance()
												.loadImageNor(info.getIcon(),
														mViewHolder.user_avatar);

									} else {
										ToastApp.showToast(data.getMsg());
									}
								}

								@Override
								public void onFailure(HttpException arg0,
										String arg1) {
									// TODO Auto-generated method stub

								}
							});
				}
			}

			JSONObject ob = new JSONObject();
			try {
				ob.put("tel", conversation.getSessionId());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpClient.post(ConsHttpUrl.USERONLINE, ob.toString(),
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							// TODO Auto-generated method stub
							LogUtils.i(arg0.result);
							BaseModel<List<Onlines>> data = new BaseModelPaser<List<Onlines>>()
									.paserJson(arg0.result,
											new TypeToken<List<Onlines>>() {
											});
							if (data != null && data.getCode() == 200) {
								// 在线
								String ison = data.getData().get(0).getOnline();
								if ("true".equals(ison)) {
									mViewHolder.user_avatar.setAlpha(1.0F);
									mViewHolder.user_avatar_online
											.setImageResource(R.drawable.round_selector_checked);
								} else {
									// 离线
									mViewHolder.user_avatar.setAlpha(0.5F);
									mViewHolder.user_avatar_online
											.setImageResource(R.drawable.umeng_update_close_bg_normal);
								}
							} else {
								ToastApp.showToast(data.getMsg());
							}
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub
							arg0.printStackTrace();
							LogUtils.i(arg1);
							ToastApp.showToast(R.string.neterror_norespanse);
						}
					});

			// if (conversation.getSessionId() != null
			// && !conversation.getSessionId().toLowerCase()
			// .startsWith("g")) {
			// // 个人信息
			// LogUtils.i("开始请求对方状态" + conversation.getSessionId());
			// ECDevice.getUserState(conversation.getSessionId(),
			// new ECDevice.OnGetUserStateListener() {
			// @Override
			// public void onGetUserState(ECError ecError,
			// ECUserState userState) {
			//
			// if (ecError.errorCode == SdkErrorCode.REQUEST_SUCCESS
			// && userState != null) {
			// LogUtils.i("判断用户是否在线接口调用1次"+conversation.getSessionId()
			// + userState.isOnline());
			// if (userState.isOnline()) {
			// // 在线
			// mViewHolder.user_avatar.setAlpha(1.0F);
			// mViewHolder.user_avatar_online
			// .setImageResource(R.drawable.round_selector_checked);
			// } else {
			// // 离线
			// mViewHolder.user_avatar.setAlpha(0.2F);
			// mViewHolder.user_avatar_online
			// .setImageResource(R.drawable.umeng_update_close_bg_normal);
			// }
			//
			// } else {
			// // 离线
			// mViewHolder.user_avatar.setAlpha(0.2F);
			// mViewHolder.user_avatar_online
			// .setImageResource(R.drawable.umeng_update_close_bg_normal);
			// }
			//
			// }
			// });
			// }

		}
		return view;
	}

	private void handleDisplayNameTextColor(EmojiconTextView textView,
			String contactId) {
		if (ContactLogic.isCustomService(contactId)) {
			textView.setTextColor(colorStateLists[1]);
		} else {
			textView.setTextColor(colorStateLists[0]);
		}
	}

	/**
	 * 设置未读图片显示规则
	 * 
	 * @param mViewHolder
	 * @param conversation
	 */
	private void setConversationUnread(ViewHolder mViewHolder,
			Conversation conversation) {
		String msgCount = conversation.getUnreadCount() > 100 ? "..." : String
				.valueOf(conversation.getUnreadCount());
		mViewHolder.tipcnt_tv.setText(msgCount);
		if (conversation.getUnreadCount() == 0) {
			mViewHolder.tipcnt_tv.setVisibility(View.GONE);
			mViewHolder.prospect_iv.setVisibility(View.GONE);
		} else if (conversation.isNotice()) {
			mViewHolder.tipcnt_tv.setVisibility(View.VISIBLE);
			mViewHolder.prospect_iv.setVisibility(View.GONE);
		} else {
			mViewHolder.prospect_iv.setVisibility(View.VISIBLE);
			mViewHolder.tipcnt_tv.setVisibility(View.GONE);
		}
	}

	static class ViewHolder {
		ImageView user_avatar;
		ImageView user_avatar_online;
		TextView tipcnt_tv;
		ImageView prospect_iv;
		EmojiconTextView nickname_tv;
		TextView update_time_tv;
		EmojiconTextView last_msg_tv;
		ImageView image_input_text;
		ImageView image_mute;
	}

	@Override
	protected void initCursor() {
		notifyChange();
	}

	@Override
	protected void notifyChange() {
		if (mCallBackListener != null) {
			mCallBackListener.OnListAdapterCallBack();
		}
		Cursor cursor = ConversationSqlManager.getConversationCursor();
		setCursor(cursor);
		super.notifyDataSetChanged();
	}

	private boolean isNotice(Conversation conversation) {
		if (conversation.getSessionId().toLowerCase().startsWith("g")) {
			return conversation.isNotice();
		}
		return true;
	}

}
