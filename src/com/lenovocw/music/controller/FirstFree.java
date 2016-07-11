package com.lenovocw.music.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.service.FlowPackageService;
import com.lenovocw.utils.JsonWriteUtil;

@Controller
@RequestMapping("/firstFree")
public class FirstFree {

	@Resource
	private FlowPackageService flowPackageService;

	/**
	 * 获取叠加包列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "flowPackageList.do")
	public void handleFlowTc(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Map<String, Object>> msg = flowPackageService.getAllPackages();

		JsonWriteUtil.write(response, msg);

	}

	/**
	 * 购买
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "buyFreePackage.do")
	public void buyFreePackage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String packageId = request.getParameter("packageId");
		String phone = (String) request.getSession().getAttribute("phone");
		Map<String, Object> msg = null;
		if ((null != phone) && (!"".equals(phone))) {
			msg = flowPackageService.buyFreePackage(packageId, phone);
		} else {
			msg = new HashMap<String, Object>();
			msg.put("state", "505");
			msg.put("msg", "你的登录信息过期了,请重新登录");

		}

		JsonWriteUtil.write(response, msg);
	}

}
