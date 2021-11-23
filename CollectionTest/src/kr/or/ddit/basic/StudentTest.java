package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 	����) �й�(int), �̸�(String), ��������, ��������, ��������, ����, ���(int)��
 		 ����� ���� StudentŬ������ �����.
 		 �� Ŭ������ �����ڿ����� �й�, �̸�, ��������, ��������, ���������� �Ű�������
 		 �޾Ƽ� �ʱ�ȭ ó���� �Ѵ�.
 		 
 		 �� Student��ü�� List�� �����Ͽ� �����Ѵ�.
 		 
 		 List�� ����� �����͵��� �й��� ������������ ������ �� �ִ� ���� ���� ������ �����ϰ�,
 		 ������ �������� �����ϴµ� ������ ������ �̸��� ������������ ���ĵǴ�
 		 �ܺ� ���� ���� Ŭ������ �ۼ��Ͽ� ���ĵ� ����� ����Ͻÿ�.
 		 
 		 (��, ����� List�� ��ü �����Ͱ� �߰��� �Ŀ� ���ϵ��� �Ѵ�.)
 		 
 */
	
public class StudentTest {
	
	public static void main(String[] args) {
		ArrayList<Student> stList = new ArrayList<>();
		
		stList.add(new Student(5, "���缮", 90, 100, 95));
		stList.add(new Student(7, "�ڸ��", 75, 55, 60));
		stList.add(new Student(11, "��ȫö", 85, 100, 100));
		stList.add(new Student(1, "������", 40, 65, 95));
		stList.add(new Student(3, "����", 90, 70, 25));
		stList.add(new Student(32, "������", 30, 75, 65));
				
		System.out.println("���� ��...");
		for(Student st : stList) {
			System.out.println(st);
		}
		System.out.println("----------------------");
		
		Collections.sort(stList);
		
		System.out.println("�й��� ��������(���� ����) ��...");
		for(Student st : stList) {
			System.out.println(st);
		}
		System.out.println("----------------------");
		
		Collections.shuffle(stList);
		
		System.out.println("���� ��...");
		for(Student st : stList) {
			System.out.println(st);
		}
		System.out.println("----------------------");
		
		Collections.sort(stList, new ScoreDesc());
		
		System.out.println("������ ���� ���� ��...");
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