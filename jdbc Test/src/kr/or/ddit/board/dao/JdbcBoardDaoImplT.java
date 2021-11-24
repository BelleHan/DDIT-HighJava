package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.DBUtil3;

public class JdbcBoardDaoImplT implements IJdbcBoardDaoT {
	
	private static JdbcBoardDaoImplT dao;
	
	private JdbcBoardDaoImplT() { }
	
	public static JdbcBoardDaoImplT getInstance() {
		if(dao==null) dao = new JdbcBoardDaoImplT();
		return dao;
	}
	
	// DB�۾��� �ʿ��� ��ü������ ����
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// ����ߴ� �ڿ��� �ݳ��ϴ� �޼���
	private void disConnect() {
		if(rs!=null) try { rs.close(); }catch(SQLException e) {}
		if(stmt!=null) try { stmt.close(); }catch(SQLException e) {}
		if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
		if(conn!=null) try { conn.close(); }catch(SQLException e) {}
	}
	

	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "insert into jdbc_board (board_no, board_title, "
					+ "board_writer, board_date, board_cnt, board_content) "
					+ "values( board_seq.nextVal, ?, ?, sysdate, 0, ? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "delete from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set "
					+ "board_title = ? ,"
					+ "board_content = ?, "
					+ "board_date = sysdate "
					+ "where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3, boardVo.getBoard_no());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return cnt;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		List<JdbcBoardVO> boardList = new ArrayList<>();
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select board_no, board_title, board_writer,"
					+ "to_char(board_date, 'YYYY-MM-DD') board_date, "
					+ "board_cnt, board_content "
					+ "from jdbc_board "
					+ "order by board_no desc ";
					
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// ���ڵ� �ϳ��ϳ��� VO��ü�� �����ϰ� ���ε� VO��ü�� List�� �߰��Ѵ�.
			while(rs.next()) {
				JdbcBoardVO boardVo = new JdbcBoardVO();
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
				
				boardList.add(boardVo);
				
			}
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally { 
			disConnect();
		}
		
		return boardList;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		JdbcBoardVO boardVo = null;
		try {
			conn = DBUtil3.getConnection();
			String sql =  "select board_no, board_title, board_writer,"
					+ "to_char(board_date, 'YYYY-MM-DD') board_date, "
					+ "board_cnt, board_content "
					+ "from jdbc_board "
					+ "where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardVo = new JdbcBoardVO();
				
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
			}
			
		} catch (SQLException e) {
			boardVo = null;
			e.printStackTrace();
		} finally {
			disConnect();
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
			conn = DBUtil3.getConnection();
			
			String sql = "select board_no, board_title, board_writer,"
					+ "to_char(board_date, 'YYYY-MM-DD') board_date, "
					+ "board_cnt, board_content "
					+ "from jdbc_board "
					+ "where board_title like '%' || ? || '%' " // ?�� ���� ����ǥ �ȿ� �ȵ��� �ؾ���
					+ "order by board_no desc ";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			
			// ���ڵ� �ϳ��ϳ��� VO��ü�� �����ϰ� ���ε� VO��ü�� List�� �߰��Ѵ�.
			while(rs.next()) {
				JdbcBoardVO boardVo = new JdbcBoardVO();
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
				
				boardList.add(boardVo);
				
			}
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally { 
			disConnect();
		}
		
		return boardList;

	}

	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set "
					+ " board_cnt = board_cnt + 1 "
					+ " where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

}
