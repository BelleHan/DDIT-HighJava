package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient01 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// ���� �ڽ��� ��ǻ�͸� ��Ÿ���� ��� 
		// 1) ������ IP�ּ� : ��) 192.168.45.2
		// 2) ������ IP�ּ� : 127.0.0.1 - ��� ��ǻ���� �� ��ǻ�͸� ��Ÿ���� IP�ּ�
		// 3) ������ ��ǻ�� �̸� : ��) SEM
		// 4) ������ ��ǻ�� �̸� : localhost - �� ��ǻ�Ͷ�� ��
			
		
		System.out.println("������ ��û ��ȣ�� �����ϴ�...");
		
		// ������ IP�ּҿ� Port��ȣ�� �����ؼ� Socket��ü�� �����Ѵ�.
		// Socket��ü�� ������ �Ϸ�Ǹ� �ش� ������ ��û ��ȣ�� ������.
		Socket socket = new Socket("localhost", 7777);
		
		// Socket��ü�� �����ϴ� ��� ���Ŀ��� ������ ������ �Ϸ�� ���� �۾��� ����ϸ� �ȴ�.
		System.out.println("������ ����Ǿ����ϴ�...");
		
		// �������� ������ �޽����� �޾Ƽ� ����ϱ�
		
		// InputStream��ü ����
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		// ������ ���� �ڷ� �޾Ƽ� ����ϱ�
		System.out.println("�������� ������ �޽���: " + dis.readUTF());
		System.out.println();
		
		System.out.println("������ �����մϴ�...");
		
		// ��Ʈ���� ���� �ݱ�
		dis.close();
		socket.close();
		
	}
}
