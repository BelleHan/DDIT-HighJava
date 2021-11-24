package kr.or.ddit.basic;

public class ThreadTest16 {

   public static void main(String[] args) {
      ShareObject obj = new ShareObject();

      TestThread th1 = new TestThread("1�� ������", obj);
      TestThread th2 = new TestThread("2�� ������", obj);

      th1.start();
      th2.start();

   }

}

// ������ ������
class TestThread extends Thread {
   ShareObject sObj;

   public TestThread(String name, ShareObject sObj) {
      super(name); // ������ �̸� ����
      this.sObj = sObj;
   }

   @Override
   public void run() {
      for (int i = 0; i < 10; i++) {
         sObj.add();
      }
   }
}

// �������� ����� Ŭ����
class ShareObject {
   private int sum = 0;

   // ����ȭ ó�� �ϱ�
   //public synchronized void add() { // ���1 : �޼��� ��ü�� ����ȭ ó���� �Ѵ�.
   public void add() { 
	  synchronized (this) {		// ���2 : ����ȭ ������ �����Ѵ�.
		  int n = sum;
		  n += 10;
		  sum = n;
		  System.out.println(Thread.currentThread().getName()
				  + " �հ�: " + sum);	
	}
   }
}