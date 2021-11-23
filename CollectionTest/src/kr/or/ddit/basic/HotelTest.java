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
	
	// 프로그램 시작
	private void hotelStart() {
		System.out.println("*****************************************");
	    System.out.println("          호텔문을 열었습니다. 어서오십시요.");   
	    System.out.println("*****************************************");
	    System.out.println();
	    
	    while(true) {
		    System.out.println("----------------------------------------------");
		    System.out.println("  어떤 업무를 하시겠습니까?");
		    System.out.println("  1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		    System.out.println("----------------------------------------------");
		    System.out.print(" 선택>>	");
		    
		    int choice = sc.nextInt();
		    switch(choice) {
		    case 1 : checkIn(); break;
		    case 2 : break;
		    case 3 : roomCondition(); break;
		    case 4 : 
		    	System.out.println("*********************************");
			    System.out.println("       호텔문을 닫았습니다.");   
			    System.out.println("**********************************");
			    return;
		    default : System.out.println("잘못 입력하셨습니다.");
		    		  System.out.println("다시 입력해 주세요.");	    
		    }
	    }
	}
	    
	    // 체크인
	    public void checkIn() {
	    	
	    	System.out.println("----------------------------------------------");
	    	System.out.println("체크인 작업");
	    	System.out.println("----------------------------------------------");
	    	System.out.println("* 201~209 : 싱글룸");
	    	System.out.println("* 301~309 : 더블룸");
	    	System.out.println("* 401~409 : 스위트룸");
	    	System.out.println("----------------------------------------------");
	    	System.out.print("방 번호 입력 >>");
	    	int roomNum = sc.nextInt();
	    	String roomType;
	    	
	    	if (roomNum>=201 && roomNum<=209) {
	    		roomType = "싱글룸";
	    		hotelMap.put(roomNum, new Room(roomNum, roomType));
	    	} else if (roomNum>=301 && roomNum<=309) {
	    		roomType = "더블룸";
	    		hotelMap.put(roomNum, new Room(roomNum, roomType));
	    	} else if (roomNum>=401 && roomNum<=409) {
	    		roomType = "스위트룸";
	    		hotelMap.put(roomNum, new Room(roomNum, roomType));
	    	} else {
	    		System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
	    		return;
	    	}
	    	
	    	System.out.println("누구를 체크인 하시겠습니까?");
	    	System.out.print("이름 입력 >>");
	    	String guest = sc.next();
	    	Room r = new Room(roomNum, roomType);
	    	r.setGuest(guest);
	    	
	    	list.add(r.getRoomNum());
	    	list.add(r.getRoomType());
	    	list.add(r.getGuest());
	    	
	    	System.out.println(r);
	    	
	    	System.out.println("체크인이 완료되었습니다.");  			
	    	
	    
	    }
	    
	    // 객실상태
	    private void roomCondition() {
	    	
	    	for(int i=1; i<=9; i++) {
	    		int roomNum = 200+i;
	    		System.out.print(roomNum + "\t");
	    		System.out.print("싱글룸" + "\t");
	    		if(hotelMap.containsKey(roomNum)) {
	    			System.out.println(list.get(2));
	    		} else {
	    			System.out.println("-");
	    		}
	    	}
	    	
	    	for(int i=1; i<=9; i++) {
	    		int roomNum = 300+i;
	    		System.out.print(roomNum + "\t");
	    		System.out.print("더블룸" + "\t");
	    		if(hotelMap.containsKey(roomNum)) {
	    			System.out.println(list.get(2));
	    		} else {
	    			System.out.println("-");
	    		}
	    	}
	    	
	    	for(int i=1; i<=9; i++) {
	    		int roomNum = 400+i;
	    		System.out.print(roomNum + "\t");
	    		System.out.print("스위트룸" + "\t");
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