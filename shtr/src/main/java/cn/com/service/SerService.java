/**下午4:11:09
 * @author zhangyh2
 * SerService.java
 * TODO
 */
package cn.com.service;

import java.util.List;

import cn.com.bean.Admin_User;

/**
 * @author zhangyh2 SerService 下午4:11:09 TODO
 */
public interface SerService {

	/**
	 * 下午4:13:07
	 * 
	 * @author zhangyh2 TODO 选取所有后台管理用户
	 */
	List<Admin_User> selectAllUser();

	/**
	 * 下午4:13:22
	 * 
	 * @author zhangyh2 TODO 注册后台管理用户
	 */
	int registUser(Admin_User user);

	/**
	 * 下午4:13:31
	 * 
	 * @author zhangyh2 TODO 后台管理用户登录
	 */
	Admin_User login(String name, String pass);
}
