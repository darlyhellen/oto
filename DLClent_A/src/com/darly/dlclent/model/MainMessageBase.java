/**上午10:41:19
 * @author zhangyh2
 * MainMessageModel.java
 * TODO
 */
package com.darly.dlclent.model;

import java.util.List;

/**
 * @author zhangyh2 MainMessageModel 上午10:41:19 TODO
 *         商品信息比较复杂，只能多建立几个Model进行GSON解析。不能使用统一的解析方式。
 */
public class MainMessageBase {

	private int code;

	private String msg;

	private List<MainMessageModel> data;

	private List<MainMenuModel> menu;

	public MainMessageBase(int code, String msg, List<MainMessageModel> data,
			List<MainMenuModel> menu) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.menu = menu;
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

	public List<MainMessageModel> getData() {
		return data;
	}

	public void setData(List<MainMessageModel> data) {
		this.data = data;
	}

	public List<MainMenuModel> getMenu() {
		return menu;
	}

	public void setMenu(List<MainMenuModel> menu) {
		this.menu = menu;
	}

}
