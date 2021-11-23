package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest2 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-2222"));
		memList.add(new Member(9, "성춘향", "010-3333-3333"));
		memList.add(new Member(3, "강감찬", "010-4444-4444"));
		memList.add(new Member(6, "일지매", "010-5555-5555"));
		memList.add(new Member(2, "변학도", "010-6666-6666"));
		
		System.out.println("정렬 전...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------");
		
		Collections.sort(memList);
		
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------");
		
		Collections.shuffle(memList);
		
		System.out.println("섞기 후...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------");
		
		Collections.sort(memList, new SortNumDesc());
		
		System.out.println("회원번호의 내림순 정렬 후...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------");
	}
}


// Member클래스 작성하기 
//  ==> 회원번호(int), 회원이름(String), 전화번호(String)로 구성
//  ==> 회원이름을 기준으로 오름차순이 되도록 내부 정렬 기준을 넣어 준다.
//  ==> Comparable인터페이스를 구현해서 작성한다.

class Member implements Comparable<Member>{
//class Member{
	private int num;
	private String name;
	private String tel;
	
	public Member(int num, String name, String tel) {
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// 내부 정렬 기준을 처리하는 메서드
	// 회원이름을 기준으로 오름차순이 되도록 내부 정렬 기준을 넣어 준다.
	@Override
	public int compareTo(Member mem) {
		// TODO Auto-generated method stub
		return name.compareTo(mem.getName());
	}
		
}

// 회원번호의 내림차순으로 정렬하는 외부 정렬 기준 클래스 작성하기
class SortNumDesc implements Comparator<Member>{

	@Override
	public int compare(Member mem1, Member mem2) {
//		if(mem1.getNum() > mem2.getNum()) {
//			return -1;
//		} else if(mem1.getNum() < mem2.getNum()) {
//			return 1;
//		} else {
//			return 0;
//		}
		
		// Wrapper클래스를 이용하는 방법1
		//return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1 ;
		
		// Wrapper클래스를 이용하는 방법2
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1 ;
	}
	
}