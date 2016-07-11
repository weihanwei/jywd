package com.lenovocw.music.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovocw.music.controller.MoreChannelUtil;
import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.CallService;
import com.lenovocw.music.service.LogService;
import com.lenovocw.music.service.SysRedenvelopeOrdersService;
import com.lenovocw.music.service.ToBeShopManagerService;
import com.lenovocw.music.util.HttpUtilsNew;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.DateUtil;
import com.lenovocw.utils.MD5;
import com.lenovocw.utils.MD5More;
import com.lenovocw.utils.PropertyUtil;
import com.lenovocw.utils.StringUtil;

/**
 * 
 * jywd
 * 
 * @author zhangzhigao
 * 
 *         copyright:Copyright@2013 代码工作室 2015-9-7
 */

@Transactional
@Service(value = "CallServiceImpl")
public class CallServiceImpl implements CallService {
	@Resource(name = "jdbcDao")
	private JdbcDao dao;

	@Resource
	private LogService logService;

	@Resource
	private ToBeShopManagerService toBeShopManagerService;

	@Override
	public Map<String, Object> getPhoneMSG(String phone) throws Exception {

		Map<String, Object> msg = new HashMap<String, Object>();

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();

		String result = "";

		JSONObject object = null;

		String data = "";

		String status = "";
		// 1.查询4G信息
		
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
				"eid="+developercode+"&sid=Check4GCardService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		params.put("account", account);
		params.put("password",password);
		params.put("mobile", phone);

		result = httpUtilsNew.httpPost(url, params).trim();
		object = new JSONObject(result);

		data = object.getString("data");
		status = object.getString("status");

		data = Base64Utils.getFromBASE64(data);
		String datas[] = data.split("~");

		if (datas[0].equals("0")) {
			msg.put("pName", "");
		} else {

			if (datas.length > 1) {

				msg.put("pName", datas[2]);

			} else {

				msg.put("pName", "");

			}

		}
		msg.put("is4G", datas[0]);
		msg.put("phone", phone);

		// 2.客户详情
		//token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		token=dateStr+"_"+secretkey+"_"+developercode;
		token = MD5.getMD5Str(token);
		url = PropertyUtil.getKey("moreChannel") + //
				"eid="+developercode+"&sid=UserBasicInformationService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		params = new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		params.put("account", account);
		params.put("password", password);
		params.put("mobile", phone);

		result = httpUtilsNew.httpPost(url, params).trim();
		object = new JSONObject(result);

		data = object.getString("data");
		status = object.getString("status");

		data = Base64Utils.getFromBASE64(data);

		String info[] = data.split(";");

		String hfinfo = info[0];
		String hfs[] = hfinfo.split("~");
		String yyinfo = info[1];
		String yys[] = yyinfo.split("~");
		String llinfo = info[2];
		String lls[] = llinfo.split("~");

		if (hfs[4].equals("null")) {
			msg.put("hf", "----");
		} else {
			msg.put("hf", hfs[4]);
		}

		if (yys[3].equals("null")) {
			msg.put("yy", "----");
		} else {
			msg.put("yy", yys[3]);
		}

		if (lls[3].equals("null")) {
			msg.put("ll", "----");
		} else {
			msg.put("ll", lls[3]);
		}

		// 2.插入接口日志
		logService.interfacelog(developercode, "UserBasicInformationService", phone,
				status, data);

		return msg;

	}

	@Override
	public Map<String, Object> getCornetNetMSG(String phone) throws Exception {

		Map<String, Object> msg = new HashMap<String, Object>();
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
				"eid="+developercode+"&sid=getBoss54023RES&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		params.put("account", account);
		params.put("password", password);
		
		params.put("mobile", phone);

		String result = httpUtilsNew.httpPost(url, params).trim();
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = Base64Utils.getFromBASE64(data);
		String temp[] = data.split(";");
		String info[] = null;

		if (temp.length > 1) {

			info = temp[1].split("~");
			msg.put("groupName", info[1]);
			msg.put("cornet", info[2]);
			msg.put("tc", info[3]);
			msg.put("state", "1");

		} else {

			msg.put("", "");
			msg.put("", "");
			msg.put("state", "0");

		}

		// 2.插入接口日志
		logService.interfacelog("do1", "getBoss54023RES", phone, status, data);

		return msg;
	}

