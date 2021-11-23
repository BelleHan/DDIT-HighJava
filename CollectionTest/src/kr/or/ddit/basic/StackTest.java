package kr.or.ddit.basic;

import java.util.LinkedList;

public class StackTest {
	
	public static void main(String[] args) {
		Browser b = new Browser();
		
		b.goURL("1. ���̹�");
		b.history();
		
		b.goURL("2. ����");
		b.goURL("3. ����");
		b.goURL("4. ����");
		b.history();
		
		System.out.println("�ڷΰ��� ��...");
		b.goBack();
		b.history();
		
		System.out.println("�ڷΰ��� ��...");
		b.goBack();
		b.history();
		
		System.out.println("�����ΰ��� ��...");
		b.goForward();
		b.history();
		
		System.out.println("���ο� ����Ʈ �����ϱ�");
		b.goURL("5. ����Ʈ");
		b.history();
		
	}
}

// ���������� �����ΰ���, �ڷΰ��� ��� �����ϱ�(���� �̿�)
class Browser{
	private LinkedList<String> back; // ���� �湮 ������ ����� ����
	private LinkedList<String> forward; // ���� �湮 ������ ����� ����
	private String currentUrl;		// ���� ������
	
	// ������
	public Browser() {
		back = new LinkedList<>();
		forward = new LinkedList<>();
		currentUrl = "";
	}
	
	// ����Ʈ�� �湮�ϴ� �޼��� ==> �Ű��������� �湮�� ����Ʈ�� ����ȴ�.
	public void goURL(String url) {
		if(back.size()>=10) {
			System.out.println("back�� �� �̻� �߰��� �� �����ϴ�.");
			return;
		}
		System.out.println(url + "����Ʈ�� �����մϴ�...");
		
		if(currentUrl!=null && !"".equals(currentUrl)) { // ���� �������� ������
			back.push(currentUrl);  // ���� �������� back���ÿ� �����Ѵ�.	
		}
		currentUrl = url;	// ���� ������ ����
	}
	
	// �ڷ� ����
	public void goBack() {
		// back���ÿ� �����Ͱ� �ִ��� �˻�
		// isEmpty()�޼��尡 �̿� ==> List�� �ڷᰡ �ϳ��� ������ true
		if(!back.isEmpty()) {
			if(forward.size()>=10) {
				System.out.println("forward�� �� �̻� �߰��� �� �����ϴ�.");
				return;
			}
			forward.push(currentUrl);	// ���� �������� forward�� �߰�
			currentUrl = back.pop();	// back���� 1���� ��Ҹ� ������ ���� �������� �Ѵ�.
		}
	}
	
	// ������ ����
	public void goForward() {
		if(!forward.isEmpty()) {
			
			back.push(currentUrl);	// ���� �������� back�� �߰�
			currentUrl = forward.pop();	// forward���� 1���� ��Ҹ� ������ ���� �������� �Ѵ�.					
		}
	}
	
	// �湮 ����� Ȯ���ϴ� �޼���
	public void history() {
		System.out.println("---------------------");
		System.out.println("       ��   ��     ��  ��");
		System.out.println("---------------------");
		System.out.println("back: " + back);
		System.out.println("����������: " + currentUrl);
		System.out.println("forward: " + forward);
		System.out.println("---------------------");
		System.out.println();
	}
}