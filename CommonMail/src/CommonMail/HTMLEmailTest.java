package CommonMail;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class HTMLEmailTest {

	public static void main(String[] args) throws EmailException, MalformedURLException {
		HtmlEmail email = new HtmlEmail();
		email.setCharset("euc-kr");    // �ѱ��� ������ �ʵ��� ���� ���� 
	    email.setHostName("smtp.naver.com");  // SMTP ������ ����
	    email.setAuthentication("heesu1028", "Mudojj0423"); // SMTP ���� ó��
	    email.addTo("heesu10258@gmail.com", "�����"); // �����ڸ� �߰�
	    email.setFrom("heesu1028@naver.com", "���"); // ������ ��� ����
		email.setSubject("HTML �׽�Ʈ �����Դϴ�."); // ���� ����
		
		// �̹��� ÷���ϰ� ��� �����ϱ�
		URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif"); // url ��ü ����
		String cid = email.embed(url, "Apache logo"); // contentID ����
		
		// html �޼��� �����ϱ�
		email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");

		// ��ü �޼��� �����ϱ�
		email.setTextMsg("Your email client does not support HTML messages");
		
		email.send();  // �̸��� �߼�
		System.out.println("���� �Ϸ�");

	}
}
