package kr.or.ddit.basic.enumTest;

/*
 	enum(������) ==> ���� �����ִ� ������� ������ ��Ÿ����.
 			   ==> Ŭ����ó�� ���̰� �ϴ� ���
 			   ==> �������� Ŭ����ó�� ������ java���Ͽ� �ܵ����� ���� �� �ְ�,
 			   	      �ϳ��� java���Ͽ� Ŭ������ ���� ���� �� �ְ�,
 			   	      Ŭ���� �ȿ� ���� Ŭ����ó�� ���� �� �ִ�.
 			   	      
 	- �������� �Ӽ� �� �޼���
 	1) name() ==> ������ ����� �̸��� ���ڿ��� ��ȯ�Ѵ�.
 	2) ordinal() ==> ������ ����� ���ǵ� ������(index)�� ��ȯ�Ѵ�. (0������ ����...)
 	3) valueOf("�����������") ==> ������ ���������� '�����������'�� ��ġ�ϴ� ������ ����� ��ȯ�Ѵ�.
 	4) �������̸�.����� ==> valueOf()�޼���� ����.
 	
 	- ������ �����ϱ�
 	���1)
 	 	enum �������̸�{ �����1, �����2, �����3, .... }
 	 
 	���2) 
 		enum �������̸�{
 			 �����1(����...),
 			 �����2(����...),
 			 �����3(����...),
 			 ...
 			 �����n(����...);
 			 
 			 // '����'�� ����� ���� ����... (private���� �Ѵ�.)
 			 private �ڷ����̸� ������;
 			 ...
 			 ...
 			 
 			 // �������� �����ڸ� �����.
 			 //  ==> �������� �����ڴ� '���������'�� '����'�� �����ϴ� ������ �����Ѵ�.
 			 //  ==> ������ �����ڴ� ���������� private �̴�. -- private ���� ����
 			 private �������̸�(�ڷ��� ������, ...){  // �Ķ����(�Ű�����)�� ������ '����'�� ������ ����.
 			 	 ���� ����� ������ �ʱ�ȭ�ϱ�;
 			 	 ... 			 
 			 }
 			 
 			 // ������ '����'�� �ܺο��� �ҷ��� �� �ִ� getter�޼��带 �����.
 			  
 		}
 */

public class EnumTest {

	public enum Color { RED, GREEN, BLUE }
	public enum Count { ONE, TWO, THREE }
	
	public enum Season{
		// �����(����) ������ ����
		��("3������ 5������"),
		����("6������ 8������"),
		����("9������ 11������"),
		�ܿ�("12������ 2������");
		
		// ���� ����� ���� ����
		private String value;	
		
		// ������
		Season(String month){	// private Season(String month){ �� ����.
			value = month;
		}
		
		// getter�޼��� �����
		public String getValue() {
			return value;
		}
		
	}
	
	public static void main(String[] args) {
//		System.out.println("RED: " + ConstTest.RED);
//		System.out.println("THREE: " + ConstTest.THREE);
//		
//		if(ConstTest.RED == ConstTest.TWO) {
//			System.out.println("....");
//		} else {
//			System.out.println("****");
//		}
		
		// ������ ����ϱ�
		// ������ ������ �����ϰ� ������ ��������� �ʱ�ȭ�ϱ�
		Color mycol = Color.valueOf("GREEN");  // 3�� ���
		Count mycnt = Count.THREE;			   // 4�� ���
		
		System.out.println("mycol: " + mycol.name());
		System.out.println("mycnt: " + mycnt.name());
		
		System.out.println("mycol ordinal: " + mycol.ordinal());
		System.out.println("mycnt ordinal: " + mycnt.ordinal());
		
//		if(mycol == mycnt) {	// ���� ������ �ٸ� ����̱� ������ �� �Ұ� -> ����
//			System.out.println("...");
//		}
		
		if(mycol == Color.GREEN) {
			System.out.println("����...");
		}
		System.out.println("==================================");
		
		Season ss = Season.valueOf("��");
		System.out.println("name: " + ss.name());
		System.out.println("ordinal: " + ss.ordinal());
		System.out.println("value: " + ss.getValue());
		
		// ��������.values();	==> ��� ������� �迭�� �����´�.
		for(Season se : Season.values()) {
			System.out.println(se.name() + " == " + se + 
					" ==> " + se.getValue());
		}
		
	}
}
