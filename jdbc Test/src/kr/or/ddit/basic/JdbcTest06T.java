package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;

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
 
 */

public class JdbcTest06T {
   private Scanner scan = new Scanner(System.in);

   public static void main(String[] args) {
	   new JdbcTest06T().memberStart();
   }

   private void memberStart() {
      while (true) {
         int choice = displayMenu();
         switch (choice) {
         case 1:
        	 insertMember();
            break;
         case 2:
        	deleteMember();
            break;
         case 3:
        	updateMember();
            break;
         case 4:
        	 displayAllMember();
            break;
         case 5:	// ���� 2
        	 updateMember2();
        	 break;
         case 0:
            System.out.println("���α׷��� �����մϴ�..");
            return;

         default:
            System.out.println("��ȣ�� �߸� �Է���");
         }
      }
   }
   
   // ȸ�� ������ �����ϴ� �޼��� ==> ���ϴ� �׸� �����ϱ�
   private void updateMember2() {
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   
	   System.out.println("������ ȸ�� ������ �Է��ϼ���.");
	   System.out.print("ȸ��ID >> ");
	   String memId = scan.next();
	   
	   int count = getMemberCount(memId);
	   if(count == 0) {  // ���� ȸ��ID�� �Է����� ���
		   System.out.println(memId + "��(��) ���� ȸ��ID �Դϴ�.");
		   System.out.println("���� �۾��� �����մϴ�.");
		   return;
	   }
	   
	   int num;  // ������ �׸��� ��ȣ�� ����� ���� 
	   String updateField = null;  // ������ �÷����� ����� ����
	   String updateTitle = null;  
	   do {
		   System.out.println();
		   System.out.println("������ �׸��� �����ϼ���.");
		   System.out.println("1.��й�ȣ  2.ȸ���̸�  3.��ȭ��ȣ  4.ȸ���ּ�");
		   System.out.println("-----------------------------------");
		   System.out.println("���� �׸� ���� >> ");
		   num = scan.nextInt();
		   
		   switch(num) {
		   		case 1 : updateField = "mem_pass"; updateTitle = "��й�ȣ";
		   				break;
		   		case 2 : updateField = "mem_name"; updateTitle = "ȸ���̸�";
		   				break;
		   		case 3 : updateField = "mem_tel"; updateTitle = "��ȭ��ȣ";
		   				break;
		   		case 4 : updateField = "mem_addr"; updateTitle = "ȸ���ּ�";
		   				break;
		   		default : 
		   			System.out.println("������ �׸��� �߸� �����߽��ϴ�.");
		   			System.out.println("�ٽ� �����ϼ���.");
		   }
		   
	   }while(num<1 || num>4);
	   
	   System.out.println();
	   scan.nextLine();  // �Է� ���� ����
	   System.out.println("���ο� " + updateTitle + " >> ");
	   String updateData = scan.nextLine();
	   
	   try {
		   conn = DBUtil.getConnection();
		   
		   String sql = "update mymember set " + updateField + " = ? "
				   + " where mem_id= ?";
		   
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, updateData);
		   pstmt.setString(2, memId);
		   
		   int cnt = pstmt.executeUpdate();
		   
		   if(cnt>0) {
			   System.out.println("���� �۾� ����~~");
		   }else {
			   System.out.println("���� �۾� ����!!!");
		   }
		   
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
	   
   }
   
   
   // ȸ�� ������ �����ϴ� �޼���
   private void updateMember() {
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   
	   System.out.println("������ ȸ�� ������ �Է��ϼ���.");
	   System.out.print("ȸ��ID >> ");
	   String memId = scan.next();
	   
	   int count = getMemberCount(memId);
	   if(count == 0) {  // ���� ȸ��ID�� �Է����� ���
		   System.out.println(memId + "��(��) ���� ȸ��ID �Դϴ�.");
		   System.out.println("���� �۾��� �����մϴ�.");
		   return;
	   }
	   
	   System.out.println("������ ������ �Է��ϼ���.");
	   System.out.print("���ο� �н����� >> ");
	   String memPass = scan.next();
	   
	   System.out.print("���ο� ȸ���̸� >> ");
	   String memName = scan.next();
	   
	   System.out.print("���ο� ��ȭ��ȣ >> ");
	   String memTel = scan.next();
	   
	   scan.nextLine();
	   System.out.print("���ο� �ּ� >> ");
	   String memAddr = scan.nextLine();
	   
	   try {
		   conn = DBUtil.getConnection();
		   
		   String sql = "update mymember set mem_pass=? ,"
				   + "mem_name=?, mem_tel=?, mem_addr=?"
				   + "where mem_id=? ";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, memPass);
		   pstmt.setString(2, memName);
		   pstmt.setString(3, memTel);
		   pstmt.setString(4, memAddr);
		   pstmt.setString(5, memId);
		   
		   int cnt = pstmt.executeUpdate();
		   
		   if(cnt>0) {
			   System.out.println("���� �۾� ����~~");
		   }else {
			   System.out.println("���� �۾� ����!!!");
		   }
		   
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
	   
   }
   
