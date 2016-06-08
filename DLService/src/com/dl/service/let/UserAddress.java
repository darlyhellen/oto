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
import com.dl.service.db.DBAddress;
import com.dl.service.model.AddressModel;
import com.dl.service.model.BaseModel;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Servlet implementation class UserAddress
 */
@WebServlet(description = "获取用户地址列表的接口", urlPatterns = { "/UserAddress" })
public class UserAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int i = 0;
	private int rud = 0;
	private String TAG = getClass().getSimpleName();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAddress() {
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
		getfeedback(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		postfeedback(request, response);
	}

	/**
	 * 下午2:31:40
	 * 
	 * @author zhangyh2 TODO 处理新增。或修改地址
	 */
	private void postfeedback(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
		// TODO Auto-generated method stub
		synchronized (this) {// 在java中，每一个对象都有一把锁，这里的this指的就是Servlet对象
			rud++;
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
			String flag = null;
			String _id = null;
			String name = null;
			String tel = null;
			String province = null;
			String city = null;
			String district = null;
			String zipcode = null;
			try {
				JSONObject object = new JSONObject(res);
				flag = object.getString("flag");
				_id = object.getString("_id");
				name = object.getString("name");
				tel = object.getString("tel");
				province = object.getString("province");
				city = object.getString("city");
				district = object.getString("district");
				zipcode = object.getString("zipcode");
				
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(flag + name + tel + province + city + district
					+ zipcode);
			// 获取Token
			String token = request.getHeader("token");
			String key = request.getHeader("key");
			String json = null;
			AddressModel model = new AddressModel(_id, name, tel, province,
					city, district, zipcode, "");

			if (token != null && key != null) {
				String to = new String(Base64.decode(token));
				String ke = new String(Base64.decode(key));
				if (to.contains(ke)) {
					String time = to.substring(to.length() - ke.length(),
							to.length());
					String Token = to.substring(0, to.length() - ke.length());
					System.out.println(Token);
					if (System.currentTimeMillis() / 1000
							- Integer.parseInt(time) < ServletHelper.TOKENTIME) {
						model.setToken(Token);
						// token正常
						switch (Integer.parseInt(flag)) {
						case 0:
							// 新增地址
							if (DBAddress.insert(model)) {
								BaseModel<AddressModel> mo = new BaseModel<AddressModel>(
										200, "用户新增成功", model);
								json = JsonUtil.pojo2Json(mo);
							} else {
								BaseModel<AddressModel> mo = new BaseModel<AddressModel>(
										201, "用户新增失败", model);
								json = JsonUtil.pojo2Json(mo);
							}
							break;
						case 1:
							// 修改地址
							if (DBAddress.update(model)) {
								BaseModel<AddressModel> mo = new BaseModel<AddressModel>(
										200, "用户新增成功", model);
								json = JsonUtil.pojo2Json(mo);
							} else {
								BaseModel<AddressModel> mo = new BaseModel<AddressModel>(
										201, "用户新增失败", model);
								json = JsonUtil.pojo2Json(mo);
							}
							break;
						case 2:
							// 删除地址
							if (DBAddress.delete(model)) {
								BaseModel<AddressModel> mo = new BaseModel<AddressModel>(
										200, "用户新增成功", model);
								json = JsonUtil.pojo2Json(mo);
							} else {
								BaseModel<AddressModel> mo = new BaseModel<AddressModel>(
										201, "用户新增失败", model);
								json = JsonUtil.pojo2Json(mo);
							}
							break;

						default:
							break;
						}
					} else {
						// token失效
						BaseModel<List<AddressModel>> mo = new BaseModel<List<AddressModel>>(
								203, "token失效", null);
						json = JsonUtil.pojo2Json(mo);
					}
				} else {
					// 错误的token
					BaseModel<List<AddressModel>> mo = new BaseModel<List<AddressModel>>(
							202, "错误的token", null);
					json = JsonUtil.pojo2Json(mo);
				}

			} else {
				// 参数缺失
				BaseModel<List<AddressModel>> mo = new BaseModel<List<AddressModel>>(
						201, "参数缺失", null);
				json = JsonUtil.pojo2Json(mo);
			}
			response.getWriter().write(json);
			System.out.println(TAG + "地址接口调用了" + rud + "次");
		}
	}

	/**
	 * 下午5:43:04
	 * 
	 * @author zhangyh2 TODO 处理获取用户地址列表。
	 * @throws UnsupportedEncodingException
	 */
	private void getfeedback(HttpServletRequest request,
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
			int size = request.getContentLength();
			InputStream is = request.getInputStream();
			byte[] reqBodyBytes = ServletHelper.readBytes(is, size);
			String res = new String(reqBodyBytes, "UTF-8");
			System.out.println(page + "--" + res);
			String token = request.getHeader("token");
			String key = request.getHeader("key");
			String json = null;
			if (token != null && key != null) {
				String to = new String(Base64.decode(token));
				String ke = new String(Base64.decode(key));
				if (to.contains(ke)) {
					String time = to.substring(to.length() - ke.length(),
							to.length());
					String tok = to.substring(0, to.length() - ke.length());
					System.out.println(tok + "用户token");
					if (System.currentTimeMillis() / 1000
							- Integer.parseInt(time) < ServletHelper.TOKENTIME) {
						// token正常
						List<AddressModel> lis = DBAddress
								.selectAllAddress(tok);
						BaseModel<List<AddressModel>> mo = new BaseModel<List<AddressModel>>(
								200, "", lis);
						json = JsonUtil.pojo2Json(mo);
					} else {
						// token失效
						BaseModel<List<AddressModel>> mo = new BaseModel<List<AddressModel>>(
								203, "token失效", null);
						json = JsonUtil.pojo2Json(mo);
					}
				} else {
					// 错误的token
					BaseModel<List<AddressModel>> mo = new BaseModel<List<AddressModel>>(
							202, "错误的token", null);
					json = JsonUtil.pojo2Json(mo);
				}

			} else {
				// 参数缺失
				BaseModel<List<AddressModel>> mo = new BaseModel<List<AddressModel>>(
						201, "参数缺失", null);
				json = JsonUtil.pojo2Json(mo);
			}
			response.getWriter().write(json);
			System.out.println(TAG + "地址接口调用了" + i + "次");
		}
	}

}
