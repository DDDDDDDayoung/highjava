package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 >로 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수를 기준으로 정렬하여 출력한다.
*/

public class horselace {
	static int CURR_RANK = 1;
	
	public static List<Horse> horList;

	public static void main(String[] args) {
		
		horList = new ArrayList<Horse>();
		
		horList.add(new Horse("1번 말"));
		horList.add(new Horse("2번 말"));
		horList.add(new Horse("3번 말"));
		horList.add(new Horse("4번 말"));
		horList.add(new Horse("5번 말"));
		horList.add(new Horse("6번 말"));
		horList.add(new Horse("7번 말"));
		horList.add(new Horse("8번 말"));
		horList.add(new Horse("9번 말"));
		horList.add(new Horse("10번 말"));
		
		Thread hd = new horPosition();
		hd.start();
		
		for(int i =0; i<horList.size(); i++) {
			horList.get(i).start();							// >>이게 뭐하는거지
		}
		
		try {
			hd.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("============================================================");
		System.out.println("                            경마가 종료되었습니다.");
		System.out.println("============================================================");
		
		Collections.sort(horList);
		
		for(int i=0; i<horList.size(); i++) {
			System.out.println(horList.get(i).getRank() + "등 : "+horList.get(i).getHorName());
		}
		
		
		

	}
	
	

}

class Horse extends Thread implements Comparable<Horse>{
	private String horName;
	private int rank;
	private int position;

	public Horse(String horname) {
		super();
		this.horName = horname;
	}
	
	

	public String getHorName() {
		return horName;
	}



	public void setHorName(String horName) {
		this.horName = horName;
	}



	public int getRank() {
		return rank;
	}



	public void setRank(int rank) {
		this.rank = rank;
	}



	public int getPosition() {
		return position;
	}



	public void setPosition(int position) {
		this.position = position;
	}
	
	@Override
	public void run() {
		for(int i=0; i<50; i++) {
			
			try {
				Thread.sleep((int)(Math.random()*500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setPosition(i);
		}
		this.rank = horselace.CURR_RANK;
		setRank(horselace.CURR_RANK++);
	}



	@Override
	public int compareTo(Horse o) {
		return Integer.compare(rank, o.getRank());
	}
	
}

class horPosition extends Thread{
	
	public void clear() {
		for(int i = 0; i <=30; i++) {
			System.out.println();
		}
	}
	
	@Override
	public void run() {
		
		while(true) {
			clear();
			
			int endH=0;
			
			System.out.println("                            경마가 시작되었습니다.");
			System.out.println("============================================================");
			System.out.println();
			
			for(int i=0; i<horselace.horList.size(); i++) {
				
				String course = "--------------------------------------------------";
				Horse horse = horselace.horList.get(i);
				
				if(horse.getPosition() != 49) {
					System.out.print(horse.getHorName() + " : ");
					System.out.print(course.substring(0,horse.getPosition())+ ">");
					System.out.println(course.substring(horse.getPosition()+1, 50));
					
				}else {
					System.out.print(horse.getHorName() + " : ");
					System.out.print(course + "[도착]");
					System.out.println();
					
					endH++;
				}								
			}
			
			if(endH==10) {
				return;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
