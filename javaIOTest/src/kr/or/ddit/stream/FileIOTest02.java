package kr.or.ddit.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileIOTest02 {

	public static void main(String[] args) {
		// FileOutputStream을 이용해서 파일에 출력하기
		try {
			// FileOutputStream객체 생성
			File file = new File("d:/d_other/out.txt"); // out.txt파일이 존재하지 않아도 OutputStream객체가 없는 파일은 새로 만들어준다. 
														// 만약 똑같은 이름의 파일이 원래 있다면 덮어쓰기를 한다.
			FileOutputStream fout = new FileOutputStream(file);
			
			for(char ch='a'; ch<='z'; ch++) {
				fout.write(ch);
				
			}
			System.out.println("출력 완료...");
			fout.close();   // 스트림 닫기
			
			// =====================
			
			// 위에서 저장한 파일 내용을 읽어와 화면에 출력하시오.
			
			FileInputStream fin = new FileInputStream(file);
			int c;
			while((c=fin.read()) != -1) {
				System.out.print((char)c);
			}
			fin.close();
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
