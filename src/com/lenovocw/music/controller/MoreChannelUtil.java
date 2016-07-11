package com.lenovocw.music.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;

import sun.misc.BASE64Decoder;

import com.lenovocw.music.util.DESPlus;
import com.lenovocw.music.util.HttpUtilsNew;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.MD5;
import com.lenovocw.utils.MD5More;
import com.lenovocw.utils.PropertyUtil;

public class MoreChannelUtil {

	/** 家宽账号检查 */
	public void Yidu_CheckAccount() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_pcWIzemI_echotech";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=echotech&sid=Yidu_CheckAccount&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("broadbandAccount", "38500000593");// 订单

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 家宽覆盖查询
	public void SdSearch() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_pcWIzemI_echotech";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=echotech&sid=Yidu_SdSearch&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("branch", "揭西");// 订单

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 家宽订单查询
	public void CheckResult() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_pcWIzemI_echotech";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=echotech&sid=Yidu_CheckResult&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("orderId", "20150826171421906");// 订单

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 家宽报装
	public void BBInstallRegister() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_pcWIzemI_echotech";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=echotech&sid=Yidu_BBInstallRegister&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("userAccount", "");// 订单
		params.put("name", "张三");// 用户名
		params.put("mobile", "13822961213");// 手机号
		params.put("branch", "揭西");// 区域
		params.put("address", "广东省揭阳市揭西县龙潭镇");// 详细地址

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 签证
	public void CcchkauthcheckService() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=CcchkauthcheckService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		String miyao = "gmcc2011";// 密钥

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("custtype", "1");
		params.put("vertype", "AuthCheckB");
		params.put("mobile", "15819631895");
		params.put("key", "pwd");
		DESPlus des = new DESPlus(miyao);// 自定义密钥
		String value = des.encrypt("66598809");
		params.put("value", value);

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 客户信息
	public void UserBasicInformationService() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=UserBasicInformationService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "18320586620");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 账单
	public void QrybillingcenterrealtimeService() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=QrybillingcenterrealtimeService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "17876520474");
		params.put("acctid", "");
		params.put("startcycle", "20150200");
		params.put("endcycle", "20150900");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 查询4G
	public void Check4GCardService() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=Check4GCardService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "15819631895");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		String datas[] = data.split("~");

		System.out.println("status:" + status + ",data:" + data);
		System.out.println(datas[1]);

	}

	// 查看可办理的优惠
	public void queryAvailableSchemas() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_PacVanel_Yidusoft";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=Yidusoft&sid=preferentialquery&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("systemAccount", "jywd");
		params.put("systemPwd", "jywd2015");

		String conditions = "<data>" + //
				"<productCode/>" + //
				"<mobile>17876520474</mobile>" + //
				"<dataType>1</dataType>" + //
				"<pageNum>1</pageNum>" + //
				"<pageSize>10</pageSize>" + //
				"</data>";

		params.put("request", conditions);

		String result = httpUtilsNew.httpPost(url, params).trim();
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		data = this.getFromBASE64(data);
		String status = object.getString("status");

		data = data.substring(data.indexOf("<data>"),
				data.indexOf("</data>") + 7);

		System.out.println(data);

	}

	// 下行接口
	public void channelDownmsgService() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=channelDownmsgService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "15819631895");
		params.put("type", "jywd_idcode");
		params.put("level", "1");
		params.put("businessType", "微店验证码");

		String context = "ABC";
		context = URLEncoder.encode(context, "UTF-8");

		String mobile = "15819631895";

		dateStr = String.valueOf(date.getTime());

		String sign = "account=AC000007&password=087c4c06-a7b9-46c3-bf7d-a5b200ebb054"
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

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 活动办理情况查询
	public void activityRegisterService() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=activityRegisterService_N&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("params", "DHWSQ2011051301,2015-08-12,2015-05-13");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);
		String data = object.getString("data");
		String status = object.getString("status");
		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 单笔业务办理结果查询
	public void ActivityHandleInterImpl() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=ActivityHandleInterImpl&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("params", "id=5ab737d6-200c-4e5f-8bab-a91113a710b9");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 上行接口
	public void channelUploadService() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=channelUploadService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000011");
		params.put("password", "4b892778-1aae-4d6b-ad61-56f3a4ff1153");
		params.put("mobile", "13822961213");
		params.put("businessType", "微店办理业务");

		String context = "WDTJDH#15819631895";
		context = URLEncoder.encode(context, "UTF-8");

		String mobile = "13822961213";

		dateStr = String.valueOf(date.getTime());

		String sign = "account=AC000011&password=4b892778-1aae-4d6b-ad61-56f3a4ff1153"
				+ "&toChannel=1&port=1008601288&context="
				+ context
				+ "&mobile="
				+ mobile
				+ "&timestamp="
				+ dateStr
				+ "&bossChannel=bsacLocal&" + "bossPortal=VDJYbsac003";

		sign = MD5More.getMD5Str(sign);

		String p = "toChannel=1-port=1008601288-context=" + context
				+ "-mobile=" + mobile + "-timestamp=" + dateStr
				+ "-bossChannel=bsacLocal-" + "bossPortal=VDJYbsac003"
				+ "-sign=" + sign;

		params.put("params", p);

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");// 1~消息XX成功，0~消息XXX失败
		String status = object.getString("status");

		data = this.getFromBASE64(data);

		System.out.println("status" + status + ",data:" + data);

	}

	// 微店4G套餐推荐
	public void WD4GPackageTJService() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=WD4GPackageTJService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "15728820075");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 号码校验
	public void CheckMobileService() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=CheckMobileService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "13822961213");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 亲友网
	public void CheckCornetService() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=CheckCornetService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "13413980974");

		String result = httpUtilsNew.httpPost(url, params).trim();
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = Base64Utils.getFromBASE64(data);

		System.out.println("status:" + status + ",data:" + data);

	}

	// 实名查看
	public void RealNameSystemService() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=RealNameSystemService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "13822961213");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 获取指定账号所下订单
	public void GetOrderList() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=GetOrderList&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "13822961213");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 用户信息查询
	public void getQrysubsinfo1RES() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=getQrysubsinfo1RES&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "13822961213");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 短号网
	public void getBoss54023RES() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=getBoss54023RES&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "15819631895");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 推荐号码信息查询
	public void WDactivityService() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=WDactivityService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "15819631895");
		params.put("id", "1b6406cb-0d46-4e4d-b734-cec31f2ce2fe");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 推荐号码信息查询
	public void getWDLLpackageTJRES() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=getWDLLpackageTJRES&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "15819631895");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	// 通话红包记录查询
	public void CheckhwhbService() throws Exception {

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid=do1&sid=CheckhwhbService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");
		params.put("mobile", "13509040255");

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = this.getFromBASE64(data);
		System.out.println("status:" + status + ",data:" + data);

	}

	/**
	 * Base64加密
	 * 
	 * @param str
	 * @return
	 */
	public String getBase64(String str) {

		if (str == null) {

			return null;

		} else {

			return (new sun.misc.BASE64Encoder()).encode(str.getBytes());
		}

	}

	/**
	 * BASE64解密
	 * 
	 * @param
	 * @return
	 */
	public String getFromBASE64(String s) {
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

	/**
	 * 转换形如的数据为List集合: [{time=2015-02-11 14:18:21, mobileB=13509040255,
	 * mobileA=13822997729, type=B100}, {time=2015-02-11 14:18:21,
	 * mobileB=13509040255, mobileA=13822997729, type=B100}]
	 * @param str
	 * @return
	 */
	public static List<Map<String, String>> getDateList(String str) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		try
		{
			str=str.substring(1,str.lastIndexOf("]"));
			str=str+",";
			String[] arrStr=str.split("},");
			String[] mapStrArray;
			String[] parameterArray;
			for(String temp:arrStr)
			{
				Map<String, String> param = new HashMap<String, String>();
				temp=temp.trim();
				temp =temp.substring(1,temp.length());
				if(!"".equals(temp.trim()))
				{
					mapStrArray=temp.split(",");
					
					for(String mapstr:mapStrArray)
					{
						parameterArray=mapstr.split("=");
						param.put(parameterArray[0].trim(), parameterArray[1].trim());
					}
					result.add(param);
				}

			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			

		return result;
	}

	public static void main(String[] args) throws Exception {

		MoreChannelUtil moreChannelUtil=new MoreChannelUtil();
		moreChannelUtil.CheckhwhbService();
		
	}
}
