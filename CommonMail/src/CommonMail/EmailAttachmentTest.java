package CommonMail;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class EmailAttachmentTest {

	public static void main(String[] args) throws EmailException {
		// 이메일 첨부물 정보 생성하기
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("d:/d_other/펭귄.jpg"); // 첨부물 경로 지정
		attachment.setDisposition(EmailAttachment.ATTACHMENT);  // 파일의 형태 지정
		attachment.setDescription("펭귄.jpg"); // 첨부물 설명 지정
		attachment.setName("펭귄.jpg"); // 파일의 이름을 지정

		MultiPartEmail email = new MultiPartEmail();
		email.setCharset("euc-kr");    // 한글이 깨지지 않도록 형식 지정 
	    email.setHostName("smtp.naver.com");  // SMTP 서버를 지정
	    email.setAuthentication("heesu1028", "Mudojj0423");  // SMTP 인증 처리
	    email.addTo("heesu10258@gmail.com", "한희수"); // 수신자를 추가
	    email.setFrom("heesu1028@naver.com", "희수"); // 보내는 사람 지정
	    email.setSubject("첨부 파일 테스트 메일입니다."); // 제목 지정
		email.setMsg("이건 내용입니다."); // 내용 지정

		email.attach(attachment);  // 첨부물 첨부
		email.send();  // 이메일 전송
		System.out.println("전송 완료");
		
	}
}
