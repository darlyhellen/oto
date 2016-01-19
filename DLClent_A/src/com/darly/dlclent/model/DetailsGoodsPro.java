/**上午10:38:01
 * @author zhangyh2
 * DetailsGoodsPro.java
 * TODO
 */
package com.darly.dlclent.model;

import java.util.List;

/**
 * @author zhangyh2 DetailsGoodsPro 上午10:38:01 TODO
 */
public class DetailsGoodsPro {

	private String name;

	private List<DetailsGoodsProperty> data;

	public DetailsGoodsPro(String name, List<DetailsGoodsProperty> data) {
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DetailsGoodsProperty> getData() {
		return data;
	}

	public void setData(List<DetailsGoodsProperty> data) {
		this.data = data;
	}

}
