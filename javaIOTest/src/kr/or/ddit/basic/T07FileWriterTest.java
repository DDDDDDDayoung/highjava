package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class T07FileWriterTest {
	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일에 저장하기
		
		// 콘솔(표준 입력장치)과 연결된 입력용 문자 스트림 생성
		// InputStreamReader => 바이트 기반 스트림을 문자 기반 스트림으로
		//						변환해주는 보조 스트림이다.
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = null; //파일 출력용 문자기반 스트림
		
		try {
			
			fw = new FileWriter("e:/D_Other/testChar.txt");
			
			int data = 0;
			
			System.out.println("아무거나 입력하세요... (입력 후  Ctrl+z 눌러주세요.)");
			
			// 콘솔에서 입력할 때 입력 끝 표시는 Ctrl+z 키를 누르면 된다.
			while((data = isr.read()) != -1) {
				fw.write(data); //콘솔에서 입력받은 값을 파일에 저장하기
			}
			
			System.out.println("작업 끝...");
			
		}catch(IOException ex){
			ex.printStackTrace();
		}finally {
			try{
				fw.close();
				isr.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
