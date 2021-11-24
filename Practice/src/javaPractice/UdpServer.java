package javaPractice;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer {

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(8888);
			DatagramPacket inpacket, outpacket;
			
			System.out.println("서버 실행중");
			
			while(true) {
				byte[] bMsg = new byte[512];
				
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				socket.receive(inpacket);
				
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				
				String msg = new String(inpacket.getData(), 0, inpacket.getLength());
				
				if("/end".equals(msg)) {
					break;
				}
				
				System.out.println("상대방이 보낸 메시지: " + msg);
				
				byte[] sendMsg = msg.getBytes();
				
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				socket.send(outpacket);
				System.out.println("송신 완료...");
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
