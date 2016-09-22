/**上午11:26:47
 * @author zhangyh2
 * ServController.java
 * TODO
 */
package cn.com.shtr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.bean.Admin_User;
import cn.com.bean.UserInfoData;
import cn.com.service.MainService;
import cn.com.service.SerService;

/**
 * @author zhangyh2 ServController 上午11:26:47 TODO
 */
@Service
@RequestMapping("/server")
public class ServController {
	@Autowired
	private SerService service;
	@Autowired
	private MainService mainser;

	/**
	 * 下午3:56:53
	 * 
	 * @author zhangyh2 TODO 后台登录
	 */
	@RequestMapping(value = "/serlogin", method = RequestMethod.GET)
	public String loginSer(@Param("name") String name,
			@Param("pass") String pass) {
		System.out.println(name + "loginSer" + pass);
		if (name != null && name.length() > 0 && pass != null
				&& pass.length() > 0) {
			Admin_User user = service.login(name, pass);
			if (user == null) {
				return "/error";
			} else {
				return "/main";
			}
		} else {
			return "/../../index";
		}
	}

	/**
	 * 下午3:56:53
	 * 
	 * @author zhangyh2 TODO 后台登录
	 */
	@RequestMapping(value = "/serlogin", method = RequestMethod.POST)
	public String loginSers(@Param("name") String name,
			@Param("pass") String pass) {
		System.out.println(name + "loginSer" + pass);
		if (name != null && name.length() > 0 && pass != null
				&& pass.length() > 0) {
			Admin_User user = service.login(name, pass);
			if (user == null) {
				return "/error";
			} else {
				return "/main";
			}
		} else {
			return "/../../index";
		}

	}

	/**
	 * 下午3:56:53
	 * 
	 * @author zhangyh2 TODO 后台注册
	 */
	@RequestMapping(value = "/seregist", method = RequestMethod.GET)
	public String register(@Param("name") String name,
			@Param("pass") String pass) {
		System.out.println("register");
		if (name != null && name.length() > 0 && pass != null
				&& pass.length() > 0) {
			Admin_User user = new Admin_User();
			user.setName(name);
			user.setPass(pass);
			int res = service.registUser(user);
			if (res == 1) {
				return "/../../index";
			} else {
				return "/error";
			}
		} else {
			return "/error";
		}
	}

	/**
	 * 下午3:57:53
	 * 
	 * @author zhangyh2 TODO 获取到用户的一些信息
	 */
	@RequestMapping(value = "/seruser")
	public String userinfo(HttpServletRequest request) {
		List<UserInfoData> users = mainser.findUsers();
		request.setAttribute("userinfo", users);
		return "/userinfo";
	}

	/** 下午2:45:36
	 * @author zhangyh2
	 * TODO尝试下载地址变更后能否继续连接下载。
	 */
	@RequestMapping(value = "/down")
	public String findDown() {
		System.out.println("findDown");
		return "/openUrl";
	}

}
