/**下午5:03:53
 * @author zhangyh2
 * UpdateServiceInterface.java
 * TODO
 */
package com.hellen.biz;

import android.content.Intent;
import android.os.Message;

import com.hellen.base.BaseView;

/**
 * @author zhangyh2 UpdateServiceInterface 下午5:03:53 TODO interface of the
 *         service
 */
public interface UpdateServiceInterface extends BaseView {

	void init(Intent intent);

	/**
	 * 下午5:34:27
	 * 
	 * @author zhangyh2 TODO upload success apk and install it;
	 */
	void installAPK(Message msg);

	/**
	 * 下午5:34:22
	 * 
	 * @author zhangyh2 TODO to update the precent
	 */
	void progressNotification(int precent);

	/**
	 * 下午5:34:14
	 * 
	 * @author zhangyh2 TODO fail to update
	 */
	void FailedAPK(int type, Message msg);

}
