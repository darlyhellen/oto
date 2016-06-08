<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
</head>
<body>
	<div align="center">
		欢迎您！网站访问了
		<%!int i = 0;%>
		<%
			i++;
			out.print(i);
		%>次
	</div>
</body>
</html>