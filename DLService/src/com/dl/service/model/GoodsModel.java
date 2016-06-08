/**上午10:41:19
 * @author zhangyh2
 * MainMessageModel.java
 * TODO
 */
package com.dl.service.model;

import java.util.List;

/**
 * @author zhangyh2 MainMessageModel 上午10:41:19 TODO 所有商品，单独一个表格。
 */
/**
 * @author zhangyh2 GoodsModel 上午10:09:49 TODO
 */
public class GoodsModel {

	private String name;

	private String description;

	private String url;

	private double price;

	private double original;

	private long commodityID;

	private String type;

	private String collect;

	/**
	 * 上午10:40:14 TODO 展示图片
	 */
	private List<DetailsGoodsShow> showinfo;

	/**
	 * 上午10:40:02 TODO 几组属性参数
	 */
	private List<DetailsGoodsPro> data;

	private int quantity;

	public GoodsModel(String name, String description, String url,
			double price, double original, long commodityID, String type,
			int quantity) {
		this.name = name;
		this.description = description;
		this.url = url;
		this.price = price;
		this.original = original;
		this.commodityID = commodityID;
		this.type = type;
		this.quantity = quantity;
	}

	public GoodsModel(String name, String description, String url,
			double price, double original, long commodityID, String type,
			String collect, List<DetailsGoodsShow> showinfo,
			List<DetailsGoodsPro> data, int quantity) {
		this.name = name;
		this.description = description;
		this.url = url;
		this.price = price;
		this.original = original;
		this.commodityID = commodityID;
		this.type = type;
		this.collect = collect;
		this.showinfo = showinfo;
		this.data = data;
		this.quantity = quantity;
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

	public String getCollect() {
		return collect;
	}

	public void setCollect(String collect) {
		this.collect = collect;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
