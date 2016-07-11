package com.lenovocw.music.bigzp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Array;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.lenovocw.music.bigzp.service.BigzpService;
import com.lenovocw.music.dao.JdbcDao;
import com.lenovocw.music.service.CallService;
import com.lenovocw.music.service.Handle4GService;
import com.lenovocw.music.service.IndexService;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.JsonWriteUtil;

@Controller
@RequestMapping("/bigzp")
public class bigzpController {
	@Resource
	private IndexService indexService;
	@Resource(name = "jdbcDao")
	private JdbcDao dao;
	@Resource
	private BigzpService bigzpService;

	// 抽奖
	@RequestMapping(value = "getLuckyDraw.do")
	public void getLuckyDraw(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.getSession().setAttribute("RULESID",
				"");
		// 获取用户雨的
		Map<String, Object> map = new HashMap<String, Object>();
		// 用户号码
		String phone = (String) request.getSession().getAttribute("phone");
		String ACTIVITYID = request.getParameter("ACTIVITYID");
		// 查询在进行的活动
		String sqlLuckyDrawGZ = "select * from CJ_LUCKYDRAW_HD t where sysdate between t.starttime and t.endtime and isshow = 0 and ACTIVITYID='"
				+ ACTIVITYID + "'";
		List<Map<String, Object>> LuckyDrawGZ = dao
				.queryForListMap(sqlLuckyDrawGZ);
		if (LuckyDrawGZ.size() > 0) {// 判断是否有活动可参加
			Map<String, Object> phoneinfo = indexService.getPersonInfo(phone);
			// 参加活动需要的金额
			double ACTIVITYAMOUNT = Double.parseDouble(LuckyDrawGZ.get(0)
					.get("ACTIVITYAMOUNT").toString());
			// 用户余额
			double balance = 0;
			String balancestr1 = phoneinfo.get("hf").toString();
			if (!balancestr1.equals("----")) {// 判断金额是否不足
				balance = Double.parseDouble(balancestr1) / 100;
				if (balance >= ACTIVITYAMOUNT) {// 用户余额>=参加活动需要的金额
					// 获取活动规则--true：可参加，false：不可参加
					if (bigzpService.getPhoneActivityBoolean(phone,
							LuckyDrawGZ.get(0))) {
						// 查询个数
						List<Map<String, Object>> LinkeRuleslist = bigzpService
								.getPhoneLinkeRules(LuckyDrawGZ.get(0));
						 int PhoneShowNum = bigzpService.getPhoneShowNum(LuckyDrawGZ.get(0));
						// 获取抽取内容
						 String[] LinkeRulesview = new String[PhoneShowNum];
						 //String[] LinkeRulesviewcolor = new String[PhoneShowNum];
						List PROBABILITYlist = new ArrayList();
//						int PROBABILITYnum = 0;
						for (Map<String, Object> LinkeRules : LinkeRuleslist) {
							 String RULESNAME = LinkeRules.get("RULESNAME")
							 .toString();
							// String COLOR =
							// LinkeRules.get("COLOR").toString();
							String[] ACTIVITYLINKEtemp = LinkeRules
									.get("ACTIVITYLINKE").toString().split(",");
							 for (String ACTIVITYLINKE : ACTIVITYLINKEtemp) {
							 int ACTIVITYLINKEnumber = Integer
							 .parseInt(ACTIVITYLINKE);
							 LinkeRulesview[ACTIVITYLINKEnumber-1] = RULESNAME;
							 //LinkeRulesviewcolor[ACTIVITYLINKEnumber] = COLOR;
							 }
							String RULESID = LinkeRules.get("RULESID").toString();
							int PROBABILITY = Integer.parseInt(LinkeRules.get(
									"PROBABILITY").toString());
							String titleMsg=LinkeRules.get("MSG").toString();
							for (int j = 0; j < PROBABILITY; j++) {
								PROBABILITYlist.add(
										ACTIVITYLINKEtemp[0]+","+RULESID+","+titleMsg);
//								PROBABILITYnum = PROBABILITYnum + 1;
							}
						}
						/*
						 * String LinkeRulesName = ""; String
						 * LinkeRuleslistcolor = ""; for (int i = 0; i <
						 * LinkeRulesview.length; i++) { LinkeRulesName =
						 * LinkeRulesName + LinkeRulesview[i] + ",";
						 * LinkeRuleslistcolor = LinkeRuleslistcolor +
						 * LinkeRulesviewcolor[i] + ","; }
						 * map.put("LinkeRuleslist", LinkeRulesName.substring(0,
						 * LinkeRulesName.length() - 1));
						 * map.put("LinkeRuleslistcolor", LinkeRuleslistcolor
						 * .substring(0, LinkeRuleslistcolor.length() - 1));
						 */
						//抽奖运算
						map=bigzpService.getHandleLuckyDrawCJ(PROBABILITYlist,phone,request);
						
						// 查询中奖结果
//						Collections.shuffle(PROBABILITYlist);
//						Random random = new Random();
//						int result = random.nextInt(99);
//						// map.put("PROBABILITYID",
//						// PROBABILITYlist.get(result));
//						int PROBABILITYID=Integer.parseInt(PROBABILITYlist.get(result).toString().split(",")[0]);
//						request.getSession().setAttribute("RULESID",
//								PROBABILITYlist.get(result).toString().split(",")[1]);
//						// bigzpService.getphoneActivityhandle();
//						map.put("bl", "true");
//						map.put("titleMsg",PROBABILITYlist.get(result).toString().split(",")[2]);
//						// 插入数据库活动记录
//						dao.execute("insert into CJ_LUCKYDRAW_PHONE t (PHONE,ACTIVITYID,ACTIVITYNAME,LUCKYDRAWNAME,LUCKYDRAWID)values('"+phone+"','"+ACTIVITYID+"','"+LuckyDrawGZ.get(0).get("ACTIVITYNAME")+"','"+LinkeRulesview[PROBABILITYID]+"','"+PROBABILITYlist.get(result).toString().split(",")[1]+"')");
//						// 放回获取值
//						map.put("number", PROBABILITYID);
					} else {
						System.out.println("您今天的抽奖机会已用完，请明天再来！");
						map.put("msg", "您今天的抽奖机会已用完，请明天再来！");
						map.put("bl", "false");
					}
				} else {
					System.out.println("尊敬的客户，您当前余额不足抵扣套餐费用，无法办理业务，请充值后再参加活动。");
					map.put("msg", "尊敬的客户，您当前余额不足抵扣套餐费用，无法办理业务，请充值后再参加活动。");
					map.put("bl", "false");
				}
			} else {
				System.out.println("尊敬的客户，您当前余额不足抵扣套餐费用，无法办理业务，请充值后再参加活动。");
				map.put("msg", "尊敬的客户，您当前余额不足抵扣套餐费用，无法办理业务，请充值后再参加活动。");
				map.put("bl", "false");
			}

		} else {
			System.out.println("尊敬的客户，活动已结束！");
			map.put("msg", "尊敬的客户，活动已结束！");
			map.put("bl", "false");
		}

		String str = JSONObject.toJSONString(map);
		System.out.println(str);
		response.getOutputStream().write(str.getBytes("UTF-8"));
		response.setContentType("text/json; charset=UTF-8");

	}

