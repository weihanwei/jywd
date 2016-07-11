package com.lenovocw.utils;

import java.util.ResourceBundle;

public class PropertyUtil {
	private static ResourceBundle rb;
	static{
		rb = ResourceBundle.getBundle("service");
	}
	public static String getKey(String key){
		return rb.getString(key);
	}
}
