package ctrl;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.MemberProcSvc;
import vo.*;

@WebServlet("*.mem")	// ȸ�� ���� �۾�(����, ��������, Ż��) traverSite
public class MemberCtrl extends HttpServlet {

	private static final long serialVersionUID = 1L;
    public MemberCtrl() { super(); }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	String kind = "";
    	String mi_mbti = "";
    	String mi_pw = "";
    	if (request.getAttribute("kind") != null) 
    	        kind = (String)request.getAttribute("kind");
    	else    kind = request.getParameter("kind");
    	if (request.getAttribute("mi_mbti") != null)
    	        mi_mbti = (String)request.getAttribute("mi_mbti");
    	else if ( request.getParameter("mi_mbti") != null )   
    	    mi_mbti = request.getParameter("mi_mbti");
    	if ( request.getParameter("mi_pw") != null )
    	    mi_pw = request.getParameter("mi_pw");
    	
        MemberInfo memberInfo = new MemberInfo();  
        
        HttpSession session = request.getSession();
        MemberInfo mi = null;
        
        if (kind.equals("mbti1") || kind.equals("mbti2")) {
            memberInfo.setMi_mbti(mi_mbti);
        }
        
        if (kind.equals("pwUp")) {
            memberInfo.setMi_pw(mi_pw);
        }
        
        if (kind.equals("in") || kind.equals("up")) {
        // ���� ó���ϴ� �۾��� ȸ�� �����̳� ���� ������ ���
            memberInfo.setMi_nickname(request.getParameter("mi_nickname"));
            memberInfo.setMi_mbti(mi_mbti);
            memberInfo.setMi_mail(request.getParameter("e1") + "@" + 
            request.getParameter("e3"));
        }
        
        if (kind.equals("in")) {
            memberInfo.setMi_id(request.getParameter("mi_id").trim().toLowerCase());
            memberInfo.setMi_pw(request.getParameter("mi_pw").trim());
            memberInfo.setMi_name(request.getParameter("mi_name").trim());
            memberInfo.setMi_birth(request.getParameter("mi_birth"));
            

        } else if (kind.equals("up") || kind.equals("del") || kind.equals("mbti1") || kind.equals("mbti2") || kind.equals("pwUp") ) {
        // ���� �����̳� Ż���� ���� �α��� �����̹Ƿ� ���̵� ���ǿ��� �����Ͽ� ������
            mi = (MemberInfo)session.getAttribute("loginInfo");
            memberInfo.setMi_id(mi.getMi_id());
            // �Ű������� ������ MemberInfo�� �ν��Ͻ� memberInfo�� ȸ�� ���̵� ������
            // ���� �����̳� Ż��� where ������ �������� ����� ���̵� �ʿ��ϱ� ����
        }
        
        
        MemberProcSvc memberProcSvc = new MemberProcSvc();
        int result = memberProcSvc.memberProc(kind, memberInfo);

        String link = "../index.jsp";   // �۾� �� �̵��� ��θ� ������ ����
        if (result == 1) {  // ���������� ���۵Ǿ�����
            if (kind.equals("in"))          {
                session.setAttribute("loginInfo", memberInfo);
                session.setMaxInactiveInterval(1800);
                ArrayList<ScheduleDay> scheduleDayList = new ArrayList<ScheduleDay>(); // �� ������ ���� ��Ҹ� ������ ������ ��Ҹ���Ʈ
                ScheduleInfo scheduleInfo = new ScheduleInfo(); // ��������
                session.setAttribute("scheduleDayList", scheduleDayList); //���ǿ� ���
                session.setAttribute("scheduleInfo", scheduleInfo); //���ǿ� ���
                session.setAttribute("selectDay", 0);
                session.setAttribute("selectDate", " ");
                link = "../member/join_end.jsp";
            }
            else if (kind.equals("del"))    link = "../member/logout.jsp";
            else if (kind.equals("up")) {
                link = "../mypage/info_up_form.jsp";
                mi.setMi_mbti(memberInfo.getMi_mbti());
                mi.setMi_mail(memberInfo.getMi_mail());
                // ���� ���� ������ ���� ���ǿ� ����ִ� �α��� ȸ�� ������ �����Ŵ
            } 
            else if (kind.equals("mbti1")) {
                link="/traverSite/MBTIMain";
                mi.setMi_mbti(memberInfo.getMi_mbti());
                mi.setMi_mail(memberInfo.getMi_mail());
            }
            else if (kind.equals("mbti2")) {
                link="lmth/mbti/mbti_start_sub2.jsp";
                mi.setMi_mbti(memberInfo.getMi_mbti());
                mi.setMi_mail(memberInfo.getMi_mail());
            }
            else if (kind.equals("pwUp")) {
                link="../mypage/pw_check.jsp";
                mi.setMi_pw(memberInfo.getMi_pw());
            }
        } else {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script> alert('�۾��� �����߽��ϴ�. �ٽ� �õ��� ������.'); history.back(); </script>");
            out.close();
        }
        
    	response.sendRedirect(link);
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
