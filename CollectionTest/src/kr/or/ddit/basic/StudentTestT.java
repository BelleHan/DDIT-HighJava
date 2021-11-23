package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/*
	문제) 학번(int), 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수(int)를
		 멤버로 갖는 Student클래스를 만든다.
		 이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로
		 받아서 초기화 처리를 한다.
		 
		 이 Student객체는 List에 저장하여 관리한다.
		 
		 List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
		 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되는
		 외부 정렬 기준 클래스를 작성하여 정렬된 결과를 출력하시오.
		 
		 (단, 등수는 List에 전체 데이터가 추가된 후에 구하도록 한다.)
		 
*/

public class StudentTestT {
	// 등수를 구하는 메서드
	public void setRanking(List<StudentT> stdList) {
		for(StudentT std1 : stdList) {	// 기준 데이터를 구하기 위한 반복문
			int rank = 1;	// 처음에는 1등으로 설정해 놓고 시작한다.
			for(StudentT std2 : stdList) {	// 비교 대상을 나타내는 반복문
				// 기준보다 큰 값을 만나면 rank값을 증가시킨다.
				if(std1.getTot() < std2.getTot()) {
					rank++;
				}
			} // 비교 대상 반복문 끝...
			
			std1.setRank(rank); // 구해진 등수를 Student객체의 rank변수에 저장한다.
		}
	}
	

	public static void main(String[] args) {
		StudentTestT stdTest = new StudentTestT();
		
		List<StudentT> stdList = new ArrayList<StudentT>();
		stdList.add(new StudentT(1, "홍길동", 90, 95, 80));
		stdList.add(new StudentT(3, "홍차차", 90, 75, 70));
		stdList.add(new StudentT(2, "강감찬", 100, 95, 70));
		stdList.add(new StudentT(4, "한치치", 20, 65, 50));
		stdList.add(new StudentT(7, "성길동", 50, 80, 85));
		stdList.add(new StudentT(5, "오나라", 95, 65, 80));
		
		// 등수 구하는 메서드 호출하기
		stdTest.setRanking(stdList);
		
		System.out.println("정렬 전...");
		for(StudentT std : stdList) {
			System.out.println(std);
		}
		System.out.println("----------------------");
		
		// 학번의 오름차순으로 정렬하기 - 내부 정렬 기준 적용
		Collections.sort(stdList);
		
		System.out.println("학번의 오름차순 정렬 후...(내부 정렬 기준 적용)");
		for(StudentT std : stdList) {
			System.out.println(std);
		}
		System.out.println("----------------------");

		// 총점의 역수으로 정렬하기 - 외부 정렬 기준 적용
		Collections.sort(stdList, new SortByTotal());
		
		System.out.println("총점의 내림차순 정렬 후...(외부 정렬 기준 적용)");
		for(StudentT std : stdList) {
			System.out.println(std);
		}
		System.out.println("----------------------");
	}

}

class StudentT implements Comparable<StudentT>{
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private int rank;
	
	public StudentT(int num, String name, int kor, int eng, int mat) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		tot = kor + eng + mat;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", tot="
				+ tot + ", rank=" + rank + "]";
	}

	// 학번의 오름차순
	@Override
	public int compareTo(StudentT std) {		
		return Integer.compare(num, std.getNum());
	}
		
}

// 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되는 외부 정렬 기준 클래스를 작성
class SortByTotal implements Comparator<StudentT>{

	@Override
	public int compare(StudentT std1, StudentT std2) {
		if(std1.getTot() == std2.getTot()) {
			return std1.getName().compareTo(std2.getName());
		} else {
			return Integer.compare(std1.getTot(), std2.getTot()) * -1;
		}
	}
	
	
}