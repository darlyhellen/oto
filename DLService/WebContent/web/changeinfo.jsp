<%@page import="com.dl.service.model.UserInfoData"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>修改用户信息</title>
</head>
<body>
	<% 
	request.setCharacterEncoding("GB2312");
	String name = (String)request.getParameter("name");
	String icon = (String)request.getParameter("token");
	out.print(name+icon);
	%>
</body>
</html>