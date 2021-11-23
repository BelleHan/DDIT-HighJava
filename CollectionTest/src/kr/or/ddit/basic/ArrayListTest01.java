package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {

		public static void main(String[] args) {
			// ArrayList�� �⺻���� ������ Vector�� ����.
			ArrayList list1 = new ArrayList();
			
			// add()�޼���� �����͸� �߰��Ѵ�.
			list1.add("aaa");
			list1.add("bbb");
			list1.add(123);
			list1.add('k');
			list1.add(true);
			list1.add(123.45);
			
			System.out.println("size ==> " + list1.size());
			System.out.println("list1 ==> " + list1);
			
			// get()�޼���� �����͸� �����´�.
			System.out.println("2��° �ڷ�: " + list1.get(2));
			
			// ������ �����ֱ⵵ ����.
			list1.add(3, "zzz");
			System.out.println("list1 ==> " + list1);
			
			// ������ �����ϱ�
			String temp = (String) list1.set(1, "yyyy");
			System.out.println("temp : " + temp);
			System.out.println("list1 ==> " + list1);
			
			// ������ ����.
			list1.remove(3);
			System.out.println("list1 ==> " + list1);
			
			list1.remove("yyyy");
			System.out.println("list1 ==> " + list1);
			
			// ���׸��� ����� �� �ִ�.
			ArrayList<String> list2 = new ArrayList<>();
			
			list2.add("AAAA");
			list2.add("BBBB");
			list2.add("CCCC");
			list2.add("DDDD");
			list2.add("EEEE");
			
			System.out.println("--------------------------------");
			for(int i=0; i<list2.size(); i++) {
				System.out.println(list2.get(i));
			}
			
			System.out.println("--------------------------------");
			for(String str : list2) {
				System.out.println(str);
			}
			
			System.out.println("---------------------------------");
			
			// contains(�񱳰�ü);
			//  ==> ����Ʈ�� '�񱳰�ü'�� ������ true, ������ false�� ��ȯ
			System.out.println(list2.contains("DDDD"));
			System.out.println(list2.contains("ZZZZ"));
			
			// indexOf(�񱳰�ü)
			//  ==> ����Ʈ�� '�񱳰�ü'�� ������ '�񱳰�ü'�� �ִ� index���� ��ȯ�ϰ�
			// 		'�񱳰�ü'�� �ϳ��� ������ -1�� ��ȯ�Ѵ�.
			
			list2.add("AAAA");
			list2.add("BBBB");
			list2.add("CCCC");
			list2.add("DDDD");
			list2.add("EEEE");
			System.out.println("list2 => " + list2);
			System.out.println("DDDD�� ��ġ��: " + list2.indexOf("DDDD")); //ù��°�� ������ ��ġ�� ��ȯ
			//System.out.println("DDDD�� ��ġ��: " + list2.indexOf("DDDD", 4));
			System.out.println("ZZZZ�� ��ġ��: " + list2.indexOf("ZZZZ"));
			System.out.println("------------------------------------");
			
			// toArray() ==> ����Ʈ ���� �����͵��� �迭�� ��ȯ�Ͽ� ��ȯ�Ѵ�.
			//			 ==> �⺻������ Object�� �迭�� ��ȯ�Ѵ�.
			
			// toArray(new ���׸�Ÿ��[0]); ==> ���׸� Ÿ���� �迭�� ��ȯ�Ѵ�.
			
			Object[] strArr = list2.toArray(); //Object[] - ������Ʈ�� �迭 : Object�� �ִ� �����͸� ������ ����ؾ� �ϱ� ������ ������Ʈ�� �迭 ���, ��Ʈ���迭�� �� �� ������.
			System.out.println("�迭�� ����: " + strArr.length);
			for(int i=0; i<strArr.length; i++) {
				System.out.println(i + "��° �ڷ�: " + strArr[i]);
			}
			System.out.println("------------------------------------");
			
			String[] strArr2 = list2.toArray(new String[0]);
			for(String str : strArr2) {
				System.out.println(str);
			}
			
			
			
		}
}
