package com.lenovocw.music.service;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public interface LoginService {

	public Map<String, Object> login(String phone,String password,String type, HttpServletRequest request) throws Exception;
	
	
	 public Map<String, Object> getRandomPassword(String phone) throws Exception;


	public Map<String, Object> getUUID(String string) throws Exception;


	public Map<String, Object> pointLogin(String channelCod, String data,HttpServletRequest request) throws Exception;
	
}
