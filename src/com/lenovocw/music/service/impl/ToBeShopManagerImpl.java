package com.lenovocw.music.service.impl;



import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.model.Sales;
import com.lenovocw.music.service.LogService;
import com.lenovocw.music.service.ToBeShopManagerService;
import com.lenovocw.music.util.HttpUtilsNew;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.MD5;
import com.lenovocw.utils.MD5More;
import com.lenovocw.utils.PropertyUtil;

/**
 * 
 * jywd
 * @author zhangzhigao
 * 
 * copyright:Copyright@2013 代码工作室
 * 2015-9-7
 */
@Transactional
@Service
public class ToBeShopManagerImpl implements ToBeShopManagerService{
	@Resource(name = "jdbcDao")
	private JdbcDao dao;
	
	@Resource
	private LogService logService;

	/**
	 * 获取套餐类型
	 */
	@Override
	public List<Map<String, Object>> getType(String type) {

		String sql="select * from sys_tobeshopmanager_type where isshow='1' and PTYPE='"+type+"'";
      
		return dao.queryForListMap(sql);
	}

	/**
	 * 获取套餐
	 */
	@Override
	public List<Map<String, Object>> getTc(String name) {
		
		String sql="select sys_tobeshopmanager_type.icon,sys_tobeshopmanager_tc.name,sys_tobeshopmanager_tc.type,sys_tobeshopmanager_tc.id" +//
				   ",sys_tobeshopmanager_tc.TOURL,sys_tobeshopmanager_tc.INSTRUCTION,sys_tobeshopmanager_tc" +//
				   ".ISMSMRECOMMEND from sys_tobeshopmanager_tc,sys_tobeshopmanager_type where sys_tobeshopmanager_type" +//
				   ".name=sys_tobeshopmanager_tc.type and sys_tobeshopmanager_type.name='"+name+"' order by" +//
				   " sys_tobeshopmanager_tc.no asc";
		
		return dao.queryForListMap(sql);
	}


