/**上午10:58:11
 * @author zhangyh2
 * a.java
 * TODO
 */
package com.dl.service.model;

/** @author zhangyh2
 * a
 * 上午10:58:11
 * TODO
 */
public class Onlines {
	private String name;
	private String tel;
	private String online;

	public Onlines(String name, String tel, String online) {
		this.name = name;
		this.tel = tel;
		this.online = online;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}
}