	/**
	 * 查询转盘内容
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getLinkeRulesview.do")
	public void getLinkeRulesview(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ACTIVITYID = request.getParameter("ACTIVITYID");
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询在进行的活动
		String sqlLuckyDrawGZ = "select * from CJ_LUCKYDRAW_HD t where sysdate between t.starttime and t.endtime and isshow = 0 and ACTIVITYID='"
				+ ACTIVITYID + "'";

		List<Map<String, Object>> LuckyDrawGZ = dao
				.queryForListMap(sqlLuckyDrawGZ);
		if (LuckyDrawGZ.size() > 0) {// 判断是否有活动可参加
			map.put("msg", "true");
			map.put("ACTIVITYNAME", LuckyDrawGZ.get(0).get("ACTIVITYNAME"));
			List<Map<String, Object>> LinkeRuleslist = bigzpService
					.getPhoneLinkeRules(LuckyDrawGZ.get(0));
			int PhoneShowNum = bigzpService.getPhoneShowNum(LuckyDrawGZ.get(0));
			String[] LinkeRulesview = new String[PhoneShowNum];
			String[] LinkeRulesviewcolor = new String[PhoneShowNum];
			List PROBABILITYlist = new ArrayList();
			int PROBABILITYnum = 0;
			for (Map<String, Object> LinkeRules : LinkeRuleslist) {
				String RULESNAME = LinkeRules.get("RULESNAME").toString();
				String COLOR = LinkeRules.get("COLOR").toString();
				String[] ACTIVITYLINKEtemp = LinkeRules.get("ACTIVITYLINKE")
						.toString().split(",");
				for (String ACTIVITYLINKE : ACTIVITYLINKEtemp) {
					int ACTIVITYLINKEnumber = Integer.parseInt(ACTIVITYLINKE);
					LinkeRulesview[ACTIVITYLINKEnumber - 1] = RULESNAME;
					LinkeRulesviewcolor[ACTIVITYLINKEnumber - 1] = COLOR;
				}

			}
			String LinkeRulesName = "";
			String LinkeRuleslistcolor = "";
			for (int i = 0; i < LinkeRulesview.length; i++) {
				LinkeRulesName = LinkeRulesName + LinkeRulesview[i] + ",";
				LinkeRuleslistcolor = LinkeRuleslistcolor
						+ LinkeRulesviewcolor[i] + ",";
			}
			map.put("LinkeRuleslist", LinkeRulesview);
			map.put("LinkeRuleslistcolor", LinkeRulesviewcolor);
			
		} else {
			map.put("msg", "false");
		}
		String str = JSONObject.toJSONString(map);
		System.out.println(str);
		response.getOutputStream().write(str.getBytes("UTF-8"));
		response.setContentType("text/json; charset=UTF-8");
	}
	//抽奖办理
	@RequestMapping(value = "getHandleLuckyDraw.do")
	public void getHandleLuckyDraw(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String AGENTNO=request.getParameter("AGENTNO");
		// 获取用户雨的
		// 用户号码
		String phone = (String) request.getSession().getAttribute("phone");
		// 查询用户结果
		String RULESID = (String) request.getSession().getAttribute("RULESID");
		Map<String, Object> map = new HashMap<String, Object>();
		//开始办理
		//查询办理方案
		String sql="select * from CJ_LUCKYDRAW_GZ t where t.RULESID='"+RULESID+"'";
		List<Map<String,Object>> gzlist= dao.queryForListMap(sql);
		if(gzlist.size()>0){
			//获取办理方案
			String PROJECTID=gzlist.get(0).get("PROJECTID").toString();
			String ACTIVITYID=gzlist.get(0).get("ACTIVITYID").toString();
			String ACTIVITYNAME=gzlist.get(0).get("ACTIVITYNAME").toString();
			String result=bigzpService.getHandleLuckyDraw(phone,PROJECTID,ACTIVITYID,ACTIVITYNAME,AGENTNO);
			String datastatus = result.split("~")[0];
			//返回接口内容
			if(datastatus.equals("1")){
				dao.execute("update CJ_LUCKYDRAW_PHONE t set  t.STRUT=1 where t.PHONE='"+phone+"' and t.ACTIVITYID='"+ACTIVITYID+"' and to_char(sysdate,'yyyy-mm-dd')= to_char(INSERTTIME,'yyyy-mm-dd') ");
				map.put("msg",result.split("~")[1]);
			}else{
				dao.execute("update CJ_LUCKYDRAW_PHONE t set  t.STRUT=0 where t.PHONE='"+phone+"' and t.ACTIVITYID='"+ACTIVITYID+"' and to_char(sysdate,'yyyy-mm-dd')= to_char(INSERTTIME,'yyyy-mm-dd') ");
				map.put("msg", result.split("~")[1]);
			}
		} 
		String str = JSONObject.toJSONString(map);
		System.out.println(str);
		response.getOutputStream().write(str.getBytes("UTF-8"));
		response.setContentType("text/json; charset=UTF-8");
	}
	
	// 查询抽奖记录
	@RequestMapping(value = "getLuckyDrawHistory.do")
	public void getLuckyDrawHistory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取用户雨的
		// 用户号码
		String phone = (String) request.getSession().getAttribute("phone");
		// 查询用户抽奖记录
		
	}

	// 查询用户抽奖次数--当天
	@RequestMapping(value = "getphoneLuckyDrawDay.do")
	public void getphoneLuckyDrawDay(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取用户雨的
		// 用户号码
		String phone = (String) request.getSession().getAttribute("phone");
		// 查询用户当天抽奖次数
		// 查询抽奖规则

	}

}
