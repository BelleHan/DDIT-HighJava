package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 은행의 입출금을 쓰레드로 처리하는 예제
// (Lock객체를 이용한 동기화 처리 예제)

public class ThreadTest18 {	
		private int balance;  // 잔액이 저장될 변수
	
	// Lock객체 생성 ==> private final로 만든다.
		private final Lock lock = new ReentrantLock();
		
		public int getBalance() {
			return balance;
		}
		
		public void setBalance(int balance) {
			this.balance = balance;
		}
	
		// 입금을 처리하는 메서드
		public void deposit(int money) {
			// Lock객체는 lock()메서드로 락을 설정하고 
			// 반드시 unLock()메서드로 락을 해제해 주어야 한다.
			
			lock.lock();  // 락 설정 시작....
			balance += money;
			lock.unlock();  // 락 해제
		}
		
		// 출금을 처리하는 메서드 (반환값 : 출금성공(true), 출금실패(false))
		public boolean withdraw(int money) {
			/*
			lock.lock();
			
			boolean chk = false;
			if(balance >= money) {  // 잔액이 출금액 이상인지 여부 검사
				for(int i=1; i<=100000000; i++) {}  // 시간 지연용
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				chk = true;
			}else {
				chk = false;
			}	
				
			lock.unlock();
			return chk;
			*/
			
			// try ~ catch블럭이 사용되는 부분에서 unlock()메서드를 호출할 때는
			// finally영역에서 호출하도록 한다. try 영역에서 unlock()을 호출하면 
			// exception이 발생되었을 때 unlock()이 실행되지 않고 넘어가 버리기 때문에
			
			lock.lock();
			
			boolean chk = false;
			
			try {
				if(balance >= money) {  // 잔액이 출금액 이상인지 여부 검사
					for(int i=1; i<=100000000; i++) {}  // 시간 지연용
					balance -= money;
					System.out.println("메서드 안에서 balance = " + getBalance());
					chk = true;
				}else {
					chk = false;
				}	
								
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
			
			return chk;
			
	}
	
	public static void main(String[] args) {
		ThreadTest18 acount = new ThreadTest18();
		acount.setBalance(10000);	// 잔액을 10000원으로 설정
		
		// 익명 구현체로 쓰레드를 구성하기
		Runnable test = new Runnable() {
			public void run() {
				boolean result = acount.withdraw(6000);  // 6000원 출금하기
				System.out.println("쓰레드에서 result = " + result + 
						", balance = " + acount.getBalance());
			}
		};
		// ----------------------------

		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);
		
		th1.start();
		th2.start();
				
	}
	
}
