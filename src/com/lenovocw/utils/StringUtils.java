package com.lenovocw.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * 字符串处理相关工具类
 * 
 * @author xiaoxh
 * @link 
 * 
 * @version $Revision: 1.00 $ $Date: 2010-04-12
 */

public class StringUtils {
	
	public static String intToStrTime(int timeInSeconds)
	{
		 int hours, minutes, seconds;
	     hours = timeInSeconds / 3600;
	     timeInSeconds = timeInSeconds - (hours * 3600);
	     minutes = timeInSeconds / 60;
	     timeInSeconds = timeInSeconds - (minutes * 60);
	     seconds = timeInSeconds;
	     String str = toStrNum(hours, 2) + ":" + toStrNum(minutes, 2) + ":" + toStrNum(seconds, 2);
	     System.out.println(str);
	     return str;
	     
	}
	
	/** 返回指定个数的随机数字串 */
	public static String getRandomStr( int num )
	{
		StringBuffer temp = new StringBuffer();
		Random rand = new Random();
		for ( int i = 0; i < num; i++ )
		{
			temp.append( rand.nextInt( 10 ) );
		}
		return temp.toString();
	}
	
	/** 将字符串转移成布尔数*/
	public static boolean toBoolean( String num )
	{
		if ( isEmpty( num ) )
			return false;
		if ( num.equals( "true" ) )
			return true;
		return false;
	}
	
	/** 将字符串转移成整数 */
	public static int toInt( String num )
	{
		try
		{
			return Integer.parseInt( num );
		} catch ( Exception e )
		{
			return 0;
		}
	}

	/** 将字符串转移成长整数 */
	public static long toLong( String num )
	{
		try
		{
			return Long.parseLong( num );
		} catch ( Exception e )
		{
			return 0;
		}
	}
	
	/** 将字符串转移成浮点数 */
	public static float toFloat( String num )
	{
		try
		{
			return Float.parseFloat( num );
		} catch ( Exception e )
		{
			return 0;
		}
	}
	
	/**
	 * 格式一个日期
	 * @param longDate 需要格式日期的长整数的字符串形式
	 * @param format 格式化参数
	 * @return 格式化后的日期
	 */
	public static String getStrDate( String longDate, String format )
	{
		if ( isEmpty( longDate ) )
			return "";
		long time = Long.parseLong( longDate );
		Date date = new Date( time );
		return getStrDate( date, format );
	}

	/**
	 * 格式一个日期
	 * @param longDate 需要格式日期的长整数
	 * @param format 格式化参数
	 * @return 格式化后的日期
	 */
	public static String getStrDate( long time, String format )
	{
		Date date = new Date( time );
		return getStrDate( date, format );
	}

	/**
	 * 转换成一个日期
	 * @param longDate 需要格式日期的长整数的字符串形式
	 * @return 转换后的日期
	 */
	public static Date getDate( String longDate )
	{
		if ( isEmpty( longDate ) )
			return null;
		long time = Long.parseLong( longDate );
		return new Date( time );
	}

	/** 返回当前日期的格式化（yyyy-MM-dd）表示 */
	public static String getStrDate()
	{
		SimpleDateFormat dd = new SimpleDateFormat( "yyyy-MM-dd" );
		return dd.format( new Date() );
	}
	
	/** 返回一个StringBuffer对象 */
	public static StringBuffer getBuffer()
	{
		return new StringBuffer();
	}

	/** 返回指定日期的格式化（yyyy-MM-dd）表示 */
	public static String getStrDate( Date date )
	{
		SimpleDateFormat dd = new SimpleDateFormat( "yyyy-MM-dd" );
		return dd.format( date );
	}
	

	/**
	 * 返回当前日期的格式化表示
	 * @param date 指定格式化的日期
	 * @param formate 格式化参数
	 * @return 
	 */
	public static String getStrDate( Date date, String formate )
	{
		SimpleDateFormat dd = new SimpleDateFormat( formate );
		return dd.format( date );
	}
	
	public static String encodeUtf8(String str)
	{
		if(isEmpty(str))
			return "";
		try {
			return URLEncoder.encode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String toStrNum(int num, int n)
	{
		String pat = "";
		for(int i = 0; i < n; i++)
			pat = pat + "0";
		java.text.DecimalFormat format=new java.text.DecimalFormat(pat);
		return format.format(num);
	}
	
	public static String getItOfLogin(String text)
	{
		String bstr = "<input type=\"hidden\" name=\"lt\" value=\"";
		int begin = text.indexOf(bstr);
		int end = 0;
		String temp = "";
		if(begin > 0)
			end = text.indexOf("\"", begin + bstr.length());
		if( begin > 0 && end > 0 && begin < end)
		{
			temp = text.substring(begin + bstr.length(), end);
		}
		
		return temp;
	}
	
	
	public static String filterChar(String str, String replace)
	{
		if(isEmpty(str))
			return "";
		str = str.replace("\\", replace);
		str = str.replace("\"", replace);
		str = str.replace("\'", replace);
		String reg = "@'\"？`~!@#$%^&*()_+={[}]|\\:;<>.?/-Aa%！￥……（）、：；“”’‘《》，。？－";
		for(int i = 0; i < reg.length(); i++)
		{
			str = str.replace(reg.substring(i, i + 1), replace);
		}
		
		return str;
	}
	
	public static String urlDecode(String str)
	{
		if(isEmpty(str))
			return "";
		return str.replace("&amp;", "&");
	}
	
	public static boolean isMobile(String str)
	{
		if(str == null || str.equals("") || str.trim().equals(""))
			return false;
		
		if(str.length() == 11 && isNum(str))
		{
			if(str.startsWith("189") || str.startsWith("153") || str.startsWith("133"))
				return true;
		}
		
		return false;
	}
	
	public static boolean isNum(String str)
	{
		for (int i = str.length();--i>=0;)
			if (!Character.isDigit(str.charAt(i)))
				return false;
		return true;
	}
	
	public static boolean isEmpty(String str)
	{
		if(str == null || str == "" || str.trim().equals(""))
			return true;
		return false;
	}
	
	public static boolean isNotEmpty(String str) {
		return (str != null && str.length() > 0);
	}	
	
	public static void main(String args[])
	{
//		intToStrTime(1000);
		System.out.println(formartMobil(null));
	}
	
	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 *  将用户手机号中间四位换成星号
	 * <br /><br />  
	 * <span><b>参数</b></span><br />
	 *  <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
	 *  phone 手机号<br />
	 * <br /><br />
	 * <span><b>返回值 </b> </span><br>
	 * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->  
	 *
	 * </span>
	 * <br /><br />
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-3-31  下午05:29:44 </span><br /> 
	 *
	 */
	public static String formartMobil(String phone){
		if(StringUtils.isEmpty(phone) || phone.trim().length() != 11){
			return phone;
		}
		
		return phone.substring(0,3) + "****" + phone.substring(7);
	}
	
	
}