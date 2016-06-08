/**下午3:03:02
 * @author zhangyh2
 * ECLoginUser.java
 * TODO
 */
package com.darly.dlclent.model;

/**
 * @author zhangyh2 ECLoginUser 下午3:03:02 TODO
 */
public class ECUserFriends {

	private String name;
	private String icon;
	private String tel;
	private String sex;
	private String username;
	private String subAccountSid;
	private String voipAccount;
	private String dateCreated;
	private String voipPwd;
	private String subToken;

	public ECUserFriends(String name, String icon, String tel, String sex,
			String username, String subAccountSid, String voipAccount,
			String dateCreated, String voipPwd, String subToken) {
		this.name = name;
		this.icon = icon;
		this.tel = tel;
		this.sex = sex;
		this.username = username;
		this.subAccountSid = subAccountSid;
		this.voipAccount = voipAccount;
		this.dateCreated = dateCreated;
		this.voipPwd = voipPwd;
		this.subToken = subToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSubAccountSid() {
		return subAccountSid;
	}

	public void setSubAccountSid(String subAccountSid) {
		this.subAccountSid = subAccountSid;
	}

	public String getVoipAccount() {
		return voipAccount;
	}

	public void setVoipAccount(String voipAccount) {
		this.voipAccount = voipAccount;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getVoipPwd() {
		return voipPwd;
	}

	public void setVoipPwd(String voipPwd) {
		this.voipPwd = voipPwd;
	}

	public String getSubToken() {
		return subToken;
	}

	public void setSubToken(String subToken) {
		this.subToken = subToken;
	}

}
