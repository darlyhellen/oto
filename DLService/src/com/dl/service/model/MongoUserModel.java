/**上午10:46:56
 * @author zhangyh2
 * ActThemeModel.java
 * TODO
 */
package com.dl.service.model;

/**
 * @author zhangyh2 ActThemeModel 上午10:46:56 TODO
 */
public class MongoUserModel {
	private String name;

	private String pass;

	public MongoUserModel(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
