package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 	 ����) 5���� ��� �̸��� �Է� �޾Ƽ� ArrayList�� ������ �Ŀ� 
 	 	 �̵� �� '��'�� ���� �̸��� ��� ����Ͻÿ�.
 	 	 (��, �Է��� Scanner��ü�� �̿��Ѵ�.)
 */

public class ArrayListTest02 {

		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			
			ArrayList<String> list = new ArrayList<>();
			
			System.out.println("5���� �̸��� �Է��ϼ���.");
			for(int i=1; i<6; i++) {
				System.out.print(i + "��° �̸� : ");
				String name = sc.next();
				list.add(name);
			}
			
			System.out.println("�达 ���� ���� �����... ");
			for(int i=0; i<list.size(); i++) {
				String name = list.get(i);
				
//				if (name.substring(0,1).equals("��")) {					
//				System.out.println(name);
//				}
//				
//				if(name.charAt(0)=='��') {
//					System.out.println(name);
//				}
//				
//				if(name.startsWith("��")) {
//					System.out.println(name);
//				}
				
				if(name.indexOf("��")==0) {
					System.out.println(name);
				}
				
			}
			
		}
		
}
