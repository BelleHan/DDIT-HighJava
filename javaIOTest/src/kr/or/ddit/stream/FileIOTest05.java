package kr.or.ddit.stream;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 	�ѱ��� ����� ���� �о����(�ѱ��� ���ڵ� ����� �����ؼ� �о�´�.)
 */
public class FileIOTest05 {

	public static void main(String[] args) {
		try {
			//FileReader fr = new FileReader("d:/d_other/test_ansi.txt");
			//FileReader fr = new FileReader("d:/d_other/test_utf8.txt");
			
			FileInputStream fis = 
					new FileInputStream("d:/d_other/test_utf8.txt"); // ����Ʈ ������� �о���̸� � ����̵� �ѱ��� ����
			
			// �⺻ ���ڵ� ������� �о�´�.
			//InputStreamReader isr = new InputStreamReader(fis);
			
			// ���ڵ� ����� �����ؼ� �о����
			// ���ڵ� ��� ����
			// - MS949  ==> �������� �⺻ �ѱ� ���ڵ� ���(ANSI�� ����.)
			// - UTF-8  ==> �����ڵ� UTF-8 ���ڵ� �椷��
			// - US-ASCII ==> ���� ���� ���ڵ� ���
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			
			
			int c;
			
			while((c=isr.read())!= -1) {
				System.out.print((char)c);
			}
			
			//fr.close();
			isr.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
