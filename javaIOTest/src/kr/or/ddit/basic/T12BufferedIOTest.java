package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 성능 향상을 위한 보조스트림 예제
 * (바이트 기반의 Buffered스트림 사용 예제)
 */
public class T12BufferedIOTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		
		try {
			
			fos = new FileOutputStream("e:/D_Other/bufferTest.txt");
			
			// 버퍼의 크기를 지정하지 않으면 기복적으로 8192byte(8KB)로 설정된다.
			bos = new BufferedOutputStream(fos, 5);
			
			for(char ch='1'; ch<='9'; ch++) {
				bos.write(ch);
			}
			
			System.out.println("작업 끝...");
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				bos.close(); // 보조 스트림만 닫아도 된다.
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
		}
	}

}
