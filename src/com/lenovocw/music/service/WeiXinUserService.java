package com.lenovocw.music.service;

import java.util.Map;


/**
 * 
 *功能：微信用户信息操作Service
 * @author zhaofl
 *
 */
public interface WeiXinUserService {

	/**
	 * 根据用户微信oppenId或者手机号搜索用户信息
	 * @param openId 微信编码
	 * @param phone  手机号
	 * @return
	 */
	public Map<String,Object> queryWeiXinUserInfo(String openId,String phone);
	
	/**
	 * 功能：查询用户微信用户信息是否存在
	 * @param openId
	 * @param phone
	 * @return
	 */
	public int countWeiXinUser(String openId,String phone);
	
	/**
	 * 功能：保存或修改用户微信绑定相关信息
	 * @param openId 微信编码
	 * @param phone  手机号
	 * @param nickname 用户昵称
	 * @param headimgurl 用户头像
	 */
	public void save(String openId,String phone,String nickname,String headimgurl);
	
	
	/**
	 * 功能：查询除本微信用户外是否有绑定信息
	 * @param openId
	 * @param phone
	 * @return
	 */
	public int countWeiXinUserExceptOpenId(String openId,String phone);


}
