package kr.or.ddit.basic.args;

public class ArgsTest {

	// 매개변수로 받은 정수들의 합계를 구하는 메서드
	// (이 정수들의 개수는 상황에 따라 변할 수 있다.)
	public int sumArr(int[] data) {
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	/*
		가변형 인수 ==> 메서드의 매개변수의 개수가 실행될 때마다 다를 수 있을 때 사용한다.
			    ==> 가변형 인수는 메서드 안에서 배열로 처리된다.
	 */
	
	// 가변형 인수를 이용하는 메서드 만들기 = ('자료형'...'변수명')
	public int sumArg(int...data) {
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		return sum;		
	}
	
	// 가변형 인수와 일반적인 변수를 같이 사용할 경우에는 가변형 변수를 제일 뒤쪽에 배치해야 한다.
//	public String sumArg2(int...data, String name) { //가변형 변수와 일반변수 위치를 바꾸면 오류, 
//	가변형 변수와 일반변수의 자료형이 다르면 구분이 가능하지면 두 개의 자료형이 같을 경우 문제가 생김
//	일반변수가 여러개 올 수도 있음. 가변형 인수를 구분하기 어렵기 때문에 맨 뒤에 배치
	public String sumArg2(String name, int...data) {
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		return name + "씨의 합계: " + sum;
	}
	
	
	public void aaa(int a) {		
	}
	
	public static void main(String[] args) {
		ArgsTest test = new ArgsTest();
		
		int[] nums = {100, 200, 300};
		int[] kkk;
		
		kkk = new int[]{1,2,3,4,5}; //선언과 동시에 초기화시켜주지 않은 경우 다시 한번 new int[]를 선언해줘야 오류가 안남
		
		System.out.println(test.sumArr(nums));
		System.out.println(test.sumArr(new int[]{1,2,3,4,5,6}));
//		int k = 100;		
//		test.aaa(k);
//		test.aaa(200);
		System.out.println();
		System.out.println(test.sumArg(100,200,300));
		System.out.println(test.sumArg(1,2,3,4,5,6));
		
		System.out.println();
		System.out.println(test.sumArg2("홍길동", 70,80,90));
		
	}
}
