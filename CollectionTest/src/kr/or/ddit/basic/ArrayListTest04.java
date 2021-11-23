package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/* 		 
 	문제2) 문제1에서 별명의 길이가 같은 것이 있을 경우를 처리하시오.
 		  (단, 새로운 클래스(ArrayListTest04)에서 작업하시오.)
 */
public class ArrayListTest04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		ArrayList<String> list = new ArrayList<>();
		
		System.out.println("별명을 5번 입력하세요.");
		for(int i=1; i<6; i++) {
			System.out.print(i + "번째 별명: ");
			String name = sc.next();
			list.add(name);
		}
		
		//제일 긴 별명의 길이가 저장될 변수 선언 ==> 초기화는 list의 첫번째 데이터의 길이로 초기화 한다.
		int maxLength = list.get(0).length();
		for(int i=1; i<list.size(); i++) {
			if(maxLength < list.get(i).length()) {
				maxLength = list.get(i).length();
			}
		}
		
		System.out.println("제일 긴 별명들...");
		for(String alias : list) {
			if(maxLength == alias.length()) {
				System.out.println(alias);
			}
		}
		
		
	}
}
