package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.IJdbcBoardServiceT;
import kr.or.ddit.board.service.JdbcBoardServiceImplT;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardControllerT {

	private IJdbcBoardServiceT service;
	private Scanner scan = new Scanner(System.in);
	
	public JdbcBoardControllerT() {
		service = JdbcBoardServiceImplT.getInstance();
	}
	
	public static void main(String[] args) {
		new JdbcBoardControllerT().jdbcBoardStart();
	}
	
	private void jdbcBoardStart() {
		String title = null;
		int choice = -1;
		while(true) {
			if(choice!=3) {
				title = null;
			}
			choice = displayMenu(title);
			
			switch(choice) {
				case 1 : // ���� �ۼ�
					insertBoard(); break;
				case 2 : // �Խñ� ����
					viewBoard();
					break;
				case 3 : // �˻�
					title = searchBoard();
					break;
				case 0 : // �۾� ��
					System.out.println("�Խ��� ���α׷� ����...");
					return;
				default : 
					System.out.println("��ȣ�� �߸� �Է��߽��ϴ�. �ٽ� �Է��ϼ���.");
			}
		}
	}
	
	// �ϳ��� �Խñ��� �����ִ� �޼���
	private void viewBoard() {
		System.out.println();
		System.out.print("���⸦ ���ϴ� �Խù� ��ȣ �Է� >> ");
		int boardNo = scan.nextInt();
		
		// �Խñ� ���⸦ �� �� ��ȸ���� �����ؾ� �ϴµ� �� �۾��� Service���� �����Ѵ�.
		JdbcBoardVO boardVo = service.getBoard(boardNo);
		if(boardVo==null) {
			System.out.println(boardNo + "���� �Խñ��� �������� �ʽ��ϴ�.");
			return;
		}
		
		System.out.println();
		System.out.println(boardNo + "���� ����");
		System.out.println("-------------------------------------------------");
		System.out.println("- �� ��: " + boardVo.getBoard_title());
		System.out.println("- �ۼ���: " + boardVo.getBoard_writer());
		System.out.println("- �� ��: " + boardVo.getBoard_content());
		System.out.println("- �ۼ���: " + boardVo.getBoard_date());
		System.out.println("- ��ȸ��: " + boardVo.getBoard_cnt());
		System.out.println("-------------------------------------------------");
		System.out.println(" �޴� : 1. ����    2. ����    3. ����Ʈ�� ����");
		System.out.print(" �۾����� >> ");
		int choice = scan.nextInt();
		
		switch(choice) {
			case 1 :	// ����
				updateBoard(boardNo);
				break;
			case 2 :	// ����
				deleteBoard(boardNo);
				break;
			case 3 :	// ����Ʈ�� ����
				return;
		}
		
	}
	
	// �Խñ��� �����ϴ� �޼���
	private void updateBoard(int boardNo) {
		System.out.println();
		scan.nextLine();  // �Է� ���� ����
		System.out.println("���� �۾� �ϱ�");
		System.out.println("-----------------------------------");
		System.out.print("- �� �� : ");
		String title = scan.nextLine();
		System.out.print("- �� �� : ");
		String content = scan.nextLine();
		
		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_no(boardNo);
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		
		int cnt = service.updateBoard(boardVo);
		if(cnt>0) {
			System.out.println(boardNo + "�� �Խñ� ���� �Ϸ�!!!");
		}else {
			System.out.println(boardNo + "�� �Խñ� ���� ����!!!");
		}
		
	}
	
	// �Խñ��� �����ϴ� �޼���
	private void deleteBoard(int boardNo) {
		int cnt = service.deleteBoard(boardNo);
		if(cnt>0) {
			System.out.println(boardNo + "�� �Խñ� ���� �Ϸ�!!!");
		}else {
			System.out.println(boardNo + "�� �Խñ� ���� ����!!!");
		}
	}
	
	// �˻��� �Խñ� ������ �Է¹޾� ��ȯ�ϴ� �޼���
	private String searchBoard() {
		scan.nextLine();  // �Է� ���� ����
		System.out.println();
		System.out.println("�˻� �۾�");
		System.out.println("----------------------------------");
		System.out.print(" - �˻��� ���� �Է� : ");
		return scan.nextLine();
		
	}
	
	// ������ �ۼ��ϴ� �޼���
	private void insertBoard() {
		System.out.println();
		scan.nextLine();  // �Է� ���� ����
		
		System.out.println("���� �ۼ��ϱ�");
		System.out.println("------------------------------");
		
		System.out.print("- �� �� : ");
		String title = scan.nextLine();
		
		System.out.print("- �ۼ��� : ");
		String writer = scan.nextLine(); // nextLine�޼��带 ������ ���� �Է¹��۴� ó�� �ѹ��� ����ָ� ��.
		
		System.out.print("- �� ��: ");
		String content = scan.nextLine();
		
		// �Է��� �����͵��� VO��ü�� ��´�.
		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt>0) {
			System.out.println("������ �߰��Ǿ����ϴ�.");
		}else {
			System.out.println("���� �߰� ����!!!!");
		}
		
	}

	
	// �Խ��� ������� �����ְ� �޴��� ��Ÿ���� ����ڰ� �Է��� �޴� ��ȣ�� ��ȯ�ϴ� �޼���
	public int displayMenu(String title) {
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println(" No      ��  ��             �ۼ���                ��ȸ�� ");
		System.out.println("--------------------------------------");
		
		
		List<JdbcBoardVO> boardList = null;
		if(title==null || "".equals(title)) {
			boardList = service.getAllBoardList();
		}else {
			boardList = service.getSearchBoardList(title);
		}
		
		if(boardList==null || boardList.size()==0) {
			System.out.println("����� �Խñ��� �ϳ��� �����ϴ�...");
		}else {
			for(JdbcBoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t"
						+ boardVo.getBoard_title() + "\t"
						+ boardVo.getBoard_writer() + "\t"
						+ boardVo.getBoard_cnt());
			}
		}
		System.out.println("--------------------------------------");
		System.out.println("�޴� : 1.�����ۼ�   2.�Խñۺ���   3.�˻�    0.�۾���");
		System.out.print("�۾� ���� >> ");
		return scan.nextInt();
		
	}
	
}