package CommonMail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SimpleEmailTest {
	public static void main(String[] args) throws EmailException {
		
		SimpleEmail email = new SimpleEmail();
	    email.setCharset("euc-kr");   // 한글이 깨지지 않도록 형식 지정 
	    email.setHostName("smtp.naver.com");  // SMTP 서버를 지정
	    email.setAuthentication("heesu1028", "Mudojj0423");  // SMTP 인증 처리
	    email.addTo("heesu10258@gmail.com", "한희수"); // 수신자를 추가
	    email.setFrom("heesu1028@naver.com", "희수"); // 보내는 사람 지정
	    email.setSubject("텍스트 테스트 메일입니다."); // 메일 제목
	    email.setContent("테스트 메일의 내용입니다.", "text/plain; charset=euc-kr"); // 내용 지정
	    email.send(); // 메일 발송
	    
	    System.out.println("전송 완료");
	    
	}
}
