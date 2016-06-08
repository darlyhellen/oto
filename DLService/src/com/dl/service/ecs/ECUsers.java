/**下午2:39:43
 * @author zhangyh2
 * ECUsers.java
 * TODO
 */
package com.dl.service.ecs;

/**
 * @author zhangyh2 ECUsers 下午2:39:43 TODO 根据注册用户到云平台--进行注册的用户资料
 */
public class ECUsers {

	private String username;
	private String subAccountSid;
	private String voipAccount;
	private String dateCreated;
	private String voipPwd;
	private String subToken;

	/**
	 * 下午2:43:10
	 * 
	 * @author zhangyh2
	 */
	public ECUsers() {
		// TODO Auto-generated constructor stub
	}

	public ECUsers(String username, String subAccountSid, String voipAccount,
			String dateCreated, String voipPwd, String subToken) {
		this.username = username;
		this.subAccountSid = subAccountSid;
		this.voipAccount = voipAccount;
		this.dateCreated = dateCreated;
		this.voipPwd = voipPwd;
		this.subToken = subToken;
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
