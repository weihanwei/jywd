package com.lenovocw.music.service.impl;



import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.LogService;
import com.lenovocw.music.service.LoginService;
import com.lenovocw.music.util.DESPlus;
import com.lenovocw.music.util.HttpUtilsNew;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.ExoCoder;
import com.lenovocw.utils.MD5;
import com.lenovocw.utils.MD5More;
import com.lenovocw.utils.PropertyUtil;
import com.lenovocw.utils.RandomNumUtil;

/**
 * 
 * jywd
 * @author zhangzhigao
 * 
 * copyright:Copyright@2013 代码工作室
 * 2015-9-7
 */

@Transactional
@Service(value = "LoginServiceImpl")
public class LoginServiceImpl implements LoginService{
	@Resource(name = "jdbcDao")
	private JdbcDao dao;

	@Resource
	private LogService logService;

	/**
	 * 登录
	 */
	public Map<String, Object> login(String phone,String password,String type,HttpServletRequest request){
		
		Map<String, Object> msg=new HashMap<String, Object>();
		
		//1.服务密码登录
		if(type.equals("0")){
			
			try{
				
				//接口秘钥
				String secretkey =PropertyUtil.getKey("query.secretKey");
				
				//开发商编号
				String 	developercode =PropertyUtil.getKey("query.developerCode");
				
				//接口服务的授权帐号
				String 	account =PropertyUtil.getKey("queryInterface.account");
				
				//接口服务的授权密码
				String 	interfacepassword =PropertyUtil.getKey("queryInterface.password");
				
				
				
				Date date=new Date();
				String dateStr="";
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
				dateStr=dateFormat.format(date);
				//String token=dateStr+"_foXjqc79_do1";//当前日期_接口密钥_开发商编号
				//String token=dateStr+"_foXjqc79_do1";//当前日期_接口密钥_开发商编号
				String token=dateStr+"_"+secretkey+"_"+developercode;
				token=MD5.getMD5Str(token);
				String url = PropertyUtil.getKey("moreChannel")+//
					     "eid="+developercode+"&sid=CcchkauthcheckService&token="+//
					      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
				
				String miyao="gmcc2011";//密钥
				
				HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
				TreeMap<String, String> params=new TreeMap<String, String>();
/*				params.put("account", "AC000007");
				params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
				params.put("account", account);
				params.put("password", interfacepassword);
				
				params.put("custtype", "1");
				params.put("vertype", "AuthCheckB");
				params.put("mobile", phone);
				params.put("key", "pwd");
				DESPlus des = new DESPlus(miyao);//自定义密钥 
				String value=des.encrypt(password);
				params.put("value", value);
				
				String result=httpUtilsNew.httpPost(url, params).trim();
				JSONObject object=new JSONObject(result);
				
				String data=object.getString("data");
				String status=object.getString("status");
				
				data=Base64Utils.getFromBASE64(data);
				Document dom=DocumentHelper.parseText(data);
				Node retype=dom.selectSingleNode("do1/ccchkauthcheckrsp/msgrspheader/retinfo/retype");
				Node retmsg=dom.selectSingleNode("do1/ccchkauthcheckrsp/msgrspheader/retinfo/retmsg");
				
				msg.put("retype", retype.getText());
				msg.put("retmsg", retmsg.getText());
				
				//插入接口日志
				logService.interfacelog(developercode, "CcchkauthcheckService", phone,status,data);
			}catch (Exception e) {
				e.printStackTrace();
				msg.put("retype", "500");
				msg.put("retmsg", "登录失败!");
			}
			
	    //2.随机密码登录
		}else{
			
			try{
				
				String randomPassword=(String) request.getSession().getAttribute("randomPassword");
				
				if(randomPassword==null||"".equals(randomPassword)){
					
					msg.put("retype", "202");
					msg.put("retmsg", "动态密码超时！");
					
				}else if(randomPassword.equals(password)){  //成功
					
					
					if(type.equals("5")){//登录推荐操作
						String key=request.getParameter("key");
						String AGENTNO=ExoCoder.decrypt(request.getParameter("AGENTNO"),key);
						String dhhm=ExoCoder.decrypt(request.getParameter("dhhm"),key);
						String tcid=request.getParameter("tcid");
						String sqltcinfo="select NAME from SYS_TOBESHOPMANAGER_TC where  id='"+tcid+"'";
						Map<String, Object> maptc=dao.queryForMap(sqltcinfo);
						System.out.println(sqltcinfo);
						
						//插入推荐访问记录
						String sqlltcid="insert into SYS_TOBESHOPMANAGER_access(id,tcid,Agentno,Time)values(TOBESHOPMANAGER_a_sequence.nextval,'"+tcid+"','"+AGENTNO+"',sysdate)";
						dao.execute(sqlltcid);
						System.out.println(sqlltcid);
					//判断用户是否已经登录过
				    String isloginsql="select * from SYS_CUSTOMER_MSG where phone='"+phone+"'";
				    List<Map<String, Object>> isloginmap=dao.queryForListMap(isloginsql);
					if(isloginmap.size()==0){
					 //判断被推荐人是否已经推荐登录过
					String sql="select * from sys_tobe_login where tophone='"+phone+"'";
					List<Map<String, Object>> isbelogin=dao.queryForListMap(sql);
					System.out.println(sql);
					if(isbelogin.size()==0){//用户未被推荐过
						if(!phone.equals(dhhm)){//店长本人不在推荐范围内
							String insertsql="insert into sys_tobe_login(id,agentno,phone,tophone,tologintime)values(SYS_TOBE_LOGIN_SEQ.nextval,'"+AGENTNO+"','"+dhhm+"','"+phone+"',sysdate)";
							dao.execute(insertsql);
							System.out.println(insertsql);
							
							System.out.println(maptc.get("NAME"));
							//插入推荐办理量
							String tobehandle="insert into sys_tobeshopmanager_recommend(id,name,agentno,time,ishandle,handletime,BEPHONE,type,SENDGOLD)values(TOBESHOPMANAGER_R_sequence.nextval,'"+maptc.get("NAME")+"','"+AGENTNO+"',sysdate,'1',sysdate,'"+phone+"','登录推荐','0')";
		                    dao.execute(tobehandle);
		                    System.out.println(tobehandle);
							//判断推荐用户是否达到5人，五人以上赠送积分
							String tsql="select * from sys_tobe_login where agentno='"+AGENTNO+"'";
							List<Map<String, Object>> totleloginuser=dao.queryForListMap(tsql);
							System.out.println(totleloginuser.size());
							if(totleloginuser.size()==5){//达到赠送积分，积分赠送规则
								String usermsg="select totalgold,remaininggold from sys_customer_msg where agentno='"+AGENTNO+"'";
								Map<String, Object> map1=dao.queryForMap(usermsg);
								int totalgold=Integer.valueOf(map1.get("totalgold").toString())+5;
								int remaininggold=Integer.valueOf(map1.get("remaininggold").toString())+5;
								String updatesql="update sys_customer_msg set totalgold='"+totalgold+"',remaininggold='"+remaininggold+"' where agentno='"+AGENTNO+"'";
								dao.execute(updatesql);
								String insertsqllg="insert into sys_give_gold_log(id,phone,cause,time,num)values(sys_give_gold_log_sequence.nextval,'"+AGENTNO+"','推荐5人登录微店赠送金币',sysdate,'5')";
							    dao.execute(insertsqllg);
							    //更新推荐金币数
							    String updaterecommend="update sys_tobeshopmanager_recommend set SENDGOLD='5' where agentno='"+AGENTNO+"' and BEPHONE='"+phone+"'";
							dao.execute(updaterecommend);
							}
						 
						}
						
					}
					}
					}
					msg.put("retype", "0");
					msg.put("retmsg", "成功！");
				}else{//失败
					
					msg.put("retype", "201");
					msg.put("retmsg", "动态密码不正确！");
					
				}
			
			}catch (Exception e) {
				e.printStackTrace();
				msg.put("retype", "500");
				msg.put("retmsg", "登录失败!");
			}
			
		}
		
		//3.记录登录日志
		String ip=request.getRemoteAddr();
		this.insertLoginLog(phone, msg.get("retype").toString(), msg.get("retmsg").toString(),ip,"jyydwd");
		
		//4.创建或更新用户信息
	    this.saveOrUpdateCustomer(phone);
		
		return msg;
		
	}
	
	/**
	 * 获取动态密码
	 */
    public Map<String, Object> getRandomPassword(String phone) throws Exception{
		
    	RandomNumUtil rnum=RandomNumUtil.Instance();
		String randomPassword=rnum.getString();
		
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
			     "eid="+developercode+"&sid=channelDownmsgService&token="+//
			      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
		
		HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
		TreeMap<String, String> params=new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		params.put("account", account);
		params.put("password", password);
		params.put("mobile", phone);
		params.put("type", "jywd_idcode");
		params.put("level", "1");
		params.put("businessType", "微店验证码");
		
		String context="您的随机密码为："+randomPassword+"，请勿告诉他人。";
		context=URLEncoder.encode(context,"UTF-8");
		
		dateStr = String.valueOf(date.getTime());
		
		String sign="account="+account+"&password="+password+
				    "&toChannel=1&port=1008601288"+
				    "&context="+context+"&mobile="+
				    phone+"&timestamp="+dateStr;
		
		sign=MD5More.getMD5Str(sign);
		
		String p="toChannel=1-port=1008601288-context="+
		         context+"-mobile="+phone+"-timestamp="+
		         dateStr+"-sign="+sign;
		
		params.put("params", p);
		
		String result=httpUtilsNew.httpPost(url,params).trim();
		JSONObject object=new JSONObject(result);
		
		String data=object.getString("data");
		String status=object.getString("status");
		
		data=Base64Utils.getFromBASE64(data);
		String temp[]=data.split("~");
		
		Map<String, Object> msg=new HashMap<String, Object>();
		msg.put("status", temp[0]);
		msg.put("randomPassword",randomPassword);
		
		//插入接口日志
		logService.interfacelog(developercode, "channelDownmsgService", phone,status,data);
		
		return msg;
		
	}
    
    /**
     * 插入登录日志
     * @param phone
     * @param state
     * @param ip 
     * @param msgn n
     * @return
     */
    public int insertLoginLog(String phone,String state,String msg, String ip,String channelCode){
    	
    	String sql="insert into SYS_CUSTOMER_LOGIN_LOG(ID,PHONE,STATE,MSG,TIME,IP,channelCode)"+//
    	           " VALUES(CUSTOMER_LOGIN_LOG_sequence.nextval,'"+phone+"'," +//
    	           "'"+state+"','"+msg+"',sysdate,'"+ip+"','"+channelCode+"')";
    	
		return dao.execute(sql);
    	
    }
    
    /**
     * 创建或更新用户信息
     * @param phone
     * @param state
     * @param msgn n
     * @return
     */
    public int saveOrUpdateCustomer(String phone){
    	
    	String conutSql="select (select count(1) from SYS_CUSTOMER_MSG where PHONE = '"+phone+"') as ISORNO,"+//
    		            "(select LOGINTIME from SYS_CUSTOMER_MSG where PHONE = '"+phone+"') as LOGINTIME" +
    		            " from dual";
    	
    	Map<String, Object> map=dao.queryForMap(conutSql);
    	
    	String saveOrUpdateSql="";
    	
    	int isOrNo=Integer.parseInt(map.get("ISORNO").toString());
    	
    	if(isOrNo>0){
    	
    		
    		int loginTime=Integer.parseInt(map.get("LOGINTIME").toString());
    		
    		loginTime=loginTime+1;
    		
    		saveOrUpdateSql="update SYS_CUSTOMER_MSG set LOGINTIME="+loginTime+//
    				        " where PHONE='"+phone+"'";
    		
    	}else{
    		
    		saveOrUpdateSql="insert into SYS_CUSTOMER_MSG(ID,TOTALGOLD,REMAININGGOLD,LOGINTIME,PHONE," +
			        "TIME,ISGUIDE,ISSHOPMANAGER) values(SYS_CUSTOMER_MSG_sequence.nextval" +//
	                ",0,0,1,'"+phone+"',sysdate,0,0)";
    		
    	}
    	
    	return dao.execute(saveOrUpdateSql);
    	
    }

    /**
     * 创建UUID,1.无则生成,2.有则判断时间差是否大于60秒，大于修改uuid,否则直接返回
     * @param phone
     * @param state
     * @param msgn n
     * @return
     */
	@Override
	public Map<String, Object> getUUID(String phone) throws Exception {
		
		String sql="select uuid,TIME as TIME from SYS_CUSTOMER_uuid where phone='"+phone+"'";
		
		Map<String, Object> map=dao.queryForMap(sql);
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(map.size()>0){//1.有就行判断
			
			Date startDate=dateFormat.parse(map.get("TIME").toString());
			
			Date endDate=new Date();
			
			long temp=(endDate.getTime()-startDate.getTime())/1000;
	
			if(temp>60*5){//1.1判断时间差
				
				String uuid=UUID.randomUUID().toString();
				
				sql="update SYS_CUSTOMER_uuid set uuid='"+uuid+"',time='"+dateFormat.format(endDate)+"' where phone='"+phone+"'";
				
				dao.execute(sql);
				
				map.put("UUID", uuid);
				map.put("TIME", dateFormat.format(endDate));
			}
			
		}else{//2.无生成
			
			String uuid=UUID.randomUUID().toString();
			
			Date startDate=new Date();
			
			sql="insert into SYS_CUSTOMER_uuid(id,uuid,phone,time) values(SYS_CUSTOMER_uuid_sequence.nextval" +
					",'"+uuid+"','"+phone+"','"+dateFormat.format(startDate)+"')";
			
			dao.execute(sql);
			
			map.put("UUID", uuid);
			map.put("TIME", dateFormat.format(startDate));
		}
		
		return map;
	}

	public Map<String, Object> pointLogin(String channelCode, String data,HttpServletRequest request){

		
		Map<String, Object> msg=new HashMap<String, Object>();
		
	    Object object=request.getSession().getAttribute("phone");
		
		if(object==null){
			
			String sql="select KEY from SYS_CHANNEL_CODE where CODE='"+channelCode+"'";
			
			Map<String, Object> map=dao.queryForMap(sql);
			
			if(map.size()>0){
				
				String key=map.get("KEY").toString();
				
				try {
					//解密
		    		 DESPlus des = new DESPlus(key);
		    		 
		    		 data = des.decrypt(data); //{phone:明文电话号码,time:时间}
		    		 
				} catch (Exception e) {

					e.printStackTrace();
				}
	    		
				String phone="";
				
				try {
					
					 JSONObject jsonObject = new JSONObject(data);
					 phone=jsonObject.getString("phone");
					 
				} catch (JSONException e) {
					
					msg.put("state", "2");
					msg.put("msg", "参数格式不正确");
					
					e.printStackTrace();
				}
	    		 
				if(this.isMobileNO(phone)){
					
					msg.put("state", "1");
					msg.put("msg", "登录成功");
					//插入日志
					
					this.saveOrUpdateCustomer(phone);
					
					request.getSession().setAttribute("phone", phone);
					
					String ip=request.getRemoteAddr();
					this.insertLoginLog(phone, "1", "单点登录成功！",ip,channelCode);
					
					
				}else{
					
					msg.put("state", "3");
					msg.put("msg", "号码错误");
					
				}
				
			}else{
				
				msg.put("state", "4");
				msg.put("msg", "渠道编码错误");
				
			}
			
			
		}else{
			
			msg.put("state", "1");
			msg.put("msg", "登录成功");
			
		}
		
		return msg;
		
	}
    
	 /**
     * 判断电话号码是否非法
     * @param mobiles
     * @return
     */
	  public  boolean isMobileNO(String mobiles) {
		
		  Pattern p = Pattern.compile("^1\\d{10}");
		  Matcher m = p.matcher(mobiles);
		 return m.matches();
		  }
    
}
