/**下午2:15:36
 * @author zhangyh2
 * LoveData.java
 * TODO
 */
package com.chenminna.towife.bean;

/**
 * @author zhangyh2 LoveData 下午2:15:36 TODO
 */
public class LoveData {
	private int logo;

	private String title;

	private String action;

	private String desc;

	public int getLogo() {
		return logo;
	}

	public void setLogo(int logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public LoveData(int logo, String title, String action, String desc) {
		this.logo = logo;
		this.title = title;
		this.action = action;
		this.desc = desc;
	}

}
