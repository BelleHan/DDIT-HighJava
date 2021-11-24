package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 	����) ����ڷκ��� Lprod_id���� �Է¹޾� �Է��� ������
 		 Lprod_id���� ū �ڷḦ ����Ͻÿ�.
 */
public class JdbcTest02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Lprod_id�� �Է�: ");
		int input = scan.nextInt();
		System.out.println();
		
		// DB �۾��� �ʿ��� ���� ����
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HHS96", "java" );
			
			String sql = "select * from lprod where lprod_id > " + input ;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println(" -- SQL�� ó�� ��� -- ");
			
			while(rs.next()) {
				System.out.println("ID : " + rs.getInt(1));
				System.out.println("GU : " + rs.getString(2));
				System.out.println("NM: " + rs.getString(3));
				System.out.println("-----------------------------");
				
			}
			System.out.println("��� ��...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally { 
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(rs!=null) try { stmt.close(); } catch(SQLException e) {}
			if(rs!=null) try { conn.close(); } catch(SQLException e) {}
		}
	}
}
