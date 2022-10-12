package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/login")
public class LoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid").trim().toLowerCase();
		String pwd = request.getParameter("pwd").trim();
		String url = request.getParameter("url").replace('$', '&');
		
		LoginSvc loginSvc = new LoginSvc(); 
		MemberInfo loginInfo = loginSvc.getLoginMember(uid, pwd);

		
		if (loginInfo != null) { 	// �α��� ������
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", loginInfo);
			session.setMaxInactiveInterval(1800);
			response.sendRedirect(url);
	        ArrayList<ScheduleDay> scheduleDayList = new ArrayList<ScheduleDay>(); // �� ������ ���� ��Ҹ� ������ ������ ��Ҹ���Ʈ
	        ScheduleInfo scheduleInfo = new ScheduleInfo(); // ��������
	        session.setAttribute("scheduleDayList", scheduleDayList); //���ǿ� ���
            session.setAttribute("scheduleInfo", scheduleInfo); //���ǿ� ���
            session.setAttribute("selectDay", " ");
            session.setAttribute("selectDate", " ");
			
		} else {	// �α��� ���н�
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script> alert('���̵�� �Ǵ� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�. \\n �Է��Ͻ� ������ �ٽ� Ȯ���� �ּ���.'); history.back(); </script>");
		}
	}
}
