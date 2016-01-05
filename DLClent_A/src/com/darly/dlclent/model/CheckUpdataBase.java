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
public class CheckUpdataBase {

	public int code;

	public String msg;

	public CheckUpdata data;

	public CheckUpdataBase(int code, String msg, CheckUpdata data) {
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

	public CheckUpdata getData() {
		return data;
	}

	public void setData(CheckUpdata data) {
		this.data = data;
	}

}
