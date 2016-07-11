package com.lenovocw.music.service;

import java.util.List;
import java.util.Map;





public interface Handle4GService {

	public List<Map<String, Object>> getTcByType(String type) throws Exception;

	public Map<String, Object> getTcByID(String id);

	public Map<String, Object> handle(String id,String phone, String aGENTNO) throws Exception;

	public Map<String, Object> get4GMSG(String phone) throws Exception;

	public Map<String, Object> recommend4G(String phone) throws Exception;



}
