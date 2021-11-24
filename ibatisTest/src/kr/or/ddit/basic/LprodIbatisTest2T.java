package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;


/*
LPROD���̺� ���ο� ������ �߰��ϱ�

��ǰ�з��ڵ�(lprod_gu), ��ǰ�з���(lprod_nm)�� ���� �Է� �޾Ƽ� ó���ϰ�, 
Lprod_ID�� ������ Lprod_ID�� ���� ū ������ 1 ũ�� �Ѵ�.
�׸��� �Է¹��� ��ǰ�з��ڵ�(lprod_gu)�� �̹� ��ϵǾ� ������ �ٽ� �Է¹޾Ƽ� ó���Ѵ�.

*/

public class LprodIbatisTest2T {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);	// ���� ���ڵ� ����
			
			Reader rd = 
					Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd); //sql�� ����� �� �ִ� ���̹�Ƽ�� Ŭ���̾�Ʈ ��ü ����
			rd.close();
			//------------------------------------------

			// Lprod_ID�� ������ Lprod_ID�� ���� ū ������ 1 ũ�� �Ѵ�.
			//String sql = "select nvl(max(lprod_id),0) from lprod";
		
			int max = (int) smc.queryForObject("lprod.getMaxId2");
			
			
			// �Է¹��� ��ǰ�з��ڵ�(lprod_gu)�� �̹� ��ϵǾ� ������ �ٽ� �Է¹޾Ƽ� ó���Ѵ�.
			
			String gu;
			int count = 0;
			do {
				System.out.print("��ǰ �з� �ڵ�(LPROD_GU) �Է�: ");
				gu = scan.next();
				
				count = 
					(int) smc.queryForObject("lprod.checkLprodGu", gu);
				
				if(count>0) {
					System.out.println("�Է��� ��ǰ �з��ڵ� " + gu + 
							"�� �̹� ��ϵ� �ڵ��Դϴ�.");
					System.out.println("�ٽ� �Է��ϼ���.");
				}
				
			} while(count>0);
			
			//----------------------------------------
			System.out.print("��ǰ �з���(LPROD_NM) �Է�: ");
			String nm = scan.next();
			
			LprodVO lpvo = new LprodVO();
			lpvo.setLprod_gu(gu);
			lpvo.setLprod_id(max);
			lpvo.setLprod_nm(nm);
			
			Object obj = smc.insert("lprod.insertLprod", lpvo);
			
			if(obj==null) {
				System.out.println("��� ����!!!");
			}else {
				System.out.println("��� ����~~~");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
