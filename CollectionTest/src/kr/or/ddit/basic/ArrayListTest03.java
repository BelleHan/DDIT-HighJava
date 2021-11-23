package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 	����1) 5���� ������ �Է� �޾� ArrayList�� �����ϰ� �̵� ��
 		 ������ ���̰� ���� �� ������ ����Ͻÿ�.
 		 (��, �� ������ ���̴� ��� �ٸ��� �Է��Ѵ�.)
 		 
 	����2) ����1���� ������ ���̰� ���� ���� ���� ��츦 ó���Ͻÿ�.
 		  (��, ���ο� Ŭ����(ArrayListTest04)���� �۾��Ͻÿ�.)
 */
public class ArrayListTest03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		ArrayList<String> list = new ArrayList<>();
		
		System.out.println("���� �ٸ� ������ ������ 5�� �Է��ϼ���.");
		for(int i=1; i<6; i++) {
			System.out.print(i + "��° ����: ");
			String name = sc.next();
			list.add(name);
		}
		
		//���� �� ������ ����� ���� ���� ==> List�� ù��° �������� �ʱ�ȭ
		String max = list.get(0);
		
		for(int i=1; i<list.size(); i++) {
			if (list.get(i).length() > max.length()) {
				max = list.get(i);
			}
		}
		
		System.out.println("���� �� ����: " + max);
		
	}
}
