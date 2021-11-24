package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 ����) ��ǻ�Ϳ� ���� ���� ���� �����ϴ� ���α׷��� �ۼ��Ͻÿ�.
 
 ��ǻ���� ���� ���� ���� ������ �̿��ؼ� ���ϰ�,
 ������� �Է� showInputDialog()�޼��带 �̿��ؼ� �Է� �޴´�.
 
 �Է� �ð��� 5�ʷ� �����ϰ� ī��Ʈ �ٿ��� �Ѵ�.
 5�� �ȿ� �Է��� ������ ���ӿ� �������� ó���Ѵ�.
 
 5�� �ȿ� �Է��� ������ �Էµ� ���� ��ǻ���� ���и� ���ؼ� ����Ѵ�.
 
 ��� ����)
    -- ��  �� --
       ��ǻ�� : ����
       ����� : ����
       �� �� : ����� �̰���ϴ�.
       
 5�� �ȿ� �Է��� ���� �� ����)
 	�ð��� �ʰ��Ǿ� ����� �����ϴ�.
     
 */

public class ThreadTest07 {

	public static void main(String[] args) {
		
		
		Thread th1 = new Input();
		Thread th2 = new Count();
		
		th1.start();
		th2.start();
	}
}

class Input extends Thread{
	
	public static boolean inputCheck = false;
	
	@Override
	public void run() {
		int com = (int)(Math.random() * 3 + 1);
		String rsp = null;
		String str = JOptionPane.showInputDialog("���� ���� �� �� �ϳ��� �Է��ϼ���.");
		
		inputCheck = true;
				
		if(com == 1) {
			rsp = "����";
		} else if(com == 2) {
			rsp = "����";
		} else {
			rsp = "��";
		}
		System.out.println("��ǻ��: " + rsp);
		System.out.println("�����: " + str);
		
		if(rsp.equals(str)) {
			System.out.println("���: �����ϴ�.");
		} else if (str.equals("����") && rsp.equals("��") ||
				   str.equals("����") && rsp.equals("����") || 
				   str.equals("��") && rsp.equals("����")) {
			System.out.println("����� �̰���ϴ�.");			
		} else {
			System.out.println("��ǻ�Ͱ� �̰���ϴ�.");
		}
		
	}
}

class Count extends Thread{
	@Override
	public void run() {
		for(int i=5; i>=1; i--) {
			if(Input.inputCheck==true) {
				return;
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		System.out.println("�ð��� �ʰ��Ǿ� ����� �����ϴ�.");
		System.exit(0);
	}
}