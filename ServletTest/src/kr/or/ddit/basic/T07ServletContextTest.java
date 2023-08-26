package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

public class T07ServletContextTest extends HttpServlet{
	/*
	 	 서블릿 컨텍스트 객체에 대하여...
	 	 
	 	 1. 서블릿 프로그램이 컨테이너와 정보를 주고 받기 위한 메서드를 제공한다.
	 	 	ex) 파일의 MINME TYPE 정보 가져오기, 요청 정보 보내기, 로깅 
	 	 	
	 	 2. 웹 애플리케이션당 1개씩 생성된다. 
	 	 
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		ServletContext ctx= req.getServletContext();
		 
		System.out.println("서블릿 컨텍스트의 기본 경로 : " + ctx.getContextPath());
		System.out.println("서버 정보 : " + ctx.getServerInfo());
		System.out.println("서블릿 API의 메이저 버전정보 : " + ctx.getMajorVersion());
		System.out.println("서블릿 API의 마이너 버전정보 : " + ctx.getMinorVersion());
		System.out.println("파일에 대한 MIME 타입 정보 : " + ctx.getMimeType("hello.mp3"));
		System.out.println("파일시스템 삽입 실제 경로 : " + ctx.getRealPath("/"));
		
		ctx.setAttribute("attr1", "속성1");
		ctx.setAttribute("attr1", "속성11");
		System.out.println("attr1의 속성값 : " + ctx.getAttribute("attr1"));
		
		ctx.log("서블릿 컨텍스트를 이용한 로깅 작업 중 입니다...");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
