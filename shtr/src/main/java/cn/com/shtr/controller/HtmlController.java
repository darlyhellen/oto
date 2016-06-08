/**上午11:35:27
 * @author zhangyh2
 * HtmlController.java
 * TODO
 */
package cn.com.shtr.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangyh2 HtmlController 上午11:35:27 TODO
 */
@Service
@RequestMapping("/web")
public class HtmlController {

	@RequestMapping(value = "/mainh")
	public String mainh() {
		System.out.println("mainh");
		return "/main";
	}

	@RequestMapping(value = "/islogin")
	public String user(@Param("url") String url,
			@RequestHeader("token") String token) {
		// WebView将参数存放在报头中，token可以从header中提取，URL直接附加的连接后面。可以直接获取。
		System.out.println(url + "url" + token + "token");
		String direct = null;
		if (url != null) {
			if (token != null && token.length() > 0) {
				// 登录状态
				direct = "html/" + url;
			} else {
				// 用户还未登录，提示用户进行登录
				direct = "html/login";
			}
		}
		// SpringMVC请求转发和重定向
		// "forward:/" + direct;
		return "redirect:/" + direct;
	}
}
