<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%
AdminInfo adminInfo = (AdminInfo)session.getAttribute("adminInfo");
if (adminInfo != null) {
	out.println("<script> alert('잘못된 경로로 들어오셨습니다.'); history.back(); </script>");
	out.close();
}

request.setCharacterEncoding("utf-8");
String url = request.getParameter("url"); // 로그인 후 이동할 페이지 주소
if (url == null) {
	url = "admin_main.jsp";
	// 로그인 후 이동할 페이지주소가 없는 경우 메인화면으로 지정
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.container {
	display : flex;
	align-items: center;
	justify-content: center;
	height : 100vh;
}
.container .inner{padding: 40px 40px; border: 1px solid #999; width: 500px; height: 250px; display: grid; align-content: center;}
.container .inner h2{text-align : center; font-size: 35px;}
.container .inner form{display : flex; justify-content: center;}
.container .inner form div:first-child{margin-right : 10px;}
.container .inner label{display : inline-block; width : 90px; font-size : 16px;}
.container .inner label:nth-of-type(1){margin-bottom : 20px;}
.container .inner input{height : 25px; padding : 0 10px;}
.container .inner form div:nth-of-type(2) input{height : 72px; background-color : #fff; border: 1px solid #999; cursor : pointer;}
.container .inner p{text-align : center; font-size : 13px; margin : 14px 0 0;}
</style>
</head>
<body>
	<div class="container">
		<div class="inner">
			<h2>관리자 로그인</h2>
			<form action="adminLogin" name="frmLogin" method="post">
			<div>
				<label for="uid">아이디 : </label> <input type="text" name="uid" id="uid" value="admin"><br>
				<label for="uid">비밀번호 : </label> <input type="password" name="pwd" id="pwd" value="1111">
			</div>
			<div>
				<input type="submit" value="로그인">
			</div>
			</form>
			<p>관리자 로그인 접속 문의<br>02-123-4567</p>
		</div>
	</div>
</body>
</html>