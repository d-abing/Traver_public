package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class IschdDao {
    private static IschdDao ischdDao; 
    private Connection conn;
    private IschdDao() {}
    
    public static IschdDao getInstance() {
        if (ischdDao == null)    ischdDao = new IschdDao();
        return ischdDao;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
    public ArrayList<GoodInfo> getIschdList(String where, String orderBy) {
    // ���������� ����� ���Ͽ� ArrayList<GoodInfo>�� �����ϴ� �޼ҵ�
        Statement stmt = null;
        ResultSet rs = null;    
        ArrayList<GoodInfo> goodList = new ArrayList<GoodInfo>();
        // ������������ ������ ��� ��ü
        GoodInfo gi = null;
        // goodList�� ������ �ϳ���  �������� ������ ���� �ν��Ͻ�
        
        try {   
            stmt = conn.createStatement();
            String sql = "select * from t_schedule_zzim a, t_good_info b "
            + where + orderBy;
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                gi = new GoodInfo();    // �ϳ��� ���������� ������ �ν��Ͻ� ����
                gi.setGi_id(rs.getString("gi_id"));
                gi.setGi_dnum(rs.getInt("gi_dnum"));
                gi.setGi_date(rs.getString("gi_date"));
                gi.setGi_name(rs.getString("gi_name"));
                gi.setGi_img(rs.getString("gi_img"));
                goodList.add(gi);
                // ������ ���鼭 rs�� ����ִ� �������� �������� goodList�� ���� 
            }
            
        } catch(Exception e) {
            System.out.println("IschdDao Ŭ������ getIschdList() �޼ҵ� ����");
            e.printStackTrace();
        } finally {
            close(rs); close(stmt);
        }
        
        return goodList;
    }

}