  /**
    * 按照套餐名称赠送金币
    */
	public Map<String, Object> giveGoldByTcName(String Name,String AGENTNO,String phone,String type,String handelState,String handelMsg) {
		
		
		Map<String, Object> tcAndUserMsg=null;
		
		Map<String, Object> msg=new HashMap<String, Object>();
		
		//1.查看是否办理成功
		if(handelState.equals("1")){
		
			//2.判断是否是在赠送范围内
			String tcCountSql="select count(1) from sys_tobeshopmanager_TC where Name ='"+Name+"'";
			
			int tcCount=dao.queryForInteger(tcCountSql);
			
			String sql="";
			
			if(tcCount>0){
				//3.在赠送范围内
				
				//3.1.判断是否存在推荐
				if(!"".equals(AGENTNO)&&!"null".equals(AGENTNO)){
		            //3.1。1存在推荐
					
					sql="select (select count(1) from SYS_CUSTOMER_MSG where AGENTNO = '"+AGENTNO+"') as count"+//
				            " from dual";
					
					tcAndUserMsg=dao.queryForMap(sql);
					
					int isOrNoDz=Integer.valueOf(tcAndUserMsg.get("COUNT").toString());
					
					//3.1。2.判断是否存在此店长，防止恶意攻击赠送
					if(isOrNoDz>0){
						
						Calendar calendar=Calendar.getInstance();
						
						calendar.set(Calendar.DATE, 1);
						
						calendar.set(Calendar.HOUR_OF_DAY, 0);
						
						calendar.set(Calendar.MINUTE, 0);
						
						calendar.set(Calendar.SECOND, 0);
						
						Date startDate=calendar.getTime();
						
						calendar.set(Calendar.MONTH, startDate.getMonth()+1);
						
						Date endDate=calendar.getTime();
						
						SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						
						sql="select (select count(1) from sys_tobeshopmanager_recommend where BEPHONE='"+phone+"' and ISHANDLE='1' and time>to_date('"+sd.format(startDate)+"','yyyy-mm-dd hh24:mi:ss')" +
							" and time<to_date('"+sd.format(endDate)+"','yyyy-mm-dd hh24:mi:ss') and type='"+type+"') as count,(select REWARDTIME from sys_tobeshopmanager_type where Name = '"+type+"')" +
							" as REWARDTIME,(select count(1) from sys_handle where type = '"+type+"' and phone='"+phone+"' and state='1' and time>to_date('"+sd.format(startDate)+"','yyyy-mm-dd hh24:mi:ss')" +//
							" and time<to_date('"+sd.format(endDate)+"','yyyy-mm-dd hh24:mi:ss')) as handlecount from dual";
						
						tcAndUserMsg=dao.queryForMap(sql);
						
						int giveTime=Integer.valueOf(tcAndUserMsg.get("COUNT").toString());
						
						int REWARDTIME=Integer.valueOf(tcAndUserMsg.get("REWARDTIME").toString());
						
						int HANDLECOUNT=Integer.valueOf(tcAndUserMsg.get("HANDLECOUNT").toString());
						
						
						
						//3.1。3.判断是否超过赠送次数
						if(giveTime+HANDLECOUNT<REWARDTIME){
							
							sql="select (select SENDGOLD from sys_tobeshopmanager_tc where name='"+Name+"') as SENDGOLD," +
							    "(select phone from SYS_CUSTOMER_MSG where AGENTNO='"+AGENTNO+"') as SENDPHONE from dual";
							
							tcAndUserMsg=dao.queryForMap(sql);
							
							int SENDGOLD=Integer.valueOf(tcAndUserMsg.get("SENDGOLD").toString());
							
							String sendphone=tcAndUserMsg.get("SENDPHONE").toString();
					        
							//3.1。4.赠送给店长
							this.give(sendphone, "推荐"+phone+"办理"+Name, SENDGOLD);
							
							msg.put("state", "1");
							
							msg.put("msg", "赠送成功");
							
							//3.1。5.保存推荐日志
							sql="insert into SYS_TOBESHOPMANAGER_recommend(ID,NAME,AGENTNO,TIME,ISHANDLE,BEPHONE,type,ismsm,SENDGOLD)"+//
									" values(TOBESHOPMANAGER_R_sequence.nextval,'"+Name+"','"+AGENTNO+"',sysdate,'1','"+phone+"','"+type+"','0',"+SENDGOLD+")";
								
							dao.execute(sql);
							
						}else{
							
							msg.put("state", "505");
							
							msg.put("msg", "赠送次数已用完！");
							
						}
						
					}else{
						
						msg.put("state", "504");
						
						msg.put("msg", "赠送失败，不存在此店长");
						
					}
					
				
				}else{
					//3.2不存在推荐
					
					Calendar calendar=Calendar.getInstance();
					
					calendar.set(Calendar.DATE, 1);
					
					calendar.set(Calendar.HOUR_OF_DAY, 0);
					
					calendar.set(Calendar.MINUTE, 0);
					
					calendar.set(Calendar.SECOND, 0);
					
					Date startDate=calendar.getTime();
					
					calendar.set(Calendar.MONTH, startDate.getMonth()+1);
					
					Date endDate=calendar.getTime();
					
					SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					sql="select (select count(1) from sys_tobeshopmanager_recommend where BEPHONE='"+phone+"' and ISHANDLE='1' and time>to_date('"+sd.format(startDate)+"','yyyy-mm-dd hh24:mi:ss')" +
						" and time<to_date('"+sd.format(endDate)+"','yyyy-mm-dd hh24:mi:ss') and type='"+type+"') as count,(select REWARDTIME from sys_tobeshopmanager_type where Name = '"+type+"')" +
						" as REWARDTIME,(select count(1) from sys_handle where type = '"+type+"' and phone='"+phone+"' and state='1' and time>to_date('"+sd.format(startDate)+"','yyyy-mm-dd hh24:mi:ss')" +//
						" and time<to_date('"+sd.format(endDate)+"','yyyy-mm-dd hh24:mi:ss')) as handlecount,(select BEGOLD from sys_tobeshopmanager_TC where Name = '"+Name+"') as BEGOLD from dual";
					
					tcAndUserMsg=dao.queryForMap(sql);
			
					int giveTime=Integer.valueOf(tcAndUserMsg.get("COUNT").toString());
					
					int REWARDTIME=Integer.valueOf(tcAndUserMsg.get("REWARDTIME").toString());
					
					int HANDLECOUNT=Integer.valueOf(tcAndUserMsg.get("HANDLECOUNT").toString());
					
					int BEGOLD=Integer.valueOf(tcAndUserMsg.get("BEGOLD").toString());
					
					//3.2。1判断是否超过赠送次数
					if(giveTime+HANDLECOUNT<REWARDTIME){
						
						//2.2。2.赠送办理本人
						this.give(phone, "办理"+Name, BEGOLD);
						
						msg=new HashMap<String, Object>();
						
						msg.put("state", "1");
						
						msg.put("msg", "赠送成功！");
						
					}else{
						
						//3.2。3.不在赠送范围内
						msg=new HashMap<String, Object>();
						
						msg.put("state", "503");
						
						msg.put("msg", "赠送次数已用完！");
						
					}
					
				}
				
				
			}else{
				//4.不在赠送范围内
				msg=new HashMap<String, Object>();
				
				msg.put("state", "502");
				
				msg.put("msg", "不在赠送范围内！");
			}
			
		}else{
			
			//5.办理失败
			msg=new HashMap<String, Object>();
			
			msg.put("state", "501");
			
			msg.put("msg", "办理失败！");
			
		}
		
		logService.saveHandleLog(Name, phone, type, handelState, handelMsg);
		
		return msg;
	}


