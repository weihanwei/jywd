package com.lenovocw.music.controller.broadband;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.service.BroadbandXfService;
import com.lenovocw.music.util.HttpUtilsNew;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.ExoCoder;
import com.lenovocw.utils.JsonWriteUtil;
import com.lenovocw.utils.MD5;
import com.lenovocw.utils.PropertyUtil;

@Controller
@RequestMapping(value="/onlinepay")
public class OnlinePayMentController {
	
	@Resource
	BroadbandXfService bs;

	@RequestMapping(value="userDL.do")
	public void UserLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String type=request.getParameter("type");
		String AGENTNO=request.getParameter("AGENTNO");
		System.out.println(AGENTNO);
		String result=null;
		Map<String, Object> map=new HashMap<String, Object>();
		try{
		if(type.equals("kd")){
			String kdzh=request.getParameter("khzh");
			System.out.println(kdzh);
			String sfz=MD5.getMD5Str(request.getParameter("sfz"));//身份证进行MD5加密
            
			result=Yidu_CheckAccount(kdzh);
		    System.out.println(result);
		    JSONObject object=new JSONObject(result);
			
			String data=object.getString("data");
			String status=object.getString("status");
			if(status.equals("1")){
				data=Base64Utils.getFromBASE64(data);
				JSONObject obj=new JSONObject(data);
				if(obj.get("IDnum").equals(sfz)){
					String BuildMode=obj.getString("BuildMode");
					String BroadbandType=obj.getString("BroadbandType");
					System.out.println(BroadbandType+"--"+BuildMode);
					if(BroadbandType.equals("手机宽带") || !BuildMode.equals("1")) {      //手机宽带，不是自建宽带
						map.put("status", "2");
						map.put("msg", "尊敬的客户，您好，您所使用的宽带目前暂不支持在线续费，请亲临揭阳移动营业厅或宽带代办点办理宽带续费，感谢您对宽带业务的支持，详询1008616，中国移动。");
					}else{
						
						map.put("status", status);
						map.put("msg", "登录成功");
						HttpSession session = request.getSession();
						session.setAttribute("khdata", data);
						session.setAttribute("AGENTNO", AGENTNO);
					}
					JsonWriteUtil.write(response, map);
					
				}else{
					map.put("status", "0");
					map.put("msg", "查找不到相关用户信息，请核对你输入的信息是否正确。");
					JsonWriteUtil.write(response, map);
				}
			}else{
				map.put("status", "0");
				map.put("msg", "查找不到相关用户信息，请核对你输入的信息是否正确。");
				JsonWriteUtil.write(response, map);
			}
		    
		}else if(type.equals("sj")){
			String phone=request.getParameter("sjhm");
			String key=request.getParameter("key");
			String yzm=ExoCoder.decrypt(request.getParameter("yzm"),key);
			String ryzm=request.getSession().getAttribute("randomyzm").toString();
			if(yzm.equals(ryzm)&&!yzm.equals("")){
				result=Yidu_CheckAccount(phone);
			    System.out.println(result);
			    JSONObject object=new JSONObject(result);
				
				String data=object.getString("data");
				String status=object.getString("status");
				if(status.equals("1")){
					data=Base64Utils.getFromBASE64(data);
					JSONObject obj=new JSONObject(data);
					String BuildMode=obj.getString("BuildMode");
					String BroadbandType=obj.getString("BroadbandType");
					System.out.println(BroadbandType+"--"+BuildMode);
					if(BroadbandType.equals("手机宽带") || !BuildMode.equals("1")) {      //手机宽带，不是自建宽带
						map.put("status", "2");
						map.put("msg", "尊敬的客户，您好，您所使用的宽带目前暂不支持在线续费，请亲临揭阳移动营业厅或宽带代办点办理宽带续费，感谢您对宽带业务的支持，详询1008616，中国移动。");
					}else{
						
						map.put("status", status);
						map.put("msg", "登录成功");
						HttpSession session = request.getSession();
						session.setAttribute("khdata", data);
						session.setAttribute("AGENTNO", AGENTNO);
					}
						
						JsonWriteUtil.write(response, map);
						
				}else{
					map.put("status", "0");
					map.put("msg", "输入的宽带账号有误");
					JsonWriteUtil.write(response, map);
				}
			}
			
		}
		}catch (Exception e) {
			// TODO: handle exception
			map.put("status", "0");
			map.put("msg", "输入的宽带账号有误");
			JsonWriteUtil.write(response, map);
		}
		
	}
	
	
	//家宽账号检查
	public String Yidu_CheckAccount(String broadbandAccount){
				try{
		        Date date=new Date();
				
				String dateStr="";
				
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
				
				dateStr=dateFormat.format(date);
				
				//接口秘钥
				String secretkey =PropertyUtil.getKey("homeBroadband.secretKey");
				
				//开发商编号
				String 	developercode =PropertyUtil.getKey("homeBroadband.developerCode");
				
				//String token=dateStr+"_pcWIzemI_echotech";//当前日期_接口密钥_开发商编号
				String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号
				token=MD5.getMD5Str(token);
				String url = "http://120.198.246.98:8080/etsoa/service/aio?" +//
						     "eid="+developercode+"&sid=Yidu_CheckAccount&token="+//
						      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
				
				HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
				TreeMap<String, String> params=new TreeMap<String, String>();
				params.put("broadbandAccount", broadbandAccount);//订单
				
				String result=httpUtilsNew.httpPost(url, params).trim();
				System.out.println(result);
				return result;
				
			
	}catch (Exception e) {
		// TODO: handle exception
		return null;
	}			
	}
	
	
   @RequestMapping(value="getinfo.do")
   public void getinfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
	  Map<String, Object> map=new HashMap<String, Object>();
	   try{
	   String data=(String)request.getSession().getAttribute("khdata");
	   if(data.equals("")||data.equals(null)||data.equals("null")){
		   map.put("status", "0");
		   map.put("msg", "登录过期");
	   }else{
		   map.put("status", "1");
		   map.put("data", data);
	   }
	   }catch (Exception e) {
		// TODO: handle exception
		   map.put("status", "0");
		   map.put("msg", "登录过期");
	}
	   JsonWriteUtil.write(response, map);
   }
   /**
    * 查询加宽订单
    * @param request
    * @param response
 * @throws IOException 
 * @throws JSONException 
    */
   @RequestMapping(value="userbyorder.do")
   public  String getUserByOrder(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException{
	   String type=request.getParameter("type");
	   Map<String, Object> map=new HashMap<String, Object>();
	   if(type.equals("kd")){
		   String broadbandAccount=request.getParameter("broadbandAccount");
		   Date date=new Date();
			
			String dateStr="";
			
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
			
			dateStr=dateFormat.format(date);
			
			//接口秘钥
			String secretkey =PropertyUtil.getKey("homeBroadband.secretKey");
			
			//开发商编号
			String 	developercode =PropertyUtil.getKey("homeBroadband.developerCode");
			
			//String token=dateStr+"_pcWIzemI_echotech";//当前日期_接口密钥_开发商编号
			String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号
			token=MD5.getMD5Str(token);
			String url = PropertyUtil.getKey("moreChannel") +//
					     "eid="+developercode+"&sid=Yidu_GetOrderList&token="+//
					      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
			
			HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
			TreeMap<String, String> params=new TreeMap<String, String>();
			params.put("broadbandAccount", broadbandAccount);//订单
			
			String result=httpUtilsNew.httpPost(url, params).trim();
			System.out.println(result);
			JSONObject object=new JSONObject(result);
			
			String data=object.getString("data");
			String status=object.getString("status");
			request.getSession().setAttribute("orderlist","");
			data=Base64Utils.getFromBASE64(data);
			if(status.equals("1")){
				map.put("status", status);
				request.getSession().setAttribute("broadbandAccount",broadbandAccount);
				request.getSession().setAttribute("orderlist",data);
				
				
			}else{
				map.put("status", "0");
				map.put("msg", "交换引擎请求失败！");
			}
			JsonWriteUtil.write(response, map);
				
			
	   }else if(type.equals("sj")){
		   String broadbandAccount=request.getParameter("broadbandAccount");
		   String yzm=request.getParameter("yzm");
		   String ryzm=request.getSession().getAttribute("randomyzm").toString();
		   System.out.println(ryzm);
		   if(yzm.equals(ryzm)){
		   Date date=new Date();
			
			String dateStr="";
			
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
			
			dateStr=dateFormat.format(date);
			//接口秘钥
			String secretkey =PropertyUtil.getKey("homeBroadband.secretKey");
			
			//开发商编号
			String 	developercode =PropertyUtil.getKey("homeBroadband.developerCode");
			
			//String token=dateStr+"_pcWIzemI_echotech";//当前日期_接口密钥_开发商编号
			String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号
			token=MD5.getMD5Str(token);
			String url = PropertyUtil.getKey("moreChannel") +//
					     "eid="+developercode+"&sid=Yidu_GetOrderList&token="+//
					      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
			
			HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
			TreeMap<String, String> params=new TreeMap<String, String>();
			params.put("broadbandAccount", broadbandAccount);//订单
			
			String result=httpUtilsNew.httpPost(url, params).trim();
			System.out.println(result);
			JSONObject object=new JSONObject(result);
			String data=object.getString("data");
			String status=object.getString("status");
			request.getSession().setAttribute("orderlist","");
			data=Base64Utils.getFromBASE64(data);
			
				if(status.equals("1")){
					map.put("status", status);
					request.getSession().setAttribute("broadbandAccount",broadbandAccount);
					request.getSession().setAttribute("orderlist",data);
					
					
				}else{
					map.put("status", "0");
					map.put("msg", "交换引擎请求失败！");
				}
				JsonWriteUtil.write(response, map);
				
			
		   }else{
			   map.put("status", "0");
				map.put("msg", "验证码有误");
				JsonWriteUtil.write(response, map);
		   }
	   }
	return null;
	
      
   }
   
   /**
    * 查询订单列表
    * @param request
    * @param response
 * @throws IOException 
    */
   @RequestMapping(value="orderlist")
   public void GetOrderList(HttpServletRequest request,HttpServletResponse response) throws IOException{
	   try {
		   Map<String, Object> map=new HashMap<String, Object>();
		   if(request.getSession(false)!=null&&request.getSession().getAttribute("orderlist")!=null){
	   String orderlist=request.getSession().getAttribute("orderlist").toString();
	   if(!orderlist.equals("null")&&!orderlist.equals(null)&&!orderlist.equals("")){
		   map.put("status", "1");
		   map.put("broadbandAccount", request.getSession().getAttribute("broadbandAccount").toString());
		   map.put("list", orderlist);
	   }else{
		   map.put("status", "0");
		   map.put("list", "");
	   }
	  
		   }else{
			   map.put("status", "2");
			  
		   }
		   JsonWriteUtil.write(response, map);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("status", "0");
		   map.put("list", "");
		JsonWriteUtil.write(response, map);
		e.printStackTrace();
	}
	
   }
   
   /**
    * 家宽订单详细
    * @param request
    * @param response
    * @return
 * @throws JSONException 
    */
   @RequestMapping(value="queryorder")
   public String queryOrderInfo(HttpServletRequest request,HttpServletResponse response) throws JSONException{
	   String Orderid=request.getParameter("prodid");
	   String broadbandAccount=request.getParameter("broadbandAccount");
	   String orderlist=request.getSession().getAttribute("orderlist").toString();
	   Map<String, Object> map=new HashMap<String, Object>();
	   JSONObject obj=new JSONObject(orderlist);
	   map.put("broadbandAccount", obj.getString("BroadbandAccount"));
	   map.put("BroadbandType", obj.getString("BroadbandType"));
	   map.put("UserName", obj.getString("UserName"));
	   map.put("BoundPhone", obj.getString("BoundPhone"));
	   map.put("Address", obj.getString("Address"));
	   map.put("HandlePackage_StartTime", obj.getString("HandlePackage_StartTime"));
	   
	   String list=obj.getString("SubscriptOrders");
	   JSONArray ol=new JSONArray(list);
	   for(int i=0;i<ol.length();i++){
		   JSONObject obj1=ol.getJSONObject(i);
		   if(Orderid.equals(obj1.getString("OrderID"))){
			   map.put("list", obj1);
		   }
		   
	   }
	   JSONObject obj2=new JSONObject(map);
	   request.setAttribute("data", obj2);
		return "broadband/orderDetail";
   }
   
   /**
    * 提交订单
    * @param request
    * @param response
    * @throws JSONException
    * @throws IOException
    */
   @RequestMapping(value="sumbitOrder")
   public void SumbitOrder(HttpServletRequest request,HttpServletResponse response) throws JSONException, IOException{
	   String broadbandAccount=request.getParameter("broadbandAccount");
	   String prodid=request.getParameter("prodid");
	   String username=request.getParameter("username");
	   String prodname=request.getParameter("prodname");
	   String mark=request.getParameter("mark");
	   String Bandwidth=request.getParameter("Bandwidth");
	   String Cost=request.getParameter("Cost");
	   String ProductType=request.getParameter("ProductType");
	   
	   Date date=new Date();
		
		String dateStr="";
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		
		dateStr=dateFormat.format(date);
		
		//接口秘钥
		String secretkey =PropertyUtil.getKey("homeBroadband.secretKey");
		
		//开发商编号
		String 	developercode =PropertyUtil.getKey("homeBroadband.developerCode");
		//String token=dateStr+"_pcWIzemI_echotech";//当前日期_接口密钥_开发商编号
		String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号
		token=MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel")+//
				     "eid="+developercode+"&sid=Yidu_CheckOrder&token="+//
				      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
		
		HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
		TreeMap<String, String> params=new TreeMap<String, String>();
		params.put("broadbandAccount", broadbandAccount);//订单
		params.put("prodid", prodid);//用户名
		
		
		String result=httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object=new JSONObject(result);
		
		String data=object.getString("data");
		String status=object.getString("status");
		data=Base64Utils.getFromBASE64(data);
		System.out.println(data);
		Map<String, Object> map=new HashMap<String, Object>();
		if(status.equals("1")){//提交成功
			map.put("status", status);
			map.put("data", data);
			JSONObject obj=new JSONObject(data);
			String OrderID=new JSONObject(obj.getString("do1")).getString("OrderID");
			System.out.println(OrderID);
			String AGENTNO=request.getSession().getAttribute("AGENTNO").toString();
			String type="";
			if(AGENTNO.equals(null)||AGENTNO.equals("")){
				if(request.getSession(false)!=null){
					   if(request.getSession(false).getAttribute("phone")!=null){
						   AGENTNO=request.getSession().getAttribute("phone").toString();
					   }
					   }
				
				type="自行办理";
			}else{
				type="推荐办理";
			}
			
			bs.saveBroadband(broadbandAccount, username, prodid, prodname, mark, Bandwidth, Cost, ProductType, OrderID,AGENTNO,type);
		}else{
			map.put("status", "0");
			map.put("data", "您所提交的套餐失败，请联系揭阳移动！");
		}
		JsonWriteUtil.write(response, map);
   }
}

