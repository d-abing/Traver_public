package act;

import javax.servlet.http.*;
import vo.*;

public interface Action {
// ���� ��û�� ���� ó���� ������ ���(�޼ҵ�)���� ó���ǰԲ� implements ��ų �������̽�
	ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	// �� ��û�� ó���ϴ� Action Ŭ�������� ���������� �����ؾ� �ϴ� �޼ҵ�μ� ����� �޼ҵ�
}
