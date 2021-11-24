package kr.or.ddit.basic;

// ���� ������ ����  ==> �ڵ����� ��� �����ϱ�
public class ThreadTest09 {

	public static void main(String[] args) {
		AutoSaveThread auto = new AutoSaveThread();
		
		// ���� ������� �����ϱ�  ==> �ݵ�� start()�޼��带 ȣ���ϱ� ���� �����Ѵ�.
		auto.setDaemon(true); // �ּ�ó���ϰ� �Ϲݾ������ �ٲٸ� ���θ޽�尡 ����ǵ� �����尡 ������� �ʾƼ� �ڵ����� �޼����� ��� ���
		
		auto.start();
		
		try {
			for(int i=1; i<=20; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println("main ������ ����...");
		
	}
}

// �ڵ� �����ϴ� ������
class AutoSaveThread extends Thread{
	// �۾� ������ �����ϴ� �޼���
	private void save() {
		System.out.println("�۾� ������ �����մϴ�...");
	}
	
	@Override
	public void run() {
		while(true) {		
			try {
				Thread.sleep(3000);
				save();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	
}