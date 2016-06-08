package com.dl.service.let;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
import com.dl.service.model.BaseModel;
import com.dl.service.model.UserInfoData;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Servlet implementation class ResetInfoServlet
 */
@WebServlet(description = "修改用户信息的接口。", urlPatterns = { "/ResetInfoServlet" })
public class ResetInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String TAG = getClass().getSimpleName();
	private int i = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResetInfoServlet() {
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
		feedback(request, response);
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
	 * 下午5:43:04
	 * 
	 * @author zhangyh2 TODO 处理用户请求
	 * @throws UnsupportedEncodingException
	 */
	private void feedback(HttpServletRequest request,
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
			int size = request.getContentLength();
			InputStream is = request.getInputStream();
			byte[] reqBodyBytes = ServletHelper.readBytes(is, size);
			String res = new String(reqBodyBytes, "UTF-8");
			System.out.println("UTF-8" + res);
			String name = null;
			String sex = null;
			String card = null;
			try {
				JSONObject object = new JSONObject(res);
				name = object.getString("name");
				sex = object.getString("sex");
				card = object.getString("card");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("name = " + name + "sex = " + sex + "card = "
					+ card);

			String token = request.getHeader("token");
			String key = request.getHeader("key");
			String json = null;
			if (token != null && key != null) {
				String to = new String(Base64.decode(token));
				String ke = new String(Base64.decode(key));
				if (to.contains(ke)) {
					String time = to.substring(to.length() - ke.length(),
							to.length());
					// 获取到用户的TOKEN字段。可以查找到用户信息。
					String userToken = to.substring(0,
							to.length() - ke.length());
					System.out.println("userToken = " + userToken);

					if (System.currentTimeMillis() / 1000
							- Integer.parseInt(time) < ServletHelper.TOKENTIME) {
						UserInfoData infoData = DBRegister
								.FindInfoByToken(userToken);
						if (name != null) {
							infoData.setName(name);
						} else if (sex != null) {
							infoData.setSex(sex);
						} else if (card != null) {
							infoData.setIdCard(card);
						}
						DBRegister.update(infoData);
						// token正常
						BaseModel<UserInfoData> mo = new BaseModel<UserInfoData>(
								200, "", infoData);
						json = JsonUtil.pojo2Json(mo);
					} else {
						// token失效
						BaseModel<List<UserInfoData>> mo = new BaseModel<List<UserInfoData>>(
								203, "token失效", null);
						json = JsonUtil.pojo2Json(mo);
					}
				} else {
					// 错误的token
					BaseModel<List<UserInfoData>> mo = new BaseModel<List<UserInfoData>>(
							202, "错误的token", null);
					json = JsonUtil.pojo2Json(mo);
				}

			} else {
				// 参数缺失
				BaseModel<List<UserInfoData>> mo = new BaseModel<List<UserInfoData>>(
						201, "参数缺失", null);
				json = JsonUtil.pojo2Json(mo);
			}
			response.getWriter().write(json);
			System.out.println(TAG + "修改用户信息接口调用了" + i + "次");
		}
	}

}
