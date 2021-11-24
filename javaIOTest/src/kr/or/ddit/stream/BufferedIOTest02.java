package kr.or.ddit.stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {

	public static void main(String[] args) {
		// ���� ����� Buffered��Ʈ�� ��� ����
		try {
			// ��Ŭ���������� �ڹ� ���α׷��� ����Ǵ� ���� ��ġ�� 
			// �ش� ������Ʈ ������ ���� ��ġ�� �ȴ�.
			FileReader fr = 
					new FileReader("./src/kr/or/ddit/basic/FileTest01.java");
			BufferedReader br = new BufferedReader(fr);
			
			String temp = ""; // �о�� �����Ͱ� ����� ����
			
			// ���ڱ���� buffered��Ʈ���� �� ������ �о���� ����� �ִ�.
			for(int i=1; (temp=br.readLine())!=null; i++) {
				System.out.printf("%4d : %s\n", i, temp); // %d�� ���� ��� 4�� �ڸ���
			}
			
			br.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
