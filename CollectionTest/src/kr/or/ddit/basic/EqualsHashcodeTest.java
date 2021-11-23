package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;

public class EqualsHashcodeTest {

	public static void main(String[] args) {
		Person p1 = new Person(1, "�̼���");
		Person p2 = new Person(1, "�̼���");
		Person p3 = p1;
		
		System.out.println("p1 = " + p1);
		System.out.println("p2 = " + p2);
		System.out.println("p3 = " + p3); 	// �������� ���ٴ� ���� �� ������ �ϳ��� ��ü�� ����Ű�� �ִٴ� ��
		System.out.println();
		
		System.out.println(p1 == p2); //�񱳴� ������(�ּ�)�� �������� �ϱ� ������ ��ü ���� �����Ͱ� ���Ƶ� false�� ��µ�.
		System.out.println(p1 == p3);
		System.out.println(p1.equals(p2)); //equals�� Object ��ü�� ������ ����. ���������� '=='���� ���ϱ� ������ ���� �� ���
										   // String�� equals�� ������ �س��ұ� ������ ���ڿ��� ��, �����Ͱ� ������ true
		System.out.println(p1.equals(p3)); //�������� ���� ������ true
		System.out.println("-------------------------------");
		
		System.out.println("p1 hashCode = " + p1.hashCode());
		System.out.println("p2 hashCode = " + p2.hashCode());
		
		HashSet<Person> testSet = new HashSet<>(); // �ߺ� �Ұ�
		testSet.add(p1);
		testSet.add(p2);
		testSet.add(p3);
		
		ArrayList<Person> testList = new ArrayList<>(); // �ߺ� ���
		testList.add(p1);
		testList.add(p2);
		testList.add(p3);
		
		System.out.println("testSet������ : " + testSet.size());
		System.out.println("testList������: " + testList.size());
		
	/*
	 - equals()�޼��� ==> �� ��ü�� ������ ������ �˻��ϴ� ������
	 - hashCode()�޼��� ==> �� ��ü�� ���ϼ��� �˻��ϴµ� ����ϴ� �޼���
	 
	 	
	 	HashSet, HashTable, HashMap�� ���� Hash�� �����ϴ� �÷��� ��ü���� ��ü��
	 	�ǹ̻��� ���ϼ��� ���� equals()�޼���� hashcode()�޼��带 ȣ���Ͽ� ���Ѵ�.
	 	�׷��Ƿ�, ��ü�� ������ ���θ� �����Ϸ��� �� �޼��� ��θ� ������ �ؾ� �Ѵ�.
	 	
	 	 - hashcode()�޼��忡�� ����ϴ� '�ؽ� �˰���'�� ���� �ٸ� ��ü�鿡 ���ؼ�
	 	     ���� hashcode�� ��Ÿ�� �� �ִ�.(������ ��� Ȯ����)
	 	     
	 	==> �׷��Ƿ� ����� �ؽ��ڵ� �� �ϳ��� �������ϸ� �����Ͱ� �޶� true�� ���� �� �ֱ� ������
	 	         �� ���� ���� ������ �ؾ� �Ѵ�.
	 	         
	 */
	}
}

class Person{
	private int id;
	private String name;
	
	// ������ -------------------------
	public Person() {}
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
//	@Override
//	public boolean equals(Object obj) { //������ Person�� p1.equals(p3)���� ��ȣ�ȿ� ���� �� (obj)��
//		if(obj == null) return false;
//		
//		if(this.getClass() != obj.getClass()) return false; // ���� ������ Ŭ�������� �˻� (�� Ŭ������ Student�ε� �񱳴���� Member�̸� ���� �� ����)
//		
//		if(this == obj) // �������� ������ �˻�
//			return true; // if���� �����ִ� ������ �ϳ��̸� {} ���� ����
//		
//		Person that = (Person)obj; // �Ű��������� ������ ��ü �������� ����ȯ �Ѵ�.
//		
////		if(this.name == null && that.name != null) 
////			return false;
////		
////		if(this.id == that.id && this.name.equals(that.name))
////			return true;
//		
//		if(this.id==that.id) {
//			if(this.name == null) {
//				if(that.name == null) return true;
//				else return false;
//			} else {
//				if(this.name.equals(that.name)) return true;
//				else return false;
//			}
//		}
//				
//		return false;
//	}
	
}