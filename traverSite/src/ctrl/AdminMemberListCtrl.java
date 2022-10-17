package ctrl;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/adminMemberList")
public class AdminMemberListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminMemberListCtrl() {
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
            where = " where mi_" + schtype + " like '%" + keyword + "%' ";
        }
        
        AdminMemberListSvc adminMemberListSvc = new AdminMemberListSvc();
        ArrayList<MemberInfo> memberInfo = adminMemberListSvc.getAdminMemberList(where, cpage, psize);

        rcnt = adminMemberListSvc.getMemberListCount(where);
        // �˻��� �Խñ��� �� ������ �Խñ� �Ϸù�ȣ ��°� ��ü �������� ����� ���� �ʿ��� ��

        AdminMemberInfo adminMemberInfo = new AdminMemberInfo();
        adminMemberInfo.setBsize(bsize);       adminMemberInfo.setCpage(cpage);
        adminMemberInfo.setPcnt(pcnt);         adminMemberInfo.setPsize(psize);
        adminMemberInfo.setRcnt(rcnt);         adminMemberInfo.setSpage(spage);
        adminMemberInfo.setSchtype(schtype);   adminMemberInfo.setKeyword(keyword);

        request.setAttribute("adminMemberInfo", adminMemberInfo);
        request.setAttribute("memberInfo", memberInfo);

        RequestDispatcher dispatcher = request.getRequestDispatcher("lmth/admin/01_member/mem_list.jsp");
        dispatcher.forward(request, response);
	}
}
