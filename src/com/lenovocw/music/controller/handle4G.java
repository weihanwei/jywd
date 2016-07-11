package com.lenovocw.music.controller;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.service.Handle4GService;
import com.lenovocw.music.service.IndexService;
import com.lenovocw.utils.JsonWriteUtil;


@Controller
@RequestMapping("/handle4G")
public class handle4G {
	
	@Resource
	Handle4GService handle4gService;
	
	@Resource
	IndexService indexService;
	
	
	/**
	 * 办理首页信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "indexMSG.do")
	public void indexMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取banner
		String phone=(String) request.getSession().getAttribute("phone");
		Map<String, Object> msg=handle4gService.get4GMSG(phone);
		JsonWriteUtil.write(response, msg);
		
	}
	
	/**
	 * 办理首页信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "recommend4G.do")
	public void recommend4G(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取banner
		String phone=(String) request.getSession().getAttribute("phone");
		Map<String, Object> msg=handle4gService.recommend4G(phone);
		JsonWriteUtil.write(response, msg);
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id=request.getParameter("id");
		Map<String, Object> map=handle4gService.getTcByID(id);
		request.setAttribute("map", map);
		return "4g/detail"; 
		
	}
	
	/**
	 * 跟据类型查询套餐
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getTcByType.do")
	public void getTcByType(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String type=request.getParameter("type");
		List<Map<String, Object>> handle4gTc=handle4gService.getTcByType(type);
		JsonWriteUtil.write(response, handle4gTc);
	}
	
	/**
	 * 办理
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "handle.do")
	public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id=request.getParameter("id");
		String phone=(String) request.getSession().getAttribute("phone");
		String AGENTNO= request.getParameter("AGENTNO");
		Map<String, Object> msg=handle4gService.handle(id,phone,AGENTNO);
		
		JsonWriteUtil.write(response, msg);
	}
	
}
