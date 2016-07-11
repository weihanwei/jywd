package com.lenovocw.music.util;

import java.util.ResourceBundle;

public class GetSysParam {

	public static String getUrl(){
		ResourceBundle bundle = ResourceBundle.getBundle("service");
		String url = bundle.getString("boss_url");
		return url;
	}
	
	public static String getValue(String propertity){
		ResourceBundle bundle = ResourceBundle.getBundle("service");
		String value = bundle.getString(propertity);
		return value;
	}

}
