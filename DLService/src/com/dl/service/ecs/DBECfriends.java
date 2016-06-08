/**下午2:48:29
 * @author zhangyh2
 * DBECuser.java
 * TODO
 */
package com.dl.service.ecs;

import java.util.ArrayList;
import java.util.List;

import com.dl.service.db.DBMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * @author zhangyh2 DBECuser 下午2:48:29 TODO
 */
public class DBECfriends {

	private static String tablename = "EC_USERFRIENDS";

	/**
	 * 下午2:45:56
	 * 
	 * @author zhangyh2 TODO 保存从云平台获取的账号信息
	 */
	public static boolean insertEC(final ECAddFriends model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject object = new BasicDBObject();
			object.put("ids", model.getIds());
			object.put("userTel", model.getUserTel());
			object.put("friendTel", model.getFriendTel());
			WriteResult dbObject = collection.insert(object);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 下午2:55:35
	 * 
	 * @author zhangyh2 TODO 修改的时候使用的方法体
	 */
	public static DBObject query(String _id) {
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find(new BasicDBObject("_id", _id));
		try {
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject db = dbObject.next();
				dbObject.close();
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
	 * 下午2:55:50
	 * 
	 * @author zhangyh2 TODO 根据用户名查找对应数据
	 */
	public static ECAddFriends getECAddFriends(String ids) {
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find(new BasicDBObject("ids", ids));
		try {
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject db = dbObject.next();
				return new ECAddFriends(db.get("_id").toString(), db.get("ids")
						.toString(), db.get("userTel").toString(), db.get(
						"friendTel").toString());
			}
			return null;
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

	public static List<ECAddFriends> findFriends(String userTel) {
		List<ECAddFriends> data = new ArrayList<ECAddFriends>();
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find(new BasicDBObject("userTel",
				userTel));
		try {
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject db = dbObject.next();
				data.add(new ECAddFriends(db.get("_id").toString(), db.get(
						"ids").toString(), db.get("userTel").toString(), db
						.get("friendTel").toString()));
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
}
