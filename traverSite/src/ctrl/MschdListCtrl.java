package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.net.*;
import svc.*;
import vo.*;

@WebServlet("/mschdList")
public class MschdListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MschdListCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ArrayList<ScheduleInfo> scheduleList = new ArrayList<ScheduleInfo>();
		// ������ ����� �����ϱ� ���� ArrayList�� �ȿ� ����� �����ʹ� ScheduleInfo�� �ν��Ͻ��� ���

		String keyword = request.getParameter("keyword");	// ������ �˻��� keyword
		String where = "";		// �˻�� ���� ��� where���� ������ ���� 
		if (keyword == null) { // Ű����˻�� ������
			keyword = "";
		} else if (!keyword.equals("")) {	// �˻�� ���� ���
			URLEncoder.encode(keyword, "UTF-8"); // ������Ʈ������ �ְ� �޴� �˻�� �ѱ��� ��� IE���� ��Ȥ ������ �߻��� �� �����Ƿ� �����ڵ�� ��ȯ��Ŵ	
			where = " where si_name like '%" + keyword + "%' ";
		}
		
		MschdListSvc mschdListSvc = new MschdListSvc();
		scheduleList = mschdListSvc.getMschdList(where); // ���ȭ�鿡�� ������ ���� ��� ArrayList������ �޾ƿ�
		
        request.setAttribute("scheduleList", scheduleList);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("lmth/mysche/mschd_list.jsp");
        dispatcher.forward(request, response);
	}
}
