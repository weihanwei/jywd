package com.lenovocw.music.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

public interface IndexService {

	public List<Map<String, Object>> getBanner() throws Exception;
	
	public List<Map<String, Object>> getAppFunction() throws Exception;

	public List<Map<String, Object>> getDownAppFunction() throws Exception;

	public Map<String, Object> isFirstLogin(String phone) throws Exception;

	public int isGuide(String phone) throws Exception;

	public List<Map<String, Object>> getSearchMSG(String type, String keyword) throws Exception;

	public Map<String, Object> getPersonInfo(String phone) throws Exception;

	public int delNotice(String id, String nid, String phone) throws Exception;

	public int collectNotice(String id, String phone) throws Exception;

	public List<Map<String, Object>> getBannerByArea(String area);

	public Map<String, Object> getDownAppById(String id);

}
