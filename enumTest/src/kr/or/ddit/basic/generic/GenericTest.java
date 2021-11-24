package kr.or.ddit.basic.generic;

// ���׸��� ������� �ʴ� Ŭ����
class NonGenericClass{
	private Object val;
	
	public void setVal(Object val) {
		this.val = val;
	}
	
	public Object getVal() {
		return val;
	}	
}

/*
 	���׸� Ŭ������ ����� ���
 ����) 
 class Ŭ������<���׸�Ÿ�Ա���>{
 	���׸�Ÿ�Ա��� ������;		// ���� ���� ���׸��� ����� ���
 	...
 	
 	���׸�Ÿ�Ա��� �޼����1(�Ű�������...){	// ��ȯ���� �ִ� �޼��忡 ����� ���
 		...
 		return ��;
 	}
 	
 	void �޼����2(���׸�Ÿ�Ա��� �Ű�������,...){	 // �޼����� �Ű������� ����� ���
 		...
 	}
 }
 
 -- ���׸�Ÿ�Ա��ڿ� ���� ���Ǵ� ���� --
 T ==> Type
 K ==> Key
 V ==> Value
 E ==> Element
 
*/

class MyGeneric<T>{ //T�� Ÿ���� ���� �������� ���� ���׸� ����, ���� ���� ����Ǵ� �ڷ����� ���� �ٲ�� �ȴ�.
	private T val;
	
	public T getVal() {
		return val;
	}
	
	public void setVal(T val) {
		this.val = val;
	}
}

public class GenericTest {

	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("�ȳ��ϼ���.");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(300);
		
		String rtn1 = (String) ng1.getVal(); //�ڽİ�ü�� �θ� ������ ĳ������ ���ص� ������ �θ�ü�� �ڽİ�ü�� �������� ����ȯ ����� ��
		System.out.println("���ڿ� ��ȯ��: " + rtn1);
		
		//Integer rtn2 = (Integer) ng2.getVal();
		int rtn2 = (int) ng2.getVal(); // �̷��� ���� ���������� Integer��(��ü�ڷ���)���� �ٲٰ�  �ڵ����� Integer�� int(�Ϲ��ڷ���)�� �ٲ�
		System.out.println("������ ��ȯ��: " + rtn2);
		
//		String rtn3 = (String) ng2.getVal();
//		System.out.println("rtn3 = " + rtn3);
		System.out.println("-------------------");
		MyGeneric<String> mg1 = new MyGeneric<>();
		mg1.setVal("���ѹα�");

		MyGeneric<Integer> mg2 = new MyGeneric<>();
		mg2.setVal(500);
		
		rtn1 = mg1.getVal();
		rtn2 = mg2.getVal();
		
		System.out.println("���׸� ���ڿ� ��ȯ��: " + rtn1);
		System.out.println("���׸� ������ ��ȯ��: " + rtn2);
		
		//String rtn3 = mg2.getVal(); - ���׸�Ÿ�԰� ��ġ���� �ʾƼ� ������ ���� �߻�
		
	}
}
