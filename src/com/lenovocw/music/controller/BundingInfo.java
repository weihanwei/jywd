package com.lenovocw.music.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.service.SysRedenvelopeOrdersService;
import com.lenovocw.music.service.WeiXinUserService;
import com.lenovocw.utils.ExoCoder;
import com.lenovocw.utils.JsonWriteUtil;

@Controller
@RequestMapping("/bunding")
public class BundingInfo {

	@Resource
	WeiXinUserService weiXinUserService;

	@Resource
	private SysRedenvelopeOrdersService redenvelopeOrdersService;

	/**
	 * 功能：进入绑定界面前的操作
	 * 
	 * @param
	 * @param 微信号
	 *            ;
	 * @param 微信信息
	 *            ;
	 * @return
	 */
	@RequestMapping(value = "toBundingUserPhone.do")
	public String toBundingUserPhone(HttpServletRequest request,
			HttpServletResponse response) {

		String code = request.getParameter("code");// 微信授权后返回的字符串

		if (code != null) {
			try {
				Map<String, String> map = redenvelopeOrdersService
						.webchatOauth2(code);
				String nickname = map.get("nickname");
				String openid = map.get("openid");
				String headimgurl = map.get("headimgurl");
				if (openid != null) {
					request.getSession().setAttribute("openid", openid);
					request.getSession().setAttribute("nickname", nickname);
					request.getSession().setAttribute("headimgurl", headimgurl);
					return "index/bindingPage";

				} else {
					return "index/bindingPageError";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "index/bindingPageError";
			}

		} else {
			return "index/bindingPageError";

		}

	}

	/**
	 * 功能：手机号绑定微信openId
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "bundingUserWeiXinInfo.do")
	public void bundingUserWeiXinInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> msg = new HashMap<String, Object>();

		String key = request.getParameter("key");

		String phone = ExoCoder.decrypt(request.getParameter("jyn"), key);

		String password = ExoCoder.decrypt(request.getParameter("jym"), key);

		String randomPassword = (String) request.getSession().getAttribute(
				"randomPassword");

		if (randomPassword == null || "".equals(randomPassword)) {

			msg.put("retype", "202");
			msg.put("retmsg", "动态密码超时！");

		} else if (randomPassword.equals(password)) {

			// 查询缓存中用户微信信息

			String openId = "";
			String nickname = "";
			String headimgurl = "";

			// openId=(String)request.getSession().getAttribute("openid");
			// nickname=(String)request.getSession().getAttribute("nickname");
			// headimgurl=(String)request.getSession().getAttribute("headimgurl");

			// update by hanwei 20151224 修改参数取值方式 begin
			openId = (String) request.getSession()
					.getAttribute("receiveOpenid");// 抢红包人微信号
			nickname = (String) request.getSession().getAttribute(
					"receiveNickname");// 抢红包人微信昵称
			headimgurl = (String) request.getSession().getAttribute(
					"headimgurl");// 抢红包人微信昵称
			// end

			// 绑定手机号
			if ((null != openId) && (!"".equals(openId))) {

				// 判断当前手机号有没有被绑定别的微信绑定过
				int bundingCount = weiXinUserService
						.countWeiXinUserExceptOpenId(openId, phone);
				if (bundingCount > 0) {
					msg.put("retype", "203");
					msg.put("retmsg", "该手机号已经被别的微信号绑定！");
				} else {
					
					weiXinUserService.save(openId, phone, nickname, headimgurl);

					msg.put("retype", "0");
					msg.put("retmsg", "成功！");
				}

			} else {
				msg.put("retype", "203");
				msg.put("retmsg", "微信认证信息过期，请重新授权！");
			}

		} else {
			msg.put("retype", "201");
			msg.put("retmsg", "动态密码不正确！");
		}

		JsonWriteUtil.write(response, msg);

	}
}
