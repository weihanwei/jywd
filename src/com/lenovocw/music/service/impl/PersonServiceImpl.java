package com.lenovocw.music.service.impl;




import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.Handle4GService;
import com.lenovocw.music.service.LogService;
import com.lenovocw.music.service.PersonService;
import com.lenovocw.music.util.HttpUtilsNew;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.DateUtil;
import com.lenovocw.utils.MD5;
import com.lenovocw.utils.MD5More;
import com.lenovocw.utils.PropertyUtil;
import com.lenovocw.utils.UUIDUtil;


/**
 * 
 * jywd
 * @author zhangzhigao
 * 
 * copyright:Copyright@2013 代码工作室
 * 2015-9-7
 */

@Transactional
@Service(value = "PersonServiceImpl")
public class PersonServiceImpl implements PersonService{
	@Resource(name = "jdbcDao")
	private JdbcDao dao;

	@Resource
	private LogService logService;
	
	@Resource
	private Handle4GService handle4gService;
	

	
	/**
	 * 获取用户信息
	 */
	@Override
	public Map<String, Object> getPersonInfo(String phone) throws Exception {
		
		//1.客户级别信息
		Map<String, Object> msg=this.getMSGByphone(phone);
		
		//2.客户详情
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
		String url =PropertyUtil.getKey("moreChannel")+//
			     "eid="+developercode+"&sid=UserBasicInformationService&token="+//
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
		
		String info[]=data.split(";");
		
		String hfinfo=info[0];
		String hfs[]=hfinfo.split("~");
		String yyinfo=info[1];
		String yys[]=yyinfo.split("~");
		String llinfo=info[2];
		String lls[]=llinfo.split("~");
		
		if(hfs[4].equals("null")){
			msg.put("hf","----");
		}else{
			msg.put("hf",hfs[4]);
		}
		
		if(yys[3].equals("null")){
			msg.put("yy","----");
		}else{
			msg.put("yy",yys[3]);
		}
		
		if(lls[3].equals("null")){
			msg.put("ll","----");
		}else{
			msg.put("ll",lls[3]);
		}

        //3.查询客户是否实名
		token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号
		token=MD5.getMD5Str(token);
		url =PropertyUtil.getKey("moreChannel")+//
			     "eid="+developercode+"&sid=RealNameSystemService&token="+//
			      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
		
		httpUtilsNew=new HttpUtilsNew();
		params=new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		params.put("account", account);
		params.put("password",password);
		params.put("mobile", phone);
		
		result=httpUtilsNew.httpPost(url,params).trim();
		object=new JSONObject(result);
		
		data=object.getString("data");
		status=object.getString("status");
		
		data=Base64Utils.getFromBASE64(data);
		info=data.split("~");
		msg.put("issm",info[0]);
		
		//3.插入接口日志
		logService.interfacelog(developercode, "UserBasicInformationService", phone,status,data);
		
		return msg;
	}

	//客户级别信息
     public Map<String, Object> getMSGByphone(String phone){
    	
    	String Sql="select REMAININGGOLD,TOTALGOLD from SYS_CUSTOMER_MSG where phone='"+phone+"'";
		return dao.queryForMap(Sql);
    	
    }

