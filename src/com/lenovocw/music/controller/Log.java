package com.lenovocw.music.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lenovocw.music.service.LogService;
import com.lenovocw.utils.JsonWriteUtil;

@Controller
@RequestMapping("/Log")
public class Log {
	
	@Resource
	LogService logService;
	
	/**
	 * 操作日志
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "operationLog.do")
	public void operationLog(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
		Object object=request.getSession().getAttribute("phone");
		
		if(object!=null){
			
			String phone=object.toString();
			
			String url= request.getParameter("url");
			
			String name=request.getParameter("name");
			
			int state=logService.saveOperation(phone,name,url);
			
			 JsonWriteUtil.write(response,"{state:'"+state+"'}");
			
		}   
		
	}
	
	/**
	 * 下载日志
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "down.do")
	public void down(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		
		Object object=request.getSession().getAttribute("phone");
		
		if(object!=null){
			
			String phone=object.toString();
			
			String id= request.getParameter("id");

			String name= request.getParameter("name");
			
			int state=logService.down(phone,name,id);
			
			 JsonWriteUtil.write(response,"{state:'"+state+"'}");
			
		}   
		
	}
	
}
