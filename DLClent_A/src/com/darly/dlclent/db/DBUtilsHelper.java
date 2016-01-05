package com.darly.dlclent.db;

import com.darly.dlclent.base.APP;
import com.lidroid.xutils.DbUtils;

public class DBUtilsHelper {
	private static String dbName = "dlclient";

	private DbUtils db;

	public DbUtils getDb() {
		if (db == null) {
			db = DbUtils.create(APP.getInstance(), dbName);
		}
		return db;
	}

	public void setDb(DbUtils db) {
		this.db = db;
	}

	private static DBUtilsHelper instance;

	/**
	 * @return 下午2:14:50
	 * @author Zhangyuhui DBUtilsHelper.java TODO 单例模式
	 */
	public static DBUtilsHelper getInstance() {
		if (instance == null) {
			instance = new DBUtilsHelper();
		}
		return instance;
	}

}
