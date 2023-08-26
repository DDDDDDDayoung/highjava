<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP : Java Server Page</title>

<style>
	table{
		border: 1px solid blue;
		margin: 50px;
	}
	
	td{
		width: 300px;
		height: 50px;
		text-align: center;
	}
</style>

</head>
<body>

<%
	request.setCharacterEncoding("UTF-8");

	String userName = request.getParameter("name");
	String gender = request.getParameter("gender");
	
	String fruit[] = request.getParameterValues("fruit");
	
	String str = "";
	
	for(String fr : fruit ){
		str += fr + "&nbsp;&nbsp;";
	}
%>

<table border="1">
	<tr>
		<td clsaa="title">이름</td>
		<td><%= userName %></td>
	</tr>
	
	<tr>
		<td clsaa="title">성별</td>
		<td><%= gender %></td>
	</tr>
	
	<tr>
		<td clsaa="title">좋아하는 과일</td>
		<td><%= str %></td>
	</tr>
</table>

</body>
</html>