     /**我的优惠*/
	@Override
	public Map<String, Object> getMyPrivilegeByPhone(String phone)
			throws Exception {
		
		List<Map<String, Object>> data_4g_list =new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> data_top_list =new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> data_other_list =new ArrayList<Map<String,Object>>();
		
		Map<String, Object> datamap=new HashMap<String, Object>();
		
		Map<String, Object> temp=new HashMap<String, Object>();
		
		
        Date date=new Date();
		
		String dateStr="";
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		
		dateStr=dateFormat.format(date);
		
		
		//接口秘钥
		String secretkey =PropertyUtil.getKey("discount.secretKey");
		
		//开发商编号
		String 	developercode =PropertyUtil.getKey("discount.developerCode");
		
		//接口服务的授权帐号
		String 	account =PropertyUtil.getKey("discount.account");
		
		//接口服务的授权密码
		String 	password =PropertyUtil.getKey("discount.password");
		
		//String token=dateStr+"_PacVanel_Yidusoft";//当前日期_接口密钥_开发商编号
		String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号  
		token=MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel")+//
			     "eid="+developercode+"&sid=preferentialquery&token="+//
			      token+ "&rspt=JSON";//echotech:开发商编号,sid:服务id
		
		HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
		TreeMap<String, String> params=new TreeMap<String, String>();
		params.put("systemAccount", account);
		params.put("systemPwd", password);
		
		String conditions ="<data>"+//
        "<productCode/>"+//
        "<mobile>"+phone+"</mobile>"+//
		 "<dataType>1</dataType>"+//
		 "<pageNum></pageNum>"+//
		 "<pageSize></pageSize>"+//
      "</data>";
	
		params.put("request", conditions);
		
		String result=httpUtilsNew.httpPost(url, params).trim();
		JSONObject object=new JSONObject(result);
		
		String data=object.getString("data");
		String status=object.getString("status");
		data=Base64Utils.getFromBASE64(data);
		data=data.substring(data.indexOf("<data>"), data.indexOf("</data>")+7);
		Document dom=DocumentHelper.parseText(data);
		List<Node> nodes=dom.selectNodes("data/activites/activity");
		
		String businessType="";
		
		for(Node n:nodes){
			
			businessType=n.selectSingleNode("businessType").getText();
			
			temp= new HashMap<String, Object>();
			
			temp.put("activityCode", n.selectSingleNode("activityCode").getText());
			
			temp.put("activityContent", n.selectSingleNode("activityContent").getText());
			
			temp.put("activityIntro", n.selectSingleNode("activityIntro").getText());
			
			temp.put("activityName", n.selectSingleNode("activityName").getText());
			
			temp.put("beginTime", n.selectSingleNode("beginTime").getText());
			
			temp.put("businessType", n.selectSingleNode("businessType").getText());
			
			temp.put("endTime", n.selectSingleNode("endTime").getText());
			
			temp.put("priority", n.selectSingleNode("priority").getText());
			
			// 4G购机
			if("4".equals(businessType)){
				data_4g_list.add(temp);
			}
			// 流量与宽带
			else if("5".equals(businessType)){
				data_top_list.add(temp);
			}
			// 充值与其他
			else if("6".equals(businessType)){
				data_other_list.add(temp);
			}		
			
		}
		
		datamap.put("data_4g_list",data_4g_list);
		datamap.put("data_top_list",data_top_list);
		datamap.put("data_other_list",data_other_list);
		
		return datamap;
	}

	
	@Override
	public List<Map<String,Object>> getMyDownloadMSG(String phone) throws Exception {
		String sql="select SYS_APP_DOWN.NAME,SYS_APP_DOWN.APPSIZE,to_char(SYS_DOWNAPP_LOG.TIME,'yyyy-mm-dd hh24:mi:ss')" +//
				   " as TIME,SYS_APP_DOWN.ICON,SYS_APP_DOWN.NAME from SYS_APP_DOWN,SYS_DOWNAPP_LOG  where" +//
				   " SYS_DOWNAPP_LOG.DOWNAPPID=SYS_APP_DOWN.ID AND SYS_DOWNAPP_LOG.PHONE='"+phone+"'" +//
				   " AND SYS_DOWNAPP_LOG.ISDEL='0' order by SYS_DOWNAPP_LOG.TIME DESC";
		List<Map<String, Object>> listMap=dao.queryForListMap(sql);
		return listMap;
	}

	@Override
	public int cleanDowm(String phone) throws Exception {
		String sql="update SYS_DOWNAPP_LOG set ISDEL='1'" +//
				   " where PHONE='"+phone+"' and ISDEL='0'";
		return dao.execute(sql);
	}

