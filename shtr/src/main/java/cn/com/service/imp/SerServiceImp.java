/**下午4:13:59
 * @author zhangyh2
 * SerServiceImp.java
 * TODO
 */
package cn.com.service.imp;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.com.bean.Admin_User;
import cn.com.dao.Admin_UserMapper;
import cn.com.service.SerService;

/**
 * @author zhangyh2 SerServiceImp 下午4:13:59 TODO
 */
@Service
@Transactional
public class SerServiceImp implements SerService {

	@Resource
	private Admin_UserMapper map;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.SerService#selectAllUser()
	 */
	@Override
	public List<Admin_User> selectAllUser() {
		// TODO Auto-generated method stub
		return map.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.SerService#registUser(cn.com.bean.Admin_User)
	 */
	@Override
	public int registUser(Admin_User user) {
		// TODO Auto-generated method stub
		return map.insert(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.SerService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public Admin_User login(String name, String pass) {
		// TODO Auto-generated method stub
		return map.selectByLogin(name, pass);
	}

}
