package com.lenovocw.music.controller;




import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.service.CallService;
import com.lenovocw.utils.JsonWriteUtil;



@Controller
@RequestMapping("/call")
public class Call {
	
	@Resource
	private CallService callService;
	
	
	/**
	 * 通话专区首页
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "indexMSG.do")
	public void indexMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{
	
		String phone=(String) request.getSession().getAttribute("phone");
		
		Map<String, Object> msg=callService.getPhoneMSG(phone);
		JsonWriteUtil.write(response, msg);
		
	}
	
	
	/**
	 * 短号网
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cornetNetMSG.do")
	public void cornetNetMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{
	
		String phone=(String) request.getSession().getAttribute("phone");
		
	    Map<String, Object> msgs=callService.getCornetNetMSG(phone);
	    
	    JsonWriteUtil.write(response, msgs);
		
	}
	
	
	/**
	 *亲友网成员
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "relativesNetMSG.do")
	public void relativesNetMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{
	
		String phone=(String) request.getSession().getAttribute("phone");
		
		/*phone="13421113474";*/
		//phone="13421113474"; //  13413980974   13421113474
	    Map<String, Object> msgs=callService.getRelativesNetMSG(phone);
	    
	    if((msgs.containsKey("state")) && (null !=((Map)msgs.get("state")).get("state")))
	    {
	    	if("2".equals(String.valueOf(((Map)msgs.get("state")).get("state"))))
	    	{
	    		msgs.put("currentPhone", phone);
	    	}
	    }

	    
	    JsonWriteUtil.write(response, msgs);
		
	}
	
	
	
	/**
	 *亲友网删除成员
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "relativesNetDel.do")
	public void relativesNetDel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		 String cornet= request.getParameter("cornet");
	
		 String phone=(String) request.getSession().getAttribute("phone");
		 
		 Map<String, Object> msgs=callService.relativesNetDel(cornet,phone);
		
		 JsonWriteUtil.write(response, msgs);
		
	}
	
	/**
	 *亲友网添加成员
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "relativesNetAdd.do")
	public void relativesNetAdd(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		 String phones= request.getParameter("phones");
		 
		 String phone=(String) request.getSession().getAttribute("phone");
		 
		 String AGENTNO= request.getParameter("AGENTNO");
		 
		 List<Map<String, Object>> msgs=callService.relativesNetAdd(phone,phones,AGENTNO);
		
		 JsonWriteUtil.write(response, msgs);
		
	}
	
	/**
	 *开通亲友网
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "relativesNetHandle.do")
	public void relativesNetHandle(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		 String phone=(String) request.getSession().getAttribute("phone");
		 
		 String AGENTNO= request.getParameter("AGENTNO");
		 
		 Map<String, Object> msgs=callService.relativesNetHandle(phone,AGENTNO);
		
		 JsonWriteUtil.write(response, msgs);
		
	}
	
	/**
	 *亲友网升级
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "relativesNetUpgrade.do")
	public void relativesNetUpgrade(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		 String phone=(String) request.getSession().getAttribute("phone");
		 
		 String AGENTNO= request.getParameter("AGENTNO");
		 
		 Map<String, Object> msgs=callService.relativesNetUpgrade(phone,AGENTNO);
		
		 JsonWriteUtil.write(response, msgs);
		
	}
	
	/**
	 *开通短号网
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cornetNetHandle.do")
	public void cornetNetHandle(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		 String addPhone=(String) request.getSession().getAttribute("phone");
		 
		 String targetPhone= request.getParameter("targetPhone");
		 
		 String AGENTNO= request.getParameter("AGENTNO");
		 
		 Map<String, Object> msgs=callService.cornetNetHandle(targetPhone,addPhone,AGENTNO);
		
		 JsonWriteUtil.write(response, msgs);
		
	}
	
	/**
	 *短号网更新套餐
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cornetNetUpdateTc.do")
	public void cornetNetUpdateTc(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		 String phone=(String) request.getSession().getAttribute("phone");
		 
		 String tc= request.getParameter("tc");
		 
		 Map<String, Object> msgs=callService.cornetNetUpdateTc(tc,phone);
		
		 JsonWriteUtil.write(response, msgs);
		
	}
	
	/**
	 *短号网更新短号
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cornetNetUpdateDh.do")
	public void cornetNetUpdateDh(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		 String phone=(String) request.getSession().getAttribute("phone");
		 
		 String dh= request.getParameter("dh");
		 
		 Map<String, Object> msgs=callService.cornetNetUpdateDh(dh,phone);
		
		 JsonWriteUtil.write(response, msgs);
		
	}
	
	
	
	/**
	 * 查询我发送的红包
	 * @throws IOException 
	 */
	@RequestMapping(value = "getMyshareCallRedpackage.do")
	public void getMyshareCallRedpackage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String phone = (String) request.getSession().getAttribute("phone");//电话
		
		//type=0 我发送的红包   type=1  我领取的红包
		String type =request.getParameter("type");
		Map<String, Object> msgs;
		try {
			
			msgs = callService.getMyshareCallRedpackage(phone,type);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			msgs= new HashMap<String,Object>();
			msgs.put("state", "-1");
		}
		
		
		JsonWriteUtil.write(response, msgs);
	}
	
}
