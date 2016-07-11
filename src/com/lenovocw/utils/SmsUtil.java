package com.lenovocw.utils;

import java.net.URLEncoder;
import java.text.MessageFormat;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <span> <b>功能：</b> </span><br />
 * <span> <!--在这下面一行写功能描述-->
 *
 *</span><br /><br />
 * <span> Copyright LENOVO </span><br /> 
 * <span> Project Name apps-common </span><br /> 
 * <span> Author  ZengZS </span><br /> 
 * <span> Create Time 2012-3-29  下午01:51:06 </span><br /> 
 * <span> App version 1.0.0 </span> <br />
 * <span> JDK version used 6.0 </span><br /> 
 * <span> Modification history none    </span><br /> 
 * <span> Modified by none    </span><br />
 * 
 */
public class SmsUtil{
	
	private static final Logger logger = LoggerFactory.getLogger(SmsUtil.class);

	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 *  发送短信
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *  phone 手机号码<br />
	 *  contents 短信内容<br />
	 *  
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 *
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-3-29  下午01:52:46 </span><br /> 
	 *
	 */
	/*
	public static boolean sendSingleMsg(String phone, String contents){
		Cmpp3WebserviceLocator locator = new Cmpp3WebserviceLocator();
		Cmpp3WebservicePortType operate;
		try{
			operate = locator.getcmpp3WebserviceHttpPort();
			String rst = operate.sendSms("zengzs", "zengzs", phone, contents);
			logger.info("{}, send result: {}",phone+"<:> " + contents, rst);
		}catch (ServiceException e){
			e.printStackTrace();
			return false;
		}catch (RemoteException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	*/
	
	/**
	 * 广州电信短信下发
	 * @param phone 要发送的手机号码，支持多个号码，以逗号分隔
	 * @param contents
	 */
	public static boolean sendSms(String phone,String contents){
		boolean flag = false;
		try {
			Object[] obj = new Object[3];
			obj[0] = phone;
			obj[1] = URLEncoder.encode(contents,"UTF-8");
			String date = DateUtil.getNowStrDate("yyMMdd");
			obj[2] = URLEncoder.encode(MD5.getMD5Str(date+"*echotech"),"UTF-8");
			String url = "http://211.139.239.71/JY10086/SMS/Send?mobiles={0}&content={1}&token={2}";
			String sendUrl = MessageFormat.format(url, obj);
			//System.out.println(sendUrl);
			HttpClient http = new DefaultHttpClient();
			HttpGet get = new HttpGet(sendUrl);
			HttpResponse response = http.execute(get);
			String result = EntityUtils.toString(response.getEntity());
			logger.info("server http response result = " + result);
			if(!StringUtil.isNullOrTrimEmpty(result)){
				String[] s = result.split(",");
				if(s[0].equals("0")){
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

}
