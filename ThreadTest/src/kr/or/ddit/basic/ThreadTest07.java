package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 문제) 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 
 컴퓨터의 가위 바위 보는 난수를 이용해서 구하고,
 사용자의 입력 showInputDialog()메서드를 이용해서 입력 받는다.
 
 입력 시간은 5초로 제한하고 카운트 다운을 한다.
 5초 안에 입력이 없으면 게임에 진것으로 처리한다.
 
 5초 안에 입력이 있으면 입력된 값과 컴퓨터의 승패를 구해서 출력한다.
 
 결과 예시)
    -- 결  과 --
       컴퓨터 : 가위
       사용자 : 바위
       결 과 : 당신이 이겼습니다.
       
 5초 안에 입력이 없을 때 예시)
 	시간이 초과되어 당신이 졌습니다.
     
 */

public class ThreadTest07 {

	public static void main(String[] args) {
		
		
		Thread th1 = new Input();
		Thread th2 = new Count();
		
		th1.start();
		th2.start();
	}
}

class Input extends Thread{
	
	public static boolean inputCheck = false;
	
	@Override
	public void run() {
		int com = (int)(Math.random() * 3 + 1);
		String rsp = null;
		String str = JOptionPane.showInputDialog("가위 바위 보 중 하나를 입력하세요.");
		
		inputCheck = true;
				
		if(com == 1) {
			rsp = "가위";
		} else if(com == 2) {
			rsp = "바위";
		} else {
			rsp = "보";
		}
		System.out.println("컴퓨터: " + rsp);
		System.out.println("사용자: " + str);
		
		if(rsp.equals(str)) {
			System.out.println("결과: 비겼습니다.");
		} else if (str.equals("가위") && rsp.equals("보") ||
				   str.equals("바위") && rsp.equals("가위") || 
				   str.equals("보") && rsp.equals("바위")) {
			System.out.println("당신이 이겼습니다.");			
		} else {
			System.out.println("컴퓨터가 이겼습니다.");
		}
		
	}
}

class Count extends Thread{
	@Override
	public void run() {
		for(int i=5; i>=1; i--) {
			if(Input.inputCheck==true) {
				return;
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		System.out.println("시간이 초과되어 당신이 졌습니다.");
		System.exit(0);
	}
}