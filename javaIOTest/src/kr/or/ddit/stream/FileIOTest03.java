package kr.or.ddit.stream;

import java.io.FileReader;
import java.io.IOException;

public class FileIOTest03 {

	public static void main(String[] args) {
		// ���� ����� ��Ʈ���� �̿��Ͽ� ���� ���� �о�� ����ϱ�
		try {
			// �Է¿� ���� ��� ��Ʈ�� ��ü ����
			FileReader fr = new FileReader("d:/d_other/test.txt");
			
			int c;  // �о�� �����Ͱ� ����� ����
			while((c=fr.read())!=-1) {
				System.out.print((char)c);
			}
			
			fr.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
