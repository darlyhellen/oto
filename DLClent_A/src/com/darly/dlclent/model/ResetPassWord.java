/**上午10:33:20
 * @author zhangyh2
 * ResetPassWord.java
 * TODO
 */
package com.darly.dlclent.model;

/**
 * @author zhangyh2 ResetPassWord 上午10:33:20 TODO
 */
public class ResetPassWord {

	private String msg;

	private boolean reLogin;

	private String some;

	public ResetPassWord(String msg, boolean reLogin, String some) {
		this.msg = msg;
		this.reLogin = reLogin;
		this.some = some;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean getReLogin() {
		return reLogin;
	}

	public void setReLogin(boolean reLogin) {
		this.reLogin = reLogin;
	}

	public String getSome() {
		return some;
	}

	public void setSome(String some) {
		this.some = some;
	}

}
