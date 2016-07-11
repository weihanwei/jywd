package wechatShare;

import java.util.Map;

public interface WeChatShareService {
	/**
	 * 分享链接签名
	 * @param shareUrl
	 * @return
	 * @throws Exception 
	 */
	public Map<String, String> weChatSigantrue(String shareUrl) throws Exception;
	/**
	 * 微厅获取ticket
	 */
	public Map<String, String> weiTingGetTicket(String shareUrl) throws Exception;
	/**
	 * 根据店主手机号获取微厅关注二维码
	 * @param tel
	 * @return
	 */
//	public Map<String, String> getQRcode_ticket(String tel)throws Exception;
}
