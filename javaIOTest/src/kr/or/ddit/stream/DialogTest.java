package kr.or.ddit.stream;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {

	public static void main(String[] args) {
		// SWING의 파일 '열기 창', '저장 창' 연습
		JFileChooser chooser = new JFileChooser();
		
		// 선택할 파일의 확장자 설정
		FileNameExtensionFilter txt = 
				new FileNameExtensionFilter("Text파일(*.txt)", "txt");
		FileNameExtensionFilter img = 
				new FileNameExtensionFilter("그림 파일", "png", "jpg", "gif");
		FileNameExtensionFilter excel = 
				new FileNameExtensionFilter("엑셀파일", new String[] {"xls", "xlsl"});
		
		chooser.addChoosableFileFilter(txt);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(excel);
		
		// '모든 파일'목록 표시 여부 결정 ==> true:설정, false:해제
		//chooser.setAcceptAllFileFilterUsed(false);
		
		// Dialog창에 나타날 기본 경로 설정
		chooser.setCurrentDirectory(new File("d:/d_other"));
		
		//int result = chooser.showOpenDialog(new Panel()); // 열기 창
		int result = chooser.showSaveDialog(new Panel()); // 저장 창
		
		// FileChooser에서 선택한 파일 정보 가져오기
		if(result == JFileChooser.APPROVE_OPTION) { // '저장' 또는 '열기'버튼을 눌렀을 때
			File selectedFile = chooser.getSelectedFile();
			System.out.println("선택한 파일: " + selectedFile.getAbsolutePath());
		}
		
		
		
	}
}
