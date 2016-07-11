package com.lenovocw.music.service;

import java.util.List;
import java.util.Map;




public interface CallService {

	public Map<String, Object> getPhoneMSG(String phone) throws Exception;

	public Map<String, Object> getCornetNetMSG(String phone) throws Exception;
	
	public Map<String, Object> relativesNetDel(String cornet,String phone) throws Exception;

	public List<Map<String, Object>> relativesNetAdd(String phone,
			String phones, String aGENTNO) throws Exception;

	public Map<String, Object> getRelativesNetMSG(String phone) throws Exception;

	public Map<String, Object> relativesNetHandle(String phone, String aGENTNO) throws Exception;

	public Map<String, Object> cornetNetHandle(String targetPhone, String addPhone, String aGENTNO) throws Exception;

	public Map<String, Object> cornetNetUpdateTc(String targetPhone,
			String phone) throws Exception;

	public Map<String, Object> cornetNetUpdateDh(String dh, String phone) throws Exception;

	public Map<String, Object> relativesNetUpgrade(String phone, String aGENTNO) throws Exception;

	public Map<String, Object> getMyshareCallRedpackage(String phone,String type)throws Exception;



}
