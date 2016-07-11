package com.lenovocw.music.service.impl;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Decoder;

import com.lenovocw.music.controller.entity.SysRedenvelopeOrders;
import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.LogService;
import com.lenovocw.music.service.SysRedenvelopeOrdersService;
import com.lenovocw.music.util.HttpUtilsNew;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.HttpUtil;
import com.lenovocw.utils.MD5;
import com.lenovocw.utils.MD5More;
import com.lenovocw.utils.PropertyUtil;
import com.lenovocw.utils.guid.GUID;
@Service(value = "SysRedenvelopeOrdersServiceImpl")
public class SysRedenvelopeOrdersServiceImpl implements SysRedenvelopeOrdersService {
	
	public static void main(String[] args) {
		try {
			channelDownmsgService("尊敬的客户：您已成功购买100M省内通用流量红包(10个)，有效期为30天，过期剩余红包将自动兑换至您的号码，请及时邀请朋友来领取或自行直接兑换给好友。","18666331280");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void channelDownmsgService(String msg,String mobile) throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);
		
		
		//接口秘钥
		String secretkey =PropertyUtil.getKey("query.secretKey");
		
		//开发商编号
		String 	developercode =PropertyUtil.getKey("query.developerCode");
		
		//接口服务的授权帐号
		String 	account =PropertyUtil.getKey("queryInterface.account");
		
		//接口服务的授权密码
		String 	password =PropertyUtil.getKey("queryInterface.password");
		

		//String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号  
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid="+developercode+"&sid=channelDownmsgService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		params.put("account", account);
		params.put("password", password);
		params.put("mobile", mobile);
		params.put("type", "jywd_idcode");
		params.put("level", "1");
		params.put("businessType", "微店验证码");

		String context = msg;
		context = URLEncoder.encode(context, "UTF-8");

	//	String mobile = "15819631895";

		dateStr = String.valueOf(date.getTime());

		String sign = "account="+account+"&password="+password
				+ "&toChannel=1&port=1008601288"
				+ "&context="
				+ context
				+ "&mobile=" + mobile + "&timestamp=" + dateStr;

		sign = MD5More.getMD5Str(sign);

		String p = "toChannel=1-port=1008601288-context=" + context
				+ "-mobile=" + mobile + "-timestamp=" + dateStr + "-sign="
				+ sign;

		params.put("params", p);

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = getFromBASE64(data);
		System.out.println(data);
		System.out.println("status:" + status + ",data:" + data);

	}
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	@Override
	public Map<String ,String> webchatOauth2(String code) throws JSONException{
		Map<String ,String > returnMap = new HashMap<String, String>();
		try{
			code = code.trim();
			System.out.println("code:"+code);
			System.out.println("-----------------------"+1);
			String toke_url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+PropertyUtil.getKey("appId")+"&secret="+PropertyUtil.getKey("appsecret")+"&code="+code+"&grant_type=authorization_code";
			String data = HttpUtil.postRequestJson(toke_url).getData();
			
			JSONObject objeck = new JSONObject(data);
			System.out.println(objeck.toString());
			String access_token=objeck.getString("access_token");// 	网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同 
			System.out.println("access_token:"+access_token);
			String refresh_token =objeck.getString("refresh_token");// 	用户刷新access_token 
			System.out.println("refresh_token:"+refresh_token);
			String openid=objeck.getString("openid");// 	用户唯一标识 
			System.out.println("openid:"+openid);
			String userinfo_url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
			String userInfr = HttpUtil.getRequestJson(userinfo_url).getData();
			
			System.out.println("用户信息："+userInfr);
			
			JSONObject useObj = new JSONObject(userInfr);
			
			String nickname = useObj.getString("nickname");
			System.out.println("nickname:"+nickname);
			String useopenid = useObj.getString("openid");
			System.out.println("openid:"+openid);
			String headimgurl = useObj.getString("headimgurl");
			returnMap.put("nickname", nickname);
			returnMap.put("openid", openid);
			returnMap.put("headimgurl", headimgurl);
		}catch(Exception e){
			e.printStackTrace();
		}
	
		
		return returnMap;
		
		
	}
	@Resource(name = "jdbcDao")
	private JdbcDao dao;
	
	@Resource
	private LogService logService;
	
	@Override
	public SysRedenvelopeOrders get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysRedenvelopeOrders> findBySql(String sql) {
		Map<String, Object> map = dao.queryForMap(sql);
		System.out.println(map);
		return null;
	}

