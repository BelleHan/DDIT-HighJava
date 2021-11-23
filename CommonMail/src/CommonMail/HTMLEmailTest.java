package CommonMail;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class HTMLEmailTest {

	public static void main(String[] args) throws EmailException, MalformedURLException {
		HtmlEmail email = new HtmlEmail();
		email.setCharset("euc-kr");    // 한글이 깨지지 않도록 형식 지정 
	    email.setHostName("smtp.naver.com");  // SMTP 서버를 지정
	    email.setAuthentication("heesu1028", "Mudojj0423"); // SMTP 인증 처리
	    email.addTo("heesu10258@gmail.com", "한희수"); // 수신자를 추가
	    email.setFrom("heesu1028@naver.com", "희수"); // 보내는 사람 지정
		email.setSubject("HTML 테스트 메일입니다."); // 제목 지정
		
		// 이미지 첨부하고 경로 지정하기
		URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif"); // url 객체 생성
		String cid = email.embed(url, "Apache logo"); // contentID 생성
		
		// html 메세지 지정하기
		email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");

		// 대체 메세지 지정하기
		email.setTextMsg("Your email client does not support HTML messages");
		
		email.send();  // 이메일 발송
		System.out.println("전송 완료");

	}
}
