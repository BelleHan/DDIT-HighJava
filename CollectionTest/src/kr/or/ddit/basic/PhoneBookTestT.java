package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTestT {
	private Map<String, PhoneT> phoneBookMap;
	private Scanner scan;
	
	// 생성자
	public PhoneBookTestT() {
		scan = new Scanner(System.in);
		phoneBookMap = new HashMap<>();
	}

	public static void main(String[] args) {
		new PhoneBookTestT().phoneStart();
	}
	
	// 프로그램이 시작되는 메서드
	private void phoneStart() {
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println("      전 화 번 호 관 리 프 로 그 램");
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1 : 	// 등록
				insert();
				break;
			case 2 : 	// 수정
				update();
				break;
			case 3 : 	// 삭제
				delete();
				break;
			case 4 : 	// 검색
				search();
				break;
			case 5 : 	// 전체 출력
				phoneBookDisplayAll();
				break;
			case 0 : 	// 프로그램 종료
				System.out.println("프로그램을 종료합니다.");				
				return;
			default : System.out.println("작업 번호를 잘못 입력했습니다.");
			System.out.println("다시 입력하세요.");
			}
		}
	}
	
	// 전화번호 정보를 검색하는 메서드
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨의 전화번호 정보가 없습니다.");
			return; 
		}
		
		// 해당 자료가 있으면 Phone객체를 구해온다.
		PhoneT p = phoneBookMap.get(name);
		
		System.out.println(name + "씨의 전화번호 정보");
		System.out.println("--------------------------");
		System.out.println(" 이  름 : " + p.getName());
		System.out.println(" 전화번호 : " + p.getNum());
		System.out.println(" 주  소 : " + p.getAddr());
		System.out.println("--------------------------");
		System.out.println("검색 완료...");
		
	}
	
	// 전화번호 정보를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨의 전화번호 정보가 없습니다.");
			System.out.println("삭제 작업을 마칩니다.");
			return;
		}
		
		// 삭제 작업 수행
		phoneBookMap.remove(name);
		
		System.out.println(name + "씨의 전화번호 정보를 삭제했습니다.");
				
	}
	
	// 전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		// 수정할 전화번호 정보가 있는지 여부를 검사한다.
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨의 전화번호 정보가 없습니다.");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.next();
		
		scan.nextLine(); // 입력버퍼 비우기
		System.out.print("새로운 주소 >> ");
		String newAddr = scan.nextLine();
		
		// 수정작업 진행 ==> 같은 키값에 새로운 전화번호 정보를 저장한다.
		// 방법1
		//phoneBookMap.put(name, new Phone(name, newTel, newAddr));
		
		// 방법2
		PhoneT p = phoneBookMap.get(name);
		p.setNum(newTel);
		p.setAddr(newAddr);
		
		System.out.println(name + "씨의 전화번호 정보 수정 완료!!");
				
	}
	
	// 메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("-------------------");
		System.out.println("  1. 전화번호 등록");
		System.out.println("  2. 전화번호 수정");
		System.out.println("  3. 전화번호 삭제");
		System.out.println("  4. 전화번호 검색");
		System.out.println("  5. 전화번호 전체 출력");
		System.out.println("  0. 프로그램 종료");
		System.out.println("===================");
		System.out.print("번호 입력  >> ");
		int num = scan.nextInt();
		return num;				
	}
	
	// 새로운 전화번호 정보를 등록하는 메서드
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		// 이미 등록된 사람인지 여부 검사
		if(phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return;
		}
		
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		
		scan.nextLine(); // 입력버퍼 비우기
		System.out.print("주 소 >> ");
		String addr = scan.nextLine();
		
		/*
		// Phone객체(인스턴스) 생성
		Phone p = new Phone(name, tel, addr);
		
		// Map에 추가하기
		phoneBookMap.put(name, p);
		*/
		phoneBookMap.put(name, new PhoneT(name, tel, addr));
		
		System.out.println("'" + name + "' 전화번호 등록 완료!!");
				
	}
	
	// 전체 전화번호 정보를 출력하는 메서드
	private void phoneBookDisplayAll() {
		System.out.println();
		
		// 키값들을 구한다.
		Set<String> phoneKey = phoneBookMap.keySet();
		
		System.out.println("--------------------------------");
		System.out.println(" 번호     이 름         전화번호         주소");
		System.out.println("--------------------------------");
		if(phoneKey.size() == 0) {
			System.out.println(" 등록된 전화번호 정보가 하나도 없습니다.");
		} else {
			int cnt = 0; // 번호가 저장될 변수
			Iterator<String> phoneIt = phoneKey.iterator();					
			while(phoneIt.hasNext()) {
				cnt++;  // 번호 증가
				String name = phoneIt.next(); // 키값 (즉, 이름)
				PhoneT p = phoneBookMap.get(name); // 키값을 이용하여 Phone객체 구하기
				System.out.println(cnt + "\t" + p.getName() + "\t" + 
									p.getNum() + "\t" + p.getAddr());
				
			}
			System.out.println("---------------------------------");
	    	System.out.println("출력 완료...");
		}
		
	}
		
}

class PhoneT {
	private String name;
	private String addr;
	private String num;
	
	// 생성자
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