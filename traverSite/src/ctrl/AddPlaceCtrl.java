package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/addPlace")
public class AddPlaceCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddPlaceCtrl() { super(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        
        
        String sddnum = request.getParameter("sddnum");     // �ش� ����
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
        ArrayList<PlaceInfo> addPlaceList = (ArrayList<PlaceInfo>)session.getAttribute("addPlaceList");
        
        int result = 0;
        
        if (addPlaceList.size() < 10) {
            PlaceInfo pi = new PlaceInfo();
            pi.setPi_id(piid);
            pi.setPi_name(piname);
            
            addPlaceList.add(pi);
            session.setAttribute("scheduleList", addPlaceList);

            result = addPlaceList.size();   
        } else {
            result = 0;
        }
        

        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(result);
    }
}
