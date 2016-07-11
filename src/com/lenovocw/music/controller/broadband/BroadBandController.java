package com.lenovocw.music.controller.broadband;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.service.BroadBandService;
import com.lenovocw.music.service.LoginService;
import com.lenovocw.music.service.ToBeShopManagerService;
import com.lenovocw.music.util.HttpUtilsNew;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.ExoCoder;
import com.lenovocw.utils.JsonWriteUtil;
import com.lenovocw.utils.MD5;
import com.lenovocw.utils.PropertyUtil;
/**
 * 
 * jywd
 * @author heshiqing
 * 
 * copyright:Copyright@2013 和跃科技有限公司
 * 2015-9-15
 */


@Controller
@RequestMapping("/broadband")
public class BroadBandController {
	@Resource
	LoginService loginService;
	
	@Resource
	private ToBeShopManagerService toBeShopManagerService;
	
	@Resource
	private BroadBandService broadBandService;
	
	
	@RequestMapping(value="queryaddress")
	public void queryAddress(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object> map=new HashMap<String, Object>();
		try{	
			String address=request.getParameter("address");
		  		Date date=new Date();
				String dateStr="";
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
				dateStr=dateFormat.format(date);
				
				//接口秘钥
				String secretkey =PropertyUtil.getKey("homeBroadband.secretKey");
				
				//开发商编号
				String 	developercode =PropertyUtil.getKey("homeBroadband.developerCode");
				
				//String token=dateStr+"_pcWIzemI_echotech";//当前日期_接口密钥_开发商编号
				String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号
				token=MD5.getMD5Str(token);
				String url =PropertyUtil.getKey("moreChannel")+//
						     "eid="+developercode+"&sid=Yidu_SdSearch&token="+//
						      token + "&rspt=JSON";//echotech:开发商编号,sid:服务id
				HttpUtilsNew httpUtilsNew=new HttpUtilsNew();
				TreeMap<String, String> params=new TreeMap<String, String>();
				System.out.println(address);
				params.put("branch", address);//订单
				String result=httpUtilsNew.httpPost(url, params).trim();
				System.out.println(result);
				JSONObject object=new JSONObject(result);
				String data=object.getString("data");
				String status=object.getString("status");
				data=Base64Utils.getFromBASE64(data);
				//JSONObject data1=new JSONObject(data);
				System.out.println(data);
				map.put("status", status);
				map.put("data", data);
				JsonWriteUtil.write(response, map);
				
	  }catch (Exception e) {
		// TODO: handle exception
		  map.put("status", "0");
		  map.put("data", "[]");
		  JsonWriteUtil.write(response, map);
	}
	}
	/**
	 * 申请加宽
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="applicationJK.do")
	public void ApplicationJK(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			String name = request.getParameter("xm");
			String dh = request.getParameter("dh");
			String qy = request.getParameter("qy");
			String xq = request.getParameter("xq");
			String xxdz = request.getParameter("xxdz");
			String code = request.getParameter("code");
			String AGENTNO = request.getParameter("AGENTNO");
			String phone = (String) request.getSession().getAttribute("phone");

			System.out.println(request.getSession().getAttribute("randCode")
					+ "--" + code);
			if (code.equals(String.valueOf(request.getSession().getAttribute(
					"randCode")))) {
				Date date = new Date();

				String dateStr = "";

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

				dateStr = dateFormat.format(date);
				//接口秘钥
				String secretkey =PropertyUtil.getKey("homeBroadband.secretKey");
				
				//开发商编号
				String 	developercode =PropertyUtil.getKey("homeBroadband.developerCode");
				
				//String token = dateStr + "_pcWIzemI_echotech";// 当前日期_接口密钥_开发商编号
				String token=dateStr+"_"+secretkey+"_"+developercode;//当前日期_接口密钥_开发商编号
				token = MD5.getMD5Str(token);
				String url = PropertyUtil.getKey("moreChannel") + //
						"eid="+developercode+"&sid=Yidu_BBInstallRegister&token=" + //
						token + "&rspt=JSON";// echotech:开发商编号,sid:服务id

				HttpUtilsNew httpUtilsNew = new HttpUtilsNew();
				TreeMap<String, String> params = new TreeMap<String, String>();
				params.put("userAccount", "");// 订单
				params.put("name", name);// 用户名
				params.put("mobile", dh);// 手机号
				params.put("branch", qy);// 区域
				params.put("address", xq + "-" + xxdz);// 详细地址

				String result = httpUtilsNew.httpPost(url, params).trim();
				// String
				// result="{\'message\':\'请求成功\',\'guid\':\'5094a1d7-7451-423e-b7fb-851381554603\',\'status\':\'1\',\'data\':\'e3N0YXR1czoxLG1lc3NhZ2U6IuWuieijheeZu+iusOaIkOWKnyJ9\'}";
				System.out.println(result);
				JSONObject object = new JSONObject(result);

				String data = object.getString("data");
				String status = object.getString("status");

				data = Base64Utils.getFromBASE64(data);
				System.out.println("status:" + status + ",data:" + data);
				JSONObject obj1 = new JSONObject(data);
				map.put("status", obj1.get("status"));
				map.put("message", obj1.get("message"));
				
				System.out.println("phone-------------"+phone);
				broadBandService.saveBroadBand(name, dh, xq, xxdz, qy, AGENTNO, phone);

			} else {
				map.put("status", "000");
				map.put("message", "输入的验证码有误！");

			}
		}catch (Exception e) {
			// TODO: handle exception
			map.put("status","001");
			map.put("message", "请求出错！");
		}
		
		JsonWriteUtil.write(response, map);
	}
	
	
	
	
	
	/**
	 * 获取登陆验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="getLogCode.do")
	public void getLogCode(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = request.getSession();
		// 在内存中创建图象
		int width =78, height =40;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证码(6位数字)
		String randCode = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			randCode += rand;
			// 将认证码显示到图象中
			//g.setColor(new Color(20 + random.nextInt(110), 20 + random
			//		.nextInt(110), 20 + random.nextInt(110)));
			g.setColor(new Color(0,183,56));
			//g.drawString(rand, 13 * i + 6, 16);
		//	g.drawString(rand, 13 * i + 6, 16);
			g.drawString(rand, 13 * i + 15, 25);
		}
		// 将认证码存入SESSION
		session.setAttribute("randCode", randCode);
		// 图象生效
		g.dispose();
		ServletOutputStream responseOutputStream = response.getOutputStream();
		// 输出图象到页面
		ImageIO.write(image, "JPEG", responseOutputStream);

		// 以下关闭输入流！
		responseOutputStream.flush();
		responseOutputStream.close();
	}

	Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	
	/**
	 * 获取动态验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getRandomyzm.do")
	public void getRandomPassword(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String key=request.getParameter("key");
		String mobile=ExoCoder.decrypt(request.getParameter("jyn"),key);
		request.getSession().setAttribute("randomyzm","");
		Map<String, Object> msg=loginService.getRandomPassword(mobile);
		request.getSession().setAttribute("randomyzm",msg.get("randomPassword"));
		System.out.println(msg.get("randomPassword"));
		JsonWriteUtil.write(response,msg);
		
	}
	
}
