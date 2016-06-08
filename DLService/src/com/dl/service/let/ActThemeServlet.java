package com.dl.service.let;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dl.service.common.JsonUtil;
import com.dl.service.db.DBActTheme;
import com.dl.service.model.ActThemeModel;
import com.dl.service.model.BaseModel;

/**
 * Servlet implementation class ActThemeServlet
 */
@WebServlet(description = "第二个页面，活动主题页面接口。", urlPatterns = { "/ActThemeServlet" })
public class ActThemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = ActThemeServlet.class.getSimpleName();
	private int i = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActThemeServlet() {
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
		doGet(request, response);
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

			String page = request.getParameter("page");
			if (page == null) {
				page = "1";
			}
			System.out.println("page = " + page);
			// 通过页码在数据库进行查询。获取数据列表
//			 for (int a = 0; a < ImageData.IMAGES.length; a++) {
//			 String id = i + "" + a
//			 + new Random().nextInt(9) + new Random().nextInt(9)
//			 + new Random().nextInt(9) + new Random().nextInt(9);
//			 DBActTheme.insert(new ActThemeModel(id,
//			 ImageData.IMAGES[a], id+"description"));
//			 }

			String json = null;
			List<ActThemeModel> thData = DBActTheme.selectAllTheme(page);
			if (thData != null && thData.size() > 0) {
				BaseModel<List<ActThemeModel>> mo = new BaseModel<List<ActThemeModel>>(
						200, "", thData);
				json = JsonUtil.pojo2Json(mo);
			} else {
				// token失效
				BaseModel<List<ActThemeModel>> mo = new BaseModel<List<ActThemeModel>>(
						203, "暂无数据", null);
				json = JsonUtil.pojo2Json(mo);
			}
			response.getWriter().write(json);
			System.out.println(TAG + "活动主题接口调用了" + i + "次");
		}
	}
}
