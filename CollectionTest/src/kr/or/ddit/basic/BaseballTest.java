package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

/*
	����) Set�� �̿��Ͽ� ���� �߱� ���� ���α׷��� �ۼ��Ͻÿ�.
		 ��ǻ���� ���ڴ� ������ �̿��Ͽ� ���Ѵ�.
		 (��Ʈ����ũ�� S, ���� B�� ��Ÿ����.)
		 
	����)
		��ǻ���� ���� ==> 9 5 7 (�ߺ��� �Ǹ� �ȵǱ� ������ Set �̿�, �����͵��� list�� ��´�
							���� �Է¹ޱ� ���� shuffle�� �̿��ؼ� �ѹ� ����)
	���� ����) 
		�����Է� => 3 5 6
		3 5 6 ==> 1S 0B
		�����Է� => 7 8 9
		7 8 9 ==> 0S 2B
		�����Է� => 9 7 5
		9 7 5 ==> 1S 2B
		�����Է� => 9 5 7
		9 5 7 ==> 3S 0B
		
		�����մϴ�.
		����� 4��° ���� ������ϴ�.
		
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
		System.out.print("���� �Է� => ");
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
		System.out.println("�����մϴ�.");
		System.out.println("�����" + (count) + "��° ���� ������ϴ�.");
		
	}
}
