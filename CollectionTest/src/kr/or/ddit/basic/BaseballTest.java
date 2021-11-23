package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

/*
	문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
		 컴퓨터의 숫자는 난수를 이용하여 구한다.
		 (스트라이크는 S, 볼은 B로 나타낸다.)
		 
	예시)
		컴퓨터의 난수 ==> 9 5 7 (중복이 되면 안되기 때문에 Set 이용, 데이터들은 list에 담는다
							숫자 입력받기 전에 shuffle을 이용해서 한번 섞기)
	실행 예시) 
		숫자입력 => 3 5 6
		3 5 6 ==> 1S 0B
		숫자입력 => 7 8 9
		7 8 9 ==> 0S 2B
		숫자입력 => 9 7 5
		9 7 5 ==> 1S 2B
		숫자입력 => 9 5 7
		9 5 7 ==> 3S 0B
		
		축하합니다.
		당신은 4번째 만에 맞췄습니다.
		
 */
public class BaseballTest {

	public static void main(String[] args) {
		HashSet<Integer> testSet = new HashSet<>();
		
		while(testSet.size() < 3) {
			int num = (int) (Math.random() * 9 + 1);
			testSet.add(num);
		}
		
		ArrayList<Integer> testList = new ArrayList<>(testSet);
		
		Collections.shuffle(testList);
		System.out.println(testList);
		
		Scanner sc = new Scanner(System.in);
		
		int count = 1;
		while(true) {
		System.out.print("숫자 입력 => ");
		ArrayList<Integer> list = new ArrayList<>();
			list.add(sc.nextInt());
			list.add(sc.nextInt());
			list.add(sc.nextInt());
			
			int str = 0;
			for(int i = 0; i<list.size(); i++) {				
				if(list.get(i) == testList.get(i)) {
					str++;
				}	
			}
			
			int ball = 0;			
			for(int i = 0; i<list.size(); i++) {				
				for(int j = 0; j<testList.size(); j++) {
					if(list.get(i) == testList.get(j)) {
						ball++;
					}
				}
			}
			System.out.println(list + " ==> " + str + "S " + (ball-str) + "B");	
			
			if(str == 3 && (ball-str) == 0) {
				break;
			}
			
			count++;
		}
		
		System.out.println();
		System.out.println("축하합니다.");
		System.out.println("당신은" + (count) + "번째 만에 맞췄습니다.");
		
	}
}