	@Override
	public Map<String, Object> getBillMSG(String phone) throws Exception {
		
		//1.账单接口调用
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
		String url = PropertyUtil.getKey("moreChannel")+//
			     "eid="+developercode+"&sid=QrybillingcenterrealtimeService&token="+//
			      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
		
		HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
		TreeMap<String, String> params=new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		params.put("account", account);
		params.put("password", password);
		
		params.put("mobile", phone);
		params.put("acctid", "");
		
		dateFormat=new SimpleDateFormat("yyyyMM");
		
		String endTime=dateFormat.format(date);
		
		int month=date.getMonth();
		
		int year=date.getYear();
		
		if(month<6){
			
			date.setMonth(month+12-6);
			
			date.setYear(year-1);
			
		}else{
			
			date.setMonth(month-6);
			
		}
		
		
		String startTime=dateFormat.format(date);
		
		params.put("startcycle",startTime+"00");
		params.put("endcycle",endTime+"00");
		
		String result=httpUtilsNew.httpPost(url, params).trim();
		JSONObject object=new JSONObject(result);
		
		String data=object.getString("data");
		String status=object.getString("status");
		
		data=Base64Utils.getFromBASE64(data);
		String datas[]=data.split(";");
		
		//2.echart数据封装
		int index=0;
		
		List<String> X=new ArrayList<String>();
		
		List<Double> y=new ArrayList<Double>();
		
		String bills[]=null;
	
		Map<String, Object> msg=new HashMap<String, Object>();
		
		dateFormat=new SimpleDateFormat("yyyyMMdd");
		
		for(String d:datas){
			
			if(index==0){
				
			}else{
				
				bills=d.split("~");
				
				date=dateFormat.parse(bills[0]);
				
				if(((date.getMonth()+2)%12)==0)
				{
					X.add(12+"月");
				}else
				{
					X.add(((date.getMonth()+2)%12)+"月");
				}
				
				
				if(bills.length>3){
					
					y.add(Double.valueOf(bills[3]));
					
				}else{
					
					y.add(0.00);
				}
				
			}
			
			index++;
			
		}
		
		msg.put("X", X);
		
		msg.put("Y", y);
		
		return msg;
	}

	@Override
	public Map<String, Object> getShopManagerMSG(String phone) throws Exception {
		
		String sql="select ISSHOPMANAGER,AGENTNO,PHONE from SYS_CUSTOMER_MSG where phone='"+phone+"'";
		
		return dao.queryForMap(sql);
	}

	@Override
	public Map<String, Object> handleShopManager(String phone,
			String newPassword, String oldPassword) throws Exception {
		
		Map<String, Object> msg=new HashMap<String, Object>();
		
		String sql="update SYS_CUSTOMER_MSG set ISSHOPMANAGER=1,AGENTNO='"+phone+"' where phone='"+phone+"'";
		
		if(newPassword.equals(oldPassword)){
			
			int i=dao.execute(sql);
			
			msg.put("state", i);
			
			msg.put("AGENTNO",phone);
			
		}else{
			
			msg.put("state", 0);
			msg.put("AGENTNO","");
			
		}
		
		return msg;
	}

	/**查询金币兑换记录*/
	@Override
	public List<Map<String, Object>> queryForRecord(String phone) {
		List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
		String sql = "select PHONE,TIME,GOLDCOUNT,CHANGECONTENT from sys_change_record where PHONE='"+phone+"' order by time desc";
		records = dao.queryForListMap(sql);
		return records;
	}

	/**查询账户总金币和可用金币*/
	@Override
	public Map<String, Object> queryGoldCount(String phone) {
		Map<String, Object> goldCount = new HashMap<String, Object>();
		String sql = "select TOTALGOLD,REMAININGGOLD from sys_customer_msg where PHONE='"+phone+"'";
		goldCount = dao.queryForMap(sql);
		return goldCount;
	}

	/**查询账号金币赠送记录*/
	@Override
	public List<Map<String, Object>> queryForGiveGoldRecord(String phone) {
		List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
		String sql = "select PHONE,to_char(TIME, 'yyyy-mm-dd hh24:mi:ss') TIME,CAUSE,NUM from SYS_GIVE_GOLD_LOG where PHONE='"+phone+"' order by time desc";
		records = dao.queryForListMap(sql);
		return records;
	}

