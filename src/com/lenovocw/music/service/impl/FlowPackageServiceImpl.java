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
import com.lenovocw.music.service.FlowPackageService;
import com.lenovocw.music.service.Handle4GService;
import com.lenovocw.music.service.LogService;
import com.lenovocw.music.service.UserBuyPackageService;
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
@Service(value = "FlowPackageService")
public class FlowPackageServiceImpl implements FlowPackageService{
	
	
	
	
	@Resource(name = "jdbcDao")
	private JdbcDao dao;
	
	@Resource
	private LogService logService;
	
	@Resource
    private  UserBuyPackageService userBuyPackageService;
	
	
	@Resource
    private  Handle4GService handle4GService;
	
	
	
	



	@Override
	public List<Map<String, Object>> getAllPackages() throws Exception {
		String sql="select a.id,a.pname,a.PIntroduction,a.price,(a.pcount - (select count(1) from user_RECEIVE_flowpackage b where b.FLOWPACKAGEID = a.id)) as rcount from sys_flow_package a order by a.price asc";
		List<Map<String, Object>> listMap=dao.queryForListMap(sql);
		return listMap;
	}





	@Override
	public Map<String, Object> buyFreePackage(String packageId, String phone) {

		
		Map<String, Object> msg=new HashMap<String, Object>();
		
		
		
		//查询用户是否已经办理过免费购买套餐
		boolean buyflag = this.userBuyPackageService.isUserBuyPackage(phone);
		if(buyflag)
		{
			msg.put("state", "505");
			msg.put("msg", "尊敬的客户,你已参与过首单免费活动!");
			return msg;
		}
		
		//查询套餐是否有剩余
		int remainPackageCount = this.userBuyPackageService.countPackagesById(packageId);
		if(remainPackageCount<1)
		{
			msg.put("state", "505");
			msg.put("msg", "尊敬的客户,你来晚了,该叠加包已被抢购完,请选择购买其他叠加包!");
			return msg;
		}
		
		//接口秘钥
		String secretkey =PropertyUtil.getKey("query.secretKey");
		
		//开发商编号
		String 	developercode =PropertyUtil.getKey("query.developerCode");
		
		//接口服务的授权帐号
		String 	account =PropertyUtil.getKey("uploadInterface.account");
		
		//接口服务的授权密码
		String 	password =PropertyUtil.getKey("uploadInterface.password");
		
		if ("".equals(secretkey) || "".equals(developercode)
				|| "".equals(account) || "".equals(password)) {
			
			msg.put("state", "506");
			msg.put("msg", "网络异常,请重试!");
			return msg;

		}
		try {
			//1。查询指令
			Map<String, Object> map=this.queryPackageById(packageId);
			String context=(String) map.get("CODE");
			context=URLEncoder.encode(context,"UTF-8");
			
			//2。调取上行接口
			
			Date date=new Date();
			
			String dateStr="";
			
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
			
			dateStr=dateFormat.format(date);
			
			
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
			
			
           //6.返回结果
			
			String state =datas[0];
			String msgStr =datas[1];
			//返回成功
			if("1".equals(state))
			{
				this.userBuyPackageService.saveBuyPackageInfo(packageId, phone);
			}
			logService.saveHandleLog("首单免费", phone, "首单免费", state, (String) map.get("PINTRODUCTION")+"-"+msgStr);
			
			msg.put("state", state);
			msg.put("msg", msgStr);
			
		} catch (Exception e) {
			
			msg.put("state","505");
			msg.put("msg", "尊敬的客户：叠加包业务办理失败,请稍后再试!");
			
			
		}
		
		return msg;
	
	}





	@Override
	public Map<String, Object> queryPackageById(String packageId)
			throws Exception {
		String sql="select a.id,a.pname,a.PIntroduction,a.CODE,a.price,(a.pcount - (select count(1) from user_RECEIVE_flowpackage b where b.FLOWPACKAGEID = a.id)) as rcount from sys_flow_package a where a.id='"+packageId+"'";
		Map<String, Object> resultMap=dao.queryForMap(sql);
		return resultMap;
	}



}
