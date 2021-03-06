package kr.or.ddit.basic;

public class ThreadTest08 {

	public static void main(String[] args) {
		Thread th1 = new UpperThread();
		Thread th2 = new LowerThread();
		
		th1.setPriority(9);
		th2.setPriority(4);
		
		System.out.println("th1의 우선 순위: " + th1.getPriority());
		System.out.println("th2의 우선 순위: " + th2.getPriority());
		
		th1.start();
		th2.start();
		
	}
}

// 대문자를 출력하는 쓰레드
class UpperThread extends Thread{
	@Override
	public void run() {
		for(char c='A'; c<='Z'; c++) {
			System.out.println(c);
			
			// 시간 지연용 반복문 - 자기 차례일 때 이 시간만큼 공회전을 한다.(실제로는 아무것도 하지 않음)
			for(long i=1L; i<=5_000_000_000L; i++) {}
		}
	}	
}

// 소문자를 출력하는 쓰레드
class LowerThread extends Thread{
	@Override
	public void run() {
		for(char c='a'; c<='z'; c++) {
			System.out.println(c);
			
			// 시간 지연용 반복문
			for(long i=1L; i<=5_000_000_000L; i++) {}
		}
	}	
}