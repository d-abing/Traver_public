package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class MschdListDao {
    private static MschdListDao mschdListDao; 
    private Connection conn;
    private MschdListDao() {}
    
    public static MschdListDao getInstance() {
        if (mschdListDao == null)    mschdListDao = new MschdListDao();
        return mschdListDao;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<ScheduleInfo> getMschdList(String where) {
    // ������ ����� ���Ͽ� ArrayList<ScheduleInfo>�� �����ϴ� �޼ҵ�
        Statement stmt = null;
        ResultSet rs = null;    
        ArrayList<ScheduleInfo> scheduleList = new ArrayList<ScheduleInfo>();
        // �������� ������ ��� ��ü
        ScheduleInfo si = null;
        // scheduleList�� ������ �ϳ���  ���� ������ ���� �ν��Ͻ�
        
        try {   
            stmt = conn.createStatement();
            String sql = "select mi_id, si_id, si_sdate, si_edate, si_dnum, si_date, si_name, si_img from t_schedule_info "
            + where + " order by si_date desc ";
            // System.out.println(sql);
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                si = new ScheduleInfo();    // �ϳ��� ������ ������ �ν��Ͻ� ����
                si.setMi_id(rs.getString("mi_id"));
                si.setSi_id(rs.getString("si_id"));
                si.setSi_sdate(rs.getString("si_sdate"));
                si.setSi_edate(rs.getString("si_edate"));
                si.setSi_dnum(rs.getInt("si_dnum"));
                si.setSi_date(rs.getString("si_date"));
                si.setSi_name(rs.getString("si_name"));
                si.setSi_img(rs.getString("si_img"));
                scheduleList.add(si);
                // ������ ���鼭 rs�� ����ִ� ���� �������� scheduleList�� ���� 
            }
            
        } catch(Exception e) {
            System.out.println("MschdListDao Ŭ������ getMschdList() �޼ҵ� ����");
            e.printStackTrace();
        } finally {
            close(rs); close(stmt);
        }
        
        return scheduleList;

    }
}
