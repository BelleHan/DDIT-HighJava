package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.JdbcBoardVO;

public interface IJdbcBoardDao {

	// ��ü ��� ���
	public List<JdbcBoardVO> getAllBoardList();
	
	// �� �� �ۼ�
	public int insertBoard(JdbcBoardVO boardVo);
	
	// �Խñ� ����
	public List<JdbcBoardVO> selectBoard(int boardNo);
	
	// ����
	public int updateBoard(int boardNo, String newTitle, String newContent);
	
	// ����
	public int deleteBoard(int boardNo);
	
	// �˻�
	public List<JdbcBoardVO> selectBoardList(String boardTitle);
	
	// ��ȸ��
	public int updateCnt(int boardNo);
	
}
