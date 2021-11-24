package kr.or.ddit.stream;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {

	public static void main(String[] args) {
		// SWING�� ���� '���� â', '���� â' ����
		JFileChooser chooser = new JFileChooser();
		
		// ������ ������ Ȯ���� ����
		FileNameExtensionFilter txt = 
				new FileNameExtensionFilter("Text����(*.txt)", "txt");
		FileNameExtensionFilter img = 
				new FileNameExtensionFilter("�׸� ����", "png", "jpg", "gif");
		FileNameExtensionFilter excel = 
				new FileNameExtensionFilter("��������", new String[] {"xls", "xlsl"});
		
		chooser.addChoosableFileFilter(txt);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(excel);
		
		// '��� ����'��� ǥ�� ���� ���� ==> true:����, false:����
		//chooser.setAcceptAllFileFilterUsed(false);
		
		// Dialogâ�� ��Ÿ�� �⺻ ��� ����
		chooser.setCurrentDirectory(new File("d:/d_other"));
		
		//int result = chooser.showOpenDialog(new Panel()); // ���� â
		int result = chooser.showSaveDialog(new Panel()); // ���� â
		
		// FileChooser���� ������ ���� ���� ��������
		if(result == JFileChooser.APPROVE_OPTION) { // '����' �Ǵ� '����'��ư�� ������ ��
			File selectedFile = chooser.getSelectedFile();
			System.out.println("������ ����: " + selectedFile.getAbsolutePath());
		}
		
		
		
	}
}
