package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		// ��Ƽ ������ ���α׷�
		
		// ���1
		//  ==> ThreadŬ������ ����� class�� �ν��Ͻ��� ������ ��
		//  	�� �ν��Ͻ��� start()�޼��带 ȣ���ؼ� �����Ѵ�.
		MyThread1 th1 = new MyThread1();
		th1.start();
		
		// ���2
		//  ==> Runnable�������̽��� ������ class�� �ν��Ͻ��� ������ ��
		// 		�� �ν��Ͻ��� Thread�� �ν��Ͻ��� ������ �� �������� ���ڰ����� �Ѱ��ش�.
		//  	�� �� ������ Thread�� �ν��Ͻ��� start()�޼��带 ȣ���ؼ� �����Ѵ�.
		MyThread2 r = new MyThread2();
		Thread th2 = new Thread(r);
		th2.start();
		// ���� �����尡 �� 3�� => ���ξ����� 1�� ���1,2 ������ 2�� (���� ó���ϴ� ���� 2���� ������)
		
		// ���3 ==> �͸���ü�� �̿��ϴ� ��� - Ŭ���� ��� ����ϱ� ���� �������� �ѹ��ۿ� ����� �� ����(Ŭ����ó�� �ٸ� ������ �ҷ��� ����� ������)
		Runnable r2 = new Runnable() {	//�������̽��� ���� = new �������̽���() { }
			public void run() {
				for(int j=1; j<=200; j++) {
					System.out.print("$");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
				}
			}
		};
		
		Thread th3 = new Thread(r2);
		th3.start();
		
		System.out.println("main�޼��� ��...");
		
	}
}


// ���1  ==> ThreadŬ������ ��ӹ��� Ŭ���� �ۼ�
class MyThread1 extends Thread{
	@Override
	public void run() {
		// �� run()�޼��� �ȿ� �����忡�� ó���� ������ ����Ѵ�.
		for(int i=1; i<=200; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(�ð�)�޼���� �־��� �ð����� �۾��� ��� �����.
				// �ð��� �и������� ������ ����Ѵ�. (��, 1000�� 1�ʸ� �ǹ��Ѵ�.)
				
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}

		}		
	}
}

// ���2 ==> Runnable�������̽��� ������ Ŭ���� �ۼ�
class MyThread2 implements Runnable{
	@Override
	public void run() {
		// �� run()�޼��� �ȿ� �����忡�� ó���� ������ ����Ѵ�.
		for(int j=1; j<=200; j++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
	}	
}