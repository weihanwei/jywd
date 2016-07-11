package com.lenovocw.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.dao.JdbcDao;
@Service(value="springGetBeanUtil")
public class SpringGetBeanUtil implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;
	
	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}
	
	
	public static Object getBean(String name){
		return applicationContext.getBean(name);
	}
	
	public static JdbcDao getDao(){
		if(applicationContext==null)
			applicationContext=new ClassPathXmlApplicationContext("classpath:*-config.xml");
		return (JdbcDao)applicationContext.getBean("jdbcDao");
	}
	
	@Transactional
	public static void executeBatchInTransactional(String [] sql){
		getDao().executeBatch(sql);
	}
}
