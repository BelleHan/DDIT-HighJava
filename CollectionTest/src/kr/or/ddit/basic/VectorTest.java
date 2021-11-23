package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		// ��ü ����
		Vector v1 = new Vector<>();
		System.out.println("ó�� ũ��: " + v1.size());
		
		// ������ �߰��ϱ� : add(�߰��� ������)
		// ��ȯ�� : ����(true), ����(false)
		v1.add("aaaa");
		v1.add(new Integer(111)); 
		v1.add(123); // ���� �ڽ�
		v1.add('a');
		v1.add(true);
		boolean r = v1.add(3.14);
		
		System.out.println("���� ũ��: " + v1.size());
		System.out.println("��ȯ��: " + r);
		
		System.out.println("v1 => " + v1); //v1.toString ���� toString ����
		
		// ������ �߰��ϱ�: addElement(�߰��ҵ�����);
		//		==> ���� ������ ���α׷��� ����� �� �ֵ��� �ϱ� ���� ���� �ִ� �޼���
		v1.addElement("CCC");
		System.out.println("v1 => " + v1);
		
		// ������ �߰��ϱ�: add(index, ������)
		//  ==> 'index'��°�� '������'�� ���� �ִ´�.
		//  ==> 'index'�� 0���� ����, ��ȯ���� ����.
		v1.add(1, "kkk");
		System.out.println("v1 => " + v1);
				
		// ������ ����: set(index, ���ο� ������);
		//  ==> 'index'��°�� �����͸� '���ο� ������'�� �����.
		//  ==> ��ȯ��: ������ ������ 
		String temp = (String) v1.set(0, "zzz");
		System.out.println("v1 => " + v1);
		System.out.println("������ ������: " + temp);
		
		// ������ ����: remove(index)
		//  ==> 'index'��°�� �����͸� �����Ѵ�.
		//  ==> ��ȯ��: ������ ������
		v1.remove(0);
		System.out.println("v1 => " + v1);
		
		temp = (String) v1.remove(0);
		System.out.println("���� �� v1 => " + v1);
		System.out.println("������ ������: " + temp);
		 
		// ������ ����: remove(������ ������)
		//  ==> '������ ������'�� ã�� �����Ѵ�.
		//  ==> '������ ������'�� �������̸� �տ������� �����ȴ�.
		//  ==> ��ȯ��: ��������(true), ��������(false)
		//  ==> '������ ������'�� '������'�̰ų� 'char��'�� ��쿡�� �ݵ��
		//		 ��ü�� ��ȯ�ؼ� ����ؾ� �Ѵ�.
		v1.remove("CCC");
		System.out.println("���� �� v1 => " + v1);
		
		v1.remove(new Integer(123)); // �׳� 123 ������ ���� �߻�(�ε��� ���� �ʰ�), ����ڽ��� �ȵǾ� �ֱ� ������ ��ü�� ����� �־����.
		System.out.println("���� �� v1 => " + v1);
		
		//v1.remove('a');
		v1.remove(new Character('a'));
		System.out.println("���� �� v1 => " + v1);
		
		v1.remove(true);
		v1.remove(3.14);
		System.out.println("���� �� v1 => " + v1);
		
		// ������ ��������: get(index)
		//  ==> 'index'��°�� �����͸� ��ȯ�Ѵ�.
		int data = (int) v1.get(0);
		System.out.println("0��° ������: " + data);
		
		/*
		 ���׸�Ÿ��(Generic Type) ==> Ŭ���� ���ο��� ����� ������ Ÿ���� Ŭ���� �ܺο��� �����ϴ� ���
			==> ��ü�� ������ �� < >�ȿ� �� ��ü�� ����� �������� Ÿ���� �����ִ� ���� ���Ѵ�. 
			==> �̷� ������ �����ϰ� �Ǹ� �� ������ Ÿ�� �̿��� �ٸ� �����͸� ������ �� ����.
			==> �� �� ���׸����� ������ �� �ִ� ������ Ÿ���� Ŭ�������̾�� �Ѵ�. (�������̾�� �Ѵ�.)
				�׷���, int�� Integer, boolean�� Boolean, char�� Character ������
				��ȯ�ؼ� ����ؾ� �Ѵ�.
			==> ���׸�Ÿ������ �����ϰ� �Ǹ� �����͸� ���� �� �� ������ ����ȯ�� �ʿ� ����.
		 */
		
		Vector<String> v2 = new Vector<String>(); // String�� ������ �� �ִ� ����
		Vector<Integer> v3 = new Vector<>(); // int���� ������ �� �ִ� ���� (new Vector<>���� ������� ��)
		
		v2.add("aaa");
		//v2.add(123); //����
		
		//String aaa = (String) v2.get(0);
		String aaa = v2.get(0); //<String>�� ���� ������ ����ȯ�� ����� ������ <String>�� ���� ����ȯ�� �� ���൵ ��.
		System.out.println("aaa = " + aaa);
		
		Vector<Vector> vv = new Vector<>(); // ���� �ȿ� ���Ͱ� ���� ���̱� ������ �迭�� ġ�� 2���� �迭�� �����
		Vector<Vector<Vector>> vvv = new Vector<>(); // 3���� �迭�� ���
													 //���׸� Ÿ�� �ȿ��� ��ü ���̸� �� �� �� �ִ�.
		// -------------------------------------------
		System.out.println("=======================================");
		
		v2.clear();  // ��ü �����͸� �����ϴ� �޼���
		System.out.println("v2�� size = " + v2.size());
		
		v2.add("AAAA");
		v2.add("BBBB");
		v2.add("CCCC");
		v2.add("DDDD");
		v2.add("EEEE");
		
		Vector<String> v4 = new Vector<>();
		v4.add("BBBB");
		v4.add("EEEE");
		
		System.out.println("v2 => " + v2);
		System.out.println("v4 => " + v4);
		
		// ������ ����: removeAll(Collection��ü)
		//  ==> 'Collection��ü'�� ������ �ִ� �����͸� ��� �����Ѵ�.
		//  ==> ��ȯ�� : ����(true), ����(false)
		v2.removeAll(v4);
		
		System.out.println("v2 => " + v2);
		System.out.println("v4 => " + v4);
		System.out.println("--------------------------");
		
		v2.clear();
		
		v2.add("AAAA");
		v2.add("BBBB");
		v2.add("CCCC");
		v2.add("DDDD");
		v2.add("EEEE");
		
		// ������ �����͸� ������� ��� ������ ����ϰ� ������ �ݺ����� ����Ѵ�.
		// �ַ� for�� ���
		for(int i=0; i<v2.size(); i++) {
			System.out.println(i + "��° �ڷ�: " + v2.get(i));
		}
		System.out.println("-------------------------------");
				
		// ���� for�� - �ڵ����� ������ ������ŭ �ݺ�
		for(String s : v2) {
			System.out.println(s);
		}
				
	}
}
