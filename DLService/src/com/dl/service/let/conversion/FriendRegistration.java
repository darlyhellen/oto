package com.dl.service.let.conversion;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

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
import com.dl.service.ecs.ECLoginUser;
import com.dl.service.ecs.ECUsers;
import com.dl.service.model.BaseModel;
import com.dl.service.model.UserInfoData;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Servlet implementation class FriendRegistration
 */
@WebServlet(description = "添加朋友接口，传递很多参数", urlPatterns = { "/FriendRegistration" })
public class FriendRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int i = 0;
	private String TAG = getClass().getSimpleName();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FriendRegistration() {
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
		addFriend(request, response);
	}

	/**
	 * 下午4:18:57
	 * 
	 * @author zhangyh2 TODO用户添加好友接口
	 */
	private void addFriend(HttpServletRequest request,
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

			int size = request.getContentLength();
			InputStream is = request.getInputStream();
			byte[] reqBodyBytes = ServletHelper.readBytes(is, size);
			String res = new String(reqBodyBytes, "UTF-8");
			System.out.println(res);
			String userTel = null;
			String voipAccount = null;
			try {
				JSONObject object = new JSONObject(res);
				userTel = object.getString("userTel");
				voipAccount = object.getString("voipAccount");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(userTel + "获取到的两个参数" + voipAccount);
			String json = null;
			if (userTel != null && voipAccount != null) {
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

							ECUsers user = DBECuser
									.findECUserByAccount(voipAccount);
							String friendTel = null;
							if (user != null) {
								friendTel = user.getUsername();

								if (DBECfriends.getECAddFriends(userTel
										+ friendTel) != null) {
									BaseModel<ECLoginUser> mo = new BaseModel<ECLoginUser>(
											205, "用户已经是好友，无需重复添加", null);
									json = JsonUtil.pojo2Json(mo);
								} else {
									DBECfriends.insertEC(new ECAddFriends(null,
											userTel + friendTel, userTel,
											friendTel));
									UserInfoData infodata = DBRegister
											.FindInfoByTel(friendTel);
									ECUsers ecUsers = DBECuser
											.getECUser(friendTel);

									ECLoginUser ecLoginUser = new ECLoginUser(
											infodata.getName(),
											infodata.getIcon(),
											infodata.getTel(),
											infodata.getSex(),
											infodata.getIdCard(), null, null,
											null, null, null,
											ecUsers.getUsername(),
											ecUsers.getSubAccountSid(),
											ecUsers.getVoipAccount(),
											ecUsers.getDateCreated(),
											ecUsers.getVoipPwd(),
											ecUsers.getSubToken());

									BaseModel<ECLoginUser> mo = new BaseModel<ECLoginUser>(
											200, "成功添加为好友", ecLoginUser);
									json = JsonUtil.pojo2Json(mo);
								}
							} else {
								BaseModel<ECLoginUser> mo = new BaseModel<ECLoginUser>(
										201, "查找无此用户", null);
								json = JsonUtil.pojo2Json(mo);
							}
						} else {
							// token失效
							BaseModel<ECLoginUser> mo = new BaseModel<ECLoginUser>(203,
									"token失效", null);
							json = JsonUtil.pojo2Json(mo);
						}
					} else {
						// 错误的token
						BaseModel<ECLoginUser> mo = new BaseModel<ECLoginUser>(202,
								"错误的token", null);
						json = JsonUtil.pojo2Json(mo);
					}
				} else {
					// 参数缺失
					BaseModel<ECLoginUser> mo = new BaseModel<ECLoginUser>(201, "参数缺失",
							null);
					json = JsonUtil.pojo2Json(mo);
				}
			} else {
				BaseModel<ECLoginUser> mo = new BaseModel<ECLoginUser>(201,
						"用户资料和朋友资料不能为空", null);
				json = JsonUtil.pojo2Json(mo);
			}
			response.getWriter().write(json);
			System.out.println(TAG + "添加朋友接口调用了" + i + "次");
		}
	}
}
