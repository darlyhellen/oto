/**
 * 下午4:26:44
 * @author zhangyh2
 * $
 * CheckUpdata.java
 * TODO
 */
package com.darly.dlclent.model;

/**
 * @author zhangyh2 CheckUpdata $ 下午4:26:44 TODO
 */
public class UserInfoDataBase {

	public int code;

	public String msg;

	public UserInfoData data;

	public UserInfoDataBase(int code, String msg, UserInfoData data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UserInfoData getData() {
		return data;
	}

	public void setData(UserInfoData data) {
		this.data = data;
	}

}
