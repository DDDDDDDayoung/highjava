package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelSave {
	
	private static Map<String, HotelVO>hotelMap;
	
	public static void main(String[] args) {
// 스캐너를 여기서 호출하거나	
		new HotelSave(). HotelStart();
	}	
	public void save() {
		ObjectOutputStream oos = null;
		Set<String> keySet = hotelMap.keySet();
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("e:/D_Other/Hotel.bin"));
			
			for (String roomNum : keySet) {
				HotelVO hv = hotelMap.get(roomNum);
				oos.writeObject(hv);
				
				System.out.println("☆ 객실상태 저장 완료 ☆");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void Input() {
		ObjectInputStream ois = null;
		
		try {
			
			ois = new ObjectInputStream(new FileInputStream("e:/D_Other/Hotel.bin"));
			
			Object obj = null;
			
			while((obj = ois.readObject()) != null) {
				HotelVO hv = (HotelVO) obj; // 형변환 시켜줌
				hotelMap.put(hv.getRoomNum(), hv);
			}
			
		} catch (IOException |ClassNotFoundException ex) {
			System.out.println("☆객실 상태 읽기 완료☆");
		} finally {
			try {
				ois.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
 

// 스캐너 객체를 여기서 생성하거나
	static Scanner scan;
// 생성자로 생성해서 호출하거나
	public HotelSave() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<String, HotelVO>();
	}

	public void HotelStart() {
		
		System.out.println("***********************");
		System.out.println("    호텔 문을 열었습니다.");
		System.out.println("***********************");
		
		while (true) {
			System.out.println("**********************************");
			System.out.println("       어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인 2.체크아웃 3.객실예약현황 4.업무종료");
			System.out.println("**********************************");
			System.out.print("메뉴 선택 : ");
			
			
		
			int menu = scan.nextInt(); // 메뉴 번호 입력란			

			switch (menu) {
			case 1:
				Input();
				System.out.println(1);
				checkIn(); // 체크인
				break;
			case 2:
				checkOut(); // 체크아웃
				break;
			case 3:
				roomCon(); // 객실상태(현황)
				break;
			case 4:
				save();
				System.out.println("업무가 종료되었습니다. 감사합니다.");
				break;
			default:
				System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
			}
		}
	}

	// 객실 상태
	private static void roomCon() {
//		 interator
		Set<String> keySet = hotelMap.keySet();

		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			String key = it.next();
			System.out.println("방 번호" + key + "호의 " + hotelMap.get(key).getName() + "님");
		}
	}
	
	private static void checkOut() {
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.println("방 번호 입력 : ");
		String roomNum = scan.next();

		if (hotelMap.get(roomNum) != null) {
			hotelMap.remove(roomNum);
			System.out.println("체크아웃 되었습니다.");
		} else {
			System.out.println("예약된 방이 아닙니다.");
		}
	}

	private static void checkIn() {
		System.out.println("어느 방을 체크인 하시겠습니까?");
		System.out.println("방 번호 : ");
		String roomNum = scan.next();
//		 sc.nextInt();
		if (hotelMap.get(roomNum) != null) {
			System.out.println(roomNum + "호실은 이미 예약이 되어있습니다.");
			System.out.println("다른 객실을 선택해주세요.");
			checkIn();
			return;
		} else {
			System.out.println("체크인 되었습니다.");
		}
		System.out.println("체크인 하시겠습니까?");
		System.out.println("이름 입력 : ");
		String name = scan.next();
		scan.nextLine();

		hotelMap.put(roomNum, new HotelVO(roomNum,name));
	}
}
class HotelVO implements Serializable {
	
	private String roomNum;
	private String name;

	public HotelVO(String roomNum,String name) {
		super();
		this.roomNum = roomNum;
		this.name=name;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "HotelVO [roomNum=" + roomNum + "]";
	}	
}