package com.lenovocw.music.controller.shop;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;//定时器类
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * 
 * Main Function : 设置定时器
 * @author 张志高
 *
 */
public class ToBeShopListenter implements ServletContextListener {
	
	 public Timer timer = null;
	  
     public void contextInitialized(ServletContextEvent event)
               {//在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
	    timer = new Timer(true);
	    
	    ServletContext context = event.getServletContext(); 
	    
        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        
        //定制每天的00:01:00执行，
        calendar.set(year, month, day, 9,00, 00);
        
        int period = 1*60*60*1000;
        
        Date date = calendar.getTime();
        
        //每天的date时刻执行task，每隔2秒重复执行
        timer.schedule(new ToBeShopTask(context), date, period);
	    
//	    timer.schedule(new ToBeShopTask(context),0,5*1000);//调用MyTask，0表示任务无延迟，5*1000表示每隔5秒执行任务，60*60*1000表示一个小时。
//	    event.getServletContext().log("短信推荐数据同步定时器已启动！");
        
	}
     
	public void contextDestroyed(ServletContextEvent event)
	     {//在这里关闭监听器，所以在这里销毁定时器。
	       timer.cancel();
	       event.getServletContext().log("短信推荐数据同步定时器销毁！");
	     }
}


	

