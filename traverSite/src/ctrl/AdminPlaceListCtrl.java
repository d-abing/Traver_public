package ctrl;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/adminPlaceList")
public class AdminPlaceListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPlaceListCtrl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// ��� ��� �Խ��� �۸���� �����ϱ� ���� ArrayList�� �ȿ� ����� �����ʹ� placeInfo�� �ν��Ͻ��� ����Ѵ�.
		
		int cpage = 1, psize = 10, bsize = 4, rcnt = 0, pcnt = 0, spage = 0;
		// ���� ������ ��ȣ, ������ ũ��, ��� ũ��, ���ڵ�(�Խñ�) ����, ������ ����, ���������� ���� ������ ������
		
		if (request.getParameter("cpage") != null)
            cpage = Integer.parseInt(request.getParameter("cpage"));
        // cpage ���� ������ �޾Ƽ� int������ ����ȯ ��Ŵ(���Ȼ��� ������ ��������� �ؾ� �ϱ� ����)
		
		String schtype = request.getParameter("schtype"); // �˻�����(��Ҹ�, ��ȣ, �з�, �����, �ּ�)
        String keyword = request.getParameter("keyword");   // �˻���
        String where = "";      // �˻� ������ ���� ��� where���� ������ ����
        if (schtype == null || keyword == null) {
            schtype = "";   keyword = "";
        } else if (!schtype.equals("") && !keyword.equals("")) {    // �˻����ǰ� �˻�� ���� ���
            URLEncoder.encode(keyword, "UTF-8");
            // ������Ʈ������ �ְ� �޴� �˻�� �ѱ��� ��� IE���� ��Ȥ ������ �߻��� �� �����Ƿ� �����ڵ�� ��ȯ��Ŵ
            where = " where pi_" + schtype + " like '%" + keyword + "%' ";
        }

        AdminPlaceListSvc adminPlaceListSvc = new AdminPlaceListSvc();
        ArrayList<PlaceInfo> placeInfo = adminPlaceListSvc.getAdminPlaceList(where, cpage, psize);

        rcnt = adminPlaceListSvc.getPlaceListCount(where);
        // �˻��� �Խñ��� �� ������ �Խñ� �Ϸù�ȣ ��°� ��ü �������� ����� ���� �ʿ��� ��

		AdminPlaceInfo adminPlaceList = new AdminPlaceInfo();
		adminPlaceList.setBsize(bsize);       adminPlaceList.setCpage(cpage);
		adminPlaceList.setPcnt(pcnt);         adminPlaceList.setPsize(psize);
		adminPlaceList.setRcnt(rcnt);         adminPlaceList.setSpage(spage);
		adminPlaceList.setSchtype(schtype);   adminPlaceList.setKeyword(keyword);

		request.setAttribute("adminPlaceList", adminPlaceList);
		request.setAttribute("placeInfo", placeInfo);

        RequestDispatcher dispatcher = request.getRequestDispatcher("lmth/admin/02_place/place_list.jsp");
        dispatcher.forward(request, response);
	}
}
