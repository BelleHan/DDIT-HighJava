package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	
public class StudentTest {
	
	public static void main(String[] args) {
		ArrayList<Student> stList = new ArrayList<>();
		
		stList.add(new Student(5, "유재석", 90, 100, 95));
		stList.add(new Student(7, "박명수", 75, 55, 60));
		stList.add(new Student(11, "노홍철", 85, 100, 100));
		stList.add(new Student(1, "정준하", 40, 65, 95));
		stList.add(new Student(3, "하하", 90, 70, 25));
		stList.add(new Student(32, "정형돈", 30, 75, 65));
				
		System.out.println("정렬 전...");
		for(Student st : stList) {
			System.out.println(st);
		}
		System.out.println("----------------------");
		
		Collections.sort(stList);
		
		System.out.println("학번의 오름차순(내부 정렬) 후...");
		for(Student st : stList) {
			System.out.println(st);
		}
		System.out.println("----------------------");
		
		Collections.shuffle(stList);
		
		System.out.println("섞기 후...");
		for(Student st : stList) {
			System.out.println(st);
		}
		System.out.println("----------------------");
		
		Collections.sort(stList, new ScoreDesc());
		
		System.out.println("총점의 역순 정렬 후...");
		for(Student st : stList) {
			System.out.println(st);
		}
		System.out.println("----------------------");
				
	}
}

class Student implements Comparable<Student>{
	private int studentId;
	private String name;
	private int korScore;
	private int engScore;
	private int mathScore;
	private int totalScore;
	private int rank;
	
	public Student(int studentId, String name, int korScore, int engScore, int mathScore) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
		totalScore = korScore + engScore + mathScore;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorScore() {
		return korScore;
	}

	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public int getEngScore() {
		return engScore;
	}

	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

		
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", korScore=" + korScore + ", engScore="
				+ engScore + ", mathScore=" + mathScore + ", totalScore=" + totalScore + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student student) {
		if(getStudentId() > student.getStudentId()) {
			return 1;
		} else if(getStudentId() < student.getStudentId()) {
			return -1;
		} else {
			return 0;			
		}	
		
	}
		
}

class ScoreDesc implements Comparator<Student>{

	@Override
	public int compare(Student st1, Student st2) {
		if(st1.getTotalScore() > st2.getTotalScore()) {
			return -1;
		} else if(st1.getTotalScore() < st2.getTotalScore()) {
			return 1;
		} else {			
			return st1.getName().compareTo(st2.getName());
		}
	}
	
}