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
LPROD테이블에 새로운 데이터 추가하기

상품분류코드(lprod_gu), 상품분류명(lprod_nm)은 직접 입력 받아서 처리하고, 
Lprod_ID는 현재의 Lprod_ID중 제일 큰 값보다 1 크게 한다.
그리고 입력받은 상품분류코드(lprod_gu)가 이미 등록되어 있으면 다시 입력받아서 처리한다.

*/

public class LprodIbatisTest2T {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);	// 문자 인코딩 지정
			
			Reader rd = 
					Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd); //sql문 사용할 수 있는 아이바티스 클라이언트 객체 생성
			rd.close();
			//------------------------------------------

			// Lprod_ID는 현재의 Lprod_ID중 제일 큰 값보다 1 크게 한다.
			//String sql = "select nvl(max(lprod_id),0) from lprod";
		
			int max = (int) smc.queryForObject("lprod.getMaxId2");
			
			
			// 입력받은 상품분류코드(lprod_gu)가 이미 등록되어 있으면 다시 입력받아서 처리한다.
			
			String gu;
			int count = 0;
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력: ");
				gu = scan.next();
				
				count = 
					(int) smc.queryForObject("lprod.checkLprodGu", gu);
				
				if(count>0) {
					System.out.println("입력한 상품 분류코드 " + gu + 
							"는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요.");
				}
				
			} while(count>0);
			
			//----------------------------------------
			System.out.print("상품 분류명(LPROD_NM) 입력: ");
			String nm = scan.next();
			
			LprodVO lpvo = new LprodVO();
			lpvo.setLprod_gu(gu);
			lpvo.setLprod_id(max);
			lpvo.setLprod_nm(nm);
			
			Object obj = smc.insert("lprod.insertLprod", lpvo);
			
			if(obj==null) {
				System.out.println("등록 성공!!!");
			}else {
				System.out.println("등록 실패~~~");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
