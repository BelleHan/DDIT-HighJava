package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
	10마리의 말들이 경주하는 경마 프로그램을 작성하시오.
	
	말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 
	이 클래스는 말이름(String), 현재위치(int), 등수(int)를 멤버변수로 갖는다.
	
	그리고, 이 클래스는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준이 있다.
	(Comparable인터페이스 구현)
	
	경기 구간은 1 ~ 50구간으로 되어 있다.
	
	경기 중 중간 중간에 각 말들의 위치를 아래와 같이 나타내시오.
	예시)
	01번말    --->------------------------------------
	02번말    -------->-------------------------------
	...
	10번말    ------>---------------------------------
	
	경기가 끝나면 등수 순으로 출력한다.
	
	*말 자체를 쓰레드로 해야함. (A-Z까지 출력하는 거랑 똑같다.)
	
 */

public class ThreadTest11 {

	static int strRank = 1;
	
	public static void main(String[] args) {
		
		List<Horse> hor = new ArrayList<>();

		hor.add(new Horse("01번말"));
		hor.add(new Horse("02번말"));
		hor.add(new Horse("03번말"));
		hor.add(new Horse("04번말"));
		hor.add(new Horse("05번말"));
		hor.add(new Horse("06번말"));
		hor.add(new Horse("07번말"));
		hor.add(new Horse("08번말"));
		hor.add(new Horse("09번말"));
		hor.add(new Horse("10번말"));
		
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
