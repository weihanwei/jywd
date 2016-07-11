package com.lenovocw.music.service;

import java.util.List;
import java.util.Map;


public interface NoticeService {

	public List<Map<String, Object>> getMessages(String phone) throws Exception;

	public Map<String, Object> getMessagesById(String id) ;

	public List<Map<String, Object>> getMessagesByType(String phone, String type) throws Exception;

	public void read(String id,String phone) throws Exception;

	public Map<String, Object> getMessagesCount(String phone);

	public int collectNotices(String id,String phone);
	
	public int delById(String id, String phone);

	public Map<String, Object> noReadMessagesMSG(String phone);

	/**
	 * 查询消息中心的消息分类类型，以及每种类型的消息总数
	 * @return
	 */
	public List<Map<String, Object>> getMessagesType(String phone);

	/**
	 * 查询用户收藏的消息数
	 * @param phone
	 * @return
	 */
	public int countCollMessage(String phone);

	public List<Map<String, Object>> getMyCollMessages(String phone);

	/**
	 * 功能：取消收藏功能
	 * @param id
	 * @param phone
	 */
	public void cancelCollectNotices(String id, String phone);
}
