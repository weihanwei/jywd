/**
 * $RCSfile: ParamUtils.java,v $
 * $Revision: 1.1.1.1 $
 * $Date: 2002/09/09 13:51:07 $
 *
 * New Jive  from Jdon.com.
 *
 * This software is the proprietary information of CoolServlets, Inc.
 * Use is subject to license terms.
 */

package com.lenovocw.utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.lenovocw.music.model.Params;

/**
 * This class assists skin writers in getting parameters.
 */
public class ParamUtils {

	private HttpServletRequest r;
	private Map<String,Object> m;
	
	public ParamUtils(HttpServletRequest request) {
		this.r=request;
		m=new HashMap<String,Object>();
		m.put(ActionType.ACTION_TYPE.name(),ActionType.QUERY.name());
		m.put("userId", ParamUtils.getUserId(r));
		m.put("user_id", ParamUtils.getUserId(r));
		m.put("imsidn", ParamUtils.getImsidn(r));
		m.put("start",ParamUtils.getParameter(request, "start", 0));
		m.put("count",ParamUtils.getParameter(request, "count", 10));
		m.put("imsi",ParamUtils.getParameter(request, "imsi", ""));
		/**2012-05-29用户表中增加来源渠道**/
		m.put("application", ParamUtils.getParameter(request, "application", ""));
	}
	
	public ParamUtils putKey(String key,Object def){
		m.put(key, ParamUtils.getParameter(r, key, def));
		return this;
	}
	
	public Map<String,Object> getParamMap(){
		return m;
	}
	/**
	 * Gets a parameter as a string.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @return The value of the parameter or null if the parameter was not
	 *      found or if the parameter is a zero-length string.
	 */
	
	public static ParamUtils newParamMapUtil(HttpServletRequest request){
		return new ParamUtils(request);
	}
	
	
	@SuppressWarnings("unchecked")
	private static <T> T castValue(Object value, T def) {
		try {
			if(value==null||"".equals(value))
				return def;
			else if (def instanceof Integer)
				return (T)(Object)Integer.valueOf(value.toString());
			else if (def instanceof Long)
				return (T)(Object)Long.valueOf(value.toString());
			else if (def instanceof Float)
				return (T)(Object)Float.valueOf(value.toString());
			else if (def instanceof Double)
				return (T)(Object)Double.valueOf(value.toString());
			else if (def instanceof Boolean)
				return (T)(Object)Boolean.valueOf(value.toString());
			else if (def instanceof Short)
				return (T)(Object)Short.valueOf(value.toString());
			else if (def instanceof String){
				if("".equals(value))
					return (T)def;
				else
					return (T)value;
			}
		} catch (Exception e) { return  (T)def;}
		return (T)value;
	}
	public static <T> T getParameter(HttpServletRequest request, String name, T def) {
		String temp = request.getParameter(name);
//		if(request.getMethod().equalsIgnoreCase("GET")&&temp!=null&&!"".equals(temp))
//			try {
//				temp=new String(temp.getBytes("ISO-8859-1"),"utf-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
		return castValue(temp,def);
	}
	public static <T> T getAttribute(HttpServletRequest request, String name, T def) {
		Object temp = request.getAttribute(name);
		return castValue(temp,def);
	}
	
	public static <T> T getSessionAttribute(HttpServletRequest request, String name, T def) {
		Object temp =  request.getSession().getAttribute(name);
		return castValue(temp,def);
	}

	/**
	 * Gets a list of int parameters.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @param defaultNum The default value of a parameter, if the parameter
	 * can't be converted into an int.
	 */
	public static String[] getParameters(HttpServletRequest request,
			String name, String defaultValue) {
		String[] paramValues = request.getParameterValues(name);
		if(paramValues != null && paramValues.length > 0)
		{
			for(int i = 0; i < paramValues.length; i++)
			{
				String temp = paramValues[i];
				if(temp == null || "".equals(temp))
					paramValues[i] = defaultValue;
			}
		}
		return paramValues;
	}

