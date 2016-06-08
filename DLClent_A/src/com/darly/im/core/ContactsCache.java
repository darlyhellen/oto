package com.darly.im.core;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.SharePreferCache;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.ECLoginUser;
import com.darly.im.common.CCPAppManager;
import com.darly.im.common.utils.LogUtil;
import com.darly.im.ui.contact.ContactLogic;
import com.darly.im.ui.contact.ECContacts;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.util.LogUtils;

/**
 * com.yuntongxun.ecdemo.core in ECDemo_Android Created by Jorstin on 2015/3/21.
 */
public class ContactsCache {
	// 初始化联系人
	public static final String ACTION_ACCOUT_INIT_CONTACTS = "com.yuntongxun.ecdemo.intent.ACCOUT_INIT_CONTACTS";

	private static ContactsCache instance;

	private ECArrayLists<ECContacts> contacts;

	private LoadingTask asyncTask;

	private ContactsCache() {

	}

	public static ContactsCache getInstance() {
		if (instance == null) {
			instance = new ContactsCache();
		}

		return instance;
	}

	private class LoadingTask extends AsyncTask<Intent, Void, Long> {
		ECArrayLists<ECContacts> contactList = null;

		public LoadingTask() {
		}

		@Override
		protected Long doInBackground(Intent... intents) {
			try {
				LogUtil.d("contatsCache:开始加载联系人");
				// contactList =
				// ContactLogic.getPhoneContacts(CCPAppManager.getContext());
				contactList = ContactLogic.getContractList(true);
				ContactLogic.getMobileContactPhoto(contactList);
			} catch (Exception ce) {
				ce.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Long result) {
			if (contactList != null) {
				// PinyinHelper.release();
				ECArrayLists<ECContacts> oldContacts = contacts;
				contacts = contactList;

				// 添加好友。
				String friend = SharePreferCache.getValue(
						SharePreferHelp.getValue(APPEnum.USERVOIP.getDec(),
								null) + SharePreferCache.CACHE,
						APPEnum.ECCONTACTS.getDec(), null);
				if (friend != null) {
					BaseModel<List<ECLoginUser>> data = new BaseModelPaser<List<ECLoginUser>>()
							.paserJson(friend,
									new TypeToken<List<ECLoginUser>>() {
									});
					if (data != null && data.getCode() == 200) {
						List<ECLoginUser> users = data.getData();
						for (ECLoginUser user : users) {
							ClientUser clientUser = new ClientUser(
									user.getTel());
							clientUser.setUserId(user.getVoipAccount());
							clientUser.setPassword(user.getVoipPwd());
							clientUser.setAppKey(ConsHttpUrl.APPKEY);
							clientUser.setAppToken(ConsHttpUrl.APPTOKEN);
							clientUser.setUserName(user.getName());
							ECContacts cos = new ECContacts();
							cos.setNickname(user.getName());
							cos.setContactid(user.getVoipAccount());
							cos.setClientUser(clientUser,user.getTel(),user.getIcon());
							contacts.add(cos);
						}
						LogUtils.i(contacts.size() + "contacts其中的数据");
					}
				}

				// added
				ArrayList<String> phones = new ArrayList<String>();
				for (ECContacts o : contacts) {
					List<Phone> phoneList = o.getPhoneList();
					if (phoneList == null) {
						continue;
					}
					for (Phone phone : phoneList) {
						if (!TextUtils.isEmpty(phone.getPhoneNum()))
							phones.add(phone.getPhoneNum());
					}
				}
				String[] array = phones.toArray(new String[] {});
				Intent intent = new Intent(ACTION_ACCOUT_INIT_CONTACTS);
				intent.putExtra("array", array);

				CCPAppManager.getContext().sendBroadcast(intent);
			}
		}

		@Override
		protected void onCancelled() {
		}
	}

	public synchronized void load() {
		try {
			if (asyncTask == null) {
				asyncTask = new LoadingTask();
			}
			asyncTask.execute();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void reload() {
		try {
			stop();
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			if (asyncTask != null && !asyncTask.isCancelled()) {
				asyncTask.cancel(true);
				asyncTask = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the contacts
	 */
	public synchronized ECArrayLists<ECContacts> getContacts() {
		return contacts;
	}

}
