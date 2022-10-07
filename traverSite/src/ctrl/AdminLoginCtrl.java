package ctrl;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/adminLogin")
public class AdminLoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminLoginCtrl() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id").trim().toLowerCase();
		String pw = request.getParameter("pw").trim();
		String url = request.getParameter("url").replace('$', '&');
		
		AdminLoginSvc adminLoginSvc = new AdminLoginSvc();
		AdminInfo adminInfo = adminLoginSvc.getAdminLogin(id, pw);
		
		if (adminInfo != null) {	// �α��� ������
			HttpSession session = request.getSession();
			// JSP�� �ƴϹǷ� ���ǰ�ü�� ����Ϸ��� ���� HttpSessionŬ������ �ν��Ͻ��� �����ؾ� �Ѵ�.
			session.setAttribute("adminInfo", adminInfo);
			// �α����� ȸ���� ������ ���� loginInfo�ν��Ͻ��� ������ �Ӽ����� ����
			response.sendRedirect(url);
		}
	}
}
