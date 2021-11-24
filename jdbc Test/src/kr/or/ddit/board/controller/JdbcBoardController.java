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
					System.out.println("프로그램을 종료합니다..");
					return;
				default : 
					System.out.println("번호를 잘못 입력했습니다.");
					System.out.println("다시 입력하세요.");
			}
		}
	}
	
	private int displayAllBoard() {
		
		List<JdbcBoardVO> boardList = service.getAllBoardList();
		
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println(" No	    제 목        작성자 	조회수   ");
		System.out.println("----------------------------------");
		
		if(boardList==null || boardList.size() == 0) {
			System.out.println("   게시판이 비어 있습니다.");
		}else {
			for(JdbcBoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t" +
						boardVo.getBoard_title() + "\t" + 
						boardVo.getBoard_writer() + "\t" +
						boardVo.getBoard_cnt());
			}
		}
		
		System.out.println("---------------------------------");
		System.out.println(" 메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("  작업선택 >> ");
		
		int choice = scan.nextInt();
		
		return choice;
		
	}
	
	private void insertBoard() {
		
		System.out.println();
		System.out.println(" 새 글 작성하기");
		System.out.println("---------------------------");
		System.out.print(" - 제 목 : ");
		String boardTitle = scan.next();
		System.out.print(" - 작성자 : ");
		String boardWriter = scan.next();
		scan.nextLine();
		System.out.print(" - 내 용 : ");
		String boardContent = scan.nextLine();
		
		JdbcBoardVO boardVo = new JdbcBoardVO();
		
		boardVo.setBoard_title(boardTitle);
		boardVo.setBoard_writer(boardWriter);
		boardVo.setBoard_content(boardContent);
		
		System.out.println();
		int cnt = service.insertBoard(boardVo);
		if(cnt>0) {
			System.out.println("새 글이 추가되었습니다.");
		}else {
			System.out.println("등록 실패");
		}

	}
	
	private void selectBoard() {
		
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int boardNo = scan.nextInt();
		System.out.println();
		
		int count = service.updateCnt(boardNo);
		
		List<JdbcBoardVO> boardList = service.selectBoard(boardNo);
		
		System.out.println(boardNo + "번글 내용");
		System.out.println("---------------------------------------");
		System.out.println("- 제 목 : " + boardList.get(boardNo-1).getBoard_title());
		System.out.println("- 작성자 : " + boardList.get(boardNo-1).getBoard_writer());
		System.out.println("- 내 용 : " + boardList.get(boardNo-1).getBoard_content());
		System.out.println("- 작성일 : " + boardList.get(boardNo-1).getBoard_date());
		System.out.println("- 조회수 : " + boardList.get(boardNo-1).getBoard_cnt());
		System.out.println("---------------------------------------");
		System.out.println(" 메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
		System.out.print(" 작업선택 >> ");
		int choice = scan.nextInt();
		
		switch(choice) {
			case 1 : updateBoard(boardNo); break;
			case 2 : deleteBoard(boardNo); break;
			case 3 : return;
			default : System.out.println("잘못 입력하셨습니다.");
					  return;
		}
		
	}
	
	private void updateBoard(int boardNo) {
		
		System.out.println();
		System.out.println(" 수정 작업하기");
		System.out.println("------------------------------");
		System.out.print(" - 제 목 : ");
		String newTitle = scan.next();
		scan.nextLine();
		System.out.print(" - 내 용 : ");
		String newContent = scan.nextLine();
		
		int cnt = service.updateBoard(boardNo, newTitle, newContent);
		
		if(cnt>0) {
			System.out.println(boardNo + "번글이 수정되었습니다.");
		}else {
			System.out.println("수정 실패");
		}
		
	}
	
	private void deleteBoard(int boardNo) {
		System.out.println();
		
		int cnt = service.deleteBoard(boardNo);
		if(cnt>0) {
			System.out.println(boardNo + "번글이 삭제되었습니다.");
		}else {
			System.out.println("삭제 실패!");
		}
	}
	
	private void searchBoard() {

		System.out.println();
		System.out.println("검색 작업");
		System.out.println("----------------------------------");
		System.out.print(" - 검색할 제목 입력 : ");
		String title = scan.next();
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println(" No	    제 목        작성자 	조회수   ");
		System.out.println("----------------------------------");
		
		List<JdbcBoardVO> boardList = service.selectBoardList(title);
		
		if(boardList==null || boardList.size() == 0) {
			System.out.println("게시물이 존재하지 않습니다.");
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
