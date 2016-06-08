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
public class DBECuser {

	private static String tablename = "EC_USERINFO";

	/**
	 * 下午2:45:56
	 * 
	 * @author zhangyh2 TODO 保存从云平台获取的账号信息
	 */
	public static boolean insertEC(final ECUsers model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject object = new BasicDBObject();
			object.put("username", model.getUsername());
			object.put("subAccountSid", model.getSubAccountSid());
			object.put("voipAccount", model.getVoipAccount());
			object.put("dateCreated", model.getDateCreated());
			object.put("voipPwd", model.getVoipPwd());
			object.put("subToken", model.getSubToken());
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
	public static DBObject query(String username) {
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find(new BasicDBObject("username",
				username));
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
	public static ECUsers getECUser(String username) {
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find(new BasicDBObject("username",
				username));
		try {
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject db = dbObject.next();
				return new ECUsers(db.get("username").toString(), db.get(
						"subAccountSid").toString(), db.get("voipAccount")
						.toString(), db.get("dateCreated").toString(), db.get(
						"voipPwd").toString(), db.get("subToken").toString());
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

	/**
	 * 下午2:55:50
	 * 
	 * @author zhangyh2 TODO 根据voipAccount查找对应数据
	 */
	public static ECUsers findECUserByAccount(String voipAccount) {
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find(new BasicDBObject("voipAccount",
				voipAccount));
		try {
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject db = dbObject.next();
				return new ECUsers(db.get("username").toString(), db.get(
						"subAccountSid").toString(), db.get("voipAccount")
						.toString(), db.get("dateCreated").toString(), db.get(
						"voipPwd").toString(), db.get("subToken").toString());
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

	public static List<ECUsers> selectALL() {
		List<ECUsers> data = new ArrayList<ECUsers>();
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find();
		try {
			// 增删查改必须放到线程中进行。否则报错。
			while (dbObject.hasNext()) {
				DBObject db = dbObject.next();
				data.add(new ECUsers(db.get("username").toString(), db.get(
						"subAccountSid").toString(), db.get("voipAccount")
						.toString(), db.get("dateCreated").toString(), db.get(
						"voipPwd").toString(), db.get("subToken").toString()));
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
	 * 下午3:32:14
	 * 
	 * @author zhangyh2 TODO 通过解析字符串。获取用户。
	 * @param ec2
	 */
	public static ECUsers showECUser(String name, String ec) {
		// TODO Auto-generated method stub
		String subAccountSid = null;
		String voipAccount = null;
		String dateCreated = null;
		String voipPwd = null;
		String subToken = null;
		if (ec.contains("subAccountSid=")) {
			subAccountSid = ec.split("subAccountSid=")[1].split(",")[0].trim();
		}
		if (ec.contains("voipAccount=")) {
			voipAccount = ec.split("voipAccount=")[1].split(",")[0].trim();
		}
		if (ec.contains("dateCreated=")) {
			dateCreated = ec.split("dateCreated=")[1].split(",")[0].trim();
		}
		if (ec.contains("voipPwd=")) {
			voipPwd = ec.split("voipPwd=")[1].split(",")[0].trim();
		}
		if (ec.contains("subToken=")) {
			subToken = ec.split("subToken=")[1].split("}")[0].trim();
		}
		return new ECUsers(name, subAccountSid, voipAccount, dateCreated,
				voipPwd, subToken);
	}
}
