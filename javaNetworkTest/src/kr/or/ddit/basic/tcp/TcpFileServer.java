package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 	��Ʈ��ũ�� ����Ǹ� Ŭ���̾�Ʈ�� 'd:/d_other'������ �ִ� '���.jpg'������
 	������ �����Ѵ�.
 	������ ���� ���� ������ 'd:/d_other/uploadFile'������ ���� �̸����� �����Ѵ�.
 	( Ŭ���̾�Ʈ : ������ �о �������� ����Ѵ�.
 	    ���� : ���Ͽ��� �о ���Ϸ� ����Ѵ�. )
 */
public class TcpFileServer {
	private ServerSocket server;
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private DataInputStream dis;
	
	public static void main(String[] args) throws IOException {
		new TcpFileServer().serverStart();				
	}
	
	private void serverStart() {
		File saveDir = new File("d:/d_other/uploadFile");
		
		if(!saveDir.exists()) {  // ������ ������ ������ ���� �����Ѵ�.
			saveDir.mkdir();
		}
		
		try {
			server = new ServerSocket(7777);
			System.out.println("������ �غ�Ǿ����ϴ�.");
			
			socket = server.accept();  // Ŭ���̾�Ʈ�� ��û�� ��ٸ���.
			
			System.out.println("���� �ٿ�ε� ����...");
			
			InputStream in = socket.getInputStream();
			
			// ������ �̿��� �Է¿� ��Ʈ�� ��ü ����
			dis = new DataInputStream(in);
			
			// ù��°�� ���ŵ� ���� �̸��� �о �����Ѵ�.
			String fileName = dis.readUTF();  
			
			// ������ ���� ��ġ�� ���ϸ��� �����Ͽ� File��ü ����
			File saveFile = new File(saveDir, fileName);
			
			// ������ �Է¿� ��Ʈ���� �̿��� ���� ��Ʈ�� ��ü ����
			bis = new BufferedInputStream(in);
			
			// ���� ����� ��� ��Ʈ�� ��ü ����
			bos = new BufferedOutputStream(new FileOutputStream(saveFile));
			
			byte[] temp = new byte[1024];
			int len = 0;
			// �������� ���� �����͸� ���Ϸ� �����Ѵ�.
			while((len = bis.read(temp))>0) {
				bos.write(temp, 0, len);
			}
			bos.flush();
			System.out.println("���� �ٿ�ε� �Ϸ�...");
			
		} catch (Exception e) {
			System.out.println("���� �ٿ�ε� ����!!!\n" + e.getMessage());
		} finally {
			// ����ߴ� ��Ʈ���� ���� �ݱ�
			if(dis!=null) try { dis.close(); }catch(IOException e) {}
			if(bis!=null) try { bis.close(); }catch(IOException e) {}
			if(bos!=null) try { bos.close(); }catch(IOException e) {}
			if(socket!=null) try { socket.close(); }catch(IOException e) {}
			if(server!=null) try { server.close(); }catch(IOException e) {}
		}
		
	}
}
