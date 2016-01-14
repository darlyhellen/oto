/**下午4:41:29
 * @author zhangyh2
 * UserAddress.java
 * TODO
 */
package com.darly.dlclent.model;

import java.io.Serializable;

/**
 * @author zhangyh2 UserAddress 下午4:41:29 TODO
 */
public class UserAddress implements Serializable {

	/**
	 * 上午11:18:19 TODO
	 */
	private static final long serialVersionUID = 1L;

	private String province;

	private String city;

	private String district;

	private String zipcode;

	/**
	 * 下午2:34:15
	 * 
	 * @author zhangyh2
	 */
	public UserAddress() {
		// TODO Auto-generated constructor stub
	}

	public UserAddress(String province, String city, String district,
			String zipcode) {
		this.province = province;
		this.city = city;
		this.district = district;
		this.zipcode = zipcode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