   /**
    * 赠送金币
    */
    public int[] give(String phone,String cause,int num){
		
		Map<String, Object>  msg=new HashMap<String, Object>();
		
    	String sql="select (select count(1) from SYS_CUSTOMER_MSG where PHONE = '"+phone+"') as ISORNO,"+//
	            "(select TOTALGOLD from SYS_CUSTOMER_MSG where PHONE = '"+phone+"') as TOTALGOLD," +//
	            "(select REMAININGGOLD from SYS_CUSTOMER_MSG where PHONE = '"+phone+"') as REMAININGGOLD" +//
	            " from dual";
    	
    	msg=dao.queryForMap(sql);
    	
    	int isOrNo=Integer.parseInt(msg.get("ISORNO").toString());
		
		if(isOrNo>0){
			
		    int TOTALGOLD=Integer.parseInt(msg.get("TOTALGOLD").toString());
			
			TOTALGOLD=TOTALGOLD+num;
			
			int REMAININGGOLD=Integer.parseInt(msg.get("REMAININGGOLD").toString());
			
			REMAININGGOLD=REMAININGGOLD+num;
			
			sql="update SYS_CUSTOMER_MSG set TOTALGOLD="+TOTALGOLD+",REMAININGGOLD="+REMAININGGOLD+" where PHONE='"+phone+"'";
			
		}else{
			
			sql="insert into SYS_CUSTOMER_MSG(ID,TOTALGOLD,REMAININGGOLD,LOGINTIME,PHONE," +//
			        "TIME,ISGUIDE,ISSHOPMANAGER) values(SYS_CUSTOMER_MSG_sequence.nextval" +//
	                ","+num+","+num+",1,'"+phone+"',sysdate,0,0)";
			
		}
		
		String giveLogSql="INSERT INTO SYS_GIVE_GOLD_LOG(id,phone,CAUSE,TIME,num) values(SYS_GIVE_GOLD_LOG_sequence.nextval" +//
				          ",'"+phone+"','"+cause+"',sysdate,"+num+")";
		
		String sqls[]=new String[2];
		
		sqls[0]=sql;
		
		sqls[1]=giveLogSql;
		
		return dao.executeBatch(sqls);
		
	}

    /**
     * 访问店主套餐
     */
	public Map<String, Object> access(String aGENTNO,String tcid) {
		
		String sql="insert into SYS_TOBESHOPMANAGER_access(id,tcid,AGENTNO,TIME) values(TOBESHOPMANAGER_a_sequence.nextval," +//
				   "'"+tcid+"','"+aGENTNO+"',sysdate)";
		
		int state=dao.execute(sql);
		
		Map<String, Object> msg=new HashMap<String, Object>();
		
		msg.put("state",state);
		
		return msg;
	}

