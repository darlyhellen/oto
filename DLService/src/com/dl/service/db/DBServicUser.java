/**上午11:06:27
 * @author zhangyh2
 * DBRegister.java
 * TODO
 */
package com.dl.service.db;

import java.util.ArrayList;
import java.util.List;

import com.dl.service.model.MongoUserModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * @author zhangyh2 DBRegister 上午11:06:27 TODO
 */
public class DBServicUser {
	private static String tablename = "MONGO_USER";

	/**
	 * 
	 * 上午11:27:09
	 * 
	 * @author zhangyh2 DBMongo.java TODO
	 * @return
	 */
	public static List<MongoUserModel> select() {
		// TODO Auto-generated method stub
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find();
		try {
			// 增删查改必须放到线程中进行。否则报错。

			List<MongoUserModel> data = new ArrayList<MongoUserModel>();
			while (dbObject.hasNext()) {
				DBObject ob = dbObject.next();
				data.add(new MongoUserModel(ob.get("name").toString(), ob.get(
						"pass").toString()));
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

	public static MongoUserModel FindInfoByName(String name) {
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find(new BasicDBObject("name", name));
		try {
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject db = dbObject.next();
				return new MongoUserModel(db.get("name").toString(), db.get(
						"pass").toString());
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

	public static boolean update(final MongoUserModel model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject des = new BasicDBObject();
			des.put("name", model.getName());
			des.put("pass", model.getPass());
			WriteResult dbObject = collection.update(getQuery(model.getName()),
					des);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	private static DBObject getQuery(String name) {
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find(new BasicDBObject("name", name));
		try {
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject db = dbObject.next();
				return db;
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

	public static boolean insert(final MongoUserModel model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject object = new BasicDBObject();
			object.put("name", model.getName());
			object.put("pass", model.getPass());
			WriteResult dbObject = collection.insert(object);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean delete(final MongoUserModel model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject object = new BasicDBObject();
			object.put("name", model.getName());
			object.put("pass", model.getPass());
			WriteResult dbObject = collection.remove(object);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
