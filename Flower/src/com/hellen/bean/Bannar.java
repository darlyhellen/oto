/**下午1:42:02
 * @author zhangyh2
 * Bannar.java
 * TODO
 */
package com.hellen.bean;

import com.hellen.widget.carousel.Carousel.CarouselModel;

/**
 * @author zhangyh2 Bannar 下午1:42:02 TODO 实现了Bannar的模型
 */
public class Bannar implements CarouselModel {

	private String url;
	private String title;
	private String icon;

	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hellen.widget.carousel.Carousel.CarouselModel#getImgUrl()
	 */
	@Override
	public String getImgUrl() {
		// TODO Auto-generated method stub
		return icon;
	}

}
