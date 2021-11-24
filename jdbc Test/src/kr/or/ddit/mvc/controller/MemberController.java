package kr.or.ddit.mvc.controller;

	// �Ʒ� �޴��� ����� �����Ͻÿ�.
	// PDF�� ����� ���� ǥ���·� ����ϵ��� �Ѵ�.

	// 6. ��ü �ڷ� Excel�� ���
	// 7. ��ü �ڷ� PDF�� ���
	//
	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil;

public class MemberController {
	private Scanner scan = new Scanner(System.in);
	private IMemberService service;
	
	public MemberController() {
		//service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new MemberController().memberStart();
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
	            System.out.println("��ȣ�� �߸� �Է��߽��ϴ�.");
	            System.out.println("�ٽ� �Է��ϼ���.");
	         }
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
	   
	   // ȸ�� ������ �߰��ϴ� �޼���
	   private void insertMember() {
		   
		   System.out.println();
		   System.out.println("�߰��� ȸ�� ������ �Է��ϼ���.");
		   int count = 0;  // �ߺ� ���θ� �˻��ϱ� ���� ���� (ID������ ����� ����)
		   String memId = null;
		   do {
			   System.out.print("ȸ��ID >> ");
			   memId = scan.next();
			   
			   count = service.getMemberCount(memId);
			   
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
		   
		   // �Է��� �����Ͱ� ����� VO��ü ����
		   MemberVO memVo = new MemberVO();
		   
		   // �Է��� �����͸� VO�� �����Ѵ�.
		   memVo.setMem_id(memId);
		   memVo.setMem_name(memName);
		   memVo.setMem_pass(memPass);
		   memVo.setMem_tel(memTel);
		   memVo.setMem_addr(memAddr);
		   
		   int cnt = service.insertMember(memVo);
		   if(cnt>0) {
			   System.out.println("ȸ�� �߰� ����!!!");
		   }else {
			   System.out.println("ȸ�� �߰� ����~~");
		   }
		   
	   }
	   
	   // ���� �޼���
	   private void deleteMember() {
		   
		   System.out.println();
		   System.out.println("������ ȸ�� ������ �Է��ϼ���.");
		   System.out.print("������ ȸ��ID >> ");
		   String memId = scan.next();
		   
		   int cnt = service.deleteMember(memId);
		   
		   if(cnt>0) {
			   System.out.println("ȸ�� ���� ����!!!");
		   }else {
			   System.out.println("ȸ���� ���� ȸ���̰ų� ������ �����߽��ϴ�.");
		   }
		   
	   }
	   
	   // ȸ�� ������ �����ϴ� �޼���
	   private void updateMember() {
		   
		   System.out.println("������ ȸ�� ������ �Է��ϼ���.");
		   System.out.print("ȸ��ID >> ");
		   String memId = null;
			
		   memId = scan.next();
			   
		   int count = service.getMemberCount(memId);
			   
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
			
			MemberVO memVo = new MemberVO();
			
			memVo.setMem_id(memId);
			memVo.setMem_pass(memPass);
			memVo.setMem_name(memName);
			memVo.setMem_tel(memTel);
			memVo.setMem_addr(memAddr);
			
			int cnt = service.updateMember(memVo);
			
			if(cnt>0) {
				   System.out.println("���� ����!!!");
			}else {
				   System.out.println("���� ����~~");
			}   
		  
		   
		   
	   }
	   
	   private void displayAllMember() {
		   
		   List<MemberVO> memList = service.getAllMemberList();
		   
		   System.out.println();
		   System.out.println("-----------------------------------");
		   System.out.println(" ID   PASSWORD  �� ��     ��ȭ��ȣ    ��  ��");
		   System.out.println("-----------------------------------");
		   
		   if(memList==null || memList.size() == 0) {
			   System.out.println("����� �����Ͱ� �ϳ��� �����ϴ�.");
		   }else {
			   for(MemberVO memvo : memList) {
				   System.out.println(memvo.getMem_id() + "\t" +
						   memvo.getMem_pass() + "\t" + 
						   memvo.getMem_name() + "\t" +
						   memvo.getMem_tel() + "\t" +
						   memvo.getMem_addr());
			   }
			   
		   }
		   
		   
//		   for(int i=0; i<memList.size(); i++) {
//			   System.out.println(memList.get(i));
//		   }
		   
	   }
	   
	   
	   private void updateMember2() {
		   
		   System.out.println("������ ȸ�� ������ �Է��ϼ���.");
		   System.out.print("ȸ��ID >> ");
		   String memId = scan.next();
		   
		   int count = service.getMemberCount(memId);
		   
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
				   System.out.print("���� �׸� ���� >> ");
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
			   System.out.print("���ο� " + updateTitle + " >> ");
			   String updateData = scan.nextLine();
			   
			   // ������ �÷���, �Է��� ������, ȸ��ID�� Map��ü�� �����Ѵ�.
			   
			   //  Key�� ���� ==> ȸ��ID(memId), �������÷���(field), �����ҵ�����(data)
			   Map<String, String> paramMap = new HashMap<String, String>();
			   paramMap.put("memId", memId);
			   paramMap.put("field", updateField);
			   paramMap.put("data", updateData);
			   
			   int cnt = service.memberUpdate2(paramMap);
			   
			   if(cnt>0) {
				   System.out.println("���� �۾� ����~~");
			   }else {
				   System.out.println("���� �۾� ����!!!");
			   }
			   
	   }
	   
	   /*
	   private void updateMember2() {
		   
		   System.out.println("������ ȸ�� ������ �Է��ϼ���.");
		   System.out.print("ȸ��ID >> ");
		   String memId = scan.next();
		   
		   int count = service.getMemberCount(memId);
		   
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
				   System.out.print("���� �׸� ���� >> ");
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
			   System.out.print("���ο� " + updateTitle + " >> ");
			   String updateData = scan.nextLine();
			   
			   int cnt = service.updateMember2(memId, updateField, updateData);
			   
			   if(cnt>0) {
				   System.out.println("���� �۾� ����~~");
			   }else {
				   System.out.println("���� �۾� ����!!!");
			   }
			   
	   }
	    */
	   
}