	@Override
	public Map<String, Object> relativesNetDel(String cornet, String phone)
			throws Exception {
		Map<String, Object> msg = new HashMap<String, Object>();

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);
		
		
		//接口秘钥
		String secretkey =PropertyUtil.getKey("query.secretKey");
		
		//开发商编号
		String 	developercode =PropertyUtil.getKey("query.developerCode");
		
		//接口服务的授权帐号
		String 	account =PropertyUtil.getKey("uploadInterface.account");
		
		//接口服务的授权密码
		String 	password =PropertyUtil.getKey("uploadInterface.password");

		//String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号  
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid="+developercode+"&sid=channelUploadService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		
		params.put("account", account);
		params.put("password", password);
		params.put("mobile", phone);
		params.put("businessType", "微店办理业务");

		String context = "QDQXJTCY#" + cornet;
		context = URLEncoder.encode(context, "UTF-8");

		dateStr = String.valueOf(date.getTime());

		String sign = "account="+account+"&password="+password
				+ "&toChannel=3&port=106573457303&context="
				+ context
				+ "&mobile="
				+ phone
				+ "&timestamp="
				+ dateStr
				+ "&bossChannel=bsacLocal&" + "bossPortal=VDJYbsac003";

		sign = MD5More.getMD5Str(sign);

		String p = "toChannel=3-port=106573457303-context=" + context
				+ "-mobile=" + phone + "-timestamp=" + dateStr
				+ "-bossChannel=bsacLocal-" + "bossPortal=VDJYbsac003"
				+ "-sign=" + sign;

		params.put("params", p);

		String result = httpUtilsNew.httpPost(url, params).trim();
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = Base64Utils.getFromBASE64(data);
		String info[] = data.split("~");

		msg.put("state", info[0]);
		msg.put("msg", info[1]);

		// 2.插入接口日志
		logService.interfacelog(developercode, "channelUploadService", phone, status,
				data);

