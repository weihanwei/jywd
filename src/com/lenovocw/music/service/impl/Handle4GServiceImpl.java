package com.lenovocw.music.service.impl;



import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
import com.lenovocw.music.service.Handle4GService;
import com.lenovocw.music.service.LogService;
import com.lenovocw.music.service.PersonService;
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
@Service(value = "Handle4GService")
public class Handle4GServiceImpl implements Handle4GService{
	@Resource(name = "jdbcDao")
	private JdbcDao dao;
	
	@Resource
	private PersonService personService;
	
	@Resource
	private LogService logService;
	
	@Resource
	private ToBeShopManagerService toBeShopManagerService;

	@Override
	public List<Map<String, Object>> getTcByType(String type) throws Exception {
		String sql="select ID,TYPE,INSTRUCTION,PID,name from sys_4g_tc where type='"+type+"' order by PRICE ASC";
		List<Map<String, Object>> listMap=dao.queryForListMap(sql);
		return listMap;
	}

	@Override
	public Map<String, Object> getTcByID(String id) {
		
		String sql="select * from sys_4g_tc where id='"+id+"'";
		Map<String, Object> map=dao.queryForMap(sql);
		return map;
	}

	@Override
	public Map<String, Object> handle(String id,String phone, String aGENTNO) throws Exception {
		
		Map<String, Object> msg=new HashMap<String, Object>();
		
		//1。查询指令
		Map<String, Object> map=this.getTcByID(id);
		String context=(String) map.get("INSTRUCTION");
		context=URLEncoder.encode(context,"UTF-8");
		
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
			
			//接口服务的授权帐号
			String 	account =PropertyUtil.getKey("uploadInterface.account");
			
			//接口服务的授权密码
			String 	password =PropertyUtil.getKey("uploadInterface.password");
			
			//String token=dateStr+"_foXjqc79_do1";//当前日期_接口密钥_开发商编号
			String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号
			token=MD5.getMD5Str(token);
			String url =PropertyUtil.getKey("moreChannel")+//
				     "eid="+developercode+"&sid=channelUploadService&token="+//
				      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
			
			HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
			TreeMap<String, String> params=new TreeMap<String, String>();
			
			
/*			params.put("account", "AC000007");
			params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
			params.put("account", account);
			params.put("password", password);
			params.put("mobile", phone);
			params.put("businessType", "微店办理业务");
			
			dateStr = String.valueOf(date.getTime());
			
			String sign="account="+account+"&password="+password+
					    "&toChannel=3&port=106573457303&context="+context+"&mobile="+
					    phone+"&timestamp="+dateStr+"&bossChannel=bsacLocal&" +
					    		"bossPortal=VDJYbsac003";
			
			sign=MD5More.getMD5Str(sign);
			
			String p="toChannel=3-port=106573457303-context="+
			         context+"-mobile="+phone+"-timestamp="+
			         dateStr+"-bossChannel=bsacLocal-" +
					  "bossPortal=VDJYbsac003"+"-sign="+sign;
			
			params.put("params", p);
			
			String result=httpUtilsNew.httpPost(url,params).trim();
			JSONObject object=new JSONObject(result);
			
			String data=object.getString("data");
			String status=object.getString("status");
			
			data=Base64Utils.getFromBASE64(data);
			String[] datas=data.split("~");
			
			//4.插入接口日志
			logService.interfacelog(developercode, "channelUploadService", phone,status,data);
			
			//5.赠送
		
			toBeShopManagerService.giveGoldByTcName(map.get("NAME").toString(), aGENTNO, phone,map.get("TYPE").toString(),datas[0],datas[1]);
			
           //6.返回结果
			msg.put("state", datas[0]);
			msg.put("msg", datas[1]);
			
		} catch (Exception e) {
			
			msg.put("state","505");
			msg.put("msg", "办理失败！");
			
		}
		
		return msg;
	}

	@Override
	public Map<String, Object> get4GMSG(String phone) throws Exception {
		
		//1.查询客户级别
		Map<String, Object> msg=personService.getMSGByphone(phone);
		
		//2.查询4G信息
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
		
		if(datas[0].equals("0")){
			msg.put("pName","");
		}else{
			
			if(datas.length>1){
				
				msg.put("pName",datas[2]);
				
			}else{
				
				msg.put("pName","");

			}
			
		}
		msg.put("is4G", datas[0]);
		msg.put("phone", phone);
		msg.put("jibie", "lv1");
		
		//3.插入接口日志
		logService.interfacelog(developercode, "Check4GCardService", phone,status,data);
		
		return msg;
	}

	@Override
	public Map<String, Object> recommend4G(String phone) throws Exception {
		
		
		
		//接口秘钥
		String secretkey =PropertyUtil.getKey("query.secretKey");
		
		//开发商编号
		String 	developercode =PropertyUtil.getKey("query.developerCode");
		
		//接口服务的授权帐号
		String 	account =PropertyUtil.getKey("queryInterface.account");
		
		//接口服务的授权密码
		String 	password =PropertyUtil.getKey("queryInterface.password");
		
		//1.调用4G套餐推荐
		
		Map<String, Object> msg=new HashMap<String, Object>();
		
        Date date=new Date();
		
		String dateStr="";
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		
		dateStr=dateFormat.format(date);
		
		//String token=dateStr+"_foXjqc79_do1";//当前日期_接口密钥_开发商编号
		String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号  
		
		token=MD5.getMD5Str(token);
		String url =PropertyUtil.getKey("moreChannel")+//
			     "eid="+developercode+"&sid=WD4GPackageTJService&token="+//
			      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
		
		HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
		TreeMap<String, String> params=new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		params.put("account", account);
		params.put("password", password);
		
		params.put("mobile",phone);
		
		String result=httpUtilsNew.httpPost(url,params).trim();
		JSONObject object=new JSONObject(result);
		
		String data=object.getString("data");
		String status=object.getString("status");
		
		data=Base64Utils.getFromBASE64(data);
		
		String datas[]=data.split("~");
		msg.put("state", datas[0]);
		msg.put("msg", datas[1]);
		
		//2.插入接口日志
		logService.interfacelog(developercode, "Check4GCardService", phone,status,data);
		
		return msg;
	}
}
