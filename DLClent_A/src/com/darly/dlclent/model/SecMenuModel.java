/**下午2:16:26
 * @author zhangyh2
 * SecMenuModel.java
 * TODO
 */
package com.darly.dlclent.model;

/**
 * @author zhangyh2 SecMenuModel 下午2:16:26 TODO
 */
public class SecMenuModel {

	private String name;

	private String value;

	public SecMenuModel(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
