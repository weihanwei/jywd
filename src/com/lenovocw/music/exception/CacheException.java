package com.lenovocw.music.exception;

import org.apache.commons.lang.StringUtils;

/**
 * <span> <b>功能：</b> </span><br />
 * <span> <!--在这下面一行写功能描述-->
 * 缓存异常
 *</span><br /><br />
 * <span> Copyright LENOVO </span><br /> 
 * <span> Project Name AppMusicServer </span><br /> 
 * <span> Author  ZengZS </span><br /> 
 * <span> Create Time 2012-3-20  下午03:42:44 </span><br /> 
 * <span> App version 1.0.0 </span> <br />
 * <span> JDK version used 6.0 </span><br /> 
 * <span> Modification history none    </span><br /> 
 * <span> Modified by none    </span><br />
 * 
 */
public class CacheException extends Exception{

	private static final long serialVersionUID = 7873495050548014966L;

	public CacheException(String msg){
		super(msg);
	}
	
	public CacheException(String msg, Throwable throwable){
		super(msg,throwable);
	}
	
	public CacheException(Throwable throwable){
		super(throwable);
	}
	
	public static void checkCacheKey(String key) throws CacheException{
		if(StringUtils.isNotBlank(key)){
			return;
		}
		throw new CacheException(CacheType.KEY_IS_NULL);
	}
	
	public static void checkCacheKey(byte [] key) throws CacheException{
		if(key == null || key.length==0){
			throw new CacheException(CacheType.KEY_IS_NULL);
		}
		return;
	}
	
}
