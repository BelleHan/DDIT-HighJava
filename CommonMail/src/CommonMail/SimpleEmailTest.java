package CommonMail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SimpleEmailTest {
	public static void main(String[] args) throws EmailException {
		
		SimpleEmail email = new SimpleEmail();
	    email.setCharset("euc-kr");   // �ѱ��� ������ �ʵ��� ���� ���� 
	    email.setHostName("smtp.naver.com");  // SMTP ������ ����
	    email.setAuthentication("heesu1028", "Mudojj0423");  // SMTP ���� ó��
	    email.addTo("heesu10258@gmail.com", "�����"); // �����ڸ� �߰�
	    email.setFrom("heesu1028@naver.com", "���"); // ������ ��� ����
	    email.setSubject("�ؽ�Ʈ �׽�Ʈ �����Դϴ�."); // ���� ����
	    email.setContent("�׽�Ʈ ������ �����Դϴ�.", "text/plain; charset=euc-kr"); // ���� ����
	    email.send(); // ���� �߼�
	    
	    System.out.println("���� �Ϸ�");
	    
	}
}
