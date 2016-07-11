package com.lenovocw.music.controller;




import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.model.ChangeRecord;
import com.lenovocw.music.service.PersonService;
import com.lenovocw.utils.JsonWriteUtil;

@Controller
@RequestMapping("/person")
public class Person {

	@Resource
	PersonService personService;
	
	
	/**
	 * 套餐使用情况
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "comboDetailsMSG.do")
	public void comboDetailsMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{ 

		//获用户信息
	    String phone=(String) request.getSession().getAttribute("phone");
		
		Map<String, Object> userMap=personService.getPersonInfo(phone);
		
		JsonWriteUtil.write(response,  userMap);
		
	}
	
	
	/**
	 * 我的账单
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "myBillMSG.do")
	public void myBillMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{ 

		Map<String, Object> msg= null;
		//获用户信息
	    String phone=(String) request.getSession().getAttribute("phone");
	    try
	    {
	    	msg=personService.getBillMSG(phone);
	    	msg.put("datastatus", 0);
	    }catch(Exception e)
	    {
	    	msg= new HashMap<String,Object>();
	    	msg.put("datastatus", 1);
	    }
		
		
		JsonWriteUtil.write(response,msg);
		
	}
	
	
	/**
	 * 我要做店长
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "toBeShopManager.do")
	public String toBeShopManager(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
		String key=(String) request.getSession().getAttribute("key");
		
		//获用户信息
	    String phone=(String) request.getSession().getAttribute("phone");
		
		request.setAttribute("key", key);
		
		request.setAttribute("phone", phone);

		return "person/toBeShopManager";
	}
	
	/**
	 * 我要做店长
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "handleShopManager.do")
	public void handleShopManager(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
	    String oldPassword=(String) request.getSession().getAttribute("randomPassword");
		
		String newPassword=request.getParameter("jym2");
		
		String phone=(String) request.getSession().getAttribute("phone");
		
		Map<String, Object> msg=personService.handleShopManager(phone,newPassword,oldPassword);
		
		JsonWriteUtil.write(response,msg);
	}
	
	/**
	 * 我要做店长
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getShopManagerMSG.do")
	public void getShopManagerMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{ 

		//获用户信息
	    String phone=(String) request.getSession().getAttribute("phone");
		
		Map<String, Object> msg=personService.getShopManagerMSG(phone);
		
		JsonWriteUtil.write(response,msg);
	}
	
	
	/**
	 * 我的下载
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "myDownloadMSG.do")
	public void myDownloadMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{ 

        String phone=(String) request.getSession().getAttribute("phone");
		
		List<Map<String, Object>> msg=personService.getMyDownloadMSG(phone);
		
		JsonWriteUtil.write(response,  msg);
		
	}
	
	/**
	 * 清除我的下载
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "cleanDowm.do")
	public void cleanDowm(HttpServletRequest request, HttpServletResponse response) throws Exception{ 

        String phone=(String) request.getSession().getAttribute("phone");
		
		int msg=personService.cleanDowm(phone);
		
		Map<String,Object> map=new HashMap<String, Object>();
		
		map.put("state",msg);
		
		JsonWriteUtil.write(response,map);
		
	}
	
	/**
	 * 我的优惠信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "myPrivilegeMSG.do")
	public void myPrivilegeMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{ 

		String phone= (String) request.getSession().getAttribute("phone");
		
		Map<String, Object> msg=personService.getMyPrivilegeByPhone(phone);
		System.out.println(msg);
		JsonWriteUtil.write(response, msg);
		
	}
	
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "loginOut.do")
	public void loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
		request.getSession().removeAttribute("key");
		
	    request.getSession().removeAttribute("phone");
	    
	    request.getSession().removeAttribute("randomPassword");
	    
	    Map<String, Object> msg=new HashMap<String, Object>();
	    
	    msg.put("state", "1");
		
		JsonWriteUtil.write(response, msg);
		
	}
	
	
	/**
	 * 个人详情
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "personMSG.do")
	public void personMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
		String phone=(String) request.getSession().getAttribute("phone");
		
		Map<String, Object> userMap=personService.getPersonInfo(phone);
	    userMap.put("jibie", "LV1");
	    userMap.put("phone", phone);
	    
	    JsonWriteUtil.write(response,  userMap);
	}

	
	/**我的金币-获取我的金币兑换记录
	 * @throws IOException 
	 * @throws JSONException */
	@RequestMapping(value = "myGoldForRecordAndGoldCount.do")
	public void myGoldForRecordAndGoldCount(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> prama = new HashMap<String, Object>();
		String phone = (String) request.getSession().getAttribute("phone");
		// 金币兑换记录
		List<Map<String, Object>> data = personService.queryForRecord(phone);
		
		ChangeRecord changeRecord = new ChangeRecord();
		JSONArray jsonary = new JSONArray();
		for(Map<String, Object> record : data){
			changeRecord.setPhone((String)record.get("PHONE"));
			changeRecord.setGold(Integer.valueOf(record.get("GOLDCOUNT").toString()));
			changeRecord.setTime((String)record.get("TIME"));
			changeRecord.setChangeContent((String)record.get("CHANGECONTENT"));
			
			jsonary.add(changeRecord);
		}
		// 账户可用金币和总金币
		Map<String, Object> goldCount = personService.queryGoldCount(phone);
		
		prama.put("records", jsonary.toString());
		prama.put("goldCount", goldCount);
		prama.put("phone", phone);
		
		JsonWriteUtil.write(response,  prama);
	}
	
	/**
	 * 金币明细
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "myGiveGoldRecord.do")
	public void myGiveGoldRecord(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String phone = (String) request.getSession().getAttribute("phone");
		// 金币明细
		List<Map<String, Object>> data = personService.queryForGiveGoldRecord(phone);
		
		JsonWriteUtil.write(response,  data);
	}
	
	/**金币兑换
	 * @throws JSONException 
	 * @throws IOException */
	@RequestMapping(value = "myGoldExchange.do")
	public void myGoldExchange(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException{
		Map<String, String> data = new HashMap<String, String>();
		
		String phone = (String) request.getSession().getAttribute("phone");
		String type = request.getParameter("type");
		
		Map<String, String> map = personService.isExcess(phone, type);
		String isCan = map.get("issuccess");
		String msg = map.get("msg");
		
		if(isCan.equals("0")){
			if(personService.isCanExchange(phone, type)){
				String issuccess = personService.goldExchange(phone, type);
				data.put("issuccess", issuccess);
				data.put("msg", msg);
			}else{
				data.put("issuccess", "0");
				data.put("msg", "您当前的金币不够兑换");
			}
		}else{
			data.put("issuccess", "0");
			data.put("msg", msg);
		}
		
		
		JsonWriteUtil.write(response,  data);
	}
}
