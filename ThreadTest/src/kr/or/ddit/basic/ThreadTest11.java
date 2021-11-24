package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
	10������ ������ �����ϴ� �渶 ���α׷��� �ۼ��Ͻÿ�.
	
	���� Horse��� �̸��� ������ Ŭ������ �ۼ��ϴµ� 
	�� Ŭ������ ���̸�(String), ������ġ(int), ���(int)�� ��������� ���´�.
	
	�׸���, �� Ŭ������ ����� ������������ ó���� �� �ִ� ���� ���ı����� �ִ�.
	(Comparable�������̽� ����)
	
	��� ������ 1 ~ 50�������� �Ǿ� �ִ�.
	
	��� �� �߰� �߰��� �� ������ ��ġ�� �Ʒ��� ���� ��Ÿ���ÿ�.
	����)
	01����    --->------------------------------------
	02����    -------->-------------------------------
	...
	10����    ------>---------------------------------
	
	��Ⱑ ������ ��� ������ ����Ѵ�.
	
	*�� ��ü�� ������� �ؾ���. (A-Z���� ����ϴ� �Ŷ� �Ȱ���.)
	
 */

public class ThreadTest11 {

	static int strRank = 1;
	
	public static void main(String[] args) {
		
		List<Horse> hor = new ArrayList<>();

		hor.add(new Horse("01����"));
		hor.add(new Horse("02����"));
		hor.add(new Horse("03����"));
		hor.add(new Horse("04����"));
		hor.add(new Horse("05����"));
		hor.add(new Horse("06����"));
		hor.add(new Horse("07����"));
		hor.add(new Horse("08����"));
		hor.add(new Horse("09����"));
		hor.add(new Horse("10����"));
		
		for(Horse hr : hor) {
			hr.start();
			System.out.println();
		}
		
		for(Horse hr : hor) {
			try {
				hr.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		Collections.sort(hor);
			
	}
}

class Horse extends Thread implements Comparable<Horse>{
	private String horseName;
	private int curLocation;
	private int rank;
	
	public Horse(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getCurLocation() {
		return curLocation;
	}

	public void setCurLocation(int curLocation) {
		this.curLocation = curLocation;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	
	@Override
	public String toString() {
		return "Horse [horseName=" + horseName + ", curLocation=" + curLocation + ", rank=" + rank + "]";
	}


	@Override
	public void run() {
		
		
		String[] game = new String[50];
				
		for(int j=0; j<game.length; j++) {
			
			System.out.println();
			System.out.print(horseName + "  ");
			
			for(int i=0; i<game.length; i++) {
				game[i] = "-";
			}

			game[j] = ">";
			if(j>0) game[j-1] = "-";
			
			for(int i=0; i<game.length; i++) {
				System.out.print(game[i]);
			}
			
			System.out.println();
		
			try {
				Thread.sleep((int)(Math.random() * 1000)); 
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
			
		}
		
				
	}
		

	@Override
	public int compareTo(Horse hor) {
		// TODO Auto-generated method stub
		return Integer.compare(rank, hor.getRank());
	}
	
}
