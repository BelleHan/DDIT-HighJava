package kr.or.ddit.basic;

public class ThreadTest08 {

	public static void main(String[] args) {
		Thread th1 = new UpperThread();
		Thread th2 = new LowerThread();
		
		th1.setPriority(9);
		th2.setPriority(4);
		
		System.out.println("th1�� �켱 ����: " + th1.getPriority());
		System.out.println("th2�� �켱 ����: " + th2.getPriority());
		
		th1.start();
		th2.start();
		
	}
}

// �빮�ڸ� ����ϴ� ������
class UpperThread extends Thread{
	@Override
	public void run() {
		for(char c='A'; c<='Z'; c++) {
			System.out.println(c);
			
			// �ð� ������ �ݺ��� - �ڱ� ������ �� �� �ð���ŭ ��ȸ���� �Ѵ�.(�����δ� �ƹ��͵� ���� ����)
			for(long i=1L; i<=5_000_000_000L; i++) {}
		}
	}	
}

// �ҹ��ڸ� ����ϴ� ������
class LowerThread extends Thread{
	@Override
	public void run() {
		for(char c='a'; c<='z'; c++) {
			System.out.println(c);
			
			// �ð� ������ �ݺ���
			for(long i=1L; i<=5_000_000_000L; i++) {}
		}
	}	
}