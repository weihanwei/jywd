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

import com.lenovocw.music.service.NoticeService;
import com.lenovocw.utils.JsonWriteUtil;



@Controller
@RequestMapping("/notice")
public class Notice {
	
	@Resource
	private NoticeService noticeService;
	
	/**
	 * 跳转通知页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "messagesMSG.do")
	public void messagesMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
	    String phone=(String) request.getSession().getAttribute("phone");
		
	    List<Map<String, Object>> messages=noticeService.getMessages(phone);
		
		JsonWriteUtil.write(response,  messages);
		
	}
	
	
	/**
	 * 功能：查询用户没读取的重要数据数以及最新的消息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "noReadMessagesMSG.do")
	public void noReadMessagesMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
	    String phone=(String) request.getSession().getAttribute("phone");
		
	    Map<String, Object> noReadMeaagesInfo=noticeService.noReadMessagesMSG(phone);
	    
	    Map<String, Object> total = noticeService.getMessagesCount(phone);
	    
	    noReadMeaagesInfo.put("total", total);
		
		JsonWriteUtil.write(response,  noReadMeaagesInfo);
		
	}
	
	
	/**
	 * 通过id查找通知
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getMessagesById.do")
	public void getMessagesById(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
	    String phone=(String) request.getSession().getAttribute("phone");
		
	    Map<String, Object> message=noticeService.getMessagesById(phone);
	    
	    

	    
		
		JsonWriteUtil.write(response,  message);
		
	}
	
	
	/**
	 * 通过类型查找通知  type为空表示查询全部数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getMessagesByType.do")
	public void getMessagesByType(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		Map<String, Object> map = new HashMap<String, Object>();
		
	    String phone=(String) request.getSession().getAttribute("phone");
	    
	    String type=request.getParameter("type");
	    
	    List<Map<String, Object>> messages=null;
		
	    if("myColl".equals(type))
	    {
	    	 //查询我的收藏消息列表
	    	 messages=noticeService.getMyCollMessages(phone);
	    }else
	    {
	    	 messages=noticeService.getMessagesByType(phone,type);
	    }
	    
	    
	    
	    Map<String, Object> total = noticeService.getMessagesCount(phone);
		
	    map.put("messages", messages);
	    map.put("total", total);
	    
		JsonWriteUtil.write(response,map);
	}
	
	
	
	/**
	 * 功能：查询消息分类列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getMessagesType.do")
	public void getMessagesType(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		Map<String, Object> map = new HashMap<String, Object>();
		
	    String phone=(String) request.getSession().getAttribute("phone");
	    
		
	    List<Map<String, Object>> typeInfoList=noticeService.getMessagesType(phone);
	    
	    
	    Map<String, Object> total = noticeService.getMessagesCount(phone);
	    
	    int collCount = noticeService.countCollMessage(phone);
	    total.put("collCount", collCount);
	    map.put("typeInfoList", typeInfoList);
	    map.put("total", total);
	    
		JsonWriteUtil.write(response,map);
	}
	
	/**
	 * 跳转通知详情页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "messagesDetail.do")
	public String messagesDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{ 

	    String id=request.getParameter("id");
		
	    String phone=(String) request.getSession().getAttribute("phone");
	    
	    noticeService.read(id,phone);
		
	    Map<String, Object> msg=noticeService.getMessagesById(id);
	    
	    request.setAttribute("msg",msg);
	    
		return "index/messagesDetail";
	    
		
	}
	
	/**
	 * 收藏
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "collectNotice.do")
	public void collectNotice(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		
		String id = request.getParameter("id");
		
		
		//flag 1：取消收藏  flag：0 收藏
		String flag=request.getParameter("flag");
		
		
		
		
		String phone=(String) request.getSession().getAttribute("phone");
		
		if("1".equals(flag))
		{
			//录入收藏记录
			
			try{
				noticeService.cancelCollectNotices(id,phone);
				map.put("state", 1);
				map.put("msg", "");
			}catch(Exception e)
			{
				e.printStackTrace();
				map.put("state", 0);
				map.put("msg", "操作失败了,请稍后重试!");
			}

			
		}else
		{
			Map<String, Object> noticeInfo =noticeService.getMessagesById(id);
			
			if(null !=noticeInfo && noticeInfo.containsKey("ID"))
			{
				//录入收藏记录
				
				noticeService.collectNotices(id,phone);
				map.put("state", 1);
				map.put("msg", "");
			}else
			{
				map.put("state", 0);
				map.put("msg", "消息已被删除,收藏失败!");
			}
		}
		

		 
		JsonWriteUtil.write(response, map);
	}	
	
	/**删除*/
	@RequestMapping(value = "delNotice.do")
	public void delNotice(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		String id = request.getParameter("id");
		
		
		String phone=(String) request.getSession().getAttribute("phone");

		try
		{
			noticeService.delById(id, phone);
			map.put("state", 1);
		}catch(Exception e)
		{
			map.put("state", 0);
			e.printStackTrace();
		}
		
		

		JsonWriteUtil.write(response, map);
	}
	
	/**
	 * 功能：首页顶部未读消息总数
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "readMessagesMSG.do")
	public void readMessagesMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
	    String phone=(String) request.getSession().getAttribute("phone");
		
	    Map<String, Object> noReadMeaagesInfo=new HashMap<String,Object>();
	    
	    Map<String, Object> total = noticeService.getMessagesCount(phone);
	    
	    noReadMeaagesInfo.put("total", total);
		
		JsonWriteUtil.write(response,  noReadMeaagesInfo);
		
	}
}
