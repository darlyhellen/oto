package com.dl.service.let.conversion;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import com.dl.service.ecs.ECLoginUser;
import com.dl.service.ecs.ECUsers;
import com.dl.service.model.BaseModel;
import com.dl.service.model.UserInfoData;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Servlet implementation class UserConvertion
 */
@WebServlet(description = "用户回话接口", urlPatterns = { "/UserConvertion" })
public class UserConvertion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int i = 0;
	private String TAG = getClass().getSimpleName();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserConvertion() {
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
		findUserByAccess(request, response);
	}

	/**
	 * 下午2:44:07
	 * 
	 * @author zhangyh2 TODO
	 */
	private void findUserByAccess(HttpServletRequest request,
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
			String voipAccount = request.getParameter("voipAccount");
			String token = request.getHeader("token");
			String key = request.getHeader("key");
			String json = null;
			if (token != null && key != null) {
				String to = new String(Base64.decode(token));
				String ke = new String(Base64.decode(key));
				if (to.contains(ke)) {
					String time = to.substring(to.length() - ke.length(),
							to.length());
					if (System.currentTimeMillis() / 1000
							- Integer.parseInt(time) < ServletHelper.TOKENTIME) {
						// token正常
						ECUsers user = DBECuser
								.findECUserByAccount(voipAccount);

						UserInfoData data = DBRegister.FindInfoByTel(user
								.getUsername());

						ECLoginUser loginUser = new ECLoginUser(data.getName(),
								data.getIcon(), data.getTel(), data.getSex(),
								null, null, null, null, null, null,
								user.getUsername(), user.getSubAccountSid(),
								user.getVoipAccount(), user.getDateCreated(),
								user.getVoipPwd(), user.getSubToken());
						BaseModel<ECLoginUser> mo = new BaseModel<ECLoginUser>(
								200, "", loginUser);
						json = JsonUtil.pojo2Json(mo);
					} else {
						// token失效
						BaseModel<List<Object>> mo = new BaseModel<List<Object>>(
								203, "token失效", null);
						json = JsonUtil.pojo2Json(mo);
					}
				} else {
					// 错误的token
					BaseModel<List<Object>> mo = new BaseModel<List<Object>>(
							202, "错误的token", null);
					json = JsonUtil.pojo2Json(mo);
				}

			} else {
				// 参数缺失
				BaseModel<List<Object>> mo = new BaseModel<List<Object>>(201,
						"参数缺失", null);
				json = JsonUtil.pojo2Json(mo);
			}
			response.getWriter().write(json);
			System.out.println(TAG + "用户信息接口调用了" + i + "次");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
