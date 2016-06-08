/**下午4:03:20
 * @author zhangyh2
 * ConsHttpUrl.java
 * TODO
 */
package com.darly.dlclent.base;

/**
 * @author zhangyh2 ConsHttpUrl 下午4:03:20 TODO
 */
public class ConsHttpUrl {

	public static final String APPKEY = "8a48b5515099b1880150a2f5f097199c";

	public static final String APPTOKEN = "fc7fc628a959a728a103339dc2f215bb";
	/**
	 * 下午4:05:36 TODO 主域名
	 */
	// public static final String HOST = "http://10.0.2.2:8080/DLService/";
	// public static final String HOSTNEW = "http://10.0.2.2:8080/shtr/";
	/**
	 * 上午11:31:22 TODO 公司测试环境
	 */
	public static final String HOST = "http://172.3.200.228:8080/DLService/";
	public static final String HOSTNEW = "http://172.3.200.228:8080/shtr/";
	/**
	 * 上午11:03:56 TODO 生成环境
	 */
	// public static final String HOST = "http://192.168.0.109:8080/DLService/";

	/**上午11:16:14
	 * TODO 服务器时间
	 */
	public static final String SERVICETIME = HOSTNEW + "api/stime";
	/**上午11:16:14
	 * TODO 主题版本查询接口
	 */
	public static final String THEMEVERSION = HOSTNEW + "api/theme_version";

	/**上午11:16:02
	 * TODO 主题变更
	 */
	public static final String THEMECHANGE = HOSTNEW + "api/themechange";
	/**上午11:16:14
	 * TODO 首页版本查询接口
	 */
	public static final String COMPVERSION = HOSTNEW + "api/home_version";
	/**上午11:15:38
	 * TODO 仿公司首页
	 */
	public static final String COMPHOMEPAGE = HOSTNEW + "api/comphome";
	/**
	 * 下午4:05:22 TODO 用户登陆接口,传递的参数包括<b>username,password</b>
	 */
	public static final String USERLOGIN = HOSTNEW + "api/login";
	/**
	 * 上午10:39:33 TODO 用户登录后验证完成时调用接口。修改服务端用户登录状态
	 */
	public static final String USERLOGINCHECK = HOSTNEW + "api/logincheck";
	public static final String USERLOGINOUT = HOSTNEW + "api/loginout";

	/**
	 * 下午4:06:58 TODO
	 * 用户首页接口，传递的参数分为两类，返回两种结果，第一组<b>bannar</b>,第二组为<b>goods,page</b>
	 */
	public static final String USERHOME = HOST + "api/home";

	/**
	 * 下午4:05:36 TODO 获取首页商品和广告
	 */
	public static final String HOMEBANNAR = HOSTNEW + "api/bannar";
	public static final String HOMEGOODS = HOSTNEW + "api/goods";
	public static final String HOMEBANNARNEW = HOSTNEW + "api/homebannar";
	public static final String HOMEGOODSNEW = HOSTNEW + "api/homegoods";

	/**
	 * 下午1:53:57 TODO 获取用户地址列表接口，传递参数为<b>page</b>
	 */
	public static final String USERADDRESS = HOSTNEW + "api/address";

	/**
	 * 下午1:53:57 TODO 用户注册接口，传递参数为<b>tel,pass</b>
	 */
	public static final String USERREGISTER = HOSTNEW + "api/register";

	/**
	 * 下午1:53:57 TODO 用户注册接口，传递参数为<b>commodityID</b>
	 */
	public static final String GOODSDETAILS = HOST + "api/details";

	/**
	 * 下午1:53:57 TODO 用户注册接口，传递参数为<b>name||sex||card</b>
	 */
	public static final String RESETUSERINFO = HOSTNEW + "api/userinfo";

	/**
	 * 下午1:53:57 TODO 活动页面接口，传递参数为<b>page</b>
	 */
	public static final String ACTTHEME = HOSTNEW + "api/act";

	/**
	 * 下午1:53:57 TODO 会话朋友资料接口，传递参数为<b>voipAccount</b>
	 */
	public static final String GETUSER = HOST + "api/getuser";
	/**
	 * 下午1:53:57 TODO 朋友接口<b>tel</b>
	 */
	public static final String FRIEND = HOST + "api/friend";

	/**
	 * 下午1:53:57 TODO 搜索用戶接口，传递参数为<b>friendName</b>
	 */
	public static final String SEARCHUSER = HOST + "api/searchuser";
	/**
	 * 下午1:53:57 TODO 添加朋友接口<b>userTel,voipAccount</b>
	 */
	public static final String ADDFRIEND = HOST + "api/addfriend";
	/**
	 * 下午1:53:57 TODO 用户上传图像接口，传递参数为<b></b>
	 */
	public static final String UPLOADICON = HOSTNEW + "api/upimage";
	/**
	 * 下午1:53:57 TODO 用户是否在线接口，传递参数为<b></b>
	 */
	public static final String USERONLINE = HOST + "api/online";
	/**
	 * 上午10:46:41 TODO 服务端视频列表接口
	 */
	public static final String CLIENTVIDEO = HOSTNEW + "api/video";
	/**
	 * 下午1:53:57 TODO 首页按钮点击链接
	 */
	public static final String SHOW_LOAD = HOST + "userweb?url=web/case1.html";
	public static final String SHOW_LOAD1 = HOST + "web/case1.html";

	public static final String TEST_JSP = HOSTNEW + "web/mainh";

	public static final String SINGLEWEL = "http://172.3.200.228:8080/shtrImage/welcome/welcome.jpg";

}
