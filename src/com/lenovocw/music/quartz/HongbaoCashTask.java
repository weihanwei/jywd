package com.lenovocw.music.quartz;

import java.util.TimerTask;

import javax.servlet.ServletContext;

import com.lenovocw.utils.HttpUtil;
import com.lenovocw.utils.PropertyUtil;

public class HongbaoCashTask extends TimerTask{
	public HongbaoCashTask(ServletContext context){
		
    }
 
 public void run() { 
	 String url = PropertyUtil.getKey("jywd")+"/flow/cashOutTimeRedpackage.do";
     HttpUtil.postRequestJson(url);
	   System.gc();
	   
    }
}
