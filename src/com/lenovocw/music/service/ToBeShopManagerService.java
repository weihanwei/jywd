package com.lenovocw.music.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lenovocw.music.model.Sales;




public interface ToBeShopManagerService {

	public List<Map<String, Object>> getType(String type);

	public List<Map<String, Object>> getTc(String name);

	public Map<String, Object> giveGoldByTcName(String tcid,String AGENTNO,String phone,String type,String handelState,String handelMsg);
	
	public Map<String, Object> giveMsmRecommend(String Name, String AGENTNO,String phone, String tjid, String handelState,String handelMsg);

	public Map<String, Object> access(String aGENTNO,String tcid);

	public List<Sales> mySales(String phone, String starTime, String endTime);

	public Map<String, Object> MsmRecommend(String bephone, String tcid, String aGENTNO, String sendphone);
	
	public void UpdateMsmRecommend();

	public Map<String, Object> queryUser(String phone) throws Exception;

	public List<Map<String, Object>> mysalesDetail(String name, String aGENTNO);


}
