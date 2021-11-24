package kr.or.ddit.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIOTest {

	// ��ü�� ���Ͽ� �����ϴ� ����
	public static void main(String[] args) {
		// Member�� �ν��Ͻ� ����
		Member mem1 = new Member("tester", 20, "daejeon");
		Member mem2 = new Member("ȫ�漭", 30, "����");
		Member mem3 = new Member("ȫ�泲", 40, "��õ");
		Member mem4 = new Member("ȫ���", 50, "����");
		
		try {
			// ��ü�� ���Ͽ� �����ϱ�
			
			// ��¿� ��Ʈ�� ��ü ����
			FileOutputStream fout = 
					new FileOutputStream("d:/d_other/memObj.bin");
			BufferedOutputStream bos = 
					new BufferedOutputStream(fout);
			ObjectOutputStream oos = 
					new ObjectOutputStream(bos);  //������Ʈ�� �ȿ��� �ٸ� ������Ʈ���� ��ø�ؼ� ����� �� �ִ�.(������� ��� ����)
			
			// ���� �۾�
			System.out.println("��ü �����ϱ� ����...");
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			System.out.println("��ü ���� �۾� ��...");
			
			// ��Ʈ�� �ݱ�
			oos.close();
			
			
			//======================================================
			// ����� ��ü�� �о�� �� ������ ȭ�鿡 ����ϱ�
			
			// �Է¿� ��Ʈ�� ��ü ����
			ObjectInputStream ois =
				new ObjectInputStream(
					new BufferedInputStream(
						new FileInputStream("d:/d_other/memObj.bin")
					)
				);
			
			Object obj;  // �о�� ��ü�� ����� ���� ����
			
			try {
				System.out.println("��ü �б� �۾� ����...");
				
				// readObject()�޼��尡 �����͸� ������ �� �о����
				// EOFException�� �߻��Ѵ�.(EndOfFile)
				while((obj = ois.readObject())!=null) {
					// �о�� �����͸� ������ ��ü������ ����ȯ �� ����Ѵ�.
					Member mem = (Member)obj;
					System.out.println("�̸�: " + mem.getName());
					System.out.println("����: " + mem.getAge());
					System.out.println("�ּ�: " + mem.getAddr());
					System.out.println("----------------------------");
				}
				
			} catch (EOFException e) {
				System.out.println("��ü �б� �۾� ��...");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// ��Ʈ�� �ݱ�
				ois.close();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

class Member implements Serializable{
	
	private static final long serialVersionUID = -6973482964023581752L; // �ý��ۿ��� �����Ͱ� ������ �Ǻ��ϴ� �뵵
	
	// transient ==> ����ȭ�� ���� ���� ��������� �����Ѵ�.
	// 				  ����ȭ�� ���� ���� ��������� �����Ͱ� �⺻������ ����ȴ�.
	//				 (�⺻��: �������� => null, ������ ==> 0, ���� = false)
	private String name;
	private transient int age;
	private transient String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}