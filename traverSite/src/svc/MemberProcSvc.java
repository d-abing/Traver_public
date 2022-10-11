package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class MemberProcSvc {
	public int memberProc(String kind, MemberInfo memberInfo) {
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		
		int result = 0;
		if (kind.equals("in")) {
			result = memberProcDao.memberInsert(memberInfo);
		} else if (kind.equals("up")) {
			result = memberProcDao.memberUpdate(memberInfo);
		} else if (kind.equals("del")) {
//			result = memberProcDao.memberDelete(memberInfo);
		}
		
		if (result == 1)	commit(conn);
		else				rollback(conn);
		// ����� ������ insert, update, delete ���̸� �ݵ�� Ʈ������� �Ϸ��ؾ� ��
		close(conn);

		return result;
	}
}
