package com.lenovocw.init;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovocw.music.model.DynamicRule;



/**
 * <span> <b>功能：</b> </span><br />
 * <span> <!--在这下面一行写功能描述-->
 *
 *</span><br /><br />
 * <span> Copyright LENOVO </span><br /> 
 * <span> Project Name apps-common </span><br /> 
 * <span> Author  ZengZS </span><br /> 
 * <span> Create Time 2012-4-18  下午04:37:58 </span><br /> 
 * <span> App version 1.0.0 </span> <br />
 * <span> JDK version used 6.0 </span><br /> 
 * <span> Modification history none    </span><br /> 
 * <span> Modified by none    </span><br />
 * 
 */
@Service 
public class InitSystemData{
	/** 初始化动态信息 **/
	public static Map<String, DynamicRule> dynamicRuleMap = new ConcurrentHashMap<String, DynamicRule>();
	//获取连接路径和端口，如需则在初始化时赋值
	public static  String CACHE_HOST = null;
	public static  int CACHE_PORT = 0;
	// 从数据库获取数据	
	@PostConstruct
	public void init(){
		System.out.println("系统初始化");
     
		
	}
	


}