		return msg;
	}

	@Override
	public List<Map<String, Object>> relativesNetAdd(String phone,
			String phones, String aGENTNO) throws Exception {

		List<Map<String, Object>> msgs = new ArrayList<Map<String, Object>>();

		Map<String, Object> msg = null;

		JSONArray array = new JSONArray(phones);
		JSONObject obj = null;
		String longmunber = "";
		String shortmunber = "";

		String state = "0";

		String hanhelMsg = "办理失败";

		for (int i = 0; i < array.length(); i++) {

			obj = array.getJSONObject(i);
			longmunber = obj.getString("longmunber");
			shortmunber = obj.getString("shortmunber");
			msg = postRelativesNetAdd(shortmunber, phone, longmunber);

			if (msg.get("state").toString().equals("1")) {

				state = "1";
				hanhelMsg = "办理成功";
			}

			msgs.add(msg);

		}

		toBeShopManagerService.giveGoldByTcName("亲友网新增成员", aGENTNO, phone,
				"亲友网", state, hanhelMsg);

		return msgs;
	}

	public Map<String, Object> postRelativesNetAdd(String shortmunber,
			String phone, String longmunber) throws Exception {
		Map<String, Object> msg = new HashMap<String, Object>();

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);
		
		
		//接口秘钥
		String secretkey =PropertyUtil.getKey("query.secretKey");
		
		//开发商编号
		String 	developercode =PropertyUtil.getKey("query.developerCode");
		
		//接口服务的授权帐号
		String 	account =PropertyUtil.getKey("uploadInterface.account");
		
		//接口服务的授权密码
		String 	password =PropertyUtil.getKey("uploadInterface.password");
		

		//String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号  
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid="+developercode+"&sid=channelUploadService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		
		params.put("account", account);
		params.put("password", password);
		
		params.put("mobile", phone);
		params.put("businessType", "微店办理业务");

		String context = "QDBLJTCY#" + shortmunber + "#" + longmunber;
		context = URLEncoder.encode(context, "UTF-8");

		dateStr = String.valueOf(date.getTime());

		String sign = "account="+account+"&password="+password
				+ "&toChannel=3&port=106573457303&context="
				+ context
				+ "&mobile="
				+ phone
				+ "&timestamp="
				+ dateStr
				+ "&bossChannel=bsacLocal&" + "bossPortal=VDJYbsac003";

		sign = MD5More.getMD5Str(sign);

		String p = "toChannel=3-port=106573457303-context=" + context
				+ "-mobile=" + phone + "-timestamp=" + dateStr
				+ "-bossChannel=bsacLocal-" + "bossPortal=VDJYbsac003"
				+ "-sign=" + sign;

		params.put("params", p);

		String result = httpUtilsNew.httpPost(url, params).trim();
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = Base64Utils.getFromBASE64(data);

		String temp[] = data.split("~");

		msg.put("state", temp[0]);

		msg.put("msg", temp[1]);

		// 2.插入接口日志
		logService.interfacelog(developercode, "channelUploadService", phone, status,
				data);

		return msg;
	}

	@Override
	public Map<String, Object> getRelativesNetMSG(String phone)
			throws Exception {
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
				"eid="+developercode+"&sid=CheckCornetService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		
		params.put("account", account);
		params.put("password", password);
		params.put("mobile", phone);

		String result = httpUtilsNew.httpPost(url, params).trim();
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = Base64Utils.getFromBASE64(data);

		String datas[] = data.split(";");

		Map<String, Object> state = new HashMap<String, Object>();// 状态盒子

		List<Map<String, Object>> msgs = new ArrayList<Map<String, Object>>();// 数据盒子

		Map<String, Object> msgAndState = null;// 缓冲

		Map<String, Object> parent = new HashMap<String, Object>();// 父盒子

		int index = 0;

		String temp[] = null;

		for (String d : datas) {

			msgAndState = new HashMap<String, Object>();

			if (index == 0) {// 状态

				temp = d.split("~");

				state.put("state", String.valueOf(temp[0]));
				state.put("name", temp.length >= 2 ? temp[1] : "");

			} else {// 成员电话

				temp = d.split("~");

				if (StringUtil.isNull(temp[0])) {
					msgAndState.put("cornet", "");
				} else {
					msgAndState.put("cornet", temp[0]);
				}
				msgAndState.put("trombone", temp[1]);

				msgs.add(msgAndState);
			}

			index++;
		}

		parent.put("state", state);
		parent.put("msgs", msgs);

		// 2.插入接口日志
		logService.interfacelog(developercode, "CheckCornetService", phone, status,
				data);

		return parent;
	}

	@Override
	public Map<String, Object> relativesNetHandle(String phone, String aGENTNO)
			throws Exception {
		Map<String, Object> msg = new HashMap<String, Object>();

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);
		
		
		//接口秘钥
		String secretkey =PropertyUtil.getKey("query.secretKey");
		
		//开发商编号
		String 	developercode =PropertyUtil.getKey("query.developerCode");
		
		//接口服务的授权帐号
		String 	account =PropertyUtil.getKey("uploadInterface.account");
		
		//接口服务的授权密码
		String 	password =PropertyUtil.getKey("uploadInterface.password");

		//String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号 
		
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid="+developercode+"&sid=channelUploadService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		
		params.put("account", account);
		params.put("password", password);
		
		params.put("mobile", phone);
		params.put("businessType", "微店办理业务");

		String context = "QDBLJT";
		context = URLEncoder.encode(context, "UTF-8");

		dateStr = String.valueOf(date.getTime());

		String sign = "account="+account+"&password="+password
				+ "&toChannel=3&port=106573457303&context="
				+ context
				+ "&mobile="
				+ phone
				+ "&timestamp="
				+ dateStr
				+ "&bossChannel=bsacLocal&" + "bossPortal=VDJYbsac003";

		sign = MD5More.getMD5Str(sign);

		String p = "toChannel=3-port=106573457303-context=" + context
				+ "-mobile=" + phone + "-timestamp=" + dateStr
				+ "-bossChannel=bsacLocal-" + "bossPortal=VDJYbsac003"
				+ "-sign=" + sign;

		params.put("params", p);

		String result = httpUtilsNew.httpPost(url, params).trim();
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");
		data = Base64Utils.getFromBASE64(data);
		String temp[] = data.split("~");

		msg.put("state", temp[0]);
		msg.put("msg", temp[1]);

		toBeShopManagerService.giveGoldByTcName("开通亲友网", aGENTNO, phone, "亲友网",
				temp[0], temp[1]);

		return msg;
	}

	@Override
	public Map<String, Object> cornetNetHandle(String targetPhone,
			String addPhone, String aGENTNO) throws Exception {

		Map<String, Object> msg = new HashMap<String, Object>();

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);
		
		
		//接口秘钥
		String secretkey =PropertyUtil.getKey("query.secretKey");
		
		//开发商编号
		String 	developercode =PropertyUtil.getKey("query.developerCode");
		
		//接口服务的授权帐号
		String 	account =PropertyUtil.getKey("uploadInterface.account");
		
		//接口服务的授权密码
		String 	password =PropertyUtil.getKey("uploadInterface.password");

		//String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号  
		
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid="+developercode+"&sid=channelUploadService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		params.put("account", account);
		params.put("password", password);
		params.put("mobile", addPhone);
		params.put("businessType", "微店办理业务");

		String context = "QDKHZDJW#" + targetPhone;
		context = URLEncoder.encode(context, "UTF-8");

		dateStr = String.valueOf(date.getTime());

		String sign = "account="+account+"&password="+password
				+ "&toChannel=3&port=106573457303&context="
				+ context
				+ "&mobile="
				+ addPhone
				+ "&timestamp="
				+ dateStr
				+ "&bossChannel=bsacLocal&" + "bossPortal=VDJYbsac003";

		sign = MD5More.getMD5Str(sign);

		String p = "toChannel=3-port=106573457303-context=" + context
				+ "-mobile=" + addPhone + "-timestamp=" + dateStr
				+ "-bossChannel=bsacLocal-" + "bossPortal=VDJYbsac003"
				+ "-sign=" + sign;

		params.put("params", p);

		String result = httpUtilsNew.httpPost(url, params).trim();
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");
		data = Base64Utils.getFromBASE64(data);
		String temp[] = data.split("~");

		msg.put("state", temp[0]);
		msg.put("msg", temp[1]);

		toBeShopManagerService.giveGoldByTcName("加短号网", aGENTNO, addPhone,
				"短号网", temp[0], temp[1]);

		return msg;
	}

	@Override
	public Map<String, Object> cornetNetUpdateTc(String tc, String phone)
			throws Exception {

		Map<String, Object> msg = new HashMap<String, Object>();

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);
		
		
		//接口秘钥
		String secretkey =PropertyUtil.getKey("query.secretKey");
		
		//开发商编号
		String 	developercode =PropertyUtil.getKey("query.developerCode");
		
		//接口服务的授权帐号
		String 	account =PropertyUtil.getKey("uploadInterface.account");
		
		//接口服务的授权密码
		String 	password =PropertyUtil.getKey("uploadInterface.password");

		//String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号 
		
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid="+developercode+"&sid=channelUploadService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		params.put("account", account);
		params.put("password", password);
		params.put("mobile", phone);
		params.put("businessType", "微店办理业务");

		String context = "QDXGDHTC#" + phone + "#" + tc;
		context = URLEncoder.encode(context, "UTF-8");

		dateStr = String.valueOf(date.getTime());

		String sign = "account="+account+"&password="+password
				+ "&toChannel=3&port=106573457303&context="
				+ context
				+ "&mobile="
				+ phone
				+ "&timestamp="
				+ dateStr
				+ "&bossChannel=bsacLocal&" + "bossPortal=VDJYbsac003";

		sign = MD5More.getMD5Str(sign);

		String p = "toChannel=3-port=106573457303-context=" + context
				+ "-mobile=" + phone + "-timestamp=" + dateStr
				+ "-bossChannel=bsacLocal-" + "bossPortal=VDJYbsac003"
				+ "-sign=" + sign;

		params.put("params", p);

		String result = httpUtilsNew.httpPost(url, params).trim();
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");
		data = Base64Utils.getFromBASE64(data);
		String temp[] = data.split("~");

		msg.put("state", temp[0]);
		msg.put("msg", temp[1]);

		return msg;
	}

	@Override
	public Map<String, Object> cornetNetUpdateDh(String dh, String phone)
			throws Exception {
		Map<String, Object> msg = new HashMap<String, Object>();

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);
		
		//接口秘钥
		String secretkey =PropertyUtil.getKey("query.secretKey");
		
		//开发商编号
		String 	developercode =PropertyUtil.getKey("query.developerCode");
		
		//接口服务的授权帐号
		String 	account =PropertyUtil.getKey("uploadInterface.account");
		
		//接口服务的授权密码
		String 	password =PropertyUtil.getKey("uploadInterface.password");
		

		//String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		//String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid="+developercode+"&sid=channelUploadService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		params.put("account", account);
		params.put("password", password);
		params.put("mobile", phone);
		params.put("businessType", "微店办理业务");

		String context = "QDXGDH#" + phone + "#" + dh;
		context = URLEncoder.encode(context, "UTF-8");

		dateStr = String.valueOf(date.getTime());

		String sign = "account="+account+"&password="+password
				+ "&toChannel=3&port=1008601288&context="
				+ context
				+ "&mobile="
				+ phone
				+ "&timestamp="
				+ dateStr
				+ "&bossChannel=bsacLocal&" + "bossPortal=VDJYbsac003";

		sign = MD5More.getMD5Str(sign);

		String p = "toChannel=3-port=1008601288-context=" + context
				+ "-mobile=" + phone + "-timestamp=" + dateStr
				+ "-bossChannel=bsacLocal-" + "bossPortal=VDJYbsac003"
				+ "-sign=" + sign;

		params.put("params", p);

		String result = httpUtilsNew.httpPost(url, params).trim();
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");
		data = Base64Utils.getFromBASE64(data);
		String temp[] = data.split("~");

		msg.put("state", temp[0]);
		msg.put("msg", temp[1]);

		return msg;
	}

	@Override
	public Map<String, Object> relativesNetUpgrade(String phone, String aGENTNO)
			throws Exception {

		Map<String, Object> msg = new HashMap<String, Object>();

		Date date = new Date();

		String dateStr = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		dateStr = dateFormat.format(date);
		
		
		//接口秘钥
		String secretkey =PropertyUtil.getKey("query.secretKey");
		
		//开发商编号
		String 	developercode =PropertyUtil.getKey("query.developerCode");
		
		//接口服务的授权帐号
		String 	account =PropertyUtil.getKey("uploadInterface.account");
		
		//接口服务的授权密码
		String 	password =PropertyUtil.getKey("uploadInterface.password");
		

		//String token = dateStr + "_foXjqc79_do1";// 当前日期_接口密钥_开发商编号
		String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号  
		
		token = MD5.getMD5Str(token);
		String url = PropertyUtil.getKey("moreChannel") + //
				"eid="+developercode+"&sid=channelUploadService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		params.put("account", account);
		params.put("password", password);
		params.put("mobile", phone);
		params.put("businessType", "微店办理业务");

		String context = "QDSJ";
		context = URLEncoder.encode(context, "UTF-8");

		dateStr = String.valueOf(date.getTime());

		String sign = "account="+account+"&password="+password
				+ "&toChannel=3&port=106573457303&context="
				+ context
				+ "&mobile="
				+ phone
				+ "&timestamp="
				+ dateStr
				+ "&bossChannel=bsacLocal&" + "bossPortal=VDJYbsac003";

		sign = MD5More.getMD5Str(sign);

		String p = "toChannel=3-port=106573457303-context=" + context
				+ "-mobile=" + phone + "-timestamp=" + dateStr
				+ "-bossChannel=bsacLocal-" + "bossPortal=VDJYbsac003"
				+ "-sign=" + sign;

		params.put("params", p);

		String result = httpUtilsNew.httpPost(url, params).trim();
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");
		data = Base64Utils.getFromBASE64(data);
		String temp[] = data.split("~");

		msg.put("state", temp[0]);
		msg.put("msg", temp[1]);

		toBeShopManagerService.giveGoldByTcName("亲友网升级", aGENTNO, phone, "亲友网",
				temp[0], temp[1]);

		return msg;
	}

	@Override
	public Map<String, Object> getMyshareCallRedpackage(String phone,
			String type) throws Exception {
		
		Map<String, Object> msg = new HashMap<String, Object>();
		
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
				"eid="+developercode+"&sid=CheckhwhbService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
/*		params.put("account", "AC000007");
		params.put("password", "087c4c06-a7b9-46c3-bf7d-a5b200ebb054");*/
		params.put("account", account);
		params.put("password", password);
		params.put("mobile", phone);

		String result = httpUtilsNew.httpPost(url, params).trim();
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = Base64Utils.getFromBASE64(data);

		/**
		 * status:1,data:0~list~[{time=2015-02-11 14:18:21, mobileB=13509040255,
		 * mobileA=13822997729, type=B100}, {time=2015-02-11 14:18:21,
		 * mobileB=13509040255, mobileA=13822997729, type=B100}}]
		 */
		String temp[] = data.split("~");

		String state = (temp.length > 0) ? temp[0] : "";

		String redPackageInfoStr = (temp.length >= 3) ? temp[2] : "";

		// 说明有通话红包数据
		if ("0".equals(state)) {

			List<Map<String, String>> redPackageInfoList = MoreChannelUtil
					.getDateList(redPackageInfoStr);

			List<Map<String, String>> resultPackageList = removeAndSortPackage(
					redPackageInfoList, phone, type);
			msg.put("resultPackageList", resultPackageList);

		}

		msg.put("state", state);

		return msg;
	}

	/**
	 * @param redPackages
	 *            接口获取的红包数据
	 * @param phone
	 *            手机号码
	 * @param type
	 *            type=0 我发送的红包 type=1 我领取的红包
	 * @return
	 */

	private List<Map<String, String>> removeAndSortPackage(
			List<Map<String, String>> redPackages, String phone, String type) {

		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		String typeName;

		for (Map<String, String> mapdata : redPackages) {
			mapdata.put("time1", mapdata.get("time").substring(0,10).replaceAll("-", "/"));
			
			//判断类型
			if(mapdata.containsKey("type"))
			{
				typeName=mapdata.get("type").toUpperCase();
				if(typeName.startsWith("B"))
				{
					mapdata.put("name",typeName.substring(1)+"分钟"+"本地通话");
				}
				else if(typeName.startsWith("C"))
				{
					mapdata.put("name",typeName.substring(1)+"分钟"+"长途通话");
				}
				else if(typeName.startsWith("M"))
				{
					mapdata.put("name",typeName.substring(1)+"分钟"+"漫游通话");
				}else
				{
					mapdata.put("name","通话");
				}
			}
			
			if ("0".equals(type)) {
				if (phone.equals(mapdata.get("mobileA"))) {
					result.add(mapdata);
				}

			} else {
				if (phone.equals(mapdata.get("mobileB"))) {
					result.add(mapdata);
				}
			}
		}
		
		
		Collections.sort(result, new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {

				int result = 0;
				Map m1 = (Map)o1;
				Map m2 = (Map)o2;

				Date d1 = DateUtil.string2Date(m1.get("time").toString(), "yyyy-MM-dd HH:mm:ss");

				Date d2 = DateUtil.string2Date(m2.get("time").toString(), "yyyy-MM-dd HH:mm:ss");;

				result = d2.compareTo(d1);

				return result;
			}
		});
		

		return result;
	}

}
