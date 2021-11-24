package kr.or.ddit.basic;
// �����忡�� ��ü�� �������� ����ϴ� ���� ���α׷�

/*
 	�������� ����ϴ� �������
 	���� �������� ����ϴ� �����尡 �ִ�.
 	
 	�������� �����ϴ� ��ü�� �ʿ��ϴ�.
 	�� ��ü�� �� �����尡 �������� ����ؼ� ó���Ѵ�.
 */

public class ThreadTest15 {

	public static void main(String[] args) {
		System.out.println("�۾� ����...");
		
		// �������� ����� ��ü ����
		ShareData sd = new ShareData();
		
		// ������ ��ü�� �����ϰ� �������� ����� ��ü�� �����忡 �����Ѵ�.
		CalcPIThread calc = new CalcPIThread(sd);
		
		PrintPIThread prn = new PrintPIThread(sd);
		
		calc.start();
		prn.start();
		
	}
}


// �������� �����ϴ� Ŭ���� (�������� ����� Ŭ����)
class ShareData{
	public double result;	// ���� �������� ����� ����
	
	// volatile ==> CPU�� �� �ھ�� ĳ���� �ִµ� �� ĳ���� ������� �ʰ�
	//				���� �޸𸮿��� �����Ͱ��� ����� �Ѵ�.
	public volatile boolean isOk = false;  // ����� �Ϸ�Ǿ����� ���θ� ��Ÿ���� ����
}

// �������� ����ϴ� ������
class CalcPIThread extends Thread{
	private ShareData sd;
	
	// ������
	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
	/*
	 	������ = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 - ....... ) * 4
	 		   +1   -3   +5   -7   +9  .... 
	 		   	0	 1	  2	   3	4 (2�� ���� ��)
	 */
		double sum = 0.0;
		for(int i=1; i<=1_000_000_000; i+=2) {	// 1���� 2�� ����
			if( (i/2) % 2 == 0 ) {  // ���� ¦���� ��
				sum += (1.0 / i);
			}else {  // ���� Ȧ���� ��
				sum -= (1.0 / i);
			}
		}
		
		sd.result = sum * 4;  // �Ϸ�� ��� ����� ���� ��ü�� �����Ѵ�.
		sd.isOk = true;
				
	}
	
}

// ���� �������� ����ϴ� ������
class PrintPIThread extends Thread{
	private ShareData sd;
	
	// ������
	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			if(sd.isOk == true) {  // ����� �Ϸ�Ǿ����� �˻�
				break;
			}
		}
		
		System.out.println();
		System.out.println(" ���: " + sd.result);
		System.out.println(" PI: " + Math.PI);
		
	}
}