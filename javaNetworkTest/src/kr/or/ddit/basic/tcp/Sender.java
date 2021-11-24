package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

// �� Ŭ������ ������ ���ؼ� �޽����� ������ ������ ����ϴ� ������ Ŭ����
public class Sender extends Thread{
	private Socket socket;
	private DataOutputStream dos;
	private String name;
	private Scanner scan;
	
	// ������
	public Sender(Socket socket) {
		this.socket = socket;
		scan = new Scanner(System.in);
		
		System.out.print("�̸� �Է� >> ");
		name = scan.nextLine();
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Override
	public void run() {
		while(dos!=null) {
			try {
				dos.writeUTF(name + " : " + scan.nextLine());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
