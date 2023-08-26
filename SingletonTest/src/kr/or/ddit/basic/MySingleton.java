package kr.or.ddit.basic;

public class MySingleton {
	/*
	 싱글턴 패턴이란?
	  => 객체(인스턴스)를 한 개만 만들어지게 하는 프로그래밍 방법
	 	- 싱글턴 클래스를 구성하는 방법
	 	1. 자기자신 class 참조변소 멤버변수로 선언한다.
	 	 (이 변수는 private static으로 선언한다.)
	 	2. 생성자를 private으로 한다.
	 	 (외부에서 생성자에 접근 못하게 하기 위해서... 즉, 외부에서 new 명령을 사용하지 못하게 하기 위해서...)
	 	3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메서드를 만든다.
	 	 (이 메서드는 static으로 선언하고, 이름은 보통 getInstance()로 지정한다.)	
	 */
	
	// 자기 자신의 class 참조값을 저장하는 멤버변수 선언
	private static MySingleton single;
	
	public MySingleton() {
		System.out.println("생성자 입니다.");
	}
	
	public static MySingleton getInstance() {
		if(single == null) {
			single = new MySingleton();
		}
		return single;
	}
	
	public void displayText() {
		System.out.println("안녕하세요. 싱글턴 객체입니다.");
	}
	
	
}
