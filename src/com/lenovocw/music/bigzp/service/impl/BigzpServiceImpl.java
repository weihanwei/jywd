package com.lenovocw.music.bigzp.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.lenovocw.music.bigzp.service.BigzpService;
import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.LogService;
import com.lenovocw.music.service.ToBeShopManagerService;
import com.lenovocw.music.util.HttpUtilsNew;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.MD5;
import com.lenovocw.utils.MD5More;
import com.lenovocw.utils.PropertyUtil;

/**
 * 
 * jywd
 * 
 * @author mohanwenz
 * 
 *         copyright:Copyright@2013 和跃科技有限公司 2015-9-7
 */

@Service(value = "BigzpServiceImpl")
public class BigzpServiceImpl implements BigzpService {
	@Resource(name = "jdbcDao")
	private JdbcDao dao;

	@Resource
	private LogService logService;

	@Resource
	private ToBeShopManagerService toBeShopManagerService;

	@Override
	public boolean getPhoneActivityBoolean(String phone, Map<String, Object> map) {
		// 查询规则
		int ACTIVITYTYPE = Integer.parseInt(map.get("ACTIVITYTYPE").toString());
		boolean s = false;
		switch (ACTIVITYTYPE) {
		// 类型（0：每天参加，1：每周可参加，2：每月可参加，3：每年可参加，4：一次性参加，5：不限）
		case 0:
			s = gz0(phone, map);
			break;
		case 1:
			s = gz1(phone, map);
			break;
		case 2:
			s = gz2(phone, map);
			break;
		case 3:
			s = gz3(phone, map);
			break;
		case 4:
			s = gz4(phone, map);
			break;
		case 5:
			s = true;
			break;
		default:
  
			System.out.println("没有该方案类型");

			break;
		}
		return s;
	}

	private boolean gz4(String phone, Map<String, Object> map) {
		String ddlSql = "select count(*) from CJ_LUCKYDRAW_PHONE t where t.phone='"
				+ phone + "' and t.ACTIVITYID='" + map.get("ACTIVITYID") + "'";
		int i = dao.queryForInteger(ddlSql);
		if (i > 0) {
			return false;
		} else {
			return true;
		}
	}

	private boolean gz3(String phone, Map<String, Object> map) {

		String ddlSql = "select count(*) from CJ_LUCKYDRAW_PHONE t where t.phone='"
				+ phone
				+ "' and to_char(t.inserttime,'yyyy') = to_char(sysdate,'yyyy') and t.ACTIVITYID='"
				+ map.get("ACTIVITYID") + "'";
		int i = dao.queryForInteger(ddlSql);
		int ACTIVITYNUM = Integer.parseInt(map.get("ACTIVITYNUM").toString());
		if (i >= ACTIVITYNUM) {
			return false;
		} else {
			return true;
		}

	}

