package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04ListSortTest {

	public static void main(String[] args) {
		List<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "이순신", "010-4444-1111"));
		memList.add(new Member(6, "강감찬", "010-5555-1111"));
		memList.add(new Member(2, "일지매", "010-6666-1111"));
		
		System.out.println("정렬 전 : ");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		System.out.println("====================================");
		Collections.sort(memList);

		System.out.println("정렬 후 : ");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		Collections.shuffle(memList); //섞기
		
		System.out.println("섞은 후 : ");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		Collections.sort(memList,new SortNumDesc());
		
		System.out.println("번호 내림차순 정렬 후 : ");
		for(Member mem : memList) {
			System.out.println(mem);
		}
	}

}


class Member implements Comparable<Member>{
	
	private int num;		//번호
	private String name;	//이름
	private String tel;		//전화번호
	

	// 회원이름을 기준으로 오름차순 정렬되도록 할 수 있는 클래스
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	@Override
	public int compareTo(Member mem) {
		
		return this.getName().compareTo(mem.getName()) *-1;
	}
	
}

//회원번호를 이용한 내림차순 정렬을 위한 외부정렬자
class SortNumDesc implements Comparator<Member> {

	@Override
	public int compare(Member mem1, Member mem2) {
		
		if(mem1.getNum() > mem2.getNum()) {
			return -1;
		}else if(mem1.getNum() == mem2.getNum()) {
			return 0;
		}else {
			return 1;
		}
		
	}
	
}