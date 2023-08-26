<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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

<h1>JSP : Java title here</h1>
<%

	request.setCharacterEncoding("UTF-8");

	String userId = request.getParameter("id");
	String userName = request.getParameter("name");
	String userTel = request.getParameter("tel");
	String userAddr = request.getParameter("addr");
	String userAge = request.getParameter("age");
	String userFile = request.getParameter("file");
	
	//DB crud처리 결과를 얻어서
	//응답 데이타를 생성한다
%>

<table border="1"> 

	<tr>
		<td clsaa="title">아이디</td>
		<td><%= userId %></td>
	</tr>
	
	<tr>
		<td clsaa="title">이름</td>
		<td><%= userName %></td>
	</tr>
	
	<tr>
		<td clsaa="title">전화번호</td>
		<td><%= userTel %></td>
	</tr>
	
	<tr>
		<td clsaa="title">주소</td>
		<td><%= userAddr %></td>
	</tr>
	
	<tr>
		<td clsaa="title">나이</td>
		<td><%= userAge %></td>
	</tr>
	
	<tr>
		<td clsaa="title">첨부파일</td>
		<td><%= userFile %></td>
	</tr>

	

</table>

</body>
</html>