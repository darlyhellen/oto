/**下午4:16:03
 * @author zhangyh2
 * ProvinceModel.java
 * TODO
 */
package com.darly.dlclent.model;

import java.util.List;

/**
 * @author zhangyh2 ProvinceModel 下午4:16:03 TODO 省份
 */
public class ProvinceModel {

	private String name;

	private List<CityModel> city;

	public ProvinceModel(String name, List<CityModel> city) {
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CityModel> getCity() {
		return city;
	}

	public void setCity(List<CityModel> city) {
		this.city = city;
	}

}
