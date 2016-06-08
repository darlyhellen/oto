/**上午10:40:57
 * @author zhangyh2
 * DBGoods.java
 * TODO
 */
package com.dl.service.db;

import java.util.ArrayList;
import java.util.List;

import com.dl.service.model.DetailsGoodsPro;
import com.dl.service.model.DetailsGoodsShow;
import com.dl.service.model.GoodsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @author zhangyh2 DBGoods 上午10:40:57 TODO
 */
public class DBGoods {

	private static String tablegoods = "COMMODITY_GOODS";

	/**
	 * 
	 * 上午11:27:09
	 * 
	 * @author zhangyh2 DBMongo.java TODO
	 * @return
	 */
	public static List<GoodsModel> selectGoods() {
		// TODO Auto-generated method stub
		// 查询记录排序：
		// BasicDBObject sort = new BasicDBObject();
		// sort.put("id",1);
		// 1,表示正序；－1,表示倒序
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablegoods);
		BasicDBObject sort = new BasicDBObject();
		sort.put("id", 1);
		DBCursor dbObject = collection.find().sort(sort);
		try {
			// 增删查改必须放到线程中进行。否则报错。

			List<GoodsModel> data = new ArrayList<GoodsModel>();
			while (dbObject.hasNext()) {
				DBObject ob = dbObject.next();
				List<DetailsGoodsShow> showinfo = new Gson().fromJson(
						ob.get("showinfo").toString(),
						new TypeToken<List<DetailsGoodsShow>>() {
						}.getType());

				List<DetailsGoodsPro> datas = new Gson().fromJson(ob
						.get("data").toString(),
						new TypeToken<List<DetailsGoodsPro>>() {
						}.getType());
				GoodsModel main = new GoodsModel(ob.get("name").toString(), ob
						.get("description").toString(), ob.get("url")
						.toString(), Double.parseDouble(ob.get("price")
						.toString()), Double.parseDouble(ob.get("original")
						.toString()), Long.parseLong(ob.get("commodityID")
						.toString()), ob.get("type").toString(), ob.get(
						"collect").toString(), showinfo, datas,
						Integer.parseInt(ob.get("quantity").toString()));
				data.add(main);
			}
			return data;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (dbObject != null) {
				dbObject.close();
			}
		}
		return null;
	}

	/**
	 * 
	 * 上午11:27:09
	 * 
	 * @author zhangyh2 DBMongo.java TODO 查找一个商品，按照commodityID
	 * @return
	 */
	public static GoodsModel findGoodsByCommodityID(long commodityID) {
		// TODO Auto-generated method stub
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablegoods);
		DBCursor dbObject = collection.find(new BasicDBObject("commodityID",
				commodityID));
		try {
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject ob = dbObject.next();
				List<DetailsGoodsShow> showinfo = new Gson().fromJson(
						ob.get("showinfo").toString(),
						new TypeToken<List<DetailsGoodsShow>>() {
						}.getType());

				List<DetailsGoodsPro> datas = new Gson().fromJson(ob
						.get("data").toString(),
						new TypeToken<List<DetailsGoodsPro>>() {
						}.getType());
				GoodsModel main = new GoodsModel(ob.get("name").toString(), ob
						.get("description").toString(), ob.get("url")
						.toString(), Double.parseDouble(ob.get("price")
						.toString()), Double.parseDouble(ob.get("original")
						.toString()), Long.parseLong(ob.get("commodityID")
						.toString()), ob.get("type").toString(), ob.get(
						"collect").toString(), showinfo, datas,
						Integer.parseInt(ob.get("quantity").toString()));
				dbObject.close();
				return main;
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (dbObject != null) {
				dbObject.close();
			}
		}
		return null;
	}

}
