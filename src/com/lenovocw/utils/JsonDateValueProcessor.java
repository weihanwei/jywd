package com.lenovocw.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public final class JsonDateValueProcessor implements JsonValueProcessor{
	public static final SimpleDateFormat yy_MM_dd__hh_mm_ss=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return null;
	}

	public Object processObjectValue(String key, Object value, JsonConfig arg2) {
		if(value==null)  
			return "";  
		if (value instanceof Date) {
			return yy_MM_dd__hh_mm_ss.format((Date) value) ;
		}
		return null;
	}

}
