/**下午3:09:20
 * @author zhangyh2
 * MainController.java
 * TODO
 */
package cn.com.shtr.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.com.alipay.AlipayConfig;
import cn.com.bean.Action_Theme;
import cn.com.bean.Cart;
import cn.com.bean.ClientCompHomePage;
import cn.com.bean.ClientTheme;
import cn.com.bean.ClientVideo;
import cn.com.bean.MainCarouselModel;
import cn.com.bean.MainMenuModel;
import cn.com.bean.MainMessageModel;
import cn.com.bean.UserAddress;
import cn.com.bean.UserEcAccount;
import cn.com.bean.UserInfoData;
import cn.com.service.MainService;
import cn.com.shtr.model.BaseHomePageModel;
import cn.com.shtr.model.BaseModel;
import cn.com.shtr.model.ECLoginUser;
import cn.com.shtr.model.MainMessageBase;
import cn.com.util.ECDataHelper;
import cn.com.util.ReadFromFile;
import cn.com.util.UserToken;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.cloopen.rest.sdk.CCPRestSDK;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * @author zhangyh2 MainController 下午3:09:20 TODO
 *         客户端进行调用的唯一的接口入口,此类完整接入SSM框架，对数据库操作，增删查改，一致进行。可以处理高并发。每个
 */
@Service
@RequestMapping("/api")
public class MainController {

	protected String TAG = getClass().getSimpleName();

	@Autowired
	private MainService service;

