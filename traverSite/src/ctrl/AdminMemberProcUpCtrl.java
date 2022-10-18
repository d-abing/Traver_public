package ctrl;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/adminMemberProcUp")
public class AdminMemberProcUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminMemberProcUpCtrl() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	    String miid = request.getParameter("miid");
	    String name = request.getParameter("name");
	    String nickname = request.getParameter("nickname");
	    String mail = request.getParameter("mail");
	    String pw = request.getParameter("pw");
	    String birth = request.getParameter("birth");
	    String mbti = request.getParameter("mbti");
	    String status = request.getParameter("status");
	    String join = request.getParameter("join");
	    
	    System.out.println(miid);
	    
	    MemberInfo memberInfo = new MemberInfo();
	    memberInfo.setMi_id(miid);
	    memberInfo.setMi_name(name);
	    memberInfo.setMi_nickname(nickname);
	    memberInfo.setMi_mail(mail);
	    memberInfo.setMi_pw(pw);
	    memberInfo.setMi_birth(birth);
	    memberInfo.setMi_mbti(mbti);
	    memberInfo.setMi_status(status);
	    memberInfo.setMi_join(join);
	    
	    AdminMemberProcUpSvc adminMemberProcUpSvc = new AdminMemberProcUpSvc();
	    int result = adminMemberProcUpSvc.AdminMemberProcUp(memberInfo);
	    
	    if (result > 0) {   // ���������� ���� �����Ǿ��ٸ�
            response.sendRedirect("/traverSite/adminMemberList");
        } else {    // �� ������ ������ �߻��ߴٸ�
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('ȸ�� ������ �����߽��ϴ�. �ٽ� �õ��� ������.');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
        }
	}
}
