package kr.or.ddit.basic;

// yield()�޼��� ����
public class ThreadTest13 {

	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1��������");
		YieldThread th2 = new YieldThread("2��������");
		
		th1.start();
		th2.start();
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("1111111 -------------------------------");
		
		th1.work = false;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("2222222 -------------------------------");
		
		th1.work = true;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("3333333 -------------------------------");
		
		th1.stop = true;
		th2.stop = true;
		
	}
	
}


// yield()�޼��� ������ ������
class YieldThread extends Thread{
	public boolean stop = false;	// �������� ���� ���θ� �����ϴ� ����
	public boolean work = true;		// �����尡 �۵� �� Ư�� ��ó�� ���θ� �����ϴ� ����
	
	// ������
	public YieldThread(String name) {
		super(name);		// �������� �̸� ����
	}
	
	@Override
	public void run() {
		while(!stop) {	// stop���� true�� �Ǹ� �ݺ����� ����ȴ�.
			if(work) {
				System.out.println(getName() + "- �۾���...");
			}else {
				System.out.println(getName() + "- �纸...");
				Thread.yield();  // �ٸ� �����忡 �纸�ؼ� ȿ������ �����ش�. 
				// �۾��� �ϴµ� �����忡�� ó���ϴ� ������ ��ȸ���� �� Ȯ���� ���� �� ��ȸ���ϴ� Ÿ�ֿ̹� �纸�� �� �� �ֵ���
				// �纸�� �� �� ������ ��ȸ���� Ƚ���� ���� ���� �� �ִ�.
			}
		}
	}
	
}