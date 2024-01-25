<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%
request.setCharacterEncoding("utf-8");
String siid = request.getParameter("siid");
int sidnum = Integer.parseInt(request.getParameter("sidnum"));

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
	
	for (int i = 1; i <= sidnum ; i++) {
		sql = "select * from t_schedule_day where si_id = '" + siid + "' and sd_dnum = '" + i +"' order by sd_seq";
		// 'n일차'에 해당하는 장소들만 가져와서 출력.
		rs = stmt.executeQuery(sql);
		
		if (rs.next()) { // 'n일차'에 장소가 존재한다면
			result = i + "일차|";
			do {
				result += "\\n" + rs.getString("pi_name");
			} while (rs.next());
			out.println(result); // 'n일차'의 장소를 모두 적었다면 한줄을 띄우고 다음일차로 이동.
		} else {
			System.out.println("일정 디테일에서 장소 정보를 받는데 실패했습니다.");
		}
	}
	
} catch(Exception e) {
	out.println("일정 디테일 장소 정보 출력 쿼리에서 문제가 발생했습니다." + sql);
	e.printStackTrace();
}

%>
