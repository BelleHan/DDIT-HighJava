package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ������ ������� ������� ó���ϴ� ����
// (Lock��ü�� �̿��� ����ȭ ó�� ����)

public class ThreadTest18 {	
		private int balance;  // �ܾ��� ����� ����
	
	// Lock��ü ���� ==> private final�� �����.
		private final Lock lock = new ReentrantLock();
		
		public int getBalance() {
			return balance;
		}
		
		public void setBalance(int balance) {
			this.balance = balance;
		}
	
		// �Ա��� ó���ϴ� �޼���
		public void deposit(int money) {
			// Lock��ü�� lock()�޼���� ���� �����ϰ� 
			// �ݵ�� unLock()�޼���� ���� ������ �־�� �Ѵ�.
			
			lock.lock();  // �� ���� ����....
			balance += money;
			lock.unlock();  // �� ����
		}
		
		// ����� ó���ϴ� �޼��� (��ȯ�� : ��ݼ���(true), ��ݽ���(false))
		public boolean withdraw(int money) {
			/*
			lock.lock();
			
			boolean chk = false;
			if(balance >= money) {  // �ܾ��� ��ݾ� �̻����� ���� �˻�
				for(int i=1; i<=100000000; i++) {}  // �ð� ������
				balance -= money;
				System.out.println("�޼��� �ȿ��� balance = " + getBalance());
				chk = true;
			}else {
				chk = false;
			}	
				
			lock.unlock();
			return chk;
			*/
			
			// try ~ catch���� ���Ǵ� �κп��� unlock()�޼��带 ȣ���� ����
			// finally�������� ȣ���ϵ��� �Ѵ�. try �������� unlock()�� ȣ���ϸ� 
			// exception�� �߻��Ǿ��� �� unlock()�� ������� �ʰ� �Ѿ ������ ������
			
			lock.lock();
			
			boolean chk = false;
			
			try {
				if(balance >= money) {  // �ܾ��� ��ݾ� �̻����� ���� �˻�
					for(int i=1; i<=100000000; i++) {}  // �ð� ������
					balance -= money;
					System.out.println("�޼��� �ȿ��� balance = " + getBalance());
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
		acount.setBalance(10000);	// �ܾ��� 10000������ ����
		
		// �͸� ����ü�� �����带 �����ϱ�
		Runnable test = new Runnable() {
			public void run() {
				boolean result = acount.withdraw(6000);  // 6000�� ����ϱ�
				System.out.println("�����忡�� result = " + result + 
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
