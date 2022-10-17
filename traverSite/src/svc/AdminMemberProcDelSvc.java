package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;


public class AdminMemberProcDelSvc {
    public int adminMemberProcDel(String where) {
        int result = 0;
        Connection conn = getConnection();
        AdminMemberProcDelDao adminMemberProcDelDao = AdminMemberProcDelDao.getInstance();
        adminMemberProcDelDao.setConnection(conn);

        result = adminMemberProcDelDao.adminMemberProcDel(where);
        if (result >= 1) {
            commit(conn);   // ���� ��ǰ �����ÿ��� result�� 1�̻��� �� ����
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }
}
