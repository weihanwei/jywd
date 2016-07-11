package com.lenovocw.music.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.BroadbandXfService;
import com.lenovocw.utils.UUIDUtil;
/**
 * 
 * jywd
 * @author heshiqing
 * 
 * copyright:Copyright@2013 和跃科技有限公司
 * 2015-12-1
 */


@Transactional
@Service(value = "BroadbandXfServiceImpl")
public class BroadbandXfServiceImpl implements BroadbandXfService {
	@Resource(name = "jdbcDao")
	private JdbcDao dao;

	@Override
	public int saveBroadband(String Broadband, String username, String prodid,
			String prodname, String mark, String Bandwidth, String Cost,
			String ProductType, String orderid,String AGENTNO,String type) {
		// TODO Auto-generated method stub
		UUIDUtil uuid=new UUIDUtil();
		@SuppressWarnings("static-access")
		String id=uuid.getUUID();
		String sql="insert into sys_jy_broadband(id, broadbandaccount,username,orderid,productid,productname,bandwidth,remark,"
					+" ngresult,payresult,refreshtime,kdcost,producttype,AGENTNOORPHONE,TYPE)values('"+id+"','"+Broadband+"','"+username+"','"+orderid+"'," +
							"'"+prodid+"','"+prodname+"','"+Bandwidth+"','"+mark+"','费用计算成功','待支付',sysdate,'"+Cost+"','"+ProductType+"','"+AGENTNO+"','"+type+"')";
		int i=dao.execute(sql);
		System.out.println(sql);
		return i;
	}
	
	
}
