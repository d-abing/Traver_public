package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/placeList")
public class PlaceListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PlaceListCtrl() { super(); }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
		
		String placeCategory = request.getParameter("placeCategory"); // ���� ���̵�ڽ��� ī�װ� ���ÿ��� ����
		String viewOption = request.getParameter("viewOption"); // '�߰��� ���' '�������'�� ��� [�ɼǹڽ�] value ����
		String searchKeyword = request.getParameter("searchKeyword"); // '�˻���' ����
		String where = " where pi_isview = 'y' "; // �ɼ� ���� �ѱ� where ���� ������ ����
		
		if (viewOption == null || viewOption.equals("")) {	// ���� �ɼǹڽ��� ��͵� �������� �ʾ����� �⺻���� 1
			viewOption = "1";
		}
		if (placeCategory == null || placeCategory.equals("")) {// ������ ī�װ��� ���� ������ �⺻���� 0
			placeCategory = "0";
		}
		if (searchKeyword == null || searchKeyword.equals("")) {
			searchKeyword = "";
		}

		// view �ɼ� ���� where �߰� switch��
		switch (viewOption) {
		case "1" :  // ���� ��ü���⸦ �����Ѵٸ� ī�װ��� ��������
			break;
		case "2" : //'�߰��� ��� ����'�� ����������
			// request�� �߰��� ��� String ���� ������ .split(',');
			// for�� �����鼭  pi_id = idx�� or�� ���� where�� ����
			break;
		case "3" : //'���� ��� ����'�� ����������
			HttpSession session = request.getSession();
			MemberInfo mi = new MemberInfo();
			String miid = mi.getMi_id();
			
			String where2 = " where mi_id = " + miid;
			// ��� idx�� �̾ƿ� ����Ʈ���� ������ �޼ҵ�� (miid)
			// for�� �����鼭 pi_idx = idx�� or�� ���� where�� ����
			break;
		case "4" : // '���� ��� ����' and '�߰��� ���'�� ����������
			// ���� �ΰ� ��ġ��
			break;
		}// �ɼ��� �������� �ʾ����� ������ �߰����� �ʴ´� (���� �����ֱ�)

		
		
		// ī�װ� ���� where �߰� switch��
		switch (placeCategory) {
		case "1" : // '����'ī�װ��� ����������
			where += " and pi_ctgr = '1' "; 	break;
		case "2" : // '������' ī�װ��� ����������
			where += " and pi_ctgr = '2' ";		break;
		case "3" : // '������' ī�װ��� ����������
			where += " and pi_ctgr = '3' "; 	break;
		} // ī�װ��� �������� �ʾ����� ������ �߰����� �ʴ´�. (���� �����ֱ�)
		
		
		
		// searchKeyword ���� where �߰� if��
		if (!searchKeyword.equals("")) { // �˻�� ������
			where += " and pi_name like '%" + searchKeyword + "%' ";
		}
		

		PlaceListSvc placeListSvc = new PlaceListSvc();
		ArrayList<PlaceInfo> placeList = placeListSvc.getPlaceList(where);
		
		request.setAttribute("placeList", placeList);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(placeList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("lmth/gotraver/map_main.jsp");
		dispatcher.forward(request,	response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}

