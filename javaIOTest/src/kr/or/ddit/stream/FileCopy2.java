package kr.or.ddit.stream;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 	복사할 원본 파일과 출력될 파일을 JFileChooser객체를 이용해서 선택하여 처리하도록 한다.
 */
public class FileCopy2 {

	public static void main(String[] args) {
			JFileChooser chooser = new JFileChooser();
				
		
			FileNameExtensionFilter img = 
					new FileNameExtensionFilter("그림 파일", "png", "jpg", "gif");
			
		
			chooser.addChoosableFileFilter(img);
		
		
			// Dialog창에 나타날 기본 경로 설정
			chooser.setCurrentDirectory(new File("d:/d_other"));
				
			int result = chooser.showOpenDialog(new Panel()); // 열기 창
				
			File sourceFile = null;
			if(result == JFileChooser.APPROVE_OPTION) {
				sourceFile = chooser.getSelectedFile();
			}else {
				System.out.println("원본 파일을 선택하지 않았습니다.");
				System.out.println("복사 작업을 중지합니다.");
				return;
			}
				
			
			if(!sourceFile.exists()) {
				System.out.println(sourceFile.getPath() + " 파일이 없습니다.");
				System.out.println("복사 작업을 중지합니다.");
				return;
			}
			
			try {
				
				System.out.println("복사 시작...");
				// 원본 데이터를 읽어올 입력용 스트림 객체 생성
				FileInputStream fis = new FileInputStream(sourceFile);
				BufferedInputStream bis = new BufferedInputStream(fis);
				
				int result2 = chooser.showSaveDialog(new Panel());
				File targetFile = null;
				if(result2 == JFileChooser.APPROVE_OPTION) {
					targetFile = chooser.getSelectedFile();
				}else {
					System.out.println("저장될 대상 파일을 선택하지 않았습니다.");
					System.out.println("복사 작업을 중지합니다.");
					return;
				}
				
				// 저장할 출력용 스트림 객체 생성
				FileOutputStream fout = new FileOutputStream(targetFile);
				BufferedOutputStream bout = new BufferedOutputStream(fout);
				
				int data;
				
				/*
				while((data = fis.read())!=-1) {
					fout.write(data);
				}
				fout.flush();
				
				// 스트림 닫기
				fout.close();
				fis.close();
				*/
				
				while((data = bis.read())!=-1) {
					bout.write(data);
				}
				bout.flush();
				
				// 스트림 닫기
				bout.close();
				bis.close();
							
				System.out.println("복사 작업 완료...");
				
			} catch (IOException e) {
				// TODO: handle exception
			}
		
	
	}
}
