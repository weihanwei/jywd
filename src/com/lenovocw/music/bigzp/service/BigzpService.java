package com.lenovocw.music.bigzp.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;




public interface BigzpService {

	public boolean getPhoneActivityBoolean(String phone,Map<String,Object> map);

	public List<Map<String, Object>> getPhoneLinkeRules(Map<String, Object> map);

	public int getPhoneShowNum(Map<String, Object> map);

	public String getHandleLuckyDraw(String phone, String pROJECTID, String aCTIVITYID, String aCTIVITYNAME, String aGENTNO) throws JSONException, UnsupportedEncodingException;



	Map<String, Object> getHandleLuckyDrawCJ(List pROBABILITYlist,
			String phone, HttpServletRequest request);

}
