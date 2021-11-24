package kr.or.ddit.mvc.dao;

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
import java.util.Map;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.mvc.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao{
	// 1��
	private static MemberDaoImpl dao;
	private SqlMapClient smc;  // iBatis��ü�� ����� ���� ����
	
	// 2��
	private MemberDaoImpl() {
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
	
	// 3��
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		
		return dao;
	}
	

	@Override
	public int insertMember(MemberVO memVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("member.insertMember", memVo);
			if(obj==null) cnt = 1;
		
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}


	
	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("member.deleteMember", memId);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("member.updateMember", memVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			memList = smc.queryForList("member.getAllMember");
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		int count = 0;
		
		try {
			count = (int) smc.queryForObject("member.getCountMember", memId);
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int memberUpdate2(Map<String, String> paramMap) {
		//  Key�� ���� ==> ȸ��ID(memId), �������÷���(field), �����ҵ�����(data)
		int cnt = 0;
		try {
			cnt = smc.update("member.updateMember2", paramMap);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}


	
}
