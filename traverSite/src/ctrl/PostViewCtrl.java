package ctrl;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/postView")
public class PostViewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PostViewCtrl() { super(); }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String gpid = request.getParameter("gpid");
        String giid = request.getParameter("giid");
        String miid = "";
        boolean isGood = false;
        boolean isInterest = false;
        
        PostViewSvc postViewSvc = new PostViewSvc();
        if ( request.getParameter("miid") != null ) { 
            miid = request.getParameter("miid");
            isGood = postViewSvc.isGood(gpid,miid); // ���ƿ� �������� ���� Ȯ��
            isInterest = postViewSvc.isInterest(giid,miid); // ���ɵ�� �ߴ��� ���� Ȯ��
            if (  request.getParameter("kind") != null ) {
                if ( request.getParameter("kind").equals("good") ) { // �����̳� ����Ʈ����  ȣ���� ���� �ƴ϶� ���ƿ� ��ư�� ������ ���ε� �ϴ� ���
                    postViewSvc.gcntUpdate(gpid, miid); // ���ƿ� �� ����
                } else if ( request.getParameter("kind").equals("interest") ) { // �����̳� ����Ʈ����  ȣ���� ���� �ƴ϶� ���ɵ�� ��ư�� ������ ���ε� �ϴ� ���
                    postViewSvc.goodUpdate(giid, miid); // ���������� �߰�
                }
            }
        }
        
        GoodInfo goodInfo = postViewSvc.getGoodInfo(giid);
        ArrayList <GoodDay> goodDayList = postViewSvc.getGoodDayList(giid);
        
        GoodPost goodPost = postViewSvc.getGoodPost(gpid);
        request.setAttribute("goodPost", goodPost);
        request.setAttribute("isGood", isGood);
        request.setAttribute("isInterest", isInterest);
        request.setAttribute("goodInfo", goodInfo);
        request.setAttribute("goodDayList", goodDayList);
        
        RequestDispatcher dispatcher = 
                request.getRequestDispatcher("lmth/mbti/mbti_view.jsp");
            dispatcher.forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
}