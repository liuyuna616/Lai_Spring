<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
//      GroupService groupSv = new GroupService();
//      List<GroupVO> list = groupSv.getCitySport("${param.groupType}","${param.groupCity}");
//      pageContext.setAttribute("list",list);

  		pageContext.getAttribute("list");
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=Big5">
<title>login</title>
</head>
<body>
<center>
	<form ACTION="/TIA103G1/group/login.do" method="post">
		
			<table border=1>
				<tr>
					<td colspan=2>
						<p align=center>
							輸入<b>(測試登入)</b>:<br> 
							帳號:<b>tomcat</b><br>
							密碼:<b>tomcat</b><br>
					</td>
				</tr>

				<tr>
					<td>
						<p align=right>
							<b>account:</b>
					</td>
					<td>
						<p>
							<input type=text name="account" value="" size=15>
					</td>
				</tr>

				<tr>
					<td>
						<p align=right>
							<b>password:</b>
					</td>
					<td>
						<p>
							<input type=password name="password" value="" size=15>
					</td>
				</tr>


				<tr>
					<td colspan=2 align=center>
						
							<input type=submit value="  ok   ">
						
					</td>
				</tr>
			</table>
	</form>
</center>
</body>
</html>