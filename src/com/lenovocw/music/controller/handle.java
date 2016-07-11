package com.lenovocw.music.controller;




import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.service.HandleService;
import com.lenovocw.utils.JsonWriteUtil;


@Controller
@RequestMapping("/handle")
public class handle {
	
	@Resource
	private HandleService handleService;
	
	/**
	 * 产品包页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getHandleApp.do")
	public void getHandleApp(HttpServletRequest request, HttpServletResponse response) throws Exception{
	
		String isBoutique=request.getParameter("isBoutique");
		
		List<Map<String, Object>> msg=handleService.getHandleApp(isBoutique);
		
		JsonWriteUtil.write(response, msg);
	}

	
}
