package com.dl.service.option;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dl.service.db.DBServicUser;
import com.dl.service.model.MongoUserModel;

/**
 * Servlet implementation class IndexLet
 */
@WebServlet(description = "网页首页对应的Servlet", urlPatterns = { "/IndexLet" })
public class IndexLet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexLet() {
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
		System.out.println("doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("user_name");
		String pass = request.getParameter("user_pass");
		// 获取管理员用户名和密码。
		MongoUserModel model = DBServicUser.FindInfoByName(name);
		if (model != null) {
			if (model.getPass().equals(pass)) {
				// 跳转正常页面
				request.getRequestDispatcher("webjsp").forward(request,
						response);
				System.out.println("传递成功");
			} else {
				// 密码错误提示
				request.getRequestDispatcher("indexmiss.jsp").forward(request,
						response);
			}
		} else {
			// 用户不存在提示
			request.getRequestDispatcher("indexerror.jsp").forward(request,
					response);
		}
	}

}
