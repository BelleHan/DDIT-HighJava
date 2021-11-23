package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Scanner;

public class LottoTest {
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		LottoTest lt = new LottoTest();
		Scanner sc = new Scanner(System.in);
		int input = 0;
		
		loop : while(true) {
			System.out.println("==========================");
		    System.out.println("       Lotto 프로그램");         
			System.out.println("--------------------------");
			System.out.println("  1. Lotto 구입"); 
			System.out.println("  2. 프로그램 종료"); 
			System.out.println("==========================");
			System.out.print(" 메뉴선택 : ");
			input = sc.nextInt();
			System.out.println();
			
			switch(input) {
				case 1 : lt.buyLotto();
					break;
				case 2 : System.out.println("감사합니다");
					break loop;		
			}
		}
		
	}
	
	public void buyLotto() {
		HashSet<Integer> lottoSet = new HashSet<>();
		int money = 0;
		System.out.println("Lotto 구입 시작");
		System.out.println();
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		money = sc.nextInt();
		
		if(money < 1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
			System.out.println();
		} else if(money <= 100000) {
			System.out.println("행운의 로또번호는 다음과 같습니다.");
			int i;
			for(i=1; i<=money/1000; i++) {			
				while(lottoSet.size() < 6) {
					int lotto = (int)(Math.random() * 45 + 1);
					lottoSet.add(lotto);				
				}	
				
				System.out.println("로또번호" + i + " : " + lottoSet);
				lottoSet.clear();
			}
			System.out.println();
			System.out.println("받은 금액은 " + money + "이고 거스름돈은 " + (money%1000) + "입니다.");
			System.out.println();
		} else {
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
			System.out.println();
		}
						
	}
	
}
