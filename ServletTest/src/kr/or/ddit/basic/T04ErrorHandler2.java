package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T04ErrorHandler2 extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
		
		Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
		
		String message = (String) req.getAttribute("javax.servlet.error.message");
		
		String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
		
		if(servletName == null) {
			servletName = "알 수 없는 서블릿 이름";
		}
		
		String reqUri = (String) req.getAttribute("javax.servlet.error.request_uri");
		
		if(reqUri == null) {
			reqUri = "알 수 없는 URI";
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		String title = "에러/예외 정보";
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><head><title>" + title + "</title></head>");
		out.println("<body>");
		
		if(throwable == null && statusCode == null) {
			out.println("<h2>에러/예외 정보 없음</h2>");
		}else {
			out.println("<h2>에러/예외 정보</h2>");
			out.println("<p>상태코드 : " + statusCode + "</p><br><br>");
			out.println("<p>에러/예외 메세지: " + message + "</p><br><br>");
			out.println("<p>서블릿 이름 : " + servletName + "</p><br><br>");
			out.println("<p>요청URI : " + reqUri + "</p><br><br>");
			
			if(statusCode != null) {
				out.println("<p>예외타입 : " + throwable.getClass().getName() + "</p><br><br>");
				out.println("<p>예외/에러 메시지 : " + throwable.getMessage() + "</p>");
			}
		}
		out.println("</body></html>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		
	}
}
