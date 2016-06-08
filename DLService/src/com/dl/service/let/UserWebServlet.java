package com.dl.service.let;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserWebServlet
 */
@WebServlet(description = "用户点击加载网页。进行判断跳转。", urlPatterns = { "/UserWebServlet" })
public class UserWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String TAG = getClass().getSimpleName();
	private int i = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserWebServlet() {
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
	 * 下午2:24:55
	 * 
	 * @author zhangyh2 TODO 通过用户连接。获取到新的URL返回给用户。
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
			String url = request.getParameter("url");
			String token= request.getHeader("token");
			String direct = null;
			if (url!=null) {
				if (token!=null) {
					//登录状态
					direct = "http://10.0.2.2:8080/DLService/"+url;
				}else {
					//用户还未登录，提示用户进行登录
					direct = "login";
				}
			}
			System.out.println(TAG + "接口调用了" + i + "次");
			response.sendRedirect(direct);
		}
	}

}
