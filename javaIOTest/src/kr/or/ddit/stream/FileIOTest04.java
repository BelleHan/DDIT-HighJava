package kr.or.ddit.stream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {

	public static void main(String[] args) {
		// ����ڰ� �Է��� ������ �״�� ���Ϸ� �����ϱ�
		
		// InputStreamReader  ==> �Է¿� byte����� ��Ʈ���� 
		//						  ���ڱ���� ��Ʈ������ ������ �ִ� ���� ��Ʈ��
		// OutputStreamWriter ==> ��¿� byte����� ��Ʈ����
		//						    ���ڱ���� ��Ʈ������ ������ �ִ� ���� ��Ʈ��
		try {
			// System.in ==> �ܼ�(ǥ�� ����� ��ġ)�� �Է� ��ġ�� ��Ʈ��
			InputStreamReader isr = new InputStreamReader(System.in);
			
			FileWriter fw = new FileWriter("d:/d_other/testchar.txt");
			
			System.out.println("�ƹ��ų� �Է��ϼ���.(�Է��� ���� Ctrl + Z �Դϴ�.)");
			
			int c;
			
			// �ַܼ� �Է� �ޱ�
			while((c=isr.read())!= -1) {
				fw.write(c);   // �ַܼ� �Է¹��� �����͸� ���Ͽ� ����ϱ�
			}
			
			isr.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
