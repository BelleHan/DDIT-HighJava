package kr.or.ddit.basic;

import java.util.LinkedList;

/*
 	Stack ==> LIFO(���� ����)�� �ڷᱸ��	
 	Queue ==> FIFO(���� ����)�� �ڷᱸ��
 	
 	Stack�� Queue�� LinkedList�� �̿��ؼ� ����� �� �ִ�.(ArrayList�� ������ �� ��)
 	
 */

public class StackQueueTest {
	public static void main(String[] args) {
	/*
	 	Stack�� ���
	1. �ڷ� �Է� : push(�Է°�)
	2. �ڷ� ��� : pop() ==> �ڷḦ ������ �� ������ �ڷḦ Stack���� ������.
	 		   peek() ==> �������� �ڷḦ �����´�.
	 */
		LinkedList<String> stack = new LinkedList<>();
		stack.push("ȫ�浿");
		stack.push("������");
		stack.push("���е�");
		stack.push("������");
		
		System.out.println("���� stack�� ��: " + stack);
		
		String data = stack.pop();
		System.out.println("������ ��: " + data);
		System.out.println("������ ��: " + stack.pop());
		
		System.out.println("���� stack�� ��: " + stack);
		
		stack.push("������");
		System.out.println("�߰� �� stack�� ��: " + stack);
		
		System.out.println("������ ��: " + stack.pop());
		System.out.println("���� stack�� ��: " + stack);
		
		System.out.println("���� ���� ������ ��: " + stack.peek());
		System.out.println("���� stack�� ��: " + stack);
		System.out.println("-------------------------------");
		
		/*
		Queue�� ���
		1. �ڷ� �Է�: offer(�Է°�)
		2. �ڷ� ���: poll() ==> �ڷḦ �������� ������ �ڷḦ �����Ѵ�.
				   peek() ==> �������� �ڷḦ �����´�.
		 */
		LinkedList<String> queue = new LinkedList<>();
		queue.offer("ȫ�浿");
		queue.offer("������");
		queue.offer("���е�");
		queue.offer("������");
		
		System.out.println("���� queue�� ��: " + queue);
		String temp = queue.poll();
		System.out.println("������ ��: " + temp);
		System.out.println("������ ��: " + queue.poll());
		System.out.println("���� queue�� ��: " + queue);
		
		queue.offer("������");
		System.out.println("���� queue�� ��: " + queue);
		System.out.println();
		
		System.out.println("������ ��: " + queue.poll());
		
		System.out.println("�������� ������ ��: " + queue.peek());
		System.out.println("���� queue�� ��: " + queue);
		
	}
}
