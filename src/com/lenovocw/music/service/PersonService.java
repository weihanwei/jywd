package com.lenovocw.music.service;



import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;


public interface PersonService {

	public Map<String, Object> getPersonInfo(String phone) throws Exception;
	
	public Map<String, Object> getMSGByphone(String phone);

	public Map<String, Object> getMyPrivilegeByPhone(String phone) throws Exception;

	public List<Map<String, Object>> getMyDownloadMSG(String phone) throws Exception;

	public int cleanDowm(String phone) throws Exception;

	public Map<String, Object> getBillMSG(String phone) throws Exception;

	public Map<String, Object> getShopManagerMSG(String phone) throws Exception;

	public Map<String, Object> handleShopManager(String phone,
			String newPassword, String oldPassword) throws Exception;
	
	public List<Map<String, Object>> queryForRecord(String phone);
	
	public Map<String, Object> queryGoldCount(String phone);
	
	public List<Map<String, Object>> queryForGiveGoldRecord(String phone);
	
	public String goldExchange(String phone, String type) throws JSONException, UnsupportedEncodingException;
	
	public boolean isCanExchange(String phone, String type);
	
	public Map<String, String> isExcess(String phone, String type);
	
	
}
