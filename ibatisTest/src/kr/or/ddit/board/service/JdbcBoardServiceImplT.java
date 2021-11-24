package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IJdbcBoardDaoT;
import kr.or.ddit.board.dao.JdbcBoardDaoImplT;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardServiceImplT implements IJdbcBoardServiceT {
	private IJdbcBoardDaoT dao;
	private static JdbcBoardServiceImplT service;
	
	private JdbcBoardServiceImplT() {
		dao = JdbcBoardDaoImplT.getInstance();
	}
	
	public static JdbcBoardServiceImplT getInstance() {
		if(service==null) service = new JdbcBoardServiceImplT();
		return service;
	}
	
	
	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		return dao.getAllBoardList();
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		// 게시글 번호에 해당하는 데이터들을 가져오기 전에 조회수를 증가 시키고 
		// 조회수 증가가 성공되면 게시글 데이터들을 가져온다.
		int cnt = service.setCountIncrement(boardNo);
		if(cnt > 0)	 // 조회수 증가 성공 후
			return dao.getBoard(boardNo);
		else	// 조회수 증가 실패 후
			return null;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {
		return dao.getSearchBoardList(title);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

}
