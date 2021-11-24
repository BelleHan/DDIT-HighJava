package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.DBUtil3;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	
	private static JdbcBoardDaoImpl dao;
	
	private JdbcBoardDaoImpl() { }
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null) dao = new JdbcBoardDaoImpl();
		
		return dao;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		List<JdbcBoardVO> boardList = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select * from jdbc_board";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				JdbcBoardVO boardVo = new JdbcBoardVO();
				int boardNo = rs.getInt("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");
				int boardCnt = rs.getInt("board_cnt");
				String boardContent = rs.getString("board_content");
				boardVo.setBoard_no(boardNo);
				boardVo.setBoard_title(boardTitle);
				boardVo.setBoard_writer(boardWriter);
				boardVo.setBoard_date(boardDate);
				boardVo.setBoard_cnt(boardCnt);
				boardVo.setBoard_content(boardContent);
				
				boardList.add(boardVo);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(stmt!=null) try { stmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
	
		
		return boardList;
	}

	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into jdbc_board(board_no, board_title, board_writer, board_content, board_date, board_cnt)"
					+ " values(board_seq.nextVal, ?, ?, ?, sysdate, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public List<JdbcBoardVO> selectBoard(int boardNo) {
		List<JdbcBoardVO> boardList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select board_title, board_writer, board_content, board_date, board_cnt from jdbc_board where board_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JdbcBoardVO boardVo = new JdbcBoardVO();
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardContent = rs.getString("board_content");
				String boardDate = rs.getString("board_date");
				int boardCnt = rs.getInt("board_cnt");
				boardVo.setBoard_no(boardNo);
				boardVo.setBoard_title(boardTitle);
				boardVo.setBoard_writer(boardWriter);
				boardVo.setBoard_content(boardContent);
				boardVo.setBoard_date(boardDate);
				boardVo.setBoard_cnt(boardCnt);
				
				boardList.add(boardVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return boardList;
	}

	@Override
	public int updateBoard(int boardNo, String newTitle, String newContent) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "update jdbc_board set board_title=?, board_content=? where board_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newTitle);
			pstmt.setString(2, newContent);
			pstmt.setInt(3, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from jdbc_board where board_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public List<JdbcBoardVO> selectBoardList(String boardTitle) {
		List<JdbcBoardVO> boardList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select board_no, board_title, board_writer, board_cnt from jdbc_board where board_title like '%' || ? || '%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JdbcBoardVO boardVo = new JdbcBoardVO();
				int boardNo = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				int boardCnt = rs.getInt("board_cnt");
				boardVo.setBoard_no(boardNo);
				boardVo.setBoard_title(title);
				boardVo.setBoard_writer(boardWriter);
				boardVo.setBoard_cnt(boardCnt);
				
				boardList.add(boardVo);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return boardList;
	}

	@Override
	public int updateCnt(int boardNo) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set board_cnt=board_cnt+1 where board_no=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return cnt;
	}
	

}
