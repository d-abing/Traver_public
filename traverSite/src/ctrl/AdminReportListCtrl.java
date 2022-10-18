package ctrl;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/adminReportList")
public class AdminReportListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminReportListCtrl() {
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
            where = " where re_" + schtype + " like '%" + keyword + "%' ";
//            ���⼭ where�� re_ �ƴϰ� �������� �ٿ��� ��
        }
        
        AdminReportListSvc adminReportListSvc = new AdminReportListSvc();
        ArrayList<Report> adminReport = adminReportListSvc.getAdminReportList(where, cpage, psize);

        AdminReportSchInfo adminReportSch = new AdminReportSchInfo();
        adminReportSch.setBsize(bsize);       adminReportSch.setCpage(cpage);
        adminReportSch.setPcnt(pcnt);         adminReportSch.setPsize(psize);
        adminReportSch.setRcnt(rcnt);         adminReportSch.setSpage(spage);
        adminReportSch.setSchtype(schtype);   adminReportSch.setKeyword(keyword);

        request.setAttribute("adminReportSch", adminReportSch);
        request.setAttribute("adminReport", adminReport);

        RequestDispatcher dispatcher = request.getRequestDispatcher("lmth/admin/03_report/report_list.jsp");
        dispatcher.forward(request, response);
	}
}
