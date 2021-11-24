package kr.or.ddit.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {
		// FileInputStream�� �̿��� ���� ���� �б�
		try {
			// �о�� ������ �μ������� �޴� FileInputStream��ü ����
			
			// ���1  ==> �о�� ���� ������ ���ڿ��� �����ϱ�
			FileInputStream fin = new FileInputStream("d:/d_other/test.txt");
			
			// ���2  ==> �о�� ���� ������ File��ü�� �����ϱ�
//			File file = new File("d:/d_other/test.txt");
//			FileInputStream fin = new FileInputStream(file);
			
			int c;  // �о�� �����͸� ������ ����
			while((c=fin.read()) != -1) {
				
				System.out.print((char)c); // 1����Ʈ�� �о �ٷ� ����ϱ� ������ �ѱ��� ������ ���ڰ� ����
				
			}
			
			fin.close();  // �۾� �Ϸ� �� ��Ʈ�� �ݱ�
			
			
		} catch (IOException e) {
			System.out.println("����� �����Դϴ�...");
		}
		
	}
}
