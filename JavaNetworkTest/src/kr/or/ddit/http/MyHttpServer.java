package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.StringTokenizer;



/**
 * 간단한 HTTP Server 예제
 * @author PC-18
 *
 */
public class MyHttpServer {
	private final int port = 80;
	private final String encoding = "UTF-8";
	
	public void start() {
		System.out.println("HTTP 서버가 시작되었습니다...");
		
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(this.port);
			
			while(true) {
			
			Socket socket = server.accept();
			
			HttpHandler handler = new HttpHandler(socket);
			handler.start();
			
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * HTTP 요청 처리를 위한 스레드
	 * @author PC-18
	 *
	 */
	class HttpHandler extends Thread {
		private final Socket socket;

		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			
			OutputStream out = null;
			BufferedReader br = null;
			
			try {
				
				out = new BufferedOutputStream(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				// 요청 헤더정보 파싱하기
				StringBuilder sb = new StringBuilder();
				
				// Request Line
				String reqLine = br.readLine(); // 첫줄은 요청라인...
				
				System.out.println("Request Line : " + reqLine);
				
				String reqPath = ""; // 요청경로
				
				// 요청 페이지 정보 가져오기
				StringTokenizer st = new StringTokenizer(reqLine);
				while(st.hasMoreTokens()) {
					String token = st.nextToken();
					
					if(token.startsWith("/")) {
						reqPath = token;
						break;
					}
				}
				
				// URL 디코딩 처리(한글깨짐 문제)
				reqPath = URLDecoder.decode(reqPath, encoding);
				
				System.out.println("reqPath : " + reqPath);
				
				/////////////////////////////////////////////////////////////////////
				
				String filePath = "./WebContent" + reqPath;
				
				// 해당 파일 이름을 이용하여 Content-Type 정보 추출하기
				String contentType = URLConnection.getFileNameMap().getContentTypeFor(filePath);
				
				System.out.println("Content-Type : " + contentType);
				
				// CS파일인 경우 인식이 안되서 추가함.
				if(contentType == null && filePath.endsWith(".css")){
					contentType = "text/css";
				}
				
				File file = new File(filePath);
				if(!file.exists()) {
					// 사용자에게 에러메시지 전송
					makeErrorPage(out,404,"Not Found");
					return;
				}
				
				byte[] body = makeResponseBody(filePath);
				
				byte[] header = makeResponseHeader(body.length, contentType);
				
				out.write(header);
				
				out.write("\r\n\r\n".getBytes()); // Empty Line 전송
				
				out.write(body); // 응답전송
			
				out.flush();
				
				
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}finally {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		
	}
	
	/**
	 * 응답헤더 생성하기
	 * @param ContentLenght 응답내용 크기
	 * @param mimeType 컨텐트 타입
	 * @return 헤더내용 바이트배열
	 */
	private byte[] makeResponseHeader(int ContentLenght, String mimeType) {
		
		String header = "HTTP/1.1 200 OK \r\n" + "Server: MyHTTPServer 1.0 \r\n" 
					+ "Content-length: " + ContentLenght + "\r\n"
					+ "Content=type: " + mimeType + "; charset=" + this.encoding;
		
		return header.getBytes();
	}
	
	/**
	 * 응답내용 생성하기
	 * @param filePath 응답내용으로 사용할 파일 경로
	 * @return 응답내용을 담은 바이트 배열
	 */
	private byte[] makeResponseBody(String filePath) {
		
		FileInputStream fis = null;
		byte[] data = null;
		
		try {
			File file = new File(filePath);
			data = new byte[(int)file.length()];
			
			fis = new FileInputStream(file);
			fis.read(data);
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	private void makeErrorPage(OutputStream out, int statusCode, String errMsg) {
		String statusLine = "HTTPL/1.1" +" " +statusCode + "" + errMsg;
		
		try {
			
			out.write(statusLine.getBytes());
			
		}catch(IOException ex) {
			
		}
	}
	
	public static void main(String[] args) {
		new MyHttpServer().start();
	}
	
}
