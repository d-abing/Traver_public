<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴 재확인</title>
<style>
.container-default_box {
	position:relative;
}
.leftBox {
	width:200px; height:1000px;
    border:solid 1px lightgray; border-top:none;
    position:absolute; right:998px;
}
.mypage { 
	color:black; font-weight:bold; font-size:18px;
}
.menu { color: gray; 
	font-weight:bold; font-size:16px;
}
a:hover{ color:black; }

.pw1 { font-size:20px; font-weight:bold; text-align:center; }

.pwChkBox {
	width:600px; height:300px;
    border:solid 1px #39f;
    position:absolute; top:250px; left:400px;
}
.cancelMem { font-size:20px; font-weight:bold; text-align:center; }
.chk1 { font-size:20px; font-weight:bold; text-align:center; }
.chk2 { text-align:center; font-size:18px; }
.chk3 { margin-top:10px; text-align:center; font-size:14px; }
.chk4 { margin-top:20px; text-align:center; font-size:14px; }
.chk5 { margin-top:5px; text-align:center; font-size:14px; }
.chk6 { margin-top:5px; text-align:center; font-size:14px; }
.chk7 { margin-top:40px; text-align:center; }
</style>
</head>
<body>
<%@ include file="../../cni/header.jsp" %>
<!-- 컨테이너 시작 -->
<div class="container">
   <div class="container-default_box">
   
	<div class="leftBox">
		<br/><br/><br/>
		<h2 class="mypage">&nbsp;&nbsp;마이페이지</h2><br/><br/>
		<hr/>
		<ul><br/><br/>
			<li><a href="post_review_list.jsp" class="menu">&nbsp;&nbsp;|내 작성 글/리뷰</a></li><br/>
			<li><a href="info_up_form.jsp" class="menu">&nbsp;&nbsp;|회원 정보 수정</a></li><br/>
			<li><a href="pw_check.jsp" class="menu">&nbsp;&nbsp;|비밀번호 변경</a></li><br/>
			<li><a href="member_del.jsp" class="menu">&nbsp;&nbsp;|회원 탈퇴</a></li><br/>
		</ul>
	</div>
	<form name="cancelFrm" action="#" method="post">
		<div class="pwChkBox"><br/><br/>
			<h2 class="chk1">회원 탈퇴</h2><br/><br/>
			<p class="chk2">정말 탈퇴 하시겠습니까?</p>
			<p class="chk3">지금 트레버를 떠나면 잃게 되는 것</p>
			<p class="chk4">☞ 정성 들여 짠 내 일정들</p>
			<p class="chk5">☞ 나와 같은 MBTM의 일정 추천</p>
			<p class="chk6">☞ 내가 찜한 장소들</p>
			<div class="chk7">	
				<input type="submit" value="회원 탈퇴" style="height:35px; width:80px;"/>
				<!-- 탈퇴 완료 후 메인페이지로 이동 -->
			</div><br/>
		</div>
		
	</form>
   
   </div>
</div>
<!-- 컨테이너 종료 -->
<%@ include file="../../cni/footer.jsp" %>   
</body>
</html>