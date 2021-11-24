package javaPractice;

public class ArrayTest02 {

	public static void main(String[] args) {
		//int[][] arr = new int[2][3];
		
		int[][] arr = {
				{10, 20, 30},
				{40, 50, 60}
		};
		
		int k = 10;
		for (int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				arr[i][j] = k;
				k+=10;
				
			}
		}
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
	}
}
