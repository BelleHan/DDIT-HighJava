package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// JDBC����̹��� �ε��ϰ� Connection��ü�� �����Ͽ� ��ȯ�ϴ� �޼���� ����

//dbinfo.properties������ ������ �о �����ϴ� ���
//���2) ResourceBundle��ü �̿��ϱ�
public class DBUtil3 {
	static ResourceBundle bundle;  // ResourceBundle��ü ���� ����
	
	static {  // static �ʱ�ȭ �� - DBUtilŬ������ ȣ���ϸ� ó�� �� �ѹ��� ����ȴ�.
		bundle = 
			ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		try {
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����~~~~");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
//			return DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe",
//					"HHS96", "java");
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
			
		} catch (SQLException e) {
			System.out.println("����Ŭ DB ���� ����~~~");
			return null;
		}
	}
}
