package kr.or.ddit.board.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import kr.or.ddit.board.service.IJdbcBoardServiceT;
import kr.or.ddit.board.service.JdbcBoardServiceImplT;
import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.CryptoUtil;

public class JdbcBoardControllerT {

	private IJdbcBoardServiceT service;
	private Scanner scan = new Scanner(System.in);
	private final String key = "abcd1234edfg4564";
	
	public JdbcBoardControllerT() {
		service = JdbcBoardServiceImplT.getInstance();
	}
	
	public static void main(String[] args) {
		try {
			new JdbcBoardControllerT().jdbcBoardStart();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void jdbcBoardStart() throws Exception {
		String title = null;
		int choice = -1;
		while(true) {
			if(choice!=3) {
				title = null;
			}
			choice = displayMenu(title);
			
			switch(choice) {
				case 1 : // 새글 작성
					insertBoard(); break;
				case 2 : // 게시글 보기
					viewBoard();
					break;
				case 3 : // 검색
					title = searchBoard();
					break;
				case 0 : // 작업 끝
					System.out.println("게시판 프로그램 종료...");
					return;
				default : 
					System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
		}
	}
	
	// 하나의 게시글을 보여주는 메서드
	private void viewBoard() throws Exception {
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int boardNo = scan.nextInt();
		
		// 게시글 보기를 할 때 조회수를 증가해야 하는데 이 작업은 Service에서 진행한다.
		JdbcBoardVO boardVo = service.getBoard(boardNo);
		if(boardVo==null) {
			System.out.println(boardNo + "번의 게시글은 존재하지 않습니다.");
			return;
		}
		
		System.out.println();
		System.out.println(boardNo + "번글 내용");
		System.out.println("-------------------------------------------------");
		System.out.println("- 제 목: " + boardVo.getBoard_title());
		System.out.println("- 작성자: " + CryptoUtil.decryptAES256(boardVo.getBoard_writer(), key));
		System.out.println("- 내 용: " + boardVo.getBoard_content());
		System.out.println("- 작성일: " + boardVo.getBoard_date());
		System.out.println("- 조회수: " + boardVo.getBoard_cnt());
		System.out.println("-------------------------------------------------");
		System.out.println(" 메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
		System.out.print(" 작업선택 >> ");
		int choice = scan.nextInt();
		
		switch(choice) {
			case 1 :	// 수정
				updateBoard(boardNo);
				break;
			case 2 :	// 삭제
				deleteBoard(boardNo);
				break;
			case 3 :	// 리스트로 가기
				return;
		}
		
	}
	
	// 게시글을 수정하는 메서드
	private void updateBoard(int boardNo) {
		System.out.println();
		scan.nextLine();  // 입력 버퍼 비우기
		System.out.println("수정 작업 하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제 목 : ");
		String title = scan.nextLine();
		System.out.print("- 내 용 : ");
		String content = scan.nextLine();
		
		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_no(boardNo);
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		
		int cnt = service.updateBoard(boardVo);
		if(cnt>0) {
			System.out.println(boardNo + "번 게시글 수정 완료!!!");
		}else {
			System.out.println(boardNo + "번 게시글 수정 실패!!!");
		}
		
	}
	
	// 게시글을 삭제하는 메서드
	private void deleteBoard(int boardNo) {
		int cnt = service.deleteBoard(boardNo);
		if(cnt>0) {
			System.out.println(boardNo + "번 게시글 삭제 완료!!!");
		}else {
			System.out.println(boardNo + "번 게시글 삭제 실패!!!");
		}
	}
	
	// 검색할 게시글 제목을 입력받아 반환하는 메서드
	private String searchBoard() {
		scan.nextLine();  // 입력 버퍼 비우기
		System.out.println();
		System.out.println("검색 작업");
		System.out.println("----------------------------------");
		System.out.print(" - 검색할 제목 입력 : ");
		return scan.nextLine();
		
	}
	
	// 새글을 작성하는 메서드
	private void insertBoard() throws Exception {
		System.out.println();
		scan.nextLine();  // 입력 버퍼 비우기
		
		System.out.println("새글 작성하기");
		System.out.println("------------------------------");
		
		System.out.print("- 제 목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 작성자 : ");
		String writer = scan.nextLine(); // nextLine메서드를 여러번 사용시 입력버퍼는 처음 한번만 비워주면 됨.
		String encWriter = CryptoUtil.encryptAES256(writer, key);
		
		System.out.print("- 내 용: ");
		String content = scan.nextLine();
		
		// 입력한 데이터들을 VO객체에 담는다.
		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(encWriter);
		boardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt>0) {
			System.out.println("새글이 추가되었습니다.");
		}else {
			System.out.println("새글 추가 실패!!!!");
		}
		
	}

	
	// 게시판 목록으로 보여주고 메뉴를 나타내며 사용자가 입력한 메뉴 번호를 반환하는 메서드
	public int displayMenu(String title) throws Exception {
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println(" No      제  목             작성자                조회수 ");
		System.out.println("--------------------------------------");
		
		
		List<JdbcBoardVO> boardList = null;
		if(title==null || "".equals(title)) {
			
			boardList = service.getAllBoardList();
		}else {
			boardList = service.getSearchBoardList(title);
		}
		
		if(boardList==null || boardList.size()==0) {
			System.out.println("출력할 게시글이 하나도 없습니다...");
		}else {
			for(JdbcBoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t"
						+ boardVo.getBoard_title() + "\t"
						+ CryptoUtil.decryptAES256(boardVo.getBoard_writer(), key) + "\t"
						+ boardVo.getBoard_cnt());
			}
		}
		
		System.out.println("--------------------------------------");
		System.out.println("메뉴 : 1.새글작성   2.게시글보기   3.검색    0.작업끝");
		System.out.print("작업 선택 >> ");
		return scan.nextInt();
		
	}
	
}
