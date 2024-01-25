<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String pw = request.getParameter("pw");

String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/traver?useUnicode=true&characterEncoding=UTF8&verifyServerCertificate=false&useSSL=false&serverTimezone=UTC";

Connection conn = null;
Statement stmt = null;
ResultSet rs = null;
String sql = "select mi_id, mi_nickname from t_member_info where mi_status = 'a' and " + 
	" mi_id = '" + id + "' and mi_pw = '" + pw + "' ";

try {
	Class.forName(driver);
	conn = DriverManager.getConnection(url, "root", "1234");
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sql);
	if (rs.next()) {
		out.print(rs.getString("mi_id") + "|" + rs.getString("mi_nickname"));
	} else {
		out.print("fail");
	}
 
} catch (Exception e) {
	out.println("회원 목록 쿼리에서 문제가 발생했습니다.");
	e.printStackTrace();
}

%>