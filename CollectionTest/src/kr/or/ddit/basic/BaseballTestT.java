package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BaseballTestT {
	ArrayList<Integer> numList; //������ ����� ����Ʈ
	ArrayList<Integer> userList; // ����ڰ� �Է��� ���� ����� ����Ʈ
	
	int strike;
	int ball;
	
	Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new BaseballTestT().gameStart();
	}
	
	// ������ ���۵Ǵ� �޼���
	public void gameStart() {
		getNum(); // ���� ����� �޼��� ȣ��
		
		System.out.println("��ǻ�� ����: " + numList);
		
		int cnt = 0; // ������� ���߾������� �����ϴ� ����
		do {
			cnt++;
			
			// ������� �Է��� ó���ϴ� �޼��� ȣ��
			inputNum();
			
			// ��ī��Ʈ�� ���ϴ� �޼��� ȣ��
			ballCount();
			
		}while(strike!=3); // 3��Ʈ����ũ�� �� ������ �ݺ�
		
		System.out.println();
		System.out.println("�����մϴ�.");
		System.out.println("����� " + cnt + "��°���� ������ϴ�.");
		
	}
	
	// 1~9������ ���� �ٸ� ���� 3���� ���� ����Ʈ�� �����ϴ� �޼���
	public void getNum() {
		Set<Integer> numSet = new HashSet<>();
		
		// ���� �����
		while(numSet.size()<3) {
			numSet.add((int)(Math.random() * 9 + 1));			
		}
		
		// ������� ������ ����Ʈ�� �����ϱ�
		numList = new ArrayList<>(numSet);
		
		// ����Ʈ�� ������ �����ش�.
		Collections.shuffle(numList);
	}
	
	// ����ڷκ��� 3���� ������ �Է� �޾� ����Ʈ�� �����ϴ� �޼���
	// �Է��� ������ �ߺ����� �ʰ� �Ѵ�.
	public void inputNum() {
		int n1, n2, n3;	// �Է��� ������ ����� ���� ����
		do {
			System.out.print("�����Է� => ");
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			n3 = scan.nextInt();
			
			if(n1==n2 || n1==n3 || n2==n3) {
				System.out.println("�ߺ��Ǵ� ���ڴ� �Է��� �� �����ϴ�. �ٽ� �Է��ϼ���.");
			}
			
		}while(n1==n2 || n1==n3 || n2==n3);
		
		// �Է¹��� �����͸� List�� �����Ѵ�.
		userList = new ArrayList<>();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
		
	}
	
	// ��Ʈ����ũ�� ���� �����ϰ� ����� ����ϴ� �޼���
	public void ballCount() {
		strike = 0;
		ball = 0;	// ��Ʈ����ũ�� ���� ���� �ʱ�ȭ
		
		for(int i=0; i<numList.size(); i++) {
			for(int j=0; j<userList.size(); j++) {
				if(numList.get(i) == userList.get(j)) {
					if(i==j) strike++;
					else ball++;
				}
			}
		}
		
		// ��ī��Ʈ ��� ����ϱ�
		System.out.println(userList.get(0) + " " + userList.get(1) + 
				" " + userList.get(2) + " ==> " + strike + "S " + ball + "B");
	}
}
