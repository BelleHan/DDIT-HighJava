package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
	LPROD테이블에 새로운 데이터 추가하기
	
	상품분류코드(lprod_gu), 상품분류명(lprod_nm)은 직접 입력 받아서 처리하고, 
	Lprod_ID는 현재의 Lprod_ID중 제일 큰 값보다 1 크게 한다.
	그리고 입력받은 상품분류코드(lprod_gu)가 이미 등록되어 있으면 다시 입력받아서 처리한다.
	
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
			
			// Lprod_ID는 현재의 Lprod_ID중 제일 큰 값보다 1 크게 한다.
			String sql = "select nvl(max(lprod_id),0) maxnum from lprod";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			int max = 0;
			if(rs.next()) {  // rs.getInt()를 사용하기 위해서는 반드시 rs.next()를 써야함. 
							 // 검색 결과가 1개의 레코드일 경우에는 if문으로 비교해도 된다.
				//max = rs.getInt(1);		// 컬럼 번호 이용
				//max = rs.getInt("nvl(max(lprod_id),0)");  // 식 내용 이용
				max = rs.getInt("maxnum");		// 컬럼의 alias로 이용
			}
			max++;		
			
			// 입력받은 상품분류코드(lprod_gu)가 이미 등록되어 있으면 다시 입력받아서 처리한다.
			String gu; 		// 상품분류 코드가 저장될 변수 선언
			int count = 0;  // 입력한 상품분류 코드의 개수가 저장될 변수 선언
			
			String sql2 = "select count(*) cnt from lprod where lprod_gu=?";
			pstmt = conn.prepareStatement(sql2);
			
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력: ");
				gu = scan.next();
				
				pstmt.setString(1, gu);  // 쿼리문에 들어갈 데이터 셋팅
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count>0) {
					System.out.println("입력한 상품 분류코드 " + gu + 
							"는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요.");
				}
				
			} while(count>0);
			
			//----------------------------------------
			System.out.print("상품 분류명(LPROD_NM) 입력: ");
			String nm = scan.next();
			
			String sql3 = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
					+ "values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, max);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("등록 성공!!!");
			}else {
				System.out.println("등록 실패~~~");
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
