package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {

	public static void main(String[] args) throws IOException {
		// ServerSocket�� �����, Ŭ���̾�Ʈ�� ������ ���� ������ ����
		// �޽����� �޴� ������� �޽����� ������ �����忡 �� ������ �Ѱ��ش�.
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("������ �غ��� �Դϴ�.");
		
		Socket socket = server.accept();
		System.out.println("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�...");
		System.out.println();
		
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
		
		sender.start();
		receiver.start();

	}

}
