/**上午11:27:39
 * @author zhangyh2
 * AddressModel.java
 * TODO
 */
package com.dl.service.model;

import java.io.Serializable;

/**
 * @author zhangyh2 AddressModel 上午11:27:39 TODO
 */
public class AddressModel implements Serializable {

	/**
	 * 上午11:18:05 TODO
	 */
	private static final long serialVersionUID = 1L;

	private String _id;

	private String name;

	private String tel;

	private String province;

	private String city;

	private String district;

	private String zipcode;

	private String token;

	/**
	 * 下午2:34:06
	 * 
	 * @author zhangyh2
	 */
	public AddressModel() {
		// TODO Auto-generated constructor stub
	}

	public AddressModel(String id, String name, String tel, String province,
			String city, String district, String zipcode, String token) {
		this._id = id;
		this.name = name;
		this.tel = tel;
		this.province = province;
		this.city = city;
		this.district = district;
		this.zipcode = zipcode;
		this.token = token;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String id) {
		this._id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
