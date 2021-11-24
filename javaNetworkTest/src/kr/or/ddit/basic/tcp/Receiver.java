package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

// �� Ŭ������ ���Ͽ��� �޽����� �޾Ƽ� ȭ�鿡 ����ϴ� ������ ����ϴ� ������ Ŭ����
public class Receiver extends Thread{
	private Socket socket;
	private DataInputStream dis;
	
	// ������
	public Receiver(Socket socket) {
		this.socket = socket;
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		while(dis!=null) {
			try {
				System.out.println(dis.readUTF());
			} catch (Exception e) {
				
			}
		}
	}
}
