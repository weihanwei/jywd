package com.lenovocw.music.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.IndexService;
import com.lenovocw.music.service.LogService;
import com.lenovocw.music.util.HttpUtilsNew;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.DateTimeUtil;
import com.lenovocw.utils.MD5;
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
@Service(value = "IndexService")
public class IndexServiceImpl implements IndexService{
	@Resource(name = "jdbcDao")
	private JdbcDao dao;
	
	@Resource
	private LogService logService;

	@Override
	public List<Map<String, Object>> getBanner() throws Exception {
		String sql="select ID, TILTLE,ICON,TYPE,URL,to_char(ENDTIME,'yyyy-mm-dd hh24:mi:ss') as ENDTIME" +//
				   " from sys_discount_tc where ISSHOW='1' and INDEXSHOW='1' order by NO ASC";
		List<Map<String, Object>> listMap=dao.queryForListMap(sql);
		return listMap;
	}

	@Override
	public List<Map<String, Object>> getAppFunction() throws Exception {
		String sql="select * from SYS_APP_FUNCTION where ISSHOW=1 and TYPE in('1','3') order by NO ASC";
		List<Map<String, Object>> listMap=dao.queryForListMap(sql);
		return listMap;
	}

	@Override
	public List<Map<String, Object>> getDownAppFunction() throws Exception {
		String sql="select * from sys_app_down where ISSHOW=1 order by NO ASC";
		List<Map<String, Object>> listMap=dao.queryForListMap(sql);
		return listMap;
	}

	@Override
	public Map<String, Object> isFirstLogin(String phone) throws Exception {

		String sql="select ISGUIDE from SYS_CUSTOMER_MSG where PHONE='"+phone+"'";

		return dao.queryForMap(sql);
	}

	@Override
	public int isGuide(String phone) throws Exception {
		String sql="update  SYS_CUSTOMER_MSG set ISGUIDE=1 where PHONE='"+phone+"'";
		return dao.execute(sql);
	}

	@Override
	public List<Map<String, Object>> getSearchMSG(String type, String keyword)
			throws Exception {
		String sql="";
		
		if("0".endsWith(type)){
		
			sql="select * from SYS_APP_search where ISSHOW=1 and" +//
				" ( name LIKE upper('%"+keyword+"%') or  details LIKE upper('%"+keyword+"%') ) order by NO ASC";
			
		}else{
			
			sql="select * from SYS_APP_search where ISSHOW=1 and ( name LIKE upper('%"+keyword+"%') or  details LIKE upper('%"+keyword+"%')  ) " +//
				" and type='"+type+"' order by NO ASC";
			
		}
		
		List<Map<String, Object>> listMap=dao.queryForListMap(sql);
		return listMap;
	}

	@Override
	public Map<String, Object> getPersonInfo(String phone) throws Exception {
		//1.客户级别信息
		Map<String, Object> msg=this.getMSGByphone(phone);
		
		if(!msg.containsKey("TOTALGOLD"))
		{
			msg.put("TOTALGOLD", 0);
		}
		
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
		String 	account =PropertyUtil.getKey("uploadInterface.account");
		
		//接口服务的授权密码
		String 	password =PropertyUtil.getKey("uploadInterface.password");
		
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
			msg.put("hf","0");
		}else{
			msg.put("hf",hfs[4]);
		}
		
		if(yys[3].equals("null")){
			msg.put("yy","0");
		}else{
			msg.put("yy",yys[3]);
		}
		
		if(lls[3].equals("null")){
			msg.put("ll","0");
		}else{
			msg.put("ll",lls[3]);
		}
		
		//3.插入接口日志
		logService.interfacelog(developercode, "UserBasicInformationService", phone,status,data);
		return msg;
	}
	
	//客户级别信息
    public Map<String, Object> getMSGByphone(String phone){
   	
   	String Sql="select REMAININGGOLD,TOTALGOLD from SYS_CUSTOMER_MSG where phone='"+phone+"'";
		return dao.queryForMap(Sql);
   	
   }

	@Override
	public int delNotice(String id, String nid, String phone) throws Exception {
		String sql = "delete from sys_notice_msg where id='"+id+"' and phone='"+phone+"'";
    	String sql1 = "delete from sys_notice where id='"+nid+"'";
    	String sql2 = "delete from sys_notice_collect where nid='"+nid+"'";
    	
    	dao.execute(sql2);
    	
    	
    	return dao.execute(sql) + dao.execute(sql1);
//		String Sql="delet from sys_notice_msg where id='"+id+"'";
//		return dao.execute(Sql);
	}

	@Override
	public int collectNotice(String nid, String phone) throws Exception {
		String timeStr = DateTimeUtil.getNowDateStr("yyyy-MM-dd HH:mm:ss");
		
		String id = UUIDUtil.getUUID();
		
		String Sql="insert into sys_notice_collect values('"+id+"','"+phone+"','"+nid+"','"+timeStr+"')";
		return dao.execute(Sql);
	}

	@Override
	public List<Map<String, Object>> getBannerByArea(String area) {
		String sql="select ID, ICON,TYPE,URL,to_char(ENDTIME,'yyyy-mm-dd hh24:mi:ss') as ENDTIME" +//
				   " from sys_discount_tc where ISSHOW=1 and area='"+area+"' order by NO ASC";
		List<Map<String, Object>> listMap=dao.queryForListMap(sql);
		return listMap;
	}

	@Override
	public Map<String, Object> getDownAppById(String id) {
		Map<String, Object> result =null;
		String sql="select * from sys_app_down where ISSHOW=1 and id='"+id+"'";
		List<Map<String, Object>> listMap=dao.queryForListMap(sql);
		if((null !=listMap) && listMap.size()>0)
		{
			result=listMap.get(0);
		}
		return result;
	}
}
