package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/scheduleSelect")
public class ScheduleSelectCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ScheduleSelectCtrl() {  super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int selectDay = Integer.parseInt(request.getParameter("selectDay"));       // ������ ��
        String selectDate = request.getParameter("selectDate");   // ������ ���� ��¥
        String datelist = request.getParameter("dateList");   // ������ ���� ��¥
        
        String[] dateList = datelist.split(",");    // ��� ���� ��¥�� ���

        HttpSession session = request.getSession();
        session.setAttribute("selectDay", selectDay);
        session.setAttribute("selectDate", selectDate);
        session.setAttribute("dateList", dateList);
        
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(selectDay);
	}
}
