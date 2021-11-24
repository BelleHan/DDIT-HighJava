package kr.or.ddit.basic;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 	ȸ�� ���� ���α׷��� �ۼ��Ͻÿ�. (MYMEMBER ���̺� �̿�)
 	
 	�Ʒ��� �޴��� ����� ��� �����Ͻÿ�. (CRUD��� �����ϱ�)
 	
 	�޴�����)
 		-- �۾� ���� --
 		1. �ڷ� �߰�
 		2. �ڷ� ����
 		3. �ڷ� ����
 		4. ��ü �ڷ� ���
 		5. �ڷ� ����2
 		0. �۾� ��.
 	---------------------
 	�۾����� > 
 	
 	ó������)
 	1) �ڷ� �߰����� 'ȸ��ID'�� �ߺ����� �ʴ´�.(�ߺ��Ǹ� �ٽ� �Է¹޴´�.)
 	2) �ڷ� ������ 'ȸ��ID'�� �Է� �޾Ƽ� ó���Ѵ�.
 	3) �ڷ� ���������� 'ȸ��ID'�� �������� �ʴ´�.
 	
 	
 	4) �ڷ� ���� ==> ���ϴ� �׸� �����ؼ� �����ǵ��� �Ѵ�.
 	
 	   MVC���Ͽ� ���Ͽ� �����ؿ���
 	
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
			
			System.out.println("    -- �۾� ���� --");
			System.out.println("    1. �ڷ� �߰�");
			System.out.println("    2. �ڷ� ����");
			System.out.println("    3. �ڷ� ����");
			System.out.println("    4. ��ü �ڷ� ���");
			System.out.println("    5. �ڷ� ���� 2");
			System.out.println("    0. �۾� ��.");
			System.out.println("---------------------");
			System.out.print("�۾����� > ");
			
			int choice = scan.nextInt();
			
			switch(choice) {
			
				case 1 : test.insert(); break;
				case 2 : test.delete(); break;
				case 3 : test.update(); break;
				case 4 : test.select(); break;
				case 5 : test.update2(); break;
				case 0 : System.out.println("���α׷��� �����մϴ�");
				System.exit(0);
				default : System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			
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
				System.out.print("ID�� �Է��ϼ���");
				id = scan.next();
				
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count>0) {
					System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
					System.out.println("�ٽ� �Է��ϼ���.");
				}
				
			} while (count>0);
			
			System.out.print("��й�ȣ�� �Է��ϼ���");
			String pass = scan.next();
			
			System.out.print("�̸��� �Է��ϼ���");
			String name = scan.next();
			
			System.out.print("��ȭ��ȣ�� �Է��ϼ���");
			String tel = scan.next();
			
			System.out.print("�ּҸ� �Է��ϼ���");
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
				System.out.println("��� ����!");
			}else {
				System.out.println("��� ����~");
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
			
			System.out.print("������ ID�� �Է��ϼ���");
			String id = scan.next();
			
			String sql = "delete from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("���� ����!");
			}else {
				System.out.println("���� ����~");
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
			
			System.out.print("������ ID�� �Է��ϼ���");
			String id = scan.next();
			
			System.out.print("������ ��й�ȣ�� �Է��ϼ���");
			String pass = scan.next();
			
			System.out.print("������ �̸��� �Է��ϼ���");
			String name = scan.next();
			
			System.out.print("������ ��ȭ��ȣ�� �Է��ϼ���");
			String tel = scan.next();
			
			System.out.print("������ �ּҸ� �Է��ϼ���");
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
				System.out.println("���� ����!");
			}else {
				System.out.println("���� ����~");
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
			System.out.println("��� ��...");
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
			
			System.out.print("������ ���̵� �Է��ϼ���. ");
			String id = scan.next();
			
			System.out.println("������ �׸��� �����ϼ���");
			System.out.println(" 1.��й�ȣ  2.�̸�  3.��ȭ��ȣ  4.�ּ�");
			System.out.print(" �׸��� >> ");
			int num = scan.nextInt();
			
			String menu = "";
			
			switch(num) {
				case 1 : menu = "mem_pass"; break;
				case 2 : menu = "mem_name"; break;
				case 3 : menu = "mem_tel"; break;
				case 4 : menu = "mem_addr"; break;
				default : System.out.println("�߸� �Է��ϼ̽��ϴ�.");
						  return;
			}
			
			scan.nextLine();
			System.out.print("���ο� " + menu + "�� �Է��ϼ���.");
			String input = scan.nextLine();
			
			String sql = "update mymember set " + menu + "=? where mem_id =? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input);
			pstmt.setString(2, id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("���� ����!");
			}else {
				System.out.println("���� ����~");
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
