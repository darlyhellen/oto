package com.dl.service.let;

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
import com.dl.service.ecs.DBECuser;
import com.dl.service.ecs.ECUsers;
import com.dl.service.model.BaseModel;
import com.dl.service.model.Onlines;
import com.dl.service.model.UserInfoData;

/**
 * Servlet implementation class UserOnline
 */
@WebServlet(description = "用户是否在线接口。", urlPatterns = { "/UserOnline" })
public class UserOnline extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String TAG = getClass().getSimpleName();
	private int i = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserOnline() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		feedback(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
			System.out.println(res);
			String name = null;
			try {
				JSONObject object = new JSONObject(res);
				name = object.getString("name");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String json = null;
			List<Onlines> list = new ArrayList<Onlines>();
			for (String b : name.split(",")) {
				System.out
						.println(b.replace("[", " ").replace("]", " ").trim());
				ECUsers users = DBECuser.findECUserByAccount(b
						.replace("[", " ").replace("]", " ").trim());
				
				UserInfoData infodata = DBRegister.FindInfoByTel(users.getUsername());
				if (infodata != null) {
					list.add(new Onlines(infodata.getName(), infodata.getTel(),
							infodata.getLogin()));
				}
			}
			BaseModel<List<Onlines>> mo = new BaseModel<List<Onlines>>(200,
					"获取成功", list);
			json = JsonUtil.pojo2Json(mo);
			response.getWriter().write(json);
			System.out.println(TAG + "用户是否在线接口。调用了" + i + "次");
		}
	}
}
