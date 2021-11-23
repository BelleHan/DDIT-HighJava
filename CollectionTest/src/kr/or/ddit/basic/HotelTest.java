package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelTest {
	Scanner sc = new Scanner(System.in);
	Map<Integer, Room> hotelMap = new HashMap<>();
	ArrayList list = new ArrayList();
	
	public static void main(String[] args) {
		new HotelTest().hotelStart();
	}
	
	// ���α׷� ����
	private void hotelStart() {
		System.out.println("*****************************************");
	    System.out.println("          ȣ�ڹ��� �������ϴ�. ����ʽÿ�.");   
	    System.out.println("*****************************************");
	    System.out.println();
	    
	    while(true) {
		    System.out.println("----------------------------------------------");
		    System.out.println("  � ������ �Ͻðڽ��ϱ�?");
		    System.out.println("  1. üũ��    2. üũ�ƿ�    3. ���ǻ���    4. ��������");
		    System.out.println("----------------------------------------------");
		    System.out.print(" ����>>	");
		    
		    int choice = sc.nextInt();
		    switch(choice) {
		    case 1 : checkIn(); break;
		    case 2 : break;
		    case 3 : roomCondition(); break;
		    case 4 : 
		    	System.out.println("*********************************");
			    System.out.println("       ȣ�ڹ��� �ݾҽ��ϴ�.");   
			    System.out.println("**********************************");
			    return;
		    default : System.out.println("�߸� �Է��ϼ̽��ϴ�.");
		    		  System.out.println("�ٽ� �Է��� �ּ���.");	    
		    }
	    }
	}
	    
	    // üũ��
	    public void checkIn() {
	    	
	    	System.out.println("----------------------------------------------");
	    	System.out.println("üũ�� �۾�");
	    	System.out.println("----------------------------------------------");
	    	System.out.println("* 201~209 : �̱۷�");
	    	System.out.println("* 301~309 : �����");
	    	System.out.println("* 401~409 : ����Ʈ��");
	    	System.out.println("----------------------------------------------");
	    	System.out.print("�� ��ȣ �Է� >>");
	    	int roomNum = sc.nextInt();
	    	String roomType;
	    	
	    	if (roomNum>=201 && roomNum<=209) {
	    		roomType = "�̱۷�";
	    		hotelMap.put(roomNum, new Room(roomNum, roomType));
	    	} else if (roomNum>=301 && roomNum<=309) {
	    		roomType = "�����";
	    		hotelMap.put(roomNum, new Room(roomNum, roomType));
	    	} else if (roomNum>=401 && roomNum<=409) {
	    		roomType = "����Ʈ��";
	    		hotelMap.put(roomNum, new Room(roomNum, roomType));
	    	} else {
	    		System.out.println(roomNum + "ȣ ������ �������� �ʽ��ϴ�.");
	    		return;
	    	}
	    	
	    	System.out.println("������ üũ�� �Ͻðڽ��ϱ�?");
	    	System.out.print("�̸� �Է� >>");
	    	String guest = sc.next();
	    	Room r = new Room(roomNum, roomType);
	    	r.setGuest(guest);
	    	
	    	list.add(r.getRoomNum());
	    	list.add(r.getRoomType());
	    	list.add(r.getGuest());
	    	
	    	System.out.println(r);
	    	
	    	System.out.println("üũ���� �Ϸ�Ǿ����ϴ�.");  			
	    	
	    
	    }
	    
	    // ���ǻ���
	    private void roomCondition() {
	    	
	    	for(int i=1; i<=9; i++) {
	    		int roomNum = 200+i;
	    		System.out.print(roomNum + "\t");
	    		System.out.print("�̱۷�" + "\t");
	    		if(hotelMap.containsKey(roomNum)) {
	    			System.out.println(list.get(2));
	    		} else {
	    			System.out.println("-");
	    		}
	    	}
	    	
	    	for(int i=1; i<=9; i++) {
	    		int roomNum = 300+i;
	    		System.out.print(roomNum + "\t");
	    		System.out.print("�����" + "\t");
	    		if(hotelMap.containsKey(roomNum)) {
	    			System.out.println(list.get(2));
	    		} else {
	    			System.out.println("-");
	    		}
	    	}
	    	
	    	for(int i=1; i<=9; i++) {
	    		int roomNum = 400+i;
	    		System.out.print(roomNum + "\t");
	    		System.out.print("����Ʈ��" + "\t");
	    		if(hotelMap.containsKey(roomNum)) {
	    			System.out.println(list.get(2));
	    		} else {
	    			System.out.println("-");
	    		}
	    	}
	    	
	    }

}

class Room{
	private int roomNum;
	private String roomType;
	private String guest;
	
	public Room(int roomNum, String roomType) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", roomType=" + roomType + ", guest=" + guest + "]";
	}
		
	
}