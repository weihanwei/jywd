package com.lenovocw.music.service;

import java.util.Map;

public interface OrderForPayService {
   
   public Map<String, Object> broadbandinfo(String broadbandid,String orderid);
   
   public int updateBroadband(String broadbandid,String orderid);
   
   public void LargessJB(String broadbandid,String orderid);
	
}
