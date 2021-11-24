package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao{
	private static final Logger logger = 
			Logger.getLogger(MemberDaoImpl.class);
	
	// 1번
	private static MemberDaoImpl dao;
	
	// 2번
	private MemberDaoImpl() { }
	
	// 3번
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		
		return dao;
	}
	

	@Override // logger.info (선생님)
	public int insertMember(MemberVO memVo) {
		int cnt = 0; // 반환값이 저장될 변수
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into mymember (mem_id, mem_pass, mem_name,"
					+ "mem_tel, mem_addr) values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_pass());
			pstmt.setString(3, memVo.getMem_name());
			pstmt.setString(4, memVo.getMem_tel());
			pstmt.setString(5, memVo.getMem_addr());
			
			logger.info("PreparedStatement객체 생성");
			logger.info("실행 SQL : " + sql);
			logger.info("사용데이터 : [" + memVo.getMem_id() + ", " +
					memVo.getMem_pass() + ", " + memVo.getMem_name() +
					", " + memVo.getMem_tel() + ", " + memVo.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			logger.info("insert 작업 성공");
			
		} catch (SQLException e) {
			logger.info("insert 작업 실패!! : " + e);
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { 
				pstmt.close(); 
				logger.info("PreparedStatement객체 반납 성공...");
			} catch(SQLException e) {}
			if(conn!=null) try { 
				conn.close(); 
				logger.info("Connection객체 반납 성공...");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			logger.info("PreparedStatement객체 생성");
			logger.info("실행  SQL : " + sql);
			logger.info("사용 데이터 : " + memId);
			
			cnt = pstmt.executeUpdate();
			logger.info("delete 작업 성공");
			
		} catch (SQLException e) {
			logger.info("delete 작업 실패!!" + e);
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { 
				pstmt.close(); 
				logger.info("PreparedStatement객체 반납 성공...");
			} catch(SQLException e) {}
			if(conn!=null) try { 
				conn.close(); 
				logger.info("Connection객체 반납 성공...");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "update mymember set mem_pass=? ,"
					   + "mem_name=?, mem_tel=?, mem_addr=?"
					   + "where mem_id=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_pass());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			pstmt.setString(5, memVo.getMem_id());
			
			logger.info("PreparedStatement객체 생성");
			logger.info("실행 SQL : " + sql);
			logger.info("사용데이터 : [" + memVo.getMem_id() + ", " +
					memVo.getMem_pass() + ", " + memVo.getMem_name() +
					", " + memVo.getMem_tel() + ", " + memVo.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			logger.info("update 작업 성공");
			
		} catch (SQLException e) {
			logger.info("update 작업 실패!! : " + e);
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { 
				pstmt.close(); 
				logger.info("PreparedStatement객체 반납 성공...");
			} catch(SQLException e) {}
			if(conn!=null) try { 
				conn.close(); 
				logger.info("Connection객체 반납 성공...");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override // logger.info (선생님)
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			logger.info("Statement객체 생성");
			logger.info("실행 SQL문 : " + sql);
			
			rs = stmt.executeQuery(sql);
			
			logger.info("SQL문 실행 결과");
			while(rs.next()) {
				MemberVO memVo = new MemberVO();
				String memId = rs.getString("mem_id");
		    	String memPass = rs.getString("mem_pass");
		    	String memName = rs.getString("mem_name");
		    	String memTel = rs.getString("mem_tel");
		    	String memAddr = rs.getString("mem_addr");
		    	memVo.setMem_id(memId);
		    	memVo.setMem_pass(memPass);
		    	memVo.setMem_name(memName);
		    	memVo.setMem_tel(memTel);
		    	memVo.setMem_addr(memAddr);
		    	
		    	memList.add(memVo);
		    	logger.info("[" + memId + ", " + memPass + ", " +
		    			memName + ", " + memTel + ", " + memAddr);
			}
			
		} catch (SQLException e) {
			logger.info("select 작업 실패!! : " + e);
			e.printStackTrace();
		} finally { 
			if(rs!=null) try { 
				rs.close(); 
				logger.info("ResultSet객체 반납 성공...");
			} catch(SQLException e) {}
			if(stmt!=null) try { 
				stmt.close(); 
				logger.info("Statement객체 반납 성공...");
			} catch(SQLException e) {}
			if(conn!=null) try { 
				conn.close(); 
				logger.info("Connection객체 반납 성공...");
			} catch(SQLException e) {}
		}
		
		return memList;
		
	}

	@Override
	public int getMemberCount(String memId) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			logger.info("PreparedStatement객체 생성");
			logger.info("실행 SQL : " + sql);
			   
			rs = pstmt.executeQuery();
			logger.info("select 작업 성공");
			   
			if(rs.next()) {
				 cnt = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			logger.info("작업 실패 : " + e);
			e.printStackTrace();
		} finally { 
			if(rs!=null) try { 
				rs.close(); 
				logger.info("ResultSet객체 반납 성공...");
			} catch(SQLException e) {}
			if(pstmt!=null) try { 
				pstmt.close(); 
				logger.info("Statement객체 반납 성공...");
			} catch(SQLException e) {}
			if(conn!=null) try { 
				conn.close(); 
				logger.info("Connection객체 반납 성공...");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int memberUpdate2(Map<String, String> paramMap) {
		//  Key값 정보 ==> 회원ID(memId), 수정할컬럼명(field), 수정할데이터(data)
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "update mymember set " + paramMap.get("field") + " = ? "
					   + " where mem_id= ?";
			   
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memId"));
			
			logger.info("PreparedStatement객체 생성");
			logger.info("실행 SQL : " + sql);
			logger.info("사용데이터 : [" + paramMap.get("data") + " : " + paramMap.get("memId") + "]");
			
			cnt = pstmt.executeUpdate();
			logger.info("update 작업 성공");
			
		} catch (SQLException e) {
			logger.info("update 작업 실패!! : " + e);
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { 
				pstmt.close(); 
				logger.info("Statement객체 반납 성공...");
			} catch(SQLException e) {}
			if(conn!=null) try { 
				conn.close(); 
				logger.info("Connection객체 반납 성공...");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}


}
