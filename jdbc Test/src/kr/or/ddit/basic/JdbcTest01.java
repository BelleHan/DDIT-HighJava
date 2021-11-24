package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 	JDBC(Java DataBase Connectivity)���̺귯���� �̿��Ͽ� DB�ڷ� ó���ϱ�
 	
 	- �����ͺ��̽� ó�� ����
 	1. ����̹� �ε� ==> ���̺귯���� ����� �� �ְ� �޸𸮷� �о� ���̴� �۾�
 		Class.forName("oracle.jdbc.driver.OracleDriver");
 		==> JDBC API ���� 4�̻󿡼��� ������ �� �ִ�.
 		==> �����ϸ� getConnection()�޼��忡�� �ڵ����� �ε����ش�.
 		
 	2. DB�� �����ϱ� ==> ������ �Ϸ�Ǹ� Connection��ü�� ��ȯ�ȴ�.
 		DriverManager.getConnection()�޼��带 �̿��Ѵ�.
 	
 	3. ���� ==> SQL������ DB������ ������ ����� ���´�.
 		(Statement��ü�� PreparedStatement��ü�� �̿��Ͽ� �۾��Ѵ�.)"HHH 
 		
 	4. ��� ó�� ==> ���� ����� �޾Ƽ� ���ϴ� �۾��� �����Ѵ�.
 	  1) SQL���� select���� ��쿡�� select�� ����� ResultSet��ü�� ����Ǿ� ��ȯ�ȴ�.
 	  2) SQL���� select���� �ƴ� ��쿡�� �������� ��ȯ�ȴ�.
 	  	  (�� �������� �ش� SQL���� ���࿡ ������ ���ڵ� ���� ���Ѵ�.)
 	
 	5. ����� �ڿ��� �ݳ��Ѵ�. ==> �� ��ü�� close()�޼��带 �����Ѵ�.
 	
 */
public class JdbcTest01 {

	public static void main(String[] args) {
		// DB�۾��� �ʿ��� ������ ����
		Connection conn = null;
		Statement stmt = null;
		//PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB ����
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",  //localhost�� ����Ŭ�� ��ġ�� ��ǻ���� IP�ּ� �ڸ�
					"HHS96", "java");
			
			// 3. ����
			// 3-1. SQL�� �ۼ�
			String sql = "select * from lprod";
			
			// 3-2. Statement��ü �Ǵ� PreparedStatement��ü ����
			// 		(Connection��ü�� �̿��ؼ� �����Ѵ�.)
			stmt = conn.createStatement();
			
			// 3-3. SQL�� ���� ( ������ SQL���� select���̱� ������ ����� ResultSet��ü�� ����Ǿ� ��ȯ�ȴ�.)
			rs = stmt.executeQuery(sql);
			
			// 4. ��� ó�� ==> �� ���ڵ徿 ȭ�鿡 ����ϱ�
			System.out.println(" -- SQL�� ó�� ��� -- ");
			
			// ResultSet�� ����� �����͸� ���ʷ� ���������� �ݺ����� next()�޼��带 �̿��Ѵ�.
			
			// rs.next() ==> ResultSet��ü�� �����͸� ����Ű�� �����͸�
			//				������° ���ڵ� ��ġ�� �̵� ��Ű�� �� ���� �����Ͱ� ������
			//				true�� ��ȯ�ϰ� ������ false�� ��ȯ�Ѵ�.
			while(rs.next()) {  
				// �����Ͱ� ����Ű�� ���� �����͸� ��������
				// ����1) rs.get�ڷ����̸�("�÷���");
				// ����2) rs.get�ڷ����̸�(�÷���ȣ); ==> �÷���ȣ�� 1������ �����Ѵ�. (db�� 0������ ó������ �ʰ� 1������ ó����)
				// ����3) rs.get�ڷ����̸�("�÷��� alias��");
				System.out.println("Lprod_id: " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu: " + rs.getString(2));
				System.out.println("Lprod_nm: " + rs.getString("LPROD_NM"));
				System.out.println("----------------------------------");
			}
			System.out.println("��� ��...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 5. ����ߴ� �ڿ� �ݳ�
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(rs!=null) try { stmt.close(); } catch(SQLException e) {}
			if(rs!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
	}
}
