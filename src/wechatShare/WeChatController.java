package wechatShare;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovocw.music.util.LogRecord;
import com.lenovocw.utils.Base64Utils;
import com.lenovocw.utils.HttpUtil;
import com.lenovocw.utils.JsonWriteUtil;
import com.lenovocw.utils.MD5;

@Controller
@RequestMapping("/weChat")
public class WeChatController {
	/**
	 *wechat signature
	 */
	@Resource
	private WeChatShareService weChatShareService;
	@RequestMapping("signature.do")
	public void signature(HttpServletRequest request, HttpServletResponse response){
		Map<String,String> data;
		try {
			String url = request.getParameter("url");
			url = url.replaceAll("@","&");
			System.out.println(url);
			if(url==null){
				return;
			}
			LogRecord.info("signature currnet url>>"+url);
			data = weChatShareService.weChatSigantrue(url);//get signature
			if(data == null){
				data = new HashMap<String,String>();
				data.put("status", "-1");
				data.put("error", "get jsapi_ticket error");
			}
			System.out.println(data);
			JsonWriteUtil.write(response, data);
		} catch (Exception e) {
			LogRecord.error("wechat signatrue error>>"+e.getMessage());
			e.printStackTrace();
			data = new HashMap<String,String>();
			data.put("status", "-1");
			data.put("error", e.getMessage());
		}
	}
	
	@RequestMapping("weiTingGetTicket.do")
	public void weiTingGetTicket(HttpServletRequest request, HttpServletResponse response){
		Map<String,String> data;
		try {
			String url = request.getParameter("url");
			if(url==null){
				return;
			}
			System.out.println(url);
			LogRecord.info("signature currnet url>>"+url);
			data = weChatShareService.weiTingGetTicket(url);//get signature
			if(data == null){
				data = new HashMap<String,String>();
				data.put("status", "-1");
				data.put("error", "get jsapi_ticket error");
			}
			System.out.println(data);
			JsonWriteUtil.write(response, data);
		} catch (Exception e) {
			LogRecord.error("wechat signatrue error>>"+e.getMessage());
			e.printStackTrace();
			data = new HashMap<String,String>();
			data.put("status", "-1");
			data.put("error", e.getMessage());
		}
	}
}
