package cn.com.shtr.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.com.bean.MainCarouselModel;
import cn.com.bean.Main_Flower;
import cn.com.bean.UserEcAccount;
import cn.com.bean.UserInfoData;
import cn.com.sax.AppVersionHandler;
import cn.com.sax.SaxParser;
import cn.com.sax.VersionUpdateModel;
import cn.com.service.MainService;
import cn.com.shtr.model.BaseModel;
import cn.com.shtr.model.ECLoginUser;
import cn.com.util.ECDataHelper;
import cn.com.util.UserToken;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * @author zhangyh2 FlowerController 上午10:36:06 TODO flower项目接口V1
 */
@Service
@RequestMapping("/flower/api")
public class FlowerController {
	protected String TAG = getClass().getSimpleName();

	@Autowired
	private MainService service;

	/**
	 * 下午2:17:47
	 *
	 * @author zhangyh2 TODO 客户端和服务器时间同步接口
	 */
	@ResponseBody
	@RequestMapping("/stime")
	public Long serviceTime() {
		System.out.println(TAG + "serviceTime");
		return System.currentTimeMillis();
	}

	/**
	 * 上午10:16:40
	 * 
	 * @author zhangyh2 TODO 用户使用手机获取验证码的接口
	 */
	@ResponseBody
	@RequestMapping(value = "/securitycode", method = RequestMethod.POST)
	public BaseModel<String> securityCode(@RequestBody Map<String, String> map) {
		System.out.println(TAG + "securityCode-->" + map.get("tel"));
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			builder.append(new Random().nextInt(9));
		}
		return new BaseModel<String>(200, "请查收验证码", builder.toString());
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
	 * 上午10:42:06
	 *
	 * @author zhangyh2 TODO 用户登录接口
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public BaseModel<UserInfoData> userLogin(
			@RequestBody Map<String, String> names) {
		System.out.println(names.get("username") + names.get("password")
				+ names.get("sim"));
		UserInfoData info = service.findUser(names.get("username"));
		if (info != null) {
			UserInfoData infodata = service.findUserByLogin(
					names.get("username"), names.get("password"));
			if (infodata != null) {
				// 用户存在
				if (infodata.getLogin() != null
						&& infodata.getLogin().length() != 0) {
					infodata.setToken(Base64.encode((names.get("username"))
							.getBytes()));
					if (names.get("sim") != null
							&& names.get("sim").contains(infodata.getSim())) {
						infodata.setSame("true");
						infodata.setLogin("true");
					} else {
						infodata.setSame("false");
						infodata.setLogin("false");
					}
					int res = service.updataUserInfo(infodata);
					System.out.println(res + "用户登录成功");
					return new BaseModel<UserInfoData>(200, "登录成功", infodata);
				} else {
					// 添加一个用户信息审核逻辑
					return new BaseModel<UserInfoData>(203,
							"正在审核您的账户信息，请耐心等候！", null);
				}
			} else {
				// 用户名密码错误
				return new BaseModel<UserInfoData>(202, "用户名密码错误", null);
			}
		} else {
			// 用户不存在
			return new BaseModel<UserInfoData>(201, "用户不存在,请先注册", null);
		}
	}

