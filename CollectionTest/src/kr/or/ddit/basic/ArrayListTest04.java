package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/* 		 
 	����2) ����1���� ������ ���̰� ���� ���� ���� ��츦 ó���Ͻÿ�.
 		  (��, ���ο� Ŭ����(ArrayListTest04)���� �۾��Ͻÿ�.)
 */
public class ArrayListTest04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		ArrayList<String> list = new ArrayList<>();
		
		System.out.println("������ 5�� �Է��ϼ���.");
		for(int i=1; i<6; i++) {
			System.out.print(i + "��° ����: ");
			String name = sc.next();
			list.add(name);
		}
		
		//���� �� ������ ���̰� ����� ���� ���� ==> �ʱ�ȭ�� list�� ù��° �������� ���̷� �ʱ�ȭ �Ѵ�.
		int maxLength = list.get(0).length();
		for(int i=1; i<list.size(); i++) {
			if(maxLength < list.get(i).length()) {
				maxLength = list.get(i).length();
			}
		}
		
		System.out.println("���� �� �����...");
		for(String alias : list) {
			if(maxLength == alias.length()) {
				System.out.println(alias);
			}
		}
		
		
	}
}
