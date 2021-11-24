package kr.or.ddit.board.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.SqlMapClientFactory;


public class JdbcBoardDaoImplT2 implements IJdbcBoardDaoT {
	
	private static JdbcBoardDaoImplT2 dao;
	private SqlMapClient smc;
	
	private JdbcBoardDaoImplT2() { 
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static JdbcBoardDaoImplT2 getInstance() {
		if(dao==null) dao = new JdbcBoardDaoImplT2();
		return dao;
	}
	

	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("jdbcBoard.insertBoard", boardVo);
			if(obj==null) cnt = 1;
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			cnt = smc.delete("jdbcBoard.deleteBoard", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("jdbcBoard.updateBoard", boardVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		List<JdbcBoardVO> boardList = null;
		try {
			boardList = smc.queryForList("jdbcBoard.getAllBoardList");
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		
		return boardList;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		JdbcBoardVO boardVo = null;
		try {
			boardVo = 
				(JdbcBoardVO) smc.queryForObject("jdbcBoard.getBoard", boardNo);
			
		} catch (SQLException e) {
			boardVo = null;
			e.printStackTrace();
		} 
		
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {
		List<JdbcBoardVO> boardList = null;
		
		if(title==null) {  // 검색할 제목이 없을 때 처리하기
			title = "";
		}
		
		try {
			boardList = 
				smc.queryForList("jdbcBoard.getSearchBoardList", title);
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		
		return boardList;

	}

	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		try {
			cnt = smc.update("jdbcBoard.setCountIncrement", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

}
