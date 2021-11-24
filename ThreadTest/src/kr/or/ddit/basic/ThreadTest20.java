package kr.or.ddit.basic;

/*
 	wait(), notify()�޼��带 �̿��� �� �����忡��
 	������ �ѹ��� �����ϴ� ����
 	
 	wait(), notify(), notifyALl()�� ����ȭ ���������� ��� �����ϴ�.
 */
public class ThreadTest20 {

	public static void main(String[] args) {
		WorkObject work = new WorkObject();
		
		ThreadA th1 = new ThreadA(work);
		ThreadB th2 = new ThreadB(work);
		
		th1.start();
		th2.start();
		
	}
}

// �������� ����� ��ü
class WorkObject{
	public synchronized void methodA() {
		System.out.println("methodA()�޼��� ���� ��...");
		
		notify();
		try {
			wait();
		} catch (InterruptedException e) {  }
		
	}
	
	public synchronized void methodB() {
		System.out.println("methodB()�޼��� ���� ��...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {  }
	}
}

// WorkObject�� methodA()�޼��常 ȣ���ϴ� ������
class ThreadA extends Thread{
	private WorkObject work;
	
	public ThreadA(WorkObject work) {
		this.work = work;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			work.methodA();
		}
		// �������� waiting pool�� �ִ� �����带 �����ش�.
		synchronized (work) {
			work.notify();
		}
	}
}

//WorkObject�� methodB()�޼��常 ȣ���ϴ� ������
class ThreadB extends Thread{
	private WorkObject work;
	
	public ThreadB(WorkObject work) {
		this.work = work;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			work.methodB();
		}
		// �������� waiting pool�� �ִ� �����带 �����ش�.
		synchronized (work) {
			work.notify();
		}
	}
}