/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lenovocw.music.service;

import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.lenovocw.music.controller.entity.SysRedenvelopeOrders;



/**
 * REDENVELOPEDAO接口
 * @author 汉维
 * @version 2015-10-24
 */
public interface SysRedenvelopeOrdersService  {
	public Map<String,Object> checkRedpacakgeValid(String id);
	public Map<String,Object> getReceiveList(String Id);
	public Map<String,Object> getRedenvelopeInfo(String id) throws Exception;
	public Map<String, Object> getRedpackageById(String id) ;
	public Map<String,Object> checkReceiveWechatCode(String redId,String code)throws Exception;//验证微信号是否已经抢过红包
	public String shareRedenvelope(String id)throws Exception;//分析流量红包
	public Map<String,String> grabFlowRedenvelope(Map<String,String> values)throws Exception;//抢红包
	public SysRedenvelopeOrders get(String id);
	public List<SysRedenvelopeOrders> findBySql(String sql);
	public void save(SysRedenvelopeOrders t); 
	public void update(SysRedenvelopeOrders t);
	public String getRedpackageUseTime(String id)throws Exception;
	public Map<String,String>cashRedpackage(String id,String moblieNo,String honnerMobile)throws Exception;
	public List<Map<String,Object>>getMyshareRedpackage(String mobileNo);
	public List<Map<String,Object>>getMyReceviceRedpackage(String mobileNo);
	public int getFlowSizeById(String id);
	public void cashOutTimeRedpackage()throws Exception;
	public void countOutTimeRedpackage()throws Exception;
	public List<Map<String,Object>>getRedpackageRecevice(String mobileNo);
	public List<Map<String,Object>>getReceviceById(String id);
	public Map<String ,String> webchatOauth2(String code) throws JSONException;
	public void countOutTimeReceiveList();
	public  void sendMsmTOcashFlow()throws Exception;
	
	/**
	 * 功能:查询用户当月购买红包数量
	 * @param phone
	 * @return
	 */
	public int queryBuyRedPackCount(String phone);
}