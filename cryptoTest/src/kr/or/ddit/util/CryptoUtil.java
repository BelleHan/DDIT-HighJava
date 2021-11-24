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
	
	// �ܹ��� ��ȣȭ�ϴ� �޼���
	// ���� str : ��ȣȭ�� �����Ͱ� ����� ����
	public static String sha512(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException { // ���ڰ� �������� ��ȣȭ�� �����ϰ� ��
		// �ܹ��� ��ȣȭ �˰��� �̸� : MD5, SHA-256, SHA-3, SHA-512
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(str.getBytes("UTF-8"));
		
		return Base64.getEncoder().encodeToString(md.digest());
	}
	
	// ����� ��ȣȭ �ϱ�
	/**
	 * AES 256 ������� ��ȣȭ �ϴ� �޼���
	 * @param str ��ȣȭ�� ���ڿ�
	 * @param key ��ȣŰ ���ڿ� 
	 * @return ��ȣȭ�� ���ڿ� 
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
		// arraycopy - key.getBytes("UTF-8") �迭�� 0��°���� keyBytes.length��ŭ�� �����͸� keyBytes�迭�� 0��°���� �����ض�
		
		// ���Ű ����
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		
		// Cipher ���� �� �ʱ�ȭ
		// �˰���/���/�е�
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		// �ʱ�ȭ ����(Initial Vector, IV) 
		//		 - ��ȣ���� ����ȭ���� �ʵ��� ����ϴ� ������
		//		 - ��ȣȭ �۾��� ù��° ��Ͽ� ���Ǵ� ��
		String iv = key.substring(0, 16);
		byte[] ivBytes = new byte[16];
		System.arraycopy(iv.getBytes("UTF-8"), 0, ivBytes, 0, ivBytes.length);
		
		// ��ȣȭ �ɼ� ������ �°� �ʱ�ȭ �Ѵ�.
		// �ɼ� ���� : ENCRYPT_MODE(��ȣȭ���), DECRYPT_MODE(��ȣȭ���)
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
		
		// ��ȣ�� ����
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		
		return Base64.getEncoder().encodeToString(encrypted);
		
	}
	
	/**
	 * ��ȣȭ�� �����͸� ������ �����ͷ� �����ϴ� �޼���
	 * @param str ��ȣȭ�� ���ڿ�
	 * @param key ��ȣŰ ���ڿ�
	 * @return ���� �����ͷ� ������ ���ڿ�
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
		
		// ���Ű ����
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		String iv = key.substring(0, 16);
		byte[] ivBytes = new byte[16];
		System.arraycopy(iv.getBytes("UTF-8"), 0, ivBytes, 0, ivBytes.length);
		
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
		
		byte[] byteStr = Base64.getDecoder().decode(str); // ��ȣ���� decoding�Ѵ�.
		
		return new String(c.doFinal(byteStr), "UTF-8");
	}
}
