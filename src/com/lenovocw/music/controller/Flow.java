package com.lenovocw.music.controller;




import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.model.FlowTC;
import com.lenovocw.music.service.FlowService;
import com.lenovocw.music.service.SysRedenvelopeOrdersService;
import com.lenovocw.music.service.WeiXinUserService;
import com.lenovocw.utils.HttpUtil;
import com.lenovocw.utils.JsonWriteUtil;
import com.lenovocw.utils.PropertyUtil;


@Controller
@RequestMapping("/flow")
public class Flow {
	
	@Resource
	private FlowService flowService;
	@Resource
	private SysRedenvelopeOrdersService redenvelopeOrdersService;
	
	@Resource
	WeiXinUserService weiXinUserService;
	/**
	 * 流量红包价格查询
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "changePrice.do")
	public void changePrice(HttpServletRequest request, HttpServletResponse response) throws Exception{

		String number= request.getParameter("number");
		
		String size= request.getParameter("size");
		
		Map<String,Object> msg=flowService.changePrice(number,size);
		
		JsonWriteUtil.write(response, msg);
		
	}
	
	/**
	 * 流量红包价格查询
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "handleFlowTc.do")
	public void handleFlowTc(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    
		String mainTC= request.getParameter("mainTC");
		
		String giveTC= request.getParameter("giveTC");
		
		String AGENTNO= request.getParameter("AGENTNO");
		
		 //获用户信息
	    String phone=(String) request.getSession().getAttribute("phone");
		
		Map<String, Object> msg=flowService.handleFlowTc(mainTC,giveTC,phone,AGENTNO);
		
		JsonWriteUtil.write(response,msg);
		
	}
	
	@RequestMapping(value = "buyRedEnvelope.do")
	public void buyRedEnvelope(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 //获用户信息
	    String phone=(String) request.getSession().getAttribute("phone");
	    String nickname=(String) request.getSession().getAttribute("nickname");
	    String openid=(String) request.getSession().getAttribute("openid");
        String number= request.getParameter("number");
        String AGENTNO= request.getParameter("AGENTNO");
		String size= request.getParameter("size");
		
//		String phone="17875761914";
//		String nickname="xxxx";
//		String openid="xxxxxx";
//		String number= "1";
//		String AGENTNO="123";
//		String size= "50";
		
		//需要判断是否为移动内部员工，如果为内部员工需要判断是否超过内部员工购买红包限制数
		
		//企业内部员工
		if(flowService.isStaffPhone(phone))
		{
			//查询是否超出红包的购买限制数
			int buyRedPackCount = redenvelopeOrdersService.queryBuyRedPackCount(phone);
			//红包购买限制数,从配置文件读取
			int limit=20;
			
			try
			{
				limit = Integer.parseInt(PropertyUtil.getKey("buyredpackage.limit"));
				
			}catch(Exception e)
			{
			    e.printStackTrace();
			}
			if((buyRedPackCount+Integer.parseInt(number))>limit)
			{
				Map<String,Object> msg= new HashMap<String,Object>();
				
				msg.put("state", "-1");
				msg.put("msg", "尊敬的客户，内部员工每月最多购买"+limit+"个流量红包。");
				JsonWriteUtil.write(response, msg);
				return;
			}
		}
		
		 
		Map<String,Object> msg=flowService.buyRedEnvelope(phone,number,size,AGENTNO,nickname,openid);
			//重定向
			request.getSession().setAttribute("ordersId", msg.get("ID"));
			request.getSession().setAttribute("number", msg.get("number"));
			request.getSession().setAttribute("size", msg.get("size"));
//			request.getSession().setAttribute("ordersId", "gc2deae0dc6248c6f");
//			request.getSession().setAttribute("number", "10");
//			request.getSession().setAttribute("size", "100");
			JsonWriteUtil.write(response, msg);
		
	}
	
	/**
	 * 流量红包价格查询
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getSizesByNumber.do")
	public void getSizesByNumber(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    
        String number= request.getParameter("number");
		
		List<Map<String, Object>> msgs=flowService.getSizesByNumber(number);
		
		JsonWriteUtil.write(response, msgs);
		
	}
	
	
	/**
	 * 流量红包价格查询
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getFlowTc.do")
	public void getFlowTc(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    
		List<Map<String, Object>> msgs=flowService.getFlowTc();
		
	    JSONObject object=new JSONObject();
		object.put("msgs", msgs);
		
		JsonWriteUtil.write(response,object);
		
	}
	
	/**
	 * 获取抢到红包的有效期
	 */
	@RequestMapping(value = "getRedpackageUseTime.do")
	public void getRedpackageUseTime(HttpServletRequest request, HttpServletResponse response){
		
		String receiveId = (String) request.getSession().getAttribute("receiveId");
		//获取红包的有效时间
		
		try {
			
			String time = redenvelopeOrdersService.getRedpackageUseTime(receiveId);
			
			 JSONObject object=new JSONObject();
			 	object.put("time", time);
			JsonWriteUtil.write(response,object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 *抢红包方法
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	@RequestMapping(value = "grabFlowRedenvelope.do")
	public void grabRedenvelope(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String redId = (String) request.getSession().getAttribute("redpackageId");//获取红包id
		
		String receiveWechatCode = (String) request.getSession().getAttribute("receiveOpenid");//抢红包人微信号
		
		String receiveWechatName = (String) request.getSession().getAttribute("receiveNickname");//抢红包人微信昵称
		
		Map<String, Object> repMap=redenvelopeOrdersService.checkRedpacakgeValid(redId);
		if(repMap != null){
			//先判读 抢红包人微信号是否已经关注 揭阳移动 和微信是否绑定手机
			Map<String, Object> weixinInfo = weiXinUserService.queryWeiXinUserInfo(receiveWechatCode, null);

			// 用户信息存在，则执行修改操作
			if (weixinInfo.containsKey("ID")){
					//查询该用户是否已经抢过当前红包如果已经抢过不能再抢
				try{
					Map<String,Object> check = redenvelopeOrdersService.checkReceiveWechatCode(redId,receiveWechatCode);
					if(check == null){//无抢红包记录
						//根据红包id查询出红包信息K
						String exchangeMobile = (String) weixinInfo.get("PHONE");//抢红包人微信绑定电话
						Map<String,String> map = new HashMap<String, String>();
							
							map.put("redId", redId);
							map.put("receiveWechatCode",receiveWechatCode );
							map.put("receiveWechatName",receiveWechatName );
							map.put("exchangeMobile", exchangeMobile);
						//调用抢红包方法返回Map参数包括 红包大小 ，发包人，成功状态，抢红包流水
							Map<String,String> result = redenvelopeOrdersService.grabFlowRedenvelope(map);
						if(result != null){
							request.getSession().setAttribute("sendWechatName", result.get("sendWechatName"));
							request.getSession().setAttribute("receiveFlowSize", result.get("flowSize"));
							request.getSession().setAttribute("receiveId", result.get("receiveId"));
							JSONObject object=new JSONObject();
							object.put("msgs", result.get("msgs"));
							JsonWriteUtil.write(response,object);
						}else{
							JSONObject object=new JSONObject();
							object.put("msgs", "该红包无效!");
							object.put("status", "0");
							JsonWriteUtil.write(response,object);
						}
					}else{//已经抢过
						//返回已抢提醒信息
						JSONObject object=new JSONObject();
						object.put("msgs", "已经抢过了");
						object.put("status", "0");
						JsonWriteUtil.write(response,object);
					}
				}catch(Exception e){
					JSONObject object=new JSONObject();
					object.put("msgs", "系统出错！");
					object.put("status", "0");
					JsonWriteUtil.write(response,object);
					e.printStackTrace();
				}
			}else{
				JSONObject object=new JSONObject();
				object.put("status", "2");
				object.put("msgs", "首次参与抢红包，须先登记绑定个人手机号码！请先进行绑定！");
				try {
					JsonWriteUtil.write(response,object);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			JSONObject object=new JSONObject();
			object.put("status", "3");
			object.put("msgs", "红包已过期！");
			try {
				JsonWriteUtil.write(response,object);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}


	/**
	 *分享红包主方法
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "shareRedenvelope.do")
	public void shareRedenvelope(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String redId = request.getParameter("flowRedPacketId");//获取红包id
		String result;
		try {
			result = redenvelopeOrdersService.shareRedenvelope(redId);
			JSONObject object=new JSONObject();
			object.put("msgs", result);
			JsonWriteUtil.write(response,object);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *获取红包信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getRedenvelopeInfo.do")
	public void getRedenvelopeInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String redId = (String) request.getSession().getAttribute("redpackageId");
		try {
			 Map<String,Object> map = redenvelopeOrdersService.getRedenvelopeInfo(redId);
			JSONObject object=new JSONObject();
			object.put("WECHATNAME", String.valueOf(map.get("WECHATNAME")));
			object.put("FLOWSIZE", String.valueOf(map.get("FLOWSIZE")));
			JsonWriteUtil.write(response,object);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 抢红包入口
	 * 配置连接方法
	 * @param 红包订单ID redpackageOrderId;
	 * @param 微信号 ;
	 * @param 微信信息 ;
	 * @return
	 */
	@RequestMapping(value = "toOpenRedpackage.do")
	public String toOpenRedpackage(HttpServletRequest request, HttpServletResponse response){
		
		//红包信息传到页面，现在我在js文件中固定了几个变量
		//入口需要获取微信号，微信信息
		String redpackageId = request.getParameter("redpackageId");
		
		
		String code = request.getParameter("code");//微信授权后返回的字符串
		
		if(code != null && redpackageId != null){
			try {
 				Map<String,String> map = redenvelopeOrdersService.webchatOauth2(code);
				String nickname = map.get("nickname");
				String openid = map.get("openid");
				String headimgurl = map.get("headimgurl");
//				String nickname = "55";
//				String openid = "66";
//				String headimgurl = "77";
				if(nickname !=null && openid !=null){
					
					//查询当前id下的红包状态
					Map<String,Object> packageMap = redenvelopeOrdersService.getRedpackageById(redpackageId);
					
					if(packageMap!=null){
						
						request.getSession().setAttribute("receiveNickname",nickname);
						request.getSession().setAttribute("redpackageId",redpackageId);
						request.getSession().setAttribute("headimgurl",headimgurl);
						request.getSession().setAttribute("receiveOpenid", openid);
						int flowSize = Integer.valueOf(String.valueOf(packageMap.get("FLOWSIZE")));
						String sendWechatName = String.valueOf(packageMap.get("WECHATNAME"));
						String sendWechatCode = String.valueOf(packageMap.get("WECHATCODE"));
						request.getSession().setAttribute("sendName", sendWechatName);
						request.getSession().setAttribute("sendCode", sendWechatCode);
						request.getSession().setAttribute("receiveFlowSize",flowSize);
						try {
							//查询当前用户是否已经抢过红包
							Map<String,Object> result= redenvelopeOrdersService.checkReceiveWechatCode(redpackageId,openid);
							if(result == null){
								int receiveStatus = Integer.valueOf(String.valueOf(packageMap.get("RECEIVESTATUS")));
								if(receiveStatus == 1){
									request.getSession().setAttribute("redpackageId", redpackageId);
									return "flow/redpackageOver";
								}else{
									request.getSession().setAttribute("redpackageId", redpackageId);
									return "flow/openRedpackage";
								}
							}else{
								int status = Integer.valueOf(String.valueOf( result.get("EXCHANGESTATUS")));
								 if(status == 0){
									 request.getSession().setAttribute("receiveId", result.get("ID"));
									return "flow/cashRedpackage";
								}else if(status == 1){
									 request.getSession().setAttribute("receiveId", result.get("ID"));
									System.out.println("兑换的电话号码为："+result.get("EXCHANGEMOBILE"));
									request.getSession().setAttribute("cashMobile", result.get("EXCHANGEMOBILE"));
									return "flow/cashSuccessed";
								}else if(status == 2){
									request.getSession().setAttribute("receiveId", result.get("ID"));
									request.getSession().setAttribute("cashMobile", "发送者手机");
									return "flow/cashSuccessed";
								}else{
									return "flow/error";
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return "flow/error";
						}
						
					}else{
						return "flow/error";
					}
				}else{
					return "flow/error";
				}
			} catch (Exception e) {
				e.printStackTrace();
			//	return "flow/error";
				return "flow/openRedpackage";
			}
		}else{
			return "flow/error";
		}	
			
/*			
		String mobileNo = request.getParameter("mobileNo");
		String wxCode = request.getParameter("wxCode");//微信号
		String wxName = request.getParameter("wxName");
		Map<String,Object> packageMap = redenvelopeOrdersService.getRedpackageById(redpackageId);
		if(packageMap!=null){
			
			int flowSize = Integer.valueOf(String.valueOf(packageMap.get("FLOWSIZE")));
			String sendWechatName = String.valueOf(packageMap.get(""));
			String sendWechatCode = String.valueOf(packageMap.get(""));
			request.getSession().setAttribute("sendWechatName", sendWechatName);
			request.getSession().setAttribute("sendWechatCode", sendWechatCode);
			request.getSession().setAttribute("receiveFlowSize",flowSize);
			try {
				//查询当前用户是否已经抢过红包
				Map<String,Object> result= redenvelopeOrdersService.checkReceiveWechatCode(redpackageId,wxCode);
				if(result == null){
					request.getSession().setAttribute("redpackageId", redpackageId);
					return "flow/openRedpackage";
				}else{
					int status = Integer.valueOf(String.valueOf( result.get("EXCHANGESTATUS")));
					 if(status == 0){
						 request.getSession().setAttribute("receiveId", result.get("ID"));
						return "flow/cashRedpackage";
					}else{
						return "flow/redpackageList";
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "flow/redpackageList";
			}
			
		}else{
			return "flow/error";
		}
	*/	
	}
	
	@RequestMapping(value = "checkReceiveWechatCode.do")
	public void checkReceiveWechatCode(HttpServletRequest request, HttpServletResponse response){
	
	}
	
	/**
	 * 验证用户是否已经抢过红包返回状态
	 */
	
	/**
	 * 兑换红包主方法
	 * @param receviceId 抢红包流水id
	 * @param flowSize
	 * @param mobile
	 * <p>根据用户输入的电话号码调用上行兑换接口，返回成功修改receive表对应红包id记录
	 * LLHB#10M----201510-揭阳流量红包领取省内通用流量10M
	 * LLHB#50M----201510-揭阳流量红包领取省内通用流量50M
	 * LLHB#100M----201510-揭阳流量红包领取省内通用流量100M
	 * LLHB#300M----201510-揭阳流量红包领取省内通用流量300M
	 * LLHB#500M----201510-揭阳流量红包领取省内通用流量500M
	 * LLHB#1024M----201510-揭阳流量红包领取省内通用流量1024M
	 * @throws IOException 
	 */
	@RequestMapping(value = "cashRedpackage.do")
	public void cashRedpackage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String receviceId = (String) request.getSession().getAttribute("receiveId");
		String mobileNo = request.getParameter("mobileNo");
		JSONObject object=new JSONObject();
		try {
			String receiveWechatCode = (String) request.getSession().getAttribute("receiveOpenid");//抢红包人微信号
			Map<String, Object> weixinInfo = weiXinUserService.queryWeiXinUserInfo(receiveWechatCode, null);

			// 用户信息存在，则执行修改操作
			String honnerMobile = "";
			if (weixinInfo.containsKey("ID")){
				honnerMobile = (String) weixinInfo.get("PHONE");
			}
			Map<String,String> resultMap =  redenvelopeOrdersService.cashRedpackage(receviceId, mobileNo,honnerMobile);
			if(resultMap.get("status").equals("1")){
				object.put("status", "1");
				object.put("msgs", resultMap.get("msgs"));
				request.getSession().setAttribute("cashMobile", resultMap.get("moblieNo"));
			}else{
				object.put("status", "0");
				object.put("msgs", resultMap.get("msgs"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			object.put("status", "error");
			object.put("msgs", "系统异常！");
		}
	//	return "/index/login";
		JsonWriteUtil.write(response,object);
		
	}
	
	/**
	 * 查询我抢到的红包
	 * @param 微信号 wechantCode  
	 * @param 电话号码 mobileNo
	 * @throws Exception
	 */
	@RequestMapping(value = "getMyReceviceRedpackage.do")
	public void getMyReceviceRedpackage(HttpServletRequest request, HttpServletResponse response){
		String mobileNo = (String) request.getSession().getAttribute("phone");//电话
		List<Map<String, Object>> msgs;
		try {
			Map<String, Object> weixinInfo = weiXinUserService.queryWeiXinUserInfo(null, mobileNo);

			// 用户信息存在，则执行修改操作
			if (weixinInfo.containsKey("ID")){
				String openId = (String) weixinInfo.get("OPENID");
			
				msgs = redenvelopeOrdersService.getMyReceviceRedpackage(openId);
			}else{
				msgs = new ArrayList<Map<String, Object>>();
			}
			JsonWriteUtil.write(response, msgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询我抢到红包领取状态
	 * @param 微信号 wechantCode  
	 * @param 电话号码 mobileNo
	 * @throws Exception
	 */
	@RequestMapping(value = "MyReceviceRedpackageStatus.do")
	public void MyReceviceRedpackageStatus(HttpServletRequest request, HttpServletResponse response){
		String mobileNo = (String) request.getSession().getAttribute("phone");//电话
		List<Map<String, Object>> msgs = null;
		try {
			
			Map<String, Object> weixinInfo = weiXinUserService.queryWeiXinUserInfo(null, mobileNo);

			// 用户信息存在，则执行修改操作
			if (weixinInfo.containsKey("ID")){
				String openId = (String) weixinInfo.get("OPENID");
				msgs = redenvelopeOrdersService.getRedpackageRecevice(openId);
			}else{
				msgs = new ArrayList<Map<String, Object>>();
			}
			JsonWriteUtil.write(response, msgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 查询我发送的红包
	 * @param 微信号 wechantCode
	 * @param 电话号码 mobileNo 
	 * @throws IOException 
	 */
	@RequestMapping(value = "getMyshareRedpackage.do")
	public void getMyshareRedpackage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String mobileNo = (String) request.getSession().getAttribute("phone");//电话
		List<Map<String, Object>> msgs;
		try {
			msgs = redenvelopeOrdersService.getMyshareRedpackage(mobileNo);
			JsonWriteUtil.write(response, msgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询红包领取记录
	 * @param redpackageId 红包id
	 */
	@RequestMapping(value = "getRedpackageRecevice.do")
	public void getRedpackageRecevice(HttpServletRequest request, HttpServletResponse response){
		String redpackageId = request.getParameter("redpackageId");//电话
		
		List<Map<String, Object>> msgs;
		try {
			msgs = redenvelopeOrdersService.getReceviceById(redpackageId);
			JsonWriteUtil.write(response, msgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * 统计已经超过有效期的红包
	 */
	@RequestMapping(value = "cashOutTimeRedpackage.do")
	public void testDemo(HttpServletRequest request, HttpServletResponse response){
		try {
			redenvelopeOrdersService.countOutTimeRedpackage();//统计
			redenvelopeOrdersService.countOutTimeReceiveList();
			redenvelopeOrdersService.cashOutTimeRedpackage();//兑换
			redenvelopeOrdersService.sendMsmTOcashFlow();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询领取红包的基本情况
	 */
	@RequestMapping(value = "getReceivePackage.do")
	public void getReceivePackage(HttpServletRequest request, HttpServletResponse response){
		try {
			String receiveId = request.getParameter("receiveId");//id
			 Map<String,Object> map = redenvelopeOrdersService.getReceiveList(receiveId);
			 Map<String,String> returnMap = new HashMap<String, String>();
			 StringBuffer sbf = new StringBuffer();
			 if(map != null){
				 int EXCHANGESTATUS = Integer.valueOf(String.valueOf(map.get("EXCHANGESTATUS")));
				 if(EXCHANGESTATUS == 1){
					 sbf.append("恭喜您！该红包已于"+(String)map.get("EXCHANGEDATES")+" 兑换给号码"+(String)map.get("EXCHANGEMOBILE"));
				 }else if(EXCHANGESTATUS == 2){
					 sbf.append("该红包兑换有效期截止"+(String)map.get("VALIDITYTIMES")+"。现已过期，不能兑换！");
				 }
				
			 }else{
				 sbf.append("查无信息"); 
			 }
			 returnMap.put("msgTXT", sbf.toString());
			 returnMap.put("redpackageId", (String)map.get("REDENVELOPEORDERSID"));
			 JsonWriteUtil.write(response, returnMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询红包记录
	 * @param redpackageId 红包id
	 */
	@RequestMapping(value = "getRedpackage.do")
	public void getRedpackage(HttpServletRequest request, HttpServletResponse response){
		String redpackageId = request.getParameter("redpackageId");//电话
		
		Map<String, Object>msgs;
		try {
			msgs = redenvelopeOrdersService.getRedpackageById(redpackageId);
			JsonWriteUtil.write(response, msgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	/**
	 * 测试微信授权回调页面
	 * @throws JSONException 
	 */
	@RequestMapping(value = "totWebchatBuyRedPK.do")
	public String testWebchat(HttpServletRequest request, HttpServletResponse response) throws JSONException{
		String code = request.getParameter("code");//微信授权后返回的字符串
		if(code != null){
			try{
				Map<String,String> map = redenvelopeOrdersService.webchatOauth2(code);
				String nickname = map.get("nickname");
				String openid = map.get("openid");
				if(nickname !=null && openid !=null){
					request.getSession().setAttribute("nickname",nickname);
					request.getSession().setAttribute("openid", openid);
					return "flow/buyRedpackage";
				}else{
					return "flow/error";
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return "flow/buyRedpackage";
		}else{
			return "flow/error";
		}
	
	}
	
	
	
	@RequestMapping(value = "testHttpClient.do")
	public void testHttpClient(HttpServletRequest request, HttpServletResponse response) throws JSONException, InterruptedException{
		System.out.println("进入");
		try {
			JsonWriteUtil.write(response, "22222222");
			System.out.println("出去");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 抢红包入口
	 * 配置连接方法
	 * @param 红包订单ID redpackageOrderId;
	 * @param 微信号 ;
	 * @param 微信信息 ;
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "toClashRedpackageJSP.do")
	public void toClashRedpackageJSP(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		//红包信息传到页面，现在我在js文件中固定了几个变量
		//入口需要获取微信号，微信信息
		String receiveId = request.getParameter("receiveId");
		
		Map<String, Object> map = redenvelopeOrdersService.getReceiveList(receiveId);
		if(map!= null){
			request.getSession().setAttribute("redpackageId", map.get("REDENVELOPEORDERSID"));
			request.getSession().setAttribute("receiveId", receiveId);
			request.getSession().setAttribute("receiveOpenid", map.get("RECEIVEWECHATCODE"));
			JsonWriteUtil.write(response, "success");
		}else{
			JsonWriteUtil.write(response, "error");
		}
	}
	
	
}
