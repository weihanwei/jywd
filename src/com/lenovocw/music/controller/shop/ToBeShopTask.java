package com.lenovocw.music.controller.shop;

import java.util.TimerTask;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lenovocw.music.service.ToBeShopManagerService;
import com.lenovocw.utils.HttpUtil;
import com.lenovocw.utils.PropertyUtil;

/**
 * Main Function : 调用TimerTask执行定时任务
 * @author 张志高
 * 
 */


public class ToBeShopTask extends TimerTask
{

   public ToBeShopTask(ServletContext context){
		
      }
   
   public void run() { 
	   
	   String url = PropertyUtil.getKey("jywd")+"/toBeShopManager/UpdateMsmRecommend.do";
	   
	   HttpUtil.postRequestJson(url);
	   
	   System.gc();
	   
      } 
              
} 


