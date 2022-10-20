package ctrl;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/admimBbsReviewList")
public class AdminBbsReviewListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminBbsReviewListCtrl() {
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
            where = " where rl_" + schtype + " like '%" + keyword + "%' ";
        }
        
        AdminBbsReviewListSvc adminBbsReviewListSvc = new AdminBbsReviewListSvc();
        ArrayList<AdminReviewList> adminReviewList = adminBbsReviewListSvc.getAdminBbsReviewList(where, cpage, psize);

        rcnt = adminBbsReviewListSvc.getBbsReVListCount(where);
        // �˻��� �Խñ��� �� ������ �Խñ� �Ϸù�ȣ ��°� ��ü �������� ����� ���� �ʿ��� ��

        AdminBbsReVInfo adminBbsReVInfo = new AdminBbsReVInfo();
        adminBbsReVInfo.setBsize(bsize);       adminBbsReVInfo.setCpage(cpage);
        adminBbsReVInfo.setPcnt(pcnt);         adminBbsReVInfo.setPsize(psize);
        adminBbsReVInfo.setRcnt(rcnt);         adminBbsReVInfo.setSpage(spage);
        adminBbsReVInfo.setSchtype(schtype);   adminBbsReVInfo.setKeyword(keyword);

        request.setAttribute("adminBbsReVInfo", adminBbsReVInfo);
        request.setAttribute("adminReviewList", adminReviewList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("lmth/admin/04_bbs/bbsReview_list.jsp");
        dispatcher.forward(request, response);
	}

}
