package com.dl.service.let;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Servlet implementation class UserLogin 用户登录接口。使用POST请求，进行登录
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogin() {
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
		Map<String, String[]> ma = request.getParameterMap();
		for (Entry<String, String[]> entry : ma.entrySet()) {
			System.out.println(entry.getKey() + entry.getValue().toString());
		}
		String token = request.getHeader("token");
		String key = request.getHeader("key");
		byte[] toke = Base64.decode(token);
		byte[] ke = Base64.decode(key);

//		String num = toke.toString().substring(toke.length - ke.length,
//				toke.length);

		System.out.println(token);

		System.out.println(key);

//		System.out.println(num);
		// 对token进行解密。查看token是否正确。
		String name = request.getParameter("username");

		String pass = request.getParameter("password");

		response.getWriter().print(name + pass);

		System.out.println(name + pass);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String[]> ma = request.getParameterMap();
		for (Entry<String, String[]> entry : ma.entrySet()) {
			System.out.println(entry.getKey() + entry.getValue().toString());
		}
		String token = request.getHeader("token");
		String key = request.getHeader("key");
		byte[] toke = Base64.decode(token);
		byte[] ke = Base64.decode(key);

//		String num = toke.toString().substring(toke.length - ke.length,
//				toke.length);

		System.out.println(token);

		System.out.println(key);

//		System.out.println(num);
		// 对token进行解密。查看token是否正确。
		String name = request.getParameter("username");

		String pass = request.getParameter("password");

		response.getWriter().print(name + pass);

		System.out.println(name + pass);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String[]> ma = request.getParameterMap();
		for (Entry<String, String[]> entry : ma.entrySet()) {
			System.out.println(entry.getKey() + entry.getValue().toString());
		}
		String token = request.getHeader("token");
		String key = request.getHeader("key");
		byte[] toke = Base64.decode(token);
		byte[] ke = Base64.decode(key);


		System.out.println(token);

		System.out.println(key);

		// 对token进行解密。查看token是否正确。
		String name = request.getParameter("username");

		String pass = request.getParameter("password");

		response.getWriter().print(name + pass);

		System.out.println(name + pass);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
		System.out.println("doDelete");
	}

	@Override
	protected void doHead(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doHead(arg0, arg1);
		System.out.println("doHead");
		
	}

	@Override
	protected void doOptions(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doOptions(arg0, arg1);
		System.out.println("doOptions");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
		System.out.println("doPut");
	}

	@Override
	protected void doTrace(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doTrace(arg0, arg1);
		System.out.println("doTrace");
	}

	@Override
	protected long getLastModified(HttpServletRequest req) {
		// TODO Auto-generated method stub
		System.out.println("getLastModified");
		return super.getLastModified(req);
		
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
		System.out.println("service");
	}
	
	

}
