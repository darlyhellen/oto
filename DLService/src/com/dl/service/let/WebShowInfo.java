package com.dl.service.let;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dl.service.db.DBRegister;
import com.dl.service.model.UserInfoData;
import com.mongodb.DBObject;

/**
 * Servlet implementation class WebShowInfo
 */
@WebServlet(description = "使用Servlet将数据传递给JSP页面", urlPatterns = { "/WebShowInfo" })
public class WebShowInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = WebShowInfo.class.getSimpleName();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WebShowInfo() {
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
		System.out.println("获取成功");
		RequestDispatcher rd = request
				.getRequestDispatcher("/web/showinfo.jsp");
		List<UserInfoData> data = new ArrayList<UserInfoData>();
		for (DBObject db : DBRegister.select()) {
			data.add(new UserInfoData(db.get("name").toString(), db.get("icon")
					.toString(), db.get("tel").toString(), db.get("sex")
					.toString(), db.get("idCard").toString(), db.get("money")
					.toString(), db.get("token").toString(), db.get("same")
					.toString(), db.get("pass").toString(), db.get("sim")
					.toString(), db.get("login").toString()));
		}
		request.setAttribute("userinfo", data);// 存值
		rd.forward(request, response);
		System.out.println(TAG + "重新定向，传递参数");
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

}
