package kr.or.ddit.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;

/**
 * ���� DB�� �����ؼ� SQL���� �����Ͽ� ����� �ۼ��ؼ� Service���� �����ϴ�
 * DAO�� interface
 */
public interface IMemberDao {
	/**
	 * MemberVO�� ����� �����͸� DB�� insert�ϴ� �޼���
	 * @param memVo DB�� insert�� �ڷᰡ ����� MemberVO��ü
	 * @return �۾� ���� : 1�̻�, �۾� ���� : 0
	 */
	public int insertMember(MemberVO memVo); // �������� �����Ͱ� ���������� �ʿ��� ���
	
	/**
	 * ȸ��ID�� �Ű������� �޾Ƽ� �ش� ȸ�� ������ �����ϴ� �޼���
	 * @param memID ������ ȸ��ID
	 * @return �۾� ���� : 1, �۾� ���� : 0
	 */
	public int deleteMember(String memID); //�ʿ��� �����Ͱ� �ѵΰ��ۿ� ���� ���� �Ű����� �ϳ��� �ᵵ �ȴ�.
	
	/**
	 * MemberVO�ڷḦ �̿��Ͽ� DB�� update�ϴ� �޼���
	 * @param memVo update�� ȸ�������� ����� MemberVO��ü
	 * @return �۾� ���� : 1, �۾� ���� : 0
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * DB�� ȸ�����̺��� ��ü ���ڵ带 �����ͼ� List�� ��Ƽ� ��ȯ�ϴ� �޼���
	 * @return �˻��� �����Ͱ� ����� List��ü
	 */
	public List<MemberVO> getAllMemberList(); // ��ü�� ����Ʈ �ϴ� ���� ������ ������ �ʿ���� ������ �Ű������� �ʿ� ����.
	
	/**
	 * ȸ��ID�� �Ű������� �޾Ƽ� �ش� ȸ���� ������ ��ȯ�ϴ� �޼���
	 * @param memId �˻��� ȸ��ID
	 * @return �˻��� ȸ���� ����
	 */
	public int getMemberCount(String memId);
	
	/**
	 * Map�� ������ �̿��Ͽ� ȸ�� ���� �� ���ϴ� �÷��� �����ϴ� �޼���
	 * Key�� ���� ==> ȸ��ID(memId), �������÷���(field), �����ҵ�����(data)
	 * @param paramMap ȸ��ID, ������ �÷���, ������ �����Ͱ� ����� Map��ü
	 * @return �۾� ���� : 1, �۾� ���� : 0
	 */
	public int memberUpdate2(Map<String, String> paramMap);
	//public int updateMember2(String memId, String updateField, String updateData);
	
	
}
