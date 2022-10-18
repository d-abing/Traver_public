package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/mschdDetail")
public class MschdDetailCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MschdDetailCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	    
	    HttpSession session = request.getSession();
        MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
        
        if (loginInfo == null) { // �α��� �Ǿ� ���� ������
            response.setContentType("text/html; charset=utf-8"); 
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('�α��� �� ����Ͻ� �� �ֽ��ϴ�.');");
            out.println("location.replace('/traverSite/index.jsp');");
            out.println("</script>");
            out.close();
        } 
        String miid = loginInfo.getMi_id(); 
        
        String siid = request.getParameter("siid");
        // ���� �� ȭ�鿡�� ������ ������ ���̵�� where������ ���
        
        MschdDetailSvc mschdDetailSvc = new MschdDetailSvc();
        ScheduleInfo si = mschdDetailSvc.getMschdDetail(miid, siid); 
         // ������ �������̵� �ش��ϴ� ������������ ScheduleInfo�� �ν��Ͻ� si�� �޾ƿ�
        
        
        //System.out.println(si.getSi_dnum()); �ܼ�Ȯ�ο�
        
        
        request.setAttribute("si", si);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("lmth/mysche/mschd_detail.jsp");
        dispatcher.forward(request, response);
        
	}

}
