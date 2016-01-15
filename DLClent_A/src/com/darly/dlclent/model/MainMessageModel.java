/**上午10:41:19
 * @author zhangyh2
 * MainMessageModel.java
 * TODO
 */
package com.darly.dlclent.model;

/**
 * @author zhangyh2 MainMessageModel 上午10:41:19 TODO
 *         商品信息比较复杂，只能多建立几个Model进行GSON解析。不能使用统一的解析方式。
 */
public class MainMessageModel {

	private int id;

	private String title;

	private String name;

	private String url;

	private double price;

	private double original;

	private long commodityID;

	private String type;

	public MainMessageModel(int id, String title, String name, String url,
			double price, double original, long commodityID, String type) {
		this.id = id;
		this.title = title;
		this.name = name;
		this.url = url;
		this.price = price;
		this.original = original;
		this.commodityID = commodityID;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOriginal() {
		return original;
	}

	public void setOriginal(double original) {
		this.original = original;
	}

	public long getCommodityID() {
		return commodityID;
	}

	public void setCommodityID(long commodityID) {
		this.commodityID = commodityID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
