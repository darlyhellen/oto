/**上午11:27:39
 * @author zhangyh2
 * AddressModel.java
 * TODO
 */
package com.darly.dlclent.model;

import java.io.Serializable;

/**
 * @author zhangyh2 AddressModel 上午11:27:39 TODO
 */
public class AddressModel implements Serializable {

	/**上午11:18:05
	 * TODO
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private String tel;

	private UserAddress addr;

	public AddressModel(int id, String name, String tel, UserAddress addr) {
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserAddress getAddr() {
		return addr;
	}

	public void setAddr(UserAddress addr) {
		this.addr = addr;
	}

}
