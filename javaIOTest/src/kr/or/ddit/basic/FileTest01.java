package kr.or.ddit.basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		// File객체 만들기 연습
		
		// 1. new File(String 파일 또는 경로)
		//  ==> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는 
		//  ==> '/'또는 '\'를 사용할 수 있다.
		File file1 = new File("D:/D_Other/test.txt");  // 구분문자를 '/'로 사용
		//File file1 = new File("D:\\D_Other\\test.txt");  // 구분문자를 '\'로 사용 ('\'는 반드시 두번씩 써줘야 함)
		System.out.println("파일명: " + file1.getName());
		System.out.println("디렉토리 인가? : " + file1.isDirectory());
		System.out.println("파일 인가? : " + file1.isFile());
		System.out.println();
		
		
		File file2 = new File("d://d_other"); // 윈도우는 상관없지만 원래는 대소문자 맞추어서 써주어야함.
		System.out.println("파일명: " + file2.getName());
		System.out.println("디렉토리 인가? : " + file2.isDirectory());
		System.out.println("파일 인가? : " + file2.isFile());
		System.out.println();
		
		// 2. new File(File parent, String child)
		//  ==> 'parent'디렉토리 안에 있는 'child'파일을 나타낸다.
		File file3 = new File(file2, "test.txt");
		System.out.println("파일명: " + file3.getName());
		System.out.println("디렉토리 인가? : " + file3.isDirectory());
		System.out.println("파일 인가? : " + file3.isFile());
		System.out.println();
		
		// 3. new File(String parent, String child)  
		//  ==> 'parent'디렉토리 안에 있는 'child'파일을 나타낸다.
		// 부모개체를 파일로 나타내는지 문자열로 나타내는지가 2번과 다름
		File file4 = new File("d:/d_other", "test.txt");
		System.out.println("파일명: " + file4.getName());
		System.out.println("디렉토리 인가? : " + file4.isDirectory());
		System.out.println("파일 인가? : " + file4.isFile());
		System.out.println();
		System.out.println("--------------------------------------------------");
		//====================================================================
		
		// 디렉토리(폴더) 만들기
		// - mkdir()  ==> File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		//			  ==> 반환값 : 만들기 성공(true), 만들기 실패(false)
		//			  ==> 최종적으로 만들려고 하는 폴더 앞쪽의 폴더들이 미리 만들어져 있어야 한다.
		
		// - mkdirs() ==> 만들려고 하는 폴더 앞쪽의 폴더가 없으면 그 앞쪽의 폴더까지 만들어준다.
		//		  ==> 반환값 : 만들기 성공(true), 만들기 실패(false)
		File file5 = new File("d:/d_other/연습용");
		System.out.println(file5.getName() + "의 존재 여부: " + file5.exists());
		
		if(file5.mkdir()) {   // 실행하고 나서 만들기에 성공했다면 true 실패면 false, new File()안에 있는  위치에서 마지막 폴더 생성 이미 있다면 false
			System.out.println(file5.getName() + "폴더 생성 완료!!");
		}else {
			System.out.println(file5.getName() + "폴더 생성 실패~~");
		}
		System.out.println();
		
		File file6 = new File("d:/d_other/test/java/src");
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + " 만들기 성공");
		}else {
			System.out.println(file6.getName() + " 만들기 실패");
		}
		
	}
}
