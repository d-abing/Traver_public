package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;
	
public class PlaceListDao {
	private static PlaceListDao placeListDao;
	private Connection conn;
	private PlaceListDao() {}
	
	public static PlaceListDao getInstance() {
		if (placeListDao == null)	placeListDao = new PlaceListDao();
		return placeListDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<PlaceInfo> getPlaceList(String where) {
		Statement stmt = null;
	    ResultSet rs = null;
	    ArrayList<PlaceInfo> placeList = new ArrayList<PlaceInfo>(); // �Խñ۵��� ������ ��� ��ü
	    PlaceInfo pi = null;   // freeList�� ������ �ϳ��� �Խñ� ������ ���� �ν��Ͻ�
	    
	    try {
	         stmt = conn.createStatement();
	         String sql = "select * from t_place_info " + where;
	            // ��� �����ִ� ����
	         rs = stmt.executeQuery(sql);
	         while (rs.next()) {
	        	pi = new PlaceInfo();
	        	pi.setPi_id(rs.getString("pi_id"));
	        	pi.setPi_name(rs.getString("pi_name"));
	        	pi.setPi_phone(rs.getString("pi_phone"));
	        	pi.setPi_link(rs.getString("pi_link"));
	        	pi.setPi_coords(rs.getString("pi_coords"));
	        	pi.setPi_addr1(rs.getString("pi_addr1"));
	        	pi.setPi_addr2(rs.getString("pi_addr2"));
	        	pi.setPi_zip(rs.getString("pi_zip"));
	        	pi.setPi_img1(rs.getString("pi_img1"));
	        	pi.setPi_img2(rs.getString("pi_img2"));
	        	pi.setPi_img3(rs.getString("pi_img3"));
	        	pi.setPi_img4(rs.getString("pi_img4"));
	        	pi.setPi_img5(rs.getString("pi_img5"));
	        	pi.setPi_desc(rs.getString("pi_desc"));
	        	pi.setPi_review(rs.getInt("pi_review"));
	        	pi.setPi_ctgr(rs.getString("pi_ctgr"));
	        	pi.setPi_date(rs.getString("pi_date"));
	            placeList.add(pi);
	            // ������ ���鼭 rs�� ����ִ� �Խñ� �������� freeList�� ����
	         }
	    } catch (Exception e) {
	         System.out.println("PlaceListDao Ŭ������ getPlaceList() �޼ҵ� ����");
	         e.printStackTrace();
	      } finally {
	         close(rs);
	         close(stmt);
	      }
		
		return placeList;
	}
}
