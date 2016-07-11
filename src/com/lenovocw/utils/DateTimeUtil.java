package com.lenovocw.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * @author Pason
 *
 */
public class DateTimeUtil {
	
	/**
	 * 得到当前日期
	 * @return
	 */
	public static Date getNowDate(){
		Date date = new Date();
		return date;
	}
	
	/**
	 * 得到当前日期字符串
	 * @param format 格式
	 * @return
	 */
	public static String getNowDateStr(String format){
		Date date = getNowDate();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
		
	}

	/**
	 * 得到指定天数之后的日期，days可以为负数
	 * @param days
	 * @return
	 */
	public static Date getAfterDays(int days,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date date = new Date();
		try {
			date = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		Date newDate = new Date(c.getTimeInMillis());
		
		return newDate;
	}
	
	/**
	 * 按格式化字符把日期转换成字符串型
	 * @param date
	 * @param format 指定格式
	 * @return
	 * String
	 */
	public static String date2String(Date date,String format){
		if (null == date)
            return "";
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
	}
	
	/**
     * 取得指定日期中的小时数 
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        if (null == date)
            return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    
    /**
     * 取得指定日期中的分钟数 
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        if (null == date)
            return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }
    
    /**
     * 取得指定日期中的秒数 
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        if (null == date)
            return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
        
    }
    
	/**
	 * 将字符串转换为日期
	 * @param str 需要转换的日期字符串
	 * @param format 指定格式
	 * @return
	 */
	public static Date string2Date(String str,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 得到两个日期间隔天数
	 * @param str1 较大的日期
	 * @param str2 较小的日期
	 * @return
	 */
	public static int getTwoDate(String str1,String str2){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			Date date1 = sdf.parse(str1);
			Date date2 = sdf.parse(str2);
			day = (date1.getTime()-date2.getTime())/(60*60*24*1000);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int)day;
		
	}
	
	/**
	 * 得到两个日期间隔天数
	 * @param date1 较大的日期
	 * @param date2 较小的日期
	 * @return
	 */
	public static int getTwoDate(Date date1,Date date2){
		long day = (date1.getTime()-date2.getTime())/(60*60*24*1000);
		return (int)day;
	}
	
	/**
	 * 比较两个日期是否相等
	 * @param date1 
	 * @param date2
	 * @return
	 */
	public static boolean equalDate(Date date1,Date date2) {
        if (date1 == date2) {
            return true;
        } else if (null == date1) {
            return false;
        }
        return date1.equals(date2);
    }
	
	/**
	 * 比较两个日期是否相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equalDate(String str1,String str2){
		Date date1 = string2Date(str1,"yyyy-MM-dd HH:mm:ss");
		Date date2 = string2Date(str2,"yyyy-MM-dd HH:mm:ss");
		return equalDate(date1,date2);
	}
	
	/**
	 * 获取当前月推几个月 mun可以为负数
	 */
	public static String getDateAffterMonth(int mun,String format){
  	  Calendar calendar = Calendar.getInstance();
		 // SimpleDateFormat matter=new SimpleDateFormat("MM");
		  SimpleDateFormat matter2=new SimpleDateFormat(format);
  	      calendar.add(Calendar.MONTH,mun);
	      Date date = calendar.getTime();
	      String timeStr=matter2.format(date);
	      return timeStr;
	}
	/**
	 * 获取每个月最后一天
	 * @return
	 * String
	 */
	public static String getDayLast(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
        return endStr.toString();
	}
	
	/**
	 *获取某年某月最后一天
	 * @param args
	 */
	  public static String getLastDayOfMonth(int year,int month)
	  {
	    Calendar cal = Calendar.getInstance();
	    //设置年份
	    cal.set(Calendar.YEAR,year);
	    //设置月份
	    cal.set(Calendar.MONTH, month-1);
	    //获取某月最大天数
	    int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    //设置日历中月份的最大天数
	    cal.set(Calendar.DAY_OF_MONTH, lastDay);
	    //格式化日期
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    String lastDayOfMonth = sdf.format(cal.getTime());
	    
	    return lastDayOfMonth;
	  }
	public static void main(String[] args) {
		
		//System.out.println(DateTimeUtil.getAfterDays(-3,"yyyy-MM-dd").toString());
		Date date1 = DateTimeUtil.getNowDate();
		/*
		Calendar c = Calendar.getInstance();
		c.setTime(date1);
		c.add(Calendar.DATE, 0);
		Date date2 = c.getTime();
		//int day = getTwoDate(date1,date2);
		boolean b = equalDate(date1,date2);
		if(b){

			System.out.println("yes");
		}else{
			System.out.println("no");
		}
		*/
		System.out.println(getLastDayOfMonth(2015,7));
		
	}
}
