package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTestT {
	private Map<String, PhoneT> phoneBookMap;
	private Scanner scan;
	
	// ������
	public PhoneBookTestT() {
		scan = new Scanner(System.in);
		phoneBookMap = new HashMap<>();
	}

	public static void main(String[] args) {
		new PhoneBookTestT().phoneStart();
	}
	
	// ���α׷��� ���۵Ǵ� �޼���
	private void phoneStart() {
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println("      �� ȭ �� ȣ �� �� �� �� �� ��");
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1 : 	// ���
				insert();
				break;
			case 2 : 	// ����
				update();
				break;
			case 3 : 	// ����
				delete();
				break;
			case 4 : 	// �˻�
				search();
				break;
			case 5 : 	// ��ü ���
				phoneBookDisplayAll();
				break;
			case 0 : 	// ���α׷� ����
				System.out.println("���α׷��� �����մϴ�.");				
				return;
			default : System.out.println("�۾� ��ȣ�� �߸� �Է��߽��ϴ�.");
			System.out.println("�ٽ� �Է��ϼ���.");
			}
		}
	}
	
	// ��ȭ��ȣ ������ �˻��ϴ� �޼���
	private void search() {
		System.out.println();
		System.out.println("�˻��� ��ȭ��ȣ ������ �Է��ϼ���.");
		System.out.print("�� �� >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "���� ��ȭ��ȣ ������ �����ϴ�.");
			return; 
		}
		
		// �ش� �ڷᰡ ������ Phone��ü�� ���ؿ´�.
		PhoneT p = phoneBookMap.get(name);
		
		System.out.println(name + "���� ��ȭ��ȣ ����");
		System.out.println("--------------------------");
		System.out.println(" ��  �� : " + p.getName());
		System.out.println(" ��ȭ��ȣ : " + p.getNum());
		System.out.println(" ��  �� : " + p.getAddr());
		System.out.println("--------------------------");
		System.out.println("�˻� �Ϸ�...");
		
	}
	
	// ��ȭ��ȣ ������ �����ϴ� �޼���
	private void delete() {
		System.out.println();
		System.out.println("������ ��ȭ��ȣ ������ �Է��ϼ���.");
		System.out.print("�̸� >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "���� ��ȭ��ȣ ������ �����ϴ�.");
			System.out.println("���� �۾��� ��Ĩ�ϴ�.");
			return;
		}
		
		// ���� �۾� ����
		phoneBookMap.remove(name);
		
		System.out.println(name + "���� ��ȭ��ȣ ������ �����߽��ϴ�.");
				
	}
	
	// ��ȭ��ȣ ������ �����ϴ� �޼���
	private void update() {
		System.out.println();
		System.out.println("������ ��ȭ��ȣ ������ �Է��ϼ���.");
		
		System.out.print("�� �� >> ");
		String name = scan.next();
		
		// ������ ��ȭ��ȣ ������ �ִ��� ���θ� �˻��Ѵ�.
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "���� ��ȭ��ȣ ������ �����ϴ�.");
			System.out.println("���� �۾��� ��Ĩ�ϴ�...");
			return;
		}
		
		System.out.print("���ο� ��ȭ��ȣ >> ");
		String newTel = scan.next();
		
		scan.nextLine(); // �Է¹��� ����
		System.out.print("���ο� �ּ� >> ");
		String newAddr = scan.nextLine();
		
		// �����۾� ���� ==> ���� Ű���� ���ο� ��ȭ��ȣ ������ �����Ѵ�.
		// ���1
		//phoneBookMap.put(name, new Phone(name, newTel, newAddr));
		
		// ���2
		PhoneT p = phoneBookMap.get(name);
		p.setNum(newTel);
		p.setAddr(newAddr);
		
		System.out.println(name + "���� ��ȭ��ȣ ���� ���� �Ϸ�!!");
				
	}
	
	// �޴��� ����ϰ� �۾� ��ȣ�� �Է¹޾� ��ȯ�ϴ� �޼���
	private int displayMenu() {
		System.out.println();
		System.out.println("-------------------");
		System.out.println("  1. ��ȭ��ȣ ���");
		System.out.println("  2. ��ȭ��ȣ ����");
		System.out.println("  3. ��ȭ��ȣ ����");
		System.out.println("  4. ��ȭ��ȣ �˻�");
		System.out.println("  5. ��ȭ��ȣ ��ü ���");
		System.out.println("  0. ���α׷� ����");
		System.out.println("===================");
		System.out.print("��ȣ �Է�  >> ");
		int num = scan.nextInt();
		return num;				
	}
	
	// ���ο� ��ȭ��ȣ ������ ����ϴ� �޼���
	private void insert() {
		System.out.println();
		System.out.println("���Ӱ� ����� ��ȭ��ȣ ������ �Է��ϼ���.");
		System.out.print("�� �� >> ");
		String name = scan.next();
		
		// �̹� ��ϵ� ������� ���� �˻�
		if(phoneBookMap.containsKey(name)) {
			System.out.println(name + "���� �̹� ��ϵ� ����Դϴ�.");
			return;
		}
		
		System.out.print("��ȭ��ȣ >> ");
		String tel = scan.next();
		
		scan.nextLine(); // �Է¹��� ����
		System.out.print("�� �� >> ");
		String addr = scan.nextLine();
		
		/*
		// Phone��ü(�ν��Ͻ�) ����
		Phone p = new Phone(name, tel, addr);
		
		// Map�� �߰��ϱ�
		phoneBookMap.put(name, p);
		*/
		phoneBookMap.put(name, new PhoneT(name, tel, addr));
		
		System.out.println("'" + name + "' ��ȭ��ȣ ��� �Ϸ�!!");
				
	}
	
	// ��ü ��ȭ��ȣ ������ ����ϴ� �޼���
	private void phoneBookDisplayAll() {
		System.out.println();
		
		// Ű������ ���Ѵ�.
		Set<String> phoneKey = phoneBookMap.keySet();
		
		System.out.println("--------------------------------");
		System.out.println(" ��ȣ     �� ��         ��ȭ��ȣ         �ּ�");
		System.out.println("--------------------------------");
		if(phoneKey.size() == 0) {
			System.out.println(" ��ϵ� ��ȭ��ȣ ������ �ϳ��� �����ϴ�.");
		} else {
			int cnt = 0; // ��ȣ�� ����� ����
			Iterator<String> phoneIt = phoneKey.iterator();					
			while(phoneIt.hasNext()) {
				cnt++;  // ��ȣ ����
				String name = phoneIt.next(); // Ű�� (��, �̸�)
				PhoneT p = phoneBookMap.get(name); // Ű���� �̿��Ͽ� Phone��ü ���ϱ�
				System.out.println(cnt + "\t" + p.getName() + "\t" + 
									p.getNum() + "\t" + p.getAddr());
				
			}
			System.out.println("---------------------------------");
	    	System.out.println("��� �Ϸ�...");
		}
		
	}
		
}

class PhoneT {
	private String name;
	private String addr;
	private String num;
	
	// ������
	public PhoneT(String name, String addr, String num) {
		super();
		this.name = name;
		this.addr = addr;
		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	
}