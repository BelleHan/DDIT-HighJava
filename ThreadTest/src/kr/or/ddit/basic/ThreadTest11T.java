package kr.or.ddit.basic;

import java.util.Arrays;

public class ThreadTest11T {

	public static void main(String[] args) {
		HorseT[] horses = new HorseT[] {
				new HorseT("01����"),new HorseT("02����"),new HorseT("03����"),
				new HorseT("04����"),new HorseT("05����"),new HorseT("06����"),
				new HorseT("07����"),new HorseT("08����"),new HorseT("09����"),
				new HorseT("10����")
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
		System.out.println("��� ��...");
		System.out.println();
		
		// ����� �������� �����ϱ�
		Arrays.sort(horses);
		
		for(HorseT h : horses) {
			System.out.println(h.toString());
		}
		
	}
}

class HorseT extends Thread implements Comparable<HorseT>{
	public static int currentRank = 0;  // ���ְ� ���� ���� ����� ���ϱ� ���� ���� ����
	
	private String horseName;  // ���̸�
	private int position;	   // ���� ��ġ
	private int rank;		   // ���
	
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
		return "���ָ�" + horseName + "��(��) " + rank + "�� �Դϴ�."; 
	}
	
	// ����� ������������ ������ �� �ִ� ���� ���� ���� ����
	@Override
	public int compareTo(HorseT horse) {		
		return Integer.compare(rank, horse.getRank());
	}
	
	// ������ ó��
	@Override
	public void run() {
		for(int i=1; i<=50; i++) {
			this.position = i;		// ���� ���� ��ġ ����
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		} // for�� ��...
		// �� ������ ���� ���ְ� ������ ����� ���ؼ� �����Ѵ�.
		currentRank++;
		this.rank = currentRank;
	}
}

// ��� �� ���� ���� ��ġ�� ����� �ִ� ������
class GameState extends Thread{
	HorseT[] horses;			  // ���ָ� �ϴ� ������ ������ ����� �迭 ���� ����

	// ������
	public GameState(HorseT[] horses) {
		this.horses = horses;
	}
	
	@Override
	public void run() {
		while(true) {
			// ��� ���� ���ְ� �������� ���� �˻�
			if(HorseT.currentRank == horses.length) {
				break;
			}
			
			for(int i=1; i<=9; i++) {
				System.out.println();
			}	
			
			for(int i=0; i<horses.length; i++) {
				System.out.print(horses[i].getHorseName() + " : ");
				for(int j=1; j<=50; j++) {
					// ���� ���� ��ġ�� Ȯ���ؼ� ����Ѵ�.
					if(horses[i].getPosition() == j) {
						System.out.print(">");
					} else {
						System.out.print("-");						
					}			
						
				}
				System.out.println();  // �ٹٲ�
			}
		}
	}
	
}