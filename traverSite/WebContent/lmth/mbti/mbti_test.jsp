<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MBTI 테스트 2</title>
<style>
.container-default_box { 
	text-align: center;
	padding: 105px 0 155px 0;
	height: 640px;
	position: relative;
}
#test-result { 
	width: 1200px; height: 624px;
	padding-top: 91px; position: absolute; top: 14px; left:0; 
	background-color: #fff 
}
.range_icon { width: 50px; vertical-align: middle; }
.range_line { display: inline; width: 400px; height: 8px;}
.b_m_font { font-size: 16px; display: inline; font-weight: bold; vertical-align: middle; } 

input[type="radio"] {
	width: 70px; height: 35px;
	display: inline;
	vertical-align: middle; 
	border: 1px solid #d9d9d9;
	cursor: pointer;
}
.last { margin-right: 10px; }
.quest_font { font-size: 20px; font-weight: bold; }

.bold { 
	font-size: 23px; font-weight: bold; 
	display: inline; 
}
.mbti { color: #4EA2B9; font-size: 30px;}

#wel_img { 	
	width: 500px;	
	vertical-align: middle;
}
#welcomebox { width: 900px; margin: auto;}
#welcome { 
	width: 290px;
	padding: 70px 0 0 30px; 
	float: right; position: relative; left: -12%; 
	text-align: left; 
	}
#wel_desc { font-size: 18px; line-height: 1.7;}

.btn {
	margin-top:20px;
	background: inherit ; border: none; box-shadow: none; border-radius: 0; 
	padding: 0; 
	overflow: visible; cursor: pointer;
}
.btn_img { height: 40px; }
.display_none { display: none; }
</style>
<script>
window.onload = function(){
	const testResult = document.querySelector('#test-result');
	const yes = document.querySelector('#submitbtn');
	
	submitbtn.addEventListener('click', function() {
		testResult.classList.remove('display_none');
	});
}
</script>
</head>
<body>
<%@ include file="../../cni/header.jsp" %>
<div class="container">
   <div class="container-default_box">
   <div class="rangebox">
   <span class="b_m_font">MBTI 테스트</span>
   <img src="../../file/img/test_icon1.png" class="range_icon">
   <img src="../../file/img/test_range1.png" class="range_line">
   <img src="../../file/img/test_icon2.png" class="range_icon">
   <span class="b_m_font">MBTI 결과</span><br><br><br><br><br><br><br>
   </div>
   <div class="test">
   <form name="frm_test" action="" method="get">
   <span class="quest_font">혼자 떠나는 여행을 즐기는 편이다</span><br><br>
   <span class="b_m_font">동의</span>
   <input type="radio" name="quest1" class="icon">
   <input type="radio" name="quest1" class="icon">
   <input type="radio" name="quest1" class="icon">
   <input type="radio" name="quest1" class="icon">
   <input type="radio" name="quest1" class="icon">
   <input type="radio" name="quest1" class="icon last">
   <span class="b_m_font">비동의</span><br><br><br>
   <span class="quest_font">여행 중 길을 잃으면 왔던 길로 되돌아 간다</span><br><br>
   <span class="b_m_font">동의</span>
   <input type="radio" name="quest2" class="icon">
   <input type="radio" name="quest2" class="icon">
   <input type="radio" name="quest2" class="icon">
   <input type="radio" name="quest2" class="icon">
   <input type="radio" name="quest2" class="icon">
   <input type="radio" name="quest2" class="icon last">
   <span class="b_m_font">비동의</span><br><br><br>
   <span class="quest_font">필요하지 않지만 예쁜 기념품을 산다</span><br><br>
   <span class="b_m_font">동의</span>
   <input type="radio" name="quest3" class="icon">
   <input type="radio" name="quest3" class="icon">
   <input type="radio" name="quest3" class="icon">
   <input type="radio" name="quest3" class="icon">
   <input type="radio" name="quest3" class="icon">
   <input type="radio" name="quest3" class="icon last">
   <span class="b_m_font">비동의</span><br><br><br>
   <span class="quest_font">여행을 떠나기 전 일정을 꼼꼼하게 계획한다</span><br><br>
   <span class="b_m_font">동의</span>
   <input type="radio" name="quest4" class="icon">
   <input type="radio" name="quest4" class="icon">
   <input type="radio" name="quest4" class="icon">
   <input type="radio" name="quest4" class="icon">
   <input type="radio" name="quest4" class="icon">
   <input type="radio" name="quest4" class="icon last">
   <span class="b_m_font">비동의</span><br><br><br><br>
   <button type="button" class="btn"><image class="btn_img" id="submitbtn" src="../../file/img/submitbtn.png" alt="제출하기"></button>
 <!--  나중에 mbti_proc_up 으로 링크 변경해야 함 -->
   </form>
   </div>
   <div class="display_none" id="test-result">
	   <div class="rangebox">
	   <span class="b_m_font">MBTI 테스트</span>
	   <img src="../../file/img/test_icon1.png" class="range_icon">
	   <img src="../../file/img/test_range2.png" class="range_line">
	   <img src="../../file/img/test_icon1.png" class="range_icon">
	   <span class="b_m_font">MBTI 결과</span><br><br><br><br><br><br><br><br><br><br>
	   </div>
	   <span class="bold">회원님의 MBTI는 </span><span class="bold mbti">ISTJ</span><span class="bold"> 입니다.</span><br><br>
	   		<div id="welcomebox">
		       	<img src="../../file/img/mbti_welcome.png" id="wel_img">
		       	<div id="welcome">
			   		<span class="bold">환영합니다!</span><br><br>
			   		<span id="wel_desc">이제부터 MBTI 여행을 이용 할 수 있습니다!</span><br>
			   		<a href="mbti_main.jsp"><button class="btn"><image class="btn_img" id="gobestbtn" src="../../file/img/gobestbtn.png" alt="MBTI여행으로 이동"></button></a>&nbsp;&nbsp;&nbsp;
			   	</div>
		   	</div>
   		</div>
   </div>
</div>
<%@ include file="../../cni/footer.jsp" %>
</body>
</html>