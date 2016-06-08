/**上午11:08:40
 * @author zhangyh2
 * DetailsGoodsShow.java
 * TODO
 */
package com.dl.service.model;

/**
 * @author zhangyh2 DetailsGoodsShow 上午11:08:40 TODO
 */
public class DetailsGoodsShow {
	private String url;
	private String name;

	public DetailsGoodsShow(String url, String name) {
		this.url = url;
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
