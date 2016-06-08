/**下午1:57:44
 * @author zhangyh2
 * DBAddress.java
 * TODO
 */
package com.dl.service.db;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.dl.service.model.ActThemeModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * @author zhangyh2 DBAddress 下午1:57:44 TODO 地址类数据库操作
 */
public class DBActTheme {
	private static String tablename = "TABLE_ACT_THEME";

	/**
	 * 
	 * 上午11:27:09
	 * 
	 * @author zhangyh2 DBMongo.java TODO 查找全部地址信息
	 * @param page
	 * @return
	 */
	public static List<ActThemeModel> selectAllTheme(String page) {
		// TODO Auto-generated method stub
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		BasicDBObject sort = new BasicDBObject();
		sort.put("id", 1);
		DBCursor dbObject = null;
		if (Integer.parseInt(page) == 1) {
			dbObject = collection.find().sort(sort).limit(10);
		} else {
			dbObject = collection.find().sort(sort)
					.skip((Integer.parseInt(page)-1)*10).limit(10);
		}

		try {
			// 增删查改必须放到线程中进行。否则报错。
			List<ActThemeModel> data = new ArrayList<ActThemeModel>();
			while (dbObject.hasNext()) {
				DBObject ob = dbObject.next();
				data.add(new ActThemeModel(ob.get("id").toString(), ob.get(
						"imagePath").toString(), ob.get("description")
						.toString()));
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
	 * 下午2:15:53
	 * 
	 * @author zhangyh2 TODO 通过_id查找到本条地址信息。
	 */
	public static DBObject getQuery(String id) {
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		ObjectId ids = new ObjectId(id);
		DBCursor dbObject = collection.find(new BasicDBObject("id", ids));
		try {
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject db = dbObject.next();
				return db;
			}
			return null;
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
	 * 下午2:18:26
	 * 
	 * @author zhangyh2 TODO 更新该条数据
	 */
	public static boolean update(final ActThemeModel model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject des = new BasicDBObject();
			des.put("description", model.getDescription());
			des.put("id", model.getId());
			des.put("imagePath", model.getImagePath());
			WriteResult dbObject = collection.update(getQuery(model.getId()),
					des);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 下午2:18:36
	 * 
	 * @author zhangyh2 TODO 插入一条数据
	 */
	public static boolean insert(final ActThemeModel model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject des = new BasicDBObject();
			des.put("description", model.getDescription());
			des.put("id", model.getId());
			des.put("imagePath", model.getImagePath());
			WriteResult dbObject = collection.insert(des);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 下午2:18:46
	 * 
	 * @author zhangyh2 TODO 删除一条数据
	 */
	public static boolean delete(final ActThemeModel model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject des = new BasicDBObject();
			des.put("description", model.getDescription());
			des.put("id", model.getId());
			des.put("imagePath", model.getImagePath());
			WriteResult dbObject = collection.remove(des);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
