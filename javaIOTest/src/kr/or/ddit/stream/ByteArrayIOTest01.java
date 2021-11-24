package kr.or.ddit.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

// ����Ʈ ����� ��Ʈ���� ����� ����

public class ByteArrayIOTest01 {

	public static void main(String[] args) {
		byte[] insrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outsrc = null;
		
		// �Է¿� ��Ʈ�� ��ü ���� ���� �� �ʱ�ȭ �۾�
		ByteArrayInputStream input = new ByteArrayInputStream(insrc);
		
		// ��¿� ��Ʈ�� ��ü ���� ���� �� �ʱ�ȭ �۾�
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int data;	// �о�� �ڷᰡ ����� ���� ����
		
		// read()�޼��� ==> �� �̻� �о�� �����Ͱ� ������ -1�� ��ȯ�Ѵ�.
		while( (data = input.read()) != -1) {
			// �о�� �����͸� ó���ϴ� ������ �ۼ��ϸ� �ȴ�.
			
			output.write(data);  // �о�� �����͸� ��¿� ��Ʈ������ ����ϱ�		
		}
		
		// ��µ� ��Ʈ������ �迭�� ��ȯ�ؼ� �����ϱ�
		outsrc = output.toByteArray();
		
		// ����� ��Ʈ���� ����� �Ŀ��� ����� ��Ʈ���� ��� �ݾ� �־�� �Ѵ�.
		// (�̰��� '�ڿ� �ݳ�'�̶� �Ѵ�.)
		try {
			input.close();
			output.close();		
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		System.out.println("insrc => " + Arrays.toString(insrc));
		System.out.println("outsrc => " + Arrays.toString(outsrc));
		
	}
	
}
