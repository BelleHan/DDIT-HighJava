package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {

	public static void main(String[] args) throws IOException {
		// TCP���� ����� ���� ServerSocket��ü�� �����Ѵ�.
		ServerSocket server = new ServerSocket(7777);
		System.out.println("������ ��ٸ��ϴ�...");
		
		// accept()�޼��� 
		//		 ==> Ŭ���̾�Ʈ���� ���� ��û�� �� ������ ��� ��ٸ���.
		//		 ==> Ŭ���̾�Ʈ�� ���� ��û�� ���� ���ο� Socket��ü�� �����ؼ� 
		//			  Ŭ���̾�Ʈ�� Socket�� ������ �� ��ȯ�Ѵ�.
		Socket socket = server.accept();
		
		// accept()�޼��� ������ ������ ������ �Ϸ�Ǿ�߸� ����ȴ�.
		// �� �޼��� ���Ŀ� ���� �����͸� �ְ� �޴� ����� ����ϸ� �ȴ�.
		System.out.println("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�...");
		System.out.println();
		
		System.out.println("������ ���� ��ǻ���� ����");
		System.out.println("IP�ּ�: " + 
				socket.getInetAddress().getHostAddress());
		System.out.println("Port��ȣ: " + socket.getPort());
		System.out.println();
		
		System.out.println("����� ����� �ڽ��� ����");
		System.out.println("IP�ּ�: " + socket.getLocalAddress());
		System.out.println("Port�ּ�: " + socket.getLocalPort());
		System.out.println();
		
		// Ŭ���̾�Ʈ�� �޽��� ������
		// OutputStream��ü�� �����ؼ� �����Ѵ�.
		// (Socket�� getOutputStream()�޼��带 �̿��Ѵ�.)
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		// Ŭ���̾�Ʈ�� �޽��� �����ϱ�
		dos.writeUTF("ȯ���մϴ�. �������...");
		
		System.out.println("�޽����� ���½��ϴ�...");
		
		// ��Ʈ���� ���� �ݱ�
		dos.close();
		socket.close();
		server.close();
		
	}
}
