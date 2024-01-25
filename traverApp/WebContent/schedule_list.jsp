<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<% 
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String schYear = request.getParameter("schYear");
String keyword = request.getParameter("keyword");
String orderBy = request.getParameter("orderBy");

String where = " where mi_id = '" + id +  "' "; // 쿼리 where절 저장될 변수
if (schYear != null && !schYear.equals("")) {
	where += " and left(si_sdate, 4) = '" + schYear + "' ";
}

if (keyword == null) {
	keyword = "";
} else if (!keyword.equals("")) {
	where += " and si_name like '%" + keyword + "%' "; 
}

if (orderBy == null || orderBy.equals(""))	orderBy = "a";
String order = "";
switch (orderBy) {
case "a" : // a: 등록 최신 순
	order = " order by si_date desc";		break;
case "b" : // b: 등록 오래된 순
	order = " order by si_date"; 			break;
}

String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/traver?useUnicode=true&characterEncoding=UTF8&" + 
		  "verifyServerCertificate=false&useSSL=false&serverTimezone=UTC";

Connection conn = null;	
Statement stmt = null; 
ResultSet rs = null;	

String sql = "select si_img, si_name, concat((si_dnum - 1), '박', ' ', si_dnum, '일') period, " + 
			" concat(si_sdate, ' ~ ', si_edate) date, si_img, si_id, si_dnum, si_date from t_schedule_info " + where + order;

try {
	Class.forName(driver);
	conn = DriverManager.getConnection(url, "root", "1234");
	stmt = conn.createStatement();
	
  	rs = stmt.executeQuery(sql);
  	String result = "";
  	
	if (rs.next()) {
		do {
			result = rs.getString("si_name") + "|";
			result += rs.getString("period") + "|";
			result += rs.getString("date") + "|";
			result += rs.getString("si_img") + "|";
			result += rs.getString("si_id") + "|";
			result += rs.getString("si_dnum");
			out.println(result);
		} while(rs.next());
	} else { 
		out.println("일정이 없습니다.");
	}
		
} catch(Exception e) { 
	out.println("회원 목록 쿼리에서 문제가 발생했습니다." + sql);
	e.printStackTrace();
} 

%>
