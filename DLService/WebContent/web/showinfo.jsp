<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java"
	import="java.util.*,java.util.List,com.dl.service.model.*"
	pageEncoding="GB2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312" />
<head>
<script src="web/js/show.js">
</script>
<title>后台管理首页</title>
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
				<th align="center" width="5%"><label>option</label></th>
			</tr>
			<%int xs = 0;%>
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
 	out.print(inf.getIdCard());
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
				<td align="center">
						<form action="web/changeinfo.jsp" method="post">
						<input type="hidden" id="name" name="name">
						<input type="hidden" id="token" name="token">
							<input type="submit" value="提交">
							<input type="button" onclick="infos(<%=xs%>)" value="修改">
						</form>
						</td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<div>
		<h3>计算两数之和</h3>
		<form action="web/jsp/common.jsp" method="post">
			数字：<input type="text" name="number1"><br> 数字：<input
				type="text" name="number2"><br> <input type="submit"
				value="加法">
		</form>
	</div>
	<div>
		<%@include file="jsp/usercountinvist.jsp"%>
	</div>
	<script type="text/javascript">
	function infos(num){
		info = getinfo(num);
		document.getElementById("name").setAttribute("value", info.name.innerHTML);
		document.getElementById("token").setAttribute("value", info.token.innerHTML);
		alert(document.getElementById("name").getAttribute("value")+document.getElementById("token").getAttribute("value"));
		}
	</script>
</body>
</html>