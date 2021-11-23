package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 	 문제) 5명의 사람 이름을 입력 받아서 ArrayList에 저장한 후에 
 	 	 이들 중 '김'씨 성의 이름을 모두 출력하시오.
 	 	 (단, 입력은 Scanner객체를 이용한다.)
 */

public class ArrayListTest02 {

		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			
			ArrayList<String> list = new ArrayList<>();
			
			System.out.println("5명의 이름을 입력하세요.");
			for(int i=1; i<6; i++) {
				System.out.print(i + "번째 이름 : ");
				String name = sc.next();
				list.add(name);
			}
			
			System.out.println("김씨 성을 가진 사람들... ");
			for(int i=0; i<list.size(); i++) {
				String name = list.get(i);
				
//				if (name.substring(0,1).equals("김")) {					
//				System.out.println(name);
//				}
//				
//				if(name.charAt(0)=='김') {
//					System.out.println(name);
//				}
//				
//				if(name.startsWith("김")) {
//					System.out.println(name);
//				}
				
				if(name.indexOf("김")==0) {
					System.out.println(name);
				}
				
			}
			
		}
		
}
