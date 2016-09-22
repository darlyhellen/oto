/**下午4:03:20
 * @author zhangyh2
 * ConsHttpUrl.java
 * TODO
 */
package com.hellen.base;

/**
 * @author zhangyh2 ConsHttpUrl 下午4:03:20 TODO
 */
public class ConsHttpUrl {

	/**
	 * 上午11:31:22 TODO 公司测试环境
	 */
	public static final String HOSTNEW = "http://172.3.200.228:8080/shtr/";
	/**
	 * 下午3:22:18 TODO用户上传BUG接口，传递参数为<b></b>
	 */
	public static final String EXCEPTIONUPLOAD = HOSTNEW
			+ "api/exceptionupload";
	/**
	 * 上午11:13:10 TODO 首页广告位，传递参数为<b>bannar</b>
	 */
	public static final String BANNER = HOSTNEW + "api/homebannar";
	/**
	 * 上午11:13:12 TODO 用户登录接口，传递参数为<b>username,password,sim</b>
	 */
	public static final String LOGIN_URl = HOSTNEW + "api/login";
	/**
	 * 下午1:53:57 TODO 用户注册接口，传递参数为<b>tel,pass</b>
	 */
	public static final String USERREGISTER = HOSTNEW + "api/register";
	/**
	 * 上午11:13:12 TODO 首页接口，传递参数为<b>group,page</b>
	 */
	public static final String MAIN_FLOWER = HOSTNEW + "api/mainflower";
	/**
	 * 上午11:13:12 TODO 首页接口，传递参数为<b>group,page</b>
	 */
	public static final String VERSIONUPDATE = HOSTNEW + "api/update";
	/**
	 * 上午11:13:12 TODO 
	 */
	public static final String DOWN = HOSTNEW + "server/down";
}
