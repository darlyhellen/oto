/**上午11:06:20
 * @author zhangyh2
 * BaseHomePageModel.java
 * TODO
 */
package cn.com.shtr.model;

import java.util.List;

import cn.com.bean.ClientCompHomePage;

/**
 * @author zhangyh2 BaseHomePageModel 上午11:06:20 TODO
 */
public class BaseHomePageModel {

	private int code;

	private String msg;

	private List<ClientCompHomePage> top;

	private List<ClientCompHomePage> center;

	private List<ClientCompHomePage> bottom;

	public BaseHomePageModel(int code, String msg,
			List<ClientCompHomePage> top, List<ClientCompHomePage> center,
			List<ClientCompHomePage> bottom) {
		this.code = code;
		this.msg = msg;
		this.top = top;
		this.center = center;
		this.bottom = bottom;
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

	public List<ClientCompHomePage> getTop() {
		return top;
	}

	public void setTop(List<ClientCompHomePage> top) {
		this.top = top;
	}

	public List<ClientCompHomePage> getCenter() {
		return center;
	}

	public void setCenter(List<ClientCompHomePage> center) {
		this.center = center;
	}

	public List<ClientCompHomePage> getBottom() {
		return bottom;
	}

	public void setBottom(List<ClientCompHomePage> bottom) {
		this.bottom = bottom;
	}

}
