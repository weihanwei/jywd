package com.lenovocw.music.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.service.IndexService;
import com.lenovocw.utils.JsonWriteUtil;

@Controller
@RequestMapping("/index")
public class Index {

	@Resource
	IndexService indexService;
	
	/**
	 * 跳转首页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getBanner.do")
	public void getBanner(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
	    
		//获取banner
	    List<Map<String, Object>> bannerListMap=indexService.getBanner();
	    request.setAttribute("bannerListMap",bannerListMap);
	    
	    JsonWriteUtil.write(response,bannerListMap);
	}
	
	/**
	 * 跳转首页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getBannerByArea.do")
	public void getBannerByArea(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
	    
		String area=request.getParameter("area");
		//获取banner
	    List<Map<String, Object>> bannerListMap=indexService.getBannerByArea(area);
	    request.setAttribute("bannerListMap",bannerListMap);
	    
	    JsonWriteUtil.write(response,bannerListMap);
	}
	
	/**
	 * app功能列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "appListMap.do")
	public void appListMap(HttpServletRequest request, HttpServletResponse response) throws Exception{ 

	    //获取app功能
	    List<Map<String, Object>> appListMap=indexService.getAppFunction();
	    
	    JsonWriteUtil.write(response,  appListMap);
	    
	}
	
	/**
	 * 个人信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "indexMSG.do")
	public void indexMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{ 

	    
	    //获用户信息
	    String phone=(String) request.getSession().getAttribute("phone");
		
		Map<String, Object> userMap=indexService.getPersonInfo(phone);
	    userMap.put("jibie", "LV1");
	    userMap.put("phone", phone);
	    
	    JsonWriteUtil.write(response,  userMap);
	    
	}
	
	
	/**
	 * 跳转APP下载列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "appDownloadMSG.do")
	public void appDownloadMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
	    
	    //下载app功能
	    List<Map<String, Object>> downAppListMap=indexService.getDownAppFunction();
	 
	    JsonWriteUtil.write(response,  downAppListMap);
	}
	
	
	/**
	 * 跳转查询页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "searchMSG.do")
	public void searchMSG(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
		String type=request.getParameter("type");
		
		String keyword=request.getParameter("keyword").toUpperCase();
		
	    List<Map<String, Object>> downAppListMap=indexService.getSearchMSG(type,keyword);
	 
	    JsonWriteUtil.write(response,  downAppListMap);
	}
	
	/**
	 * 查询是否已指引
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "isFirstLogin.do")
	public void isFirstLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
	    
		 //获用户信息
	    String phone=(String) request.getSession().getAttribute("phone");

	    //判断是否首次登录
	    Map<String, Object> msg=indexService.isFirstLogin(phone);
	    
	    JsonWriteUtil.write(response,msg);
	}
	
	
	/**
	 * 保存已指引状态
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "isGuide.do")
	public void isGuide(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
	    
		 //获用户信息
	    String phone=(String) request.getSession().getAttribute("phone");

	    //判断是否首次登录
	    int state=indexService.isGuide(phone);
	    
	    Map<String, Object> msg=new HashMap<String, Object>();
	    
	    msg.put("state", state);
	    
	    JsonWriteUtil.write(response,msg);
	}
	
	/**
	 *删除通知
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "delNotice.do")
	public void delNotice(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		String phone=(String) request.getSession().getAttribute("phone");
		String id=request.getParameter("id");
		String nid=request.getParameter("nid");

	    //判断是否首次登录
	    int state=indexService.delNotice(id, nid, phone);
	    
	    Map<String, Object> msg=new HashMap<String, Object>();
	    
	    msg.put("state", state);
	    
	    JsonWriteUtil.write(response,msg);
	}
	
	/**
	 * 收藏通知
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "collectNotice.do")
	public void collectNotice(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
	    
		String nid=request.getParameter("nid");
		String phone=(String) request.getSession().getAttribute("phone");

	    int state=indexService.collectNotice(nid, phone);
	    
	    Map<String, Object> msg=new HashMap<String, Object>();
	    
	    msg.put("state", state);
	    
	    JsonWriteUtil.write(response,msg);
	}
	
	/**
	 * 精品下载模块，IOS下载查询详情页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "appIosDownDetail.do")
	public String messagesDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{ 

	    String id=request.getParameter("id");
		
	    
	    Map<String, Object> downData = indexService.getDownAppById(id);
		
	    
	    request.setAttribute("downData",downData);
	    
		return "appDownload/downOut";
	    
		
	}
	
}
