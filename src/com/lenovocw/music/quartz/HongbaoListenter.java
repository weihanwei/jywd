package com.lenovocw.music.quartz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;//定时器类
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lenovocw.music.controller.shop.ToBeShopTask;
import com.lenovocw.utils.HttpUtil;
import com.lenovocw.utils.PropertyUtil;


/**
 * 
 * Main Function : 流量红包定时任务
 * @author hanwei
 *
 */
public class HongbaoListenter implements ServletContextListener
{
	
     private Timer timer = null;
     public void contextInitialized(ServletContextEvent event)
               {
    	//在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
 	    timer = new Timer(true);
 	    
 	    ServletContext context = event.getServletContext(); 
 	    
         //设置执行时间
         Calendar calendar = Calendar.getInstance();
         int year = calendar.get(Calendar.YEAR);
         int month = calendar.get(Calendar.MONTH);
         int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
         
         //定制每天的00:01:00执行，
         calendar.set(year, month, day, 9,00, 00);
         
         int period = 24*60*60*1000;
         
         Date date = calendar.getTime();
         
         //每天的date时刻执行task，每隔2秒重复执行
         timer.schedule(new HongbaoCashTask(context), date, period);
 	    
// 	    timer.schedule(new ToBeShopTask(context),0,5*1000);//调用MyTask，0表示任务无延迟，5*1000表示每隔5秒执行任务，60*60*1000表示一个小时。
// 	    event.getServletContext().log("短信推荐数据同步定时器已启动！");
         
 	
    	 //在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
//	    timer = new Timer(true);
//	    
//	    event.getServletContext().log("定时器已启动");//添加日志，可在tomcat日志中查看到
//	    Calendar cal = Calendar.getInstance();
//	    //每天定点执行
//	       cal.set(Calendar.HOUR_OF_DAY,2);
//	       cal.set(Calendar.MINUTE,5);
//	       cal.set(Calendar.SECOND,0);
//	      
//	       //设置每天定时运行时间
//	       Date time = cal.getTime();
//	       System.out.println(cal.toString());
//	       timer.scheduleAtFixedRate(new TimerTask() {  
//	            public void run() {  
//		            	//判断今天是不是周一
//		            	Calendar c = Calendar.getInstance();
//		      		    Date now = new Date();
//		  				SimpleDateFormat sdf = new SimpleDateFormat("HH");
//		  				String match = sdf.format(now);
//		  				if(match.equals("14")){
//		  					System.out.println("1111111111111111111111111111");
//		  					String url = PropertyUtil.getKey("jywd")+"/flow/cashOutTimeRedpackage.do";
//			                 HttpUtil.postRequestJson(url);
//		  				}
//	            	
//	            }  
//	        }, time, 1000 * 60 * 60 * 1);// 这里设定将延时每天固定执行  5*1000 ,1000 * 60 * 60 * 24
//	 //   timer.schedule(new SMTask(event.getServletContext()),0,5*1000);//调用MyTask，0表示任务无延迟，5*1000表示每隔5秒执行任务，60*60*1000表示一个小时。
//	    event.getServletContext().log("已经添加任务");
	}
     
	public void contextDestroyed(ServletContextEvent event)
	     {//在这里关闭监听器，所以在这里销毁定时器。
	       timer.cancel();
	       event.getServletContext().log("定时器销毁");
	     }
}


	

