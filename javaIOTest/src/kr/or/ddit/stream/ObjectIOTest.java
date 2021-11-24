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

	// 객체를 파일에 저장하는 예제
	public static void main(String[] args) {
		// Member의 인스턴스 생성
		Member mem1 = new Member("tester", 20, "daejeon");
		Member mem2 = new Member("홍길서", 30, "서울");
		Member mem3 = new Member("홍길남", 40, "인천");
		Member mem4 = new Member("홍길북", 50, "강릉");
		
		try {
			// 객체를 파일에 저장하기
			
			// 출력용 스트림 객체 생성
			FileOutputStream fout = 
					new FileOutputStream("d:/d_other/memObj.bin");
			BufferedOutputStream bos = 
					new BufferedOutputStream(fout);
			ObjectOutputStream oos = 
					new ObjectOutputStream(bos);  //보조스트림 안에는 다른 보조스트림을 중첩해서 사용할 수 있다.(여러기능 사용 가능)
			
			// 쓰기 작업
			System.out.println("객체 저장하기 시작...");
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			System.out.println("객체 저장 작업 끝...");
			
			// 스트림 닫기
			oos.close();
			
			
			//======================================================
			// 저장된 객체를 읽어와 그 내용을 화면에 출력하기
			
			// 입력용 스트림 객체 생성
			ObjectInputStream ois =
				new ObjectInputStream(
					new BufferedInputStream(
						new FileInputStream("d:/d_other/memObj.bin")
					)
				);
			
			Object obj;  // 읽어온 객체가 저장될 변수 선언
			
			try {
				System.out.println("객체 읽기 작업 시작...");
				
				// readObject()메서드가 데이터를 끝까지 다 읽어오면
				// EOFException이 발생한다.(EndOfFile)
				while((obj = ois.readObject())!=null) {
					// 읽어온 데이터를 원래의 객체형으로 형변환 후 사용한다.
					Member mem = (Member)obj;
					System.out.println("이름: " + mem.getName());
					System.out.println("나이: " + mem.getAge());
					System.out.println("주소: " + mem.getAddr());
					System.out.println("----------------------------");
				}
				
			} catch (EOFException e) {
				System.out.println("객체 읽기 작업 끝...");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// 스트림 닫기
				ois.close();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

class Member implements Serializable{
	
	private static final long serialVersionUID = -6973482964023581752L; // 시스템에서 데이터가 같은지 판별하는 용도
	
	// transient ==> 직렬화가 되지 않을 멤버변수에 지정한다.
	// 				  직렬화가 되지 않은 멤버변수는 데이터가 기본값으로 저장된다.
	//				 (기본값: 참조변수 => null, 숫자형 ==> 0, 논리형 = false)
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