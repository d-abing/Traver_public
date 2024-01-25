<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%
request.setCharacterEncoding("utf-8");
String siid = request.getParameter("siid");

//String siid = "SI1007";
//int sidnum = 6;

String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/traver?useUnicode=true&characterEncoding=UTF8&" + 
		  "verifyServerCertificate=false&useSSL=false&serverTimezone=UTC";


Connection conn = null;	
Statement stmt = null; 
ResultSet rs = null;	

String sql = "";
String result = "";

try {
	Class.forName(driver);
	conn = DriverManager.getConnection(url, "root", "1234");
	stmt = conn.createStatement();
	
	sql = "select si_name, concat((si_dnum - 1), '박', ' ', si_dnum, '일') period, concat(si_sdate, '~', si_edate) date "
			+ "from t_schedule_info where si_id = '" + siid + "'";
	rs = stmt.executeQuery(sql);
	
	if (rs.next()) {
		result +=  "|" + rs.getString("si_name");
		result += "|" + rs.getString("period");
		result += "|" + rs.getString("date");
		out.println(result); // 일정정보를 출력
	} else {
		System.out.println("일정 디테일에서 일정 정보를 받는데 실패했습니다.");
	}
} catch(Exception e) {
	out.println("일정 디테일 정보 출력 쿼리에서 문제가 발생했습니다." + sql);
	e.printStackTrace();
}

%>