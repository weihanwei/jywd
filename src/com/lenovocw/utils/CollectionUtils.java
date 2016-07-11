package com.lenovocw.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CollectionUtils {
	
	/**判断集合是否为null 或者 为空
	 * @param c
	 * @return
	 */
	public static boolean isEmpty(Collection<Object> c){
		if(c==null)
			return true;
		return c.isEmpty();
	}
	
	/**判断集合是否包含某个元素
	 * @param c
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean contains(Collection<Object> c,Object key){
		if(c==null)
			return false;
		if(key instanceof Collection)
			return c.containsAll((Collection<Object>)key);
		return c.contains(key);
	}
	
	/**集合元素不能为空字符和 null
	 * @param map
	 * @param key
	 * @return
	 */
	public static boolean valueIsNotEmptyString(Map<String,Object> map,Object key){
		if(map==null)
			return false;
		else{
			Object val=map.get(key);
			return val!=null && !"".equals(val);
		}
	}
	
	/**集合元素不能为空字符和 null
	 * @param list
	 * @param key
	 * @return
	 */
	public static boolean valueIsNotEmptyString(List<Object> list,int key){
		if(list==null)
			return false;
		else{
			Object val=list.get(key);
			return val!=null && !"".equals(val);
		}
	}
}
