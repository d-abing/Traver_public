package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import svc.*;
import vo.*;

public class MemberProcAct implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String kind = request.getParameter("kind");
		// ȸ�� ����(in), ����(up), Ż��(del) ���θ� �����ϴ� ��
		MemberInfo memberInfo = new MemberInfo();	// �޾ƿ� ȸ�� �����͵��� ������ �ν��Ͻ�
		
		HttpSession session = request.getSession();
		MemberInfo mi = null;
		// ���� �����̳� Ż��� ����� ���Ǿȿ� ����ִ� �α��� ������ ������ �ν��Ͻ�
		
		if (kind.equals("in") || kind.equals("up")) {
		// ���� ó���ϴ� �۾��� ȸ�� �����̳� ���� ������ ���
			memberInfo.setMi_nickname(request.getParameter("mi_nickname"));
			memberInfo.setMi_nickname(request.getParameter("mi_mbti"));
			memberInfo.setMi_mail(request.getParameter("e1") + "@" + 
			request.getParameter("e3"));
		}
		
		if (kind.equals("in")) {
			memberInfo.setMi_id(request.getParameter("mi_id").trim().toLowerCase());
			memberInfo.setMi_pw(request.getParameter("mi_pw").trim());
			memberInfo.setMi_name(request.getParameter("mi_name").trim());
			memberInfo.setMi_birth(request.getParameter("mi_birth"));
			

		} else if (kind.equals("up") || kind.equals("del")) {
		// ���� �����̳� Ż���� ���� �α��� �����̹Ƿ� ���̵� ���ǿ��� �����Ͽ� ������
			mi = (MemberInfo)session.getAttribute("loginInfo");
			memberInfo.setMi_id(mi.getMi_id());
			// �Ű������� ������ MemberInfo�� �ν��Ͻ� memberInfo�� ȸ�� ���̵� ������
			// ���� �����̳� Ż��� where ������ �������� ����� ���̵� �ʿ��ϱ� ����
		}
		
		MemberProcSvc memberProcSvc = new MemberProcSvc();
		int result = memberProcSvc.memberProc(kind, memberInfo);

		String link = "../index.jsp";	// �۾� �� �̵��� ��θ� ������ ����
		if (result == 1) {	// ���������� ���۵Ǿ�����
			if (kind.equals("in"))			link = "../member/login_form.jsp";
			else if (kind.equals("del"))	link = "../member/logout.jsp";
			else if (kind.equals("up")) {
				link = "../index.jsp";
				mi.setMi_mbti(memberInfo.getMi_mbti());
				mi.setMi_mail(memberInfo.getMi_mail());
				// ���� ���� ������ ���� ���ǿ� ����ִ� �α��� ȸ�� ������ �����Ŵ
			}
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script> alert('�۾��� �����߽��ϴ�. �ٽ� �õ��� ������.'); history.back(); </script>");
			out.close();
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(link);
		
		return forward;
	}
}
