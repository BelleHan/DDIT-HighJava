package kr.or.ddit.basic;
/*
 	1 ~ 20������� �հ踦 ���ϴ� ���α׷��� �ϳ��� �����尡 �ܵ����� ó���� ����
 	�������� �����尡 �����ؼ� ó���� ���� ��� �ð��� ���� ����.
 */
public class ThreadTest04 {

	public static void main(String[] args) {
		// �ܵ����� ó���ϴ� ������ ��ü ����
		SumThread sm = new SumThread(1L, 2_000_000_000L);
		
		// ������ �����ؼ� ó���ϴ� ������ ��ü ����
		SumThread[] sums = new SumThread[] {
			new SumThread(            1L,   500_000_000L),
			new SumThread( 500_000_000L, 1_000_000_000L),
			new SumThread(1_000_000_000L, 1_500_000_000L),
			new SumThread(1_500_000_000L, 2_000_000_000L),
		};
		
		// �ܵ����� ó���ϱ�
		long startTime = System.currentTimeMillis();
		sm.start();   // ������ ����
		try {
			sm.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("�ܵ����� ó������ �� ��� �ð�: " + (endTime - startTime));
		
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println();
		
		// ���� �����尡 �����ؼ� ó���ϱ�
		startTime = System.currentTimeMillis();
		
		// ������ �����ϱ�
		for(SumThread ss : sums) {
			ss.start();
		}
		
		// �����ϴ� ��� �����尡 ����� ������ ��ٸ���.
		for(int i=0; i<sums.length; i++) {
			try {
				sums[i].join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("�����ؼ� ó���� ��� �ð�: " + (endTime - startTime));
		
	}
}

// ���۰��� ���ᰪ�� �̿��Ͽ� ���۰��� ���ᰪ ������ �հ踦 ���ϴ� ������ Ŭ������ �ۼ�
class SumThread extends Thread{
	private long min, max;

	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i=min; i<=max; i++) {
			sum += i;
		}
		System.out.println("�հ�(" + min + "~" + max + ") : " + sum);
	}
	
	
}