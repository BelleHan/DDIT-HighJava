package kr.or.ddit.basic;

/*
 	Threadt�� stop()�޼��带 ȣ���ϸ� �����尡 �ٷ� �����.
 	�� �� ����ϴ� �ڿ��� �������� ���ϰ� ���α׷��� ����Ǿ�
 	���߿� ����Ǵ� ���α׷��� ������ �� �� �ִ�.
 	�׷��� stop()�޼���� ����õ���� �Ǿ� �ִ�.
 */
// �����带 ���߰� �ϴ� ������ ���α׷�
public class ThreadTest14 {

	public static void main(String[] args) {
//		StopThreadTest1 th1 = new StopThreadTest1();
//		th1.start();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException  e) {
//			// TODO: handle exception
//		}
//		
//		//th1.stop();  // ����õ �޼���� �޼���� ���� �׷��� ���·� �����ش�.
//		th1.setStop(true);
		
		//---------------------------------------------------
		
		//interrupt()�޼��带 �̿��� ������ ���߱�
		StopThreadTest2 th2 = new StopThreadTest2();
		th2.start();
		try {		
				Thread.sleep(1000);  
				
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		th2.interrupt();
		
	}
	
}


// �����带 ���߰� �ϴ� ������ ������
class StopThreadTest1 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop) {
			System.out.println("������ ���� ��..........");
		}
		
		System.out.println("�ڿ� ����....");
		System.out.println("������ ����....");
	}	
}

// interrupt()�޼��带 �̿��ؼ� �����带 ���߰� �ϴ� ������
class StopThreadTest2 extends Thread{
	@Override
	public void run() {
		// ���1 ==> interrupt()�޼���� sleep()�޼��带 �̿��ϴ� ���
//		try {
//			while(true) {		
//				System.out.println("interrupt() - ������...........");
//				Thread.sleep(1);  // �Ͻ� ���� ���¿��� interrupt()�޼��尡 ����Ǹ�
//								  // InterruptedException�� �߻��Ѵ�.
//			}
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//		}
//		
		// ���2
		while(true) {
			System.out.println("���2 - interrupt() - ������................");
			
			// interrupt()�޼��尡 ȣ��Ǿ����� ���θ� �˻��Ѵ�.
			// �˻� ���1) Thread�� �ν��Ͻ� �޼����� isInterrupted()�޼��� �̿��ϱ�
			// 			==> isInterrupted()�޼���� interrupt()�޼��尡 ȣ��Ǹ�
			//			    true���� ��ȯ�Ѵ�.
//			if(this.isInterrupted()) {
//				break;
//			}
			
			// �˻���2) Thread�� ����(static) �޼����� interrupted()�޼��� �̿��ϱ�
			//			==> interrupt()�޼��尡 ȣ��Ǹ� true ��ȯ�Ѵ�.
			if(Thread.interrupted()) {
				break;
			}
		}
		
		
		System.out.println("interrupt() - �ڿ� ����....");
		System.out.println("interrupt() - ������ ����....");
		
	}
	
}