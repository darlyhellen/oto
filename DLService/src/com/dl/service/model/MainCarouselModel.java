/**上午10:41:02
 * @author zhangyh2
 * MainCarouselModel.java
 * TODO
 */
package com.dl.service.model;

/**
 * @author zhangyh2 MainCarouselModel 上午10:41:02 TODO
 */
public class MainCarouselModel {

	private int id;

	private String url;

	private String title;

	private String icon;

	/**
	 * 上午10:51:31
	 * 
	 * @author zhangyh2
	 */
	public MainCarouselModel() {
		// TODO Auto-generated constructor stub
	}

	public MainCarouselModel(int id, String url, String title, String icon) {
		this.id = id;
		this.url = url;
		this.title = title;
		this.icon = icon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
