package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 	LPROD���̺� ���ο� ������ �߰��ϱ�
 	
 	��ǰ�з��ڵ�(lprod_gu), ��ǰ�з���(lprod_nm)�� ���� �Է� �޾Ƽ� ó���ϰ�, 
 	Lprod_ID�� ������ Lprod_ID�� ���� ū ������ 1 ũ�� �Ѵ�.
 	�׸��� �Է¹��� ��ǰ�з��ڵ�(lprod_gu)�� �̹� ��ϵǾ� ������ �ٽ� �Է¹޾Ƽ� ó���Ѵ�.
 	
 */
public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		JdbcTest05 test = new JdbcTest05();
		int id = test.sql1();
		String gu = test.sql2();
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"HHS96", "java");
			
			
			System.out.print("��ǰ�з����� �Է��ϼ���.");
			String nm = scan.next();
			
			String sql = "insert into lprod"
					+ "(lprod_id, lprod_gu, lprod_nm)"
					+ "values( ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			System.out.println("��ȯ��: " + cnt);
			
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
			
		
	}
	public int sql1() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		int id = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HHS96", "java");
			String sql = "select max(lprod_id)+1 from lprod";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				id = rs.getInt(1);		
			}		

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(rs!=null) try { stmt.close(); } catch(SQLException e) {}
			if(rs!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return id;
		
	}
	
	public String sql2() {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.print("��ǰ �з��ڵ带 �Է��ϼ���");
		String gu = scan.next();
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HHS96", "java");
			while (true) {
				String sql = "select count(*) from lprod where lprod_gu = '" + gu + "'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
			if(rs.next()) {
					if(rs.getInt(1) == 0) {
						break;
					}
					System.out.println("�̹� ��ϵǾ� �ִ� ��ȣ�Դϴ�.");
					System.out.print("��ǰ �з��ڵ带 �Է��ϼ���");
					gu = scan.next();
				}
				
			}		

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(rs!=null) try { stmt.close(); } catch(SQLException e) {}
			if(rs!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return gu;
		
	}
}