	/**金币对换
	 * 兑换指令：		 context		type
	 * 兑换话费30元   	WDJBDH#30Y		1
	 * 兑换话费50元   	WDJBDH#50Y		2
	 * 兑换话费100元 	WDJBDH#100Y		3
	 * 兑换流量300M   	WDJBDH#300M		4
	 * 兑换流量500M   	WDJBDH#500M		5
	 * 兑换流量1000M  	WDJBDH#1000M	6
	 * @throws JSONException 
	 * @throws UnsupportedEncodingException 
	 * */
	@Override
	public String goldExchange(String phone, String type) throws JSONException, UnsupportedEncodingException {
		Date date=new Date();
		
		String dateStr="";
		String context= "";
		String changeContext = "";
		String issuccess = "";
		int goldcount = 0;
		int many = 0;
		int form = -1;
		
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
		String token=dateStr+"_"+secretkey+"_"+developercode;
		token=MD5.getMD5Str(token);
		String url =PropertyUtil.getKey("moreChannel")+//
			     "eid="+developercode+"&sid=channelUploadService&token="+//
			      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
		
		HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
		TreeMap<String, String> params=new TreeMap<String, String>();
/*		params.put("account", "AC000011");
		params.put("password", "4b892778-1aae-4d6b-ad61-56f3a4ff1153");*/
		params.put("account", account);
		params.put("password", password);
		params.put("mobile", phone);
		params.put("businessType", "微店办理业务");
		
		if(type.equals("1")){
			context="WDJBDH#30Y";
			changeContext = "30元话费充值兑换";
			goldcount = 30;
			many = 30;
			form = 1;
		}else if(type.equals("2")){
			context="WDJBDH#50Y";
			changeContext = "50元话费充值兑换";
			goldcount = 50;
			many = 50;
			form = 1;
		}else if(type.equals("3")){
			context="WDJBDH#100Y";
			changeContext = "100元话费充值兑换";
			goldcount = 100;
			many = 100;
			form = 1;
		}else if(type.equals("4")){
			context="WDJBDH#300M";
			changeContext = "300M流量直充兑换";
			goldcount = 20;
			many = 300;
			form = 2;
		}else if(type.equals("5")){
			context="WDJBDH#500M";
			changeContext = "500M流量直充兑换";
			goldcount = 30;
			many = 500;
			form = 2;
		}else if(type.equals("6")){
			context="WDJBDH#1000M";
			changeContext = "1000M流量直充兑换";
			goldcount = 50;
			many = 1000;
			form = 2;
		}
		
		context = URLEncoder.encode(context,"UTF-8");
		
		String mobile= phone;
		
		dateStr = String.valueOf(date.getTime());
		
		String sign="account="+account+"&password="+password+
				    "&toChannel=1&port=1008601288&context="+context+"&mobile="+
				    mobile+"&timestamp="+dateStr+"&bossChannel=bsacLocal&" +
				    		"bossPortal=VDJYbsac003";
		
		sign=MD5More.getMD5Str(sign);
		
		String p="toChannel=1-port=1008601288-context="+
		         context+"-mobile="+mobile+"-timestamp="+
		         dateStr+"-bossChannel=bsacLocal-" +
				  "bossPortal=VDJYbsac003"+"-sign="+sign;
		
		params.put("params", p);
		
		String result=httpUtilsNew.httpPost(url,params).trim();
		
		System.out.println(result);
		JSONObject object=new JSONObject(result);
		
		String data = object.getString("data");//1~消息XX成功，0~消息XXX失败
		String status = object.getString("status");
		
		data = Base64Utils.getFromBASE64(data);
		String datas[] = data.split("~");
		issuccess = datas[0];
		if(issuccess.equals("1")){
			String id = UUIDUtil.getUUID();
//			Date datetime = new Date();
//			SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String time = dateformat.format(datetime);
			
			String insert_sql = "insert into SYS_CHANGE_RECORD(ID,PHONE,TIME,GOLDCOUNT,CHANGECONTENT,ORDERNO,TYPE,MANY) " +
					"values('"+id+"','"+phone+"',to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'"+goldcount+"','"+changeContext+"','"+datas[1]+"','"+form+"','"+many+"')";
			
			String update_sql = "update SYS_CUSTOMER_MSG set REMAININGGOLD="
								+"(select REMAININGGOLD-"+goldcount+" as REMAININGGOLD from SYS_CUSTOMER_MSG where phone='"+phone+"')"			
								+"where phone = '"+phone+"'";
			// 保存兑换记录
			dao.execute(insert_sql);
			// 在剩余金币的基础上减调兑换的金币
			dao.execute(update_sql);
		}else{
			issuccess = "0";
		}
		
		System.out.println("status"+status+",data:"+data);
		
		return issuccess;
	}
	
