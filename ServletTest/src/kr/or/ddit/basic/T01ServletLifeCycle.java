package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿의 라이프사이클을 확인하기
 * (서블릿이란? 컨테이너(서블릿 엔진)에 의해 관리되는 자바기반 웹 컴포넌트로서,
 * 동적인 웹 컨텐츠 생성을 가능하게 해준다.)
 */


public class T01ServletLifeCycle extends HttpServlet{
	
	public T01ServletLifeCycle() {
		System.out.println("T01ServletLifeCycle 생성자 호출됨.");
	}
	
	@Override
		public void init() throws ServletException {
		//초기화 코드 작성하는 부분...
		
			System.out.println("init() 메서드 호출됨");
		}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 실제적인 작업이 시작되는 지점 (자바의 메인메서드 역할)
		
		System.out.println("service() 매서드 호출됨.");
		
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 GET인 경우 호출됨...
		System.out.println("doGet() 메서드 호출됨.");
				throw new ServletException("서블릿에서 엄청 큰 예외 발생했어요.!!!");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 POST인 경우 호출됨...
		System.out.println("doPost() 메서드 호출됨.");
	}
	
	@Override
	public void destroy() {
		// 서블릿객체 소멸시(컨테이너로부터 서블릿 객체 제거시) 필요한 호출됨.
		System.out.println("destroy() 메서드 호출됨.");
	}
	
}
