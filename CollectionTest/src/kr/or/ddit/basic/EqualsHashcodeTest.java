package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;

public class EqualsHashcodeTest {

	public static void main(String[] args) {
		Person p1 = new Person(1, "이순신");
		Person p2 = new Person(1, "이순신");
		Person p3 = p1;
		
		System.out.println("p1 = " + p1);
		System.out.println("p2 = " + p2);
		System.out.println("p3 = " + p3); 	// 참조값이 같다는 것은 두 변수가 하나의 객체를 가르키고 있다는 뜻
		System.out.println();
		
		System.out.println(p1 == p2); //비교는 참조값(주소)을 기준으로 하기 때문에 객체 안의 데이터가 같아도 false가 출력됨.
		System.out.println(p1 == p3);
		System.out.println(p1.equals(p2)); //equals는 Object 객체가 가지고 있음. 내부적으로 '=='으로 비교하기 때문에 같은 값 출력
										   // String은 equals를 재정의 해놓았기 때문에 문자열을 비교, 데이터가 같으면 true
		System.out.println(p1.equals(p3)); //참조값이 같기 때문에 true
		System.out.println("-------------------------------");
		
		System.out.println("p1 hashCode = " + p1.hashCode());
		System.out.println("p2 hashCode = " + p2.hashCode());
		
		HashSet<Person> testSet = new HashSet<>(); // 중복 불가
		testSet.add(p1);
		testSet.add(p2);
		testSet.add(p3);
		
		ArrayList<Person> testList = new ArrayList<>(); // 중복 허용
		testList.add(p1);
		testList.add(p2);
		testList.add(p3);
		
		System.out.println("testSet사이즈 : " + testSet.size());
		System.out.println("testList사이즈: " + testList.size());
		
	/*
	 - equals()메서드 ==> 두 객체의 내용이 같은지 검사하는 연산자
	 - hashCode()메서드 ==> 두 객체의 동일성을 검사하는데 사용하는 메서드
	 
	 	
	 	HashSet, HashTable, HashMap과 같이 Hash로 시작하는 컬렉션 객체들은 객체의
	 	의미상의 동일성을 위해 equals()메서드와 hashcode()메서드를 호출하여 비교한다.
	 	그러므로, 객체가 같은지 여부를 결정하려면 두 메서드 모두를 재정의 해야 한다.
	 	
	 	 - hashcode()메서드에서 사용하는 '해싱 알고리즘'은 서로 다른 객체들에 대해서
	 	     같은 hashcode를 나타낼 수 있다.(굉장히 드믄 확률로)
	 	     
	 	==> 그러므로 이퀄즈나 해쉬코드 중 하나만 재정의하면 데이터가 달라도 true가 나올 수 있기 때문에
	 	         두 개를 같이 재정의 해야 한다.
	 	         
	 */
	}
}

class Person{
	private int id;
	private String name;
	
	// 생성자 -------------------------
	public Person() {}
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
//	@Override
//	public boolean equals(Object obj) { //현재의 Person과 p1.equals(p3)에서 괄호안에 들어가는 값 (obj)비교
//		if(obj == null) return false;
//		
//		if(this.getClass() != obj.getClass()) return false; // 같은 유형의 클래스인지 검사 (한 클래스가 Student인데 비교대상이 Member이면 같을 수 없음)
//		
//		if(this == obj) // 참조값이 같은지 검사
//			return true; // if문에 딸려있는 문장이 하나이면 {} 생략 가능
//		
//		Person that = (Person)obj; // 매개변수값을 현재의 객체 유형으로 형변환 한다.
//		
////		if(this.name == null && that.name != null) 
////			return false;
////		
////		if(this.id == that.id && this.name.equals(that.name))
////			return true;
//		
//		if(this.id==that.id) {
//			if(this.name == null) {
//				if(that.name == null) return true;
//				else return false;
//			} else {
//				if(this.name.equals(that.name)) return true;
//				else return false;
//			}
//		}
//				
//		return false;
//	}
	
}