	/**判断是否够金币兑换*/
	public boolean isCanExchange(String phone, String type){
		boolean flag = false;
		String sql = "select REMAININGGOLD from SYS_CUSTOMER_MSG t where phone='"+phone+"'";
		Map<String, Object> map = dao.queryForMap(sql);
		int goldSize = Integer.valueOf(String.valueOf(map.get("REMAININGGOLD")));
		if(type.equals("1")){
			if(goldSize >= 30){
				flag = true;
			}
		}else if(type.equals("2")){
			if(goldSize >= 50){
				flag = true;
			}
		}else if(type.equals("3")){
			if(goldSize >= 100){
				flag = true;
			}
		}else if(type.equals("4")){
			if(goldSize >= 20){
				flag = true;
			}
		}else if(type.equals("5")){
			if(goldSize >= 30){
				flag = true;
			}
		}else if(type.equals("6")){
			if(goldSize >= 50){
				flag = true;
			}
		}
		return flag;
	}
	
	/**判断本月兑换额度是否超额*/
	public Map<String, String> isExcess(String phone, String type){
		int many = 0;
		String msg = null;
		String nowtime = DateUtil.getnowDate("yyyy-MM");
		String sql = "select sum(many)sum from SYS_CHANGE_RECORD t where PHONE='"+phone+"' and substr(time,0,7)='"+nowtime+"'";
		Map<String, String> mapreasult = new HashMap<String, String>();
		if(type.equals("1")){
			sql = sql + " and type ='1'";
			many = 30;
		}else if(type.equals("2")){
			sql = sql + " and type ='1'";
			many = 50;
		}else if(type.equals("3")){
			sql = sql + " and type ='1'";
			many = 100;
		}else if(type.equals("4")){
			sql = sql + " and type ='0'";
			many = 300;
		}else if(type.equals("5")){
			sql = sql + " and type ='0'";
			many = 500;
		}else if(type.equals("6")){
			sql = sql + " and type ='0'";
			many = 1000;
		}
		Map<String, Object> map = dao.queryForMap(sql);
		String _sum = String.valueOf(map.get("sum"));
		int sum = 0;
		if(!_sum.equals("null")){
			sum = Integer.valueOf(_sum);
		}
		
		if(Integer.valueOf(type) <= 3){
			if(sum + many <= 500){
				mapreasult.put("issuccess", "0");
				msg = "您兑换的"+many+"元话费，兑换成功";
				mapreasult.put("msg", msg);
			}else{
				mapreasult.put("issuccess", "1");
				msg = "您本月已兑换"+map.get("sum")+"元话费，每月最多可以兑换500元话费，无法再兑换"+many+"元话费";
				mapreasult.put("msg", msg);
			}
			
		}else{
			if(sum + many <= 2048){
				mapreasult.put("issuccess", "0");
				msg = "您兑换的"+many+"M流量，兑换成功";
				mapreasult.put("msg", msg);
			}else{
				mapreasult.put("issuccess", "1");
				msg = "您本月已兑换"+map.get("sum")+"M流量，每月最多可以兑换2G流量，无法再兑换"+many+"M流量";
				mapreasult.put("msg", msg);
			}
		}
		return mapreasult;
	}



}