	@Override
	public void save(SysRedenvelopeOrders t) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(SysRedenvelopeOrders t) {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	@Override
	public String shareRedenvelope(String redId) {
		// TODO Auto-generated method stub
		//1先调用微信接口分析朋友圈
		//分析成功以后修改红包订单状态
		String finSql = " select t.SHARESTATUS from SYS_REDENVELOPE_ORDERS t where t.id = '"+redId+"' ";
		Map<String,Object> map = dao.queryForMap(finSql);
		if(map.get("SHARESTATUS")!=null){
			int shareStatus = Integer.valueOf(String.valueOf(map.get("SHARESTATUS")));
			if(shareStatus == 0){
				String sql = "update SYS_REDENVELOPE_ORDERS t set t.SHARESTATUS = 1,t.UPDATEDATE = sysdate , t.REMAININGNUMBER = t.QUANTITY where t.id = '"+redId+"' and t.SHARESTATUS = 0 and t.RECEIVESTATUS = 0 and t.ORDERSTATUS = 1 and t.AWARDSTATUS = 0 ";
				int a = dao.execute(sql);
				if(a == 0){
					return "error";
				}else{
					return "success";
				}
			}else{
				return "success";
			}
		}else{
			return "error";	
		}
		
		
	}
	
	public Map<String,Object> getRedenvelopeInfo(String id) throws Exception {
		String sql = "Select * from SYS_REDENVELOPE_ORDERS t where t.ID = '"+id+"' ";//查询锁 for update
		
		//查询出当前id的红包
		Map<String, Object> map = dao.queryForMap(sql);
		if(map.get("ID") != null){
			return map;
		}else{
			return null;
		}
		
	}
	@Transactional
	@Override
	public Map<String,String> grabFlowRedenvelope(Map<String,String> values) throws Exception {
		
		String redId = values.get("redId");
		String grabMobile = values.get("exchangeMobile");
		String sql = "Select * from SYS_REDENVELOPE_ORDERS t where t.ID = '"+redId+"' and t.SHARESTATUS = 1 and t.RECEIVESTATUS = 0 and t.REMAININGNUMBER > 0 and t.ORDERSTATUS = 1 for update ";//查询锁 for update
		
		//查询出当前id的红包
		Map<String, Object> map = dao.queryForMap(sql);
		
		System.out.println(map);
		
		if(map.get("ID") != null){//判断是否找到可领取的红包
			//判读是否是最后一个红包
			String sendMobile = String.valueOf(map.get("MOBILENO"));
			int totalNo = Integer.valueOf(String.valueOf(map.get("QUANTITY")));
			int remainingnumber = Integer.valueOf(String.valueOf(map.get("REMAININGNUMBER")));
			String receiveId = null;//抢红包流水id
			if(remainingnumber -1 == 0){
				System.out.println("最后一个红包");
				
				
				//生成领取红包流水记录
				receiveId = createRedEnvelopeReceive(map,values);
				//修改红包订单的领取状态为1：已领取
				String updateSql = " update SYS_REDENVELOPE_ORDERS t set t.REMAININGNUMBER = 0 ,t.RECEIVESTATUS = 1 ,t.UPDATEDATE = sysdate where t.ID = '"+redId+"' and  t.RECEIVESTATUS = 0 ";
				dao.execute(updateSql);
				
				String msmMsg= "";
					msmMsg= "尊敬的客户：您的"+map.get("FLOWSIZE")+"M流量红包("+totalNo+"个)已全部被好友领取。【揭阳移动】";
				channelDownmsgService(msmMsg,sendMobile);
			//	String gradMsg = "恭喜您！成功领取来自"+map.get("WECHATNAME")+"的"+map.get("FLOWSIZE")+"M省内通用流量红包，于7日内兑换有效，请及时兑换。【揭阳移动】";
			//	channelDownmsgService(gradMsg,grabMobile);
			}else if(remainingnumber -1 > 0){
				
				System.out.println("还有"+ (remainingnumber -1)+"个红包");
				receiveId = createRedEnvelopeReceive(map,values);
				String updateSql = " update SYS_REDENVELOPE_ORDERS t set t.REMAININGNUMBER = t.REMAININGNUMBER-1  ,t.UPDATEDATE = sysdate where t.ID = '"+redId+"' and  t.RECEIVESTATUS = 0 ";
				dao.execute(updateSql);
				String msmMsg= "";
					msmMsg= "尊敬的客户：您的"+map.get("FLOWSIZE")+"M流量红包("+totalNo+"个)已被好友领取，目前剩余"+(remainingnumber -1)+"个，过期未被领取的红包流量将自动兑换至您购买该红包的号码，请及时邀请好友领取。【揭阳移动】";
				channelDownmsgService(msmMsg,sendMobile);
			//	String gradMsg = "恭喜您！成功领取来自"+map.get("WECHATNAME")+"的"+map.get("FLOWSIZE")+"M省内通用流量红包，于7日内兑换有效，请及时兑换。【揭阳移动】";
			//	channelDownmsgService(gradMsg,grabMobile);
			}
			Map<String,String> returnMap = new HashMap<String, String>();
				returnMap.put("msgs", "success");
				returnMap.put("sendWechatName", (String)map.get("WECHATNAME"));
				returnMap.put("flowSize", String.valueOf(map.get("FLOWSIZE")));
				returnMap.put("receiveId", receiveId);
			return returnMap;
		}else{
			return null;
		}
		
		//判断
	}

	@Override
	public Map<String,Object> checkReceiveWechatCode(String redId, String code) {

		String sql = " select ID,EXCHANGESTATUS ,EXCHANGEMOBILE from SYS_REDENVELOPE_RECEIVE t where t.REDENVELOPEORDERSID ='"+redId+"' AND t.RECEIVEWECHATCODE = '"+code+"' "; 
		
		Map<String, Object> map = dao.queryForMap(sql);
		
		if(map.get("ID") == null){
			return null;
		}else{
			return map;
		}
		
	}
	/**
	 * 根据id查询抢到的红包记录
	 * @param redId
	 * @return
	 */
	public Map<String,Object> getReceiveList(String Id) {

		String sql = " select t.*,to_char(t.EXCHANGEDATE,'YYYY/MM/DD') AS EXCHANGEDATES,to_char(t.VALIDITYTIME,'YYYY/MM/DD') AS VALIDITYTIMES  from SYS_REDENVELOPE_RECEIVE t where t.ID ='"+Id+"'"; 
		
		Map<String, Object> map = dao.queryForMap(sql);
		
		if(map.get("ID") == null){
			return null;
		}else{
			return map;
		}
		
	}
	
	
	//count 红包领取流水序号
	public int getMsxNo(String redId){
		String sql = " select COUNT(ID) as MAXNO from SYS_REDENVELOPE_RECEIVE t where t.REDENVELOPEORDERSID ='"+redId+"' ";
		
		Map<String, Object> map = dao.queryForMap(sql);
		
		if(map.get("MAXNO") != null){
			int no = Integer.valueOf( String.valueOf(map.get("MAXNO")));
			return no;
		}else{
			return 0;
		}
	}
	//生成红包流水
	public String createRedEnvelopeReceive(Map<String ,Object> map ,Map<String,String> values)throws Exception{
		int no = getMsxNo( String.valueOf(map.get("ID")))+1;
			//判断流水记录必须小于红包购买个数
			int quantity = Integer.valueOf(String.valueOf(map.get("QUANTITY")));
			if(quantity >= no){
				String insertSql = " insert into SYS_REDENVELOPE_RECEIVE (ID,NO,REDENVELOPEORDERSID,SENDWECHATCODE,SENDWECHATNAME,RECEIVEWECHATCODE,RECEIVEWECHATNAME,FLOWSIZE,VALIDITYTIME) values (?,?,?,?,?,?,?,?,to_date(to_char(sysdate+7,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss'))";
				String id = GUID.generateGUID16();
				
				String REDENVELOPEORDERSID  = String.valueOf(map.get("ID"));
				String SENDWECHATCODE = String.valueOf(map.get("WECHATCODE"));
				String SENDWECHATNAME = String.valueOf(map.get("WECHATNAME"));
				String RECEIVEWECHATCODE = String.valueOf(values.get("receiveWechatCode"));
				String RECEIVEWECHATNAME = String.valueOf(values.get("receiveWechatName"));
				String FLOWSIZE = String.valueOf(map.get("FLOWSIZE"));
				dao.execute(insertSql,id,no,REDENVELOPEORDERSID,SENDWECHATCODE,SENDWECHATNAME,RECEIVEWECHATCODE,RECEIVEWECHATNAME,FLOWSIZE);
				return id;
			}else{
				return null;
			}
	}
	@Override
	public String getRedpackageUseTime(String id)throws Exception{
		
		String sql =  " select to_char(VALIDITYTIME,'yyyy/MM/dd hh24:mi:ss') as VALIDITYTIME from SYS_REDENVELOPE_RECEIVE t where t.ID ='"+id+"' ";
		Map<String, Object> map = dao.queryForMap(sql);
			if(map.get("VALIDITYTIME") != null){
//				String orderId = (String) map.get("REDENVELOPEORDERSID");
//				String ordesSql = " select to_char(VALIDITYTIME,'yyyy/MM/dd hh24:mi:ss') as VALIDITYTIME from SYS_REDENVELOPE_ORDERS t where t.ID ='"+orderId+"' ";
//				Map<String, Object> orderMap = dao.queryForMap(ordesSql);
//				if(orderMap.get("VALIDITYTIME")!=null){
					return (String) map.get("VALIDITYTIME");
//				}else{
//					return "";
//				}
				//				Calendar cal = Calendar.getInstance();
//				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//				
//				try {
//					
//					Date date = format.parse(String.valueOf(map.get("CREATEDATE")));
//					
//					cal.setTime(date);
//					
//					cal.add(Calendar.HOUR_OF_DAY, +168);
//					
//					String  selectDate = format.format(cal.getTime());
//					return selectDate;
//				} catch (ParseException e) {
//					e.printStackTrace();
//					return "";
//				} 
			}else{
				return "";
			}
	}

	@Override
	public Map<String, String> cashRedpackage(String id, String moblieNo,String honnerMobile)
			throws Exception {
		// 查询 抢红包记录
		 String receiveSql = " select * from SYS_REDENVELOPE_RECEIVE t where t.ID = '"+id+"' and t.EXCHANGESTATUS = 0 ";
		 Map<String, Object> receiveMap = dao.queryForMap(receiveSql);
		 Map<String, String> returnMap = new HashMap<String, String>();
		 	if(receiveMap.get("ID")!= null){
	        	//String receiveMobile = String.valueOf(receiveMap.get("EXCHANGEMOBILE"));
	        	//判断输入的电话号码是否跟抢红包时的绑定手机一致
	        //	if(receiveMobile.equals(moblieNo)){
	        		String orderId = String.valueOf(receiveMap.get("REDENVELOPEORDERSID"));
	        		//判断红包是否还在有效期内
	        		String orderSql = "select AWARDSTATUS from SYS_REDENVELOPE_ORDERS t where t.id = '"+orderId+"' ";
	        		 Map<String, Object> awardMap = dao.queryForMap(orderSql);
	        		 String awardStatus = String.valueOf( awardMap.get("AWARDSTATUS"));
	        		 if(awardStatus.equals("0")){
	        			 
	        			 //红包还在有效期内
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
	 /*	    			params.put("account", "AC000007");
	 	    			params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
	 	    			
	 	    			params.put("account", account);
	 	    			params.put("password", password);
	 	    			params.put("mobile", moblieNo);
	 	    			params.put("businessType", "微店办理业务");
	 	    			
	 	    			String context="LLHB#"+receiveMap.get("FLOWSIZE")+"M";
	 	    			context=URLEncoder.encode(context,"UTF-8");
	 	    			
	 	    		
	 	    			dateStr = String.valueOf(date.getTime());
	 	    			
	 	    			String sign="account="+account+"&password="+password+
	 	    					    "&toChannel=3&port=1008601288&context="+context+"&mobile="+
	 	    					    moblieNo+"&timestamp="+dateStr+"&bossChannel=bsacLocal&" +
	 	    					    		"bossPortal=VDJYbsac003";
	 	    			
	 	    			sign=MD5More.getMD5Str(sign);
	 	    			
	 	    			String p="toChannel=3-port=1008601288-context="+
	 	    			         context+"-mobile="+moblieNo+"-timestamp="+
	 	    			         dateStr+"-bossChannel=bsacLocal-" +
	 	    					  "bossPortal=VDJYbsac003"+"-sign="+sign;
	 	    			
	 	    			params.put("params", p);
	 	    			
	 	    			String result=httpUtilsNew.httpPost(url,params).trim();
	 	    			System.out.println(result);
	 	    			JSONObject object=new JSONObject(result);
	 	    			
	 	    			String data=object.getString("data");
	 	    			String status=object.getString("status");
	 	    			
	 	    			//请求成功
	 	    			data=Base64Utils.getFromBASE64(data);
	 	    			String info[]=data.split("~");
	 	    			
	 	    			if(info[0].equals("1")){
	 	    				String updateSql =" update SYS_REDENVELOPE_RECEIVE t set t.EXCHANGESTATUS = 1, t.UPDATEDATE = sysdate ,t.EXCHANGEMOBILE = '"+moblieNo+"',t.EXCHANGEDATE = sysdate  where t.ID = '"+id+"' ";
	 	    				dao.execute(updateSql);
	 	    				
	 	    				
	 	    				returnMap.put("status", "1");
	 		        		returnMap.put("msgs", "兑换成功");
	 		        		returnMap.put("moblieNo", moblieNo);
	 		        		//发短信
	 		        		String msmMsg= "尊敬的客户：您的流量红包已成功兑换到"+moblieNo+"【揭阳移动】";
	 						channelDownmsgService(msmMsg,honnerMobile);
	 						String msmMsg2= "尊敬的客户：您获得流量红包一个，内含"+String.valueOf(receiveMap.get("FLOWSIZE"))+"M省内通用流量，所含流量立即生效，下一月结日失效，请注意使用。【揭阳移动】";
	 						channelDownmsgService(msmMsg2,moblieNo);
	 	    			}else{
	 	    				returnMap.put("status", "0");
	 	    				String str = info[1];
	 	    				int index = str.indexOf(",");
	 	    				String ss = str.substring(index+1);
	 		        		returnMap.put("msgs", ss);
	 	    			}
	 	    			logService.interfacelog("do1", "cashRedpackage", moblieNo,status,data);
	        		 }else{
	        			 returnMap.put("status", "0");
	 		        	 returnMap.put("msgs", "红包已经过了有效期！");
	        		 }
	        		
//	        	}else{
//	        		//不相等，直接return
//	        		returnMap.put("status", "0");
//	        		returnMap.put("msgs", "电话号码与微信绑定手机号码不一致");
//	        	}
	        }else{
	        	returnMap.put("status", "0");
        		returnMap.put("msgs", "没找到红包记录！");
	        }
		   
		 	return returnMap;
	}

	@Override
	public List<Map<String, Object>> getMyshareRedpackage(String mobileNo) {
		
		String sql = " select ID,SHARESTATUS,WECHATNAME,FLOWSIZE,RECEIVESTATUS,to_char(CREATEDATE,'yyyy/MM/dd') as CREATEDATE,to_char(VALIDITYTIME,'yyyy/MM/dd') as VALIDITYTIME ,t.QUANTITY , t.REMAININGNUMBER,t.COUNTSTATUS  from SYS_REDENVELOPE_ORDERS t where t.MOBILENO = '"+mobileNo+"' and t.ORDERSTATUS = '1'  order by t.CREATEDATE DESC ";
	
		return dao.queryForListMap(sql);
	}

	@Override
	public List<Map<String, Object>> getMyReceviceRedpackage(String openID) {
		String sql = " select ID, SENDWECHATNAME,FLOWSIZE,to_char(CREATEDATE,'yy/MM/dd') as CREATEDATE ,to_char(EXCHANGEDATE,'yy/MM/dd hh24:mi') as EXCHANGEDATE , EXCHANGESTATUS ,COUNTSTATUS  from SYS_REDENVELOPE_RECEIVE t where t.RECEIVEWECHATCODE ='" +openID+"' order by t.CREATEDATE DESC";
		return dao.queryForListMap(sql);
	}
	
	//统计过期的红包记录
	/**
	 * 统计过期的红包记录
	 * alter table sys_redenvelope_receive add COUNTSTATUS NUMBER(1)  default 0 not null;
	 * alter table sys_redenvelope_receive add VALIDITYTIME DATE  ;
	 *  alter table sys_redenvelope_outtime add TYPE NUMBER(1)  default 0 not null  ;
	 */
	@Transactional
	@Override
	public void countOutTimeRedpackage(){
		String countSql = " select count(id) as totalNo from SYS_REDENVELOPE_ORDERS t where t.ORDERSTATUS = 1 and t.AWARDSTATUS = 0";
		Map<String,Object> map = dao.queryForMap(countSql);
		int totalNo = Integer.valueOf(String.valueOf(map.get("totalNo")));
		for(int i = 1; i<=totalNo;){
			int starNo = i;
			int endNo = i+50;
			i = endNo+1;
			String findSql = "select * from (select a.*,rownum rn from  (select ID,WECHATCODE,WECHATNAME,MOBILENO,AMOUNT,QUANTITY,FLOWSIZE,CREATEDATE,UPDATEDATE,SHARESTATUS,RECEIVESTATUS,REMAININGNUMBER,ORDERSTATUS,to_char(VALIDITYTIME,'yyyy/MM/dd hh24:mi:ss') as VALIDITYTIME,AWARDSTATUS from SYS_REDENVELOPE_ORDERS t where t.ORDERSTATUS = 1 and t.AWARDSTATUS = 0 and t.COUNTSTATUS = 0) a)where rn between "+starNo +" and "+endNo+" ";
			List<Map<String, Object>> list = dao.queryForListMap(findSql);
			if(!list.isEmpty()){
				for(Map<String, Object> orderMap :list){
					
					String valitidyTime = (String) orderMap.get("VALIDITYTIME");
					boolean vait =  checkValidityTime(valitidyTime);
					
					if(vait){
						String orderId = (String) orderMap.get("ID");
						String mobileNo = (String) orderMap.get("MOBILENO");
						int flowSize = Integer.valueOf(String.valueOf( orderMap.get("FLOWSIZE")));
						//先判读订单是否已经被分享
						int shareStatus = Integer.valueOf(String.valueOf( orderMap.get("SHARESTATUS")));
						if(shareStatus == 0){
							//没有分享把流量直接对话给本人电话
							int no = Integer.valueOf(String.valueOf(orderMap.get("QUANTITY")));
							//生产流量超时记录
							for(int t = 0;t<no;t++){
								saveRedpackageOutTime(mobileNo,flowSize,orderId,0);
							}
							//修改红包状态
							updateOrderAwardStatus(orderId);
						}else{
							//已经分享统计已经被领了多少个红包了多少流量
							int countNo = countReceiveByOrderId(orderId);
							int totalCount = Integer.valueOf(String.valueOf(orderMap.get("QUANTITY")));
							int no = totalCount -countNo ;//获得剩余的流量
							
							for(int t = 0;t<no;t++){
								saveRedpackageOutTime(mobileNo,flowSize,orderId,0);
							}
							//修改红包状态
							updateOrderAwardStatus(orderId);
							//统计出所有过期的抢红包纪录
//							List<Map<String, Object>> listMap =  countReceiveOutTimeByOrderId( orderId);
//							
//							if(listMap != null){
//								for(Map<String, Object> recevieMap :listMap){
//									saveRedpackageOutTime(mobileNo,flowSize,orderId);
//									updateRecvieAwardStatus((String)recevieMap.get("ID"));
//								}
//							}
						}
					}
					
				}
			}
		}
		
	}
	
	/**
	 * 查询领取红包记录表里面的过期记录记录数量应该不会很大，暂时不做分页查询
	 */
	public void countOutTimeReceiveList(){
		String countSql = " select count(id) as totalNo from SYS_REDENVELOPE_RECEIVE t where sysdate > t.validitytime and t.COUNTSTATUS = 0 and t.EXCHANGESTATUS = 0  order by t.CREATEDATE DESC";
		Map<String,Object> map = dao.queryForMap(countSql);
		int totalNo = Integer.valueOf(String.valueOf(map.get("totalNo")));
		for(int i = 1; i<=totalNo;){
			int starNo = i;
			int endNo = i+50;
			i = endNo+1;
			String findSql = "select * from (select a.*,rownum rn from  (select id, no, redenvelopeordersid, sendwechatcode, sendwechatname, receivewechatcode, receivewechatname, flowsize, exchangemobile, exchangedate, createdate, updatedate, exchangestatus, to_char(VALIDITYTIME, 'yyyy/MM/dd hh24:mi:ss') as VALIDITYTIME, COUNTSTATUS from sys_redenvelope_receive v where v.EXCHANGESTATUS = 0 and v.COUNTSTATUS = 0 and sysdate > v.VALIDITYTIME ) a)where rn between "+starNo +" and "+endNo+" ";
			List<Map<String, Object>> list = dao.queryForListMap(findSql);
			if(!list.isEmpty()){
				for(Map<String, Object> receiveMap :list){
					String mobileNo =  getBuyMobileBYid((String)receiveMap.get("REDENVELOPEORDERSID"));
					int flowSize = Integer.valueOf(String.valueOf(receiveMap.get("FLOWSIZE")));
						saveRedpackageOutTime(mobileNo,flowSize,(String)receiveMap.get("REDENVELOPEORDERSID"),1);
						updateRecvieAwardStatus((String)receiveMap.get("ID"));
					}
				}
			}
		}
	/**
	 * 根据订单号查询红包购买的电话号码
	 */
	public String getBuyMobileBYid(String id){
		String sql = " select t.mobileno from sys_redenvelope_orders t where t.id = '"+id+"' ";
		Map<String,Object> map = dao.queryForMap(sql);
		if(map.get("MOBILENO") != null){
			return (String )map.get("MOBILENO");
		}else{
			return null;
		}
	}
	
	
	//查询红包领取状态
	@Override
	public List<Map<String,Object>>getRedpackageRecevice(String openID){
		String sql = " SELECT T.ID,R.EXCHANGESTATUS,R.ID AS RID, T.WECHATNAME,T.QUANTITY,T.FLOWSIZE,to_char(T.CREATEDATE,'yyyy/MM/dd') as CREATEDATE,T.FLOWSIZE,to_char(R.VALIDITYTIME,'yyyy/MM/dd') as VDATE,(T.QUANTITY - T.REMAININGNUMBER) AS REMAININGNUMBER FROM SYS_REDENVELOPE_ORDERS T left join  SYS_REDENVELOPE_RECEIVE R on T.id = R.REDENVELOPEORDERSID where R.RECEIVEWECHATCODE ='" +openID+"' order by t.CREATEDATE DESC";
		return dao.queryForListMap(sql);
	}
	@Override
	public List<Map<String,Object>>getReceviceById(String id){
		String sql = "select RECEIVEWECHATNAME,FLOWSIZE,to_char(T.CREATEDATE,'yyyy/MM/dd') as CREATEDATE,to_char(T.UPDATEDATE,'yyyy/MM/dd') as UPDATEDATE,EXCHANGESTATUS from SYS_REDENVELOPE_RECEIVE t where t.REDENVELOPEORDERSID = '" +id+"' order by t.CREATEDATE DESC";
		return dao.queryForListMap(sql);
	}
	//兑换过期的流量红包
	@Transactional
	@Override
	public void cashOutTimeRedpackage(){
		
		String countSql = " select count(id) as totalNo from SYS_REDPACKAGE_OUTTIME t where t.EXCHANGESTATUS = 0 and t.READSTATUS = 0";
		Map<String,Object> map = dao.queryForMap(countSql);
		int totalNo = Integer.valueOf(String.valueOf(map.get("totalNo")));
		for(int i = 1; i<=totalNo;){
			int starNo = i;
			int endNo = i+50;
			i = endNo+1;
			
			String findSql = " select * from (select a.*,rownum rn from  (select ID, REDENVELOPEORDERSID,FLOWSIZE,EXCHANGEMOBILE,EXCHANGEDATE,EXCHANGESTATUS from SYS_REDPACKAGE_OUTTIME t where t.EXCHANGESTATUS = 0 and t.READSTATUS = 0 ) a)where rn between "+starNo +" and "+endNo +" ";
			
			System.out.println(findSql);
			
			List<Map<String, Object>> list = dao.queryForListMap(findSql);
			
			if(!list.isEmpty()){
				for(Map<String, Object> outTimeMap :list){
					String id = (String) outTimeMap.get("ID");
					String mobileNo = (String) outTimeMap.get("EXCHANGEMOBILE");
					int flowSize = Integer.valueOf(String.valueOf(outTimeMap.get("FLOWSIZE")));
					String sql = "update SYS_REDPACKAGE_OUTTIME t set t.READSTATUS = 1 , t.UPDATEDATE = sysdate where t.ID = '"+id+"' ";
					dao.execute(sql);
					String result = cashOutTimeRedpackage( mobileNo, flowSize);
					
					if(result.equals("1")){
						String updateSql = " update SYS_REDPACKAGE_OUTTIME t set t.EXCHANGESTATUS = 1 , t.UPDATEDATE = sysdate where t.ID = '"+id+"' ";
						dao.execute(updateSql);
					}
				}
			}
		}
		
		
		
	}
	
	//判断红包是否已经过期
	public boolean checkValidityTime(String timeStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			Date time = sdf.parse(timeStr);
			Date now = new Date();
			if(time.before(now)){
				return true;
			}else{
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false ;
		}
	}

	//修改红包兑换状态
	
	public void updateOrderAwardStatus(String id){
		String updateORDERSql = " update SYS_REDENVELOPE_ORDERS t set t.AWARDSTATUS = 1 ,t.UPDATEDATE = sysdate ,t.COUNTSTATUS = 1 , t.SHARESTATUS = 1  where t.ID = '"+id+"' ";
		dao.execute(updateORDERSql);
//		String updateRECEIVESql = " update SYS_REDENVELOPE_RECEIVE t set t.EXCHANGESTATUS = 2 ,t.UPDATEDATE = sysdate where t.REDENVELOPEORDERSID = '"+id+"' ";
//		dao.execute(updateRECEIVESql);
	}
	//修改红包兑换状态
	
	public void updateRecvieAwardStatus(String id){
		String updateRECEIVESql = " update SYS_REDENVELOPE_RECEIVE t set t.EXCHANGESTATUS = 2 ,t.UPDATEDATE = sysdate ,t.COUNTSTATUS = 1 where t.ID = '"+id+"' ";
		dao.execute(updateRECEIVESql);
	}
	//统计已经兑换了多少条记录 返回 int
	public int countReceiveByOrderId(String orderId){
		
		String countSql = " select count(id) as countNo from SYS_REDENVELOPE_RECEIVE t where t.REDENVELOPEORDERSID = '"+orderId+"'  order by t.CREATEDATE DESC";
		Map<String, Object> map = dao.queryForMap(countSql);
		int countNo = Integer.valueOf(String.valueOf(map.get("countNo")));
		return countNo;
	}
	//统计已经已经过期的领取记录 返回 int
		public List<Map<String, Object>> countReceiveOutTimeByOrderId(String orderId){
			
			String countSql = " select *  from SYS_REDENVELOPE_RECEIVE t where sysdate > t.validitytime and t.COUNTSTATUS = 0 and  t.REDENVELOPEORDERSID = '"+orderId+"'  order by t.CREATEDATE DESC";
			List<Map<String, Object>> list = dao.queryForListMap(countSql);
			if(list.isEmpty()){
				return null;
			}else{
				return list;
			}	
		}
	
	
	//红包过期后生产红包过期记录
	public void saveRedpackageOutTime(String mobileNo,int flowSize,String orderId,int type){
		String insertSql = "insert into SYS_REDPACKAGE_OUTTIME (ID,REDENVELOPEORDERSID,FLOWSIZE,EXCHANGEMOBILE,TYPE) values (?,?,?,?,?)";
		String id = GUID.generateGUID16();
		dao.execute(insertSql,id,orderId,flowSize,mobileNo,type);
	}
	
	//兑换流量
	public String cashOutTimeRedpackage(String moblieNo,int flowSize)
			 {
		// 查询 抢红包记录
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
	  /*  			params.put("account", "AC000007");
	    			params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
	    			params.put("account", account);
	    			params.put("password", password);
	    			params.put("mobile", moblieNo);
	    			params.put("businessType", "微店办理业务");
	    			
	    			String context="LLHB#"+flowSize+"M";
	    			try {
						context=URLEncoder.encode(context,"UTF-8");
					
	    			
	    		
		    			dateStr = String.valueOf(date.getTime());
		    			
		    			String sign="account="+account+"&password="+password+
		    					    "&toChannel=3&port=1008601288&context="+context+"&mobile="+
		    					    moblieNo+"&timestamp="+dateStr+"&bossChannel=bsacLocal&" +
		    					    		"bossPortal=VDJYbsac003";
		    			
		    			sign=MD5More.getMD5Str(sign);
		    			
		    			String p="toChannel=3-port=1008601288-context="+
		    			         context+"-mobile="+moblieNo+"-timestamp="+
		    			         dateStr+"-bossChannel=bsacLocal-" +
		    					  "bossPortal=VDJYbsac003"+"-sign="+sign;
		    			
		    			params.put("params", p);
		    			
		    			String result=httpUtilsNew.httpPost(url,params).trim();
		    			System.out.println(result);
		    			JSONObject object=new JSONObject(result);
		    			
		    			String data=object.getString("data");
		    			String status=object.getString("status");
		    			
		    			//请求成功
		    			data=Base64Utils.getFromBASE64(data);
		    			String info[]=data.split("~");
		    			System.out.println(data);
		    			logService.interfacelog("do1", "cashRedpackage", moblieNo,status,data);
		    			return info[0] ; 
	    			} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return "0";
					}
	}

	@Override
	public int getFlowSizeById(String id) {
		String sql = " select * from SYS_REDENVELOPE_ORDERS t where t.ID = '"+id+"' ";
		Map<String, Object> map = dao.queryForMap(sql);

		if(map.get("ID") !=null){
			int flowSize =Integer.valueOf( String.valueOf(map.get("FLOWSIZE")));
			return flowSize;
		}else{
			return 0;
		}
		
	}
	@Override
	public Map<String, Object> getRedpackageById(String id) {
		String sql = " select t.*,to_char(T.CREATEDATE,'YYYY/MM/dd') as CREATEDATES,to_char(T.UPDATEDATE,'YYYY/MM/dd') as UPDATEDATES ,to_char(VALIDITYTIME,'YYYY/MM/dd') as VALIDITYTIMES from SYS_REDENVELOPE_ORDERS t where t.ID = '"+id+"' ";
		Map<String, Object> map = dao.queryForMap(sql);
		if(map.get("ID") !=null){
			return map;
		}else{
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.lenovocw.music.service.SysRedenvelopeOrdersService#queryBuyRedPackCount(java.lang.String)
	 * 
	 *  select NVL(sum(a.QUANTITY), 0)
          from SYS_REDENVELOPE_ORDERS a
         where a.MOBILENO = '13925614317'
           and a.ORDERSTATUS = '1'
           and a.createdate >=
               to_date(to_char(trunc(add_months(last_day(sysdate), -1) + 1),
                        'yyyy-mm-dd') || ' 00:00:00',
                'yyyy-mm-dd hh24:mi:ss')
          and a.createdate <=
              to_date(to_char(last_day(sysdate), 'yyyy-mm-dd') || ' 23:59:59',
                'yyyy-mm-dd hh24:mi:ss')
	 */
	@Override
	public int queryBuyRedPackCount(String phone) {
		
		String sql = " select NVL(sum(a.QUANTITY), 0) from SYS_REDENVELOPE_ORDERS a where a.MOBILENO = '"
				+ phone
				+ "' and a.ORDERSTATUS = '1' and a.createdate >=  to_date(to_char(trunc(add_months(last_day(sysdate), -1) + 1),   'yyyy-mm-dd') || ' 00:00:00',  'yyyy-mm-dd hh24:mi:ss') and a.createdate <=  to_date(to_char(last_day(sysdate), 'yyyy-mm-dd') || ' 23:59:59',  'yyyy-mm-dd hh24:mi:ss') ";

		return dao.queryForInteger(sql);
	}
	
	
	/**
	 * 根据红包id判断是否已经过期
	 */
	public Map<String,Object> checkRedpacakgeValid(String id) {
		
		String sql = " select * from SYS_REDENVELOPE_ORDERS t where t.ID = '"+id+"' and t.COUNTSTATUS = 0 ";
		
		Map<String,Object> map = dao.queryForMap(sql);
		if(map.get("ID")!=null){
			return map;
		}else{
			return null;
		}
	}
	/**
	 * 过期兑换下发短信
	 * @throws Exception 
	 */
	public void sendMsmTOcashFlow() throws Exception{
		//先找出需要发短信的用户号码与流量详情
		String sql = "select count(1) as COUNT ,REDENVELOPEORDERSID,sum(t.flowsize) AS TFLOWSIZE ,t.exchangemobile AS MOBILE,t.type as TYP from SYS_REDPACKAGE_OUTTIME t where t.updatedate > trunc(sysdate, 'DD') and t.exchangestatus = 1 group by t.exchangemobile , t.redenvelopeordersid  ,t.type ";
		
		List<Map<String,Object>> list = dao.queryForListMap(sql);
			for(Map<String,Object> map :list){
				StringBuffer sbf = new StringBuffer("");
				int type = Integer.valueOf(String.valueOf(map.get("TYP")));
				String orderID= String.valueOf(map.get("REDENVELOPEORDERSID"));
				Map<String, Object>orderMap =  getRedpackageById(orderID);
				if(orderMap != null){
					if(type == 1){//领取红包未兑换过期
						sbf.append("尊敬的客户：您购买的"+ Integer.valueOf(String.valueOf(orderMap.get("FLOWSIZE")))+
								"M流量红包（"+ Integer.valueOf(String.valueOf(orderMap.get("QUANTITY")))+
								"个），有"+Integer.valueOf(String.valueOf(map.get("COUNT")))+
								"个已过期未兑换，合计"+Integer.valueOf(String.valueOf(map.get("TFLOWSIZE")))+
								"M省内通用流量已兑换到您的号码，流量立即生效，下一月结日失效，请注意使用。【揭阳移动】");
						
					}else{
						sbf.append("尊敬的客户：您购买的"+ Integer.valueOf(String.valueOf(orderMap.get("FLOWSIZE")))+
								"M流量红包（"+ Integer.valueOf(String.valueOf(orderMap.get("FLOWSIZE")))+
								"个），有"+Integer.valueOf(String.valueOf(map.get("COUNT")))+
								"个已过期未被领取或兑换，合计"+Integer.valueOf(String.valueOf(map.get("TFLOWSIZE")))+
								"M省内通用流量已兑换至您的号码，流量立即生效，下一月结日失效，请注意使用。【揭阳移动】");
					}
				}
				channelDownmsgService(sbf.toString(),(String)map.get("MOBILE"));
			}
	
	}
}
