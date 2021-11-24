package javaPractice;

import java.util.Arrays;

public class ArrayCopyTest {

	public static void main(String[] args) {
		int[] arr1 = new int[] {1,2,3,4,5};
		int newLen = 10;
		
		// 1. System 클래스의 arraycopy() 메소드
		int[] arr2 = new int[newLen];
		System.arraycopy(arr1, 0, arr2, 0, arr1.length);
		
		// 2. Arrays 클래스의 copyof() 메소드
		int[] arr3 = Arrays.copyOf(arr1, 10);
		
		// 3. Object 클래스의 clone() 메소드
		int[] arr4 = (int[])arr1.clone();
		
		int[] arr = new int[] {1,2,3,4,5};
		
		for(int e : arr) {
			System.out.print(e + " ");
		}
		
	}
}
