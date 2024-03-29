package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
// Properties -> key와 value값으로 이루어진 텍스트 파일
public class T02PropertiesTest {
	public static void main(String[] args) {
		
		// 읽어온 정보를 저장할 Properties 객체 생성
		Properties prop = new Properties();
		
		try {
			// Properties객체로 파일내용 읽기
			prop.load(new FileInputStream("./res/db.properties"));
			
			// 읽어온 내용 출력하기 
			Enumeration<String> keys = 
					(Enumeration<String>) prop.propertyNames();
			
			while(keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = prop.getProperty(key);
				System.out.println(key + " => " + value);
			}
			System.out.println("출력 끝...");
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
