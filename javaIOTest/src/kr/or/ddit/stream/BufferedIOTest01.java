package kr.or.ddit.stream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		try {
			System.out.println("�۾� ����...");
			
			// ������� ���� ����� ���ؼ� Buffered��Ʈ���� ����Ѵ�.(���� ����� �Ǵ� ��Ʈ��)
			FileOutputStream fout = 
					new FileOutputStream("d:/d_other/bufferTest.txt");
		
			// ������ ũ�⸦ �������� ������ �⺻ũ���� 8KB(8192byte)�� �����ȴ�.(����� �����ִ� ������Ʈ��)
			BufferedOutputStream bout =
					new BufferedOutputStream(fout, 5);
			
			for(char i='1'; i<='9'; i++) {
				bout.write(i);
			}
			//bout.flush();  // ���ۿ� ���� �ִ� �����͸� ���������� ��� ��½�Ų��.
			//fout.close();  
			bout.close();  // ������Ʈ���� ������ ������Ʈ���� ����� ����� �Ǵ� ��Ʈ���� �ڵ����� ������.
						   // ������ close()����� flush()��ɵ� �����ϰ� �ִ�.
			
			System.out.println("�۾� ��...");
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
