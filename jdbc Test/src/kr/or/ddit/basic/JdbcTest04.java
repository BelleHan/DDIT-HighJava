package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"HHS96", "java");
			
			System.out.println("���¹�ȣ ���� �߰��ϱ�");
			System.out.print("���¹�ȣ: ");
			String bankNo = scan.next();
			
			System.out.print("�� �� ��: ");
			String bankName = scan.next();
			
			System.out.print("�����ָ�: ");
			String bankUser = scan.next();
			
			/*
			// Statement��ü�� �̿��ؼ� ������ �߰��ϱ�
			String sql = "insert into bankinfo"
					+ "(bank_no, bank_name, bank_user_name, bank_date)"
					+ "values( '" + bankNo + "', '" + bankName 
					+ "', '" + bankUser + "', sysdate)";
			
			stmt = conn.createStatement();
			
			//SQL�� �����ϱ�
			// 1) select���� ��� : executeQuery() �޼��� ���
			//		==> ��ȯ�� : ResultSet��ü
			// 2) select���� �ƴ� ��� : executeUpdate() �޼��� ���
			//		==> ��ȯ�� : �۾��� ������ ���ڵ� ��
			int cnt = stmt.executeUpdate(sql);
			*/
			
			// PreparedStatement��ü�� �̿��Ͽ� ó���ϱ�
			
			// �������� �ۼ��� �� �������� �����Ͱ� �� �ڸ��� ����ǥ(?)�� �־ �ۼ��Ѵ�.
			String sql = "insert into bankinfo"
					+ "(bank_no, bank_name, bank_user_name, bank_date)"
					+ "values( ?, ?, ?, sysdate)";
			
			// PreparedStatement��ü ����
			//		==> ����� �������� �Ű������� �Ѱ��ش�.
			pstmt = conn.prepareStatement(sql);
			
			// �������� ����ǥ(?)�ڸ��� �� �����͸� �����Ѵ�.
			// ����) pstmt.set�ڷ����̸�(����ǥ��ȣ, ������);
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, bankUser);
			
			// ������ ������ �Ϸ�Ǹ� �������� �����Ѵ�.
			int cnt = pstmt.executeUpdate();
			
			System.out.println("��ȯ��: " + cnt);
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
	}
}
