package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
	
    ����) �̸�, �ּ�, ��ȭ��ȣ�� ����� ���� numŬ������ �����,
         Map�� �̿��Ͽ� ��ȭ��ȣ ������ �����ϴ� ���α׷��� �ۼ��Ͻÿ�.
         
         �� ���α׷����� ��ȭ��ȣ�� ���, ����, ����, �˻�, ��ü��� ����� �ִ�.
         (�����ʹ� Map�� �����Ͽ� �����ϴµ� key�����δ� '�̸�'�� ����ϰ�
          value�����δ� 'numŬ������ �ν��Ͻ�'�� �Ѵ�.)
          
    ���࿹��)
    -------------------------
       1. ��ȭ��ȣ ���
       2. ��ȭ��ȣ ����
       3. ��ȭ��ȣ ����
       4. ��ȭ��ȣ �˻�
       5. ��ȭ��ȣ ��ü ���
       0. ���α׷� ����
     ========================
    ��ȣ�Է� >> 1
    
    ���Ӱ� ����� ��ȭ��ȣ ������ �Է��ϼ���.
    �� �� >> ȫ�浿
    ��ȭ��ȣ >> 010-1234-5678
    �� �� >> ������
    
    'ȫ�浿' ��ȭ��ȣ ��� �Ϸ�!!
    
    -------------------------
       1. ��ȭ��ȣ ���
       2. ��ȭ��ȣ ����
       3. ��ȭ��ȣ ����
       4. ��ȭ��ȣ �˻�
       5. ��ȭ��ȣ ��ü ���
       0. ���α׷� ����
     ========================
     ��ȣ�Է� >> 1
     
    ���Ӱ� ����� ��ȭ��ȣ ������ �Է��ϼ���.
    �� �� >> ȫ�浿   
    
    'ȫ�浿'�� �̹� ��ϵ� ����Դϴ�.
    
    -------------------------
       1. ��ȭ��ȣ ���
       2. ��ȭ��ȣ ����
       3. ��ȭ��ȣ ����
       4. ��ȭ��ȣ �˻�
       5. ��ȭ��ȣ ��ü ���
       0. ���α׷� ����
     ========================
     ��ȣ�Է� >> 5
     
     -----------------------------------------
     ��ȣ   �� ��      ��ȭ��ȣ       �ּ�
     -----------------------------------------
     1      ȫ�浿       010-1234-5678    ������
     ~~~
     -----------------------------------------
     ��� �Ϸ�...
     
     
      -------------------------
       1. ��ȭ��ȣ ���
       2. ��ȭ��ȣ ����
       3. ��ȭ��ȣ ����
       4. ��ȭ��ȣ �˻�
       5. ��ȭ��ȣ ��ü ���
       0. ���α׷� ����
     ========================
     ��ȣ �Է� >> 0
     
     ���α׷��� �����մϴ�.
     
 */  
   
public class PhoneBookTest {
	HashMap<String, Phone> map = new HashMap<String, Phone>();
	Scanner sc = new Scanner(System.in);
	
	String name;
	String num;
    String addr;
		
