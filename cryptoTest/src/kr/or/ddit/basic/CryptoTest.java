package kr.or.ddit.basic;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import kr.or.ddit.util.CryptoUtil;

public class CryptoTest {

	public static void main(String[] args) throws Exception {
		String plainText = "Hello, World! �����ٶ� ���ٻ�� 123456 &*()_+";
		String key = "a1b2c3d4e5f6g7h8"; // ��ȣȭ Ű�� (16�ڸ��̻�)
		
		System.out.println("���������� : " + plainText);
		
		String sha512 = CryptoUtil.sha512(plainText);
		System.out.println("�ܹ��� SHA-26 : " + sha512 + " - " + sha512.length());
		System.out.println();
		
		// ��ȣȭ �ϱ�
		String enc = CryptoUtil.encryptAES256(plainText, key);
		System.out.println("��ȣȭ�� �� : " + enc);
		
		// ��ȣȭ �ϱ�
		String dec = CryptoUtil.decryptAES256(enc, key);
		System.out.println("��ȣȭ�� �� : " + dec);

	}
	
}
