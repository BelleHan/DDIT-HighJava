package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.IJdbcBoardService;
import kr.or.ddit.board.service.JdbcBoardServiceImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardController {
	private Scanner scan = new Scanner(System.in);
	private IJdbcBoardService service;
	
	public JdbcBoardController() {
		service = JdbcBoardServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new JdbcBoardController().boardStart();
	}
	private void boardStart() {
		while (true) {
			int choice = displayAllBoard();
			switch (choice) {
				case 1 : insertBoard(); break;
				case 2 : selectBoard(); break;
				case 3 : searchBoard(); break;
				case 0 :
					System.out.println("���α׷��� �����մϴ�..");
					return;
				default : 
					System.out.println("��ȣ�� �߸� �Է��߽��ϴ�.");
					System.out.println("�ٽ� �Է��ϼ���.");
			}
		}
	}
	
	private int displayAllBoard() {
		
		List<JdbcBoardVO> boardList = service.getAllBoardList();
		
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println(" No	    �� ��        �ۼ��� 	��ȸ��   ");
		System.out.println("----------------------------------");
		
		if(boardList==null || boardList.size() == 0) {
			System.out.println("   �Խ����� ��� �ֽ��ϴ�.");
		}else {
			for(JdbcBoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t" +
						boardVo.getBoard_title() + "\t" + 
						boardVo.getBoard_writer() + "\t" +
						boardVo.getBoard_cnt());
			}
		}
		
		System.out.println("---------------------------------");
		System.out.println(" �޴� : 1. �����ۼ�     2. �Խñۺ���    3. �˻�    0. �۾���");
		System.out.print("  �۾����� >> ");
		
		int choice = scan.nextInt();
		
		return choice;
		
	}
	
	private void insertBoard() {
		
		System.out.println();
		System.out.println(" �� �� �ۼ��ϱ�");
		System.out.println("---------------------------");
		System.out.print(" - �� �� : ");
		String boardTitle = scan.next();
		System.out.print(" - �ۼ��� : ");
		String boardWriter = scan.next();
		scan.nextLine();
		System.out.print(" - �� �� : ");
		String boardContent = scan.nextLine();
		
		JdbcBoardVO boardVo = new JdbcBoardVO();
		
		boardVo.setBoard_title(boardTitle);
		boardVo.setBoard_writer(boardWriter);
		boardVo.setBoard_content(boardContent);
		
		System.out.println();
		int cnt = service.insertBoard(boardVo);
		if(cnt>0) {
			System.out.println("�� ���� �߰��Ǿ����ϴ�.");
		}else {
			System.out.println("��� ����");
		}

	}
	
	private void selectBoard() {
		
		System.out.println();
		System.out.print("���⸦ ���ϴ� �Խù� ��ȣ �Է� >> ");
		int boardNo = scan.nextInt();
		System.out.println();
		
		int count = service.updateCnt(boardNo);
		
		List<JdbcBoardVO> boardList = service.selectBoard(boardNo);
		
		System.out.println(boardNo + "���� ����");
		System.out.println("---------------------------------------");
		System.out.println("- �� �� : " + boardList.get(boardNo-1).getBoard_title());
		System.out.println("- �ۼ��� : " + boardList.get(boardNo-1).getBoard_writer());
		System.out.println("- �� �� : " + boardList.get(boardNo-1).getBoard_content());
		System.out.println("- �ۼ��� : " + boardList.get(boardNo-1).getBoard_date());
		System.out.println("- ��ȸ�� : " + boardList.get(boardNo-1).getBoard_cnt());
		System.out.println("---------------------------------------");
		System.out.println(" �޴� : 1. ����    2. ����    3. ����Ʈ�� ����");
		System.out.print(" �۾����� >> ");
		int choice = scan.nextInt();
		
		switch(choice) {
			case 1 : updateBoard(boardNo); break;
			case 2 : deleteBoard(boardNo); break;
			case 3 : return;
			default : System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					  return;
		}
		
	}
	
	private void updateBoard(int boardNo) {
		
		System.out.println();
		System.out.println(" ���� �۾��ϱ�");
		System.out.println("------------------------------");
		System.out.print(" - �� �� : ");
		String newTitle = scan.next();
		scan.nextLine();
		System.out.print(" - �� �� : ");
		String newContent = scan.nextLine();
		
		int cnt = service.updateBoard(boardNo, newTitle, newContent);
		
		if(cnt>0) {
			System.out.println(boardNo + "������ �����Ǿ����ϴ�.");
		}else {
			System.out.println("���� ����");
		}
		
	}
	
	private void deleteBoard(int boardNo) {
		System.out.println();
		
		int cnt = service.deleteBoard(boardNo);
		if(cnt>0) {
			System.out.println(boardNo + "������ �����Ǿ����ϴ�.");
		}else {
			System.out.println("���� ����!");
		}
	}
	
	private void searchBoard() {

		System.out.println();
		System.out.println("�˻� �۾�");
		System.out.println("----------------------------------");
		System.out.print(" - �˻��� ���� �Է� : ");
		String title = scan.next();
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println(" No	    �� ��        �ۼ��� 	��ȸ��   ");
		System.out.println("----------------------------------");
		
		List<JdbcBoardVO> boardList = service.selectBoardList(title);
		
		if(boardList==null || boardList.size() == 0) {
			System.out.println("�Խù��� �������� �ʽ��ϴ�.");
		}else {
			for(JdbcBoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t" +
						boardVo.getBoard_title() + "\t" + 
						boardVo.getBoard_writer() + "\t" +
						boardVo.getBoard_cnt());
			}
		}
		
		if(title.equals("")) {
			service.getAllBoardList();
		}
	}
	
}
