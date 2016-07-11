package com.base;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;
public class SpringFactory implements BeanFactoryAware {
	private static BeanFactory factory;

	public void setBeanFactory(BeanFactory factory) throws BeansException {
		this.factory = factory;
	}

	public static <T> T getBean(String className) {
		return (T) factory.getBean(className);
	}
}