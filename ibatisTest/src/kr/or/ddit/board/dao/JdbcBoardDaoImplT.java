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


public class JdbcBoardDaoImplT implements IJdbcBoardDaoT {
	
	private static JdbcBoardDaoImplT dao;
	private SqlMapClient smc;
	
	private JdbcBoardDaoImplT() { 
		try {
			// Dao�� �����ڿ��� iBatis ȯ�� ���� �� iBatis�� ��ü�� �����ϴ� ���� �Ѵ�.
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);	// ���� ���ڵ� ����
			
			Reader rd = 
					Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd); //sql�� ����� �� �ִ� ���̹�Ƽ�� Ŭ���̾�Ʈ ��ü ����
			rd.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static JdbcBoardDaoImplT getInstance() {
		if(dao==null) dao = new JdbcBoardDaoImplT();
		return dao;
	}
	

	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("board.insertBoard", boardVo);
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
			cnt = smc.delete("board.deleteBoard", boardNo);
			
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
			cnt = smc.update("board.updateBoard", boardVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		List<JdbcBoardVO> boardList = new ArrayList<>();
		try {
			boardList = smc.queryForList("board.getAllBoard");
			
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
			boardVo = (JdbcBoardVO) smc.queryForObject("board.getBoard", boardNo);
			
		} catch (SQLException e) {
			boardVo = null;
			e.printStackTrace();
		} 
		
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {
		List<JdbcBoardVO> boardList = new ArrayList<>();
		
		if(title==null) {  // �˻��� ������ ���� �� ó���ϱ�
			title = "";
		}
		
		try {
			boardList = smc.queryForList("board.getSearchBoard", title);
			
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
			cnt = smc.update("board.setCountIncrement", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

}
