package ctrl;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/admimBbsContentsList")
public class AdminBbsContentsListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminBbsContentsListCtrl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
        
        int cpage = 1, psize = 10, bsize = 4, rcnt = 0, pcnt = 0, spage = 0;
        // ���� ������ ��ȣ, ������ ũ��, ��� ũ��, ���ڵ�(�Խñ�) ����, ������ ����, ���������� ���� ������ ������
        
        if (request.getParameter("cpage") != null)
            cpage = Integer.parseInt(request.getParameter("cpage"));
        
        String schtype = request.getParameter("schtype"); // �˻�����(���̵�, �г���, �̸�, �̸���, ����, ������)
        String keyword = request.getParameter("keyword");   // �˻��� 
        String where = "";      // �˻� ������ ���� ��� where���� ������ ����
        if (schtype == null || keyword == null) {
            schtype = "";   keyword = "";
        } else if (!schtype.equals("") && !keyword.equals("")) {    // �˻����ǰ� �˻�� ���� ���
            URLEncoder.encode(keyword, "UTF-8");
            // ������Ʈ������ �ְ� �޴� �˻�� �ѱ��� ��� IE���� ��Ȥ ������ �߻��� �� �����Ƿ� �����ڵ�� ��ȯ��Ŵ
            where = " where gp_" + schtype + " like '%" + keyword + "%' ";
        }
        
        AdminBbsContentsListSvc adminBbsContentsListSvc = new AdminBbsContentsListSvc();
        ArrayList<GoodPost> adminGoodPost = adminBbsContentsListSvc.getAdminBbsContentsList(where, cpage, psize);

        rcnt = adminBbsContentsListSvc.getBbsConListCount(where);
        // �˻��� �Խñ��� �� ������ �Խñ� �Ϸù�ȣ ��°� ��ü �������� ����� ���� �ʿ��� ��

        AdminBbsConInfo adminBbsConInfo = new AdminBbsConInfo();
        adminBbsConInfo.setBsize(bsize);       adminBbsConInfo.setCpage(cpage);
        adminBbsConInfo.setPcnt(pcnt);         adminBbsConInfo.setPsize(psize);
        adminBbsConInfo.setRcnt(rcnt);         adminBbsConInfo.setSpage(spage);
        adminBbsConInfo.setSchtype(schtype);   adminBbsConInfo.setKeyword(keyword);

        request.setAttribute("adminBbsConInfo", adminBbsConInfo);
        request.setAttribute("adminGoodPost", adminGoodPost);

        RequestDispatcher dispatcher = request.getRequestDispatcher("lmth/admin/04_bbs/bbsContents_list.jsp");
        dispatcher.forward(request, response);
	}
}
