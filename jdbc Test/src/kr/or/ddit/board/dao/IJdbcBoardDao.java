package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.JdbcBoardVO;

public interface IJdbcBoardDao {

	// 전체 목록 출력
	public List<JdbcBoardVO> getAllBoardList();
	
	// 새 글 작성
	public int insertBoard(JdbcBoardVO boardVo);
	
	// 게시글 보기
	public List<JdbcBoardVO> selectBoard(int boardNo);
	
	// 수정
	public int updateBoard(int boardNo, String newTitle, String newContent);
	
	// 삭제
	public int deleteBoard(int boardNo);
	
	// 검색
	public List<JdbcBoardVO> selectBoardList(String boardTitle);
	
	// 조회수
	public int updateCnt(int boardNo);
	
}
