/**上午10:41:19
 * @author zhangyh2
 * MainMessageModel.java
 * TODO
 */
package com.darly.dlclent.model;

import java.util.List;

/**
 * @author zhangyh2 MainMessageModel 上午10:41:19 TODO
 *         商品信息比较复杂，只能多建立几个Model进行GSON解析。不能使用统一的解析方式。
 */
public class MainMessageModel {

	private int id;

	private String title;

	private String name;

	private String description;

	private String url;

	private double price;

	private double original;

	private long commodityID;

	private String type;

	/**
	 * 上午10:40:14 TODO 展示图片
	 */
	private List<DetailsGoodsShow> showinfo;

	/**
	 * 上午10:40:02 TODO 几组属性参数
	 */
	private List<DetailsGoodsPro> data;

	public MainMessageModel(int id, String title, String name,
			String description, String url, double price, double original,
			long commodityID, String type) {
		this.id = id;
		this.title = title;
		this.name = name;
		this.description = description;
		this.url = url;
		this.price = price;
		this.original = original;
		this.commodityID = commodityID;
		this.type = type;
	}

	public MainMessageModel(int id, String title, String name,
			String description, String url, double price, double original,
			long commodityID, String type, List<DetailsGoodsShow> showinfo,
			List<DetailsGoodsPro> data) {
		this.id = id;
		this.title = title;
		this.name = name;
		this.description = description;
		this.url = url;
		this.price = price;
		this.original = original;
		this.commodityID = commodityID;
		this.type = type;
		this.showinfo = showinfo;
		this.data = data;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<DetailsGoodsShow> getShowinfo() {
		return showinfo;
	}

	public void setShowinfo(List<DetailsGoodsShow> showinfo) {
		this.showinfo = showinfo;
	}

	public List<DetailsGoodsPro> getData() {
		return data;
	}

	public void setData(List<DetailsGoodsPro> data) {
		this.data = data;
	}

}
