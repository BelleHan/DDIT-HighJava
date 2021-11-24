package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

// JDBC����̹��� �ε��ϰ� Connection��ü�� �����Ͽ� ��ȯ�ϴ� �޼���� ����

//dbinfo.properties������ ������ �о �����ϴ� ���
//���2) ResourceBundle��ü �̿��ϱ�
public class DBUtil3 {
	
	static final Logger logger = Logger.getLogger(DBUtil3.class);
	
	static ResourceBundle bundle;  // ResourceBundle��ü ���� ����
	
	static {  // static �ʱ�ȭ �� - DBUtilŬ������ ȣ���ϸ� ó�� �� �ѹ��� ����ȴ�.
		bundle = 
			ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		logger.info("properties������ �̿��� ResourceBundle��ü ����");
		
		try {
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			logger.info("DB ����̹� �ε� ����!!!");
			
		} catch (ClassNotFoundException e) {
			//System.out.println("����̹� �ε� ����~~~~");
			logger.error("����̹� �ε� ���� ~~~ : " + e);
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
//			return DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe",
//					"HHS96", "java");
			conn = DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
			logger.info("DB �ý��ۿ� ���� ����!!");
			return conn;
			
		} catch (SQLException e) {
			//System.out.println("����Ŭ DB ���� ����~~~");
			logger.error("DB �ý��ۿ� ���� ����~~~ : " + e);
			return null;
		}
	}
}
