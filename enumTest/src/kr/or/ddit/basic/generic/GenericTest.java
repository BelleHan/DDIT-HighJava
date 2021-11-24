package kr.or.ddit.basic.generic;

// 제네릭을 사용하지 않는 클래스
class NonGenericClass{
	private Object val;
	
	public void setVal(Object val) {
		this.val = val;
	}
	
	public Object getVal() {
		return val;
	}	
}

/*
 	제네릭 클래스를 만드는 방법
 형식) 
 class 클래스명<제네릭타입글자>{
 	제네릭타입글자 변수명;		// 변수 선언에 제네릭을 사용할 경우
 	...
 	
 	제네릭타입글자 메서드명1(매개변수들...){	// 반환값이 있는 메서드에 사용할 경우
 		...
 		return 값;
 	}
 	
 	void 메서드명2(제네릭타입글자 매개변수명,...){	 // 메서드의 매개변수에 사용할 경우
 		...
 	}
 }
 
 -- 제네릭타입글자에 많이 사용되는 글자 --
 T ==> Type
 K ==> Key
 V ==> Value
 E ==> Element
 
*/

class MyGeneric<T>{ //T는 타입이 아직 정해지지 않은 제네릭 변수, 실제 사용시 선언되는 자료형에 따라 바뀌게 된다.
	private T val;
	
	public T getVal() {
		return val;
	}
	
	public void setVal(T val) {
		this.val = val;
	}
}

public class GenericTest {

	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("안녕하세요.");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(300);
		
		String rtn1 = (String) ng1.getVal(); //자식객체를 부모에 담을땐 캐스팅을 안해도 되지만 부모객체를 자식객체에 담을때는 형변환 해줘야 함
		System.out.println("문자열 반환값: " + rtn1);
		
		//Integer rtn2 = (Integer) ng2.getVal();
		int rtn2 = (int) ng2.getVal(); // 이렇게 쓰면 내부적으로 Integer형(객체자료형)으로 바꾸고  자동으로 Integer가 int(일반자료형)로 바뀜
		System.out.println("정수형 반환값: " + rtn2);
		
//		String rtn3 = (String) ng2.getVal();
//		System.out.println("rtn3 = " + rtn3);
		System.out.println("-------------------");
		MyGeneric<String> mg1 = new MyGeneric<>();
		mg1.setVal("대한민국");

		MyGeneric<Integer> mg2 = new MyGeneric<>();
		mg2.setVal(500);
		
		rtn1 = mg1.getVal();
		rtn2 = mg2.getVal();
		
		System.out.println("제네릭 문자열 반환값: " + rtn1);
		System.out.println("제네릭 정수형 반환값: " + rtn2);
		
		//String rtn3 = mg2.getVal(); - 제네릭타입과 일치하지 않아서 컴파일 오류 발생
		
	}
}
