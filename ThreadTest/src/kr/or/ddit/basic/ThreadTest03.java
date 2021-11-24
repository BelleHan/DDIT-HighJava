package kr.or.ddit.basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		// �������� ���� �ð��� üũ�� ����.
//		MyRunner r = new MyRunner();
//		Thread th = new Thread(r); - �Ʒ� ����� �����
		
		Thread th = new Thread(new MyRunner());
				
		// 1970��1��1�� 0��0��0��(ǥ�ؽð�)�κ��� ����� �ð��� �и������� ������ ��ȯ�Ѵ�.
		// �и������� ==> 1/1000��
		long startTime = System.currentTimeMillis();
		
		// �ð��� üũ�� ����
		th.start();
		try {
			th.join();   // ���� �������� �����忡�� ����� �Ǵ� ������(������ th)
						 // ����� ������ ��ٸ���.
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("��� �ð�: " + (endTime - startTime));
		
	}
}

class MyRunner implements Runnable{
	@Override
	public void run() {
		long sum = 0L;
		for(long i=1L; i<=1_000_000_000L; i++) {
			sum += i;
		}
		System.out.println("�հ� : " + sum);
	}
}