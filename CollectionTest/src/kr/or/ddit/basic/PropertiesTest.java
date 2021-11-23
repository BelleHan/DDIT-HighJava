package kr.or.ddit.basic;

import java.util.Properties;

public class PropertiesTest {
	/*
	 	Properties ��ü
	 ==> Map���� ��ҵ� ����� ���� ��ü��� �� �� �ִ�.
	 
	 ==> Map�� key���� value���� ��� ������ ��ü�� ����� �� ������
	 	 Properties�� key���� value���� String�� ����� �� �ִ�.
	 	 
	 ==> Map�� put()�޼���� get()�޼��带 �̿��ؼ� �����͸� �����������
	 	 Properties�� setProperty()�޼���� getProperty()�޼��带 ���ؼ� ������Ѵ�.
	 	 
	 ==> Properties�� �����͸� ���Ϸ� ����� �� �� �ִ�.
	 */
	public static void main(String[] args) {
		// ��ü ����
		Properties prop = new Properties();
		
		// ������ �߰�
		int iAge = 30;
		prop.setProperty("name", "ȫ�浿");
		//prop.setProperty("age", "20");
		//prop.setProperty("age", String.valueOf(iAge));
		prop.setProperty("age", "" + iAge);
		prop.setProperty("tel", "010-1234-5678");
		prop.setProperty("addr", "������");
		
		// ������ �б� ==> getProperty()
		String name = prop.getProperty("name");
		String age = prop.getProperty("age");
		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");
		
		System.out.println("�� ��: " + name);
		System.out.println("�� ��: " + age);
		System.out.println("�� ȭ: " + tel);
		System.out.println("�� ��: " + addr);
		
		
	}
	
}
