/**下午1:44:02
 * @author zhangyh2
 * ConnectionPool.java
 * TODO
 */
package design.patterns.structural.Flyweight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author zhangyh2 ConnectionPool 下午1:44:02 TODO
 *         通过连接池的管理，实现了数据库连接的共享，不需要每一次都重新创建连接
 *         ，节省了数据库重新创建的开销，提升了系统的性能！本章讲解了7种结构型模式，因为篇幅的问题，剩下的11种行为型模式，
 */
public class ConnectionPool {

	private Vector<Connection> pool;

	/* 公有属性 */
	private String url = "jdbc:mysql://localhost:3306/test";
	private String username = "root";
	private String password = "root";
	private String driverClassName = "com.mysql.jdbc.Driver";
	private int poolSize = 100;
	static ConnectionPool instance = null;
	Connection conn = null;

	/* 构造方法，做一些初始化工作 */
	/**
	 * 下午1:46:19
	 * 
	 * @author zhangyh2
	 */
	public ConnectionPool() {
		// TODO Auto-generated constructor stub
		pool = new Vector<Connection>(poolSize);
		try {
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
			pool.add(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* 返回连接到连接池 */
	public synchronized void release() {
		pool.add(conn);
	}

	public synchronized Connection getConnection() {
		if (pool.size() > 0) {
			Connection conn = pool.get(0);
			pool.remove(conn);
			return conn;
		} else {
			return null;
		}
	}
}
