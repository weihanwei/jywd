package com.lenovocw.music.controller.broadband;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.service.OrderForPayService;


/**
 * 
 * jywd
 * @author heshiqing
 * 
 * copyright:Copyright@2013 和跃科技有限公司
 * 2015-10-12
 */
@Controller
@RequestMapping(value="orderbypay")
public class OrderForPay {
	@Resource
	OrderForPayService orderpay;
	

	@RequestMapping(value="pay")
	public void ForPay(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, JSONException{
		
		 String Orderid=request.getParameter("orderid");
		   String orderlist=request.getSession().getAttribute("orderlist").toString();
		   JSONObject obj=new JSONObject(orderlist);
		   String list=obj.getString("SubscriptOrders");
		   JSONArray ol=new JSONArray(list);
		   String ordername="";
		   String orderCost="";
		   String orderBandwidth="";
		   for(int i=0;i<ol.length();i++){
			   JSONObject obj1=ol.getJSONObject(i);
			   if(Orderid.equals(obj1.getString("OrderID"))){
				   ordername=obj1.getString("ProductName");
				   orderCost=obj1.getString("Cost");
				   orderBandwidth=obj1.getString("Bandwidth");
			   }
			   
		   }
		
		
		String pType=request.getParameter("otype");//浏览器类型
		System.out.println(pType);
		String TransferUrl="";//请求地址
		String c_id="16";//渠道标识
    	String cd_type="0";//收银台类型，0：Web；1：WAP
    	String h_pixel="";//手机屏幕分辨率长度（像素），收银台类型为1时，该参数有意义，但不必填
    	String w_pixel="";//手机屏幕分辨率宽度（像素），收银台类型为1时，该参数有意义，但不必填
    	String merchant="16010663001";//商户标识
    	String srv_id="18";//支付业务标识，如果为空，则默认为充值缴费
    	String p_method="CMPayUnion,CMPayExpress,CMPayAccount,ChinaPayExpress,ChinaPayUnion";//期望的支付方式，每个支付方式限长20个字符，多个支付方式之间使用半角逗号间隔，如果值为all，则表示期望使用所有的支付方式
    	 if (pType.equals("web"))
         {
             p_method = "CMPayUnion,CMPayExpress,CMPayAccount,ChinaPayExpress,ChinaPayUnion";
             cd_type = "0";
         }
         else
         {
            // p_method= "CMPayWAP";
        	 p_method = "all";
             cd_type = "1";
         }
    	
    	String p_direct="0";//是否直接跳转到支付机构支付页面进行支付，0：需显示支付网关收银台；1：不显示支付网关收银台,直接跳转;不必填,默认为0
    	Date date=new Date();
    	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
    	String strDate=dateFormat.format(date);
    	String o_id=request.getParameter("orderid");//订单编号，由渠道生成
    	String o_time=strDate;//订单时间，yyyyMMddHHmmss
    	String user=request.getParameter("broadbandAccount");//订单用户
    	String p_user="";//支付用户，为空时由订单用户支付
    	int point=0;//积分数量，没有积分支付要求时填0
    	System.out.println(orderCost);
    	int amount=(int)Double.parseDouble(orderCost)*100;//待付金额，单位：分，完全由积分支付时，填0
    	//int amount=1;
    	String title="宽带["+orderBandwidth+"M],使用时间（"+obj.getString("HandlePackage_StartTime")+"至)";//订单标题，需要转换为application/x-www-form-urlencoded格式，编码方式为UTF-8
    	title=URLEncoder.encode(title,"UTF-8");
    	
    	String _parm = "oId=" + o_id + "&account=" + user;
    	
    	String o_url="";//订单详细信息链接地址，需要转换为application/x-www-form-urlencoded格式，编码方式为UTF-8
    	String back_url=getProjectUrl(request)+"/orderbypay/payresult.do?"+_parm;//支付完成页面通知地址，需要转换为application/x-www-form-urlencoded格式，编码方式为UTF-8
    	back_url=URLEncoder.encode(back_url,"UTF-8");//支付完成后台通知地址，需要转换为application/x-www-form-urlencoded格式，编码方式为UTF-8
    	String notify_url=getProjectUrl(request)+"/orderbypay/payresult.do?";
    	notify_url=URLEncoder.encode(notify_url,"UTF-8");
    	String desc=URLEncoder.encode(ordername+",使用时间（"+obj.getString("HandlePackage_StartTime")+"至)","UTF-8");//描述信息，需要转换为application/x-www-form-urlencoded格式，编码方式为UTF-8
    	String digest="";//订单指纹信息
    	
    	String rev1="";
    	String rev2="";
    	
    	 if (pType.equals("web"))
             TransferUrl = "http://gd.10086.cn/epay-portal/charge/charge_input.action?";
         else
             TransferUrl = "http://wap.gd.10086.cn/epay-portal/wap-store/html/main.html?";
    	
    	try {
    		System.out.println(o_time);
    		
			digest=URLEncoder.encode(this.DigestInfo(c_id, o_id, o_time, merchant, user, p_user, point, amount),"UTF-8");
			System.out.println(digest);
			 String postData ="c_id="+c_id+"&cd_type="+cd_type+"&h_pixel="+h_pixel+"&w_pixel="+w_pixel+"&merchant="+merchant+"&srv_id="+srv_id
					 +"&p_method="+p_method+"&p_direct="+p_direct+"&o_id="+o_id+"&o_time="+o_time+"&user="+user+"&p_user="+p_user+"&point="+point
                     + "&amount="+amount+"&title="+title+"&o_url="+o_url+"&back_url="+back_url+"&notify_url="+notify_url+"&desc="+desc+"&digest="+digest+"&rev1="+rev1+"&rev2="+rev2;			
		  // String url="http://gd.10086.cn/epay-portal/charge/charge_input.action?c_id=16&cd_type=0&h_pixel=&w_pixel=&merchant=16010663001&srv_id=18&p_method=CMPayUnion,CMPayExpress,CMPayAccount,ChinaPayExpress,ChinaPayUnion&p_direct=0&o_id=2015082711330015&o_time=20151013005722&user=38500060991&p_user=&point=0&amount=1&title=%E5%B8%A6%E5%AE%BD%5b20+M%5d%EF%BC%8C%E4%BD%BF%E7%94%A8%E6%97%A5%E6%9C%9F%EF%BC%882016-05-01%E8%87%B32018-05-31%EF%BC%89&o_url=&back_url=http%3a%2f%2f211.139.239.71%2fjkxf%2fBB%2fResultsNotice.aspx%3foId%3d2015082711330015%26account%3d38500060991&notify_url=http%3a%2f%2f10.252.42.74%3a7018%2fBroadband%2fBroadbandRenew.ashx&desc=%5b20M%5d%E5%85%89%E5%AE%BD%E5%B8%A6%E5%8C%85%E5%B9%B4999%E5%85%83%E8%B5%A0%E9%80%81%E4%B8%80%E5%B9%B4%E4%BD%BF%E7%94%A8%E6%9C%9F%E4%BC%98%E6%83%A0%EF%BC%88%E5%90%88%E8%AE%A1%E4%BD%BF%E7%94%A8%E4%B8%A4%E5%B9%B4%EF%BC%89%EF%BC%882016-05-01%E8%87%B32018-05-31%EF%BC%89&digest=XxphcK3asAEOH6cxdv7sFw%3d%3d&rev1=&rev2=";
		 	
		    response.sendRedirect(TransferUrl+postData);
		 //  response.sendRedirect(url);
	}catch (Exception e) {
		// TODO: handle exception
	}
	}
	
	@RequestMapping(value="pay1")
	public void ForPay1(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, JSONException{
		
		
		 String ordername=new String(request.getParameter("ProductName").getBytes("ISO-8859-1"),"UTF-8");
		 String  orderCost=new String(request.getParameter("Cost").getBytes("ISO-8859-1"),"UTF-8");
		 String  orderBandwidth=new String(request.getParameter("Bandwidth").getBytes("ISO-8859-1"),"UTF-8");
		 String pType=request.getParameter("otype");//浏览器类型
		 System.out.println(pType);
		 String statetime=request.getParameter("NextPackage_StartTime").replace("-", "");
		System.out.println(ordername+"--"+orderCost+"---"+orderBandwidth+"--"+statetime);
		String TransferUrl="";//请求地址
		String c_id="16";//渠道标识
    	String cd_type="0";//收银台类型，0：Web；1：WAP
    	String h_pixel="";//手机屏幕分辨率长度（像素），收银台类型为1时，该参数有意义，但不必填
    	String w_pixel="";//手机屏幕分辨率宽度（像素），收银台类型为1时，该参数有意义，但不必填
    	String merchant="16010663001";//商户标识
    	String srv_id="18";//支付业务标识，如果为空，则默认为充值缴费
    	String p_method="CMPayUnion,CMPayExpress,CMPayAccount,ChinaPayExpress,ChinaPayUnion";//期望的支付方式，每个支付方式限长20个字符，多个支付方式之间使用半角逗号间隔，如果值为all，则表示期望使用所有的支付方式
    	 if (pType.equals("web"))
         {
             p_method = "CMPayUnion,CMPayExpress,CMPayAccount,ChinaPayExpress,ChinaPayUnion";
             cd_type = "0";
         }
         else
         {
            // p_method= "CMPayWAP";
        	 p_method = "all";
             cd_type = "1";
         }
    	
    	String p_direct="0";//是否直接跳转到支付机构支付页面进行支付，0：需显示支付网关收银台；1：不显示支付网关收银台,直接跳转;不必填,默认为0
    	Date date=new Date();
    	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
    	String strDate=dateFormat.format(date);
    	String o_id=request.getParameter("orderid");//订单编号，由渠道生成
    	String o_time=strDate;//订单时间，yyyyMMddHHmmss
    	String user=request.getParameter("broadbandAccount");//订单用户
    	String p_user="";//支付用户，为空时由订单用户支付
    	int point=0;//积分数量，没有积分支付要求时填0
    	System.out.println(orderCost);
    	int amount=(int)Double.parseDouble(orderCost.substring(0, orderCost.length()-1))*100;//待付金额，单位：分，完全由积分支付时，填0
    	//int amount=1;
    	String title="宽带["+orderBandwidth+"M],使用时间（"+statetime+"至)";//订单标题，需要转换为application/x-www-form-urlencoded格式，编码方式为UTF-8
    	title=URLEncoder.encode(title,"UTF-8");
    	
    	  String _parm = "oId=" + o_id + "&account=" + user;
    	
    	String o_url="";//订单详细信息链接地址，需要转换为application/x-www-form-urlencoded格式，编码方式为UTF-8
    	String back_url=getProjectUrl(request)+"/orderbypay/payresult.do?"+_parm;//支付完成页面通知地址，需要转换为application/x-www-form-urlencoded格式，编码方式为UTF-8
    	back_url=URLEncoder.encode(back_url,"UTF-8");//支付完成后台通知地址，需要转换为application/x-www-form-urlencoded格式，编码方式为UTF-8
    	String notify_url=getProjectUrl(request)+"/orderbypay/payresult.do";
    	notify_url=URLEncoder.encode(notify_url,"UTF-8");
    	String desc=URLEncoder.encode(ordername+",使用时间（"+statetime+"至)","UTF-8");//描述信息，需要转换为application/x-www-form-urlencoded格式，编码方式为UTF-8
    	String digest="";//订单指纹信息
    	
    	String rev1="";
    	String rev2="";
    	
    	 if (pType.equals("web"))
             TransferUrl = "http://gd.10086.cn/epay-portal/charge/charge_input.action?";
         else
             TransferUrl = "http://wap.gd.10086.cn/epay-portal/wap-store/html/main.html?";
    	
    	try {
    		System.out.println(o_time);
    		
    		digest=URLEncoder.encode(this.DigestInfo(c_id, o_id, o_time, merchant, user, p_user, point, amount),"UTF-8");
			System.out.println(digest);
			 String postData ="c_id="+c_id+"&cd_type="+cd_type+"&h_pixel="+h_pixel+"&w_pixel="+w_pixel+"&merchant="+merchant+"&srv_id="+srv_id
					 +"&p_method="+p_method+"&p_direct="+p_direct+"&o_id="+o_id+"&o_time="+o_time+"&user="+user+"&p_user="+p_user+"&point="+point
                     + "&amount="+amount+"&title="+title+"&o_url="+o_url+"&back_url="+back_url+"&notify_url="+notify_url+"&desc="+desc+"&digest="+digest+"&rev1="+rev1+"&rev2="+rev2;			
		  // String url="http://gd.10086.cn/epay-portal/charge/charge_input.action?c_id=16&cd_type=0&h_pixel=&w_pixel=&merchant=16010663001&srv_id=18&p_method=CMPayUnion,CMPayExpress,CMPayAccount,ChinaPayExpress,ChinaPayUnion&p_direct=0&o_id=2015082711330015&o_time=20151013005722&user=38500060991&p_user=&point=0&amount=1&title=%E5%B8%A6%E5%AE%BD%5b20+M%5d%EF%BC%8C%E4%BD%BF%E7%94%A8%E6%97%A5%E6%9C%9F%EF%BC%882016-05-01%E8%87%B32018-05-31%EF%BC%89&o_url=&back_url=http%3a%2f%2f211.139.239.71%2fjkxf%2fBB%2fResultsNotice.aspx%3foId%3d2015082711330015%26account%3d38500060991&notify_url=http%3a%2f%2f10.252.42.74%3a7018%2fBroadband%2fBroadbandRenew.ashx&desc=%5b20M%5d%E5%85%89%E5%AE%BD%E5%B8%A6%E5%8C%85%E5%B9%B4999%E5%85%83%E8%B5%A0%E9%80%81%E4%B8%80%E5%B9%B4%E4%BD%BF%E7%94%A8%E6%9C%9F%E4%BC%98%E6%83%A0%EF%BC%88%E5%90%88%E8%AE%A1%E4%BD%BF%E7%94%A8%E4%B8%A4%E5%B9%B4%EF%BC%89%EF%BC%882016-05-01%E8%87%B32018-05-31%EF%BC%89&digest=XxphcK3asAEOH6cxdv7sFw%3d%3d&rev1=&rev2=";
		 	 
		    response.sendRedirect(TransferUrl+postData);
		   //response.sendRedirect(url);
	}catch (Exception e) {
		// TODO: handle exception
	}
	}
    	
    	
public String DigestInfo(String c_id, String o_id, String o_time, String merchant, String user, String p_user, int point, int amount) throws Exception{
	    	String Key = "MnYhdwAj8Gm3Vc0NsA9BeRtn";
	    	String strList = c_id + "," + o_id + "," + o_time + "," + merchant + "," + user + ",," + point + "," + amount + ",";
			System.out.println(strList);
	    	return this.getDigest(strList, Key);
	    }

public String getDigest(String origin, String pswd) throws Exception
{
    String alg = "DESede" ;
    DESedeKeySpec dks=new DESedeKeySpec( pswd.getBytes() ); 

    SecretKeyFactory keyFactory=SecretKeyFactory.getInstance(alg);

    SecretKey secretKey=keyFactory.generateSecret(dks); 

    

    Cipher cipher = Cipher.getInstance( alg ) ;

    cipher.init( Cipher.ENCRYPT_MODE, secretKey ) ;

    byte[] after = cipher.doFinal( origin.getBytes() ) ;
     
    String digest = new String( Base64.encodeBase64( DigestUtils.md5(after) ) ) ;
    return digest;
}

/**
 * 支付成功返回页面
 * @param request
 * @param response
 * @return
 */
@RequestMapping(value="payresult")
public String  PayResult(HttpServletRequest request,HttpServletResponse response){
	String oId=request.getParameter("oId");
	String account=request.getParameter("account");
	System.out.println(oId+"--"+account);
	//更新订单状态
	orderpay.updateBroadband(account, oId);
	Map<String, Object> map=orderpay.broadbandinfo(account, oId);
	request.setAttribute("productname", map.get("PRODUCTNAME"));
	request.setAttribute("kdcost", map.get("KDCOST"));
	request.setAttribute("bandwidth", map.get("BANDWIDTH"));
	request.setAttribute("account", account);
	if(map.get("PAYRESULT").equals("支付成功")){
		//金币赠送
		orderpay.LargessJB(account, oId);
		return "payresult/payyesresult"; 
	}else{
		return "payresult/paynoresult";
	}
	 
}

public static void main(String[] args) throws Exception {
	String Key="BBDmwTjBsF7IwTIyGWt1bmFn";
	//String strList = c_id + "," + o_id + "," + o_time + "," + merchant + "," + user + "," + p_user + "," + point + "," + amount + ",";
	
	
	
	String strList = "channel1,order001,20121210231000,merchant01,13600000000, ,0,10,";
	OrderForPay op=new OrderForPay();
	 
	System.out.println(op.getDigest(strList, Key));
}

public String getProjectUrl(HttpServletRequest request){
	
	HttpServletRequest httpRequest=(HttpServletRequest)request;  
    
	String strBackUrl = "http://" + request.getServerName() //服务器地址  
	                    + ":"   
	                    + request.getServerPort()           //端口号  
	                    + httpRequest.getContextPath();//项目名称    
	return strBackUrl;
}
	
}
