package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class AdminLoginDao {
	// �α��ο� ���õ� ���� �۾��� ó���ϴ� Ŭ����
		private static AdminLoginDao adminLoginDao; // ������ LoginDao�� �ν��Ͻ��� ������ ���
		private Connection conn;
		private AdminLoginDao() {}
		// �⺻ �����ڸ� private���� �����Ͽ� �ܺο��� �Ժη� �ν��Ͻ��� �������� ���ϰ� ���´�.
		
		public static AdminLoginDao getInstance() {
		// AdminLoginDaoŬ������ �ν��Ͻ��� �������ִ� �޼ҵ�� �ν��Ͻ��� ������ ��� ������ �ν��Ͻ��� ����
		// AdminLoginDaoŬ������ �ν��Ͻ��� �ϳ��� �����Ͽ� ����ϰ� �ϴ� �̱��� ���
			if (adminLoginDao == null) {
				adminLoginDao = new AdminLoginDao();
				// �̹� ������ AdminLoginDaoŬ������ �ν��Ͻ��� ������ ���Ӱ� AdminLoginDaoŬ������ �ν��Ͻ��� ����
			}
			return adminLoginDao;
		}
		public void setConnection(Connection conn) {
		// �� DaoŬ�������� ����� Ŀ�ؼ� ��ü�� �޾ƿͼ� �������ִ� �޼ҵ�
			this.conn = conn;
		}

		public AdminInfo getAdminLogin(String id, String pw) {
			Statement stmt = null; // �α��� ó���� ���� ������ DB�� ���� Statement ����
			ResultSet rs = null; // �α��� ó���� ���� ������ ���� ����� ���� ResultSet ����
			AdminInfo adminInfo = null; // rs�� ��ƿ� ����� ������ MemberInfo�� �ν��Ͻ� ����
			
			try {
				String sql = "select * from t_admin_info where ai_isuse = 'y' " + 
						" and ai_id = '" + id + "' and ai_pw = '" + pw + "' ";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {	// rs�� �����Ͱ� ������(�α����� �������� ���)
					adminInfo = new AdminInfo();	// ���� ������ ������ �ν��Ͻ� ����
					adminInfo.setAi_id(id);
					adminInfo.setAi_pw(pw);
					adminInfo.setAi_name(rs.getString("ai_name"));
					adminInfo.setAi_isuse(rs.getString("ai_isuse"));
					// rs�� ������� else ���� adminInfo �ν��Ͻ���  null�� �ִ� ���·� �����Ѵ�.
				}
			} catch (Exception e) {
				System.out.println("AdminLoginDaoŬ������ getAdminLogin() �޼ҵ� ����");
				e.printStackTrace();
			} finally {
				close(rs);
				close(stmt);
			}
			
			return adminInfo;
		}
		
}
