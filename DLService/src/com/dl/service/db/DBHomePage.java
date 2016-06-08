/**上午11:06:27
 * @author zhangyh2
 * DBRegister.java
 * TODO
 */
package com.dl.service.db;

import java.util.ArrayList;
import java.util.List;

import com.dl.service.model.MainCarouselModel;
import com.dl.service.model.MainMenuModel;
import com.dl.service.model.MainMessageModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * @author zhangyh2 DBRegister 上午11:06:27 TODO
 */
public class DBHomePage {
	private static String tablebannar = "homebannar";

	private static String tablegoods = "homegoods";

	private static String tablemenu = "homemenu";

	/**
	 * 
	 * 上午11:27:09
	 * 
	 * @author zhangyh2 DBMongo.java TODO 获取首页广告列表
	 * @return
	 */
	public static List<MainCarouselModel> selectBannar() {
		// TODO Auto-generated method stub
		// 查询记录排序：
		// BasicDBObject sort = new BasicDBObject();
		// sort.put("id",1);
		// 1,表示正序；－1,表示倒序
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablebannar);
		BasicDBObject sort = new BasicDBObject();
		sort.put("id", 1);
		DBCursor dbObject = collection.find().sort(sort);
		try {
			// 增删查改必须放到线程中进行。否则报错。MainCarouselModel

			List<MainCarouselModel> data = new ArrayList<MainCarouselModel>();
			while (dbObject.hasNext()) {
				DBObject ob = dbObject.next();
				data.add(new MainCarouselModel(Integer.parseInt(ob.get("id")
						.toString()), ob.get("url").toString(), ob.get("title")
						.toString(), ob.get("icon").toString()));
			}
			return data;
		} catch (Exception e) {
			// TODO: handle exception
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
	 * @author zhangyh2 DBMongo.java TODO 获取首页商品列表
	 * @return
	 */
	public static List<MainMessageModel> selectGoods() {
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
			List<MainMessageModel> data = new ArrayList<MainMessageModel>();
			while (dbObject.hasNext()) {
				DBObject ob = dbObject.next();
				data.add(new MainMessageModel(Integer.parseInt(ob.get("id")
						.toString()), ob.get("title").toString(), ob
						.get("name").toString(), ob.get("description")
						.toString(), ob.get("url").toString(), Double
						.parseDouble(ob.get("price").toString()), Double
						.parseDouble(ob.get("original").toString()), Long
						.parseLong(ob.get("commodityID").toString()), ob.get(
						"type").toString()));
			}
			return data;
		} catch (Exception e) {
			// TODO: handle exception
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
	public static MainMessageModel findGoodsBycommodityID(long commodityID) {
		// TODO Auto-generated method stub
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablegoods);
		DBCursor dbObject = collection.find(new BasicDBObject("commodityID",
				commodityID));
		try {
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject ob = dbObject.next();
				MainMessageModel mainMessageModel = new MainMessageModel(
						Integer.parseInt(ob.get("id").toString()), ob.get(
								"title").toString(), ob.get("name").toString(),
						ob.get("description").toString(), ob.get("url")
								.toString(), Double.parseDouble(ob.get("price")
								.toString()), Double.parseDouble(ob.get(
								"original").toString()), Long.parseLong(ob.get(
								"commodityID").toString()), ob.get("type")
								.toString());
				return mainMessageModel;
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

	/**
	 * 
	 * 上午11:27:09
	 * 
	 * @author zhangyh2 DBMongo.java TODO 获取首页菜单列表
	 * @return
	 */
	public static List<MainMenuModel> selectMenu() {
		// TODO Auto-generated method stub
		// 查询记录排序：
		// BasicDBObject sort = new BasicDBObject();
		// sort.put("id",1);
		// 1,表示正序；－1,表示倒序
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablemenu);
		BasicDBObject sort = new BasicDBObject();
		sort.put("id", 1);
		DBCursor dbObject = collection.find().sort(sort);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			List<MainMenuModel> data = new ArrayList<MainMenuModel>();
			while (dbObject.hasNext()) {
				DBObject ob = dbObject.next();
				data.add(new MainMenuModel(Integer.parseInt(ob.get("id")
						.toString()), ob.get("title").toString(), ob.get("url")
						.toString(), ob.get("icon").toString()));
			}
			return data;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (dbObject != null) {
				dbObject.close();
			}
		}
		return null;
	}

	public static boolean insertBannar(final MainCarouselModel model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablebannar);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject object = new BasicDBObject();
			object.put("id", model.getId());
			object.put("icon", model.getIcon());
			object.put("url", model.getUrl());
			object.put("title", model.getTitle());
			WriteResult dbObject = collection.insert(object);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean insertMenu(final MainMenuModel model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablemenu);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject object = new BasicDBObject();
			object.put("id", model.getId());
			object.put("icon", model.getIcon());
			object.put("url", model.getUrl());
			object.put("title", model.getTitle());
			WriteResult dbObject = collection.insert(object);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean insertGoods(final MainMessageModel model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablegoods);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject object = new BasicDBObject();
			object.put("id", model.getId());
			object.put("title", model.getTitle());
			object.put("name", model.getName());
			object.put("description", model.getDescription());
			object.put("url", model.getUrl());
			object.put("price", model.getPrice());
			object.put("original", model.getOriginal());
			object.put("commodityID", model.getCommodityID());
			object.put("type", model.getType());
			WriteResult dbObject = collection.insert(object);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
