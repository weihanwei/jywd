package com.lenovocw.music.controller;




import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.service.DiscountService;
import com.lenovocw.utils.JsonWriteUtil;


@Controller
@RequestMapping("/discount")
public class discount {
	
	@Resource
	private DiscountService discountService;
	
	@RequestMapping(value = "getDiscount.do")
	public void changePrice(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<Map<String,Object>> msg=discountService.getDiscount();
		
		JsonWriteUtil.write(response, msg);
		
	}
	
	
	
	@RequestMapping(value = "getDiscountContent.do")
	public void getDiscountContent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String,Object> result = null;
		
		String id=request.getParameter("id");
		
		List<Map<String,Object>> msg=discountService.getDiscountById(id);
		
		
		
		if((null !=msg) && (msg.size()>0))
		{
			result =msg.get(0);
			
			
			 //设置图片富文本编辑器的文件路径，如果项目没有发布路径，则无需此步骤
			String contextPath =request.getContextPath();
			if(!"/".equals(contextPath))
			{
				String htmlContent =result.get("HTMLCONTENT").toString();
				htmlContent =htmlContent.replaceAll("/userfiles/",contextPath+"/userfiles/");
				result.put("HTMLCONTENT", htmlContent);
			}
			
		}
		JsonWriteUtil.write(response, result);
		
	}
	
}
