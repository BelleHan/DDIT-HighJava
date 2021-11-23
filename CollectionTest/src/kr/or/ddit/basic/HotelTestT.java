package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class HotelTestT {
	private HashMap<Integer, RoomT> hotelMap;
	private Scanner scan;
	
	// ������
	public HotelTestT() {
		hotelMap = new HashMap<>();
		scan = new Scanner(System.in);
		
		// ���� �ʱ�ȭ
		for(int i=2; i<=4; i++) {
			String roomType = null;
			switch(i) {
				case 2 : roomType = "�̱۷�"; break;
				case 3 : roomType = "�����"; break;
				case 4 : roomType = "����Ʈ��"; break;
			}
			
			for(int j=1; j<=9; j++) {
				int roomNumber = i * 100 + j;
				RoomT room = new RoomT(roomNumber, roomType);
				hotelMap.put(roomNumber, room);
			}
		}
	}
	
	public static void main(String[] args) {
		new HotelTestT().hotelStart();
	}
	
	// ���� �޼���
	private void hotelStart() {
		System.out.println();
		System.out.println("**************************************");
		System.out.println("         ȣ�ڹ��� �������ϴ�. �������");
		System.out.println("**************************************");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
				case 1 :  // üũ��
					checkIn(); break;
				case 2 :  // üũ�ƿ�
					checkOut(); break;
				case 3 :  // ���� ����
					showRoom(); break;
				case 4 :  // ���� ����
					System.out.println("*************************");
					System.out.println("      ȣ�ڹ��� �ݾҽ��ϴ�.");
					System.out.println("*************************");
					return;
				default : 
					System.out.println("�߸� �Է��߽��ϴ�.");
			}
		}
	}
	
	// üũ �ƿ��� �����ϴ� �޼���
	private void checkOut() {
		System.out.println();
		System.out.println("----------------------------------------------");
    	System.out.println("    üũ�ƿ� �۾�");
    	System.out.println("----------------------------------------------");
    	System.out.println("  üũ�ƿ��� �� ��ȣ�� �Է��ϼ���.");	
    	System.out.print(" ���ȣ �Է� >> ");
    	int num = scan.nextInt();
    	
    	// �Է��� ���ȣ�� Map�� Ű���� ������ �߸� �Է��� ���ȣ�̴�.
    	if(!hotelMap.containsKey(num)) {
    		System.out.println(num + "ȣ ������ �������� �ʽ��ϴ�.");
    	}else if(hotelMap.get(num).getGuestName()==null) {  // �ش� ���ǿ� �ٸ� �մ��� �ִ��� �˻�
    		System.out.println(num + "ȣ ���ǿ��� üũ���� �մ��� �����ϴ�.");
    	}else {
    		// üũ �ƿ� �۾��� �ش� ������ �մ� �̸��� null�� �����ϸ� �ȴ�.
    		
    		// ���� �մ� �̸� ���ϱ�
    		String name = hotelMap.get(num).getGuestName();
    		
    		hotelMap.get(num).setGuestName(null);  // �մ� �̸��� null�� �����ϱ�
    		
    		System.out.println(num + "ȣ ������ " + name + "���� üũ�ƿ��� �Ϸ��߽��ϴ�.");
    	}
	}
	
	// ���� ���¸� ����ϴ� �޼���
	private void showRoom() {
		System.out.println();
		
		// ���ȣ�� ������� ������ �ϱ� ���ؼ� ���ȣ�� List�� ���� �� �����Ѵ�.
		ArrayList<Integer> roomNumList = new ArrayList<>(hotelMap.keySet());
		Collections.sort(roomNumList);  // ���ȣ�� �������� ����
		
		System.out.println("-----------------------------------------");
		System.out.println("   ���� ���� ����");
		System.out.println("-----------------------------------------");
		System.out.println("   �� ��ȣ    �� ����      ������ �̸�");
		System.out.println("-----------------------------------------");
		
		// List���� ���ȣ�� �ϳ��� �����ͼ� Map�� Ű�� �� �ش� ���ȣ�� �ش��ϴ�
		// Room��ü�� ���ؼ� ����Ѵ�. 
		for(int roomNum : roomNumList) {
			RoomT r = hotelMap.get(roomNum);
			System.out.print(roomNum + "\t" + r.getRoomType() + "\t");
			String name = " -";
			if(r.getGuestName()!=null) {
				name = r.getGuestName();
			}
			System.out.println(name);
		}
		System.out.println("-----------------------------------------");
		System.out.println();
			
		
	}
	
	// üũ���ϴ� �޼���
	private void checkIn() {
		
		System.out.println("----------------------------------------------");
    	System.out.println("    üũ�� �۾�");
    	System.out.println("----------------------------------------------");
    	System.out.println("* 201~209 : �̱۷�");
    	System.out.println("* 301~309 : �����");
    	System.out.println("* 401~409 : ����Ʈ��");
    	System.out.println("----------------------------------------------");
    	System.out.print("�� ��ȣ �Է� >> ");   	
    	int num = scan.nextInt();
    	
    	// �Է��� ���ȣ�� Map�� Ű���� ������ �߸� �Է��� ���ȣ�̴�.
    	if(!hotelMap.containsKey(num)) {
    		System.out.println(num + "ȣ ������ �������� �ʽ��ϴ�.");
    	}else if(hotelMap.get(num).getGuestName()!=null) {  // �ش� ���ǿ� �ٸ� �մ��� �ִ��� �˻�
    		System.out.println(num + "ȣ ���ǿ��� �̹� �մ��� �ֽ��ϴ�.");
    	}else {
    		// üũ�� �۾� �����ϱ�
    		System.out.println("������ üũ�� �Ͻðڽ��ϱ�?");
    		System.out.print(" �̸� �Է�>> ");
    		String name = scan.next();
    		
    		// �Է� ���� ������ �̸��� �ش� ������ ������ ��ܿ� �ִ´�.
    		hotelMap.get(num).setGuestName(name);
    		System.out.println(name + "���� " + num + "ȣ ���ǿ� üũ�� �Ǿ����ϴ�.");
    			
    	}
    	
	}
	
	// �޴��� ����ϰ� ������ �۾� ��ȣ�� ��ȯ�ϴ� �޼���
	private int displayMenu() {
		System.out.println("----------------------------------------------");
	    System.out.println("  � ������ �Ͻðڽ��ϱ�?");
	    System.out.println("  1. üũ��    2. üũ�ƿ�    3. ���ǻ���    4. ��������");
	    System.out.println("----------------------------------------------");
	    System.out.print(" ����>>	");
	    int num = scan.nextInt();
	    return num;	    		
	}
}

class RoomT{
	private int roomNumber;     // ���ȣ
	private String roomType;    // ������
	private String guestName;   // �մ��̸�
	
	// ������
	public RoomT(int roomNumber, String roomType) {
		super();
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}


	public int getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public String getGuestName() {
		return guestName;
	}


	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	
	
}