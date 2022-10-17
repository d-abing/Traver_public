package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class ScheduleInSvc {
    public String scheduleInfoInsert(String miid, String scheduleName, String imgPiid, ScheduleInfo scheduleInfo) {
        Connection conn = getConnection();
        ScheduleDao scheduleDao = ScheduleDao.getInstance();
        scheduleDao.setConnection(conn);

        String result = scheduleDao.scheduleInfoInsert(miid, scheduleName, imgPiid, scheduleInfo);
        String[] arr = result.split(":"); // siid�� �������θ� ���� ���ڵ� ��
        String siid = arr[0];             // ���� id (siid)
        int success = Integer.parseInt(arr[1]);  // ������ ���ڵ� ����
        if (success > 0) { // ���������� insert������
            commit(conn);
        } else { // insert�� ����������
            rollback(conn);
        }
        close(conn);

        return result;
    }

    public String scheduleDayInsert(String siid, ArrayList<ScheduleDay> scheduleDayList) {
        String result = null;
        Connection conn = getConnection();
        ScheduleDao scheduleDao = ScheduleDao.getInstance();
        scheduleDao.setConnection(conn);

        result = scheduleDao.scheduleDayInsert(siid, scheduleDayList);
        String[] arr = result.split(":");
        int success = Integer.parseInt(arr[0]);  // ���� ����� ���ڵ� ����
        int target = Integer.parseInt(arr[1]);  // ����Ǿ���� �� ���ڵ� ����
        if (success == target)   commit(conn);
        else                    rollback(conn);
        close(conn);

        return result;
    }
}
