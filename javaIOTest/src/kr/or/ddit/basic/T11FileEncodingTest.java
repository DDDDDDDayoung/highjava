package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class T11FileEncodingTest {
	public static void main(String[] args) throws IOException {
		/*
			outputStreamWriter => 바이트 기반의 출력용 객체를 문자기반 출력용 객체로
								   변환해주는 보조스트림
					=> 이 객체도 출력할 때 '인코딩 방식'을 지정해서 출력할 수 있다.
		*/
		
			// 키보드로 입력한 내용을 파일로 저장하는데...
			// out_utf8.txt 파일은 'utf-8'인코딩 방식으로
			// out_ansi.txt 파일은 'ms949'인코딩 장식으로 저장하도록 사용한다.
		
			
			InputStreamReader isr = new InputStreamReader(System.in);
			
			// 파일 출력용 
			FileOutputStream fos1 = new FileOutputStream("e:/D_Other/out_utf8.txt");
			FileOutputStream fos2 = new FileOutputStream("e:/D_Other/out_ansi.txt");
			
			OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "UTF-8");
			OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "MS949");
			
			int data = 0;
			
			System.out.println("아무거나 입력한 후 Ctrl-z 를 눌러주세요...");
			
			while((data = isr.read()) != -1) {
				osw1.write(data);
				osw2.write(data);
			}
			
			System.out.println("작업 완료...");
			
			// 보조스트림만 닫아도 된다.
			osw1.close();
			osw2.close();
			isr.close();
			
			fos1.close();
			fos2.close();
			
	}
}
