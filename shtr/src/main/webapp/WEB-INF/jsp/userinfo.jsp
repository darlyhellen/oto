<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	import="java.util.*,java.util.List,cn.com.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<link rel="stylesheet" type="text/css" href="/shtr/html/css/body.css">
<script src="/shtr/html/jquery/jquery-1.12.3.js" type="text/javascript"></script>
<script src="/shtr/html/jquery/jquery-1.12.3.min.js"></script>
</head>
<body>

	<h3 align="center">
		<font color="#55994433" size="5" face="隶书">用户信息列表</font>
	</h3>
	<div align="center">
		<table border="1" cellpadding="0" cellspacing="0"
			bordercolor="#e1e1e1">
			<tr>
				<th align="center" width="5%"><label> name </label></th>
				<th align="center" width="5%"><label>icon </label></th>
				<th align="center" width="5%"><label>tel</label></th>
				<th align="center" width="5%"><label> sex </label></th>
				<th align="center" width="5%"><label> idCard </label></th>
				<th align="center" width="5%"><label> money </label></th>
				<th align="center" width="5%"><label> token </label></th>
				<th align="center" width="5%"><label> same </label></th>
				<th align="center" width="5%"><label> pass </label></th>
				<th align="center" width="5%"><label>sim</label></th>
				<th align="center" width="5%"><label>login</label></th>
			</tr>
			<%
				int xs = 0;
			%>
			<%
				@SuppressWarnings("unchecked")
				List<UserInfoData> data = (List<UserInfoData>) request
						.getAttribute("userinfo");
				for (UserInfoData inf : data) {
					xs++;
			%>
			<tr>

				<td align="center"><label id="name<%=xs%>"> <%
 	out.print(inf.getName());
 %>
				</label></td>
				<td align="center"><label id="icon<%=xs%>"> <%
 	out.print(inf.getIcon());
 %>
				</label></td>
				<td align="center"><label id="tel<%=xs%>"> <%
 	out.print(inf.getTel());
 %>
				</label></td>
				<td align="center"><label id="sex<%=xs%>"> <%
 	out.print(inf.getSex());
 %>
				</label></td>
				<td align="center"><label id="idCard<%=xs%>"> <%
 	out.print(inf.getIdcard());
 %>
				</label></td>
				<td align="center"><label id="money<%=xs%>"> <%
 	out.print(inf.getMoney());
 %>
				</label></td>
				<td align="center"><label id="token<%=xs%>"> <%
 	out.print(inf.getToken());
 %>
				</label></td>
				<td align="center"><label id="same<%=xs%>"> <%
 	out.print(inf.getSame());
 %>
				</label></td>
				<td align="center"><label id="pass<%=xs%>"> <%
 	out.print(inf.getPass());
 %>
				</label></td>
				<td align="center"><label id="sim<%=xs%>"> <%
 	out.print(inf.getSim());
 %>
				</label></td>
				<td align="center"><label id="sim<%=xs%>"> <%
 	out.print(inf.getLogin());
 %>
				</label></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>