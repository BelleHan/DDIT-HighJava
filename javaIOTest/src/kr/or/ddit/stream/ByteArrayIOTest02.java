package kr.or.ddit.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {
		byte[] insrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outsrc = null;
		
		byte[] temp = new byte[4];  // 4��¥�� �迭 ���� -> �о�� �����Ͱ� ����� �迭
		
		ByteArrayInputStream input = new ByteArrayInputStream(insrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			// input.available() ==> �о�� �������� ���� ��ȯ�Ѵ�.
			// �о�� �����Ͱ� �ִ��� ���θ� �˻�
			while(input.available() > 0) {
				/*
				input.read(temp);  // temp�迭 ������ŭ �о�� temp�迭�� �����Ѵ�.			
				output.write(temp);
				*/
				int len = input.read(temp);  // ���� �о�� byte���� ��ȯ�Ѵ�.
				
				// temp�迭�� ���� �߿��� 0��°���� len������ŭ ����Ѵ�.
				output.write(temp, 0, len);
				
				System.out.println("�ݺ��� �ȿ��� temp : " + Arrays.toString(temp));
			}
			
			outsrc = output.toByteArray();
			
			System.out.println();
			System.out.println(" insrc = " + Arrays.toString(insrc));
			System.out.println("outsrc = " + Arrays.toString(outsrc));
			
			input.close();
			output.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		
	}
}
