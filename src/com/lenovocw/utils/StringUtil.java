package com.lenovocw.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil
{
	public static StringBuffer getBuffer()
	{
		return new StringBuffer();
	}
	
	public static boolean isPhoneNumberValid( String phoneNumber )
	{
		boolean isValid = false;
		String expression = "^//(?(//d{3})//)?[- ]?(//d{3})[- ]?(//d{5})$";
		String expression2 = "^//(?(//d{3})//)?[- ]?(//d{4})[- ]?(//d{4})$";
		CharSequence inputStr = phoneNumber;
		Pattern pattern = Pattern.compile( expression );
		Matcher matcher = pattern.matcher( inputStr );
		Pattern pattern2 = Pattern.compile( expression2 );
		Matcher matcher2 = pattern2.matcher( inputStr );
		if ( matcher.matches() || matcher2.matches() )
		{
			isValid = true;
		}
		return isValid;

	}

	/**
	 * 判断字符串的编码
	 * 
	 * @param str
	 * @return
	 */
	public static String getEncoding( String str )
	{
		String encode = "GB2312";
		try
		{
			if ( str.equals( new String( str.getBytes( encode ), encode ) ) )
			{
				return encode;
			}
		} catch ( Exception exception )
		{
		}
		encode = "ISO-8859-1";
		try
		{
			if ( str.equals( new String( str.getBytes( encode ), encode ) ) )
			{
				return encode;
			}
		} catch ( Exception exception1 )
		{
		}
		encode = "UTF-8";
		try
		{
			if ( str.equals( new String( str.getBytes( encode ), encode ) ) )
			{
				return encode;
			}
		} catch ( Exception exception2 )
		{
		}
		encode = "GBK";
		try
		{
			if ( str.equals( new String( str.getBytes( encode ), encode ) ) )
			{
				return encode;
			}
		} catch ( Exception exception3 )
		{
		}
		return "";
	}

	public static String getRandChar( String source )
	{
		if ( StringUtil.isEmpty( source ) )
			return "";
		int length = source.length();
		Random rand = new Random();
		char[] chars = source.toCharArray();
		int index = rand.nextInt( length );
		return chars[index] + "";
	}

	/**
	 * 两日期相减
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static long diffDate( int num )
	{
		Calendar c = Calendar.getInstance();
		c.setTime( new Date() );

		// System.out.println(c.get(Calendar.YEAR));
		// System.out.println(c.get(Calendar.MONTH));
		// System.out.println(c.get(Calendar.DAY_OF_MONTH));
		c.add( Calendar.DAY_OF_MONTH, ( 0 - num ) );
		// c.roll(Calendar.DAY_OF_MONTH, false);
		// System.out.println(c.get(Calendar.YEAR));
		// System.out.println(c.get(Calendar.MONTH));
		// System.out.println(c.get(Calendar.DAY_OF_MONTH));

		return c.getTime().getTime();

		// System.out.println(day+1);
	}

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

	public static boolean toBoolean( String num )
	{
		if ( isEmpty( num ) )
			return false;
		if ( num.equals( "true" ) )
			return true;
		return false;
	}

	public static String getStrDate( String longDate )
	{
		if ( isEmpty( longDate ) )
			return "";
		long time = Long.parseLong( longDate );
		Date date = new Date( time );
		return getStrDate( date, "MM-dd HH:mm" );
	}

	public static String getStrDate( String longDate, String format )
	{
		if ( isEmpty( longDate ) )
			return "";
		long time = Long.parseLong( longDate );
		Date date = new Date( time );
		return getStrDate( date, format );
	}

	public static String getStrDate( long time, String format )
	{
		Date date = new Date( time );
		return getStrDate( date, format );
	}

	public static Date getDate( String longDate )
	{
		if ( isEmpty( longDate ) )
			return null;
		long time = Long.parseLong( longDate );
		return new Date( time );
	}

	public static String[] getData( String str )
	{
		String empty[] = { "", "" };
		if ( isEmpty( str ) )
			return empty;
		try
		{
			int start = str.indexOf( ":" );
			if ( start > 0 && start < str.length() )
			{
				empty[0] = str.substring( 0, start );
				empty[1] = str.substring( start + 1 );
			}
		} catch ( Exception e )
		{
			e.printStackTrace();
		}
		return empty;
	}

	public static final String ENCODING = "utf-8";

	private StringUtil()
	{
	}

	public static String getStrDate()
	{
		SimpleDateFormat dd = new SimpleDateFormat( "yyyy-MM-dd" );
		return dd.format( new Date() );
	}

	public static String getStrDate( Date date )
	{
		SimpleDateFormat dd = new SimpleDateFormat( "yyyy-MM-dd" );
		return dd.format( date );
	}

	public static String getStrDate( Date date, String formate )
	{
		SimpleDateFormat dd = new SimpleDateFormat( formate );
		return dd.format( date );
	}

	public static String encode( String data )
	{
		try
		{
			return URLEncoder.encode( data, ENCODING );
		} catch ( UnsupportedEncodingException e )
		{
			return "";
		}
	}

	public static String decode( String data )
	{
		try
		{
			return URLDecoder.decode( data, ENCODING );
		} catch ( UnsupportedEncodingException e )
		{
			return "";
		}
	}

	// 随机数字产生
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

	/**
	 * 判断指定的字符串是否为null或空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean isNullOrEmpty( String str )
	{
		if ( ( str == null ) || "".equals( str ) )
		{
			return true;
		}

		return false;
	}

	public static final boolean isNull( String str )
	{
		if ( ( str == null ) || "".equals( str ) || "null".equals( str ))
		{
			return true;
		}

		return false;
	}
	
	/**
	 * 判断指定的字符串是否为null、空字符串或空格字符串
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean isNullOrTrimEmpty( String str )
	{
		if ( ( str == null ) || "".equals( str.trim() ) )
		{
			return true;
		}

		return false;
	}

	/**
	 * 判断指定的字符串不为null，空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean isNotNullOrEmpty( String str )
	{
		if ( ( str == null ) || "".equals( str ) )
		{
			return false;
		}

		return true;
	}

	public static String toStrNum( int num, int n )
	{
		String pat = "";
		for ( int i = 0; i < n; i++ )
			pat = pat + "0";
		java.text.DecimalFormat format = new java.text.DecimalFormat( pat );
		return format.format( num );
	}

	public static String filterChar( String str, String replace )
	{
		if ( isEmpty( str ) )
			return "";
		str = str.replace( "\\", replace );
		str = str.replace( "\"", replace );
		str = str.replace( "\'", replace );
		String reg = "@'\"？`~!@#$%^&*()_+={[}]|\\:;<>.?/-Aa%！￥……（）、：；“”’‘《》，。？－";
		for ( int i = 0; i < reg.length(); i++ )
		{
			str = str.replace( reg.substring( i, i + 1 ), replace );
		}

		return str;
	}

	public static String urlDecode( String str )
	{
		if ( isEmpty( str ) )
			return "";
		return str.replace( "&amp;", "&" );
	}

	public static boolean isMobile( String str )
	{
		if ( str == null || str.equals( "" ) || str.trim().equals( "" ) )
			return false;

		if ( str.length() == 11 && isNum( str ) )
		{
			if ( str.startsWith( "1" ) )
				return true;
		}

		return false;
	}

	public static boolean isNum( String str )
	{
		for ( int i = str.length(); --i >= 0; )
			if ( !Character.isDigit( str.charAt( i ) ) )
				return false;
		return true;
	}

	public static boolean isEmpty( Object str )
	{
		if ( str == null || str == "" || str.toString().trim().equals( "" ) )
			return true;
		return false;
	}
	public static boolean isEmpty( String str )
	{
		if ( str == null || str == "" || str.trim().equals( "" ) )
			return true;
		return false;
	}

	public static String gbEncoding( final String gbString )
	{
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for ( int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++ )
		{
			String hexB = Integer.toHexString( utfBytes[byteIndex] );
			if ( hexB.length() <= 2 )
			{
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\\\u" + hexB;
		}
//		System.out.println( "unicodeBytes is: " + unicodeBytes );
		return unicodeBytes;
	}
	
	public static String gbEncoding( final char utfByte )
	{
//		char[] utfBytes = gbString.toCharArray();
		String hexB = Integer.toHexString( utfByte );
		if ( hexB.length() <= 2 )
		{
			hexB = "00" + hexB;
		}
//		unicodeBytes = unicodeBytes + "\\\\u" + hexB;
//		System.out.println( "unicodeBytes is: " + hexB );
		return hexB;
	}

	/** */
	/*****************************************************
	 * 功能介绍:将unicode字符串转为汉字 输入参数:源unicode字符串 输出参数:转换后的字符串
	 *****************************************************/
	@SuppressWarnings("unused")
	private String decodeUnicode( final String dataStr )
	{
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while ( start > -1 )
		{
			end = dataStr.indexOf( "\\\\u", start + 2 );
			String charStr = "";
			if ( end == -1 )
			{
				charStr = dataStr.substring( start + 2, dataStr.length() );
			} else
			{
				charStr = dataStr.substring( start + 2, end );
			}
			char letter = (char) Integer.parseInt( charStr, 16 ); // 16进制parse整形字符串。
			buffer.append( new Character( letter ).toString() );
			start = end;
		}
		return buffer.toString();
	}
	
	public static String stringToUnicode(char chineseChar)
	{
		return Integer.toHexString(chineseChar).toUpperCase();
	}
	
	public static boolean gbk(String str)
	 {
	     char[] chars=str.toCharArray(); 
	     boolean isGB2312=false; 
	     for(int i=0;i<chars.length;i++){
             byte[] bytes=(""+chars[i]).getBytes(); 
             if(bytes.length==2){ 
                 int[] ints=new int[2]; 
                 ints[0]=bytes[0]& 0xff; 
                 ints[1]=bytes[1]& 0xff; 
                 if(ints[0]>=0x81 && ints[0]<=0xFE && ints[1]>=0x40 && ints[1]<=0xFE){ 
                     isGB2312=true; 
                     break; 
                 } 
             } 
	     } 
	  return isGB2312;
	 }
	
	/**去除数组中重复的记录  */
	public static Integer[] sort(int[] a){
		int num = 0;
		System.out.println("原数组的元素为：");
		for (int i = 0; i < a.length; i++) {
			System.out.print(" " + a[i]);
		}

		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] > a[i]) {
					int n = a[i];
					a[i] = a[j];
					a[j] = n;
				}
			}
		}

		System.out.println("");
		System.out.println("排序后的数组元素为：");
		for (int i = 0; i < a.length; i++) {
			System.out.print(" " + a[i]);
		}
		Integer[] b = getDistinct(a);
		System.out.println("");
		System.out.println("删除重复元素后的数组元素为：");
		for (int i = 0; i < b.length; i++) {
			System.out.print(" " + b[i]);
		}
		return b;
	}
	
	/**数组去重*/
	public static Integer[] getDistinct(int num[]) {
        List<Integer> list = new java.util.ArrayList<Integer>();
        for (int i = 0; i < num.length; i++) {
            if (!list.contains(num[i])) {//如果list数组不包括num[i]中的值的话，就返回true。
                list.add(num[i]); //在list数组中加入num[i]的值。已经过滤过。
            }
        }
        return list.toArray(new Integer[0]); 
	}
	

	
	
	public static void main( String[] args )
	{
		int[] a = {0,0,0,1,5,3,2,1,6,0,2,3,8,5,5,6};
		sort(a);
	}
}
