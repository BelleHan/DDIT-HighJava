package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SetTest {

	public static void main(String[] args) {
	/*
		- List�� Set�� ������
	1. List
		- �������� ����(index)�� �ִ�.
		- �ߺ��Ǵ� �����͸� ������ �� �ִ�.
	2. Set
		- �������� ����(index)�� ����.
		- �ߺ��Ǵ� �����͸� ������ �� ����.
	 */
		HashSet hs1 = new HashSet();
		
		// Set�� �����͸� �߰��� ���� add()�޼��带 ����Ѵ�.
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set ������: " + hs1);
		System.out.println("Set�� ����: " + hs1.size()); // �÷����� ������ size�޼��� �̿�
		System.out.println();
		
		// Set�� �ߺ��Ǵ� �����͸� �߰��ϸ� false�� ��ȯ�ϰ� �����Ͱ� �߰����� �ʴ´�.
		boolean isAdd = hs1.add("FF");
		System.out.println("�ߺ����� ���� ��: " + isAdd);
		System.out.println("Set ������: " + hs1);
		
		isAdd = hs1.add("CC");
		System.out.println("�ߺ��� ��: " + isAdd);
		System.out.println("Set ������: " + hs1);
		System.out.println();
		
		// Set�� �����͸� �����Ϸ��� �����ϴ� ����� ���� ���� ������
		// �ش� �ڷḦ ������ �� �߰��� �ִ� ����� ����Ѵ�.
		
		// �����ϴ� �޼��� remove(�������ڷ�) ==> ��ȯ��: ����(true), ����(false)
		// 			  clear() ==> ��ü ����
		
		// "FF"�����͸� "EE"�� �����ϱ�
		hs1.remove("FF");
		System.out.println("���� �� hs1 = " + hs1);
		
		hs1.add("EE");
		System.out.println("Set ������: " + hs1);
		System.out.println();
		
		
//		hs1.clear();
//		System.out.println("Set ������: " + hs1);
		
		/*
			Set�� �����Ϳ��� ����(index)�� ���� ������ Listó�� �ε����� �����͸� �ϳ��� �ҷ��� �� ����.
			�׷��� �����͸� �ϳ��� ��� ���ؼ��� Iterator�� ��ü�� ��ȯ�ϰų� '���� for��'�� ����ؼ� ó���Ѵ�.
		
			- Set���� �����͸� Iterator���� ��ü�� ��ȯ�ϴ� �޼��� ==> iterator()
		*/
		 	Iterator it = hs1.iterator();	// Set�����͸� Iterator������ ��ȯ�ϱ�
		 	
		 	// Iterator�� hasNext()�޼���
		 	//		==> Iterator�� �����Ͱ� ����Ű�� ���� ������°�� �����Ͱ� �ִ��� �˻��Ѵ�.
		 	//			������ true, ������ false ��ȯ�Ѵ�.
		 	while(it.hasNext()) {
		 		// Iterator�� next()�޼���
		 		// 		==> Iterator�� �����͸� ������° ��ġ�� �̵��� �� �� ���� �����͸� ��ȯ�Ѵ�.
		 		System.out.println(it.next());
		 	}
		 	System.out.println();
		 	
		 	System.out.println("���� for�� �̿��ϱ�");		 	
		 	// ���� for������ ������ ��������
		 	for(Object obj : hs1) {
		 		System.out.println(obj);
		 	}
		 	
		 	// �츮�� �л��� �� ��ȣ�� �̿��Ͽ� �����ϴ� ���α׷��� �ۼ��� ����.
		 	// ��ȣ�� 1������ 25������ �̰�, ��÷�� �ο��� 5���̴�.
		 	// ��÷�ڸ� ����� ���ÿ�. (�ߺ� ��÷�ڴ� ����.)
		 	
		 	// �ּҰ����� �ִ밪 ������ ������ ���� �����
		 	// (int)(Math.random() * (�ִ밪-�ּҰ�+1) + �ּҰ�) 
		 	// (Math.random()�� 0���� 0.9999...������ ���� �߻���Ŵ)
		 	HashSet<Integer> testSet = new HashSet<>();
		 	
		 	while(testSet.size() < 5) {
			 	int num = (int)(Math.random() * 25 + 1);	
			 	testSet.add(num);
		 	}
		 	
		 	System.out.println("��÷�� ��ȣ: " + testSet);
		 	System.out.println();
		 	for(int num : testSet) {
		 		System.out.println(num);
		 	}
		 	System.out.println();
		 	
		 	// Set������ �ڷḦ List������ ��ȯ�ϱ�
		 	ArrayList<Integer> testList = new ArrayList<>(testSet);
		 	
		 	System.out.println("List ������ ���");
		 	for(int i=0; i<testList.size(); i++) {
		 		System.out.println(testList.get(i));
		 	}
		 	
		 	// �ζǹ�ȣ �����
		 	HashSet<Integer> lottoSet = new HashSet<>();
		 	
		 	while(lottoSet.size() < 6) {
		 		int lotto = (int)(Math.random() * 45 + 1);
		 		lottoSet.add(lotto);
		 	}
		 	
		 	System.out.println("��÷�� ��ȣ: " + lottoSet);
		 	
	}
}
