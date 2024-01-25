<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%
request.setCharacterEncoding("utf-8");
String siid = request.getParameter("siid");

String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/traver?useUnicode=true&characterEncoding=UTF8&" + 
		  "verifyServerCertificate=false&useSSL=false&serverTimezone=UTC";


Connection conn = null;	
Statement stmt = null; 
ResultSet rs = null;	

String sql = "";
int result = 0;
int tmp = 0;

try {
	Class.forName(driver);
	conn = DriverManager.getConnection(url, "root", "1234");
	stmt = conn.createStatement();
	
	sql = "select count(*) cnt from t_schedule_day where si_id = '"+ siid +"'";
	rs = stmt.executeQuery(sql);
	if (rs.next()) {
		tmp = rs.getInt("cnt") + 1; 
	}
	
	sql = "delete from t_schedule_day where si_id = '"+ siid +"'";
	result += stmt.executeUpdate(sql);
	
	sql = "delete from t_schedule_info where si_id = '"+ siid +"'";
	result += stmt.executeUpdate(sql);
	
	conn.setAutoCommit(false); // 트랜잭션 시작
	
    if(result == tmp) {
    	conn.commit();	//쿼리의 실행결과를 DB에 적용시키라는 명령
    	out.println("일정 삭제쿼리 commit()");
    } else {
    	conn.rollback(); //쿼리 취소
		out.println("일정 삭제쿼리 rollback() > 삭제값: " + result + ", 실제값: " + tmp);
    }
    
    conn.setAutoCommit(true); // 트랜잭션 종료
    
} catch(Exception e) {
	out.println("일정 디테일 정보 출력 쿼리에서 문제가 발생했습니다." + sql);
	e.printStackTrace();
}

%>

