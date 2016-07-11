package com.lenovocw.music.service.impl;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Decoder;

import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.model.FlowTC;
import com.lenovocw.music.service.FlowService;
import com.lenovocw.music.service.LogService;
import com.lenovocw.music.service.ToBeShopManagerService;
import com.lenovocw.music.util.HttpUtilsNew;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.MD5;
import com.lenovocw.utils.MD5More;
import com.lenovocw.utils.PropertyUtil;
import com.lenovocw.utils.guid.GUID;

/**
 * 
 * jywd
 * 
 * @author zhangzhigao
 * 
 *         copyright:Copyright@2013 代码工作室 2015-9-7
 */

@Transactional
@Service(value = "FlowServiceImpl")
public class FlowServiceImpl implements FlowService {
	@Resource(name = "jdbcDao")
	private JdbcDao dao;

	@Resource
	private LogService logService;

	@Resource
	private ToBeShopManagerService toBeShopManagerService;

	@Override
	public Map<String, Object> changePrice(String number, String size)
			throws Exception {

		Map<String, Object> result = null;

		// 判断当前是否为特惠日期范围
		String isDisDateSql = "select count(*) from sys_flow_disdate a where a.begintime <=sysdate and sysdate<a.endtime";

		// 当前日期所在的特惠日期个数
		int disDateCount = dao.queryForInteger(isDisDateSql);

		String sql = "select PRICE,DISPRICE,ISDISAPPEAR from SYS_REDENVELOPE_type where FLOWNUMBER="
				+ Integer.valueOf(number) + "" + //
				" and FLOWSIZE=" + Integer.valueOf(size);

		List<Map<String, Object>> priceList = dao.queryForListMap(sql);

		if (priceList.size() > 0) {
			result = priceList.get(0);

			if ((disDateCount > 0)
					&& ("1".equals(String.valueOf(result.get("ISDISAPPEAR"))))
					&& (null != result.get("DISPRICE"))) {
				result.put("PRICE", result.get("DISPRICE"));
			}
		}

		return result;
	}

	@Override
	public Map<String, Object> buyRedEnvelope(String phone, String number,
			String size, String aGENTNO, String nickname, String openid)
			throws Exception {
		Map<String, Object> msg = this.changePrice(number, size);
		int amount = Integer.valueOf(String.valueOf(msg.get("PRICE")));

		// 先创建订单
		String id = GUID.generateGUID16();

		String SQL = " insert into SYS_REDENVELOPE_ORDERS (ID,WECHATCODE,WECHATNAME,MOBILENO,AMOUNT,QUANTITY,FLOWSIZE) values (?,?,?,?,?,?,?)";
		dao.execute(SQL, id, openid, nickname, phone, amount * 100, number,
				size);

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

		String context = "LLHB#" + msg.get("PRICE") + "Y";
		
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
		try {
			String result = httpUtilsNew.httpPost(url, params).trim();
			System.out.println(result);
			JSONObject object = new JSONObject(result);

			String data = object.getString("data");
			String status = object.getString("status");

			// 请求成功
			data = Base64Utils.getFromBASE64(data);
			logService.interfacelog(developercode, "channelUploadService", phone,
					status, data);
			String info[] = data.split("~");
			msg = new HashMap<String, Object>();
			msg.put("state", info[0]);
			String str = info[1];
			int index = str.indexOf(",");
			String ss = str.substring(index + 1);
			msg.put("msg", ss);
			System.out.println("-------------------------------------");
			System.out.println(msg);
			System.out.println("-------------------------------------");
			if (info[0].equals("1")) {

				String updateSql = " update SYS_REDENVELOPE_ORDERS t set t.ORDERSTATUS = 1, t.UPDATEDATE = sysdate , t.VALIDITYTIME =  to_date(to_char(sysdate+7,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss') where t.ID = '"
						+ id + "' ";
				msg.put("ID", id);
				msg.put("size", size);
				msg.put("number", number);
				String successStr = "尊敬的客户：您已成功购买" + size + "M省内通用流量红包("
						+ number + "个)，资费"+amount+"元，有效期为7天(不含购买当天)，过期未被领取的红包流量将自动兑换至您购买该红包的号码，请及时邀请好友来领取。【揭阳移动】";
				String successMSG = "尊敬的客户：您已成功购买" + size + "M省内通用流量红包("
						+ number + "个)，资费"+amount+"元，有效期为7天(不含购买当天)，过期未被领取的红包流量将自动兑换至您购买该红包的号码，请及时邀请好友来领取。";
				msg.put("successStr", successMSG);
				
				dao.execute(updateSql);
				channelDownmsgService(successStr,phone);
			} else {
				String msmMsg= "";
				String successStr = "尊敬的客户：您的" + size + "M省内通用流量红包("
						+ number + "个)购买失败。揭阳移动";
				channelDownmsgService(successStr,phone);
				String updateSql = " update SYS_REDENVELOPE_ORDERS t set t.ORDERSTATUS = 2,t.MEMO = '"
						+ msg
						+ "' ,t.UPDATEDATE = sysdate,t.VALIDITYTIME = sysdate+7 where t.ID = '"
						+ id + "' ";
				msg.put("ID", id);
				msg.put("size", size);
				msg.put("number", number);
				dao.execute(updateSql);
			}

			toBeShopManagerService.giveGoldByTcName("流量红包", aGENTNO, phone,
					"流量红包", info[0], info[1]);

			return msg;
		} catch (Exception e) {
			msg.put("status", "error");
			e.printStackTrace();
			return msg;
		}

	}

