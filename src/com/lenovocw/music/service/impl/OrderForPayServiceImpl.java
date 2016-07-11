package com.lenovocw.music.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.LogService;
import com.lenovocw.music.service.OrderForPayService;

@Transactional
@Service(value = "OrderForPayServiceImpl")
public class OrderForPayServiceImpl implements OrderForPayService{
	@Resource(name = "jdbcDao")
	private JdbcDao dao;
	
	@Resource
	private LogService logService;
	
	@Override
	public Map<String, Object> broadbandinfo(String broadbandid, String orderid) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		String sql="select productname,bandwidth,kdcost,payresult from sys_jy_broadband where  orderid='"+orderid+"' and broadbandaccount='"+broadbandid+"'";
		map=dao.queryForMap(sql);
		return map;
	}

	@Override
	public int updateBroadband(String broadbandid, String orderid) {
		// TODO Auto-generated method stub
		String sql="update sys_jy_broadband set payresult='支付成功' where  orderid='"+orderid+"' and broadbandaccount='"+broadbandid+"'";
		int i=dao.execute(sql);
		return i;
	}
/**
 * 金币赠送
 */
	@Override
	public void LargessJB(String broadbandid, String orderid) {
		// TODO Auto-generated method stub
		String sqlordersql="select TYPE,AGENTNOORPHONE,ISLARGESS  from  sys_jy_broadband WHERE BROADBANDACCOUNT='"+broadbandid+"' AND ORDERID='"+broadbandid+"'";
		String updateorder="update sys_jy_broadband set ISLARGESS='1' where  orderid='"+orderid+"' and broadbandaccount='"+broadbandid+"'";
		//获取订单信息
		Map<String, Object> mapinfo=dao.queryForMap(sqlordersql);
		if(mapinfo.size()>0){
			String sqltcinfo="select ID,TYPE,NAME,SENDGOLD,BEGOLD from SYS_TOBESHOPMANAGER_TC where  INSTRUCTION='KDXF'";
			Map<String, Object> maptc=dao.queryForMap(sqltcinfo);
			if(mapinfo.get("TYPE").equals("推荐办理")){
				
				//插入推荐访问记录
				String sqlltcid="insert into SYS_TOBESHOPMANAGER_access(id,tcid,Agentno,Time)values(TOBESHOPMANAGER_a_sequence.nextval,'"+maptc.get("ID")+"','"+mapinfo.get("AGENTNOORPHONE")+"',sysdate)";
				dao.execute(sqlltcid);
				//插入推荐办理量
				String tobehandle="insert into sys_tobeshopmanager_recommend(id,name,agentno,time,ishandle,handletime,BEPHONE,type,SENDGOLD)values(TOBESHOPMANAGER_R_sequence.nextval,'"+maptc.get("NAME")+"','"+mapinfo.get("AGENTNOORPHONE")+"',sysdate,'1',sysdate,'','"+mapinfo.get("TYPE")+"','"+maptc.get("SENDGOLD")+"')";
                dao.execute(tobehandle);
				//获取推荐人金币信息
                String usermsg="select totalgold,remaininggold from sys_customer_msg where agentno='"+mapinfo.get("AGENTNOORPHONE")+"'";
				
				Map<String, Object> map1=dao.queryForMap(usermsg);
				int totalgold=Integer.valueOf(map1.get("totalgold").toString())+Integer.valueOf(maptc.get("SENDGOLD").toString());
				int remaininggold=Integer.valueOf(map1.get("remaininggold").toString())+Integer.valueOf(maptc.get("SENDGOLD").toString());
                
                //赠送推荐人金币
                String updatesql="update sys_customer_msg set totalgold='"+totalgold+"',remaininggold='"+remaininggold+"' where agentno='"+mapinfo.get("AGENTNOORPHONE")+"'";
				dao.execute(updatesql);
			}else{
				if(!mapinfo.get("AGENTNOORPHONE").equals("")||!mapinfo.get("AGENTNOORPHONE").equals(null)){
					//获取推荐人金币信息
	                String usermsg="select totalgold,remaininggold from sys_customer_msg where agentno='"+mapinfo.get("AGENTNOORPHONE")+"'";
					
					Map<String, Object> map1=dao.queryForMap(usermsg);
					int totalgold=Integer.valueOf(map1.get("totalgold").toString())+Integer.valueOf(maptc.get("BEGOLD").toString());
					int remaininggold=Integer.valueOf(map1.get("remaininggold").toString())+Integer.valueOf(maptc.get("BEGOLD").toString());
	                
	                //赠送推荐人金币
	                String updatesql="update sys_customer_msg set totalgold='"+totalgold+"',remaininggold='"+remaininggold+"' where phone='"+mapinfo.get("AGENTNOORPHONE")+"'";
	                dao.execute(updatesql);
	                logService.saveHandleLog("宽带续费",mapinfo.get("AGENTNOORPHONE").toString(),"家庭宽带", "1","宽带续费赠送金币成功");				}
			}
			
		}
		//更多赠送金币状态
		dao.execute(updateorder);
		
	}
   
}
