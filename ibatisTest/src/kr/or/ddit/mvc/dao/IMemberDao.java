package kr.or.ddit.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서 Service에게 전달하는
 * DAO의 interface
 */
public interface IMemberDao {
	/**
	 * MemberVO에 담겨진 데이터를 DB에 insert하는 메서드
	 * @param memVo DB에 insert할 자료가 저장될 MemberVO객체
	 * @return 작업 성공 : 1이상, 작업 실패 : 0
	 */
	public int insertMember(MemberVO memVo); // 여러가지 데이터가 복합적으로 필요한 경우
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param memID 삭제할 회원ID
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int deleteMember(String memID); //필요한 데이터가 한두개밖에 없을 때는 매개변수 하나만 써도 된다.
	
	/**
	 * MemberVO자료를 이용하여 DB에 update하는 메서드
	 * @param memVo update할 회원정보가 저장된 MemberVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * DB의 회원테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return 검색된 데이터가 저장된 List객체
	 */
	public List<MemberVO> getAllMemberList(); // 전체를 셀렉트 하는 것은 별도의 조건이 필요없기 때문에 매개변수가 필요 없다.
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원의 개수를 반환하는 메서드
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원의 개수
	 */
	public int getMemberCount(String memId);
	
	/**
	 * Map의 정보를 이용하여 회원 정보 중 원하는 컬럼을 수정하는 메서드
	 * Key값 정보 ==> 회원ID(memId), 수정할컬럼명(field), 수정할데이터(data)
	 * @param paramMap 회원ID, 수정할 컬럼명, 수정할 데이터가 저장된 Map객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int memberUpdate2(Map<String, String> paramMap);
	//public int updateMember2(String memId, String updateField, String updateData);
	
	
}
