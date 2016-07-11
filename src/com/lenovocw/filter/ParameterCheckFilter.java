package com.lenovocw.filter;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParameterCheckFilter implements Filter {



	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		   XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(  
		            (HttpServletRequest) request);  
		            filterChain.doFilter(xssRequest, response);  
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {


	}

}
