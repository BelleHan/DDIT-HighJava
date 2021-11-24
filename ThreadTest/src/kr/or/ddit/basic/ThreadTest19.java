package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class ThreadTest19 {
	/*
	 	Vector, Hashtable�� �������� �����ϴ� Collection��ü���� ���ο�
	 	����ȭ ó���� �Ǿ� �ִ�.
	 	
	 	�׷���, ���� ������ Collection��ü���� ����ȭ ó���� �Ǿ� ���� �ʴ�.
	 	�׷��� ����ȭ ó���� �ʿ��� ���α׷����� �̷� Collection���� ����Ϸ���
	 	����ȭ ó���� �� �Ŀ� ����ؾ� �Ѵ�.
	 */

	// Vector��ü ����
	private static Vector<Integer> vec = new Vector<>();
	
	// List��ü ���� -> ����ȭ ó���� ���� ����
	private static List<Integer> list1 = new ArrayList<>();
	
	// ����ȭ ó���� �� List��ü ����
	private static List<Integer> list2 =
			Collections.synchronizedList(new ArrayList<>());
	
	public static void main(String[] args) {
		
		// �͸� ����ü�� ������ ����
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<10000; i++) {
					//vec.add(i);
					//list1.add(i);
					list2.add(i);
				}
			}
		};
		//---------------------------------
		Thread[] ths = new Thread[] {
				new Thread(r), new Thread(r), new Thread(r),
				new Thread(r), new Thread(r),
		};
		
		for(Thread th : ths) {
			th.start();
		}
		
		for(Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		//System.out.println("vec�� ����: " + vec.size());
		//System.out.println("list1�� ����: " + list1.size());
		System.out.println("list2�� ����: " + list2.size());
		
		
	}
}
