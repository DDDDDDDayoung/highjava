package kr.or.ddit.basic;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class ThreadGame {
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {	
		
		Thread th1 = new DataIn();
		Thread th2 = new Count();
		
		th1.start();
		th2.start();
		
	}

}

class DataIn extends Thread{

	String[] data = {"가위", "바위", "보"};
	int index = (int)(Math.random()*3); 
	String com =data[index];
	
	@Override
	public void run() {
		
		
		String user = JOptionPane.showInputDialog("가위, 바위, 보 중 하나를 입력하시오");
		
		
		// 입력이 완료되면 inputCheck변수를 true로 변경한다.
		
		
		System.out.println("====결  과====");
		System.out.println("당  신 : " + user);
		System.out.println("컴퓨터 : " + com);
		
		if(user.equals(com)) {
			System.out.println("비겼습니다.");
			ThreadGame.inputCheck=true;
		}else if((user.equals("가위") && com.equals("보"))
				|| (user.equals("바위") && com.equals("가위"))
				|| (user.equals("보") && com.equals("주먹"))) {
			System.out.println("당신이 이겼습니다.");
			ThreadGame.inputCheck=true;
		}else {
			System.out.println("당신이 졌습니다.");
			ThreadGame.inputCheck=true;
		}
		
		
	}
	
	
}

/**
 * 카운트다운 처리를 위한 스레드
 */
class Count extends Thread {
	@Override
	public void run() {
		
		for(int i=5; i>=1; i--) {
			// 입력이 완료되었는지 여부를 검사하고 입력이 완료되면
			// run()메서드를 종료시킨다. 즉 현재 스레드를 종료시킨다.
			if(ThreadGame.inputCheck) {
				return;
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		// 10초가 경과되었는데도 입렵이 안되면 프로그램을 종료한다.
		System.out.println("시간이 초과되었습니다.");
		System.exit(0); // 프로그램을 종료시키는 명령
	}
}