package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


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

public class StudentTestT {
	// ����� ���ϴ� �޼���
	public void setRanking(List<StudentT> stdList) {
		for(StudentT std1 : stdList) {	// ���� �����͸� ���ϱ� ���� �ݺ���
			int rank = 1;	// ó������ 1������ ������ ���� �����Ѵ�.
			for(StudentT std2 : stdList) {	// �� ����� ��Ÿ���� �ݺ���
				// ���غ��� ū ���� ������ rank���� ������Ų��.
				if(std1.getTot() < std2.getTot()) {
					rank++;
				}
			} // �� ��� �ݺ��� ��...
			
			std1.setRank(rank); // ������ ����� Student��ü�� rank������ �����Ѵ�.
		}
	}
	

	public static void main(String[] args) {
		StudentTestT stdTest = new StudentTestT();
		
		List<StudentT> stdList = new ArrayList<StudentT>();
		stdList.add(new StudentT(1, "ȫ�浿", 90, 95, 80));
		stdList.add(new StudentT(3, "ȫ����", 90, 75, 70));
		stdList.add(new StudentT(2, "������", 100, 95, 70));
		stdList.add(new StudentT(4, "��ġġ", 20, 65, 50));
		stdList.add(new StudentT(7, "���浿", 50, 80, 85));
		stdList.add(new StudentT(5, "������", 95, 65, 80));
		
		// ��� ���ϴ� �޼��� ȣ���ϱ�
		stdTest.setRanking(stdList);
		
		System.out.println("���� ��...");
		for(StudentT std : stdList) {
			System.out.println(std);
		}
		System.out.println("----------------------");
		
		// �й��� ������������ �����ϱ� - ���� ���� ���� ����
		Collections.sort(stdList);
		
		System.out.println("�й��� �������� ���� ��...(���� ���� ���� ����)");
		for(StudentT std : stdList) {
			System.out.println(std);
		}
		System.out.println("----------------------");

		// ������ �������� �����ϱ� - �ܺ� ���� ���� ����
		Collections.sort(stdList, new SortByTotal());
		
		System.out.println("������ �������� ���� ��...(�ܺ� ���� ���� ����)");
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

	// �й��� ��������
	@Override
	public int compareTo(StudentT std) {		
		return Integer.compare(num, std.getNum());
	}
		
}

// ������ �������� �����ϴµ� ������ ������ �̸��� ������������ ���ĵǴ� �ܺ� ���� ���� Ŭ������ �ۼ�
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