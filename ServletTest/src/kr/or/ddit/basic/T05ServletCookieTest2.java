package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T05ServletCookieTest2 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setCookieExam(req, resp);
	}
	
	private void setCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Cookie userId = new Cookie("userId", req.getParameter("userId"));
		
		Cookie name = new Cookie("name", URLEncoder.encode(req.getParameter("name"), "UTF-8"));
		
		userId.setMaxAge(60*60*24);
		userId.setHttpOnly(true);
		
		name.setMaxAge(60*60*48);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		resp.addCookie(userId);
		resp.addCookie(name);
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키 설정 예제";
		
		out.println("<!DOCTYPE html><html><head><title>" + title+ "</title></head><body>" + "<h1 align=\"center\">" 
				+ title + "</h1>" + "<ul><li><b>ID:</b> " 
				+ req.getParameter("userId") + "</li>"
				+"<li><b>이름: </b>" + req.getParameter("name") + "</li></ul>"
				+ "</body></html>");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
