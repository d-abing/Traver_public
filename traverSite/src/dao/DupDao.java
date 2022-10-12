package dao;

import static db.JdbcUtil.*;
import java.sql.*;

import vo.MemberInfo;

public class DupDao {
	private static DupDao dupDao;
	private Connection conn;
	private DupDao() {}
	
	public static DupDao getInstance() {
		if (dupDao == null)	dupDao = new DupDao();
		return dupDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public int chkDup(String kind, String input) {
	// ȸ�� ���Խ� ����ڰ� ����� ���̵��� �ߺ� ���θ� �����ϴ� �޼ҵ�
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;

		if ( kind.equals("i")) {
		    try {
	            String sql = "select count(*) from t_member_info where mi_id = '" + input + "'";
	            stmt = conn.createStatement();
	            rs = stmt.executeQuery(sql);
	            rs.next();
	            result = rs.getInt(1);

	        } catch(Exception e) {
	            System.out.println("DupDao Ŭ������ chkDup() �޼ҵ� ����");
	            e.printStackTrace();
	        } finally {
	            close(rs);  close(stmt);
	        }

	        
		} else if (kind.equals("n")) {
		    try {
                String sql = "select count(*) from t_member_info where mi_nickname = '" + input + "'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                result = rs.getInt(1);

            } catch(Exception e) {
                System.out.println("DupDao Ŭ������ chkDup() �޼ҵ� ����");
                e.printStackTrace();
            } finally {
                close(rs);  close(stmt);
            }
		}
		
		return result;
	}
}
