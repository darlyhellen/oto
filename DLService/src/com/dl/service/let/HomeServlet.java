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
import com.dl.service.db.DBHomePage;
import com.dl.service.model.BaseModel;
import com.dl.service.model.MainCarouselModel;
import com.dl.service.model.MainMenuModel;
import com.dl.service.model.MainMessageBase;
import com.dl.service.model.MainMessageModel;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(description = "轮播请求类，以后整合一体成为首页一个请求。", urlPatterns = { "/HomeServlet" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String TAG = getClass().getSimpleName();
	private int main = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
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
		getFeedBack(request, response);
	}

	/**
	 * 下午4:16:23
	 * 
	 * @author zhangyh2 TODO get请求下的返回结果，两个返回，一个轮播返回，一个商品列表返回
	 */
	private void getFeedBack(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
		// TODO Auto-generated method stub
		synchronized (this) {// 在java中，每一个对象都有一把锁，这里的this指的就是Servlet对象
			System.out.println(System.currentTimeMillis());
			main++;
			// 针对post请求，设置允许接收中文
			request.setCharacterEncoding("UTF-8");
			// 设置可以在页面中响应的内容类型及中文
			response.setContentType("text/html;charset=UTF-8");

			String bannar = request.getParameter("bannar");

			String goods = request.getParameter("goods");

			String page = request.getParameter("page");

			String json = null;

			if ("1".equals(bannar)) {
				// 注释掉的代码块是往Mongo数据库中添加商品的代码块。
				// List<MainCarouselModel> list = new
				// ArrayList<MainCarouselModel>();
				// for (int i = 0; i < ImageData.IMAGES.length; i++) {
				// MainCarouselModel ban = new MainCarouselModel(i, "", "show"
				// + i, ImageData.IMAGES[i]);
				// list.add(ban);
				// DBHomePage.insertBannar(ban);
				// }
				// BaseModel<List<MainCarouselModel>> model = new
				// BaseModel<List<MainCarouselModel>>(
				// 200, "", list);

				List<MainCarouselModel> listBannar = DBHomePage.selectBannar();
				BaseModel<List<MainCarouselModel>> model = new BaseModel<List<MainCarouselModel>>(
						200, "", listBannar);
				json = JsonUtil.pojo2Json(model);
			} else if ("1".endsWith(goods)) {
				// 注释掉的代码块是往Mongo数据库中添加商品的代码块。
				// List<MainMessageModel> data = new
				// ArrayList<MainMessageModel>();
				// List<MainMenuModel> menu = new ArrayList<MainMenuModel>();
				// for (int i = 0; i < ImageData.IMAGES.length; i++) {
				// if (i == 0) {
				// data.add(new MainMessageModel(i, "特卖", "", "", "", 0,
				// 0, 0, "标题"));
				// DBHomePage.insertGoods(new MainMessageModel(i, "特卖",
				// "", "", "", 0, 0, 0, "标题"));
				// } else if (i == 5) {
				// data.add(new MainMessageModel(i, "本周商品", "", "", "", 0,
				// 0, 0, "标题"));
				// DBHomePage.insertGoods(new MainMessageModel(i, "本周商品",
				// "", "", "", 0, 0, 0, "标题"));
				// } else {
				// data.add(new MainMessageModel(i, "", "商品" + i, "描述商品信息"
				// + i, ImageData.IMAGES[i], i * 110, i * 100, i,
				// "商品"));
				// DBHomePage.insertGoods(new MainMessageModel(i, "", "商品"
				// + i, "描述商品信息" + i, ImageData.IMAGES[i],
				// i * 110, i * 100, i, "商品"));
				// }
				// menu.add(new MainMenuModel(i, "菜单" + i, "",
				// ImageData.IMAGES[i]));
				// DBHomePage.insertMenu(new MainMenuModel(i, "菜单" + i, "",
				// ImageData.IMAGES[i]));
				// }
				// MainMessageBase base = new MainMessageBase(200, "", data,
				// menu);

				List<MainMessageModel> listgoods = DBHomePage.selectGoods();
				List<MainMenuModel> listmenu = DBHomePage.selectMenu();
				MainMessageBase base = new MainMessageBase(200, "", listgoods,
						listmenu);
				json = JsonUtil.pojo2Json(base);
			} else {
				BaseModel<List<MainCarouselModel>> model = new BaseModel<List<MainCarouselModel>>(
						201, "参数缺失", null);
				json = JsonUtil.pojo2Json(model);
			}
			System.out.println(bannar + "--" + goods + "--" + page);
			response.getWriter().print(json);
			System.out.println(TAG + "首页接口调用了" + main + "次");
			System.out.println(System.currentTimeMillis());
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
