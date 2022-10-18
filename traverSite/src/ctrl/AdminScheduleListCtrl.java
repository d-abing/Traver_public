package ctrl;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/adminScheduleList")
public class AdminScheduleListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminScheduleListCtrl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
        
        int cpage = 1, psize = 10, bsize = 4, rcnt = 0, pcnt = 0, spage = 0;
        // ���� ������ ��ȣ, ������ ũ��, ��� ũ��, ���ڵ�(�Խñ�) ����, ������ ����, ���������� ���� ������ ������
        
        if (request.getParameter("cpage") != null)
            cpage = Integer.parseInt(request.getParameter("cpage"));
        
        String schtype = request.getParameter("schtype"); // �˻�����
        String keyword = request.getParameter("keyword");   // �˻��� 
        String where = "";      // �˻� ������ ���� ��� where���� ������ ����
        if (schtype == null || keyword == null) {
            schtype = "";   keyword = "";
        } else if (!schtype.equals("") && !keyword.equals("")) {    // �˻����ǰ� �˻�� ���� ���
            URLEncoder.encode(keyword, "UTF-8");
            // ������Ʈ������ �ְ� �޴� �˻�� �ѱ��� ��� IE���� ��Ȥ ������ �߻��� �� �����Ƿ� �����ڵ�� ��ȯ��Ŵ
            if (schtype.equals("mi")) { // �˻������� 
                where = " where mi_" + schtype + " like '%" + keyword + "%' ";
            } else {
                where = " where si_" + schtype + " like '%" + keyword + "%' ";
            }
        }
        
        AdminScheduleListSvc adminScheduleListSvc = new AdminScheduleListSvc();
        ArrayList<AdminSchList> adminSchList = adminScheduleListSvc.getAdminScheduleList(where, cpage, psize);

        rcnt = adminScheduleListSvc.getSchListCount(where);
        // �˻��� �Խñ��� �� ������ �Խñ� �Ϸù�ȣ ��°� ��ü �������� ����� ���� �ʿ��� ��

        AdminSchInfo adminSchInfo = new AdminSchInfo();
        adminSchInfo.setBsize(bsize);       adminSchInfo.setCpage(cpage);
        adminSchInfo.setPcnt(pcnt);         adminSchInfo.setPsize(psize);
        adminSchInfo.setRcnt(rcnt);         adminSchInfo.setSpage(spage);
        adminSchInfo.setSchtype(schtype);   adminSchInfo.setKeyword(keyword);

        request.setAttribute("adminSchInfo", adminSchInfo);
        request.setAttribute("adminSchList", adminSchList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("lmth/admin/05_schedule/schedule_list.jsp");
        dispatcher.forward(request, response);
	}
}
