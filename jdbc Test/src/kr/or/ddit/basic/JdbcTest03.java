package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 	����1) Lprod_id���� 2�� �Է� �޾Ƽ� �� �� �� ���� ������ ū �� ������ �ڷ���� ����Ͻÿ�.
 	
 */
public class JdbcTest03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("ù��° Lprod_id�� �Է�: ");
		int input1 = scan.nextInt();
		
		System.out.print("�ι�° Lprod_id�� �Է�: ");
		int input2 = scan.nextInt();
		
		int max, min;
		if(input1 > input2) {
			max = input1;
			min = input2;
		}else {
			max = input2;
			min = input1;
		}
		
		System.out.println();
		
		// DB �۾��� �ʿ��� ���� ����
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HHS96", "java" );
			//String sql = "select * from lprod where lprod_id >= " + min + "and lprod_id <=" + max;
			String sql = "select * from lprod where lprod_id between " + min + " and " + max;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println(" -- SQL�� ó�� ��� -- ");
			
			while(rs.next()) {
				System.out.println("Lprod_id: " + rs.getInt(1));
				System.out.println("Lprod_gu: " + rs.getString(2));
				System.out.println("Lprod_nm: " + rs.getString(3));
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
