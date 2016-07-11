/**
 * 
 */
package com.lenovocw.web.spring;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;


/**
 * JsonViewResolver
 * @author 5754
 * Apr 21, 2007
 */
public class JsonOrFreemarkViewResolver extends AbstractTemplateViewResolver {

	/**
	 * Sets default viewClass to <code>requiredViewClass</code>.
	 * @see #setViewClass
	 * @see #requiredViewClass
	 */
	public JsonOrFreemarkViewResolver() {
		setViewClass(requiredViewClass());
	}

	/**
	 * Requires FreeMarkerView.
	 * @see FreeMarkerView
	 */
	protected Class requiredViewClass() {
		return FreeMarkerView.class;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractCachingViewResolver#loadView(java.lang.String, java.util.Locale)
	 */
	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		if (null == viewName) {
			return new JsonView();
		}

		if (viewName.length() == 0) {
			return new JsonView();
		}

		if (viewName.startsWith("json:")) {
			return new JsonView(viewName.substring(5));
		}
		return super.loadView(viewName, locale);
	}

}
