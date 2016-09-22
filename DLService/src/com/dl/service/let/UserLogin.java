package com.dl.service.let;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

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
import com.dl.service.ecs.ECLoginUser;
import com.dl.service.ecs.ECUsers;
import com.dl.service.model.BaseModel;
import com.dl.service.model.UserInfoData;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Servlet implementation class UserLogin 用户登录接口。使用POST请求，进行登录
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String TAG = getClass().getSimpleName();
	private int i = 0;

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
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		loginOut(request, response);
	}

	/**
	 * 下午1:40:15
	 * 
	 * @author zhangyh2 TODO 用户离线接口
	 */
	private void loginOut(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
		// TODO Auto-generated method stub
		synchronized (this) {// 在java中，每一个对象都有一把锁，这里的this指的就是Servlet对象
			i++;
			// 针对post请求，设置允许接收中文
			request.setCharacterEncoding("UTF-8");
			// 设置可以在页面中响应的内容类型及中文
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			String json = null;
			// 获取Token
			String token = request.getHeader("token");
			String key = request.getHeader("key");
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
						// token正常
						UserInfoData data = DBRegister.FindInfoByToken(Token);
						// 将数据库变更为用户离线
						data.setLogin("false");
						BaseModel<List<UserInfoData>> mo = new BaseModel<List<UserInfoData>>(
								200, "退出登录", null);
						json = JsonUtil.pojo2Json(mo);
						DBRegister.update(data);

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
			String sim = null;

			try {
				JSONObject object = new JSONObject(res);
				name = object.getString("username");
				pass = object.getString("password");
				sim = object.getString("sim");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(name + "---" + pass + "---" + sim);
			System.out.println(TAG + "登录接口调用了" + i + "次");
			UserInfoData infodata = DBRegister.FindInfoByTel(name);

			String json = null;
			if (infodata != null) {
				// 用户存在
				if (name.equals(infodata.getTel())
						&& pass.equals(infodata.getPass())) {
					ECUsers ecUsers = DBECuser.getECUser(name);
					if (ecUsers != null) {
						// 云通讯用户信息正常
						String same = null;
						if (sim.contains(infodata.getSim())) {
							same = "true";
						} else {
							same = "false";
						}
						infodata.setToken(Base64.encode((name).getBytes()));
						infodata.setSame(same);
						infodata.setLogin("true");
						ECLoginUser ecLoginUser = new ECLoginUser(
								infodata.getName(), infodata.getIcon(),
								infodata.getTel(), infodata.getSex(),
								infodata.getIdCard(), infodata.getMoney(),
								infodata.getToken(), infodata.getSame(),
								infodata.getPass(), infodata.getSim(),
								ecUsers.getUsername(),
								ecUsers.getSubAccountSid(),
								ecUsers.getVoipAccount(),
								ecUsers.getDateCreated(), ecUsers.getVoipPwd(),
								ecUsers.getSubToken());
						BaseModel<ECLoginUser> mo = new BaseModel<ECLoginUser>(
								200, "", ecLoginUser);
						json = JsonUtil.pojo2Json(mo);
						DBRegister.update(infodata);
					} else {
						// 尝试重新注册
						// 对云通讯进行初始化。
						HashMap<String, Object> result = null;
						CCPRestSDK restAPI = new CCPRestSDK();
						restAPI.init(ECDataHelper.HTTPHOST, ECDataHelper.PORT);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
						restAPI.setAccount(ECDataHelper.ACCOUNTSID,
								ECDataHelper.ACCOUNTTOKEN);// 初始化主帐号和主帐号TOKEN
						restAPI.setAppId(ECDataHelper.AppId);// 初始化应用ID
						// 注册完成后，获取对应用户资料
						result = restAPI.createSubAccount(name);
						System.out.println("云通讯生成用户" + result);
						if ("000000".equals(result.get("statusCode"))) {
							// 正常返回输出data包体信息（map）
							HashMap<String, Object> data = (HashMap<String, Object>) result
									.get("data");
							// 注册成功后。对其数据进行保存。
							String ec = data.get("SubAccount").toString();
							ECUsers ecUses = DBECuser.showECUser(name, ec);
							// 保存用户信息
							DBECuser.insertEC(ecUses);

							// 云通讯用户信息正常
							String same = null;
							if (sim.contains(infodata.getSim())) {
								same = "true";
							} else {
								same = "false";
							}
							infodata.setToken(Base64.encode((name).getBytes()));
							infodata.setSame(same);

							ECLoginUser ecLoginUser = new ECLoginUser(
									infodata.getName(), infodata.getIcon(),
									infodata.getTel(), infodata.getSex(),
									infodata.getIdCard(), infodata.getMoney(),
									infodata.getToken(), infodata.getSame(),
									infodata.getPass(), infodata.getSim(),
									ecUses.getUsername(),
									ecUses.getSubAccountSid(),
									ecUses.getVoipAccount(),
									ecUses.getDateCreated(),
									ecUses.getVoipPwd(), ecUses.getSubToken());
							BaseModel<ECLoginUser> mo = new BaseModel<ECLoginUser>(
									200, "", ecLoginUser);
							json = JsonUtil.pojo2Json(mo);
							DBRegister.update(infodata);

						} else {
							BaseModel<UserInfoData> mo = new BaseModel<UserInfoData>(
									211, "云平台出现错误", null);
							json = JsonUtil.pojo2Json(mo);
						}
					}
				} else {
					// 密码错误
					BaseModel<UserInfoData> mo = new BaseModel<UserInfoData>(
							201, "用户名密码错误", null);
					json = JsonUtil.pojo2Json(mo);
				}
			} else {
				// 用户不存在
				BaseModel<UserInfoData> mo = new BaseModel<UserInfoData>(202,
						"用户不存在,请先注册", null);
				json = JsonUtil.pojo2Json(mo);
			}
			response.getWriter().write(json);

		}
	}

}
