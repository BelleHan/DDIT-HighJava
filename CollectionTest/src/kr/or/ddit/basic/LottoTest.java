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
		    System.out.println("       Lotto ���α׷�");         
			System.out.println("--------------------------");
			System.out.println("  1. Lotto ����"); 
			System.out.println("  2. ���α׷� ����"); 
			System.out.println("==========================");
			System.out.print(" �޴����� : ");
			input = sc.nextInt();
			System.out.println();
			
			switch(input) {
				case 1 : lt.buyLotto();
					break;
				case 2 : System.out.println("�����մϴ�");
					break loop;		
			}
		}
		
	}
	
	public void buyLotto() {
		HashSet<Integer> lottoSet = new HashSet<>();
		int money = 0;
		System.out.println("Lotto ���� ����");
		System.out.println();
		System.out.println("(1000���� �ζǹ�ȣ �ϳ��Դϴ�.)");
		System.out.print("�ݾ� �Է� : ");
		money = sc.nextInt();
		
		if(money < 1000) {
			System.out.println("�Է� �ݾ��� �ʹ� �����ϴ�. �ζǹ�ȣ ���� ����!!!");
			System.out.println();
		} else if(money <= 100000) {
			System.out.println("����� �ζǹ�ȣ�� ������ �����ϴ�.");
			int i;
			for(i=1; i<=money/1000; i++) {			
				while(lottoSet.size() < 6) {
					int lotto = (int)(Math.random() * 45 + 1);
					lottoSet.add(lotto);				
				}	
				
				System.out.println("�ζǹ�ȣ" + i + " : " + lottoSet);
				lottoSet.clear();
			}
			System.out.println();
			System.out.println("���� �ݾ��� " + money + "�̰� �Ž������� " + (money%1000) + "�Դϴ�.");
			System.out.println();
		} else {
			System.out.println("�Է� �ݾ��� �ʹ� �����ϴ�. �ζǹ�ȣ ���� ����!!!");
			System.out.println();
		}
						
	}
	
}
