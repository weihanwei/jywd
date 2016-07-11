package com.lenovocw.music.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.service.LoginService;
import com.lenovocw.utils.ExoCoder;
import com.lenovocw.utils.JsonWriteUtil;

@Controller
@RequestMapping("/login")
public class Login {

	@Resource
	LoginService loginService;
	
	


	/**
	 * 鐧诲綍鎺ュ彛(type涓哄垽鏂瓧娈碉紱service涓烘湇鍔″瘑鐮佺櫥褰曪紱code涓洪獙璇佺爜鐧诲綍)
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "login.do")
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String key = request.getParameter("key");
		String mobile = ExoCoder.decrypt(request.getParameter("jyn"), key);
		String password = ExoCoder.decrypt(request.getParameter("jym"), key);
		String logintype = request.getParameter("logintype");

		Map<String, Object> msg = loginService.login(mobile, password,
				logintype, request);

		if (msg.get("retype").equals("0")) {

			request.getSession().setAttribute("phone", mobile);
			request.getSession().setAttribute("key", key);

		}

		JsonWriteUtil.write(response, msg);

	}

	/**
	 * 鑾峰彇鍔ㄦ�楠岃瘉鐮�
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getRandomPassword.do")
	public void getRandomPassword(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String key = request.getParameter("key");
		String mobile = ExoCoder.decrypt(request.getParameter("jyn"), key);

		Map<String, Object> msg = loginService.getRandomPassword(mobile);
		request.getSession().setAttribute("randomPassword",
				msg.get("randomPassword"));

		msg.remove("randomPassword");

		JsonWriteUtil.write(response, msg);

	}

	/**
	 * 鑾峰彇uuid
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getUUID.do")
	public void getUUID(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Object object = request.getSession().getAttribute("phone");

		if (object != null) {

			Map<String, Object> msg = loginService.getUUID(object.toString());

			JsonWriteUtil.write(response, msg);

		}

	}

	/**
	 * 鍗曠偣鐧诲綍鎺ュ彛(type涓哄垽鏂瓧娈碉紱service涓烘湇鍔″瘑鐮佺櫥褰曪紱code涓洪獙璇佺爜鐧诲綍)
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "pointLogin.do")
	public void pointLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String channelCod = request.getParameter("channelCode");

		String data = request.getParameter("data");

		Map<String, Object> msg = loginService.pointLogin(channelCod, data,
				request);

		JsonWriteUtil.write(response, msg);

	}

	/**
	 * 鍒ゆ柇鏄惁鐧诲綍
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "isLogin")
	public void IsLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		if (request.getSession() != null) {
			if (request.getSession().getAttribute("phone") != null) {
				map.put("islogin", "1");
			} else {
				map.put("islogin", "0");
			}
		} else {
			map.put("islogin", "0");
		}

		JsonWriteUtil.write(response, map);
	}
	
	
	

}
