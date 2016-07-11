package com.lenovocw.dom;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * hmtl页面跳转
 * @date 2014-03-19 10:10:51
 * @version 1.0
 */
public class UrlViewResolver extends UrlBasedViewResolver {
	@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
//		<property name="prefix" value="/WEB-INF/view_jsp/" />
//		<property name="suffix" value=".jsp" />
		
		if(viewName.lastIndexOf(".html") > -1){
			setSuffix("");
			setPrefix("");
		}
		return super.resolveViewName(viewName, locale);
	}
}
