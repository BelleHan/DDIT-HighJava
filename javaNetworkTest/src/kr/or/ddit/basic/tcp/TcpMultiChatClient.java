package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {

	public static void main(String[] args) {
		new TcpMultiChatClient().clientStart();
	}
	
	private void clientStart() {
		Socket socket = null;
		try {
			socket = new Socket("192.168.45.34", 7777);
			System.out.println("������ ����Ǿ����ϴ�.");
			System.out.println();
			
			ClientSender sender = new ClientSender(socket);
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	} // clientStart()�޼��� ��...
	
	// -------------------------------------- ������ �ۼ�
	
	// �޽��� ���ۿ� ������ 
	class ClientSender extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		private String name;
		private Scanner scan;
		
		// ������
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);
			
			try {
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				
				if(dos!=null) {
					// ó�� ������ �Ǹ� �ڽ��� ��ȭ���� �Է� �޾� ������ �����ϰ� 
					// ��ȭ���� �ߺ����θ� feedBack���� �޾Ƽ� Ȯ���Ѵ�.
					System.out.print("��ȭ�� : ");
					String name = scan.nextLine();
					while(true) {
						dos.writeUTF(name);  // ��ȭ�� ����
						
						// ��ȭ���� �ߺ� ���θ� feedBack���� �޴´�.
						String feedBack = dis.readUTF();
						if("�̸��ߺ�".equals(feedBack)) { // ��ȭ���� �ߺ��� ��...
							System.out.println(name + "�� ��ȭ���� �ߺ��˴ϴ�.");
							System.out.println("�ٸ� ��ȭ���� �Է��ϼ���.");
							System.out.println("��ȭ��: ");
							name = scan.nextLine();
						}else {  // �ߺ����� ���� ��...
							this.name = name;
							System.out.println(name + " �̸����� ��ȭ�濡 �����߽��ϴ�.");
							break;  // �ݺ��� Ż��
						}
						
					}  // while�� ��..
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} // ������ ��...
		
		@Override
		public void run() {
			try {
				while(dos!=null) {
					// Ű���� �Է��� �޽����� ������ �����Ѵ�.
					dos.writeUTF("[" + name + "] " + scan.nextLine());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	} // ���ۿ� ������ ��...
	
	// �޽��� ���ſ� ������
	class ClientReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		
		// ������
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} // ������ ��..
		
		@Override
		public void run() {
			try {
				while(dis!=null) {
					// �����κ��� ���� �޽����� ȭ�鿡 ����Ѵ�.
					System.out.println(dis.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
