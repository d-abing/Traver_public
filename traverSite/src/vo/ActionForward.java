package vo;

public class ActionForward {
// ��û�� ���� ó���� ���� �� View �������� �̵��� �� �̵��ϴ� ���(�����̷�Ʈ, ����ġ)�� �����ϴ� Ŭ����
	private String path;		// �̵��� View �������� url�� ������ ����
	private boolean redirect;	// �̵����(true : �����̷�Ʈ, false : ����ġ) ���� ����
	// boolean �����̹Ƿ� ���� �������� ������ �⺻������ false�� ���� '����ġ' ������� �̵��ϰ� ��
	
	public ActionForward() {}
	public ActionForward(String path, boolean redirect) {
		this.path = path;	this.redirect = redirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
}
