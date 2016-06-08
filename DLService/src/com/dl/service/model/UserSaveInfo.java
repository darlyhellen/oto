/**下午2:45:49
 * @author zhangyh2
 * UserInfoData.java
 * TODO
 */
package com.dl.service.model;

/**
 * @author zhangyh2 UserInfoData 下午2:45:49 TODO
 */
public class UserSaveInfo {

	private String name;

	private String pass;

	private String sim;

	/**
	 * 下午2:34:29
	 * 
	 * @author zhangyh2
	 */
	public UserSaveInfo() {
		// TODO Auto-generated constructor stub
	}

	public UserSaveInfo(String name, String pass, String sim) {
		this.name = name;
		this.pass = pass;
		this.sim = sim;
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

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

}