	/**
	 * 上午10:42:06
	 *
	 * @author zhangyh2 TODO 用户登录接口
	 */
	@ResponseBody
	@RequestMapping(value = "/checkuser", method = RequestMethod.POST)
	public BaseModel<UserInfoData> checkUser(
			@RequestBody Map<String, String> names) {
		System.out.println(names.get("tel"));
		UserInfoData info = service.findUser(names.get("tel"));
		if (info != null) {
			return new BaseModel<UserInfoData>(200, "该用户已注册，请直接登录", info);
		} else {
			// 用户不存在
			return new BaseModel<UserInfoData>(201, "用户不存在,请先注册", null);
		}
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
				UserInfoData newUser = new UserInfoData(params.get("tel"),
						params.get("pass"), params.get("tel"),
						params.get("icon") == null ? "" : params.get("icon"),
						"", money, "", "", params.get("tel"), "", "");
				newUser.setCertificate(params.get("certificate") == null ? ""
						: params.get("certificate"));
				newUser.setSection(params.get("section") == null ? "" : params
						.get("section"));
				newUser.setTitle(params.get("title") == null ? "" : params
						.get("title"));
				SimpleDateFormat sdf = new SimpleDateFormat(
						"YYYY-MM-dd HH:mm:ss");
				String usercreate = sdf.format(new Date());
				System.out.println(params.get("tel") + "---->" + usercreate);
				newUser.setCreatedate(usercreate);
				int insUser = service.insertRegistUser(newUser);
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
				return new BaseModel<UserInfoData>(200, "注册完成", newUser);
			} else {
				// 用户存在，直接登录
				return new BaseModel<UserInfoData>(202, "该用户已注册，请直接登录", null);
			}

		}
	}

	/**
	 * 下午3:32:14
	 *
	 * @param ec2
	 * @author zhangyh2 TODO 通过解析字符串。获取用户。
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
	 * 下午5:09:15
	 *
	 * @author zhangyh2 TODO 修改用户信息接口
	 */
	@ResponseBody
	@RequestMapping(value = "/changepass", method = RequestMethod.POST)
	public BaseModel<UserInfoData> changeUserPass(
			@RequestBody Map<String, String> params) {
		System.out.println(params);
		// 正常的token
		try {
			UserInfoData infoData = service.findUser(params.get("tel"));
			infoData.setPass(params.get("pass"));
			int res = service.updataUserInfo(infoData);
			System.out.println(res + "修改成功,");
			UserInfoData info = service.findUser(params.get("tel"));
			System.out.println(info.getName() + info.getSex());
			return new BaseModel<UserInfoData>(200, "修改成功", info);
		} catch (Exception e) {
			// TODO: handle exception
			return new BaseModel<UserInfoData>(201, "修改失败，请联系工作人员", null);
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
		// 在图片保存之前，应该先剔除以前老的旧的图片。防止空间爆满
		File er = new File(path);
		File fils[] = er.listFiles();
		for (File fil : fils) {
			if (fil.getName().startsWith(user))
				fil.delete();
		}
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
	 * 上午11:49:32
	 *
	 * @author zhangyh2 TODO 用户未注册头像上传接口
	 */
	@RequestMapping(value = "/headicon", method = RequestMethod.POST)
	@ResponseBody
	public BaseModel<String> headImageIcon(HttpServletRequest request)
			throws ServletException {
		String icon = null;
		String user = request.getParameter("user");
		List<MultipartFile> files = ((MultipartHttpServletRequest) request)
				.getFiles("file");
		String pathcacth = request.getSession().getServletContext()
				.getRealPath("");
		String path = pathcacth.split("shtr")[0] + "\\ImageIcon\\";
		// 在图片保存之前，应该先剔除以前老的旧的图片。防止空间爆满
		File er = new File(path);
		File fils[] = er.listFiles();
		for (File fil : fils) {
			if (fil.getName().startsWith(user))
				fil.delete();
		}
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
					return new BaseModel<String>(201, "图片上传失败", null);
				}
				icon = request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + "/ImageIcon/" + user
						+ name;
			} else {
				return new BaseModel<String>(201, "图片为空", null);
			}
		}
		return new BaseModel<String>(200, "图片上传成功", icon);
	}

	/**
	 * 上午11:49:32
	 *
	 * @author zhangyh2 TODO 用户崩溃日志上传接口
	 */
	@RequestMapping(value = "/exceptionupload", method = RequestMethod.POST)
	@ResponseBody
	public BaseModel<String> exceptionUpload(HttpServletRequest request)
			throws ServletException {
		String icon = null;
		List<MultipartFile> files = ((MultipartHttpServletRequest) request)
				.getFiles("file");
		String pathcacth = request.getSession().getServletContext()
				.getRealPath("");
		String path = pathcacth.split("shtr")[0] + "\\UserBug\\";
		File exfile = new File(path);
		if (!exfile.exists()) {
			exfile.mkdir();
		}
		System.out.println(path);
		for (int i = 0; i < files.size(); i++) {
			MultipartFile file = files.get(i);
			String name = file.getOriginalFilename();
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(path + name)));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					return new BaseModel<String>(201, "文件上传失败", null);
				}

				icon = request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + "/UserBug/" + name;
			} else {
				return new BaseModel<String>(201, "文件为空", null);
			}
		}
		return new BaseModel<String>(200, "日志上传成功", icon);
	}

	/**
	 * 上午11:49:32
	 *
	 * @author zhangyh2 TODO 用户执照上传接口
	 */
	@RequestMapping(value = "/certificate", method = RequestMethod.POST)
	@ResponseBody
	public BaseModel<String> userCertificate(HttpServletRequest request)
			throws ServletException {
		String icon = null;
		List<MultipartFile> files = ((MultipartHttpServletRequest) request)
				.getFiles("file");
		String pathcacth = request.getSession().getServletContext()
				.getRealPath("");
		String path = pathcacth.split("shtr")[0] + "\\Certificate\\";
		File exfile = new File(path);
		if (!exfile.exists()) {
			exfile.mkdir();
		}
		System.out.println(path);
		for (int i = 0; i < files.size(); i++) {
			MultipartFile file = files.get(i);
			String name = file.getOriginalFilename();
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(path + name)));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					return new BaseModel<String>(201, "文件上传失败", null);
				}
				icon = request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + "/Certificate/"
						+ name;
			} else {
				return new BaseModel<String>(201, "文件为空", null);
			}
		}
		return new BaseModel<String>(200, "图片上传成功", icon);
	}

	@RequestMapping(value = "/mainflower", method = RequestMethod.GET)
	@ResponseBody
	public BaseModel<List<Main_Flower>> findMainFlower(
			@Param("group") String group, @Param("page") int page) {
		System.out.println("mainflower is run" + group + page);
		List<Main_Flower> main = service.findMain_Flower(group, page);
		if (main != null && main.size() > 0) {
			return new BaseModel<List<Main_Flower>>(200,
					"第" + page + "页数据返回成功", main);
		} else {
			return new BaseModel<List<Main_Flower>>(201, "查无此数据", null);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@ResponseBody
	public BaseModel<VersionUpdateModel> versionUpdate(
			@Param("version") String version, @Param("type") String type,
			@Param("channel") String channel, HttpServletRequest request) {
		System.out.println(version + type + channel);
		// 测试数据一组，进行SAX解析测试。
		String paths = request.getServletContext().getRealPath(
				"androidAppVersion.xml");
		SaxParser factorys = null;
		try {
			factorys = new SaxParser();
			List<VersionUpdateModel> groupList = null;
			int lenth = factorys.getXMLLines(paths);
			for (int i = 0; i < lenth; i++) {
				if (i == (lenth - 1)) {
					groupList = ((AppVersionHandler) factorys.getHandlers(
							"version", paths)).getResult();
				} else {
					factorys.getHandlers("version", paths);
				}
			}
			for (VersionUpdateModel group : groupList) {
				if (channel.equals(group.getSource())) {
					return new BaseModel<VersionUpdateModel>(200, "获取到版本更新的数据",
							group);
				}
			}
			return new BaseModel<VersionUpdateModel>(201, "未找到对应渠道信息", null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new BaseModel<VersionUpdateModel>(202, "查找出错，暂无信息", null);
	}
}
