package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {
	// ������ ������ Socket������ ������ Map��ü ���� ����
	// 		==> key�� : ������ ��� �̸�, value�� : ������ Socket��ü
	private Map<String, Socket> clientMap;
	
	// ������
	public TcpMultiChatServer() {
		// clientMap�� ����ȭ ó���� �ǵ��� �����Ѵ�.
		clientMap = 
			Collections.synchronizedMap(new HashMap<String, Socket>());
	}	
	
	public static void main(String[] args) {
		new TcpMultiChatServer().serverStart();
	}
	
	private void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("������ ���۵Ǿ����ϴ�...");
			
			while(true ) {
				socket = server.accept();  // Ŭ���̾�Ʈ�� ������ ��ٸ���.
				System.out.println("[" + socket.getInetAddress() + " : " +
						 	socket.getPort() + "]���� �����߽��ϴ�.");
				
				// ������ ������ ��ü�� ������ �� �����Ų��.
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(server!=null) try { server.close(); } catch(IOException e) {}
		}
	}
	
	// clientMap�� ����� ��ü ����ڿ��� �޽����� �����ϴ� �޼���
	private void sendToAll(String msg) {
		// clientMap�� ������ ������ŭ �ݺ� 
		for(String name : clientMap.keySet()) {
			try {
				DataOutputStream dos = new DataOutputStream(
						clientMap.get(name).getOutputStream()
					);
					dos.writeUTF(msg);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	} // sendToAll()�޼��� ��...
	
	// �������� Ŭ���̾�Ʈ�� �޽����� �����ϴ� Thread�� �����. (Inner Class�� �ۼ�)
	class ServerReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		
		// ������
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				// ���ſ� ��ü ����
				dis = new DataInputStream(socket.getInputStream());
				
				// �۽ſ� ��ü ����
				dos = new DataOutputStream(socket.getOutputStream());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} // ������ ��..
		
		@Override
		public void run() {
			String name = "";
			try {
				while(true) {
					// Ŭ���̾�Ʈ�� ���� �� ���ʷ� ���� �����ʹ� ������� �̸��̴�.
					// �� �̸��� �ߺ��Ǵ��� ���θ� �˻��ؼ� �� ��� feedBack����
					// Ŭ���̾�Ʈ���� �����ش�.
					name = dis.readUTF();
					
					if(clientMap.containsKey(name)) {  // �̸��� �ߺ��Ǹ�..
						dos.writeUTF("�̸��ߺ�");
					}else {  // �̸��� �ߺ����� ������..
						dos.writeUTF("OK");
						break;
					}
				}  // while�� ��..				
				
				// ��ü ����ڿ��� ���ο� �������� ���忡 ���� �޽����� �����Ѵ�.
				sendToAll("[" + name + "]���� ��ȭ�濡 �����߽��ϴ�.");
				
				// ������̸��� Ŭ���̾�Ʈ�� Socket��ü�� Map�� �����Ѵ�.
				clientMap.put(name, socket);
				
				System.out.println("���� ���� ������ ��: " + clientMap.size() + "��");
				
				// �� Ŭ���̾�Ʈ�� ���� �޽����� �޾Ƽ� ��ü Ŭ���̾�Ʈ���� ������.
				while(dis!=null) {
					sendToAll(dis.readUTF());
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				// �� finally������ ����ȴٴ� ���� �ش� Ŭ���̾�Ʈ�� ������ �����ߴٴ� �ǹ��̴�.
				sendToAll("[" + name + "]���� ������ �����߽��ϴ�.");
				
				// ����� ��Ͽ��� ����
				clientMap.remove(name);
				
				System.out.println("���� ���� ������ ��: " + clientMap.size() + "��");
				
			}
		}
	}
	
	
}
