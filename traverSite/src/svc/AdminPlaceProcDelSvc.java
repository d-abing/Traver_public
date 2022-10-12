package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AdminPlaceProcDelSvc {
    public int adminPlaceProcDel(String where) {
        int result = 0;
        Connection conn = getConnection();
        AdminPlaceProcDelDao adminPlaceProcDelDao = AdminPlaceProcDelDao.getInstance();
        adminPlaceProcDelDao.setConnection(conn);

        result = adminPlaceProcDelDao.adminPlaceProcDel(where);
        if (result >= 1) {
            commit(conn);   // ���� ��ǰ �����ÿ��� result�� 1�̻��� �� ����
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }
}
