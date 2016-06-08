/**上午11:06:27
 * @author zhangyh2
 * DBRegister.java
 * TODO
 */
package com.dl.service.db;

import java.util.ArrayList;
import java.util.List;

import com.dl.service.model.UserInfoData;
import com.dl.service.model.UserSaveInfo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * @author zhangyh2 DBRegister 上午11:06:27 TODO
 */
public class DBRegister {
	private static String tablename = "USER_REGISTER";

	/**
	 * 
	 * 上午11:27:09
	 * 
	 * @author zhangyh2 DBMongo.java TODO
	 * @return
	 */
	public static List<DBObject> select() {
		// TODO Auto-generated method stub
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find();
		try {
			// 增删查改必须放到线程中进行。否则报错。

			List<DBObject> data = new ArrayList<DBObject>();
			while (dbObject.hasNext()) {
				DBObject ob = dbObject.next();
				data.add(ob);
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

	public static DBObject selectOne(String name) {
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		BasicDBObject query = new BasicDBObject();
		query.put("tel", name);
		DBCursor dbObject = collection.find(query);
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

	public static DBObject getQuery(String tel) {
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find(new BasicDBObject("tel", tel));
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

	public static UserInfoData FindInfoByToken(String token) {
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find(new BasicDBObject("token", token));
		try {
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject db = dbObject.next();
				return new UserInfoData(db.get("name").toString(), db.get(
						"icon").toString(), db.get("tel").toString(), db.get(
						"sex").toString(), db.get("idCard").toString(), db.get(
						"money").toString(), db.get("token").toString(), db
						.get("same").toString(), db.get("pass").toString(), db
						.get("sim").toString(), db.get("login").toString());
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

	public static UserInfoData FindInfoByTel(String tel) {
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find(new BasicDBObject("tel", tel));
		try {
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject db = dbObject.next();
				return new UserInfoData(db.get("name").toString(), db.get(
						"icon").toString(), db.get("tel").toString(), db.get(
						"sex").toString(), db.get("idCard").toString(), db.get(
						"money").toString(), db.get("token").toString(), db
						.get("same").toString(), db.get("pass").toString(), db
						.get("sim").toString(), db.get("login").toString());
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

	public static List<UserInfoData> FindInfoByName(String name) {
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find(new BasicDBObject("name", name));
		try {
			List<UserInfoData> data = new ArrayList<UserInfoData>();
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject db = dbObject.next();
				data.add(new UserInfoData(db.get("name").toString(), db.get(
						"icon").toString(), db.get("tel").toString(), db.get(
						"sex").toString(), db.get("idCard").toString(), db.get(
						"money").toString(), db.get("token").toString(), db
						.get("same").toString(), db.get("pass").toString(), db
						.get("sim").toString(), db.get("login").toString()));
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

	public static boolean update(final UserInfoData model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject des = new BasicDBObject();
			des.put("name", model.getName());
			des.put("pass", model.getPass());
			des.put("sim", model.getSim());
			des.put("icon", model.getIcon());
			des.put("idCard", model.getIdCard());
			des.put("money", model.getMoney());
			des.put("same", model.getSame());
			des.put("sex", model.getSex());
			des.put("tel", model.getTel());
			des.put("token", model.getToken());
			des.put("login", model.getLogin());
			WriteResult dbObject = collection.update(getQuery(model.getTel()),
					des);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean insert(final UserInfoData model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject object = new BasicDBObject();
			object.put("name", model.getName());
			object.put("pass", model.getPass());
			object.put("sim", model.getSim());
			object.put("icon", model.getIcon());
			object.put("idCard", model.getIdCard());
			object.put("money", model.getMoney());
			object.put("same", model.getSame());
			object.put("sex", model.getSex());
			object.put("tel", model.getTel());
			object.put("token", model.getToken());
			object.put("login", model.getLogin());
			WriteResult dbObject = collection.insert(object);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean delete(final UserSaveInfo model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject object = new BasicDBObject();
			object.put("name", model.getName());
			object.put("pass", model.getPass());
			object.put("sim", model.getSim());
			WriteResult dbObject = collection.remove(object);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
