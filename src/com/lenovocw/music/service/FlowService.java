package com.lenovocw.music.service;

import java.util.List;
import java.util.Map;

import com.lenovocw.music.model.FlowTC;





public interface FlowService {

	/**
	 * 功能：根据红包数量与流量大小查询特定红包的价格，价格会根据当前是否为特价日显示特价价格
	 * @param number
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> changePrice(String number, String size) throws Exception;

	public Map<String, Object> buyRedEnvelope(String phone, String number,
			String size, String aGENTNO,String nickname,String openid) throws Exception;

	public List<Map<String, Object>> getSizesByNumber(String number) throws Exception;

	public List<Map<String, Object>> getFlowTc() throws Exception;

	public Map<String, Object> handleFlowTc(String mainTC, String giveTC,String phone, String aGENTNO) throws Exception;
	
	

	/**
	 * 功能：判断用户是否内部员工
	 * 详细描述：判断用户手机号是否在内部员工的群组内
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public boolean isStaffPhone(String phone) throws Exception;




}
