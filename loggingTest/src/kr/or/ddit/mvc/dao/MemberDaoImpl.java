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
	
	// 1��
	private static MemberDaoImpl dao;
	
	// 2��
	private MemberDaoImpl() { }
	
	// 3��
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		
		return dao;
	}
	

	@Override // logger.info (������)
	public int insertMember(MemberVO memVo) {
		int cnt = 0; // ��ȯ���� ����� ����
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
			
			logger.info("PreparedStatement��ü ����");
			logger.info("���� SQL : " + sql);
			logger.info("��뵥���� : [" + memVo.getMem_id() + ", " +
					memVo.getMem_pass() + ", " + memVo.getMem_name() +
					", " + memVo.getMem_tel() + ", " + memVo.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			logger.info("insert �۾� ����");
			
		} catch (SQLException e) {
			logger.info("insert �۾� ����!! : " + e);
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { 
				pstmt.close(); 
				logger.info("PreparedStatement��ü �ݳ� ����...");
			} catch(SQLException e) {}
			if(conn!=null) try { 
				conn.close(); 
				logger.info("Connection��ü �ݳ� ����...");
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
			
			logger.info("PreparedStatement��ü ����");
			logger.info("����  SQL : " + sql);
			logger.info("��� ������ : " + memId);
			
			cnt = pstmt.executeUpdate();
			logger.info("delete �۾� ����");
			
		} catch (SQLException e) {
			logger.info("delete �۾� ����!!" + e);
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { 
				pstmt.close(); 
				logger.info("PreparedStatement��ü �ݳ� ����...");
			} catch(SQLException e) {}
			if(conn!=null) try { 
				conn.close(); 
				logger.info("Connection��ü �ݳ� ����...");
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
			
			logger.info("PreparedStatement��ü ����");
			logger.info("���� SQL : " + sql);
			logger.info("��뵥���� : [" + memVo.getMem_id() + ", " +
					memVo.getMem_pass() + ", " + memVo.getMem_name() +
					", " + memVo.getMem_tel() + ", " + memVo.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			logger.info("update �۾� ����");
			
		} catch (SQLException e) {
			logger.info("update �۾� ����!! : " + e);
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { 
				pstmt.close(); 
				logger.info("PreparedStatement��ü �ݳ� ����...");
			} catch(SQLException e) {}
			if(conn!=null) try { 
				conn.close(); 
				logger.info("Connection��ü �ݳ� ����...");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override // logger.info (������)
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			logger.info("Statement��ü ����");
			logger.info("���� SQL�� : " + sql);
			
			rs = stmt.executeQuery(sql);
			
			logger.info("SQL�� ���� ���");
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
			logger.info("select �۾� ����!! : " + e);
			e.printStackTrace();
		} finally { 
			if(rs!=null) try { 
				rs.close(); 
				logger.info("ResultSet��ü �ݳ� ����...");
			} catch(SQLException e) {}
			if(stmt!=null) try { 
				stmt.close(); 
				logger.info("Statement��ü �ݳ� ����...");
			} catch(SQLException e) {}
			if(conn!=null) try { 
				conn.close(); 
				logger.info("Connection��ü �ݳ� ����...");
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
			
			logger.info("PreparedStatement��ü ����");
			logger.info("���� SQL : " + sql);
			   
			rs = pstmt.executeQuery();
			logger.info("select �۾� ����");
			   
			if(rs.next()) {
				 cnt = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			logger.info("�۾� ���� : " + e);
			e.printStackTrace();
		} finally { 
			if(rs!=null) try { 
				rs.close(); 
				logger.info("ResultSet��ü �ݳ� ����...");
			} catch(SQLException e) {}
			if(pstmt!=null) try { 
				pstmt.close(); 
				logger.info("Statement��ü �ݳ� ����...");
			} catch(SQLException e) {}
			if(conn!=null) try { 
				conn.close(); 
				logger.info("Connection��ü �ݳ� ����...");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int memberUpdate2(Map<String, String> paramMap) {
		//  Key�� ���� ==> ȸ��ID(memId), �������÷���(field), �����ҵ�����(data)
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
			
			logger.info("PreparedStatement��ü ����");
			logger.info("���� SQL : " + sql);
			logger.info("��뵥���� : [" + paramMap.get("data") + " : " + paramMap.get("memId") + "]");
			
			cnt = pstmt.executeUpdate();
			logger.info("update �۾� ����");
			
		} catch (SQLException e) {
			logger.info("update �۾� ����!! : " + e);
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { 
				pstmt.close(); 
				logger.info("Statement��ü �ݳ� ����...");
			} catch(SQLException e) {}
			if(conn!=null) try { 
				conn.close(); 
				logger.info("Connection��ü �ݳ� ����...");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}


}
