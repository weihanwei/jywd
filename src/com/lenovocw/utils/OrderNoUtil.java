package com.lenovocw.utils;

import java.util.Date;

/**
 * <span> <b>功能：</b> </span><br />
 * <span> <!--在这下面一行写功能描述-->
 *
 *</span><br /><br />
 * <span> Copyright LENOVO </span><br /> 
 * <span> Project Name apps-common </span><br /> 
 * <span> Author  ZengZS </span><br /> 
 * <span> Create Time 2012-7-12  上午10:28:20 </span><br /> 
 * <span> App version 1.0.0 </span> <br />
 * <span> JDK version used 6.0 </span><br /> 
 * <span> Modification history none    </span><br /> 
 * <span> Modified by none    </span><br />
 * 
 */
public class OrderNoUtil{

	private static int defalutno = 10;
	public static String orderBusNo(){
		return "YL" + DateFormatHelp.yyyyMMddHHmmss.format(new Date())+nextNo();
	}
	
	public static int nextNo(){
		return defalutno>99?10:++defalutno;
	}

}
