package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
 	Ȯ���ڰ� properties�� ������ ������ �о 
 	Properties��ü�� �����ϱ�
 */
public class PropertiesTest {

	public static void main(String[] args) {
		// �о�� ������ ������ Properties��ü ����
		Properties prop = new Properties();
		
		// �о�� ���ϸ��� �̿��� File��ü ����
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		
		try {
			// ���� ������ �о�� �Է¿� ��Ʈ�� ��ü ����
			fin = new FileInputStream(f);
			
			// �Է� ��Ʈ���� �̿��Ͽ� ���� ������ �о�� Properties��ü�� �����Ѵ�.
			// ���� ������ �о�� key���� value���� �и��� �� Properties��ü��
			// �߰��Ѵ�.
			prop.load(fin);
			
			// �о�� ������ ����� ����
			System.out.println("driver : " + prop.getProperty("driver"));
			System.out.println("url : " + prop.getProperty("url"));
			System.out.println("user : " + prop.getProperty("user"));
			System.out.println("pass : " + prop.getProperty("pass"));
			
		} catch (IOException e) {
			System.out.println("����� ����....");
			e.printStackTrace();
		} finally {
			if(fin!=null)try {fin.close();}catch(IOException e) {}
		}
		
	}

}
