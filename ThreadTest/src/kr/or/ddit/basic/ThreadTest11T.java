package kr.or.ddit.basic;

import java.util.Arrays;

public class ThreadTest11T {

	public static void main(String[] args) {
		HorseT[] horses = new HorseT[] {
				new HorseT("01번말"),new HorseT("02번말"),new HorseT("03번말"),
				new HorseT("04번말"),new HorseT("05번말"),new HorseT("06번말"),
				new HorseT("07번말"),new HorseT("08번말"),new HorseT("09번말"),
				new HorseT("10번말")
		};
		GameState gs = new GameState(horses);
		
		for(HorseT h : horses) {
			h.start();
		}
		gs.start();
		
		try {
			for(HorseT h : horses) {
				h.join();
			}
			gs.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println();
		System.out.println("경기 끝...");
		System.out.println();
		
		// 등수의 오름차순 정렬하기
		Arrays.sort(horses);
		
		for(HorseT h : horses) {
			System.out.println(h.toString());
		}
		
	}
}

class HorseT extends Thread implements Comparable<HorseT>{
	public static int currentRank = 0;  // 경주가 끝난 말의 등수를 구하기 위한 변수 선언
	
	private String horseName;  // 말이름
	private int position;	   // 현재 위치
	private int rank;		   // 등수
	
	public HorseT(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "경주마" + horseName + "은(는) " + rank + "등 입니다."; 
	}
	
	// 등수를 오름차순으로 정렬할 수 있는 내부 정렬 기준 설정
	@Override
	public int compareTo(HorseT horse) {		
		return Integer.compare(rank, horse.getRank());
	}
	
	// 쓰레드 처리
	@Override
	public void run() {
		for(int i=1; i<=50; i++) {
			this.position = i;		// 말의 현재 위치 저장
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		} // for문 끝...
		// 한 마리의 말이 경주가 끝나면 등수를 구해서 설정한다.
		currentRank++;
		this.rank = currentRank;
	}
}

// 경기 중 말의 현재 위치를 출력해 주는 쓰레드
class GameState extends Thread{
	HorseT[] horses;			  // 경주를 하는 말들의 정보가 저장될 배열 변수 선언

	// 생성자
	public GameState(HorseT[] horses) {
		this.horses = horses;
	}
	
	@Override
	public void run() {
		while(true) {
			// 모든 말의 경주가 끝났는지 여부 검사
			if(HorseT.currentRank == horses.length) {
				break;
			}
			
			for(int i=1; i<=9; i++) {
				System.out.println();
			}	
			
			for(int i=0; i<horses.length; i++) {
				System.out.print(horses[i].getHorseName() + " : ");
				for(int j=1; j<=50; j++) {
					// 현재 말의 위치를 확인해서 출력한다.
					if(horses[i].getPosition() == j) {
						System.out.print(">");
					} else {
						System.out.print("-");						
					}			
						
				}
				System.out.println();  // 줄바꿈
			}
		}
	}
	
}