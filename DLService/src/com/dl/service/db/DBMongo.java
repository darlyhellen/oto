/**
 * 下午5:59:52
 * @author zhangyh2
 * $
 * DBMongo.java
 * TODO
 */
package com.dl.service.db;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * @author zhangyh2 DBMongo $ 下午5:59:52 TODO
 */
public class DBMongo {

	private static DBMongo instance;

	private MongoClient client;

	private DB db;

	public static boolean isMongoOnline;

	/**
	 * 
	 * 下午6:00:12
	 * 
	 * @author zhangyh2 DBMongo.java TODO
	 */
	private DBMongo() {
		// TODO Auto-generated constructor stub
		initDB();
	}

	/**
	 * 
	 * 下午6:00:49
	 * 
	 * @author zhangyh2 DBMongo.java TODO
	 */
	@SuppressWarnings("deprecation")
	private void initDB() {
		// TODO Auto-generated method stub
		try {
			client = new MongoClient("localhost", 27017);
			db = client.getDB("darly");
			isMongoOnline = true;
		} catch (Exception e) {
			// TODO: handle exception
			isMongoOnline = false;
			System.out.println("Mongo数据库连接失败。" + e.getMessage());
		}

	}

	/**
	 * @return the instance
	 */
	public static DBMongo getInstance() {
		if (instance == null) {
			instance = new DBMongo();
		}
		return instance;
	}

	/**
	 * 
	 * 下午6:08:30
	 * 
	 * @author zhangyh2 DBMongo.java TODO
	 */
	public void closeMongo() {
		// TODO Auto-generated method stub
		if (client != null) {
			client.close();
		}
	}


	public DB getDB() {
		return db;
	}

	public MongoClient getClient() {
		return client;
	}
}
