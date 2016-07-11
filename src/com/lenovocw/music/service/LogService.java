package com.lenovocw.music.service;




public interface LogService {

	public int saveOperation(String phone, String urlName, String url) throws Exception;

	public int down(String phone, String downAppName, String downAppId) throws Exception;
	
	public int interfacelog(String eid,String sid,String phone,String state,String msg) throws Exception;

	public int saveHandleLog(String NAME, String phone,String type,String state, String msg);

}
