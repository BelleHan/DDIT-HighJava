package kr.or.ddit.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
	
	// 단방향 암호화하는 메서드
	// 변수 str : 암호화할 데이터가 저장된 변수
	public static String sha512(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException { // 숫자가 높을수록 암호화를 복잡하게 함
		// 단방향 암호화 알고리즘 이름 : MD5, SHA-256, SHA-3, SHA-512
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(str.getBytes("UTF-8"));
		
		return Base64.getEncoder().encodeToString(md.digest());
	}
	
	// 양방향 암호화 하기
	/**
	 * AES 256 방식으로 암호화 하는 메서드
	 * @param str 암호화할 문자열
	 * @param key 암호키 문자열 
	 * @return 암호화된 문자열 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String encryptAES256(String str, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		byte[] keyBytes = new byte[16];
		System.arraycopy(key.getBytes("UTF-8"), 0, keyBytes, 0, keyBytes.length);
		// arraycopy - key.getBytes("UTF-8") 배열의 0번째부터 keyBytes.length만큼의 데이터를 keyBytes배열에 0번째부터 복사해라
		
		// 비밀키 생성
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		
		// Cipher 생성 및 초기화
		// 알고리즘/모드/패딩
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		// 초기화 벡터(Initial Vector, IV) 
		//		 - 암호문이 패턴화되지 않도록 사용하는 데이터
		//		 - 암호화 작업의 첫번째 블록에 사용되는 값
		String iv = key.substring(0, 16);
		byte[] ivBytes = new byte[16];
		System.arraycopy(iv.getBytes("UTF-8"), 0, ivBytes, 0, ivBytes.length);
		
		// 암호화 옵션 종류에 맞게 초기화 한다.
		// 옵션 종류 : ENCRYPT_MODE(암호화모드), DECRYPT_MODE(복호화모드)
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
		
		// 암호문 생성
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		
		return Base64.getEncoder().encodeToString(encrypted);
		
	}
	
	/**
	 * 암호화된 데이터를 원래의 데이터로 복원하는 메서드
	 * @param str 암호화된 문자열
	 * @param key 암호키 문자열
	 * @return 원래 데이터로 복원된 문자열
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String decryptAES256(String str, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		byte[] keyBytes = new byte[16];
		System.arraycopy(key.getBytes("UTF-8"), 0, keyBytes, 0, keyBytes.length);
		
		// 비밀키 생성
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		String iv = key.substring(0, 16);
		byte[] ivBytes = new byte[16];
		System.arraycopy(iv.getBytes("UTF-8"), 0, ivBytes, 0, ivBytes.length);
		
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
		
		byte[] byteStr = Base64.getDecoder().decode(str); // 암호문을 decoding한다.
		
		return new String(c.doFinal(byteStr), "UTF-8");
	}
}
