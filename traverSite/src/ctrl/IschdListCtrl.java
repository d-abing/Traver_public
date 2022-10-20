package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.net.*;
import svc.*;
import vo.*;

@WebServlet("/ischdList")
public class IschdListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public IschdListCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	    
	    ArrayList<GoodInfo> goodList = new ArrayList<GoodInfo>();
        // �������� ����� �����ϱ� ���� ArrayList�� �ȿ� ����� �����ʹ� GoodInfo�� �ν��Ͻ��� ���
	   
	    HttpSession session = request.getSession();
        MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
        
	    String sy = ""; // �˻��⵵
        if (request.getParameter("sy") != null)  sy = request.getParameter("sy");
        
        String keyword = request.getParameter("keyword"); // �˻�Ű����
        
        // select * from t_schedule_zzim a, t_good_info b 
        // where a.mi_id = 'test11' and a.gi_id = b.gi_id and b.gi_name like '%%' order by a.sz_date
        
        String where = " where a.mi_id ='" + loginInfo.getMi_id() + "' and a.gi_id = b.gi_id ";  // �˻�� ���� ��� where���� ������ ���� 
        if (keyword == null ) {                   // �˻�� ������
            keyword = ""; 
        } else if (!keyword.equals("")) {    // �˻�� ���� ���
            URLEncoder.encode(keyword, "UTF-8");
            // ������Ʈ������ �ְ� �޴� �˻�� �ѱ��� ��� IE���� ��Ȥ ������ �߻��� �� �����Ƿ� �����ڵ�� ��ȯ��Ŵ
            where += " and gi_name like '%" + keyword + "%' ";    
        }
        
        
        
        String o = request.getParameter("o");     // �������� 
        if (o == null || o.equals(""))  o = "a";    // ���������� ������ �⺻ a(��� �ֽ� ��)����
        String orderBy = "";
        switch (o) {
        case "a" : // ��� �ֽ� ��
            orderBy = " order by a.gi_id desc";   break;
        case "b" : // ��� ������ ��
            orderBy = " order by a.gi_id";        break;
        }
        
        IschdListSvc ischdListSvc = new IschdListSvc();
        
        goodList = ischdListSvc.getIschdList(where, orderBy); // ���ȭ�鿡�� ������ �������� ��� ArrayList������ �޾ƿ�
        
        request.setAttribute("sy", sy); 
        request.setAttribute("keyword", keyword); 
        request.setAttribute("o", o); 
        request.setAttribute("goodList", goodList);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("lmth/mysche/ischd_list.jsp");
        dispatcher.forward(request, response);
        
	}

}
