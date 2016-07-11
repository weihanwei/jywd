package com.lenovocw.music.service;




/**
 * 
 * 功能：用户购买叠加包Service
 * @author zhaofl
 *
 */
public interface UserBuyPackageService {

	/**
	 * 保存用户购买免费流量叠加包记录
	 * @param packageId 叠加包编码
	 * @param phone 用户手机号
	 * @return
	 * @throws Exception
	 */
	public int saveBuyPackageInfo(String packageId,String phone) throws Exception;
	
	
	/**
	 * 功能：用户是否购买过免费流量
	 * @param phone
	 * @return
	 */
	public boolean isUserBuyPackage(String phone);
	
	
	/**
	 * 功能：流量包剩余数
	 * @param packageId
	 * @return
	 */
	public int countPackagesById(String packageId);

}
