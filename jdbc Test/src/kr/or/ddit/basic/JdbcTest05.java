package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 	LPROD테이블에 새로운 데이터 추가하기
 	
 	상품분류코드(lprod_gu), 상품분류명(lprod_nm)은 직접 입력 받아서 처리하고, 
 	Lprod_ID는 현재의 Lprod_ID중 제일 큰 값보다 1 크게 한다.
 	그리고 입력받은 상품분류코드(lprod_gu)가 이미 등록되어 있으면 다시 입력받아서 처리한다.
 	
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
			
			
			System.out.print("상품분류명을 입력하세요.");
			String nm = scan.next();
			
			String sql = "insert into lprod"
					+ "(lprod_id, lprod_gu, lprod_nm)"
					+ "values( ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			System.out.println("반환값: " + cnt);
			
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
		
		System.out.print("상품 분류코드를 입력하세요");
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
					System.out.println("이미 등록되어 있는 번호입니다.");
					System.out.print("상품 분류코드를 입력하세요");
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
