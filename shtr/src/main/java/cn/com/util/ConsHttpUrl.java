/**下午4:03:20
 * @author zhangyh2
 * ConsHttpUrl.java
 * TODO
 */
package cn.com.util;

/**
 * @author zhangyh2 ConsHttpUrl 下午4:03:20 TODO
 */
public class ConsHttpUrl {

	public static final String[] IMAGES = new String[] {
			"http://pic2.ooopic.com/01/01/17/53bOOOPIC4e.jpg",
			"http://pic2.ooopic.com/01/01/17/39bOOOPICe8.jpg",
			"http://pic13.nipic.com/20110424/818468_090858462000_2.jpg",
			"http://thumbs.dreamstime.com/z/%C9%BD%C2%B7%BE%B6-20729104.jpg",
			"http://image.72xuan.com/cases/100305/600_600/1003051017041241.jpg",
			"http://pica.nipic.com/2007-11-14/20071114114452315_2.jpg",
			"http://md.cuncun8.com/media/cc8/upload/68192031/0c67e362be347607a877697f46c5f773/101104142242_2026.jpg",
			"http://pic16.nipic.com/20110824/8169416_135754121000_2.jpg",
			"http://b.hiphotos.bdimg.com/album/w%3D2048/sign=79f7b0c594cad1c8d0bbfb274b066509/5366d0160924ab18de9241dd34fae6cd7a890b57.jpg",
			"http://pic2.ooopic.com/01/01/18/42bOOOPIC6c.jpg" };

	public static final String APPKEY = "8a48b5515099b1880150a2f5f097199c";

	public static final String APPTOKEN = "fc7fc628a959a728a103339dc2f215bb";
	/**
	 * 下午4:05:36 TODO 主域名
	 */
	public static final String HOST = "http://10.0.2.2:8080/DLService/";
	public static final String HOSTNEW = "http://10.0.2.2:8080/shtr/";
	/**
	 * 上午11:31:22 TODO 公司测试环境
	 */
	// public static final String HOST = "http://172.3.200.58:8080/DLService/";
	/**
	 * 上午11:03:56 TODO 生成环境
	 */
	// public static final String HOST = "http://192.168.0.109:8080/DLService/";

	/**
	 * 下午4:05:22 TODO 用户登陆接口,传递的参数包括<b>username,password</b>
	 */
	public static final String USERLOGIN = HOSTNEW + "api/login";

	/**
	 * 下午4:06:58 TODO
	 * 用户首页接口，传递的参数分为两类，返回两种结果，第一组<b>bannar</b>,第二组为<b>goods,page</b>
	 */
	public static final String USERHOME = HOST + "api/home";

	/**
	 * 下午4:05:36 TODO 主域名
	 */
	public static final String HOMEBANNARNEW = HOSTNEW + "api/bannar";
	public static final String HOMEGOODSNEW = HOSTNEW + "api/goods";

	/**
	 * 下午1:53:57 TODO 获取用户地址列表接口，传递参数为<b>page</b>
	 */
	public static final String USERADDRESS = HOST + "api/address";

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
	public static final String ACTTHEME = HOST + "api/act";

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
	public static final String UPLOADICON = HOST + "api/upimage";
	/**
	 * 下午1:53:57 TODO 用户是否在线接口，传递参数为<b></b>
	 */
	public static final String USERONLINE = HOST + "api/online";
	/**
	 * 下午1:53:57 TODO 首页按钮点击链接
	 */
	public static final String SHOW_LOAD = HOST + "userweb?url=web/case1.html";
	public static final String SHOW_LOAD1 = HOST + "web/case1.html";

}
