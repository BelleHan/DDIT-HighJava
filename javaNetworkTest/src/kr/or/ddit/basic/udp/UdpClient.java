package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// �۽ſ�, ���ſ� ��Ŷ ��ü���� ����
		DatagramPacket inpacket, outpacket;
		
		// ���Ź��� �����Ͱ� ����� byte�� �迭 ����
		byte[] bMsg = new byte[512];
		
		try {
			// ���� ��ü ����
			DatagramSocket socket = new DatagramSocket();
			
			// ������ ���� �ּ� ���� ���ϱ� (������ ������ �ּ� ���� ���ϱ�)
			InetAddress address = InetAddress.getByName("127.0.0.1");
			
			while(true) {
				// ������ �޽��� �Է�
				System.out.print("���� �޽��� �Է�: ");
				String msg = scan.nextLine();
				
				// ������ ��Ŷ��ü ����
				outpacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, 8888);
				
				// ����
				socket.send(outpacket);
				
				if("/end".equals(msg)) {  // �޽��� ���� ���� Ȯ���ϱ�
					break;
				}
				
				// -------------------------------------------------
				// �������� �� �����͸� �޾Ƽ� ����ϱ�
				// ���ſ� ��Ŷ��ü ����
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// �����ϱ�
				socket.receive(inpacket);
				
				System.out.println("������ ���� �޽���: " + new String(bMsg, 0, inpacket.getLength()));
				System.out.println();
				
			}
			System.out.println("��� ��...");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
