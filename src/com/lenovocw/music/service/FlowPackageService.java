package com.lenovocw.music.service;

import java.util.List;
import java.util.Map;





/**
 * 
 * 流量叠加包
 * @author zhaofl
 *
 */
public interface FlowPackageService {

	/**
	 * 功能：获取所有流量叠加包
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getAllPackages() throws Exception;
	
	
	public Map<String, Object> queryPackageById(String packageId)throws Exception;

	/**
	 * 购买流量叠加包
	 * @param packageId 包编号
	 * @param phone 登录用户手机号
	 * @return
	 */
	public Map<String, Object> buyFreePackage(String packageId, String phone)throws Exception;




}
