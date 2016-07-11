package com.lenovocw.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RequestFilter implements Filter {

	public RequestFilter() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		long t1 = System.currentTimeMillis();

		chain.doFilter(request, response);

		long t2 = System.currentTimeMillis();

		System.out.println("***请求 (" + request.getRequestURI() + ") 完成时间 (ms): " + (t2-t1) );
	}

	public void destroy() {
	}

}
