package kr.or.ddit.stream;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 	������ ���� ���ϰ� ��µ� ������ JFileChooser��ü�� �̿��ؼ� �����Ͽ� ó���ϵ��� �Ѵ�.
 */
public class FileCopy2 {

	public static void main(String[] args) {
			JFileChooser chooser = new JFileChooser();
				
		
			FileNameExtensionFilter img = 
					new FileNameExtensionFilter("�׸� ����", "png", "jpg", "gif");
			
		
			chooser.addChoosableFileFilter(img);
		
		
			// Dialogâ�� ��Ÿ�� �⺻ ��� ����
			chooser.setCurrentDirectory(new File("d:/d_other"));
				
			int result = chooser.showOpenDialog(new Panel()); // ���� â
				
			File sourceFile = null;
			if(result == JFileChooser.APPROVE_OPTION) {
				sourceFile = chooser.getSelectedFile();
			}else {
				System.out.println("���� ������ �������� �ʾҽ��ϴ�.");
				System.out.println("���� �۾��� �����մϴ�.");
				return;
			}
				
			
			if(!sourceFile.exists()) {
				System.out.println(sourceFile.getPath() + " ������ �����ϴ�.");
				System.out.println("���� �۾��� �����մϴ�.");
				return;
			}
			
			try {
				
				System.out.println("���� ����...");
				// ���� �����͸� �о�� �Է¿� ��Ʈ�� ��ü ����
				FileInputStream fis = new FileInputStream(sourceFile);
				BufferedInputStream bis = new BufferedInputStream(fis);
				
				int result2 = chooser.showSaveDialog(new Panel());
				File targetFile = null;
				if(result2 == JFileChooser.APPROVE_OPTION) {
					targetFile = chooser.getSelectedFile();
				}else {
					System.out.println("����� ��� ������ �������� �ʾҽ��ϴ�.");
					System.out.println("���� �۾��� �����մϴ�.");
					return;
				}
				
				// ������ ��¿� ��Ʈ�� ��ü ����
				FileOutputStream fout = new FileOutputStream(targetFile);
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
