package com.dl.service.let.conversion;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.dl.service.common.JsonUtil;
import com.dl.service.common.ServletHelper;
import com.dl.service.db.DBRegister;
import com.dl.service.ecs.DBECfriends;
import com.dl.service.ecs.DBECuser;
import com.dl.service.ecs.ECAddFriends;
import com.dl.service.ecs.ECUserFriends;
import com.dl.service.ecs.ECUsers;
import com.dl.service.model.BaseModel;
import com.dl.service.model.UserInfoData;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Servlet implementation class UserFriendly
 */
@WebServlet(description = "获取本用户的朋友列表", urlPatterns = { "/UserFriendly" })
public class UserFriendly extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String TAG = getClass().getSimpleName();
	private int i = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserFriendly() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		feedback(request, response);
	}

	/**
	 * 下午2:24:55
	 * 
	 * @author zhangyh2 TODO
	 */
	private void feedback(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
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
			int size = request.getContentLength();
			InputStream is = request.getInputStream();
			byte[] reqBodyBytes = ServletHelper.readBytes(is, size);
			String res = new String(reqBodyBytes, "UTF-8");
			String tel = null;
			try {
				JSONObject object = new JSONObject(res);
				tel = object.getString("tel");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String json = null;
			if (tel != null && tel.length() > 0) {
				// 获取Token
				String token = request.getHeader("token");
				String key = request.getHeader("key");
				if (token != null && key != null) {
					String to = new String(Base64.decode(token));
					String ke = new String(Base64.decode(key));
					if (to.contains(ke)) {
						String time = to.substring(to.length() - ke.length(),
								to.length());
						if (System.currentTimeMillis() / 1000
								- Integer.parseInt(time) < ServletHelper.TOKENTIME) {
							List<ECUserFriends> friends = new ArrayList<ECUserFriends>();
							// 获取到用户手机号码后。在EC数据库中查找对应关系。
							List<ECAddFriends> fris = DBECfriends
									.findFriends(tel);
							for (ECAddFriends ecAddFriends : fris) {
								ECUsers frieEC = DBECuser
										.getECUser(ecAddFriends.getFriendTel());
								UserInfoData frieData = DBRegister
										.FindInfoByTel(ecAddFriends
												.getFriendTel());
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
									200, "用户信息集合", friends);
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
							201, "token缺失", null);
					json = JsonUtil.pojo2Json(mo);
				}
			} else {
				// 参数缺失
				BaseModel<List<ECUserFriends>> mo = new BaseModel<List<ECUserFriends>>(
						201, "参数tel缺失", null);
				json = JsonUtil.pojo2Json(mo);
			}
			response.getWriter().write(json);
			System.out.println(TAG + "用户好友接口调用了" + i + "次");

		}
	}
}