    /**
     * 统计销量、访问量、办理量
     */
	@Override
	public List<Sales> mySales(String phone,String starTime, String endTime) {
		
		//1.用户信息
		String userSql="select  AGENTNO FROM  SYS_CUSTOMER_MSG where phone='"+phone+"'";
		
		Map<String, Object> user=dao.queryForMap(userSql);
		
		String AGENTNO=user.get("AGENTNO").toString();
		
		//2.所有推荐套餐
		String tcSql="select  NAME FROM  SYS_TOBESHOPMANAGER_TC where isshow='1'";
		
		List<Map<String, Object>> Tc=dao.queryForListMap(tcSql);
		
		String accessSql="select  SYS_TOBESHOPMANAGER_TC.NAME FROM  SYS_TOBESHOPMANAGER_access,SYS_TOBESHOPMANAGER_TC" +//
				         " where AGENTNO='"+AGENTNO+"' and SYS_TOBESHOPMANAGER_TC.id=SYS_TOBESHOPMANAGER_access.TCID";
		
		String handleSql="select  NAME,SENDGOLD FROM SYS_TOBESHOPMANAGER_RECOMMEND where AGENTNO='"+AGENTNO+"' and ISHANDLE='1'";
		
		if(!starTime.equals("")&&endTime.equals("")){
			
			accessSql=accessSql+" and SYS_TOBESHOPMANAGER_ACCESS.time>to_date('"+starTime+"','yyyy-mm-dd hh24:mi:ss')";
			handleSql=handleSql+" and SYS_TOBESHOPMANAGER_recommend.time>to_date('"+starTime+"','yyyy-mm-dd hh24:mi:ss')";
			
		}
		
		if(starTime.equals("")&&!endTime.equals("")){
			
			accessSql=accessSql+" and SYS_TOBESHOPMANAGER_ACCESS.time<to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')";
			handleSql=handleSql+" and SYS_TOBESHOPMANAGER_recommend.time<to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')";
			
		}
		
		if(!starTime.equals("")&&!endTime.equals("")){
			
			accessSql=accessSql+" and SYS_TOBESHOPMANAGER_ACCESS.time>to_date('"+starTime+"','yyyy-mm-dd hh24:mi:ss') and SYS_TOBESHOPMANAGER_ACCESS.time<to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')";
			handleSql=handleSql+" and SYS_TOBESHOPMANAGER_recommend.time>to_date('"+starTime+"','yyyy-mm-dd hh24:mi:ss') and SYS_TOBESHOPMANAGER_recommend.time<to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')";
			
		}
		
		//3.访问量
		List<Map<String, Object>> Access=dao.queryForListMap(accessSql);
		
		//4.办理量
		List<Map<String, Object>> Handle=dao.queryForListMap(handleSql);
		
		Sales sales=null;
		
		int gold=0;
		
		int access=0;
		
		int handle=0;
		
		List<Sales> saless=new ArrayList<Sales>();
		
		for(Map<String, Object> m:Tc){
			
			sales=new Sales();
			
			gold=0;
			
			access=0;
			
			handle=0;
			
			sales.setName(m.get("NAME").toString());
			
			for(Map<String, Object> a:Access){
				
				if(a.get("NAME").toString().equals(sales.getName())){
					
					access++;
					
				}
				
			}
			
			sales.setAccess(access);
			
           for(Map<String, Object> h:Handle){
				
				if(h.get("NAME").toString().equals(sales.getName())){
					
					gold=gold+Integer.valueOf(h.get("SENDGOLD").toString());
					
					handle++;
					
				}
				
			}
			
			sales.setGold(gold);
			
			sales.setHandle(handle);

			saless.add(sales);
			
		}
		
		
		return saless;
	}

