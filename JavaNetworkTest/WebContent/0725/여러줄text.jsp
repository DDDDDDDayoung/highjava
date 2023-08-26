<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

table{
	border : 2px solid green;
}

td{
	width : 300px;
	height: 50px;
	text-align: center;
}

.title{
	background : lightgreen;
}
#info{
	height : 200px;
}

</style>


</head>
<body>

<h1>JSP : Java Server Page</h1>
<%

	request.setCharacterEncoding("UTF-8");

	String userId = request.getParameter("id");
	String userName = request.getParameter("name");
	
	String userInfo = request.getParameter("area");
	//엔터기호\n\r를 <br>태그로 변경 - replaceAll('기존','새로운')

	String newInfo = userInfo.replaceAll("\n", "<br>");
	
	//DB접속 - crud처리
	
	//결과값을 얻는다
	//결과값으로 출력
%>

<table border="1">
	<tr>
		<td class="title">아이디</td>
		<td><%= userId %></td>	
	</tr>
	
	<tr>
		<td class="title">이름</td>
		<td><%= userName %></td>	
	</tr>
	
	<tr>
		<td class="title">소개</td>
		<td><%= userInfo %></td>	
	</tr>
</table>

</body>
</html>