   // ȸ�� ������ �����ϴ� �޼���
   private void deleteMember() {
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   
	   System.out.println();
	   System.out.println("������ ȸ�� ������ �Է��ϼ���.");
	   System.out.print("������ ȸ��ID >> ");
	   String memId = scan.next();
	   
	   try {
		   conn = DBUtil.getConnection();
		   String sql = "delete from mymember where mem_id = ?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, memId);
		   
		   int cnt = pstmt.executeUpdate();
		   
		   if(cnt>0) {
			   System.out.println(memId + "ȸ�� ���� ���� ����!!");
		   }else {
			   System.out.println(memId + "ȸ���� ���� ȸ���̰ų� ������ �����߽��ϴ�.");
		   }
			   
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
	   
   }
   
   // ȸ�� ������ �߰��ϴ� �޼���
   private void insertMember() {
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   
	   System.out.println();
	   System.out.println("�߰��� ȸ�� ������ �Է��ϼ���.");
	   int count = 0;  // �ߺ� ���θ� �˻��ϱ� ���� ���� (ID������ ����� ����)
	   String memId = null;
	   do {
		   System.out.println("ȸ��ID >> ");
		   memId = scan.next();
		   
		   count = getMemberCount(memId);
		   
		   if(count>0) {
			   System.out.print(memId + "��(��) �̹� ��ϵ� ȸ��ID �Դϴ�.");
			   System.out.println("�ٸ� ȸ��ID�� �Է��ϼ���.");
		   }
				   
	   } while (count>0);
	   
	   System.out.print("�н����� >> ");
	   String memPass = scan.next();
	   
	   System.out.print("ȸ���̸� >> ");
	   String memName = scan.next();
	   
	   System.out.print("��ȭ��ȣ >> ");
	   String memTel = scan.next();
	   
	   scan.nextLine();  // �Է� ���� ����
	   System.out.print("ȸ���ּ� >> ");
	   String memAddr = scan.nextLine();
	   
	   try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember (mem_id, mem_pass, mem_name,"
					+ "mem_tel, mem_addr) values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, memName);
			pstmt.setString(4, memTel);
			pstmt.setString(5, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId + "ȸ�� ��� ����");
			}else {
				System.out.println(memId + "ȸ�� ��� ����!!!");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
	   
   }
   
   // �Ű������� ������ ȸ��ID�� ������ ��ȯ�ϴ� �޼���
   private int getMemberCount(String memId) {
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
	   int count = 0;  // ȸ��ID������ ����� ����
	   
	   try {
		   conn = DBUtil.getConnection();
		   
		   String sql = "select count(*) cnt from mymember where mem_id = ?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, memId);
		   
		   rs = pstmt.executeQuery();
		   
		   if(rs.next()) {
			   count = rs.getInt("cnt");
		   }
				   
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
	   
	   return count;
   }
   
   
   // ��ü ȸ�� ������ ����ϴ� �޼���
   private void displayAllMember() {
	   
	   Connection conn = null;
	   Statement stmt = null;
	   ResultSet rs = null;
	   
	   System.out.println("-----------------------------------");
	   System.out.println(" ID   PASSWORD  �� ��     ��ȭ��ȣ    ��  ��");
	   System.out.println("-----------------------------------");
	   
	   try {
		    //conn = DBUtil.getConnection();
		    //conn = DBUtil2.getConnection();
		    conn = DBUtil3.getConnection();
		    
		    String sql = "select * from mymember";
		    stmt = conn.createStatement();
		    
		    rs = stmt.executeQuery(sql);
		    
		    while(rs.next()) {
		    	String memId = rs.getString("mem_id");
		    	String memPass = rs.getString("mem_pass");
		    	String memName = rs.getString("mem_name");
		    	String memTel = rs.getString("mem_tel");
		    	String memAddr = rs.getString("mem_addr");
		    	System.out.println(memId + "\t" + memPass + "\t" + 
		    			memName + "\t" + memTel + "\t" + memAddr);
		    }
		    System.out.println("-----------------------------------");
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(stmt!=null) try { stmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
	   
   }

   // �޴��� ����ϰ� ������ �۾���ȣ�� �Է¹޾� ��ȯ�ϴ� �޼���
   private int displayMenu() {
      System.out.println("========== �۾����� ==========");
      System.out.println("1. �ڷ� �߰�");
      System.out.println("2. �ڷ� ����");
      System.out.println("3. �ڷ� ����");
      System.out.println("4. ��ü �ڷ� ���");
      System.out.println("5. �ڷ� ����2");
      System.out.println("0. �۾� ��");
      System.out.println("=============================");
      System.out.print("�۾� ���� >> ");
      return scan.nextInt();
   }

}