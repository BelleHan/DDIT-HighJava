package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest2 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "ȫ�浿", "010-1111-1111"));
		memList.add(new Member(5, "�̼���", "010-2222-2222"));
		memList.add(new Member(9, "������", "010-3333-3333"));
		memList.add(new Member(3, "������", "010-4444-4444"));
		memList.add(new Member(6, "������", "010-5555-5555"));
		memList.add(new Member(2, "���е�", "010-6666-6666"));
		
		System.out.println("���� ��...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------");
		
		Collections.sort(memList);
		
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------");
		
		Collections.shuffle(memList);
		
		System.out.println("���� ��...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------");
		
		Collections.sort(memList, new SortNumDesc());
		
		System.out.println("ȸ����ȣ�� ������ ���� ��...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------");
	}
}


// MemberŬ���� �ۼ��ϱ� 
//  ==> ȸ����ȣ(int), ȸ���̸�(String), ��ȭ��ȣ(String)�� ����
//  ==> ȸ���̸��� �������� ���������� �ǵ��� ���� ���� ������ �־� �ش�.
//  ==> Comparable�������̽��� �����ؼ� �ۼ��Ѵ�.

class Member implements Comparable<Member>{
//class Member{
	private int num;
	private String name;
	private String tel;
	
	public Member(int num, String name, String tel) {
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// ���� ���� ������ ó���ϴ� �޼���
	// ȸ���̸��� �������� ���������� �ǵ��� ���� ���� ������ �־� �ش�.
	@Override
	public int compareTo(Member mem) {
		// TODO Auto-generated method stub
		return name.compareTo(mem.getName());
	}
		
}

// ȸ����ȣ�� ������������ �����ϴ� �ܺ� ���� ���� Ŭ���� �ۼ��ϱ�
class SortNumDesc implements Comparator<Member>{

	@Override
	public int compare(Member mem1, Member mem2) {
//		if(mem1.getNum() > mem2.getNum()) {
//			return -1;
//		} else if(mem1.getNum() < mem2.getNum()) {
//			return 1;
//		} else {
//			return 0;
//		}
		
		// WrapperŬ������ �̿��ϴ� ���1
		//return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1 ;
		
		// WrapperŬ������ �̿��ϴ� ���2
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1 ;
	}
	
}