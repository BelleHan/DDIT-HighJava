package kr.or.ddit.basic;

import java.util.ResourceBundle;

/*
 	ResourceBundle��ü ==> ������ Ȯ���ڰ� properties�� ������ ������ �о��
 			key���� value���� �и��ؼ� ������ ���� �ִ� ��ü
 */
public class ResourceBundleTest {

	public static void main(String[] args) {
		// ResourceBundle��ü�� �̿��Ͽ� ���� �о����
		
		// ResourceBundel��ü �����ϱ�
		//	 ==> �о�� ������ ������ �� '��Ű����.���ϸ�'������ �����ϰ� 
		// 		 Ȯ���ڴ� �������� �ʴ´�.
		//		(���� : Ȯ���ڴ� �׻� properties�̱� ������...)
		ResourceBundle bundle = 
			ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		
		// �о�� ���� ����ϱ�
		System.out.println("driver : " + bundle.getString("driver"));
		System.out.println("url : " + bundle.getString("url"));
		System.out.println("user : " + bundle.getString("user"));
		System.out.println("pass : " + bundle.getString("pass"));
		
	}
}