	/**
	 * 短信推荐
	 */
	@Override
	public Map<String, Object> MsmRecommend(String bephone, String tcid,String aGENTNO,String sendphone) {
	
		String sql="select * from sys_tobeshopmanager_TC where id='"+tcid+"'";
		
		Map<String, Object> map=dao.queryForMap(sql);
		
		//2。调取上行接口
		try {
			
			Date date=new Date();
			
			String dateStr="";
			
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
			
			dateStr=dateFormat.format(date);
			
			
			//接口秘钥
			String secretkey =PropertyUtil.getKey("query.secretKey");
			
			//开发商编号
			String 	developercode =PropertyUtil.getKey("query.developerCode");
			
			//短信推荐的用户名密码
			//接口服务的授权帐号
			String 	account =PropertyUtil.getKey("uploadSmsInterface.account");
			
			//接口服务的授权密码
			String 	password =PropertyUtil.getKey("uploadSmsInterface.password");
			
			
			//String token=dateStr+"_foXjqc79_do1";//当前日期_接口密钥_开发商编号
			String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号  
			token=MD5.getMD5Str(token);
			String url =PropertyUtil.getKey("moreChannel")+//
				     "eid="+developercode+"&sid=channelUploadService&token="+//
				      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
			
			HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
			TreeMap<String, String> params=new TreeMap<String, String>();
/*			params.put("account", "AC000011");
			params.put("password", "4b892778-1aae-4d6b-ad61-56f3a4ff1153");*/
			params.put("account", account);
			params.put("password", password);
			params.put("mobile", sendphone);
			params.put("businessType", "微店办理业务");
			
			dateStr = String.valueOf(date.getTime());
			
			String context=(String) map.get("INSTRUCTION")+bephone;
			context=URLEncoder.encode(context,"UTF-8");
			
			String Name=(String) map.get("NAME").toString();
			
			String sign="account="+account+"&password="+password+
					    "&toChannel=1&port=1008601288&context="+context+"&mobile="+
					    sendphone+"&timestamp="+dateStr+"&bossChannel=bsacLocal&" +
					    		"bossPortal=VDJYbsac003";
			
			sign=MD5More.getMD5Str(sign);
			
			String p="toChannel=1-port=1008601288-context="+
			         context+"-mobile="+sendphone+"-timestamp="+
			         dateStr+"-bossChannel=bsacLocal-" +
					  "bossPortal=VDJYbsac003"+"-sign="+sign;
			
			params.put("params", p);
			
			String result=httpUtilsNew.httpPost(url,params).trim();
			JSONObject object=new JSONObject(result);
			
			String data=object.getString("data");
			String status=object.getString("status");
			
			data=Base64Utils.getFromBASE64(data);
			String[] datas=data.split("~");
			
			if(datas[0].equals("1")){
				
				int SENDGOLD=Integer.valueOf(map.get("SENDGOLD").toString());
				
				sql="insert into SYS_TOBESHOPMANAGER_recommend(ID,NAME,AGENTNO,TIME,ISHANDLE,BEPHONE,BATCH,type,ismsm,SENDGOLD) values(TOBESHOPMANAGER_R_sequence.nextval," +
					"'"+Name+"','"+aGENTNO+"',sysdate,'0','"+bephone+"','"+datas[1]+"','"+map.get("TYPE").toString()+"','1',"+SENDGOLD+")";
					
				dao.execute(sql);
				
			}
			
			map=new HashMap<String, Object>();
			
			map.put("state", datas[0]);
			map.put("msg","推荐成功！");
			
			
        } catch (Exception e) {
		    
        	map=new HashMap<String, Object>();
        	
			map.put("state", "500");
		    map.put("msg","推荐失败！");
			
		}
		
		return map;
	}

