package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		// InetAddress Ŭ���� ==> IP�ּҸ� �ٷ�� ���� Ŭ����
		
		// www.naver.com�� IP���� ��������
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		
		System.out.println("Host Name: " + naverIp.getHostName());
		System.out.println("Host Address: " + naverIp.getHostAddress()); 
		System.out.println("toString: " + naverIp.toString());
		System.out.println("------------------------------------------");
		
		// �ڽ��� ��ǻ�ͷ� IP���� ��������
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("Host Name: " + localIp.getHostName());
		System.out.println("Host Address: " + localIp.getHostAddress());
		System.out.println("toString: " + localIp.toString());
		System.out.println("------------------------------------------");
		
		// IP�ּҰ� �������� Host�� ���� ��������
		InetAddress[] ips = InetAddress.getAllByName("www.naver.com");
		for(InetAddress ip : ips) {
			System.out.println(ip.toString());
		}
  
	}

}
