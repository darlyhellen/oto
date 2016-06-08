package com.dl.service.let.conversion;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dl.service.common.JsonUtil;
import com.dl.service.common.ServletHelper;
import com.dl.service.db.DBRegister;
import com.dl.service.ecs.DBECuser;
import com.dl.service.ecs.ECUserFriends;
import com.dl.service.ecs.ECUsers;
import com.dl.service.model.BaseModel;
import com.dl.service.model.UserInfoData;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Servlet implementation class SearchUsers
 */
@WebServlet(description = "搜索其他用户，根据用户昵称", urlPatterns = { "/SearchUsers" })
public class SearchUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String TAG = getClass().getSimpleName();
	private int i = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		setUserFriend(request, response);
	}

	/**
	 * 下午4:18:57
	 * 
	 * @author zhangyh2 TODO用户添加好友接口
	 */
	private void setUserFriend(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
		// TODO Auto-generated method stub
		/**
		 * 加了synchronized后，并发访问i时就不存在线程安全问题了， 为什么加了synchronized后就没有线程安全问题了呢？
		 * 假如现在有一个线程访问Servlet对象，那么它就先拿到了Servlet对象的那把锁
		 * 等到它执行完之后才会把锁还给Servlet对象，由于是它先拿到了Servlet对象的那把锁，
		 * 所以当有别的线程来访问这个Servlet对象时，由于锁已经被之前的线程拿走了，后面的线程只能排队等候了
		 */
		synchronized (this) {// 在java中，每一个对象都有一把锁，这里的this指的就是Servlet对象
			i++;
			// 针对post请求，设置允许接收中文
			request.setCharacterEncoding("UTF-8");
			// 设置可以在页面中响应的内容类型及中文
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			String friendName = request.getParameter("friendName");
			String json = null;
			// 获取Token
			String token = request.getHeader("token");
			String key = request.getHeader("key");
			List<UserInfoData> usersColl = DBRegister
					.FindInfoByName(friendName);
			if (usersColl != null && usersColl.size() > 0) {
				if (token != null && key != null) {
					String to = new String(Base64.decode(token));
					String ke = new String(Base64.decode(key));
					if (to.contains(ke)) {
						String time = to.substring(to.length() - ke.length(),
								to.length());
						if (System.currentTimeMillis() / 1000
								- Integer.parseInt(time) < ServletHelper.TOKENTIME) {
							List<ECUserFriends> friends = new ArrayList<ECUserFriends>();
							for (UserInfoData userInfoData : usersColl) {
								// 搜索到多个用户情况。
								ECUsers frieEC = DBECuser
										.getECUser(userInfoData.getTel());
								UserInfoData frieData = DBRegister
										.FindInfoByTel(userInfoData.getTel());
								friends.add(new ECUserFriends(frieData
										.getName(), frieData.getIcon(),
										frieData.getTel(), frieData.getSex(),
										frieEC.getUsername(), frieEC
												.getSubAccountSid(), frieEC
												.getVoipAccount(), frieEC
												.getDateCreated(), frieEC
												.getVoipPwd(), frieEC
												.getSubToken()));
							}
							BaseModel<List<ECUserFriends>> mo = new BaseModel<List<ECUserFriends>>(
									200, "数据加载成功", friends);
							json = JsonUtil.pojo2Json(mo);
						} else {
							// token失效
							BaseModel<List<ECUserFriends>> mo = new BaseModel<List<ECUserFriends>>(
									203, "token失效", null);
							json = JsonUtil.pojo2Json(mo);
						}
					} else {
						// 错误的token
						BaseModel<List<ECUserFriends>> mo = new BaseModel<List<ECUserFriends>>(
								202, "错误的token", null);
						json = JsonUtil.pojo2Json(mo);
					}
				} else {
					// 参数缺失
					BaseModel<List<ECUserFriends>> mo = new BaseModel<List<ECUserFriends>>(
							201, "参数缺失", null);
					json = JsonUtil.pojo2Json(mo);
				}
			} else {
				BaseModel<List<ECUserFriends>> mo = new BaseModel<List<ECUserFriends>>(
						204, "用户不存在", null);
				json = JsonUtil.pojo2Json(mo);
			}
			response.getWriter().write(json);
			System.out.println(TAG + "搜索用戶接口调用了" + i + "次");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
