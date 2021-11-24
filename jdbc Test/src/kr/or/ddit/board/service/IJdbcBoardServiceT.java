package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.JdbcBoardVO;

public interface IJdbcBoardServiceT {

	/**
	 * JdbcBoardVO��ü�� ����� �ڷḦ DB�� insert�ϴ� �޼���
	 * @param boardVo DB�� insert�� �ڷᰡ ����� JdbcBoardVO��ü
	 * @return �۾����� : 1, �۾����� : 0
	 */
	public int insertBoard(JdbcBoardVO boardVo);
	
	/**
	 * �Խñ� ��ȣ�� �Ű������� �޾Ƽ� �ش� �Խñ��� �����ϴ� �޼���
	 * @param boardNo ������ �Խñ� ��ȣ
	 * @return �۾����� : 1, �۾����� : 0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * �ϳ��� JdbcBoardVO�ڷḦ �̿��Ͽ� DB�� update�ϴ� �޼���
	 * @param boardVo update�� �Խñ� ������ ����� JdbcBoardVO��ü
	 * @return �۾����� : 1, �۾����� : 0
	 */
	public int updateBoard(JdbcBoardVO boardVo);
	
	/**
	 * DB�� jdbc_board���̺��� ��ü ���ڵ带 �����ͼ� List�� ��Ƽ� ��ȯ�ϴ� �޼���
	 * @return JdbcBoardVO��ü�� ��� �ִ� List��ü
	 */
	public List<JdbcBoardVO> getAllBoardList();
	
	/**
	 * �Խñ� ��ȣ�� �Ű������� �޾Ƽ� �ش� �Խñ� ������ ������ ��ȯ�ϴ� �޼���
	 * @param boardNo ������ �Խñ� ��ȣ
	 * @return �Խñ۹�ȣ�� �´� �ڷᰡ ������ �ش� �Խñ� ������ ��� �ִ� JdbcBoardVO��ü
	 * 		   , �ڷᰡ ������  null ��ȯ
	 */
	public JdbcBoardVO getBoard(int boardNo);
	
	/**
	 * �Խñ� ������ �̿��Ͽ� �Խñ��� �˻��ϴ� �޼���
	 * @param title �˻��� �Խñ��� ����
	 * @return �˻��� ����� ���� List��ü
	 */
	public List<JdbcBoardVO> getSearchBoardList(String title);
	
	/**
	 * �Խñ� ��ȣ�� �Ű������� �޾Ƽ� �ش� �Խñ��� ��ȸ���� ������Ű�� �޼���
	 * @param boardNo ��ȸ���� ������ �Խñ� ��ȣ
	 * @return �۾����� : 1, �۾����� : 0
	 */
	public int setCountIncrement(int boardNo);
}
