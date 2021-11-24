package kr.or.ddit.basic;

// �������� ���¸� ����ϴ� ���α׷�
public class ThreadTest12 {

	public static void main(String[] args) {
		StatePrintThread th = 
				new StatePrintThread(new TargetThread());
		th.start();
	}
}

// ������ ������ �˻� ����� �Ǵ� ������
class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i=1L; i<=20_000_000_000L; i++) {}	// �ð� ������
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		for(long i=1L; i<=20_000_000_000L; i++) {}	// �ð� ������
	}
	
}

// TargetThread�� ���¸� ����ϴ� ������
class StatePrintThread extends Thread{
	private TargetThread target;

	// ������
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true) {
			// �������� ���� ���� ���ϱ�
			Thread.State state = target.getState();
			System.out.println("TargetThread�� ���� ���°�: " + state);
			
			if(state == Thread.State.NEW) {	// �������� ���°� NEW�����̸�...
				target.start();  // TargetThread�� �����Ѵ�.				
			}
			
			if(state == Thread.State.TERMINATED) {  // �������� ���°� ���� �����̸�...
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}		
	}	
}