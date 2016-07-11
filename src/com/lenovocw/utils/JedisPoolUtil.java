package com.lenovocw.utils;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class JedisPoolUtil {
	private static JedisPool pool;
	
	 static {
	  ResourceBundle bundle = ResourceBundle.getBundle("redis");
	  if (bundle == null) {
	   throw new IllegalArgumentException(
	     "cannot find the SignVerProp.properties");
	  }
	  JedisPoolConfig config = new JedisPoolConfig();
	  config.setMaxActive(
			  Integer.valueOf(bundle .getString("redis.pool.maxActive")));
	  config.setMaxIdle(
			  Integer.valueOf(bundle .getString("redis.pool.maxIdle")));
	  config.setMaxWait(Integer.valueOf(
			  bundle .getString("redis.pool.maxWait")));
	
	  pool = new JedisPool(
				  config, 
				  bundle.getString("redis.server"),
				  Integer.valueOf(bundle.getString("redis.port"))
			  );
	}
	 
	public static JedisPool getJedisPool(){
		return pool;
	}
	
	public static Jedis getResource(){
		Jedis j=pool.getResource();
		return j;
	}
	
	public static void returnResource(Jedis jedis){
		if(jedis!=null){
			pool.returnResource(jedis);
		}
	}
	
	public static void returnBrokenResource(Jedis jedis){
		if(jedis!=null)
			pool.returnBrokenResource(jedis);
	}
	
	
	
	
	public static byte [] get(byte [] key){
		Jedis jedis =null ;
		try {
			jedis=getResource();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	public static String get(String key){
		Jedis jedis =null ;
		try {
			jedis=getResource();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	
	
	public static String set(String key,String value)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			return jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	public static String set(byte [] key,byte [] value)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			return jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	
	/**缓存数据 (如果 同一个key 再次调用 save 方法  缓冲时间为永久)
	 * @param key	
	 * @param value
	 * @param second 缓冲时间(秒)
	 */
	public static Response<String> set(String key,String value,int second)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			Transaction transaction = jedis.multi();
			Response<String> response=transaction.set(key, value);
			transaction.expire(key, second);
			transaction.exec();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	
	public static Response<byte[]> set(byte [] key,byte [] value,int second)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			Transaction transaction = jedis.multi();
			Response<byte[]> response=transaction.set(key, value);
			transaction.expire(key, second);
			transaction.exec();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	public static Response<Long> lpush(String key,String value,int second)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			Transaction transaction = jedis.multi();
			Response<Long> response=transaction.lpush(key, value);
			transaction.expire(key, second);
			transaction.exec();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	
	public static Response<Long> lpush(byte [] key,byte [] value,int second)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			Transaction transaction = jedis.multi();
			Response<Long> response=transaction.lpush(key, value);
			transaction.expire(key, second);
			transaction.exec();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	
	
	public static Long lpush(String key,String value)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			return jedis.lpush(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	public static Long lpush(byte [] key,byte [] value)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			return jedis.lpush(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	public static Response<Long> expire(String key,int seconds)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			Transaction transaction = jedis.multi();
			Response<Long> response=transaction.expire(key, seconds);
			transaction.exec();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	public static Response<Long> expire(byte [] key,int seconds)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			Transaction transaction = jedis.multi();
			Response<Long> response=transaction.expire(key, seconds);
			transaction.exec();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	

	
	public static Boolean exists(byte [] key)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	public static Boolean exists(String key)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	public static Long llen(String key)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			return jedis.llen(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	public static Long llen(byte [] key)  {
		Jedis jedis =null ;
		try {
			jedis=getResource();
			return jedis.llen(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	

}

