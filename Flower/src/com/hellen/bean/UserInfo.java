/**上午10:25:10
 * @author zhangyh2
 * UserInfo.java
 * TODO
 */
package com.hellen.bean;

public class UserInfo {
	private Integer id;

	private String name;

	private String pass;

	private String sim;

	private String icon;

	private String idcard;

	private Double money;

	private String same;

	private String sex;

	private String tel;

	private String token;

	private String login;

	/**
	 * 下午4:33:40
	 * 
	 * @author zhangyh2
	 */
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}

	public UserInfo(String name, String pass, String sim, String icon,
			String idcard, Double money, String same, String sex, String tel,
			String token, String login) {
		this.name = name;
		this.pass = pass;
		this.sim = sim;
		this.icon = icon;
		this.idcard = idcard;
		this.money = money;
		this.same = same;
		this.sex = sex;
		this.tel = tel;
		this.token = token;
		this.login = login;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass == null ? null : pass.trim();
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim == null ? null : sim.trim();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon == null ? null : icon.trim();
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard == null ? null : idcard.trim();
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getSame() {
		return same;
	}

	public void setSame(String same) {
		this.same = same == null ? null : same.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token == null ? null : token.trim();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login == null ? null : login.trim();
	}
}