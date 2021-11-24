package kr.or.ddit.basic;

/*
 	3���� �����尡 ���� ���ĺ�(A~Z)�� ����ϴµ� ����� ���� �������
 	����� ����ϴ� ���α׷� �ۼ��ϱ�
 */

public class ThreadTest10 {

	public static void main(String[] args) {
		DisplayCharacter[] disp = new DisplayCharacter[] {
			new DisplayCharacter("ȫ�浿"),
			new DisplayCharacter("�̼���"),
			new DisplayCharacter("���е�"),
		};
		
		for(DisplayCharacter ds : disp) {
			ds.start();
		}
		
		// ��� �������� ����� ���������� ��ٸ���.
		for(DisplayCharacter ds : disp) {
			try {
				ds.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		System.out.println();
		System.out.println(" ��� ���");
		System.out.println("�� ��: " + DisplayCharacter.setRank);
		
	}
}

// A~Z���� ����ϴ� ������
class DisplayCharacter extends Thread{
	public static String setRank = "";
	private String name;
	
	// ������
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(char c='A'; c<='Z'; c++) {
			System.out.println(name + "�� ��� ���� -- " + c);
			try {
				// 200 ~ 500 ������ ������ �Ͻ����� �ð��� �����Ѵ�.
				Thread.sleep((int)(Math.random() * (301) + 200 ));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println(name + " ��� ��...");
		
		// ����� ���� ������� �̸��� ��ġ�Ѵ�.
		setRank += name + " ";
	}
	
}
