package kr.or.ddit.stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void main(String[] args) {
		try {
			
			FileOutputStream fout =
					new FileOutputStream("d:/d_other/test.dat");
			
			// �⺻ �ڷ��� ������ ����� ���� ��Ʈ�� ��ü ����(DataOutputStream)
			DataOutputStream dos = new DataOutputStream(fout);
			
			dos.writeInt(200);			// ���������� ���
			dos.writeFloat(123.45f);	// �Ǽ���(float)���� ���
			dos.writeBoolean(false);	// �������� ���
			dos.writeUTF("ABCD1234"); 	// ���ڿ� ���
			
			System.out.println("��� �Ϸ�...");
			
			dos.close(); 	// ��Ʈ�� �ݱ�
			
			// ----------------------------------------
			// ����� �ڷ� �о����
			FileInputStream fin = new FileInputStream("d:/d_other/test.dat");
			DataInputStream dis = new DataInputStream(fin);
			
			// DataInputStream���� �ڷḦ �о�� ���� 
			// ����� ���� ������ ���� ���ҷ� �о�;� �Ѵ�.
			System.out.println("������: " + dis.readInt());
			System.out.println("�Ǽ���: " + dis.readFloat());
			System.out.println("����: " + dis.readBoolean());
			System.out.println("���ڿ�: " + dis.readUTF());
			
			System.out.println("�б� �۾� �Ϸ�...");
			dis.close();
			
					
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}