	/**
	 * 短信推荐自动更新状态
	 */
	@Override
	public void UpdateMsmRecommend() {
		

		
		String sql="select NAME,AGENTNO,BEPHONE,ID,BATCH from SYS_TOBESHOPMANAGER_recommend where ISHANDLE='0' and ismsm='1' and sysdate-7<time";
		
		List<Map<String, Object>> msg=dao.queryForListMap(sql);
		
		for(Map<String, Object> m:msg){
			
			try {
				
		        Date date=new Date();
				
				String dateStr="";
				
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
				
				dateStr=dateFormat.format(date);
				
				
				//接口秘钥
				String secretkey =PropertyUtil.getKey("query.secretKey");
				
				//开发商编号
				String 	developercode =PropertyUtil.getKey("query.developerCode");
				
				//接口服务的授权帐号 
				String 	account =PropertyUtil.getKey("queryInterface.account");
				
				//接口服务的授权密码
				String 	password =PropertyUtil.getKey("queryInterface.password");
				
				
				
				//String token=dateStr+"_foXjqc79_do1";//当前日期_接口密钥_开发商编号
				String token=dateStr+"_"+secretkey+"_"+developercode;
				
				token=MD5.getMD5Str(token);
				String url =PropertyUtil.getKey("moreChannel")+//
					     "eid="+developercode+"&sid=WDactivityService&token="+//
					      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
				
				HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
				TreeMap<String, String> params=new TreeMap<String, String>();
		/*		params.put("account", "AC000007");
				params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
				
				params.put("account", account);
				params.put("password", password);
				params.put("mobile", m.get("BEPHONE").toString());
				params.put("id", m.get("BATCH").toString());
				
				String result=httpUtilsNew.httpPost(url, params).trim();
				JSONObject object=new JSONObject(result);
				
				String data=object.getString("data");
				String status=object.getString("status");
				
				data=Base64Utils.getFromBASE64(data);
				
				String[] datas=data.split("~");
				
				if(datas[0].equals("1")){
					
					if(datas[4].equals("1")){
						
						this.giveMsmRecommend(m.get("NAME").toString(), m.get("AGENTNO").toString(),m.get("BEPHONE").toString(),m.get("ID").toString(),datas[4],"办理成功！");
						
					}
					
				}
				
				
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}
	
	}
	
	/**
	 * 短信推荐赠送
	 */
	@Override
	public Map<String, Object> giveMsmRecommend(String Name,String AGENTNO,String phone, String tjid,String handelState,String handelMsg) {
		
		String sql="select (select SENDGOLD from sys_tobeshopmanager_TC where Name = '"+Name+"') as SENDGOLD," +//
	            "(select PHONE from SYS_CUSTOMER_MSG where AGENTNO = '"+AGENTNO+"') as SENDPHONE,"+//
	            "(select type from sys_tobeshopmanager_TC where Name = '"+Name+"') as type from dual";
		
		Map<String, Object> tcAndUserMsg=dao.queryForMap(sql);
		
		Map<String, Object> giveTime=null;
		
		Map<String, Object> msg=new HashMap<String, Object>();

			
		Calendar calendar=Calendar.getInstance();
		
		calendar.set(Calendar.DATE, 1);
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		
		calendar.set(Calendar.MINUTE, 0);
		
		calendar.set(Calendar.SECOND, 0);
		
		Date startDate=calendar.getTime();
		
		calendar.set(Calendar.MONTH, startDate.getMonth()+1);
		
		Date endDate=calendar.getTime();
		
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String type=tcAndUserMsg.get("TYPE").toString();
		
		sql="select (select count(1) from sys_tobeshopmanager_recommend where BEPHONE='"+phone+"' and ISHANDLE='1' and time>to_date('"+sd.format(startDate)+"','yyyy-mm-dd hh24:mi:ss')" +
				" and time<to_date('"+sd.format(endDate)+"','yyyy-mm-dd hh24:mi:ss') and type='"+type+"') as count,(select REWARDTIME from sys_tobeshopmanager_type where Name = '"+type+"')" +
				" as REWARDTIME,(select count(1) from sys_handle where type = '"+type+"' and phone='"+phone+"' and state='1' and time>to_date('"+sd.format(startDate)+"','yyyy-mm-dd hh24:mi:ss')" +//
				" and time<to_date('"+sd.format(endDate)+"','yyyy-mm-dd hh24:mi:ss')) as handlecount from dual";

		giveTime=dao.queryForMap(sql);
		
		int recommendTime=Integer.valueOf(tcAndUserMsg.get("COUNT").toString());
		
		int REWARDTIME=Integer.valueOf(tcAndUserMsg.get("REWARDTIME").toString());
		
		int HANDLECOUNT=Integer.valueOf(tcAndUserMsg.get("HANDLECOUNT").toString());
		
		//1.判断是否赠送次数已用完
		if(recommendTime+HANDLECOUNT<REWARDTIME){
		
			int SENDGOLD=Integer.valueOf(tcAndUserMsg.get("SENDGOLD").toString());
			
			String sendphone=tcAndUserMsg.get("SENDPHONE").toString();
	
			this.give(sendphone, "推荐"+phone+"办理"+Name, SENDGOLD);
			
			msg.put("state", "1");
			
			msg.put("msg", "赠送成功");
			
			sql="update SYS_TOBESHOPMANAGER_recommend set ISHANDLE='1' where id='"+tjid+"'";
		
		}else{
			
			msg.put("state", "0");
			
			msg.put("msg", "赠送次数已用完");
			
			sql="update SYS_TOBESHOPMANAGER_recommend set ISHANDLE='3' where id='"+tjid+"'";
		}
		
		
		dao.execute(sql);
	
		logService.saveHandleLog(Name, phone, type, handelState, handelMsg);
		
		return msg;
	}
	
	@Override
	public Map<String, Object> queryUser(String phone) throws Exception {
		
		Map<String, Object> temp=new HashMap<String, Object>();
		
		Map<String, Object> msg=new HashMap<String, Object>();
		
		msg.put("phone", phone);
		
		//1.查询4G信息
		Date date=new Date();
		
		String dateStr="";
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		
		dateStr=dateFormat.format(date);
		
		
		//接口秘钥
		String secretkey =PropertyUtil.getKey("query.secretKey");
		
		//开发商编号
		String 	developercode =PropertyUtil.getKey("query.developerCode");
		
		//接口服务的授权帐号
		String 	account =PropertyUtil.getKey("queryInterface.account");
		
		//接口服务的授权密码
		String 	password =PropertyUtil.getKey("queryInterface.password");
		
		//String token=dateStr+"_foXjqc79_do1";//当前日期_接口密钥_开发商编号
		
		String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号  
		token=MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel")+//
			     "eid="+developercode+"&sid=Check4GCardService&token="+//
			      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
		
		HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
		TreeMap<String, String> params=new TreeMap<String, String>();
		params=new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		
		params.put("account", account);
		params.put("password", password);
		params.put("mobile", phone);
		
		String result=httpUtilsNew.httpPost(url, params).trim();
		JSONObject object=new JSONObject(result);
		
		String data=object.getString("data");
		String status=object.getString("status");
		
		data=Base64Utils.getFromBASE64(data);
		String datas[]=data.split("~");
	
		msg.put("is4G", datas[0]);
		
		//2.4G套餐推荐
        date=new Date();
		
		dateStr="";
		
		dateFormat=new SimpleDateFormat("yyyyMMdd");
		
		dateStr=dateFormat.format(date);
		
		//token=dateStr+"_foXjqc79_do1";//当前日期_接口密钥_开发商编号
		
		token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号
		
		token=MD5.getMD5Str(token);
		url =PropertyUtil.getKey("moreChannel")+//
			     "eid="+developercode+"&sid=WD4GPackageTJService&token="+//
			      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
		
		httpUtilsNew=new HttpUtilsNew();
		params=new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		
		params.put("account", account);
		params.put("password", password);
		params.put("mobile",phone);
		
		result=httpUtilsNew.httpPost(url,params).trim();
		object=new JSONObject(result);
		
		data=object.getString("data");
		status=object.getString("status");
		
		data=Base64Utils.getFromBASE64(data);
		
		datas=data.split("~");
		temp=new HashMap<String, Object>();
		temp.put("state", datas[0]);
		temp.put("msg", datas[4]);
		
		msg.put("tj4g",temp);
		
		return msg;
	}

	@Override
	public List<Map<String,Object>> mysalesDetail(String name, String aGENTNO) {
		
		String sql="select to_char(SYS_TOBESHOPMANAGER_RECOMMEND.TIME,'yyyy-mm-dd hh24:mi:ss') as time,name,SENDGOLD,BEPHONE" +
				   " from SYS_TOBESHOPMANAGER_RECOMMEND where name='"+name+"' and AGENTNO='"+aGENTNO+"'" +
				   " and SYS_TOBESHOPMANAGER_RECOMMEND.ISHANDLE='1'";
		
		return dao.queryForListMap(sql);
	}

}


