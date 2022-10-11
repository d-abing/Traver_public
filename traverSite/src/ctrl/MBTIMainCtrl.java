package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;



@WebServlet("/MBTIMain")
public class MBTIMainCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MBTIMainCtrl() { super(); }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	    HttpSession session = request.getSession();
	    
	    if(session.getAttribute("loginInfo")!=null) {  // �α��� �Ǿ� ������
    	    MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
    	    String mimbti = loginInfo.getMi_mbti();
    	    System.out.println(mimbti);
    	    if ( mimbti != null && !mimbti.equals("") ) { // mbti���� ������
    	        RequestDispatcher dispatcher = 
                        request.getRequestDispatcher("lmth/mbti/mbti_main.jsp");
                    dispatcher.forward(request, response);
    	    } else {
    	        RequestDispatcher dispatcher = 
                        request.getRequestDispatcher("lmth/mbti/mbti_start_main.jsp");
                dispatcher.forward(request, response);
    	    }
	    } else { // �α��� �Ǿ� ���� ������
	        RequestDispatcher dispatcher = 
                    request.getRequestDispatcher("lmth/mbti/mbti_start_main.jsp");
            dispatcher.forward(request, response);
	    }
                
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

}