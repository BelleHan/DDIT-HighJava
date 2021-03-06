package kr.or.ddit.basic;

// 데몬 쓰레드 연습  ==> 자동저장 기능 구현하기
public class ThreadTest09 {

	public static void main(String[] args) {
		AutoSaveThread auto = new AutoSaveThread();
		
		// 데몬 쓰레드로 설정하기  ==> 반드시 start()메서드를 호출하기 전에 설정한다.
		auto.setDaemon(true); // 주석처리하고 일반쓰레드로 바꾸면 메인메써드가 종료되도 쓰레드가 종료되지 않아서 자동저장 메세지가 계속 출력
		
		auto.start();
		
		try {
			for(int i=1; i<=20; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println("main 쓰레드 종료...");
		
	}
}

// 자동 저장하는 쓰레드
class AutoSaveThread extends Thread{
	// 작업 내용을 저장하는 메서드
	private void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	
	@Override
	public void run() {
		while(true) {		
			try {
				Thread.sleep(3000);
				save();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	
}