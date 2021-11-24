package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 	UDP��� : �񿬰� ����, �ŷڼ��� ����., �����Ͱ� ������� �����Ѵٴ� ������ ����.
 		         �׷����� TCP��ĺ��� �ӵ��� ������.
 		         
 	- TCP����� ��Ʈ���� �̿��ؼ� �ۼ��������� 
 	  UDP����� �����ͱ׷��� �̿��ؼ� �ۼ����Ѵ�.
 	  
 	- DatagramSocket��ü�� DatagramPacket��ü�� �̿��ؼ� ����Ѵ�.
 	  * DatagramSocket : �������� �ۼ��Ű� ���õ� �۾��� �����Ѵ�.(��ü��)
 	  * DatagramPacket : �ְ� �޴� �����Ϳ� ���õ� �۾��� �����Ѵ�.(����, ����)
 	  * 		==> �۽��� ���� �����ڿ� ������ ���� �����ڸ� ���� �����Ѵ�.
 */

public class UdpServer {

	public static void main(String[] args) {
		try {
			// ����� ��Ʈ��ȣ�� �����Ͽ� ���ϰ�ü�� �����Ѵ�.
			DatagramSocket socket = new DatagramSocket(8888);
			
			// ���ſ� ��Ŷ������ �۽ſ� ��Ŷ���� ����
			DatagramPacket inpacket, outpacket;
			
			System.out.println("���� ������...\n");
					
			while(true) {
				// �����Ͱ� ����� byte�� �迭 ����
				byte[] bMsg = new byte[512];
				
				// ���ſ� ��Ŷ��ü ����
				//  ==> �����Ͱ� ����� byte�� �迭, �迭�� ���̸� �����ڿ� �־ �����Ѵ�.
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// �����͸� �����Ѵ�.  ==> receive()�޼��� �̿�
				// �� �޼���� �����Ͱ� �� ������ ��ٸ���.
				// ���ŵ� �������� ��Ŷ������ ������ ��Ŷ������ ����ȴ�.
				socket.receive(inpacket);
				
				// ���Ź��� ��Ŷ���� ������ �ּ�, ��Ʈ��ȣ���� ������ �� �� �ִ�.
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				
				System.out.println("������ IP����: " + address);
				System.out.println("������ port��ȣ: " + port);
				
				// ������ ���� �޽��� ����ϱ�
				// inpacket.getLength() ==> ���� �о�� �������� ���̸� ��ȯ
				// inpacket.getData(), �Ǵ� ���ſ� ��Ŷ�� ������ �� ������ byte�� �迭
				// 		==> ���� �о�� �����Ͱ� ��ȯ�ȴ�.
				
				// ���Ź��� ������(byte�� �迭)�� ���ڿ��� ��ȯ�ϱ�
				//String msg = new String(bMsg, 0, inpacket.getLength());
				String msg = new String(inpacket.getData(), 0, inpacket.getLength());
				
				if("/end".equals(msg)) {
					break;
				}
				
				System.out.println("������ ���� �޽���: " + msg);
				System.out.println("--------------------------------------");
				
				//---------------------------------------------------------------
				
				// ���濡�� �޽��� ������ (���Ź��� �޽����� �״�� �۽��ϱ�)
				
				// �۽��� �޽����� byte�迭�� ��ȯ�Ѵ�.
				byte[] sendMsg = msg.getBytes();
				
				// �۽ſ� ��Ŷ��ü ����
				//		==> �����ڿ� ������ ���� ������ �����ؼ� �����Ѵ�.
				//		==> ������ �����Ͱ� ����� byte�� �迭, ������ �ڷ��� ����(�迭�� ����), 
				//			������ �ּ�����, ������ ��Ʈ��ȣ
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				// �۽��ϱ� ==> send()�޼��带 ����Ѵ�.
				socket.send(outpacket);
				System.out.println("�۽� �Ϸ�...\n");
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
