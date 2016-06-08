/**下午2:45:49
 * @author zhangyh2
 * UserInfoData.java
 * TODO
 */
package com.dl.service.model;

/**
 * @author zhangyh2 UserInfoData 下午2:45:49 TODO
 */
public class UserInfoData {

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

	private String login;

	/**
	 * 下午2:34:29
	 * 
	 * @author zhangyh2
	 */
	public UserInfoData() {
		// TODO Auto-generated constructor stub
	}

	public UserInfoData(String name, String icon, String tel, String sex,
			String idCard, String money, String token, String same, String login) {
		this.name = name;
		this.icon = icon;
		this.tel = tel;
		this.sex = sex;
		this.idCard = idCard;
		this.money = money;
		this.token = token;
		this.same = same;
		this.login = login;
	}

	public UserInfoData(String name, String icon, String tel, String sex,
			String idCard, String money, String token, String same,
			String pass, String sim, String login) {
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
		this.login = login;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
