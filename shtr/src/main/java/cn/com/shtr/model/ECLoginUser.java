/**下午3:03:02
 * @author zhangyh2
 * ECLoginUser.java
 * TODO
 */
package cn.com.shtr.model;

import cn.com.bean.UserEcAccount;
import cn.com.bean.UserInfoData;

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

	public ECLoginUser(UserInfoData user, UserEcAccount acc) {
		this.name = user.getName();
		this.icon = user.getIcon();
		this.tel = user.getTel();
		this.sex = user.getSex();
		this.idCard = user.getIdcard();
		this.money = user.getMoney() + "";
		this.token = user.getToken();
		this.same = user.getSame();
		this.pass = null;
		this.sim = null;
		this.username = acc.getUsertel();
		this.subAccountSid = acc.getSubaccountsid();
		this.voipAccount = acc.getVoipaccount();
		this.dateCreated = acc.getDatecreated();
		this.voipPwd = acc.getVoippwd();
		this.subToken = acc.getSubtoken();
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

	public void setUser(UserInfoData user, UserEcAccount acc) {

	}

}
