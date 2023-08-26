package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T06WildCardTest {
	/*
	 	와일드 카드에 대하여...
	 	
	 	와일드카드(?)는 제너릭 타입을 이용한 타입 안전한 코드를 위해 사용되는 특별한 종류의
	 	인수(argument)로서, 변수선언, 객체생성 및 메서드 정의할 때 사용된다.
	 	
	 	<? extends T> 	=> 와일드 카드의 상한 제한. T와 그 자손들만 가능
	 	<? super T> 	=> 와일드 카드의 하한 제한. T와 그 조상들만 가능
	 	<?>				=> 허용 가능한 모든 타입 가능.
	  
	 */
	
	public static void main(String[] args) {
		
		List<?> list = new ArrayList<Integer>();
		
		FruitBox<Fruit> fruitBox = new FruitBox<>();
		FruitBox<Apple> appleBox = new FruitBox<>();
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		System.out.println(fruitBox.getFruitList());
		
		appleBox.add(new Apple());
		appleBox.add(new Apple());
		
		System.out.println(appleBox.getFruitList());
		
		Juicer.makeJuiece(fruitBox);
		
	}

}

class Juicer{
	
	
	//static <T extends Fruit> void makeJuiece(FruitBox<T> box) {
	//static void makeJuiece(FruitBox<? extends Fruit> box) {	
	static void makeJuiece(FruitBox<?> box) {	
		
		String fruitListStr = ""; //과일목록
		
		int cnt = 0;
		for(Object f : box.getFruitList()) {
			if(cnt==0) {
				fruitListStr += f;
			}else{
				fruitListStr += ", " + f;
			}
		cnt++;
		}
	
	System.out.println(fruitListStr + " => 쥬스완성!!!");
	}
}



class Fruit{
	private String name;
	

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일 (" + name + ")";
	}
	
}

class Apple extends Fruit {
	
	public Apple() {
		super("사과");
	}
}

class Grape extends Fruit {
	
	public Grape() {
		super("포도");
	}
}

class FruitBox<T> {
	
	private List<T> FruitList;
	
	public FruitBox() {
		FruitList = new ArrayList<T>();
	}
	
	// 과일 담기
	public void add(T fruit) {
		FruitList.add(fruit);
	}

	public List<T> getFruitList() {
		return FruitList;
	}
	
}