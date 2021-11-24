package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest07T {
	public static boolean inputCheck; // �ڵ����� false�� �ʱ�ȭ��
	
	public static void main(String[] args) {
		GameTimer timer = new GameTimer();
		
		// ������ �̿��ؼ� ��ǻ���� ���� ���� �� ���ϱ�
		String[] data = {"����", "����", "��"};
		int index = (int)(Math.random() * data.length);	// 0 ~ 2���� ���� �����
		String com = data[index];  // ��ǻ���� ���� ���� ���� ���Ѵ�.
		
		// ������� ���� ���� �� �Է� �ޱ�
		String user = null;	 // ������� ���� ���� ���� ����� ���� ����
		timer.start();  // ī��Ʈ �ٿ� ����...
		
		do {
		user = JOptionPane.showInputDialog("���� ���� ���� �Է��ϼ���.");
//		}while(user==null || !(user.equals("����") || user.equals("����") || user.equals("��")) ); - �Ʒ� �İ� ����
		}while(user==null || (!user.equals("����") && !user.equals("����") && !user.equals("��")) );
		
		inputCheck = true;
		
		// ��� �����ϱ�
		String result = "";
		if(user.equals(com)) {
			result = "�����ϴ�.";
		}else if( user.equals("����") && com.equals("��") ||
				  user.equals("����") && com.equals("����") ||
				  user.equals("��") && com.equals("����")) {
			result = "����� �̰���ϴ�.";
		}else {
			result = "����� �����ϴ�.";
		}
		
		// ��� ���
		System.out.println("  --- ��  ��  ---");
		System.out.println("��ǻ��: " + com);
		System.out.println("�����: " + user);
		System.out.println("��   ��: " + result);
		
	}
}

class GameTimer extends Thread{
	@Override
	public void run() {
		System.out.println("ī��Ʈ �ٿ� ����...");
		for(int i=5; i>=1; i--) {
			
			if(ThreadTest07T.inputCheck==true) {  // �Է� �Ϸ� ���� �˻�
				return;
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		} // for�� ��..
		
		System.out.println("�ð��� �ʰ��Ǿ� ����� �����ϴ�.");
		System.exit(0);
	}
	
}