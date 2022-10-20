package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class AdminBbsReviewListDao {
    private static AdminBbsReviewListDao adminBbsReviewListDao; 
    private Connection conn;
    private AdminBbsReviewListDao() {}
    
    public static AdminBbsReviewListDao getInstance() {
        if (adminBbsReviewListDao == null) {
            adminBbsReviewListDao = new AdminBbsReviewListDao();
        }
        return adminBbsReviewListDao;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<AdminReviewList> getAdminBbsReviewList(String where, int cpage, int psize) {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<AdminReviewList> adminReviewList = new ArrayList<AdminReviewList>(); 
        AdminReviewList rl = null;
    
        try {
            stmt = conn.createStatement();
            String sql = "select rl_idx, mi_id, rl_pname, rl_content, rl_date, rl_img1, " + 
                    " rl_img2, rl_img3, rl_img4 " + 
                    " from t_review_list " + where + " order by rl_date desc " + 
                    " limit " + ((cpage -1) * psize) + ", " + psize;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rl = new AdminReviewList();
                rl.setRl_idx(rs.getInt("rl_idx"));
                rl.setMi_id(rs.getString("mi_id"));
                rl.setRl_pname(rs.getString("rl_pname"));
                rl.setRl_content(rs.getString("rl_content"));
                rl.setRl_date(rs.getString("rl_date"));
                rl.setRl_img1(rs.getString("rl_img1"));
                rl.setRl_img2(rs.getString("rl_img2"));
                rl.setRl_img3(rs.getString("rl_img3"));
                rl.setRl_img4(rs.getString("rl_img4"));
                adminReviewList.add(rl);
            }

        } catch (Exception e) {
            System.out.println("AdminBbsReviewListDao Ŭ������ getAdminBbsReviewList() �޼ҵ� ����");
            e.printStackTrace();
        } finally {
            close(rs);
            close(stmt);
        }
        
        return adminReviewList;
    }

    public int getBbsReVListCount(String where) {
        Statement stmt = null;  // ������ DB�� ���� ��ü ����
        ResultSet rs = null;    // ����(select)�� ����� ������ ��ü ����
        int rcnt = 0;           // �� �޼ҵ��� ����� ������ ������ ������ ���̱⵵ ��

        try {
            stmt = conn.createStatement();
            String sql = "select count(*) cnt from t_review_list " + where;
            rs = stmt.executeQuery(sql);
            if (rs.next())  rcnt = rs.getInt("cnt");

        } catch(Exception e) {
            System.out.println("AdminBbsReviewListDao Ŭ������ getBbsReVListCount() �޼ҵ� ����");
            e.printStackTrace();
        } finally {
            close(rs);  close(stmt);
        }

        return rcnt;
    }
}
