package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/ischdDetail")
public class IschdDetailCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public IschdDetailCtrl() { super(); }

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        
        String giid = request.getParameter("giid");
        // �������� �� ȭ�鿡�� ������ ������ ���̵�� where������ ���
       // System.out.println(giid); //�ܼ�Ȯ�ο�
        
                
        
        IschdDetailSvc ischdDetailSvc = new IschdDetailSvc();
        GoodInfo gi = ischdDetailSvc.getIschdDetail(miid, giid); 
        // ������ �������̵� �ش��ϴ� �������� �������� GoodInfo�� �ν��Ͻ� gi�� �޾ƿ�
        //System.out.println(gi.getGi_dnum()); �ܼ�Ȯ�ο�
        
        GoodPost goodPost = ischdDetailSvc.getGoodPost(giid);
        //System.out.println(goodPost.getGp_id());
        
        request.setAttribute("goodPost", goodPost);
        request.setAttribute("gi", gi);
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("lmth/mysche/ischd_detail.jsp");
        dispatcher.forward(request, response);
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

}
