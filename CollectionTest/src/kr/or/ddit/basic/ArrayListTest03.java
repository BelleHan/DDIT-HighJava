package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 	문제1) 5명의 별명을 입력 받아 ArrayList에 저장하고 이들 중
 		 별명의 길이가 제일 긴 별명을 출력하시오.
 		 (단, 각 별명의 길이는 모두 다르게 입력한다.)
 		 
 	문제2) 문제1에서 별명의 길이가 같은 것이 있을 경우를 처리하시오.
 		  (단, 새로운 클래스(ArrayListTest04)에서 작업하시오.)
 */
public class ArrayListTest03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		ArrayList<String> list = new ArrayList<>();
		
		System.out.println("서로 다른 길이의 별명을 5번 입력하세요.");
		for(int i=1; i<6; i++) {
			System.out.print(i + "번째 별명: ");
			String name = sc.next();
			list.add(name);
		}
		
		//제일 긴 별명이 저장될 변수 선언 ==> List의 첫번째 별명으로 초기화
		String max = list.get(0);
		
		for(int i=1; i<list.size(); i++) {
			if (list.get(i).length() > max.length()) {
				max = list.get(i);
			}
		}
		
		System.out.println("제일 긴 별명: " + max);
		
	}
}
