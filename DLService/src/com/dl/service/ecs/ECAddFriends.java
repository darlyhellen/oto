/**下午4:23:25
 * @author zhangyh2
 * ECAddFriends.java
 * TODO
 */
package com.dl.service.ecs;

/**
 * @author zhangyh2 ECAddFriends 下午4:23:25 TODO 朋友用户关系表
 */
public class ECAddFriends {
	private String _id;
	private String ids;
	private String userTel;
	private String friendTel;

	public ECAddFriends(String _id, String ids, String userTel, String friendTel) {
		this._id = _id;
		this.ids = ids;
		this.userTel = userTel;
		this.friendTel = friendTel;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getFriendTel() {
		return friendTel;
	}

	public void setFriendTel(String friendTel) {
		this.friendTel = friendTel;
	}

}
