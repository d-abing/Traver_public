package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/scheduleDay")
public class ScheduleDayListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ScheduleDayListCtrl() {super(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        
        
        int sddnum = Integer.parseInt(request.getParameter("sddnum"));     // �ش� ����
        String sddate = request.getParameter("sddate");     // �ش� ��¥
        String piid =  request.getParameter("piid");        // ��� id
        String piname = request.getParameter("piname");     // ��� �̸�
        
        
        /*
         * ���� ��¥�� �����ϰԵǸ�?
         * �� ��ȣ�� �ο����ִ� ��� ��¥�� �ٲ�����. �װ� ���.
         * �׷� ó������ ������ȣ�� �ְ�, for�� �����鼭 ��¥�� ����Ҷ� ����ؼ� set�ؼ� ����.
         * 
         * �����ִ°� ��̸���Ʈ �������.
         */
                
        
        HttpSession session = request.getSession();
        ArrayList<ScheduleDay> scheduleDayList = (ArrayList<ScheduleDay>)session.getAttribute("scheduleDayList");
        
        int result = 0;
        
        if (scheduleDayList.size() < 10) {
            ScheduleDay sd = new ScheduleDay();
            sd.setSd_dnum(sddnum);
            sd.setSd_date(sddate);
            sd.setPi_id(piid);
            sd.setPi_name(piname);
            
            scheduleDayList.add(sd);
            session.setAttribute("scheduleDayList", scheduleDayList);

            result = scheduleDayList.size();   
        } else {
            result = 0;
        }
        

        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(result);
	}

}
