package kr.or.ddit.basic;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 	회원 관리 프로그램을 작성하시오. (MYMEMBER 테이블 이용)
 	
 	아래의 메뉴의 기능을 모두 구현하시오. (CRUD기능 구현하기)
 	
 	메뉴예시)
 		-- 작업 선택 --
 		1. 자료 추가
 		2. 자료 삭제
 		3. 자료 수정
 		4. 전체 자료 출력
 		5. 자료 수정2
 		0. 작업 끝.
 	---------------------
 	작업선택 > 
 	
 	처리조건)
 	1) 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다.)
 	2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
 	3) 자료 수정에서는 '회원ID'는 변경하지 않는다.
 	
 	
 	4) 자료 수정 ==> 원하는 항목만 선택해서 수정되도록 한다.
 	
 	   MVC패턴에 대하여 조사해오기
 	
 */
public class JdbcTest06 {
	Scanner scan = new Scanner(System.in);
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public static void main(String[] args) {
		JdbcTest06 test = new JdbcTest06();
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("    -- 작업 선택 --");
			System.out.println("    1. 자료 추가");
			System.out.println("    2. 자료 삭제");
			System.out.println("    3. 자료 수정");
			System.out.println("    4. 전체 자료 출력");
			System.out.println("    5. 자료 수정 2");
			System.out.println("    0. 작업 끝.");
			System.out.println("---------------------");
			System.out.print("작업선택 > ");
			
			int choice = scan.nextInt();
			
			switch(choice) {
			
				case 1 : test.insert(); break;
				case 2 : test.delete(); break;
				case 3 : test.update(); break;
				case 4 : test.select(); break;
				case 5 : test.update2(); break;
				case 0 : System.out.println("프로그램을 종료합니다");
				System.exit(0);
				default : System.out.println("잘못 입력하셨습니다.");
			
			}
			
		}
	
	}
	
	public void insert() {
		try {
			conn = DBUtil.getConnection();
			
			String id = "";
			int count = 0;
			
			String sql2 = "select count(*) cnt from mymember where mem_id=?";
			pstmt = conn.prepareStatement(sql2);
			
			do {
				System.out.print("ID를 입력하세요");
				id = scan.next();
				
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count>0) {
					System.out.println("이미 존재하는 아이디입니다.");
					System.out.println("다시 입력하세요.");
				}
				
			} while (count>0);
			
			System.out.print("비밀번호를 입력하세요");
			String pass = scan.next();
			
			System.out.print("이름을 입력하세요");
			String name = scan.next();
			
			System.out.print("전화번호를 입력하세요");
			String tel = scan.next();
			
			System.out.print("주소를 입력하세요");
			String addr = scan.next();
			
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
					+ "values(?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("등록 성공!");
			}else {
				System.out.println("등록 실패~");
			}
			System.out.println();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
	}
	
	public void delete() {
		try {
			conn = DBUtil.getConnection();
			
			System.out.print("삭제할 ID를 입력하세요");
			String id = scan.next();
			
			String sql = "delete from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("삭제 성공!");
			}else {
				System.out.println("삭제 실패~");
			}
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
	}
	
	public void update() {
		try {
			conn = DBUtil.getConnection();
			
			System.out.print("수정할 ID를 입력하세요");
			String id = scan.next();
			
			System.out.print("수정할 비밀번호를 입력하세요");
			String pass = scan.next();
			
			System.out.print("수정할 이름을 입력하세요");
			String name = scan.next();
			
			System.out.print("수정할 전화번호를 입력하세요");
			String tel = scan.next();
			
			System.out.print("수정할 주소를 입력하세요");
			String addr = scan.next();
			
			String sql = "update mymember set mem_pass = ?, "
					+ "mem_name = ?, mem_tel = ?, "
					+ "mem_addr = ? where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setString(5, id);
		
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("수정 성공!");
			}else {
				System.out.println("수정 실패~");
			}
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
	}
	
	public void select() {
		
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("id: " + rs.getString(1));
				System.out.println("pass: " + rs.getString(2));
				System.out.println("name: " + rs.getString(3));
				System.out.println("tel: " + rs.getString(4));
				System.out.println("addr: " + rs.getString(5));
				System.out.println("-----------------------------");
			}
			System.out.println("출력 끝...");
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(rs!=null) try { stmt.close(); } catch(SQLException e) {}
			if(rs!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
	}
	
	public void update2() {
		try {
			conn = DBUtil.getConnection();
			
			System.out.print("수정할 아이디를 입력하세요. ");
			String id = scan.next();
			
			System.out.println("수정할 항목을 선택하세요");
			System.out.println(" 1.비밀번호  2.이름  3.전화번호  4.주소");
			System.out.print(" 항목선택 >> ");
			int num = scan.nextInt();
			
			String menu = "";
			
			switch(num) {
				case 1 : menu = "mem_pass"; break;
				case 2 : menu = "mem_name"; break;
				case 3 : menu = "mem_tel"; break;
				case 4 : menu = "mem_addr"; break;
				default : System.out.println("잘못 입력하셨습니다.");
						  return;
			}
			
			scan.nextLine();
			System.out.print("새로운 " + menu + "를 입력하세요.");
			String input = scan.nextLine();
			
			String sql = "update mymember set " + menu + "=? where mem_id =? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input);
			pstmt.setString(2, id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("수정 성공!");
			}else {
				System.out.println("수정 실패~");
			}
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
			
	}
	
}
