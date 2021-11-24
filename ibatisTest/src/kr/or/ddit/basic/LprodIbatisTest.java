package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class LprodIbatisTest {
	// iBatis�� �̿��Ͽ� DB�ڷḦ ó���ϴ� ���� �� ���
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// 1. iBatis�� ȯ�漳������(sqlMapConfig.xml)�� �о�� �����Ѵ�.
		try {
			// 1-1. ���� ���ڵ� ĳ���ͼ� ���� 
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			// 1-2. ȯ�� ���� ������ �о�´�.
			Reader rd = 
				Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
			// 1-3. ������ �о�� Reader��ü�� �̿��Ͽ� ���� ȯ�� ������ �ϼ��� �� 
			// 		�ۼ��� SQL���� ȣ���ؼ� ó���� ��ü�� �����Ѵ�.
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();  // Reader��ü �ݱ�
			
			// -------------------------------------------------
			
			// 2. ������ SQL���� �´� �������� ȣ���ؼ� ���ϴ� �۾��� �����Ѵ�.
			
			// 2-1. insert ����
			/*
			System.out.println("insert �۾� ����...");
			System.out.print("lprod_id �Է�: ");
			int lprodId = scan.nextInt();
			
			System.out.print("lprod_gu �Է�: ");
			String lprodGu = scan.next();
			
			
			System.out.print("lprod_nm �Է�: ");
			String lprodNm = scan.next();
			
			//1) ������ �����͸� VO��ü�� �����Ѵ�.
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(lprodId);
			lvo.setLprod_gu(lprodGu);
			lvo.setLprod_nm(lprodNm);
			
			//2) sqlMapClient ��ü������ �̿��ؼ� ó����  �������� ȣ���Ͽ� �����Ѵ�.
			// ����) smc.insert("namespace�Ӽ���.id�Ӽ���", �Ķ����Ŭ����);
			//		��ȯ�� : insert���� : null, ���� : ������ü
			Object obj = smc.insert("lprod.insertLprod", lvo);
			if(obj==null) {
				System.out.println("insert�۾� ����!!!");
			}else {
				System.out.println("insert�۾� ����~~~");
			}
			*/
			
			/*
			// 2-2. update ����
			System.out.println("update �۾� ����...");
			System.out.print("������ lprod_gu �Է�: ");
			String lprodGu2 = scan.next();
			
			System.out.print("���ο� lprod_id �Է�: ");
			int lprodId2 = scan.nextInt();
			
			System.out.print("���ο� lprod_nm �Է�: ");
			String lprodNm2 = scan.next();
			
			//1) ������ �����͸� VO��ü�� �����Ѵ�.
			LprodVO lvo2 = new LprodVO();
			lvo2.setLprod_id(lprodId2);
			lvo2.setLprod_gu(lprodGu2);
			lvo2.setLprod_nm(lprodNm2);
			
			//2) ����) smc.update("namespace�Ӽ���.id�Ӽ���", �Ķ����Ŭ����)
			//		��ȯ�� : �۾��� ������ ���ڵ� ��
			int cnt = smc.update("lprod.updateLprod", lvo2);
			if(cnt>0) {
				System.out.println("update ����!!!");
			}else {
				System.out.println("update ����~~~");
			}
			*/
			
			/*
			// 2-3. delete ����
			System.out.println("delete ����...");
			System.out.print("������ lprod_gu �Է�: ");
			String lprodGu3 = scan.next();
			
			// ����) smc.delete("namespace�Ӽ���.id�Ӽ���", �Ķ����Ŭ����);
			//		��ȯ�� : �۾��� ������ ���ڵ� ��
			int cnt2 = smc.delete("lprod.deleteLprod", lprodGu3);
			if(cnt2>0) {
				System.out.println("���� ����!!!");
			}else {
				System.out.println("���� ����~~~");
			}
			*/
			
			// 2-4. select ����
			System.out.println("select ���� ����...");
			
			/*
			// 1) ������ ����� �������� ���ڵ� �� ���
			System.out.println("1) ���� ����� �������� ���ڵ��� ���");
			// ����) smc.queryForList("namespace�Ӽ���.id�Ӽ���", �Ķ����Ŭ����);
			// 	==> queryForList()�޼���� �������� ���ڵ� ������ VO�� ���� ��
			//		�� VO�����͸� List�� �߰��� �ִ� �۾��� �ڵ����� �����Ѵ�.
			List<LprodVO> lprodList = smc.queryForList("lprod.getAllLprod");
			
			for(LprodVO lpvo : lprodList) {
				System.out.println("ID : " + lpvo.getLprod_id());
				System.out.println("GU : " + lpvo.getLprod_gu());
				System.out.println("NM : " + lpvo.getLprod_nm());
				System.out.println("-------------------------------");
			}
			System.out.println("��� ��...");
			*/
			
			System.out.println("2) ���� ����� 1���� ���ڵ��� ���");
			// ���� ����� 1���� ���ڵ��� ��쿡�� queryForObject()�޼��带 ����Ѵ�.
			// ����) smc.queryForObject("namespace�Ӽ���.id�Ӽ���", �Ķ����Ŭ����);
			//		��ȯ�� : ���� ����� �������� ��� ==> ���� Exception�� ��ȯ
			//			  1���� ��� ==> �ش� ��ü ��ȯ (����)
			//			      ���� ��� ==> null ��ȯ
			System.out.print("�˻��� Lprod_gu �Է�: ");
			String lprodGu4 = scan.next();
			
			LprodVO lpvo2 = 
					(LprodVO) smc.queryForObject("lprod.getLprod", lprodGu4);
			
			if(lpvo2==null) {
				System.out.println("�˻��� �����Ͱ� �ϳ��� �����ϴ�.");
				return;
			}
			
			System.out.println("ID : " + lpvo2.getLprod_id());
			System.out.println("GU : " + lpvo2.getLprod_gu());
			System.out.println("NM : " + lpvo2.getLprod_nm());
			System.out.println("-------------------------------");
			System.out.println("��� ��...");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
