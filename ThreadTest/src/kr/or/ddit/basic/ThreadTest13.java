package kr.or.ddit.basic;

// yield()메서드 연습
public class ThreadTest13 {

	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번쓰레드");
		YieldThread th2 = new YieldThread("2번쓰레드");
		
		th1.start();
		th2.start();
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("1111111 -------------------------------");
		
		th1.work = false;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("2222222 -------------------------------");
		
		th1.work = true;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("3333333 -------------------------------");
		
		th1.stop = true;
		th2.stop = true;
		
	}
	
}


// yield()메서드 연습용 쓰레드
class YieldThread extends Thread{
	public boolean stop = false;	// 쓰레드의 종료 여부를 결정하는 변수
	public boolean work = true;		// 쓰레드가 작동 중 특정 일처리 여부를 결정하는 변수
	
	// 생성자
	public YieldThread(String name) {
		super(name);		// 쓰레드의 이름 설정
	}
	
	@Override
	public void run() {
		while(!stop) {	// stop값이 true가 되면 반복문이 종료된다.
			if(work) {
				System.out.println(getName() + "- 작업중...");
			}else {
				System.out.println(getName() + "- 양보...");
				Thread.yield();  // 다른 쓰레드에 양보해서 효율성을 높여준다. 
				// 작업을 하는데 쓰레드에서 처리하는 내용이 공회전을 할 확률이 있을 때 공회전하는 타이밍에 양보를 할 수 있도록
				// 양보를 안 할 때보다 공회전의 횟수를 많이 줄일 수 있다.
			}
		}
	}
	
}