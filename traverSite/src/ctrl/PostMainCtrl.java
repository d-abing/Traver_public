package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;
import java.util.*;

@WebServlet("/postMain")
public class PostMainCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PostMainCtrl() { super(); }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        
        HttpSession session = request.getSession();
        
        String mbti = "";
        if ( session.getAttribute("loginInfo") != null ) {
            MemberInfo mi = (MemberInfo)session.getAttribute("loginInfo");
            mbti = mi.getMi_mbti();
        } else mbti = "ISTJ";
        
        PostMainSvc postMainSvc = new PostMainSvc();
        
        // MBTI ���ƿ� ���� ���� �Խñ� �ҷ����� 
        ArrayList<GoodPost> mbtiPostList = postMainSvc.getMBTIPostList(mbti);
        request.setAttribute("mbtiPostList", mbtiPostList);
        
        // MBTI ���� �α� �Խñ� �ҷ�����
        ArrayList<GoodPost> popPostList = postMainSvc.getPopPostList();
        request.setAttribute("popPostList", popPostList);
        
        RequestDispatcher dispatcher = 
                request.getRequestDispatcher("lmth/mbti/mbti_main.jsp");
            dispatcher.forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
}