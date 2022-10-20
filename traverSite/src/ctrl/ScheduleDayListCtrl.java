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
        
        String piid =  request.getParameter("piid");        // ��� id
        String coords =  request.getParameter("coords");        // ��� id
        String piname = request.getParameter("piname");     // ��� �̸�
        int sddnum = Integer.parseInt(request.getParameter("sddnum"));     // �ش� ����
        String sddate = request.getParameter("sddate");     // �ش� ��¥
        
        
        /*
         * ���� ��¥�� �����ϰԵǸ�?
         * �� ��ȣ�� �ο����ִ� ��� ��¥�� �ٲ�����. �װ� ���.
         * �׷� ó������ ������ȣ�� �ְ�, for�� �����鼭 ��¥�� ����Ҷ� ����ؼ� set�ؼ� ����.
         * 
         * �����ִ°� ��̸���Ʈ �������.
         */
                
        
        HttpSession session = request.getSession();
        ArrayList<ScheduleDay> scheduleDayList = (ArrayList<ScheduleDay>)session.getAttribute("scheduleDayList");
        
        int result = 0; // 0 �� �� ���� ����, 1 ��Ұ��� �ʰ� ����, 2 �ߺ� ��� ����, 3 ����
        boolean isCanAddDay = true;   // ���� ���� 10�� �ʰ����� ���� ����
        boolean isCanAddPlace = true;   // ���� ���� ��� �ߺ����� ���� ���� 
        int dayCount = 0; // �ش������� ������ ������ ���� 
        
        for (int i = 0 ; i < scheduleDayList.size(); i++ ) { // ���� ����, �ߺ� ��ҿ��� �˻� �ݺ���
            ScheduleDay sd = scheduleDayList.get(i);
            if( sd.getSd_dnum() == sddnum ) { // ���� ���� �˻� + ���� (����� ���ڿ� ���õ� ���� ���Ͻ�)
                dayCount ++;    // ���� ���� 1����
                if (sd.getPi_id().equals(piid)) {   // �ߺ� ��� �����̶�� ���� + ����
                    isCanAddPlace = false;
                    result = 2;
                    break;
                }
            }
            if (dayCount >= 10) { // ���� 10�� �ʰ���� ���� + ����
                isCanAddDay = false;
                result = 1;
                break;
            }
        }

        
        if (isCanAddDay == true && isCanAddPlace == true) { // ��� �߰��� �����ϸ� �߰� (10���̸�, �ߺ�X)
            ScheduleDay sd = new ScheduleDay();
            sd.setPi_id(piid);
            sd.setPi_name(piname);
            sd.setSd_coords(coords);
            sd.setSd_dnum(sddnum);
            sd.setSd_date(sddate);
            
            scheduleDayList.add(sd);
            session.setAttribute("scheduleDayList", scheduleDayList);

            result = 3; //����
        } 
        

        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(result);
	}

}
