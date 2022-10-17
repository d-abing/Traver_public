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
        ScheduleInfo scheduleInfo = (ScheduleInfo)session.getAttribute("scheduleInfo"); 
        // ���� ���� �ҷ����� (������, ������, day����)

        MemberInfo mi = (MemberInfo)session.getAttribute("loginInfo");
        String miid = mi.getMi_id(); // scheduleInfo insert�� miid
        String imgPiid = scheduleDayList.get(1).getPi_id(); // �̹��� ����� ������ piid �ϳ�
        // scheduleDayList = �߰��� ����(���)����Ʈ �ҷ����� (����ID, ���ID, ��Ҹ�, ������ȣ, �ش糯¥, ����)
        ScheduleInSvc scheduleInSvc = new ScheduleInSvc();
        String result = scheduleInSvc.scheduleInfoInsert(miid, scheduleName, imgPiid, scheduleInfo);
       
        String[] arr = result.split(":");
        String siid = arr[0]; // ���� ID
        int success = Integer.parseInt(arr[1]);  // ������ insert ��
        if (success > 0) { // ScheduleInfo insert ����������
            // ������� �߰� ����
            String result2 = scheduleInSvc.scheduleDayInsert(siid, scheduleDayList);
            String[] arr2 = result.split(":");
            int success2 = Integer.parseInt(arr[0]);  // ���� ����� ���ڵ� ����
            int target2 = Integer.parseInt(arr[1]);  // ����Ǿ���� �� ���ڵ� ����
            
            if (success2 == target2)   {    // scheduleDay insert�� ����������
                response.sendRedirect("scheduleView?siid=" + siid);
            } else { // ScheduleDay insert�� ���������� �˸�
                response.setContentType("text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script> alert('���� ��Ͽ� �����߽��ϴ�.'); history.back(); </script>");
                out.close();
            }
            
        } else { // ScheduleInfo insert�� ����������
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
