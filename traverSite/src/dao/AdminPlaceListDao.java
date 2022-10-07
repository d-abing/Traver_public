package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class AdminPlaceListDao {
    private static AdminPlaceListDao adminPlaceListDao; 
    private Connection conn;
    private AdminPlaceListDao() {}
    
    public static AdminPlaceListDao getInstance() {
        if (adminPlaceListDao == null) {
            adminPlaceListDao = new AdminPlaceListDao();
        }
        return adminPlaceListDao;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<PlaceInfo> getAdminPlaceList(String where, int cpage, int psize) {
    // �Խ��� ��Ͽ��� �˻��� �Խñ۵��� ����� ArrayList<FreeList>�� �ν��Ͻ��� �����ϴ� �޼ҵ�
            Statement stmt = null;
            ResultSet rs = null;
            ArrayList<PlaceInfo> placeInfo = new ArrayList<PlaceInfo>(); // �Խñ۵��� ������ ��� ��ü
            PlaceInfo pl = null;    // placeList�� ������ �ϳ��� �Խñ� ������ ���� �ν��Ͻ�
        
            try {
                stmt = conn.createStatement();
                String sql = "select pi_id, pi_name, pi_phone, pi_ctgr, pi_date, pi_addr1 " + 
                        " from t_place_info " + where + " order by pi_id desc " + 
                        " limit " + ((cpage -1) * psize) + ", " + psize;
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    pl = new PlaceInfo();
                    pl.setPi_id(rs.getString("pi_id"));
                    pl.setPi_name(rs.getString("pi_name"));
                    pl.setPi_phone(rs.getString("pi_phone"));
                    pl.setPi_ctgr(rs.getString("pi_ctgr"));
                    pl.setPi_date(rs.getString("pi_date"));
                    pl.setPi_addr1(rs.getString("pi_addr1"));
                    placeInfo.add(pl);
                }
                
            } catch (Exception e) {
                System.out.println("AdminPlaceListDao Ŭ������ getAdminPlaceList() �޼ҵ� ����");
                e.printStackTrace();
            } finally {
                close(rs);
                close(stmt);
            }
            
        return placeInfo;
    }

    public int getPlaceListCount(String where) {
     // �Խ��� ��Ͽ��� ����� ���ڵ�(�Խñ�) �� ������ �����ϴ� �޼ҵ�
        Statement stmt = null;  // ������ DB�� ���� ��ü ����
        ResultSet rs = null;    // ����(select)�� ����� ������ ��ü ����
        int rcnt = 0;           // �� �޼ҵ��� ����� ������ ������ ������ ���̱⵵ ��

        try {
            stmt = conn.createStatement();
            String sql = "select count(*) cnt from t_place_info " + where;
            rs = stmt.executeQuery(sql);
            if (rs.next())  rcnt = rs.getInt("cnt");

        } catch(Exception e) {
            System.out.println("AdminPlaceListDao Ŭ������ getPlaceListCount() �޼ҵ� ����");
            e.printStackTrace();
        } finally {
            close(rs);  close(stmt);
        }

        return rcnt;
    }
}
