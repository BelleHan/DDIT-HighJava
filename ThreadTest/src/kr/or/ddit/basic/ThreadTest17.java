package kr.or.ddit.basic;
// ������ ������� ������� ó���ϴ� ����
// (����ȭ ó�� ���� - synchronized�� �̿��� ����ȭ ó��)

public class ThreadTest17 {
	private int balance;  // �ܾ��� ����� ����
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}

	// �Ա��� ó���ϴ� �޼���
	public void deposit(int money) {
		balance += money;
	}
	
	// ����� ó���ϴ� �޼��� (��ȯ�� : ��ݼ���(true), ��ݽ���(false))
	public synchronized boolean withdraw(int money) {
		
		//synchronized (this) {  // ����ȭ ��
			if(balance >= money) {  // �ܾ��� ��ݾ� �̻����� ���� �˻�
				for(int i=1; i<=100000000; i++) {}  // �ð� ������
				balance -= money;
				System.out.println("�޼��� �ȿ��� balance = " + getBalance());
				return true;
			}else {
				return false;
			}		
		//}
		
	}
	
	
	public static void main(String[] args) {
		// ������� ó���ϴ� ��ü ����
		ThreadTest17 acount = new ThreadTest17();
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
