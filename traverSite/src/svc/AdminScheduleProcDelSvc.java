package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AdminScheduleProcDelSvc {
    public int adminScheduleProcDel(String where) {
        int result = 0;
        Connection conn = getConnection();
        AdminScheduleProcDelDao adminScheduleProcDelDao = AdminScheduleProcDelDao.getInstance();
        adminScheduleProcDelDao.setConnection(conn);

        result = adminScheduleProcDelDao.adminScheduleProcDel(where);
        if (result >= 1) {
            commit(conn);   // ���� ��ǰ �����ÿ��� result�� 1�̻��� �� ����
        } else {
            rollback(conn);
        }
        close(conn);
        
        return result;
    }
}
