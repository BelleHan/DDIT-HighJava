package kr.or.ddit.basic.args;

public class ArgsTest {

	// �Ű������� ���� �������� �հ踦 ���ϴ� �޼���
	// (�� �������� ������ ��Ȳ�� ���� ���� �� �ִ�.)
	public int sumArr(int[] data) {
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	/*
		������ �μ� ==> �޼����� �Ű������� ������ ����� ������ �ٸ� �� ���� �� ����Ѵ�.
			    ==> ������ �μ��� �޼��� �ȿ��� �迭�� ó���ȴ�.
	 */
	
	// ������ �μ��� �̿��ϴ� �޼��� ����� = ('�ڷ���'...'������')
	public int sumArg(int...data) {
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		return sum;		
	}
	
	// ������ �μ��� �Ϲ����� ������ ���� ����� ��쿡�� ������ ������ ���� ���ʿ� ��ġ�ؾ� �Ѵ�.
//	public String sumArg2(int...data, String name) { //������ ������ �Ϲݺ��� ��ġ�� �ٲٸ� ����, 
//	������ ������ �Ϲݺ����� �ڷ����� �ٸ��� ������ ���������� �� ���� �ڷ����� ���� ��� ������ ����
//	�Ϲݺ����� ������ �� ���� ����. ������ �μ��� �����ϱ� ��Ʊ� ������ �� �ڿ� ��ġ
	public String sumArg2(String name, int...data) {
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		return name + "���� �հ�: " + sum;
	}
	
	
	public void aaa(int a) {		
	}
	
	public static void main(String[] args) {
		ArgsTest test = new ArgsTest();
		
		int[] nums = {100, 200, 300};
		int[] kkk;
		
		kkk = new int[]{1,2,3,4,5}; //����� ���ÿ� �ʱ�ȭ�������� ���� ��� �ٽ� �ѹ� new int[]�� ��������� ������ �ȳ�
		
		System.out.println(test.sumArr(nums));
		System.out.println(test.sumArr(new int[]{1,2,3,4,5,6}));
//		int k = 100;		
//		test.aaa(k);
//		test.aaa(200);
		System.out.println();
		System.out.println(test.sumArg(100,200,300));
		System.out.println(test.sumArg(1,2,3,4,5,6));
		
		System.out.println();
		System.out.println(test.sumArg2("ȫ�浿", 70,80,90));
		
	}
}
