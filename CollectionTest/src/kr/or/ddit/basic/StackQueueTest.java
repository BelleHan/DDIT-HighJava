package kr.or.ddit.basic;

import java.util.LinkedList;

/*
 	Stack ==> LIFO(후입 선출)의 자료구조	
 	Queue ==> FIFO(선입 선출)의 자료구조
 	
 	Stack과 Queue는 LinkedList를 이용해서 사용할 수 있다.(ArrayList는 지원을 안 함)
 	
 */

public class StackQueueTest {
	public static void main(String[] args) {
	/*
	 	Stack의 명령
	1. 자료 입력 : push(입력값)
	2. 자료 출력 : pop() ==> 자료를 꺼내온 후 꺼내온 자료를 Stack에서 꺼낸다.
	 		   peek() ==> 삭제없이 자료를 꺼내온다.
	 */
		LinkedList<String> stack = new LinkedList<>();
		stack.push("홍길동");
		stack.push("일지매");
		stack.push("변학도");
		stack.push("성춘향");
		
		System.out.println("현재 stack의 값: " + stack);
		
		String data = stack.pop();
		System.out.println("꺼내온 값: " + data);
		System.out.println("꺼내온 값: " + stack.pop());
		
		System.out.println("현재 stack의 값: " + stack);
		
		stack.push("강감찬");
		System.out.println("추가 후 stack의 값: " + stack);
		
		System.out.println("꺼내온 값: " + stack.pop());
		System.out.println("현재 stack의 값: " + stack);
		
		System.out.println("삭제 없이 꺼내온 값: " + stack.peek());
		System.out.println("현재 stack의 값: " + stack);
		System.out.println("-------------------------------");
		
		/*
		Queue의 명령
		1. 자료 입력: offer(입력값)
		2. 자료 출력: poll() ==> 자료를 꺼내오고 꺼내온 자료를 삭제한다.
				   peek() ==> 삭제없이 자료를 꺼내온다.
		 */
		LinkedList<String> queue = new LinkedList<>();
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("변학도");
		queue.offer("성춘향");
		
		System.out.println("현재 queue의 값: " + queue);
		String temp = queue.poll();
		System.out.println("꺼내온 값: " + temp);
		System.out.println("꺼내온 값: " + queue.poll());
		System.out.println("현재 queue의 값: " + queue);
		
		queue.offer("강감찬");
		System.out.println("현재 queue의 값: " + queue);
		System.out.println();
		
		System.out.println("꺼내온 값: " + queue.poll());
		
		System.out.println("삭제없이 꺼내온 값: " + queue.peek());
		System.out.println("현재 queue의 값: " + queue);
		
	}
}
