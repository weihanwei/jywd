package com.lenovocw.utils;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JsonNotNullArray;
import org.json.JsonNotNullObject;

/**
 * 灏哅ap銆丩ist銆丱bject瀵硅薄杞崲鎴恓son瀛楃涓诧紝骞惰繃婊ゆ暟鎹繑鍥炪�
 * 
 * @author 鑲栨柊鎱�
 * @link http://www.lenovo-cw.com
 * 
 * @version $Revision: 1.00 $ $Date: 2010-08-30
 */
public class JsonUtils {
	

	/**
	 * 灏哋bject瀵硅薄杞崲鎴恓son瀛楃涓�
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String toString(Object obj)
	{
		if(obj instanceof List){
			return filterData(new JsonNotNullArray((List)obj).toString());
		}else if(obj instanceof Map)
			return filterData(new JsonNotNullObject((Map)obj).toString());
		else
			return filterData(new JsonNotNullObject(obj).toString());
	}
	
	private static String filterData(String str){
		return str;
	}
	
	/**娉涘瀷鏂瑰紡鑾峰彇 jsonObject涓殑鍊�濡傛灉娌℃湁杩斿洖榛樿鍊�
	 * @param jo	jsonObject 瀵硅薄
	 * @param key	key
	 * @param t		娉涘瀷榛樿鍊�
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static  <T> T get(JSONObject jo,String key,T t){
		try {
			if(t instanceof Long)
				return (T) (Object)jo.getLong(key);
			if(t instanceof Double)
				return (T) (Object)jo.getDouble(key);
			if(t instanceof Integer)
				return (T) (Object)jo.getInt(key);
			if(t instanceof Boolean)
				return (T) (Object)jo.getBoolean(key);
			if(t instanceof String)
				return (T) jo.getString(key);
			return (T) jo.get(key);
		} catch (JSONException e) {
			return t;
		}
	}
	
	/**娉涘瀷鏂瑰紡鑾峰彇 jsonArray涓殑鍊�濡傛灉娌℃湁杩斿洖榛樿鍊�
	 * @param ja	jsonArray 瀵硅薄
	 * @param index	index
	 * @param t		娉涘瀷榛樿鍊�
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(JSONArray ja,int index,T t){
		try {
			if(t instanceof Long)
				return (T) (Object)ja.getLong(index);
			if(t instanceof Double)
				return (T) (Object)ja.getDouble(index);
			if(t instanceof Integer)
				return (T) (Object)ja.getInt(index);
			if(t instanceof Boolean)
				return (T) (Object)ja.getBoolean(index);
			if(t instanceof String)
				return (T) ja.getString(index);
			return (T) ja.get(index);
		} catch (JSONException e) {
			return t;
		}
	}
	
	/**鑾峰彇jsonObject 娌℃湁杩斿洖null
	 * @param ja	JSONArray瀵硅薄
	 * @param index	绱㈠紩
	 * @return
	 */
	public static JSONObject getJSONObject(JSONArray ja,int index){
		try {
			return ja.getJSONObject(index);
		} catch (JSONException e) {
			return null;
		}
	}
	/**鑾峰彇jsonObject 娌℃湁杩斿洖null
	 * @param jo	jsonObject瀵硅薄
	 * @param key	閿�
	 * @return
	 */
	public static JSONObject getJSONObject(JSONObject jo,String key){
		try {
			return jo.getJSONObject(key);
		} catch (JSONException e) {
			return null;
		}
	}
	/**鑾峰彇JSONArray 娌℃湁杩斿洖null
	 * @param ja	JSONArray瀵硅薄
	 * @param index	绱㈠紩
	 * @return
	 */
	public static JSONArray getJSONArray(JSONArray ja,int index){
		try {
			return ja.getJSONArray(index);
		} catch (JSONException e) {
			return null;
		}
	}
	/**鑾峰彇JSONArray 娌℃湁杩斿洖null
	 * @param jo	jsonObject瀵硅薄
	 * @param key	閿�
	 * @return
	 */
	public static JSONArray getJSONArray(JSONObject jo,String key){
		try {
			return jo.getJSONArray(key);
		} catch (JSONException e) {
			return null;
		}
	}
	
	
}
