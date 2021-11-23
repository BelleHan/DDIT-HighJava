package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		// 객체 생성
		Vector v1 = new Vector<>();
		System.out.println("처음 크기: " + v1.size());
		
		// 데이터 추가하기 : add(추가할 데이터)
		// 반환값 : 성공(true), 실패(false)
		v1.add("aaaa");
		v1.add(new Integer(111)); 
		v1.add(123); // 오토 박싱
		v1.add('a');
		v1.add(true);
		boolean r = v1.add(3.14);
		
		System.out.println("현재 크기: " + v1.size());
		System.out.println("반환값: " + r);
		
		System.out.println("v1 => " + v1); //v1.toString 에서 toString 생략
		
		// 데이터 추가하기: addElement(추가할데이터);
		//		==> 이전 버전의 프로그램도 사용할 수 있도록 하기 위해 남아 있는 메서드
		v1.addElement("CCC");
		System.out.println("v1 => " + v1);
		
		// 데이터 추가하기: add(index, 데이터)
		//  ==> 'index'번째에 '데이터'를 끼워 넣는다.
		//  ==> 'index'는 0부터 시작, 반환값은 없다.
		v1.add(1, "kkk");
		System.out.println("v1 => " + v1);
				
		// 데이터 수정: set(index, 새로운 데이터);
		//  ==> 'index'번째의 데이터를 '새로운 데이터'로 덮어쓴다.
		//  ==> 반환값: 원래의 데이터 
		String temp = (String) v1.set(0, "zzz");
		System.out.println("v1 => " + v1);
		System.out.println("원래의 데이터: " + temp);
		
		// 데이터 삭제: remove(index)
		//  ==> 'index'번째의 데이터를 삭제한다.
		//  ==> 반환값: 삭제된 데이터
		v1.remove(0);
		System.out.println("v1 => " + v1);
		
		temp = (String) v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		System.out.println("삭제된 데이터: " + temp);
		 
		// 데이터 삭제: remove(삭제할 데이터)
		//  ==> '삭제할 데이터'를 찾아 삭제한다.
		//  ==> '삭제할 데이터'가 여러개이면 앞에서부터 삭제된다.
		//  ==> 반환값: 삭제성공(true), 삭제실패(false)
		//  ==> '삭제할 데이터'가 '정수형'이거나 'char형'일 경우에는 반드시
		//		 객체로 변환해서 사용해야 한다.
		v1.remove("CCC");
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove(new Integer(123)); // 그냥 123 넣으면 에러 발생(인덱스 범위 초과), 오토박싱이 안되어 있기 때문에 객체로 만들어 주어야함.
		System.out.println("삭제 후 v1 => " + v1);
		
		//v1.remove('a');
		v1.remove(new Character('a'));
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove(true);
		v1.remove(3.14);
		System.out.println("삭제 후 v1 => " + v1);
		
		// 데이터 꺼내오기: get(index)
		//  ==> 'index'번째의 데이터를 반환한다.
		int data = (int) v1.get(0);
		System.out.println("0번째 데이터: " + data);
		
		/*
		 제네릭타입(Generic Type) ==> 클래스 내부에서 사용할 데이터 타입을 클래스 외부에서 지정하는 기법
			==> 객체를 선언할 때 < >안에 그 객체가 사용할 데이터의 타입을 정해주는 것을 말한다. 
			==> 이런 식으로 선언하게 되면 그 데이터 타입 이외의 다른 데이터를 저장할 수 없다.
			==> 이 때 제네릭으로 선언할 수 있는 데이터 타입은 클래스형이어야 한다. (참조형이어야 한다.)
				그래서, int는 Integer, boolean은 Boolean, char은 Character 등으로
				변환해서 사용해야 한다.
			==> 제네릭타입으로 선언하게 되면 데이터를 꺼내 올 때 별도의 형변환이 필요 없다.
		 */
		
		Vector<String> v2 = new Vector<String>(); // String만 저장할 수 있는 벡터
		Vector<Integer> v3 = new Vector<>(); // int형만 저장할 수 있는 벡터 (new Vector<>안을 비워놔도 됨)
		
		v2.add("aaa");
		//v2.add(123); //오류
		
		//String aaa = (String) v2.get(0);
		String aaa = v2.get(0); //<String>을 쓰지 않으면 형변환을 해줘야 하지만 <String>을 쓰면 형변환을 안 해줘도 됨.
		System.out.println("aaa = " + aaa);
		
		Vector<Vector> vv = new Vector<>(); // 벡터 안에 벡터가 들어가는 것이기 때문에 배열로 치면 2차원 배열과 비슷함
		Vector<Vector<Vector>> vvv = new Vector<>(); // 3차원 배열과 비슷
													 //제네릭 타입 안에는 객체 형이면 다 들어갈 수 있다.
		// -------------------------------------------
		System.out.println("=======================================");
		
		v2.clear();  // 전체 데이터를 삭제하는 메서드
		System.out.println("v2의 size = " + v2.size());
		
		v2.add("AAAA");
		v2.add("BBBB");
		v2.add("CCCC");
		v2.add("DDDD");
		v2.add("EEEE");
		
		Vector<String> v4 = new Vector<>();
		v4.add("BBBB");
		v4.add("EEEE");
		
		System.out.println("v2 => " + v2);
		System.out.println("v4 => " + v4);
		
		// 데이터 삭제: removeAll(Collection객체)
		//  ==> 'Collection객체'가 가지고 있는 데이터를 모두 삭제한다.
		//  ==> 반환값 : 성공(true), 실패(false)
		v2.removeAll(v4);
		
		System.out.println("v2 => " + v2);
		System.out.println("v4 => " + v4);
		System.out.println("--------------------------");
		
		v2.clear();
		
		v2.add("AAAA");
		v2.add("BBBB");
		v2.add("CCCC");
		v2.add("DDDD");
		v2.add("EEEE");
		
		// 벡터의 데이터를 순서대로 모두 가져와 사용하고 싶으면 반복문을 사용한다.
		// 주로 for문 사용
		for(int i=0; i<v2.size(); i++) {
			System.out.println(i + "번째 자료: " + v2.get(i));
		}
		System.out.println("-------------------------------");
				
		// 향상된 for문 - 자동으로 데이터 개수만큼 반복
		for(String s : v2) {
			System.out.println(s);
		}
				
	}
}
