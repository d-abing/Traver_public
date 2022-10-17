package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/scheduleInfo")
public class ScheduleInfoCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ScheduleInfoCtrl() { super(); }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        
        String sisdate = request.getParameter("sisdate");       // ������
        String siedate = request.getParameter("siedate");       // ������
        int sidnum =  Integer.parseInt(request.getParameter("sidnum"));        // �� ������
        
        /*
         * ���� ��¥�� �����ϰԵǸ�?
         * �� ��ȣ�� �ο����ִ� ��� ��¥�� �ٲ�����. �װ� ���.
         * �׷� ó������ ������ȣ�� �ְ�, for�� �����鼭 ��¥�� ����Ҷ� ����ؼ� set�ؼ� ����.
         * 
         * �����ִ°� ��̸���Ʈ �������.
         */
                
        
        HttpSession session = request.getSession();
        ScheduleInfo scheduleInfo = (ScheduleInfo)session.getAttribute("scheduleInfo");
        
        scheduleInfo.setSi_sdate(sisdate);
        scheduleInfo.setSi_edate(siedate);
        scheduleInfo.setSi_dnum(sidnum);
        
        session.setAttribute("scheduleInfo", scheduleInfo);
        int result = scheduleInfo.getSi_dnum();
        
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(result);
	}

}
