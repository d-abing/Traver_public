package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/scheduleIn")
public class ScheduleInCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ScheduleInCtrl() { super(); }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // ���� �߰��� ��� & ���� ������ DB�� ������ ��Ʈ��
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        ArrayList<ScheduleDay> scheduleDayList = (ArrayList<ScheduleDay>)session.getAttribute("scheduleDayList");
        // �߰��� ����(���)������ ��� ����Ʈ
        
        if (scheduleDayList.size() == 0) {  // � ��ҵ� �߰����� �ʾҴٸ� ƨ�ܹ�����!
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('������ �߰��� �� �ٽ� �õ��� �ּ���.');");
            out.println("</script>");
            out.close();
        }
        

        //scheduleInfo Insert ������
        String scheduleName = (String)session.getAttribute("schedule_name"); // ������
        if (scheduleName == null || scheduleName.equals("null")) {
            scheduleName = "�ӽ� ���� ����";
        }
        ScheduleInfo scheduleInfo = (ScheduleInfo)session.getAttribute("scheduleInfo"); 
        // ���� ���� �ҷ����� (������, ������, day����)

        MemberInfo mi = (MemberInfo)session.getAttribute("loginInfo");
        String miid = mi.getMi_id(); // scheduleInfo insert�� miid
        String imgPiid = scheduleDayList.get(0).getPi_id(); // �̹��� ����� ������ piid �ϳ�
        // scheduleDayList = �߰��� ����(���)����Ʈ �ҷ����� (����ID, ���ID, ��Ҹ�, ������ȣ, �ش糯¥, ����)
        ScheduleInSvc scheduleInSvc = new ScheduleInSvc();
        String result = scheduleInSvc.scheduleInsert(miid, scheduleName, imgPiid, scheduleInfo, scheduleDayList);
       
        String[] arr = result.split(":");
        String siid = arr[0]; // ���� ID
        int infoSuccess = Integer.parseInt(arr[1]); //����� info ����
        int daySuccess = Integer.parseInt(arr[2]); // ����� day����
        int dayTarget = Integer.parseInt(arr[3]); // ����ƾ���� day����
        
        if (infoSuccess > 0 && daySuccess == dayTarget) { // ������� ����������
            // ���ǿ� ����� ���� ����������
            session.removeAttribute("scheduleDayList");
            session.removeAttribute("scheduleInfo");
            session.removeAttribute("selectDay");
            session.removeAttribute("selectDate");
            session.removeAttribute("dateList");
            
            // ���ǿ� ���밪�� �ٽ� �����
            ArrayList<ScheduleDay> scheduleDayLst = new ArrayList<ScheduleDay>();
            ScheduleInfo scheduleIfo = new ScheduleInfo();
            session.setAttribute("scheduleDayList", scheduleDayLst);
            session.setAttribute("scheduleInfo", scheduleIfo);
            session.setAttribute("selectDay", 0);
            session.setAttribute("selectDate", " ");
            
            
            request.setAttribute("siid", siid);
            response.sendRedirect("/traverSite/lmth/gotraver/remove_session.jsp?siid=" + siid); // �������������� siid�� ������ �̵�
        } else { // ���� ��Ͽ� ����������
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script> alert('���� ��Ͽ� �����߽��ϴ�.'); history.back(); </script>");
            out.close();
        }
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
	}
}
