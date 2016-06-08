/**下午3:03:02
 * @author zhangyh2
 * ECLoginUser.java
 * TODO
 */
package com.darly.dlclent.model;


/**
 * @author zhangyh2 ECLoginUser 下午3:03:02 TODO
 */
public class ECLoginUser {

	private String name;

	private String icon;

	private String tel;

	private String sex;

	private String idCard;

	private String money;

	private String token;

	private String same;

	private String pass;

	private String sim;

	private String username;
	private String subAccountSid;
	private String voipAccount;
	private String dateCreated;
	private String voipPwd;
	private String subToken;

	public ECLoginUser(String name, String icon, String tel, String sex,
			String idCard, String money, String token, String same,
			String pass, String sim, String username, String subAccountSid,
			String voipAccount, String dateCreated, String voipPwd,
			String subToken) {
		this.name = name;
		this.icon = icon;
		this.tel = tel;
		this.sex = sex;
		this.idCard = idCard;
		this.money = money;
		this.token = token;
		this.same = same;
		this.pass = pass;
		this.sim = sim;
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSame() {
		return same;
	}

	public void setSame(String same) {
		this.same = same;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
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
