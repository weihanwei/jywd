package com.lenovocw.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;


public class ApiBossService {
	
	public static String url="http://10.249.239.35:8078/apiBossClient/";
	
	 public static HashMap<String, Object> jsontoHashMap(Object object)  
	  {  
	       HashMap<String, Object> data = new HashMap<String, Object>();  
	       // 将json字符串转换成jsonObject  
	       JSONObject jsonObject = JSONObject.fromObject(object);  
	       Iterator it = jsonObject.keys();  
	       // 遍历jsonObject数据，添加到Map对象  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());  
	           Object value =  jsonObject.get(key);  
	           data.put(key, value);  
	       }  
	       return data;  
	    }  
	 
		/**
		 * 获取当前中文时间
		 * @return
		 */
		public static String getTime(){
			Calendar cal = Calendar.getInstance(); 
			int year = cal.get(Calendar.YEAR); 
			int month = cal.get(Calendar.MONTH )+1; 
			int date =cal.get(Calendar.DATE);
			int hour =cal.get(Calendar.HOUR);
			int min =cal.get(Calendar.MINUTE);
			int se =cal.get(Calendar.SECOND);
	        return String.valueOf(year)+"年"+String.valueOf(month)+"月"+String.valueOf(date)+"日"+String.valueOf(hour)+String.valueOf(min)+String.valueOf(se);
	        
		}
		
		/**
		 * 托收状态键值对
		 * 1：正在托收；2：已回盘，成功；3：已回盘，未成功；4：当月没有托收；5：托收完成。当实际回盘金额小于托收金额时，就是未成功
		 */
		public static  Map<String,Object> tskeyValue(){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("1", "正在托收");
			map.put("0", "托收成功");
			map.put("3", "托收未成功");
			map.put("2", "当月没有托收");
			map.put("5", "托收完成");
			
			return map;
			
			
			
		}
	 

}