	public  void channelDownmsgService(String msg,String mobile) throws Exception {

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
				"eid="+developercode+"&sid=channelDownmsgService&token=" + //
				token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

		HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
		TreeMap<String, String> params = new TreeMap<String, String>();
	/*	params.put("account", "AC000007");
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
	public  String getFromBASE64(String s) {
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
	public List<Map<String, Object>> getSizesByNumber(String number)
			throws Exception {

		String sql = "select FLOWSIZE from SYS_REDENVELOPE_type where FLOWNUMBER="
				+ Integer.valueOf(number) + " order by FLOWSIZE ASC";

		return dao.queryForListMap(sql);
	}

	@Override
	public List<Map<String, Object>> getFlowTc() throws Exception {

		String sql = "select maintc,tcdescription  from  sys_flow_tc order by PRICE asc";

		// 1.全部套餐
		List<Map<String, Object>> tcs = dao.queryForListMap(sql);
		
		for (Map<String, Object> m : tcs) {

			m
					.put("TCDESCRIPTION", m.get("TCDESCRIPTION").toString().replaceAll("\n", "<br/>"));

		}
		

		/*// 2.转化后套餐
		List<FlowTC> flowTCs = new ArrayList<FlowTC>();

		FlowTC flowTC = null;

		// 3.去重复主套餐
		Map<String, String> distinct = new LinkedHashMap<String, String>();

		// 4、赠送的套餐
		List<String> giveList = new ArrayList<String>();

		// 5.去重复，有序
		for (Map<String, Object> m : tcs) {

			distinct
					.put(m.get("MAINTC").toString(), m.get("MAINTC").toString());

		}

		// 6.迭代主套餐
		Iterator<Entry<String, String>> it = distinct.entrySet().iterator();

		while (it.hasNext()) {

			Entry<String, String> entity = it.next();

			flowTC = new FlowTC();

			flowTC.setMainTC(entity.getKey().toString());

			giveList = new ArrayList<String>();

			// 7.迭代全部套餐
			for (Map<String, Object> m : tcs) {

				if (flowTC.getMainTC().equals(m.get("MAINTC").toString())) {

					if (m.get("GIVIETC") != null
							&& !"".equals(m.get("GIVIETC").toString())) {

						// 8。当前主套餐 赠送套餐
						giveList.add(m.get("GIVIETC").toString());

					}

				}

			}

			// 9.当前套餐装入 优惠套餐
			flowTC.setGiveTC(giveList);

			// 10.当前套餐装入 名称
			flowTCs.add(flowTC);

		}*/

		return tcs;

	}

	public Map<String, Object> handleFlowTc(String mainTC, String giveTC,
			String phone, String AGENTNO) throws Exception {

		Map<String, Object> msg = this.getFlowTCByName(mainTC, giveTC);

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

		String context = msg.get("INSTRUCTION").toString();
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
		msg = new HashMap<String, Object>();
		msg.put("state", temp[0]);
		msg.put("msg", temp[1]);

		// 推荐赠送
		toBeShopManagerService.giveGoldByTcName(mainTC, AGENTNO, phone, "流量套餐",
				temp[0], temp[1]);

		return msg;

	}

	private Map<String, Object> getFlowTCByName(String mainTC, String giveTC) {

		String sql = "";

		if ("".equals(giveTC)) {

			sql = "select INSTRUCTION from  sys_flow_tc where" + " maintc='"
					+ mainTC + "'";

		} else {

			sql = "select INSTRUCTION from  sys_flow_tc where" + " maintc='"
					+ mainTC + "' and GIVIETC='" + giveTC + "'";

		}

		return dao.queryForMap(sql);
	}

	@Override
	public boolean isStaffPhone(String phone) throws Exception {
		
		boolean result = false;
		
		//查询手机号是否在内部员工群组
		String sql = "select count(b.id) from sys_notice_group a, sys_notice_phone b  where a.type = 'RYLX' and a.id = b.groupid and b.phone = '"+phone+"'";
		
		int count = dao.queryForInteger(sql);
		
		if(count>0)
		{
			result=true;
		}
		return result;
	}


}
