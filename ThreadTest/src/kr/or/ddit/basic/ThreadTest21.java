package kr.or.ddit.basic;

public class ThreadTest21 {

	public static void main(String[] args) {
		DataBox box = new DataBox();
		
		ProducerThread th1 = new ProducerThread(box);
		ConsumerThread th2 = new ConsumerThread(box);
		
		th1.start();
		th2.start();
		
	}
}

// �����͸� �������� ����ϴ� Ŭ����
class DataBox{
	private String data;
	
	// �����͸� �����ϴ� �޼���
	// ==> data������ ���� ������ data�� null�� �� ������ ��ٸ���.
	// ==> data�� null�� �Ǹ� ���ο� �����͸� data������ �����Ѵ�.
	public synchronized void setData(String data) {
		if(this.data!=null) {  // ���� ������...
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		} // if�� ��...
		
		this.data = data;  // ���ο� ������ ����
		System.out.println("Thread���� ���� ������ ������: " + data);
		notify();
		
	}
	
	// �����͸� ����ϴ� �޼���
	// ==> data������ ���� null�̸� data������ ���ο� �����Ͱ� ����� ������ ��ٸ���.
	// ==> data������ ���� ������ �ش� �����͸� ��ȯ�Ѵ�.
	// 	    ��ȯ �Ŀ��� data��������  null�� �����.
	public synchronized String getData() {
		if(data==null) {
			try {
				wait();
			} catch (InterruptedException e) {  }
		} // if�� ��...
		
		String returnData = data;
		System.out.println("�����尡 ���� ������: " + data);
		data = null;
		notify();
		
		return returnData;
		
	}
	
}

// �����͸� �־��ִ� ������
class ProducerThread extends Thread{
	private DataBox box; // Ŭ���������� ������ �����ϴ� ���� ��𿡼��� �� �� ������ �����ڸ� ���� ������ ��ü�� �ʱ�ȭ �������� ������ 
						 //�� �����⿡ �Ұ��� NullPointerException�� �߻��Ѵ�.
	
	public ProducerThread(DataBox box) {
		this.box = box;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=3; i++) {
			box.setData("���޵�����-" + i);
		}
	}
	
}

// �����͸� ������ ����ϴ� ������
class ConsumerThread extends Thread{
	private DataBox box;
	
	public ConsumerThread(DataBox box) {
		this.box = box;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=3; i++) {
			String data = box.getData();
		}
	}
	
}