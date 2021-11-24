package kr.or.ddit.basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		// File��ü ����� ����
		
		// 1. new File(String ���� �Ǵ� ���)
		//  ==> ���丮�� ���丮 ���� �Ǵ� ���丮�� ���ϸ� ������ ���й��ڴ� 
		//  ==> '/'�Ǵ� '\'�� ����� �� �ִ�.
		File file1 = new File("D:/D_Other/test.txt");  // ���й��ڸ� '/'�� ���
		//File file1 = new File("D:\\D_Other\\test.txt");  // ���й��ڸ� '\'�� ��� ('\'�� �ݵ�� �ι��� ����� ��)
		System.out.println("���ϸ�: " + file1.getName());
		System.out.println("���丮 �ΰ�? : " + file1.isDirectory());
		System.out.println("���� �ΰ�? : " + file1.isFile());
		System.out.println();
		
		
		File file2 = new File("d://d_other"); // ������� ��������� ������ ��ҹ��� ���߾ ���־����.
		System.out.println("���ϸ�: " + file2.getName());
		System.out.println("���丮 �ΰ�? : " + file2.isDirectory());
		System.out.println("���� �ΰ�? : " + file2.isFile());
		System.out.println();
		
		// 2. new File(File parent, String child)
		//  ==> 'parent'���丮 �ȿ� �ִ� 'child'������ ��Ÿ����.
		File file3 = new File(file2, "test.txt");
		System.out.println("���ϸ�: " + file3.getName());
		System.out.println("���丮 �ΰ�? : " + file3.isDirectory());
		System.out.println("���� �ΰ�? : " + file3.isFile());
		System.out.println();
		
		// 3. new File(String parent, String child)  
		//  ==> 'parent'���丮 �ȿ� �ִ� 'child'������ ��Ÿ����.
		// �θ�ü�� ���Ϸ� ��Ÿ������ ���ڿ��� ��Ÿ�������� 2���� �ٸ�
		File file4 = new File("d:/d_other", "test.txt");
		System.out.println("���ϸ�: " + file4.getName());
		System.out.println("���丮 �ΰ�? : " + file4.isDirectory());
		System.out.println("���� �ΰ�? : " + file4.isFile());
		System.out.println();
		System.out.println("--------------------------------------------------");
		//====================================================================
		
		// ���丮(����) �����
		// - mkdir()  ==> File��ü�� ��� �� ������ ��ġ�� ���丮�� �����.
		//			  ==> ��ȯ�� : ����� ����(true), ����� ����(false)
		//			  ==> ���������� ������� �ϴ� ���� ������ �������� �̸� ������� �־�� �Ѵ�.
		
		// - mkdirs() ==> ������� �ϴ� ���� ������ ������ ������ �� ������ �������� ������ش�.
		//		  ==> ��ȯ�� : ����� ����(true), ����� ����(false)
		File file5 = new File("d:/d_other/������");
		System.out.println(file5.getName() + "�� ���� ����: " + file5.exists());
		
		if(file5.mkdir()) {   // �����ϰ� ���� ����⿡ �����ߴٸ� true ���и� false, new File()�ȿ� �ִ�  ��ġ���� ������ ���� ���� �̹� �ִٸ� false
			System.out.println(file5.getName() + "���� ���� �Ϸ�!!");
		}else {
			System.out.println(file5.getName() + "���� ���� ����~~");
		}
		System.out.println();
		
		File file6 = new File("d:/d_other/test/java/src");
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + " ����� ����");
		}else {
			System.out.println(file6.getName() + " ����� ����");
		}
		
	}
}