	/**
	 * Gets a list of int parameters.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @param defaultNum The default value of a parameter, if the parameter
	 * can't be converted into an int.
	 */
	public static int[] getIntParameters(HttpServletRequest request,
			String name, int defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new int[0];
		}
		int[] values = new int[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Integer.parseInt(paramValues[i]);
			}
			catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}



	/**
	 * Gets a list of long parameters.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @param defaultNum The default value of a parameter, if the parameter
	 * can't be converted into a long.
	 */
	public static long[] getLongParameters(HttpServletRequest request,
			String name, long defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new long[0];
		}
		long[] values = new long[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Long.parseLong(paramValues[i]);
			}
			catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}


	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 *  从头里面取手机号，先从ctwap上取，如果取不了，再从客户端传过来的头里取,如果头里为空，再从参数中取。
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *  x-up-calling-line-id  从ctwap里取手机号<br />
	 *  imsidn  从客户端里取手机号<br />
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 *
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-4-10  下午03:05:04 </span><br /> 
	 *
	 */
	public static String getImsidn(HttpServletRequest request){
		String imsidn = request.getHeader("x-up-calling-line-id");
		imsidn = StringUtils.isBlank(imsidn) ? (StringUtils.isBlank(request.getHeader("imsidn"))?request.getParameter("imsidn"):request.getHeader("imsidn") ) : imsidn;
		return imsidn;
	}
	
	/**
	 * 从请求头部中获取客户端传的地市名
	 * @return 
	 */
	public static String getCity(HttpServletRequest request){
		String city = request.getHeader("city");
		if(StringUtils.isBlank(city)){
			city = "GUANGZHOU";
		}
		return city;
	}
	
	public static String getUserId(HttpServletRequest request){
		return request.getHeader("userId")==null?"0":request.getHeader("userId");
	}
	
	public static String getImsi(HttpServletRequest request){
		return request.getHeader("imsi")==null?"":request.getHeader("imsi");
	}
	
	public static String getDeviceType(HttpServletRequest request){
		return request.getHeader("deviceType")==null?"":request.getHeader("deviceType");
	}
	
	public static String getTimeStamp(HttpServletRequest request){
		return request.getHeader("timeStamp")==null?"":request.getHeader("timeStamp");
	}
	
	public static String getRequestId(HttpServletRequest request){
		return request.getHeader("requestId")==null?"":request.getHeader("requestId");
	}
	
	public static String getHashCode(HttpServletRequest request){
		return request.getHeader("hashCode")==null?"":request.getHeader("hashCode");
	}
	
	public static String getPhonebymsg(HttpServletRequest request){
		String msg = request.getHeader("phonebymsg");
		if(StringUtils.isBlank(msg)){
			msg = "1";
		}
		return msg;
	}



	/**
	 * ��ҳ�����String�ı���ת����UTF-8
	 * added by zhu hongjin at 2006/03/09 22:04
	 */
	public static String getUTF8Parameter(HttpServletRequest request,String name, String defaultValue) {
		String temp = request.getParameter(name);
		if(temp == null || "".equals(temp)){
			return "";
		}
		String result = temp;
		try{
			byte[] b = temp.getBytes("ISO-8859-1");
			result = new String(b,"UTF-8");

		} catch(Exception e){
		}

		return result; 
	}

	public static Map<String, Object> getParamterMap( HttpServletRequest request, List<Params> params) {
		ParamUtils pu=ParamUtils.newParamMapUtil(request);;
		if(params!=null&&!params.isEmpty()){
			for(Params p : params){
				String defType=p.getDef_type();
				Object value=null;
				String _value=p.getDef();
				if (defType.equalsIgnoreCase("Integer"))
					value=Integer.valueOf(_value);
				else if (defType.equalsIgnoreCase("Long"))
					value=new Long(_value);
				else if (defType.equalsIgnoreCase("Float"))
					value=Float.valueOf(_value);
				else if (defType.equalsIgnoreCase("Double"))
					value=Double.valueOf(_value);
				else if (defType.equalsIgnoreCase("Boolean"))
					value=Boolean.valueOf(_value);
				else if (defType.equalsIgnoreCase("Short"))
					value=Short.valueOf(_value);
				else if (defType.equalsIgnoreCase("String"))
					value=String.valueOf(_value);
				pu.putKey(p.getName(),value);
			}
		}
		return pu.getParamMap();
	}
}
