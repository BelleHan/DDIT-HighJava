package kr.or.ddit.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileIOTest02 {

	public static void main(String[] args) {
		// FileOutputStream�� �̿��ؼ� ���Ͽ� ����ϱ�
		try {
			// FileOutputStream��ü ����
			File file = new File("d:/d_other/out.txt"); // out.txt������ �������� �ʾƵ� OutputStream��ü�� ���� ������ ���� ������ش�. 
														// ���� �Ȱ��� �̸��� ������ ���� �ִٸ� ����⸦ �Ѵ�.
			FileOutputStream fout = new FileOutputStream(file);
			
			for(char ch='a'; ch<='z'; ch++) {
				fout.write(ch);
				
			}
			System.out.println("��� �Ϸ�...");
			fout.close();   // ��Ʈ�� �ݱ�
			
			// =====================
			
			// ������ ������ ���� ������ �о�� ȭ�鿡 ����Ͻÿ�.
			
			FileInputStream fin = new FileInputStream(file);
			int c;
			while((c=fin.read()) != -1) {
				System.out.print((char)c);
			}
			fin.close();
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
