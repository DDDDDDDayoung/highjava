package kr.or.ddit.basic;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener, ServletContextAttributeListener {
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("[ MyHttpSessionListener] sessopmCreated 호출 됨.");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("[ MyHttpSessionListener] sessionDestroyed 호출 됨.");
		
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		System.out.println("[ MyHttpSessionListener] attributeAdded 호출 됨.");
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		System.out.println("[ MyHttpSessionListener] attributeRemoved 호출 됨.");
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		System.out.println("[ MyHttpSessionListener] attributeReplaced 호출 됨.");
		
	}
	
	

}
