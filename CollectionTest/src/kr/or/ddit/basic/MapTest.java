package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
	/*
	 	Map ==> key���� value���� �� ������ �����ϴ� ��ü
	 	 - key�� : �ߺ��� ������� �ʰ� ������ ����. (SetƯ¡�� ���´�.)
	 	 - value�� : �ߺ��� ����Ѵ�.	 	
	 */
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<>();
		
		// �ڷ� �߰�: put(key��, value��)
		map.put("name", "ȫ�浿");
		map.put("addr", "������");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map => " + map);
		
		// �ڷ� ����: �����͸� �߰��� �� key���� ������ �����Ϳ� �����ϸ� 
		// 		      ���߿� �߰��� ���� ����ȴ�.
		map.put("name", "�̼���");		
		System.out.println("map => " + map);
		System.out.println();
		
		// �ڷ� ����: remove(key��) => key���� ���� �ڷḦ ã�� �����Ѵ�.
		//		      ��ȯ�� : ������ �ڷ��� value���� ��ȯ�ȴ�.
		
//		String delTel = map.remove("tel");
//		System.out.println("delTel => " + delTel);
//		System.out.println("map => " + map);
//		System.out.println();
		
		// �ڷ� �б�: get(key��) ==> key���� ¦�� �Ǵ� value���� ��ȯ�Ѵ�.
		System.out.println("�̸�: " + map.get("name"));
		System.out.println();
		
		// key���� �����ϴ��� ���θ� ��Ÿ���� �޼���: containsKey(ã�� key��)
		//  ==> 'ã�� key��'�� ������ true, ������ false
		System.out.println("tel Ű���� ���� ����: " + map.containsKey("tel"));
		System.out.println("age Ű���� ���� ����: " + map.containsKey("age"));
		System.out.println("---------------------------------");
		
		// Map�� ����� ��� key���� �о�� Map �����͸� ó���ϴ� ���
		
		// ���1 : keySet()�޼��� �̿��ϱ�
		//			==> Map�� ��� key������ �о�� Set������ ��ȯ�Ѵ�.
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();	
		
		while(it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("=================================");
		
		// ���2 : keySet�� ���� for������ ó���ϱ�
		for(String key : map.keySet()) {
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("=================================");
		
		// ���3 : value���� �о�� ó���ϱ� ==> values()�޼��带 �̿��Ѵ�.
		for(String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("=================================");
		
		/*
		���4 : Map���� Entry��� ���� class�� ������� �ִ�.
			  �� EntryŬ������ key�� value��� ��������� �����Ǿ� �ִ�.
			  Map������ �� EntryŬ������ Set�������� �����Ͽ� �����Ѵ�.
			  
		- Entry��ü ��ü�� �������� �޼��� : entrySet() �޼���
				==> ������ Entry��ü���� Set�������� �Ǿ� �ִ�.		
		 */
		
		Set<Map.Entry<String, String>> mapEntry = map.entrySet();
		Iterator<Map.Entry<String, String>> entryIt = mapEntry.iterator();
		
		while(entryIt.hasNext()) {
			// Entry��ü 1�� ���ϱ�
			Map.Entry<String, String> entry = entryIt.next();
			
			System.out.println("key��: " + entry.getKey());
			System.out.println("value��: " + entry.getValue());
			System.out.println();
		}
		System.out.println("=================================");
	}
	
}

