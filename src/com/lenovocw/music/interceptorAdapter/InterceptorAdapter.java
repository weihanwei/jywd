package com.lenovocw.music.interceptorAdapter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class InterceptorAdapter implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
		String url=arg0.getRequestURI();
		
		if(!url.contains("login")){
			
			String phone=(String) arg0.getSession().getAttribute("phone");
			if(phone==null){
				arg1.sendRedirect("/jywd/login/loginUI.do"); 
			}
			
		}
			
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		
		return true;
	}

	
	
}
