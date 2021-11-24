package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/*
LPROD���̺� ���ο� ������ �߰��ϱ�(iBatis�� ó���Ͻÿ�.)

��ǰ�з��ڵ�(lprod_gu), ��ǰ�з���(lprod_nm)�� ���� �Է� �޾Ƽ� ó���ϰ�, 
Lprod_ID�� ������ Lprod_ID�� ���� ū ������ 1 ũ�� �Ѵ�.
�׸��� �Է¹��� ��ǰ�з��ڵ�(lprod_gu)�� �̹� ��ϵǾ� ������ �ٽ� �Է¹޾Ƽ� ó���Ѵ�.

*/
public class LprodIbatisTest2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
			
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();
			
			int max = (int) smc.queryForObject("lprod.getMaxId");
			
			max++;
			
			int count = 0;
			String gu = null;
			do {
				
				System.out.print("��ǰ �з� �ڵ�(LPROD_GU) �Է�: ");
				gu = scan.next();
				
				count = (int) smc.queryForObject("lprod.checkLprodGu", gu);
				
				if(count>0) {
					System.out.println("�Է��� ��ǰ �з��ڵ� " + gu + 
							"�� �̹� ��ϵ� �ڵ��Դϴ�.");
					System.out.println("�ٽ� �Է��ϼ���.");
				}
				
			} while(count>0);
			
			System.out.print("��ǰ �з���(LPROD_NM) �Է�: ");
			String nm = scan.next();
			
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(max);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			Object obj = smc.insert("lprod.insertLprod", lvo);
			if(obj==null) {
				System.out.println("insert�۾� ����!!!");
			}else {
				System.out.println("insert�۾� ����~~~");
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}



