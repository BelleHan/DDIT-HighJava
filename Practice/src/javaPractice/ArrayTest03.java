package javaPractice;

public class ArrayTest03 {

	public static void main(String[] args) {
//		int[][] arr = new int[3][];
//		arr[0] = new int[2];
//		arr[1] = new int[4];
//		arr[2] = new int[1];
		
		int[][] arr = {
				{10, 20},
				{10, 20, 30, 40},
				{10}
		};
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
}