	public static void main(String[] args) {
		PhoneBookTest pb = new PhoneBookTest();
		Scanner sc = new Scanner(System.in);
		int num = 0;
		
		loop : while(true) {
			
			System.out.println("-----------------------");
			System.out.println("  1. ��ȭ��ȣ ���");
			System.out.println("  2. ��ȭ��ȣ ����");
			System.out.println("  3. ��ȭ��ȣ ����");
			System.out.println("  4. ��ȭ��ȣ �˻�");
			System.out.println("  5. ��ȭ��ȣ ��ü ���");
			System.out.println("  0. ���α׷� ����");
			System.out.println("=======================");
			System.out.print("��ȣ �Է�  >> ");
			num = sc.nextInt();
			System.out.println();
						
			switch(num) {		 
				case 1 : pb.addPhone();
						 break;
				case 2 : pb.modifyPhone();
						 break;
				case 3 : pb.removePhone();
						 break;
				case 4 : pb.selectPhone();
						 break;
				case 5 : pb.selectPhoneList();
						 break;
				case 0 : System.out.println("���α׷��� �����մϴ�.");
						 break loop;		
			}
		}
	}
	
	    
		// ��ȭ��ȣ ���
	    public void addPhone() {	   
	    	
		System.out.println("���Ӱ� ����� ��ȭ��ȣ ������ �Է��ϼ���.");
	    System.out.print("�� �� >> ");
	    name = sc.next();
	    
	    if(map.containsKey(name)) {
	    	System.out.println("'" + name + "'��(��) �̹� ��ϵ� ����Դϴ�.");
	    	return;
	    }
	    
	    System.out.print("��ȭ��ȣ >> ");
	    num = sc.next();
	    System.out.print("�� �� >> ");
	    addr = sc.next();
			
		
	    Phone phone = new Phone(name, addr, num);
		map.put(name, phone);
			
		System.out.println(name + " ��ȭ��ȣ ��� �Ϸ�!!");
		System.out.println();
	    }
	    
		// ��ȭ��ȣ ����
	    public void modifyPhone() {
	    	
		System.out.println("������ ��ȭ��ȣ ������ �Է��ϼ���.");
	    System.out.print("�� �� >> ");
	    name = sc.next();
	    System.out.print("��ȭ��ȣ >> ");
	    num = sc.next();
	    System.out.print("�� �� >> ");
	    addr = sc.next();
		
	    Phone phone = new Phone(name, addr, num);
	    map.put(name, phone);
		
	    System.out.println(name + " ��ȭ��ȣ ���� �Ϸ�!!");
		System.out.println();
	    }
	    
	    // ��ȭ��ȣ ����
	    public void removePhone() {
	    	System.out.println("������ ��ȭ��ȣ ������ �Է��ϼ���.");
		    System.out.print("�� �� >> ");
		    name = sc.next();	
		    
		    map.remove(name);
		    
		    System.out.println(name + " ��ȭ��ȣ ���� �Ϸ�!!");
			System.out.println();
	    }
	    
	    // ��ȭ��ȣ �˻�
	    public void selectPhone() {
	    	System.out.println("�˻��� ��ȭ��ȣ ������ �Է��ϼ���.");
		    System.out.print("�� �� >> ");
		    name = sc.next();
		    
		    if(map.containsKey(name)) {
		    	System.out.println("-----------------------------");
				System.out.println("  �̸�     ��ȭ��ȣ        �ּ�   ");
				System.out.println("-----------------------------");
		    	System.out.println(map.get(name).toString());
		    	System.out.println("-----------------------------");
		    	System.out.println();
		    	
		    } else {
		    	System.out.println("��ġ�ϴ� ������ �����ϴ�.");
		    	System.out.println();
		    }
	    }
	    
	    // ��ȭ��ȣ ��ü ���
	    public void selectPhoneList() {
	    	Set<String> keySet = map.keySet();
	    	Iterator<String> it = keySet.iterator();
	    	
	    	System.out.println("---------------------------------");
			System.out.println("  ��ȣ    �̸�     ��ȭ��ȣ        �ּ�   ");
			System.out.println("---------------------------------");
	    	int index = 1;
	    	
	    	while(it.hasNext()) {
	    		System.out.println(" " + index + map.get(it.next()).toString());
	    		index++;
	    	}
	    	System.out.println("---------------------------------");
	    	System.out.println("��� �Ϸ�...");
	    	System.out.println();
	    }
	
}

class Phone{
	private String name;
	private String addr;
	private String num;
		
	public Phone(String name, String addr, String num) {
		super();
		this.name = name;
		this.num = num;
		this.addr = addr;
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
	public String getnum() {
		return num;
	}
	public void setnum(String num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "  " + name + "  " + num + "  " + addr;
	}
	
	
	
}
