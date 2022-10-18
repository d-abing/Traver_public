package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class ScheduleInSvc {
    public String scheduleInsert(String miid, String scheduleName, String imgPiid, ScheduleInfo scheduleInfo, ArrayList<ScheduleDay> scheduleDayList) {
        Connection conn = getConnection();
        ScheduleDao scheduleDao = ScheduleDao.getInstance();
        scheduleDao.setConnection(conn);
        
        String info = scheduleDao.scheduleInfoInsert(miid, scheduleName, imgPiid, scheduleInfo);
        String[] infoArr = info.split(":"); // siid�� �������θ� ���� ���ڵ� ��
        String dayIn = "";  // Day�� �־� �����߾���� ���ڵ��, ������ ���ڵ���� ���� ����
        
        String siid = infoArr[0]; // ���� id (siid)
        int infoSuccess = Integer.parseInt(infoArr[1]);  // info ������ ���ڵ� ����
        if (infoSuccess > 0) { // info ���������� insert������
            dayIn = scheduleDao.scheduleDayInsert(siid, scheduleDayList);
            // day ���� ����
            
            String[] dayArr = dayIn.split(":");   // ����Ƚ��:����ƾ���� ���ڵ� ����� �迭
            int daySuccess = Integer.parseInt(dayArr[0]); // Day ����� ���ڵ� ����
            int dayTarget = Integer.parseInt(dayArr[1]);  // Day ����Ǿ���� �� ���ڵ� ����
            
            if(daySuccess == dayTarget) {
                commit(conn);
            } else {
                rollback(conn);
            }
        } else { // insert�� ����������
            rollback(conn);
        }
        close(conn);

        return info + ":" + dayIn;
    }
}
