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
LPROD테이블에 새로운 데이터 추가하기(iBatis로 처리하시오.)

상품분류코드(lprod_gu), 상품분류명(lprod_nm)은 직접 입력 받아서 처리하고, 
Lprod_ID는 현재의 Lprod_ID중 제일 큰 값보다 1 크게 한다.
그리고 입력받은 상품분류코드(lprod_gu)가 이미 등록되어 있으면 다시 입력받아서 처리한다.

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
				
				System.out.print("상품 분류 코드(LPROD_GU) 입력: ");
				gu = scan.next();
				
				count = (int) smc.queryForObject("lprod.checkLprodGu", gu);
				
				if(count>0) {
					System.out.println("입력한 상품 분류코드 " + gu + 
							"는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요.");
				}
				
			} while(count>0);
			
			System.out.print("상품 분류명(LPROD_NM) 입력: ");
			String nm = scan.next();
			
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(max);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			Object obj = smc.insert("lprod.insertLprod", lvo);
			if(obj==null) {
				System.out.println("insert작업 성공!!!");
			}else {
				System.out.println("insert작업 실패~~~");
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}



