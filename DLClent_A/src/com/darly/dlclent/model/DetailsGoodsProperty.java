/**上午10:42:04
 * @author zhangyh2
 * DetailsGoodsProperty.java
 * TODO
 */
package com.darly.dlclent.model;

/**
 * @author zhangyh2 DetailsGoodsProperty 上午10:42:04 TODO
 */
public class DetailsGoodsProperty {
	private String name;

	private int id;

	public DetailsGoodsProperty(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
