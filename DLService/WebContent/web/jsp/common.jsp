<%@page contentType="text/html; charset=GB2312"%>
<html>
<head>
<title>request����</title>
</head>
<body>
	<%
		request.setCharacterEncoding("gb2312");
		String a = request.getParameter("number1");
		String b = request.getParameter("number2");
		int x = 0;
		int y = 0;
		try {
			x = Integer.parseInt(a);
			y = Integer.parseInt(b);
	%>
	����֮���ǣ�
	<font color="#879234" face="����"> <%=x + y%>
	</font>
	<%
		} catch (NumberFormatException e) {
	%>
	Input Error!
	<%
		}
	%>
	<div>
		<%@include file="usercountinvist.jsp"%>
	</div>
</body>
</html>