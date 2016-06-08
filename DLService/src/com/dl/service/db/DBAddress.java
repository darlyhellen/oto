/**下午1:57:44
 * @author zhangyh2
 * DBAddress.java
 * TODO
 */
package com.dl.service.db;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.dl.service.model.AddressModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * @author zhangyh2 DBAddress 下午1:57:44 TODO 地址类数据库操作
 */
public class DBAddress {
	private static String tablename = "TABLE_ADDRESS";

	/**
	 * 
	 * 上午11:27:09
	 * 
	 * @author zhangyh2 DBMongo.java TODO 查找全部地址信息
	 * @return
	 */
	public static List<AddressModel> selectAllAddress(String token) {
		// TODO Auto-generated method stub
		DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		DBCursor dbObject = collection.find(new BasicDBObject("token", token));
		try {
			// 增删查改必须放到线程中进行。否则报错。
			List<AddressModel> data = new ArrayList<AddressModel>();
			while (dbObject.hasNext()) {
				DBObject ob = dbObject.next();
				data.add(new AddressModel(ob.get("_id").toString(), ob.get(
						"name").toString(), ob.get("tel").toString(), ob.get(
						"province").toString(), ob.get("city").toString(), ob
						.get("district").toString(), ob.get("zipcode")
						.toString(), ob.get("token").toString()));
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
		DBCursor dbObject = collection.find(new BasicDBObject("_id", ids));
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
	public static boolean update(final AddressModel model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject des = new BasicDBObject();
			des.put("name", model.getName());
			des.put("tel", model.getTel());
			des.put("province", model.getProvince());
			des.put("city", model.getCity());
			des.put("district", model.getDistrict());
			des.put("zipcode", model.getZipcode());
			des.put("token", model.getToken());
			WriteResult dbObject = collection.update(getQuery(model.get_id()),
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
	public static boolean insert(final AddressModel model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject des = new BasicDBObject();
			des.put("name", model.getName());
			des.put("tel", model.getTel());
			des.put("province", model.getProvince());
			des.put("city", model.getCity());
			des.put("district", model.getDistrict());
			des.put("zipcode", model.getZipcode());
			des.put("token", model.getToken());
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
	public static boolean delete(final AddressModel model) {
		final DBCollection collection = DBMongo.getInstance().getDB()
				.getCollection(tablename);
		try {
			// 增删查改必须放到线程中进行。否则报错。
			BasicDBObject des = new BasicDBObject();
			des.put("name", model.getName());
			des.put("tel", model.getTel());
			des.put("province", model.getProvince());
			des.put("city", model.getCity());
			des.put("district", model.getDistrict());
			des.put("zipcode", model.getZipcode());
			des.put("token", model.getToken());
			WriteResult dbObject = collection.remove(des);
			return dbObject.isUpdateOfExisting();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
