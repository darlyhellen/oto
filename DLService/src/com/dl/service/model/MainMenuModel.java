/**上午10:50:07
 * @author zhangyh2
 * MainMenuModel.java
 * TODO
 */
package com.dl.service.model;

/**
 * @author zhangyh2 MainMenuModel 上午10:50:07 TODO
 */
public class MainMenuModel {

	private int id;

	private String title;

	private String url;

	private String icon;

	/**
	 * 上午10:51:20
	 * 
	 * @author zhangyh2
	 */
	public MainMenuModel() {
		// TODO Auto-generated constructor stub
	}

	public MainMenuModel(int id, String title, String url, String icon) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.icon = icon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
