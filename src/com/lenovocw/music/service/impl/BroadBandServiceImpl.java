package com.lenovocw.music.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.BroadBandService;
import com.lenovocw.utils.UUIDUtil;

/**
 * <span> <b>���ܣ�</b> </span><br />
 * <span> <!--��������һ��д��������-->
 *  
 *</span><br /><br />
 * <span> Author  XW </span><br /> 
 * <span> Create Time 2015-11-28 ����4:09:56  </span><br /> 
 * <span> Version 1.0.0 </span> <br />
 * <span> JDK version used 7.0 </span><br /> 
 */

@Service
public class BroadBandServiceImpl implements BroadBandService {
	
	@Resource(name = "jdbcDao")
	private JdbcDao dao;
	
	@Override
	public void saveBroadBand(String khname, String khdh, String khxq, String khxxdz, String khqy, String agentno,String phone) {
		String id = UUIDUtil.getUUID();
		String sql = "insert into SYS_BROADBAND_REGISTER(id,khname,khdh,khxq,khxxdz,khqy,agentno,phone)" +
				"values('"+id+"','"+khname+"','"+khdh+"','"+khxq+"','"+khxxdz+"','"+khqy+"','"+agentno+"','"+phone+"')";
		
		dao.execute(sql);
	}

}
