package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.IJdbcBoardDao;
import kr.or.ddit.board.dao.JdbcBoardDaoImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardServiceImpl implements IJdbcBoardService {
	private IJdbcBoardDao boardDao;
	
	private static JdbcBoardServiceImpl service;
	
	private JdbcBoardServiceImpl() {
		boardDao = JdbcBoardDaoImpl.getInstance();
	}
	
	public static JdbcBoardServiceImpl getInstance() {
		if(service==null) service = new JdbcBoardServiceImpl();
		
		return service;
	}
	
	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		return boardDao.getAllBoardList();
	}

	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		return boardDao.insertBoard(boardVo);
	}

	@Override
	public List<JdbcBoardVO> selectBoard(int boardNo) {
		return boardDao.selectBoard(boardNo);
	}

	@Override
	public int updateBoard(int boardNo, String newTitle, String newContent) {
		return boardDao.updateBoard(boardNo, newTitle, newContent);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return boardDao.deleteBoard(boardNo);
	}

	@Override
	public List<JdbcBoardVO> selectBoardList(String boardTitle) {
		return boardDao.selectBoardList(boardTitle);
	}

	@Override
	public int updateCnt(int boardNo) {
		return boardDao.updateCnt(boardNo);
	}

}
