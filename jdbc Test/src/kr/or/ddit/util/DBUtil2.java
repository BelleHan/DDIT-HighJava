package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// JDBC����̹��� �ε��ϰ� Connection��ü�� �����Ͽ� ��ȯ�ϴ� �޼���� ����

// dbinfo.properties������ ������ �о �����ϴ� ���
// ���1) Properties��ü �̿��ϱ�
public class DBUtil2 {
	static Properties prop;  // Properties��ü���� ����
	
	static { // static �ʱ�ȭ ��
		prop = new Properties();  // Properties��ü ����
		
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(f);  // �Է½�Ʈ�� ��ü ����
			prop.load(fin);  // ���� ���� �о�� �߰��ϱ�
				
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
			
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����~~~~");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("���� ���� ����� ����...");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
//			return DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe",
//					"HHS96", "java");
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"), 
					prop.getProperty("pass"));
			
		} catch (SQLException e) {
			System.out.println("����Ŭ DB ���� ����~~~");
			return null;
		}
	}
}
