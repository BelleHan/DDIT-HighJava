package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {

	public static void main(String[] args) throws IOException {
		// URLConnection ==> ���ø����̼ǰ� URL���� ��� ������ ���� Ŭ����
		
		// Ư�� ������ ������ ���� ������ ������ ����ϴ� ����		
		URL url = new URL("https://www.naver.com/index.html");
		
		// URLConnection��ü ���ϱ� ==> URL��ü�� �̿��ؼ� ���Ѵ�.
		URLConnection urlCon = url.openConnection();
		
		// Header ���� ��������
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		// headerMap�� key���� value�� ���
		for(String headerKey : headerMap.keySet()) {
			System.out.println(headerKey + " : " + headerMap.get(headerKey));
		}
		System.out.println("-----------------------------------------------");
		System.out.println();
		
		// �ش� ������ ������ �������� ( ������ index.html���� ���� �������� )
		
		/*
		// ���1 => URLConnection��ü�� �̿��ϴ� ���
		
		// ������ �о���� ���� ��Ʈ�� ��ü ����
		InputStream is = urlCon.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		
		// ��Ʈ�� ��ü�� �̿��ؼ� �ڷḦ �о�� ����ϱ�
		while(true) {
			String str = br.readLine();  // ���پ� �б�
			if(str==null) {
				break;
			}
			System.out.println(str);
		}
		
		br.close();  // ��Ʈ�� �ݱ�
		*/
		
		// ���2 ==> URL��ü�� openStream()�޼��� �̿��ϱ�
		InputStream is2 = url.openStream();
		BufferedReader br2 = new BufferedReader(
				new InputStreamReader(is2, "utf-8"));
		
		BufferedWriter bw = new BufferedWriter(
				new FileWriter("d:/d_other/naver.html")
		);
		
		while(true) {
			String str2 = br2.readLine();
			if(str2==null) break;
			//System.out.println(str2);
			bw.write(str2 + "\n");
		}
		br2.close();
		System.out.println("�۾� �Ϸ�...");
	}
}
