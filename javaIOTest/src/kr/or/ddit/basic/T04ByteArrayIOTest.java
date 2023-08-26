package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest {
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		byte[] temp= new byte[4]; //
		
		// 직접 복사하는 방법
		/*outSrc = new byte[inSrc.length];
		
		for(int i=0; i<inSrc.length; i++) {
			outSrc[i] = inSrc[i];
		}*/
		
		// arraycopy를 이용한 배열복사 방법
		/*outSrc = new byte[inSrc.length]; //공간 확보
		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
	
		System.out.println("직접 복사 후 ourSrc =>"
				+Arrays.toString(outSrc));*/
		
		//스트림 객체를 이용한 방법
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int readBytes = 0; //읽어온 데이터 저장할 변수
		
		// read() 메서드 => byte단위로 데이터를 읽어와 int형으로 반환한다.
		//				  더 이상 읽을 데이터가 없으면 -1을 반환한다.
		while((readBytes = bais.read(temp)) != -1) {	
			
			System.out.println("temp =>"
					+Arrays.toString(temp));
			
			baos.write(temp, 0, readBytes);
		}
		
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc =>"
				+Arrays.toString(inSrc));
		System.out.println("outSrc =>"
				+Arrays.toString(outSrc));
				
		
		
	}
}
