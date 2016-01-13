/**下午4:17:03
 * @author zhangyh2
 * CityModel.java
 * TODO
 */
package com.darly.dlclent.model;

import java.util.List;

/**
 * @author zhangyh2 CityModel 下午4:17:03 TODO 市区类
 */
public class CityModel {

	private String name;

	private List<DistrictModel> district;

	public CityModel(String name, List<DistrictModel> district) {
		this.name = name;
		this.district = district;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DistrictModel> getDistrict() {
		return district;
	}

	public void setDistrict(List<DistrictModel> district) {
		this.district = district;
	}

}
