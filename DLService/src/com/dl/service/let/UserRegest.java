package com.dl.service.let;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.dl.service.common.ECDataHelper;
import com.dl.service.common.JsonUtil;
import com.dl.service.common.ServletHelper;
import com.dl.service.db.DBRegister;
import com.dl.service.ecs.DBECuser;
import com.dl.service.ecs.ECUsers;
import com.dl.service.model.BaseModel;
import com.dl.service.model.UserInfoData;
import com.mongodb.DBObject;

/**
 * Servlet implementation class UserRegest
 */
@WebServlet(description = "用户注册调用接口。", urlPatterns = { "/UserRegest" })
public class UserRegest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String TAG = getClass().getSimpleName();
	private int i = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegest() {
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
	 * @author zhangyh2 TODO
	 */
	@SuppressWarnings("unchecked")
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
			// 对云通讯进行初始化。
			HashMap<String, Object> result = null;
			CCPRestSDK restAPI = new CCPRestSDK();
			restAPI.init(ECDataHelper.HTTPHOST, ECDataHelper.PORT);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
			restAPI.setAccount(ECDataHelper.ACCOUNTSID,
					ECDataHelper.ACCOUNTTOKEN);// 初始化主帐号和主帐号TOKEN
			restAPI.setAppId(ECDataHelper.AppId);// 初始化应用ID

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
			String pass = null;

			try {
				JSONObject object = new JSONObject(res);
				name = object.getString("name");
				pass = object.getString("pass");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(name + "---" + pass);
			System.out.println(TAG + "注册接口调用了" + i + "次");
			// 将用户注册数据写入数据库
			DBObject info = DBRegister.selectOne(name);
			String json = null;
			// 用户金钱从另外一个表格获取
			String money = "0";
			if (info == null) {
				if (name != null && pass != null) {
					BaseModel<UserInfoData> mo = new BaseModel<UserInfoData>(
							200, "注册完成", new UserInfoData());
					json = JsonUtil.pojo2Json(mo);
					DBRegister.insert(new UserInfoData(name, "", name, "", "",
							money, "", "", pass, name, "false"));

					// 注册完成后，获取对应用户资料
					result = restAPI.createSubAccount(name);
					System.out.println("云通讯生成用户" + result);
					if ("000000".equals(result.get("statusCode"))) {
						// 正常返回输出data包体信息（map）
						HashMap<String, Object> data = (HashMap<String, Object>) result
								.get("data");
						String ec = data.get("SubAccount").toString();
						ECUsers ecUsers = DBECuser.showECUser(name, ec);
						// 保存用户信息
						DBECuser.insertEC(ecUsers);
					} else {
						// 异常返回输出错误码和错误信息
						System.out.println(result.get("statusCode") + ""
								+ result.get("statusMsg"));
					}

				} else {
					// 参数缺失
					BaseModel<UserInfoData> mo = new BaseModel<UserInfoData>(
							201, "参数缺失", null);
					json = JsonUtil.pojo2Json(mo);
				}
			} else {
				BaseModel<UserInfoData> mo = new BaseModel<UserInfoData>(202,
						"该用户已注册，请直接登录", null);
				json = JsonUtil.pojo2Json(mo);
			}
			response.getWriter().write(json);
		}
	}
}
