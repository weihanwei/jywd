package com.lenovocw.utils;


import com.lenovocw.init.InitSystemData;
import com.lenovocw.music.exception.CacheException;
import com.lenovocw.music.exception.CacheType;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * <span> <b>功能：</b> </span><br />
 * <span> <!--在这下面一行写功能描述-->
 *
 *</span><br /><br />
 * <span> Copyright LENOVO </span><br /> 
 * <span> Project Name AppMusicServer </span><br /> 
 * <span> Author  ZengZS </span><br /> 
 * <span> Create Time 2012-3-20  下午03:26:26 </span><br /> 
 * <span> App version 1.0.0 </span> <br />
 * <span> JDK version used 6.0 </span><br /> 
 * <span> Modification history none    </span><br /> 
 * <span> Modified by none    </span><br />
 * 
 */
public class CacheJedisUtil{
	
	public CacheJedisUtil(){
		super();
	}
	
	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 *  获取Jedis的连接
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 *
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-3-20  下午03:30:32 </span><br /> 
	 *
	 */
	public static Jedis getConnection(){
		Jedis jedis = new Jedis(InitSystemData.CACHE_HOST, InitSystemData.CACHE_PORT);
		jedis.connect();
		return jedis;
	}
	
	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 * 根据键值key,判断是否存在
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *  key,键值<br />
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 * 存在返回true,不存在返回false
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-3-20  下午04:44:05 </span><br /> 
	 *
	 */
	public static boolean exists(String key) throws CacheException{
		CacheException.checkCacheKey(key);
		Jedis jedis = null;
			jedis = getConnection();
			if(jedis == null)
			throw new CacheException(CacheType.NO_CONNECTION);
	   try{
			boolean rst = jedis.exists(key);
			return rst;
		}catch (RuntimeException  e) {
			throw e;
		}finally{
			closeConnection(jedis);
		}
	}
	
	public static boolean exists(byte [] key) throws CacheException{
		CacheException.checkCacheKey(key);
		Jedis jedis = null;
			jedis = getConnection();
			if(jedis == null)
			throw new CacheException(CacheType.NO_CONNECTION);
	   try{
			boolean rst = jedis.exists(key);
			return rst;
		}catch (RuntimeException  e) {
			throw e;
		}finally{
			closeConnection(jedis);
		}
	}
	
	public static byte [] get(byte [] key) throws CacheException{
		CacheException.checkCacheKey(key);
		Jedis jedis = null;
			jedis = getConnection();
			if(jedis == null)
			throw new CacheException(CacheType.NO_CONNECTION);
	   try{
			byte [] b = jedis.get(key);
			return b;
		}catch (RuntimeException  e) {
			throw e;
		}finally{
			closeConnection(jedis);
		}
	}
	
	
	public static String get(String key) throws CacheException{
		Jedis jedis = null;
			jedis = getConnection();
			if(jedis == null)
			throw new CacheException(CacheType.NO_CONNECTION);
	   try{
			return jedis.get(key);
		}catch (RuntimeException  e) {
			throw e;
		}finally{
			closeConnection(jedis);
		}
	}
	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 *  根据键值key,删除缓存对象
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *  key,键值<br />
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 * 删除成功为true,失败为false
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-3-20  下午04:43:04 </span><br /> 
	 *
	 */
	public static boolean delete(String key) throws CacheException{
		CacheException.checkCacheKey(key);
		Jedis jedis = null;
			jedis = getConnection();
			if(jedis == null)
				throw new CacheException(CacheType.NO_CONNECTION);
			try{
				long rst = jedis.del(key);
				if(rst == 1l){
					return true;
				}
				else {
					return false;
				}
			}catch (RuntimeException  e) {
				throw e;
			}finally{
				closeConnection(jedis);
			}	
	}
	
	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 * 将字符串Value存放缓存中，键值为key.
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *  key 缓存的键值<br />
	 *  value 缓存的值<br />
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 *  成功返回1
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-3-20  下午04:39:32 </span><br /> 
	 *
	 */
	public static int saveForString(String key,String value)  throws CacheException{
		CacheException.checkCacheKey(key);
		Jedis jedis = null;
			jedis = getConnection();
			if(jedis == null)
				throw new CacheException(CacheType.NO_CONNECTION);
			try{
				jedis.set(key, value);
				return  Const.CACHE_SUCCESS;
			}catch (RuntimeException  e) {
				throw e;
			}finally{
				closeConnection(jedis);
			}	
	}
	
	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 * 将字符串Value存放缓存中,键值为key,存放second秒.
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *  key 缓存的键值<br />
	 *  value 缓存的值<br />
	 *  second 缓存的时间，单位秒<br />
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 *  成功返回1
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-3-20  下午04:39:32 </span><br /> 
	 *
	 */
	public static int saveForString(String key,String value,int second)  throws CacheException{
		CacheException.checkCacheKey(key);
		Jedis jedis = null;
			jedis = getConnection();
			if(jedis == null)
				throw new CacheException(CacheType.NO_CONNECTION);
			try{
				Transaction transaction = jedis.multi();
				transaction.set(key, value);
				transaction.expire(key, second);
				transaction.exec();
				return  Const.CACHE_SUCCESS;
			}catch (RuntimeException  e) {
				throw e;
			}finally{
				closeConnection(jedis);
			}	
	}
	
	public static int saveForByte(byte [] key,byte [] value,int second)  throws CacheException{
		CacheException.checkCacheKey(key);
		Jedis jedis = null;
			jedis = getConnection();
			if(jedis == null)
				throw new CacheException(CacheType.NO_CONNECTION);
			try{
				Transaction transaction = jedis.multi();
				transaction.set(key, value);
				transaction.expire(key, second);
				transaction.exec();
				return  Const.CACHE_SUCCESS;
			}catch (RuntimeException  e) {
				throw e;
			}finally{
				closeConnection(jedis);
			}	
	}
	
	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 * 根据Key查询存在缓存中的字符串
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *  key 缓存的Key值
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 * key对应的String对象
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-3-20  下午04:37:33 </span><br /> 
	 *
	 */
	public static String queryForString(String key)  throws CacheException{
		CacheException.checkCacheKey(key);
		Jedis jedis = null;
			jedis = getConnection();
			if(jedis == null)
				throw new CacheException(CacheType.NO_CONNECTION);
			try{
				return jedis.get(key);
			}catch (RuntimeException  e) {
				throw e;
			}finally{
				closeConnection(jedis);
			}	
	}
	
	
	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 *  关闭连接
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 *
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-3-20  下午04:37:11 </span><br /> 
	 *
	 */
	public static void  closeConnection(Jedis jedis){
		if(jedis!=null&&jedis.isConnected()){
			jedis.disconnect();
		}
	}
	
}
