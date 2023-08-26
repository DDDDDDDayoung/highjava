package kr.or.ddit.basic;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class h1 {
	public static boolean inputCheck=false;
	
	public static void main(String[] args) {
		
		Thread dt = new GameResult();
		Thread gc = new GameCount();
		
		dt.start();
		gc.start();
	

	}

}

class GameResult extends Thread {
	Scanner sc = new Scanner(System.in);
	String[] data = {"가위", "바위", "보"};
	int index = (int)(Math.random()*3);
	String com = data[index];
	
	@Override
	public void run() {
		String user 
				= JOptionPane.showInputDialog("가위, 바위, 보 중 하나를 입력하시오.");
		
		System.out.println("=== 결 과 ===");
		System.out.println("USER : " + user);
		System.out.println("COM : " + com);
		
		if(user.equals(com)) {
			System.out.println("비겼습니다");
			h1.inputCheck=true;
		}else if((user.equals("가위") && com.equals("보"))
				|| (user.equals("바위") && com.equals("가위"))
				|| (user.equals("보") && com.equals("바위"))) {
			System.out.println("USER가 이겼습니다.");
			h1.inputCheck=true;
		}else {
			System.out.println("USER가 졌습니다.");
			h1.inputCheck=true;
		}
	}
	
}

class GameCount extends Thread {
	@Override
	public void run() {
		for(int i=5; i>=1; i--) {
			if(h1.inputCheck) {
				return;
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("시간이 초과되었습니다.");
		System.exit(0);
	}
}
