package kr.or.ddit.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 	'd:/d_other/'������ ����� '���.jpg'������
 	'd:/d_other/������' ������ '���纻-���.jpg'���Ϸ� �����ϴ� ���α׷��� �ۼ��Ͻÿ�.
 */
public class FileCopy {

	public static void main(String[] args) {
			File file = new File("d:/d_other/���.jpg");
		
			if(!file.exists()) {
				System.out.println(file.getPath() + " ������ �����ϴ�.");
				System.out.println("���� �۾��� �����մϴ�.");
				return;
			}
			
			try {
				
				System.out.println("���� ����...");
				// ���� �����͸� �о�� �Է¿� ��Ʈ�� ��ü ����
				FileInputStream fis = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(fis);
				
				// ������ ��¿� ��Ʈ�� ��ü ����
				FileOutputStream fout = 
						new FileOutputStream("d:/d_other/������/���纻-���.jpg");
				BufferedOutputStream bout = new BufferedOutputStream(fout);
				
				int data;
				
				/*
				while((data = fis.read())!=-1) {
					fout.write(data);
				}
				fout.flush();
				
				// ��Ʈ�� �ݱ�
				fout.close();
				fis.close();
				*/
				
				while((data = bis.read())!=-1) {
					bout.write(data);
				}
				bout.flush();
				
				// ��Ʈ�� �ݱ�
				bout.close();
				bis.close();
							
				System.out.println("���� �۾� �Ϸ�...");
				
			} catch (IOException e) {
				// TODO: handle exception
			}
		
		
	}
}
