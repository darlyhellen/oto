/**下午2:45:49
 * @author zhangyh2
 * UserInfoData.java
 * TODO
 */
package com.darly.dlclent.model;

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

	/**
	 * 下午2:34:29
	 * 
	 * @author zhangyh2
	 */
	public UserInfoData() {
		// TODO Auto-generated constructor stub
	}

	public UserInfoData(String name, String icon, String tel, String sex,
			String idCard, String money, String token) {
		this.name = name;
		this.icon = icon;
		this.tel = tel;
		this.sex = sex;
		this.idCard = idCard;
		this.money = money;
		this.token = token;
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

}