	@RequestMapping(value = "/goods", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MainMessageBase findGoods(@Param("goods") String goods,
			@Param("page") String page) {
		System.out.println(TAG + "--findGoods--" + goods + "--" + page);
		if (service.findGoods() == null && service.findMenu() == null) {
			return new MainMessageBase(201, "没有查找到数据", null, null);
		} else {
			return new MainMessageBase(200, "数据返回成功", service.findGoods(),
					service.findMenu());
		}
	}

	@RequestMapping(value = "/pogo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MainMessageBase postGoods(@RequestBody Map<String, String> name) {
		service.findByTel("");
		System.out.println(TAG + "--postGoods--" + name);
		return new MainMessageBase(201, "没有查找到数据", null, null);
	}

	/**
	 * 下午3:22:25
	 * 
	 * @author zhangyh2 TODO 测试接口
	 */
	@ResponseBody
	@RequestMapping("/cart")
	public BaseModel<List<Cart>> toIndex(HttpServletRequest request) {
		System.out.println(TAG + "toIndex");
		if (request.getParameter("id") != null) {
			service.findCarousel();
			service.findGoods();
			List<Cart> cars = this.service.getAllUser();
			System.out.println(TAG + cars);
			BaseModel<List<Cart>> model = new BaseModel<List<Cart>>(200,
					"数据返回成功", cars);
			return model/* "showUser" */;
		} else {
			List<MainCarouselModel> car = service.findCarousel();
			List<MainMessageModel> msgs = service.findGoods();
			List<Cart> cars = this.service.getAllUser();
			System.out.println(TAG + cars + car + msgs);
			BaseModel<List<Cart>> model = new BaseModel<List<Cart>>(200,
					"数据返回成功", cars);
			return model/* "showUser" */;
		}
	}

	/**
	 * 下午2:17:47
	 * 
	 * @author zhangyh2 TODO 客户端和服务器时间同步接口
	 */
	@ResponseBody
	@RequestMapping("/stime")
	public Long serviceTime() {
		return System.currentTimeMillis();
	}

	/**
	 * 下午2:18:08
	 * 
	 * @author zhangyh2 TODO 客户端主题版本号接口
	 */
	@ResponseBody
	@RequestMapping("/theme_version")
	public String havMainVersion(HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("version.dl");
		System.out.println(ReadFromFile.readFileByLines(path).get("version"));
		return ReadFromFile.readFileByLines(path).get("theme_version");
	}

	/**
	 * 下午2:18:30
	 * 
	 * @author zhangyh2 TODO 修改主题
	 */
	@ResponseBody
	@RequestMapping("/themechange")
	public BaseModel<List<ClientTheme>> isSetClientThemeChange() {
		List<ClientTheme> thems = service.findClienTheme();
		if (thems != null && thems.size() > 0) {
			return new BaseModel<List<ClientTheme>>(200, "主题返回成功", thems);
		} else {
			return new BaseModel<List<ClientTheme>>(201, "没有新主题", null);
		}

	}

	/**
	 * 下午2:18:39
	 * 
	 * @author zhangyh2 TODO 客户端首页版本接口
	 */
	@ResponseBody
	@RequestMapping("/home_version")
	public String homeMainVersion(HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("version.dl");
		System.out.println(ReadFromFile.readFileByLines(path).get("version"));
		return ReadFromFile.readFileByLines(path).get("home_version");
	}

	/**
	 * 下午2:18:53
	 * 
	 * @author zhangyh2 TODO 修改首页
	 */
	@RequestMapping("/comphome")
	@ResponseBody
	public BaseHomePageModel compHomePage() {
		// TODO Auto-generated method stub
		List<ClientCompHomePage> home = service.findClientHomePage();
		List<ClientCompHomePage> top = new ArrayList<ClientCompHomePage>();
		List<ClientCompHomePage> center = new ArrayList<ClientCompHomePage>();
		List<ClientCompHomePage> bottom = new ArrayList<ClientCompHomePage>();
		if (home != null && home.size() > 0) {

			for (ClientCompHomePage client : home) {
				switch (client.getCompAction()) {
				case 0:
					top.add(client);
					break;
				case 1:
					center.add(client);
					break;
				case 2:
					bottom.add(client);
					break;
				default:
					break;
				}
			}
			return new BaseHomePageModel(200, "数据查找完成", top, center, bottom);
		} else {
			return new BaseHomePageModel(201, "没有查找到数据", null, null, null);
		}
	}

	/**
	 * 下午3:22:11
	 * 
	 * @author zhangyh2 TODO 获取首页广告接口
	 */
	@RequestMapping(value = "/homebannar", method = RequestMethod.GET)
	@ResponseBody
	public BaseModel<List<MainCarouselModel>> homebannar(
			@Param("bannar") String bannar) {
		// TODO Auto-generated method stub
		System.out.println(bannar + "homebannar is run");
		List<MainCarouselModel> cou = service.findCarousel();
		if (cou != null && cou.size() > 0) {
			return new BaseModel<List<MainCarouselModel>>(200, "广告查找成功",
					service.findCarousel());
		} else {
			return new BaseModel<List<MainCarouselModel>>(201, "暂无数据", null);
		}

	}

	/**
	 * 下午3:21:57
	 * 
	 * @author zhangyh2 TODO获取首页商品接口
	 */
	@RequestMapping("/homegoods")
	@ResponseBody
	public MainMessageBase homegoods(@Param("goods") String goods,
			@Param("page") String page) {
		// TODO Auto-generated method stub
		System.out.println(goods + "homegoods is run" + page);
		List<MainMessageModel> gos = service.findGoods();

		List<MainMenuModel> menu = service.findMenu();

		if (gos != null && gos.size() > 0 && menu != null && menu.size() > 0) {
			return new MainMessageBase(200, "商品查找成功", service.findGoods(),
					service.findMenu());
		} else {
			return new MainMessageBase(200, "暂无数据", service.findGoods(),
					service.findMenu());
		}
	}

	/**
	 * 上午10:42:06
	 * 
	 * @author zhangyh2 TODO 用户登录接口
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public BaseModel<ECLoginUser> userLogin(
			@RequestBody Map<String, String> names) {
		System.out.println(names.get("username") + names.get("password")
				+ names.get("sim"));
		UserInfoData info = service.findUser(names.get("username"));
		if (info != null) {
			UserEcAccount account = service.findByTel(names.get("username"));
			UserInfoData infodata = service.findUserByLogin(
					names.get("username"), names.get("password"));
			if (infodata != null && account != null) {
				// 用户存在

				infodata.setToken(Base64.encode((names.get("username"))
						.getBytes()));
				if (names.get("sim").contains(infodata.getSim())) {
					infodata.setSame("true");
					infodata.setLogin("true");
				} else {
					infodata.setSame("false");
					infodata.setLogin("false");
				}
				int res = service.updataUserInfo(infodata);
				System.out.println(res + "用户登录成功");
				ECLoginUser user = new ECLoginUser(infodata, account);
				return new BaseModel<ECLoginUser>(200, "登录成功", user);
			} else {
				// 用户名密码错误
				return new BaseModel<ECLoginUser>(202, "用户名密码错误", null);
			}
		} else {
			// 用户不存在
			return new BaseModel<ECLoginUser>(201, "用户不存在,请先注册", null);
		}
	}

	/**
	 * 上午10:32:12
	 * 
	 * @author zhangyh2 TODO 用户登录完成后，由于条件差异，有的需要进行验证。故而验证完毕后。才会进行登录情况改变
	 */
	@ResponseBody
	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	public void loginCheck(@RequestBody Map<String, String> login) {
		UserInfoData infodata = service.findUserByLogin(login.get("username"),
				login.get("password"));
		infodata.setSim(login.get("sim"));
		infodata.setSame("true");
		infodata.setLogin("true");
		int res = service.updataUserInfo(infodata);
		System.out.println(res + "用户验证登录成功");
	}

	/**
	 * 下午4:35:27
	 * 
	 * @author zhangyh2 TODO 用户退出接口
	 */
	@ResponseBody
	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public BaseModel<UserInfoData> userLoginout(@Param("tel") String tel,
			@RequestHeader("token") String token,
			@RequestHeader("key") String key) {
		System.out.println(tel + token + key);
		switch (UserToken.checkToken(token, key)) {
		case UserToken.OKTOKEN:
			// 正常的token
			UserInfoData info = service.findUser(tel);
			if (info != null) {
				info.setLogin("false");
				int src = service.updataUserInfo(info);
				System.out.println(src + "loginout");
				if (src == 1) {
					return new BaseModel<UserInfoData>(200, "用户退出登录", null);
				} else {
					return new BaseModel<UserInfoData>(201, "数据库修改失败", null);
				}
			} else {
				return new BaseModel<UserInfoData>(201, "参数缺失", null);
			}
		case UserToken.NULLPARAMS:
			// 空参数
			return new BaseModel<UserInfoData>(201, "参数缺失", null);
		case UserToken.ERRORPARAMS:
			// token错误
			return new BaseModel<UserInfoData>(201, "token错误", null);
		case UserToken.OUTTOKEN:
			// token失效
			return new BaseModel<UserInfoData>(201, "token失效", null);
		default:
			return new BaseModel<UserInfoData>(201, "其他问题", null);
		}
	}

	/**
	 * 下午4:21:47
	 * 
	 * @author zhangyh2 TODO 用户注册接口
	 */
	@RequestMapping(value = "/register")
	@ResponseBody
	public BaseModel<UserInfoData> userRegister(
			@RequestBody() Map<String, String> params) {
		synchronized (this) {
			System.out.println(params.get("tel") + params.get("pass"));
			// 由于要同步云通讯，故而进行绑定。pass
			UserInfoData user = service.findUser(params.get("tel"));
			double money = 0;
			if (user == null) {
				// 用户不存在，可以注册
				// 对云通讯进行初始化。
				HashMap<String, Object> result = null;
				CCPRestSDK restAPI = new CCPRestSDK();
				restAPI.init(ECDataHelper.HTTPHOST, ECDataHelper.PORT);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
				restAPI.setAccount(ECDataHelper.ACCOUNTSID,
						ECDataHelper.ACCOUNTTOKEN);// 初始化主帐号和主帐号TOKEN
				restAPI.setAppId(ECDataHelper.AppId);// 初始化应用ID
				// 插入数据库
				int insUser = service.insertRegistUser(new UserInfoData(params
						.get("tel"), params.get("pass"), params.get("tel"), "",
						"", money, "", "", params.get("tel"), "", "false"));
				System.out.println(insUser + "用户资料插入数据库");
				// 注册完成后，获取对应用户资料
				result = restAPI.createSubAccount(params.get("tel"));
				System.out.println("云通讯生成用户" + result);
				if ("000000".equals(result.get("statusCode"))) {
					// 正常返回输出data包体信息（map）
					@SuppressWarnings("unchecked")
					HashMap<String, Object> data = (HashMap<String, Object>) result
							.get("data");
					String ec = data.get("SubAccount").toString();
					UserEcAccount ecUsers = showECUser(params.get("tel"), ec);
					// 保存用户信息
					int insEc = service.insertRegistEcAccount(ecUsers);
					System.out.println(insEc + "用户云通讯账号插入数据库");
				} else {
					// 异常返回输出错误码和错误信息
					System.out.println(result.get("statusCode") + ""
							+ result.get("statusMsg"));
				}
				return new BaseModel<UserInfoData>(200, "注册完成",
						new UserInfoData());
			} else {
				// 用户存在，直接登录
				return new BaseModel<UserInfoData>(202, "该用户已注册，请直接登录", null);
			}

		}
	}

	/**
	 * 下午3:32:14
	 * 
	 * @author zhangyh2 TODO 通过解析字符串。获取用户。
	 * @param ec2
	 */
	private UserEcAccount showECUser(String name, String ec) {
		// TODO Auto-generated method stub
		String subAccountSid = null;
		String voipAccount = null;
		String dateCreated = null;
		String voipPwd = null;
		String subToken = null;
		if (ec.contains("subAccountSid=")) {
			subAccountSid = ec.split("subAccountSid=")[1].split(",")[0].trim();
		}
		if (ec.contains("voipAccount=")) {
			voipAccount = ec.split("voipAccount=")[1].split(",")[0].trim();
		}
		if (ec.contains("dateCreated=")) {
			dateCreated = ec.split("dateCreated=")[1].split(",")[0].trim();
		}
		if (ec.contains("voipPwd=")) {
			voipPwd = ec.split("voipPwd=")[1].split(",")[0].trim();
		}
		if (ec.contains("subToken=")) {
			subToken = ec.split("subToken=")[1].split("}")[0].trim();
		}
		return new UserEcAccount(name, subAccountSid, voipAccount, dateCreated,
				voipPwd, subToken);
	}

	/**
	 * 下午5:09:15
	 * 
	 * @author zhangyh2 TODO 修改用户信息接口
	 */
	@ResponseBody
	@RequestMapping(value = "/userinfo", method = RequestMethod.POST)
	public BaseModel<ECLoginUser> changeUserInfo(
			@RequestHeader("token") String token,
			@RequestHeader("key") String key,
			@RequestBody Map<String, String> params) {
		System.out.println(params);
		switch (UserToken.checkToken(token, key)) {
		case UserToken.OKTOKEN:
			// 正常的token
			UserInfoData infoData = service.findUser(params.get("tel"));
			if (params.get("name") != null) {
				infoData.setName(params.get("name"));
			} else if (params.get("sex") != null) {
				infoData.setSex(params.get("sex"));
			} else if (params.get("card") != null) {
				infoData.setIdcard(params.get("card"));
			}
			int res = service.updataUserInfo(infoData);
			System.out.println(res + "修改成功,");
			UserInfoData info = service.findUser(params.get("tel"));
			UserEcAccount acc = service.findByTel(params.get("tel"));
			System.out.println(info.getName() + info.getSex());

			return new BaseModel<ECLoginUser>(200, "修改成功", new ECLoginUser(
					info, acc));
		case UserToken.NULLPARAMS:
			// 空参数
			return new BaseModel<ECLoginUser>(201, "参数缺失", null);
		case UserToken.ERRORPARAMS:
			// token错误
			return new BaseModel<ECLoginUser>(201, "token错误", null);
		case UserToken.OUTTOKEN:
			// token失效
			return new BaseModel<ECLoginUser>(201, "token失效", null);
		default:
			return new BaseModel<ECLoginUser>(201, "其他问题", null);
		}
	}

	/**
	 * 上午11:49:32
	 * 
	 * @author zhangyh2 TODO 用户图像上传接口
	 */
	@RequestMapping(value = "/upimage", method = RequestMethod.POST)
	@ResponseBody
	public BaseModel<ECLoginUser> headImageUpload(HttpServletRequest request)
			throws ServletException {
		String user = request.getParameter("user");
		List<MultipartFile> files = ((MultipartHttpServletRequest) request)
				.getFiles("file");
		String pathcacth = request.getSession().getServletContext()
				.getRealPath("");
		String path = pathcacth.split("shtr")[0] + "\\ImageIcon\\";
		for (int i = 0; i < files.size(); i++) {
			MultipartFile file = files.get(i);
			String name = file.getOriginalFilename();
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(path + user + name)));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					return new BaseModel<ECLoginUser>(201, "图片上传失败", null);
				}
				String icon = request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort() + "/ImageIcon/" + user + name;
				UserInfoData data = service.findUser(user);
				data.setIcon(icon);
				service.updataUserInfo(data);
			} else {
				return new BaseModel<ECLoginUser>(201, "图片为空", null);
			}
		}
		return new BaseModel<ECLoginUser>(200, "图片上传成功", new ECLoginUser(
				service.findUser(user), service.findByTel(user)));
	}

	/**
	 * 下午2:11:48
	 * 
	 * @author zhangyh2 TODO 查找用户所有地址接口
	 */
	@RequestMapping(value = "/address", method = RequestMethod.GET)
	@ResponseBody
	public BaseModel<List<UserAddress>> findAddressByUser(
			@RequestHeader("token") String token,
			@RequestHeader("key") String key, @Param("tel") String tel,
			@Param("page") String page) {

		switch (UserToken.checkToken(token, key)) {
		case UserToken.OKTOKEN:
			if (tel != null && tel.length() > 0) {
				// 参数正常
				List<UserAddress> address = service.findUserAddress(tel);
				if (address != null && address.size() > 0) {
					return new BaseModel<List<UserAddress>>(200, "查找成功",
							address);
				} else {
					return new BaseModel<List<UserAddress>>(202, "查无数据", null);
				}
			} else {
				// 参数缺失
				return new BaseModel<List<UserAddress>>(201, "参数缺失", null);
			}
		case UserToken.NULLPARAMS:
			// 空参数
			return new BaseModel<List<UserAddress>>(201, "参数缺失", null);
		case UserToken.ERRORPARAMS:
			// token错误
			return new BaseModel<List<UserAddress>>(201, "token错误", null);
		case UserToken.OUTTOKEN:
			// token失效
			return new BaseModel<List<UserAddress>>(201, "token失效", null);
		default:
			return new BaseModel<List<UserAddress>>(201, "其他问题", null);
		}

	}

	/**
	 * 下午2:11:38
	 * 
	 * @author zhangyh2 TODO 更新用户地址接口。
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/address", method = RequestMethod.POST)
	@ResponseBody
	public BaseModel<UserAddress> updataAddressByUser(
			@RequestHeader("token") String token,
			@RequestHeader("key") String key,
			@ModelAttribute("as") UserAddress as,
			@RequestBody Map<String, String> params)
			throws UnsupportedEncodingException {
		System.out.println(as);
		System.out.println(params);
		switch (UserToken.checkToken(token, key)) {
		case UserToken.OKTOKEN:
			if (params.get("flag").equals("0")) {
				// 新增地址
				UserAddress address = new UserAddress();
				if (params.get("id") != null && params.get("id").length() > 0) {
					address.setId(Integer.parseInt(params.get("id")));
				}
				address.setName(params.get("name"));
				address.setTel(params.get("tel"));
				address.setProvince(new String(params.get("province")
						.getBytes(), "UTF-8"));
				address.setCity(params.get("city"));
				address.setDistrict(params.get("district"));
				address.setZipcode(params.get("zipcode"));
				address.setUsertel(params.get("usertel"));
				service.insertAddress(address);
				return new BaseModel<UserAddress>(200, "新增地址完成", address);
			} else {
				// 修改地址
				UserAddress address = new UserAddress();
				address.setId(Integer.parseInt(params.get("id")));
				address.setName(params.get("name"));
				address.setTel(params.get("tel"));
				address.setProvince(params.get("province"));
				address.setCity(params.get("city"));
				address.setDistrict(params.get("district"));
				address.setZipcode(params.get("zipcode"));
				address.setUsertel(params.get("usertel"));
				service.updateAddress(address);

				List<UserAddress> ad = service.findAllAddress();
				for (UserAddress userAddress : ad) {
					System.out
							.println(userAddress.getProvince()
									+ userAddress.getCity()
									+ userAddress.getDistrict());
				}

				return new BaseModel<UserAddress>(200, "修改完成",
						service.findAddress(Integer.parseInt(params.get("id"))));
			}
		case UserToken.NULLPARAMS:
			// 空参数
			return new BaseModel<UserAddress>(201, "参数缺失", null);
		case UserToken.ERRORPARAMS:
			// token错误
			return new BaseModel<UserAddress>(201, "token错误", null);
		case UserToken.OUTTOKEN:
			// token失效
			return new BaseModel<UserAddress>(201, "token失效", null);
		default:
			return new BaseModel<UserAddress>(201, "其他问题", null);
		}

	}

	/**
	 * 下午2:11:48
	 * 
	 * @author zhangyh2 TODO 删除地址接口
	 */
	@RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
	@ResponseBody
	public BaseModel<UserAddress> delAddressByUser(
			@RequestHeader("token") String token,
			@RequestHeader("key") String key,
			@RequestBody Map<String, String> params) {

		switch (UserToken.checkToken(token, key)) {
		case UserToken.OKTOKEN:
			UserAddress address = new UserAddress();
			address.setId(Integer.parseInt(params.get("id")));
			address.setName(params.get("name"));
			address.setTel(params.get("tel"));
			address.setProvince(params.get("province"));
			address.setCity(params.get("city"));
			address.setDistrict(params.get("district"));
			address.setZipcode(params.get("zipcode"));
			address.setUsertel(params.get("usertel"));
			int src = service.deleteAddress(address);
			if (src == 1) {
				return new BaseModel<UserAddress>(200, "修改完成", address);
			} else {
				return new BaseModel<UserAddress>(201, "修改失败", address);
			}
		case UserToken.NULLPARAMS:
			// 空参数
			return new BaseModel<UserAddress>(201, "参数缺失", null);
		case UserToken.ERRORPARAMS:
			// token错误
			return new BaseModel<UserAddress>(201, "token错误", null);
		case UserToken.OUTTOKEN:
			// token失效
			return new BaseModel<UserAddress>(201, "token失效", null);
		default:
			return new BaseModel<UserAddress>(201, "其他问题", null);
		}
	}

	@RequestMapping(value = "/alipay", method = RequestMethod.POST)
	@ResponseBody
	public String alipaydo() {
		JSONObject object = new JSONObject();
		object.put("primary_industry_name", "IT科技/IT软件与服务");
		object.put("primary_industry_code", "10001/20102");
		object.put("secondary_industry_code", "10001/20102");
		object.put("secondary_industry_name", "IT科技/IT软件与服务");
		// 实例化客户端
		AlipayClient client = new DefaultAlipayClient(
				"https://openapi.alipay.com/gateway.do", AlipayConfig.partner,
				AlipayConfig.private_key, "json", "GBK",
				AlipayConfig.ali_public_key);
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
		AlipayOpenPublicTemplateMessageIndustryModifyRequest request = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数
		// 此次只是参数展示，未进行字符串转义，实际情况下请转义
		System.out.println("\"" + object.toString() + "\"");
		request.setBizContent("\"" + object.toString() + "\"");
		AlipayOpenPublicTemplateMessageIndustryModifyResponse response = null;
		try {
			response = client.execute(request);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 调用成功，则处理业务逻辑
		if (response == null) {
			return "服务器异常";
		} else {
			if (response.isSuccess()) {
				return "返回成功";
			} else {
				return "返回失败";
			}
		}
	}

	/**
	 * 上午10:28:32
	 * 
	 * @author zhangyh2 TODO 获取网络视频连接地址
	 */
	@RequestMapping("/video")
	@ResponseBody
	public BaseModel<List<ClientVideo>> getVideos() {
		List<ClientVideo> videos = service.findClienVideo();
		if (videos != null && videos.size() > 0) {
			return new BaseModel<List<ClientVideo>>(200, "获取视频集合", videos);
		} else {
			return new BaseModel<List<ClientVideo>>(201, "暂无视频", null);
		}
	}

	/**
	 * 上午9:50:38
	 * 
	 * @author zhangyh2 TODO 获取活动页面 商品列表
	 */
	@RequestMapping("/act")
	@ResponseBody
	public BaseModel<List<Action_Theme>> getActions(
			@RequestHeader("token") String token,
			@RequestHeader("key") String key, @Param("page") int page) {
		System.out.println(token + key + page);
		// 通过页码在数据库进行查询。获取数据列表
		// for (int a = 0; a < ConsHttpUrl.IMAGES.length; a++) {
		// String id = 1 + "" + a + new Random().nextInt(9)
		// + new Random().nextInt(9) + new Random().nextInt(9)
		// + new Random().nextInt(9);
		// Action_Theme action_Theme = new Action_Theme();
		// action_Theme.setId(Integer.parseInt(id));
		// action_Theme.setDescription(id + "description");
		// action_Theme.setImagepath(ConsHttpUrl.IMAGES[a]);
		// service.insertAction(action_Theme);
		// }
		return new BaseModel<List<Action_Theme>>(200, "第" + page + "页数据返回成功",
				service.findAction_Themes(page));
	}
}
