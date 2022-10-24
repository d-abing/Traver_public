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
		ArrayList<ScheduleInfo> fullScheduleList = new ArrayList<ScheduleInfo>();
	    // �⵵ ������ ���� ��� ����Ʈ

		
		HttpSession session = request.getSession();
		MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
		if (loginInfo == null) { // �α��� �Ǿ� ���� ������
            response.setContentType("text/html; charset=utf-8"); 
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('�α��� �� ����Ͻ� �� �ֽ��ϴ�.');");
            out.println("location.replace('/traverSite/lmth/member/login_form.jsp');");
            out.println("</script>");
            out.close();
        } 
		
		
		String where = " where mi_id ='" + loginInfo.getMi_id() + "' ";    // where���� ������ ���� 
		String sy = "";
		if (request.getParameter("sy") != null )  { 
		    if ( !request.getParameter("sy").equals("null") && ! request.getParameter("sy").equals("total") ) {
	          sy = request.getParameter("sy");
	          where += " and si_sdate like '%" + sy + "%' ";
		    }
	    } 

		
		String keyword = request.getParameter("keyword"); // ������ �˻��� keyword
		
		
		if (keyword == null ) {                   // �˻�� ������
		    keyword = ""; 
        } else if (!keyword.equals("")) {    // �˻�� ���� ���
            URLEncoder.encode(keyword, "UTF-8");
            // ������Ʈ������ �ְ� �޴� �˻�� �ѱ��� ��� IE���� ��Ȥ ������ �߻��� �� �����Ƿ� �����ڵ�� ��ȯ��Ŵ
            where += " and si_name like '%" + keyword + "%' ";    
        }
		
		String o = request.getParameter("o");     // �������� 
		if (o == null || o.equals(""))  o = "a";    // ���������� ������ �⺻ a(��� �ֽ� ��)����
        String orderBy = "";
        switch (o) {
        case "a" : // ��� �ֽ� ��
            orderBy = " order by si_date desc";   break;
        case "b" : // ��� ������ ��
            orderBy = " order by si_date";        break;
        }
		
		MschdListSvc mschdListSvc = new MschdListSvc();
		fullScheduleList = mschdListSvc.getfullMschdList(loginInfo.getMi_id());
		scheduleList = mschdListSvc.getMschdList(where, orderBy); // ���ȭ�鿡�� ������ ���� ��� ArrayList������ �޾ƿ�
		
		request.setAttribute("sy", sy); 
		request.setAttribute("keyword", keyword); 
		request.setAttribute("o", o); 
		request.setAttribute("scheduleList", scheduleList);
		request.setAttribute("fullScheduleList", fullScheduleList);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("lmth/mysche/mschd_list.jsp");
        dispatcher.forward(request, response);
	}
	
}
