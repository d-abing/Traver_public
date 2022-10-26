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

    public GoodInfo getIschdDetail(String miid, String giid, String day) {
    // �޾ƿ�(������) �������̵� �ش��ϴ� �������� ������ �������� GoodInfo�� �ν��Ͻ��� �����Ͽ� �����ϴ� �޼ҵ�
        Statement stmt = null;
        ResultSet rs = null;
        GoodInfo gi = null;
        
        try {
            String sql = "select * from t_good_info a, t_schedule_zzim b where b.mi_id = '" + miid + "' "
            + " and a.gi_id = b.gi_id and a.gi_id = '" + giid + "' ";
            
            stmt = conn.createStatement();
            //System.out.println(sql);
            rs = stmt.executeQuery(sql);
            if (rs.next()) { 
                gi = new GoodInfo();
                gi.setMi_id(miid);
                gi.setGi_id(giid);
                gi.setGi_nickname(rs.getString("gi_nickname"));
                gi.setGi_name(rs.getString("gi_name"));
                gi.setGi_dnum(rs.getInt("gi_dnum"));
                gi.setGoodDayList(getGoodDayList(giid, day)); // �� ���������� ���� ���� ����� ArrayList�� �޾ƿ� gi�� ����
            }
            
        } catch(Exception e) {
            System.out.println("IschdDao Ŭ������ getIschdDetail() �޼ҵ� ����");
            e.printStackTrace();
        } finally {
            close(rs); close(stmt);
        }
    
        return gi;
    }

    public ArrayList<GoodDay> getGoodDayList(String giid, String day) {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<GoodDay> goodDayList = new ArrayList<GoodDay>();
        GoodDay gd = null;
        
        try {
            String sql = "select * from t_good_day a, t_schedule_zzim b where a.gi_id = b.gi_id and a.gi_id = '" + giid + "' and gd_dnum = '" + day + "'";
            
            stmt = conn.createStatement();
           // System.out.println(sql);
            
            rs = stmt.executeQuery(sql);
            while (rs.next()) {   // �ϳ��� ���������� �ش��ϴ� ���������� ����ִ� rs
                gd = new GoodDay();
                gd.setGi_id(giid);
                gd.setGd_name(rs.getString("gd_name"));
                gd.setPi_id(rs.getInt("pi_id"));
                gd.setGd_dnum(rs.getInt("gd_dnum"));
                gd.setGd_coords(rs.getString("gd_coords"));
                gd.setGd_seq(rs.getInt("gd_seq"));
                goodDayList.add(gd);
            }
            
        } catch(Exception e) {
            System.out.println("IschdDao Ŭ������ getGoodDayList() �޼ҵ� ����");
            e.printStackTrace();
        } finally {
            close(rs); close(stmt);
        }
    
        return goodDayList;
    }

    public int ischdDelete(String where) {
    // ������ ���ǿ� �´� ���������� �ϳ� �����ϴ� �޼ҵ�
        Statement stmt = null;      
        int result = 0;         
        
        try {   
            stmt = conn.createStatement();
            String sql = "delete from t_schedule_zzim " + where;
            
            //System.out.println(sql);
            result = stmt.executeUpdate(sql);
            
        } catch(Exception e) {
            System.out.println("IschdDao Ŭ������ ischdDelete() �޼ҵ� ����");
            e.printStackTrace();
        } finally {
            close(stmt);
        }
        
        return result;
    }

    public GoodPost getGoodPost(String giid) {
        Statement stmt = null;
        ResultSet rs = null;
        GoodPost goodPost = new GoodPost();   
        
        try {
             stmt = conn.createStatement();
             String sql = "select * from t_good_post a, t_good_info b where a.gi_id = b.gi_id and a.gi_id = '" + giid + "' ";
             //System.out.println(sql);
             rs = stmt.executeQuery(sql);
             while (rs.next()) {
                 goodPost.setGp_id(rs.getString("gp_id"));
                 goodPost.setMi_nickname(rs.getString("mi_nickname"));
             }
        } catch (Exception e) {
             System.out.println("IschdDao Ŭ������ getGoodPost() �޼ҵ� ����");
             e.printStackTrace();
          } finally {
             close(rs);
             close(stmt);
          }
        
        return goodPost;
    }

    public GoodInfo getFullIschdDetail(String miid, String giid) {
     // �޾ƿ�(������) �������̵� �ش��ϴ� �������� ������ �������� GoodInfo�� �ν��Ͻ��� �����Ͽ� �����ϴ� �޼ҵ�
        Statement stmt = null;
        ResultSet rs = null;
        GoodInfo fullgi = null;
        
        try {
            String sql = "select * from t_good_info a, t_schedule_zzim b where b.mi_id = '" + miid + "' "
            + " and a.gi_id = b.gi_id and a.gi_id = '" + giid + "' ";
            
            stmt = conn.createStatement();
            //System.out.println(sql);
            rs = stmt.executeQuery(sql);
            if (rs.next()) { 
                fullgi = new GoodInfo();
                fullgi.setMi_id(miid);
                fullgi.setGi_id(giid);
                fullgi.setGi_nickname(rs.getString("gi_nickname"));
                fullgi.setGi_name(rs.getString("gi_name"));
                fullgi.setGi_dnum(rs.getInt("gi_dnum"));
                fullgi.setGoodDayList(getGoodDayList(giid)); // �� ���������� ���� ���� ����� ArrayList�� �޾ƿ� gi�� ����
            }
            
        } catch(Exception e) {
            System.out.println("IschdDao Ŭ������ getIschdDetail() �޼ҵ� ����");
            e.printStackTrace();
        } finally {
            close(rs); close(stmt);
        }
    
        return fullgi;
    }

    private ArrayList<GoodDay> getGoodDayList(String giid) {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<GoodDay> goodDayList = new ArrayList<GoodDay>();
        GoodDay gd = null;
        
        try {
            String sql = "select * from t_good_day a, t_schedule_zzim b where a.gi_id = b.gi_id and a.gi_id = '" + giid + "'";
            
            stmt = conn.createStatement();
           // System.out.println(sql);
            
            rs = stmt.executeQuery(sql);
            while (rs.next()) {   // �ϳ��� ���������� �ش��ϴ� ���������� ����ִ� rs
                gd = new GoodDay();
                gd.setGi_id(giid);
                gd.setGd_name(rs.getString("gd_name"));
                gd.setPi_id(rs.getInt("pi_id"));
                gd.setGd_dnum(rs.getInt("gd_dnum"));
                gd.setGd_coords(rs.getString("gd_coords"));
                gd.setGd_seq(rs.getInt("gd_seq"));
                goodDayList.add(gd);
            }
            
        } catch(Exception e) {
            System.out.println("IschdDao Ŭ������ getGoodDayList() �޼ҵ� ����");
            e.printStackTrace();
        } finally {
            close(rs); close(stmt);
        }
    
        return goodDayList;
    }
}
