package com.lenovocw.utils;

import java.util.ResourceBundle;

public class ServiceConfig {
	
	protected static final ResourceBundle bundle = ResourceBundle.getBundle("service");

	/**
	 * 是否采取文件追加形式
	 * 
	 * @return
	 */
	public static String getValue(String key) {
		return bundle.getString(key);
	}
}
