package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
	
    문제) 이름, 주소, 전화번호를 멤버로 갖는 num클래스를 만들고,
         Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
         
         이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체출력 기능이 있다.
         (데이터는 Map에 저장하여 관리하는데 key값으로는 '이름'을 사용하고
          value값으로는 'num클래스의 인스턴스'로 한다.)
          
    실행예시)
    -------------------------
       1. 전화번호 등록
       2. 전화번호 수정
       3. 전화번호 삭제
       4. 전화번호 검색
       5. 전화번호 전체 출력
       0. 프로그램 종료
     ========================
    번호입력 >> 1
    
    새롭게 등록할 전화번호 정보를 입력하세요.
    이 름 >> 홍길동
    전화번호 >> 010-1234-5678
    주 소 >> 대전시
    
    '홍길동' 전화번호 등록 완료!!
    
    -------------------------
       1. 전화번호 등록
       2. 전화번호 수정
       3. 전화번호 삭제
       4. 전화번호 검색
       5. 전화번호 전체 출력
       0. 프로그램 종료
     ========================
     번호입력 >> 1
     
    새롭게 등록할 전화번호 정보를 입력하세요.
    이 름 >> 홍길동   
    
    '홍길동'은 이미 등록된 사람입니다.
    
    -------------------------
       1. 전화번호 등록
       2. 전화번호 수정
       3. 전화번호 삭제
       4. 전화번호 검색
       5. 전화번호 전체 출력
       0. 프로그램 종료
     ========================
     번호입력 >> 5
     
     -----------------------------------------
     번호   이 름      전화번호       주소
     -----------------------------------------
     1      홍길동       010-1234-5678    대전시
     ~~~
     -----------------------------------------
     출력 완료...
     
     
      -------------------------
       1. 전화번호 등록
       2. 전화번호 수정
       3. 전화번호 삭제
       4. 전화번호 검색
       5. 전화번호 전체 출력
       0. 프로그램 종료
     ========================
     번호 입력 >> 0
     
     프로그램을 종료합니다.
     
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
			System.out.println("  1. 전화번호 등록");
			System.out.println("  2. 전화번호 수정");
			System.out.println("  3. 전화번호 삭제");
			System.out.println("  4. 전화번호 검색");
			System.out.println("  5. 전화번호 전체 출력");
			System.out.println("  0. 프로그램 종료");
			System.out.println("=======================");
			System.out.print("번호 입력  >> ");
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
				case 0 : System.out.println("프로그램을 종료합니다.");
						 break loop;		
			}
		}
	}
	
	    
		// 전화번호 등록
	    public void addPhone() {	   
	    	
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
	    System.out.print("이 름 >> ");
	    name = sc.next();
	    
	    if(map.containsKey(name)) {
	    	System.out.println("'" + name + "'은(는) 이미 등록된 사람입니다.");
	    	return;
	    }
	    
	    System.out.print("전화번호 >> ");
	    num = sc.next();
	    System.out.print("주 소 >> ");
	    addr = sc.next();
			
		
	    Phone phone = new Phone(name, addr, num);
		map.put(name, phone);
			
		System.out.println(name + " 전화번호 등록 완료!!");
		System.out.println();
	    }
	    
		// 전화번호 수정
	    public void modifyPhone() {
	    	
		System.out.println("수정할 전화번호 정보를 입력하세요.");
	    System.out.print("이 름 >> ");
	    name = sc.next();
	    System.out.print("전화번호 >> ");
	    num = sc.next();
	    System.out.print("주 소 >> ");
	    addr = sc.next();
		
	    Phone phone = new Phone(name, addr, num);
	    map.put(name, phone);
		
	    System.out.println(name + " 전화번호 수정 완료!!");
		System.out.println();
	    }
	    
	    // 전화번호 삭제
	    public void removePhone() {
	    	System.out.println("삭제할 전화번호 정보를 입력하세요.");
		    System.out.print("이 름 >> ");
		    name = sc.next();	
		    
		    map.remove(name);
		    
		    System.out.println(name + " 전화번호 삭제 완료!!");
			System.out.println();
	    }
	    
	    // 전화번호 검색
	    public void selectPhone() {
	    	System.out.println("검색할 전화번호 정보를 입력하세요.");
		    System.out.print("이 름 >> ");
		    name = sc.next();
		    
		    if(map.containsKey(name)) {
		    	System.out.println("-----------------------------");
				System.out.println("  이름     전화번호        주소   ");
				System.out.println("-----------------------------");
		    	System.out.println(map.get(name).toString());
		    	System.out.println("-----------------------------");
		    	System.out.println();
		    	
		    } else {
		    	System.out.println("일치하는 정보가 없습니다.");
		    	System.out.println();
		    }
	    }
	    
	    // 전화번호 전체 출력
	    public void selectPhoneList() {
	    	Set<String> keySet = map.keySet();
	    	Iterator<String> it = keySet.iterator();
	    	
	    	System.out.println("---------------------------------");
			System.out.println("  번호    이름     전화번호        주소   ");
			System.out.println("---------------------------------");
	    	int index = 1;
	    	
	    	while(it.hasNext()) {
	    		System.out.println(" " + index + map.get(it.next()).toString());
	    		index++;
	    	}
	    	System.out.println("---------------------------------");
	    	System.out.println("출력 완료...");
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
