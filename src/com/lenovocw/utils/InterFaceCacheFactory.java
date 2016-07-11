package com.lenovocw.utils;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;


public class InterFaceCacheFactory {
	
	/** 查询所有接口sql **/
	private static String query_interface="select * from tb_s_interface";
	/** 接口缓存标识key **/
	public static String interface_cache_key="interface_u";
	/** 接口缓存时间 **/
	public static Integer interface_cache_time=60*15;
	/** 接口url key 前缀   保存接口 map**/
	public static String url_prefix="interface.url:";
	/** 接口id key 前缀   保存接口  url**/
	public static String id_prefix="interface.id:";
	
	public static final Logger log=LoggerFactory.getLogger(InterFaceCacheFactory.class);
	
	/** 初始化接口列表  **/
	public static void init(){
		Jedis jedis =null;
		log.debug("InterFaceCacheFactory init");
		try {
			jedis=JedisPoolUtil.getResource();
			Transaction transaction = jedis.multi();
			transaction.set(interface_cache_key,"");
			transaction.expire(interface_cache_key, interface_cache_time);
			transaction.exec();
			
			log.debug("save interface_cache_key :"+interface_cache_key);
			//缓冲数据
			List<Map<String,Object>> list=
					SpringGetBeanUtil.getDao().queryForListMap(query_interface);
			if(list!=null){
				for(Map<String,Object> map : list){
					String url=""+map.get("url");
					jedis.set(id_prefix+map.get("id"),url);
					log.debug("set  "+id_prefix+map.get("id") +"  "+url);
					//jedis.set((url_prefix+url).getBytes(),StreamUtil.objectToByteArray(map));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JedisPoolUtil.returnResource(jedis);
		}
	}
	
	
	/**根据接口id获取接口的url
	 * @param id
	 * @return
	 */
	public static String getUrlById(String id){
		Jedis jedis=null;
		try {
			jedis=JedisPoolUtil.getResource();
			if(!jedis.exists(interface_cache_key))
				init();
			return jedis.get(id_prefix+id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JedisPoolUtil.returnResource(jedis);
		}
		return null;
	}
	
}
