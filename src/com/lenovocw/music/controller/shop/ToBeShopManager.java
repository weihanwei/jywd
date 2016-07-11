package com.lenovocw.music.controller.shop;




import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.model.Sales;
import com.lenovocw.music.service.ToBeShopManagerService;
import com.lenovocw.utils.ExoCoder;
import com.lenovocw.utils.JsonWriteUtil;


@Controller
@RequestMapping("/toBeShopManager")
public class ToBeShopManager {
	
	@Resource
	ToBeShopManagerService toBeShopManagerService;
   
	/**
	 * 获取商品类型
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getType.do")
	public void getType(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
		String type=request.getParameter("type");
		
		List<Map<String, Object>> msg=toBeShopManagerService.getType(type);
		
	    JsonWriteUtil.write(response,  msg);
		
	}
	
	/**
	 * 获取商品
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getTc.do")
	public void getTc(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
		String name=request.getParameter("name");
		
		List<Map<String, Object>> msg=toBeShopManagerService.getTc(name);
		
	    JsonWriteUtil.write(response,  msg);
		
	}
	
	
	/**
	 * 访问商品
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "access.do")
	public void access(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
		String AGENTNO=request.getParameter("AGENTNO");
		
		String tcid=request.getParameter("tcid");;
		
		Map<String, Object> msgs=toBeShopManagerService.access(AGENTNO,tcid);
		
	    JsonWriteUtil.write(response,  msgs);
		
	}
	
	/**
	 * 统计
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "mySales.do")
	public void mySales(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
		String phone=(String) request.getSession().getAttribute("phone");
		
		String starTime=request.getParameter("starTime");
		
		String endTime=request.getParameter("endTime");
		
		List<Sales> msgs=toBeShopManagerService.mySales(phone,starTime,endTime);
		
	    JSONObject object=new JSONObject();
		object.put("msgs", msgs);
		
	    JsonWriteUtil.write(response,  object);
		
	}
	
	/**
	 * 统计
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "mysalesDetail.do")
	public void mysalesDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
	
		
		String name=request.getParameter("name");
		
		String AGENTNO=request.getParameter("AGENTNO");
		
		List<Map<String,Object>> msgs=toBeShopManagerService.mysalesDetail(name,AGENTNO);
		
	    JsonWriteUtil.write(response,  msgs);
		
	}
	
	/**
	 * 统计
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "MsmRecommend.do")
	public void MsmRecommend(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
		String bephone=request.getParameter("phone");
		
		
		String tcid=request.getParameter("tcid");
		
		
		String AGENTNO=request.getParameter("AGENTNO");
		
		
		String sendphone=(String) request.getSession().getAttribute("phone");
		
		Map<String, Object> msgs=toBeShopManagerService.MsmRecommend(bephone,tcid,AGENTNO,sendphone);
	    
		
	    JsonWriteUtil.write(response, msgs);
		
	}
	
	/**
	 * 按手机号查询客户信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "queryUser.do")
	public void queryUser(HttpServletRequest request, HttpServletResponse response) throws Exception{ 

		//获用户信息
	    String phone=request.getParameter("phone");
		
		Map<String, Object> userMap=toBeShopManagerService.queryUser(phone);
		
		JsonWriteUtil.write(response,  userMap);
		
	}
	
	/**
	 * 按手机号查询客户信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "UpdateMsmRecommend.do")
	public void UpdateMsmRecommend(HttpServletRequest request, HttpServletResponse response) throws Exception{ 

	
		toBeShopManagerService.UpdateMsmRecommend();
		
		String state="{state:'1'}";
		
		JsonWriteUtil.write(response,state);
		
	}
}
