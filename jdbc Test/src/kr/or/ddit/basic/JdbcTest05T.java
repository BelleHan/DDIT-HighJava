package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
	LPROD���̺� ���ο� ������ �߰��ϱ�
	
	��ǰ�з��ڵ�(lprod_gu), ��ǰ�з���(lprod_nm)�� ���� �Է� �޾Ƽ� ó���ϰ�, 
	Lprod_ID�� ������ Lprod_ID�� ���� ū ������ 1 ũ�� �Ѵ�.
	�׸��� �Է¹��� ��ǰ�з��ڵ�(lprod_gu)�� �̹� ��ϵǾ� ������ �ٽ� �Է¹޾Ƽ� ó���Ѵ�.
	
*/

public class JdbcTest05T {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe",
//					"HHS96", "java");
			
			conn = DBUtil.getConnection();
			
			// Lprod_ID�� ������ Lprod_ID�� ���� ū ������ 1 ũ�� �Ѵ�.
			String sql = "select nvl(max(lprod_id),0) maxnum from lprod";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			int max = 0;
			if(rs.next()) {  // rs.getInt()�� ����ϱ� ���ؼ��� �ݵ�� rs.next()�� �����. 
							 // �˻� ����� 1���� ���ڵ��� ��쿡�� if������ ���ص� �ȴ�.
				//max = rs.getInt(1);		// �÷� ��ȣ �̿�
				//max = rs.getInt("nvl(max(lprod_id),0)");  // �� ���� �̿�
				max = rs.getInt("maxnum");		// �÷��� alias�� �̿�
			}
			max++;		
			
			// �Է¹��� ��ǰ�з��ڵ�(lprod_gu)�� �̹� ��ϵǾ� ������ �ٽ� �Է¹޾Ƽ� ó���Ѵ�.
			String gu; 		// ��ǰ�з� �ڵ尡 ����� ���� ����
			int count = 0;  // �Է��� ��ǰ�з� �ڵ��� ������ ����� ���� ����
			
			String sql2 = "select count(*) cnt from lprod where lprod_gu=?";
			pstmt = conn.prepareStatement(sql2);
			
			do {
				System.out.print("��ǰ �з� �ڵ�(LPROD_GU) �Է�: ");
				gu = scan.next();
				
				pstmt.setString(1, gu);  // �������� �� ������ ����
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count>0) {
					System.out.println("�Է��� ��ǰ �з��ڵ� " + gu + 
							"�� �̹� ��ϵ� �ڵ��Դϴ�.");
					System.out.println("�ٽ� �Է��ϼ���.");
				}
				
			} while(count>0);
			
			//----------------------------------------
			System.out.print("��ǰ �з���(LPROD_NM) �Է�: ");
			String nm = scan.next();
			
			String sql3 = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
					+ "values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, max);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("��� ����!!!");
			}else {
				System.out.println("��� ����~~~");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		
	}
}
