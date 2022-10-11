package dao;

import static db.JdbcUtil.*;
import java.sql.*;

import vo.MemberInfo;

public class DupIdDao {
	private static DupIdDao dupIdDao;
	private Connection conn;
	private DupIdDao() {}
	
	public static DupIdDao getInstance() {
		if (dupIdDao == null)	dupIdDao = new DupIdDao();
		return dupIdDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public int chkDupId(String uid) {
	// ȸ�� ���Խ� ����ڰ� ����� ���̵��� �ߺ� ���θ� �����ϴ� �޼ҵ�
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			String sql = "select count(*) from t_member_info where mi_id = '" + uid + "'";
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			result = rs.getInt(1);

		} catch(Exception e) {
			System.out.println("DupIdDao Ŭ������ chkDupId() �޼ҵ� ����");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}

		return result;
	}
}
