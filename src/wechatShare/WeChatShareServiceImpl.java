package wechatShare;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.lenovocw.music.util.LogRecord;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.HttpUtil;
import com.lenovocw.utils.MD5;
import com.lenovocw.utils.PropertyUtil;
import com.lenovocw.utils.ResBean;
import com.lenovocw.utils.StringUtil;
import com.lenovocw.utils.guid.GUID;

/**
 * 进行微信操作签名使用
 * 
 * @author Administrator
 *
 */
@Service(value = "WeChatShareServiceImpl")
public class WeChatShareServiceImpl implements WeChatShareService {
	/**
	 * 分享链接签名
	 * @param shareUrl paramater should order by ascii and need lowcase
	 * 不带#参数名称必须小写字母并按ascii小到大排序
	 * @return
	 */
	@Override
	public Map<String, String> weChatSigantrue(String shareUrl)throws Exception {
	
		//get jsapi_ticket
		//product
		//Map<String,String> signatureData = getJsapi_ticket();
		//test
        Map<String,String> signatureData = directGetJsapi_ticket();
		if(!signatureData.get("status").equals("0")){
			return signatureData;
		}
		
		String jsapi_ticket = signatureData.get("jsapi_ticket");
		//获取时间戳
		String timestamp = Sign.create_timestamp();
		LogRecord.info("signature data>>"+String.format("jsapi_ticket:%s,shareUrl:%s,timestamp:%s", jsapi_ticket,shareUrl,timestamp));
		String signature = Sign.sign(jsapi_ticket, shareUrl, timestamp);
		LogRecord.info(String.format("weChat singnature:%s,timestamp:%s",signature,timestamp));
		
		signatureData.remove("jsapi_ticket");
		signatureData.put("status", "0");
		signatureData.put("signature", signature);
		signatureData.put("timestamp", timestamp);
		signatureData.put("nonceStr", "82693e11-b9bc-448e-892f-f5289f46cd0f");
		LogRecord.info("get signatureData2>>"+signatureData.toString());
		return signatureData;
	}
	/**
	 * 获取微厅关注二维码ticket
	 
	@Override
	public Map<String,String> getQRcode_ticket(String tel)throws Exception{
		if(StringUtil.isNullOrTrimEmpty(tel)){
			throw new Exception("getQRcode_ticket function tel paramters is null or empty");
		}
		
		Map<String,String> signatureData = new HashMap<String,String>();
		//get ticket from cache
		Object ticketCache =  CacheUtils.get("QRCodeCache", tel);
		if(ticketCache!=null){
			LogRecord.info("get qrcode_ticket from cache:"+ticketCache);
			signatureData.put("status", "0");
			signatureData.put("qrcode_ticket", ticketCache.toString());
			return signatureData;
		}
		//get ticket from 微厅
		String rqplanid = "402";//请求微厅ID

		String rqdtype = "WEISHOP";//请求微厅的类型

		String rqtoken = "";//请求令牌

		String dateTemp = "";//时间yyyyMMdd

		Date date = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateTemp = dateFormat.format(date);

		rqtoken = MD5.getMD5Str(tel+rqplanid + dateTemp).toUpperCase();//caulate token to upperCase

		//base64 加密
		rqdtype = Base64Utils.getBase64(rqdtype);// (rqdtype);

		rqplanid = Base64Utils.getBase64(rqplanid);
		
		String telBase64  = Base64Utils.getBase64(tel);
		//请求微厅获取微信ticket
		//P/vdlogin/wqcode.action?rqmobile =XXX&rqplanid=XXX&rqdtype=XXX &rqtoken=XXX 
		String sendurl = StaticResource.bundleProperties.getString("wxurl")+ "P/vdlogin/wqcode.action?rqmobile="+telBase64+"&rqplanid=" + rqplanid+ "&rqdtype=" + rqdtype + "&rqtoken=" + rqtoken;
		LogRecord.info("获取qrcode_ticket request url:"+sendurl);
		
		//解析微厅返回数据
		String jsapi_ticket = HttpUtil.postRequestJson(sendurl).getData();
		
		LogRecord.info("微厅获取返回qrcode_ticket:"+jsapi_ticket);
		
		if(StringUtil.isNullOrEmpty(jsapi_ticket)){
			LogRecord.error("wechat signature>>get qrcode_ticket error");
			signatureData.put("status", "1");
			signatureData.put("error", "get qrcode_ticket error");
			return signatureData;
		}
		//获取ticket
		jsapi_ticket = new String(Base64Utils.getFromBASE64(jsapi_ticket));
		boolean isSignatureSuccess = false;
		int retCode = 0;
		String errorInfo="unHandle error:"+jsapi_ticket;
		try{
			retCode = Integer.parseInt(jsapi_ticket.substring(0,1));//get return code
		}catch(Exception e){
			LogRecord.error("qrcode_ticket response error>>"+e.getMessage());
			signatureData.put("status", "4");
			signatureData.put("error", "error info:"+jsapi_ticket);
			return signatureData;
		}
		switch(retCode){
			case 1:
				isSignatureSuccess = true;
				break;
			case 3:
				errorInfo = retCode+":没有鉴权（token验证不通过）";
				break;
			case 4:
				errorInfo = retCode+":获取失败";
				break;
			case 5:
				errorInfo = retCode+":渠道验证不通过";
				break;
		    default:
				errorInfo = retCode+":undefine errorCode";
		    	break;
		}
		if(!isSignatureSuccess){
			signatureData.put("status", "2");
			signatureData.put("error", errorInfo);
			return signatureData;
		}

		jsapi_ticket = jsapi_ticket.substring(2, jsapi_ticket.length());
		LogRecord.info("get qrcode_ticket:"+jsapi_ticket);
		signatureData.put("status", "0");
		signatureData.put("qrcode_ticket", jsapi_ticket);
		//save ticket to cache
		CacheUtils.put("QRCodeCache", tel,jsapi_ticket);
		return signatureData;
	}
	*/
	/**
	 * test get jsapi_ticket
	 * @return
	 */
	private Map<String,String> directGetJsapi_ticket(){
		
		String 	appId =PropertyUtil.getKey("appId");
		String 	appsecret =PropertyUtil.getKey("appsecret");
//		String appId = "wx46e693cb4ef86d4d";
//		String appsecret = "d4624c36b6795d1d99dcf0547af5443d";
//		String appId = "wx9bde70f51e1a2f17";
//		String appsecret = "23f699309b6606540653e9c8aadb6958";
//		String appId = "wxce5e597ff299e666";
//		String appsecret = "ca14dcdd9689dbf8ba2c6bd6af486815";
		Map<String,String> signatureData = new HashMap<String,String>();
		JSONObject json = null;
		ResBean resBean;
		String token;
		String tikect = null;
		boolean newAccessToken = false;
		try {
			Object objToken = CacheUtils.get("token_jsapiTicket", "access_token");
			if(objToken==null){
				newAccessToken = true;
				//获取微信token
				resBean = HttpUtil.postRequestJson(String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",appId,appsecret));
				if(resBean.getStatus()!=0){
					throw new Exception("获取微信token失败，请检查网络!");
				}
				json = new JSONObject(resBean.getData());
				LogRecord.info("get access_token from wechat>>"+json.toString());
				token = json.getString("access_token");
				CacheUtils.put("token_jsapiTicket", "access_token", token);//save token to cache
			}
			else{
				token = objToken.toString();
				LogRecord.info("get access_token from wechat>>"+token);
			}

			Object objJsapiTicket = CacheUtils.get("token_jsapiTicket", "jsapi_ticket");
			if(newAccessToken||objJsapiTicket==null){
				//获取微信ticket
				resBean = HttpUtil
						.postRequestJson(String.format(
								"https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi", token));
				if(resBean.getStatus()!=0){
					throw new Exception("获取微信token失败，请检查网络!");
				}

				JSONObject jsTikect = new JSONObject(resBean.getData());
				tikect = jsTikect.getString("ticket");
				CacheUtils.put("token_jsapiTicket", "jsapi_ticket", tikect);//save ticket to cache
			}
			else{
				tikect = objJsapiTicket.toString();
			}
			signatureData.put("status", "0");
			signatureData.put("jsapi_ticket", tikect);
			//test weChat appid
			signatureData.put("appid",appId);
		} catch (Exception e) {
			e.printStackTrace();
			signatureData.put("status", "5");
			signatureData.put("error", e.getMessage());
		}
		LogRecord.info("get signatureData>>"+signatureData.toString());
		return signatureData;
	}
	@Override
	public Map<String, String> weiTingGetTicket(String shareUrl)
			throws Exception {
		String rqplanid="218";
		
		String rqdtype="SHIMING";
		
		String appId = "wx99cae204a08e3ff4";
		
		String rqtoken="";
		
		String dateTemp="";
		
		Date date=new Date();
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		
		dateTemp=dateFormat.format(date);
		
		rqtoken=MD5.getMD5Str(rqplanid+dateTemp);
		
		rqdtype=Base64Utils.getBase64(rqdtype);
		
		rqplanid=Base64Utils.getBase64(rqplanid);
		
		String sendurl="http://www.zhg3.com/P/vdlogin/wxgeticket.action?rqplanid="+rqplanid+"&rqdtype="+rqdtype+"&rqtoken="+rqtoken;
		
		Map<String,String> signatureData = new HashMap<String, String>();
		try{
			String jsapi_ticket=HttpUtil.postRequestJson(sendurl).getData();
			
			jsapi_ticket=Base64Utils.getFromBASE64(jsapi_ticket);
				
			jsapi_ticket=jsapi_ticket.substring(2, jsapi_ticket.length());
			
			//String jsurl=request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+request.getRequestURI(); 
			
			System.out.println(sendurl);
			
			String timestamp=Sign.create_timestamp();
			
			String signature=Sign.sign(jsapi_ticket, shareUrl, timestamp);
			
			signatureData.put("status", "0");
			signatureData.put("appid",appId);
			signatureData.put("signature", signature);
			signatureData.put("timestamp", timestamp);
			signatureData.put("nonceStr", "82693e11-b9bc-448e-892f-f5289f46cd0f");
			return signatureData;
		}catch(Exception e){
			e.printStackTrace();
			signatureData.put("status", "5");
			signatureData.put("error", e.getMessage());
			return signatureData;
		}
	}

}
