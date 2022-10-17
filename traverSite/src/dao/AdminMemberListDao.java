package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class AdminMemberListDao {
    private static AdminMemberListDao adminMemberListDao; 
    private Connection conn;
    private AdminMemberListDao() {}
    
    public static AdminMemberListDao getInstance() {
        if (adminMemberListDao == null) {
            adminMemberListDao = new AdminMemberListDao();
        }
        return adminMemberListDao;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<MemberInfo> getAdminMemberList(String where, int cpage, int psize) {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<MemberInfo> memberInfo = new ArrayList<MemberInfo>(); 
        MemberInfo mi = null;
    
        try {
            stmt = conn.createStatement();
            String sql = "select mi_id, mi_nickname, mi_name, mi_mail, mi_status, mi_join " + 
                    " from t_member_info " + where + " order by mi_id desc " + 
                    " limit " + ((cpage -1) * psize) + ", " + psize;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                mi = new MemberInfo();
                mi.setMi_id(rs.getString("mi_id"));
                mi.setMi_nickname(rs.getString("mi_nickname"));
                mi.setMi_name(rs.getString("mi_name"));
                mi.setMi_mail(rs.getString("mi_mail"));
                mi.setMi_status(rs.getString("mi_status"));
                mi.setMi_join(rs.getString("mi_join"));
                memberInfo.add(mi);
            }

        } catch (Exception e) {
            System.out.println("AdminMemberListDao Ŭ������ getAdminMemberList() �޼ҵ� ����");
            e.printStackTrace();
        } finally {
            close(rs);
            close(stmt);
        }
        
        return memberInfo;
    }

    public int getMemberListCount(String where) {
        Statement stmt = null;  // ������ DB�� ���� ��ü ����
        ResultSet rs = null;    // ����(select)�� ����� ������ ��ü ����
        int rcnt = 0;           // �� �޼ҵ��� ����� ������ ������ ������ ���̱⵵ ��

        try {
            stmt = conn.createStatement();
            String sql = "select count(*) cnt from t_member_info " + where;
            rs = stmt.executeQuery(sql);
            if (rs.next())  rcnt = rs.getInt("cnt");

        } catch(Exception e) {
            System.out.println("AdminMemberListDao Ŭ������ getMemberListCount() �޼ҵ� ����");
            e.printStackTrace();
        } finally {
            close(rs);  close(stmt);
        }

        return rcnt;
    }
}
