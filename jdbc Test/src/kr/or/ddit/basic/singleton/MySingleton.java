package kr.or.ddit.basic.singleton;
/*
 	Singleton���� ==> ��ü�� 1���� ��������� �ϴ� ������ ����
 		(�ܺο��� new����� ������� ���ϰ� �Ѵ�.)
 		
 	- Singleton Ŭ������ ����� ���( �ʼ� ���� ��� )
 	1. �ڱ��ڽ� class�� �������� ����� ������ private static���� �����Ѵ�.
 	
 	2. �������� ���������ڸ� private���� �Ѵ�. ( �ܺο��� new����� ������ ���� )
 	
 	3. �ڱ��ڽ� class�� �ν��Ͻ��� �����ϰ� �� �������� ��ȯ�ϴ� �޼��带 
 	   public static���� �ۼ��Ѵ�.
 	   (���� �޼������ 'getInstance()'�� �Ѵ�.)
 	   
 	- �̱����� ����ϴ� ����
 	1. �޸� ���� �����ϴ� ����
 	2. �������� ������ �ʿ��� ��
 	
 */
public class MySingleton {
	//1��
	private static MySingleton single;
	
	// 2��
	private MySingleton() {
		System.out.println("�̱��� Ŭ������ ������ �Դϴ�.");
	}
	
	// 3��
	public static MySingleton getInstance() {
		// �ڱ� �ڽ� class�� �ν��Ͻ� ���� �� ��ȯ �۾��� �����Ѵ�.
		if(single==null) {
			single = new MySingleton();
		}
		
		return single;
	}
	
	// ��Ÿ �� Ŭ������ ó���� ������� ����Ѵ�.
	public void displayTest() {
		System.out.println("�̱��� Ŭ������ �޼��� ȣ���Դϴ�...");
	}
}