	private boolean gz2(String phone, Map<String, Object> map) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		String stime = df.format(c.getTime());
		System.out.println(stime);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		String etime = df.format(c.getTime());
		System.out.println(etime);
		String ddlSql = "select count(*) from CJ_LUCKYDRAW_PHONE t where t.phone='"
				+ phone
				+ "' and t.inserttime between to_date('"
				+ stime
				+ "','yyyy-mm-dd') and to_date('"
				+ etime
				+ "','yyyy-mm-dd') and t.ACTIVITYID='"
				+ map.get("ACTIVITYID")
				+ "'";
		int i = dao.queryForInteger(ddlSql);
		int ACTIVITYNUM = Integer.parseInt(map.get("ACTIVITYNUM").toString());
		if (i >= ACTIVITYNUM) {
			return false;
		} else {
			return true;
		}
	}

	private boolean gz1(String phone, Map<String, Object> map) {
		// TODO Auto-generated method stub
		// 查询时间周期
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		String stime = df.format(cal.getTime());
		System.out.println(stime);
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		String etime = df.format(cal.getTime());
		System.out.println(etime);
		String ddlSql = "select count(*) from CJ_LUCKYDRAW_PHONE t where t.phone='"
				+ phone
				+ "' and t.inserttime between to_date('"
				+ stime
				+ "','yyyy-mm-dd') and to_date('"
				+ etime
				+ "','yyyy-mm-dd') and t.ACTIVITYID='"
				+ map.get("ACTIVITYID")
				+ "'";
		int i = dao.queryForInteger(ddlSql);
		int ACTIVITYNUM = Integer.parseInt(map.get("ACTIVITYNUM").toString());
		if (i >= ACTIVITYNUM) {
			return false;
		} else {
			return true;
		}

	}

	public boolean gz0(String phone, Map<String, Object> map) {
		String ddlSql = "select count(*) from CJ_LUCKYDRAW_PHONE t where  t.phone='"
				+ phone
				+ "' and t.ACTIVITYID='"
				+ map.get("ACTIVITYID")
				+ "' and to_char(t.inserttime,'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')";
		int i = dao.queryForInteger(ddlSql);
		int ACTIVITYNUM = Integer.parseInt(map.get("ACTIVITYNUM").toString());
		if (i >= ACTIVITYNUM) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 查询规则内容
	 */
	@Override
	public List<Map<String, Object>> getPhoneLinkeRules(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String ddlSql = "select * from CJ_LUCKYDRAW_GZ t where ACTIVITYID='"
				+ map.get("ACTIVITYID") + "'";

		return dao.queryForListMap(ddlSql);
	}

	/**
	 * 查询个数
	 */
	@Override
	public int getPhoneShowNum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String ddlSql = "select sum(t.SHOWNUM) from CJ_LUCKYDRAW_GZ t where ACTIVITYID='"
				+ map.get("ACTIVITYID") + "'";

		return dao.queryForInteger(ddlSql);
	}

	/**
	 * 办理抽奖
	 * 
	 * @throws JSONException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public String getHandleLuckyDraw(String phone, String pROJECTID,
			String aCTIVITYID, String aCTIVITYNAME, String aGENTNO)
			throws JSONException, UnsupportedEncodingException {
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
		String token = dateStr+"_"+secretkey+"_"+developercode;// 当前日期_接口密钥_开发商编号
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

		String context = pROJECTID;
		context = URLEncoder.encode(context, "UTF-8");

		// String mobile="1382296";

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

		// String
		// sign="account=AC000007&password=087c4c06-a7b9-46c3-bf7d-a5b200ebb054"+
		// "&toChannel=3&port=106573457303&context="+context+"&mobile="+
		// phone+"&timestamp="+dateStr+"&bossChannel=bsacLocal&" +
		// "bossPortal=VDJYbsac003";
		//
		// sign = MD5More.getMD5Str(sign);
		//
		// String p="toChannel=3-port=106573457303-context="+
		// context+"-mobile="+phone+"-timestamp="+
		// dateStr+"-bossChannel=bsacLocal-" +
		// "bossPortal=VDJYbsac003"+"-sign="+sign;

		params.put("params", p);

		String result = httpUtilsNew.httpPost(url, params).trim();
		System.out.println(result);
		JSONObject object = new JSONObject(result);

		String data = object.getString("data");
		String status = object.getString("status");

		data = Base64Utils.getFromBASE64(data);
		// ystem.out.println("status:"+status+",data:"+data);
		//插入记录，方便后面补给客户
		String insertloghandersql = "insert into CJ_HANDLE_LOG t (PHONE,HANDLEREQUEST,HANDLERETURN,ACTIVITYID,ACTIVITYNAME)values"
				+ "('"
				+ phone
				+ "','"
				+ p
				+ "','"
				+ result
				+ "','"
				+ aCTIVITYID + "','" + aCTIVITYNAME + "')";
		dao.execute(insertloghandersql);
		if (status.equals("1")) {
			String datastatus = data.split("~")[0];
			if (datastatus.equals("1")) {

				// 推荐赠送
				toBeShopManagerService
						.giveGoldByTcName("流量叠加包", aGENTNO, phone,"流量红包","1","办理成功！");

				return data;
			} else {
				// 推荐赠送
				toBeShopManagerService
						.giveGoldByTcName("流量叠加包", aGENTNO, phone,"流量红包","0","办理失败！");
				System.out.println("尊敬的客户：由于系统原因，优惠办理不成功，敬请留意我司后续优惠信息，感谢您的支持。");
				return data;
			}
		} else {
			System.out.println("尊敬的客户：由于系统原因，优惠办理不成功，敬请留意我司后续优惠信息，感谢您的支持。");
			return data;
		}

	}

	/**
	 * 抽奖判断
	 */
	@Override
	public Map<String, Object> getHandleLuckyDrawCJ(List pROBABILITYlist,
			String phone, HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 查询中奖结果
		Collections.shuffle(pROBABILITYlist);
		Random random = new Random();
		int result = random.nextInt(99);
		// map.put("PROBABILITYID",
		// PROBABILITYlist.get(result));
		// int PROBABILITYID = Integer.parseInt(pROBABILITYlist.get(result)
		// .toString().split(",")[1]);
		String RULESID = pROBABILITYlist.get(result).toString().split(",")[1];
		String sql = "select * from CJ_LUCKYDRAW_GZ t where t.RULESID='"
				+ RULESID + "'";
		List<Map<String, Object>> list = dao.queryForListMap(sql);
		// 查询有抽取份额 判断
		if (cjamount(list)) {
			// 查询冲中奖次数判断
			if (zjamount(list, phone)) {
				Map<String, Object> objMap = new HashMap<String, Object>();
				objMap.put("bl", "true");
				objMap.put("titleMsg", pROBABILITYlist.get(result).toString()
						.split(",")[2]);
				// 插入数据库活动记录
				String uuid = UUID.randomUUID().toString();
				dao.execute("insert into CJ_LUCKYDRAW_PHONE t (UUID,PHONE,ACTIVITYID,ACTIVITYNAME,LUCKYDRAWNAME,LUCKYDRAWID)values('"
						+ uuid
						+ "','"
						+ phone
						+ "','"
						+ list.get(0).get("ACTIVITYID")
						+ "','"
						+ list.get(0).get("ACTIVITYNAME")
						+ "','"
						+ list.get(0).get("RULESNAME") + "','" + RULESID + "')");
				// 放回获取值

				int PROBABILITYID = Integer.parseInt(pROBABILITYlist
						.get(result).toString().split(",")[0]);
				objMap.put("number", PROBABILITYID);
				request.getSession().setAttribute("RULESID", RULESID);
				return objMap;
			} else {
				getHandleLuckyDrawCJ(pROBABILITYlist, phone, request);
			}
		} else {
			getHandleLuckyDrawCJ(pROBABILITYlist, phone, request);
		}
		// request.getSession().setAttribute("RULESID",
		// PROBABILITYlist.get(result).toString().split(",")[1]);
		return null;
	}

	/**
	 * 查询冲中奖个人次数判断
	 * 
	 * @param phone
	 * @param string
	 */
	private boolean zjamount(List<Map<String, Object>> list, String phone) {
		String ACTIVITYID = list.get(0).get("ACTIVITYID").toString();
		String RULESID = list.get(0).get("RULESID").toString();
		// 判断最多中奖次数
		int ZJNUM = Integer.parseInt(list.get(0).get("ZJNUM").toString());
		boolean flag = false;
		// 中奖周期
		int ZJTYPE = Integer.parseInt(list.get(0).get("ZJTYPE").toString());
		switch (ZJTYPE) {// 1.每天2.每周，3.每月，4每年
		case 1:
			flag = zj1(ZJNUM, ACTIVITYID, RULESID, phone);
			break;
		case 2:
			flag = zj2(ZJNUM, ACTIVITYID, RULESID, phone);
			break;
		case 3:
			flag = zj3(ZJNUM, ACTIVITYID, RULESID, phone);
			break;
		case 4:
			flag = zj4(ZJNUM, ACTIVITYID, RULESID, phone);
			break;
		default:
			break;
		}

		return flag;

	}

	private boolean zj4(int zJNUM, String aCTIVITYID, String rULESID,
			String phone) {
		// TODO Auto-generated method stub
		String ddlSql = "select count(*) from CJ_LUCKYDRAW_PHONE t where to_char(t.inserttime,'yyyy') = to_char(sysdate,'yyyy') and t.ACTIVITYID='"
				+ aCTIVITYID + "' and t.LUCKYDRAWID='" + rULESID + "'";
		int cjnumphone = dao.queryForInteger(ddlSql);
		if (cjnumphone < zJNUM) {
			return true;
		} else {
			return false;
		}
	}

	private boolean zj3(int zJNUM, String aCTIVITYID, String RULESID,
			String phone) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		String stime = df.format(c.getTime());
		System.out.println(stime);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		String etime = df.format(c.getTime());
		System.out.println(etime);
		String ddlSql = "select count(*) from CJ_LUCKYDRAW_PHONE t where t.inserttime between to_date('"
				+ stime
				+ "','yyyy-mm-dd') and to_date('"
				+ etime
				+ "','yyyy-mm-dd') and t.ACTIVITYID='"
				+ aCTIVITYID
				+ "' and t.LUCKYDRAWID='"
				+ RULESID
				+ "' and t.phone='"
				+ phone
				+ "'";
		int cjnumphone = dao.queryForInteger(ddlSql);
		if (cjnumphone < zJNUM) {
			return true;
		} else {
			return false;
		}

	}

	private boolean zj2(int ZJNUM, String ACTIVITYID, String RULESID,
			String phone) {
		// TODO Auto-generated method stub

		// 查询时间周期
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		String stime = df.format(cal.getTime());
		System.out.println(stime);
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		String etime = df.format(cal.getTime());
		System.out.println(etime);
		String ddlSql = "select count(*) from CJ_LUCKYDRAW_PHONE t where t.inserttime between to_date('"
				+ stime
				+ "','yyyy-mm-dd') and to_date('"
				+ etime
				+ "','yyyy-mm-dd') and t.ACTIVITYID='"
				+ ACTIVITYID
				+ "' and t.LUCKYDRAWID='"
				+ RULESID
				+ "'  and t.phone='"
				+ phone + "'";

		int cjnumphone = dao.queryForInteger(ddlSql);
		if (cjnumphone < ZJNUM) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 前台
	 */
	private boolean zj1(int ZJNUM, String ACTIVITYID, String RULESID,
			String phone) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from CJ_LUCKYDRAW_PHONE t where t.aCTIVITYID='"
				+ ACTIVITYID
				+ "' and to_char(t.INSERTTIME,'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd') and t.LUCKYDRAWID='"
				+ RULESID + "' and t.phone='" + phone + "'";
		int cjnumphone = dao.queryForInteger(sql);
		if (cjnumphone < ZJNUM) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 抽奖限额
	 * 
	 * @param RULESID
	 * @return
	 */
	public boolean cjamount(List<Map<String, Object>> list) {

		boolean cj = false;

		if (list.size() > 0) {
			// 获取类型
			int CJTYPE = Integer.parseInt(list.get(0).get("CJTYPE").toString());
			int CJTYPENUM = Integer.parseInt(list.get(0).get("CJTYPENUM")
					.toString());
			String ACTIVITYID = list.get(0).get("ACTIVITYID").toString();
			String RULESID = list.get(0).get("RULESID").toString();
			switch (CJTYPE) {
			case 1:// 每日抽取管理
				cj = cj1(CJTYPENUM, ACTIVITYID, RULESID);
				break;
			case 2:// 每周抽取管理
				cj = cj2(CJTYPENUM, ACTIVITYID, RULESID);
				break;
			case 3:// 每月抽取管理
				cj = cj3(CJTYPENUM, ACTIVITYID, RULESID);
				break;
			case 4:// 每年
				cj = cj4(CJTYPENUM, ACTIVITYID, RULESID);
				break;
			default:
				System.out.println("没有该方案类型");
				break;
			}
		}
		return cj;
	}

	private boolean cj4(int cJTYPENUM, String aCTIVITYID, String RULESID) {

		String ddlSql = "select count(*) from CJ_LUCKYDRAW_PHONE t where to_char(t.inserttime,'yyyy') = to_char(sysdate,'yyyy') and t.ACTIVITYID='"
				+ aCTIVITYID + "' and t.LUCKYDRAWID='" + RULESID + "'";
		int cjnumphone = dao.queryForInteger(ddlSql);
		if (cjnumphone < cJTYPENUM) {
			return true;
		} else {
			return false;
		}

	}

	private boolean cj3(int cJTYPENUM, String aCTIVITYID, String RULESID) {

		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		String stime = df.format(c.getTime());
		System.out.println(stime);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		String etime = df.format(c.getTime());
		System.out.println(etime);
		String ddlSql = "select count(*) from CJ_LUCKYDRAW_PHONE t where t.inserttime between to_date('"
				+ stime
				+ "','yyyy-mm-dd') and to_date('"
				+ etime
				+ "','yyyy-mm-dd') and t.ACTIVITYID='"
				+ aCTIVITYID
				+ "' and t.LUCKYDRAWID='" + RULESID + "'";
		int cjnumphone = dao.queryForInteger(ddlSql);
		if (cjnumphone < cJTYPENUM) {
			return true;
		} else {
			return false;
		}
	}

	private boolean cj2(int cJTYPENUM, String aCTIVITYID, String RULESID) {
		// TODO Auto-generated method stub
		// 查询时间周期
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		String stime = df.format(cal.getTime());
		System.out.println(stime);
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		String etime = df.format(cal.getTime());
		System.out.println(etime);
		String ddlSql = "select count(*) from CJ_LUCKYDRAW_PHONE t where t.inserttime between to_date('"
				+ stime
				+ "','yyyy-mm-dd') and to_date('"
				+ etime
				+ "','yyyy-mm-dd') and t.ACTIVITYID='"
				+ aCTIVITYID
				+ "' and t.LUCKYDRAWID='" + RULESID + "'";
		int cjnumphone = dao.queryForInteger(ddlSql);

		if (cjnumphone < cJTYPENUM) {
			return true;
		} else {
			return false;
		}
	}

	private boolean cj1(int CJTYPENUM, String aCTIVITYID, String RULESID) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from CJ_LUCKYDRAW_PHONE t where t.aCTIVITYID='"
				+ aCTIVITYID
				+ "' and to_char(t.INSERTTIME,'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd') and t.LUCKYDRAWID='"
				+ RULESID + "'";
		int cjnumphone = dao.queryForInteger(sql);
		if (cjnumphone < CJTYPENUM) {
			return true;
		} else {
			return false;
		}

	}
}
