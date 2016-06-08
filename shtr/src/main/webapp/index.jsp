<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<link rel="stylesheet" type="text/css" href="/shtr/html/css/body.css">
<script src="/shtr/html/jquery/jquery-1.12.3.js" type="text/javascript"></script>
<script src="/shtr/html/jquery/jquery-1.12.3.min.js"></script>
<title>服务端后台登录页面</title>
</head>
<body>
	<h4 align="center">
		<font color="#55994433">用户登录</font>
	</h4>
	<div>
		<div align="center">
			<img width="100" height="100"
				src="<%=request.getContextPath()%>/html/images/koala.jpg">
		</div>
		<div align="center">
			<form method="post" action="/shtr/server/serlogin" enctype="text/html"
				onsubmit="user_login()">
				用户名称: <input type="text" id="username" name="name" /><br /> <br />
				用户密码: <input type="text" id="userpass" name="pass" /><br /> <br />
				<input type="submit" value="提交" /> <input type="button" value="注册"
					onclick="user_regist()">
			</form>
		</div>
	</div>
</body>

<script type="text/javascript">
	function user_login() {
		var name = document.getElementById("username").value;
		var pass = document.getElementById("userpass").value;
		console.log("name:%s", name);
		console.log("pass:%s", pass);
		if (name == "" || pass == "") {
			alert("用户名密码不能为空");
		} else {
			console.log("执行后续操作:%s", null);
			/* 在这里进行设置，进入那个接口。其实挺好玩的。 */
		}
	}
	function user_regist() {
		window.location.replace("/shtr/home/regist.html");
	}
</script>
</html>