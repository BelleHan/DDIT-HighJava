package javaPractice;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		DatagramPacket inpacket, outpacket;
		
		byte[] bMsg = new byte[512];
		
		try {
			DatagramSocket socket = new DatagramSocket();
			
			InetAddress address = InetAddress.getByName("127.0.0.1");
			
			while(true) {
				System.out.print("보낼 메세지 입력: ");
				String msg = scan.nextLine();
				
				outpacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, 8888);
				
				socket.send(outpacket);
				
				if("/end".equals(msg)) {
					break;
				}
				
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
