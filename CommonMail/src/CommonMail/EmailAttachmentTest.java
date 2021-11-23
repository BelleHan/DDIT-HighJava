package CommonMail;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class EmailAttachmentTest {

	public static void main(String[] args) throws EmailException {
		// �̸��� ÷�ι� ���� �����ϱ�
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("d:/d_other/���.jpg"); // ÷�ι� ��� ����
		attachment.setDisposition(EmailAttachment.ATTACHMENT);  // ������ ���� ����
		attachment.setDescription("���.jpg"); // ÷�ι� ���� ����
		attachment.setName("���.jpg"); // ������ �̸��� ����

		MultiPartEmail email = new MultiPartEmail();
		email.setCharset("euc-kr");    // �ѱ��� ������ �ʵ��� ���� ���� 
	    email.setHostName("smtp.naver.com");  // SMTP ������ ����
	    email.setAuthentication("heesu1028", "Mudojj0423");  // SMTP ���� ó��
	    email.addTo("heesu10258@gmail.com", "�����"); // �����ڸ� �߰�
	    email.setFrom("heesu1028@naver.com", "���"); // ������ ��� ����
	    email.setSubject("÷�� ���� �׽�Ʈ �����Դϴ�."); // ���� ����
		email.setMsg("�̰� �����Դϴ�."); // ���� ����

		email.attach(attachment);  // ÷�ι� ÷��
		email.send();  // �̸��� ����
		System.out.println("���� �Ϸ�");
		
	}
}
