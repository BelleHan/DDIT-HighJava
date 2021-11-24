package kr.or.ddit.basic.tcp;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;

/*
	��Ʈ��ũ�� ����Ǹ� Ŭ���̾�Ʈ�� 'd:/d_other'������ �ִ� '���.jpg'������
	������ �����Ѵ�.
	������ ���� ���� ������ 'd:/d_other/uploadFile'������ ���� �̸����� �����Ѵ�.
	( Ŭ���̾�Ʈ : ������ �о �������� ����Ѵ�.
	    ���� : ���Ͽ��� �о ���Ϸ� ����Ѵ�. )
*/

public class TcpFileClient {
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private DataOutputStream dos;

	public static void main(String[] args) {
		new TcpFileClient().ClientStart();	
	}
	
	// ������ ������ �����ؼ� ��ȯ�ϴ� �޼���
	private File getUploadFile() {
		JFileChooser chooser = new JFileChooser();
		
		chooser.setCurrentDirectory(new File("d:/d_other"));
		
		File selectedFile = null;
		int result = chooser.showOpenDialog(new Panel());
		
		if(result == JFileChooser.APPROVE_OPTION) {
			selectedFile = chooser.getSelectedFile();
		}
		
		return selectedFile;
		
	}
	
	private void ClientStart() {
		// ������ ������ �̿��� File��ü ����
		//File file = new File("d:/d_other/���.jpg");
		
		File file = getUploadFile();
		
		if(file==null ) {
			System.out.println("������ ������ ���õ��� �ʾҽ��ϴ�.");
			System.out.println("���� �۾� ���...");
			return;
		}
		
		String fileName = file.getName();  // ���� �̸� ���ϱ�
		if(!file.exists()) {  // ������ ������ ������...
			System.out.println(fileName + " ������ �����ϴ�.");
			System.out.println("���� �۾� ���...");
			return;
		}
		
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("���� ���� ����...");
			
			OutputStream out = socket.getOutputStream();
			
			dos = new DataOutputStream(out);
			
			// ������ �����ϸ� ù��°�� ������ ������ �̸��� �����Ѵ�.
			dos.writeUTF(fileName);
			
			// ������ ������ �б� ���� �Է¿� ��Ʈ�� ��ü ����
			bis = new BufferedInputStream(new FileInputStream(file));
			
			// ������ ������ ��¿� ��Ʈ�� ��ü ����(������ ��¿� ��Ʈ�� ���)
			bos = new BufferedOutputStream(out);
			
			byte[] temp = new byte[1024];
			int len = 0;
			while((len = bis.read(temp))>0) {
				bos.write(temp, 0, len);
			}
			bos.flush();
			System.out.println("���� ���� �Ϸ�...");
			
			
		} catch (Exception e) {
			System.out.println("���� ���� ����!!\n " + e.getMessage() );
		} finally {
			// ����ߴ� ��Ʈ���� ���� �ݱ�
			if(dos!=null) try { dos.close(); }catch(IOException e) {}
			if(bis!=null) try { bis.close(); }catch(IOException e) {}
			if(bos!=null) try { bos.close(); }catch(IOException e) {}
			if(socket!=null) try { socket.close(); }catch(IOException e) {}
		}
		
	}
}
