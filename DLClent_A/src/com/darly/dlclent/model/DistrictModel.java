/**下午4:18:01
 * @author zhangyh2
 * DistrictModel.java
 * TODO
 */
package com.darly.dlclent.model;

/**
 * @author zhangyh2 DistrictModel 下午4:18:01 TODO 区域类
 */
public class DistrictModel {

	private String name;

	private String zipcode;

	public DistrictModel(String name, String zipcode) {
		this.name = name;
		this.zipcode = zipcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
