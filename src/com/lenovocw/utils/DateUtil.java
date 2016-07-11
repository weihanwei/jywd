package com.lenovocw.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

/**
 * <span> <b>功能：</b> </span><br />
 * <span> <!--在这下面一行写功能描述-->
 *   时间帮助类
 *</span><br /><br />
 * <span> Copyright LENOVO </span><br /> 
 * <span> Project Name AppMusicServer </span><br /> 
 * <span> Author  ZengZS </span><br /> 
 * <span> Create Time 2012-3-20  下午04:46:11 </span><br /> 
 * <span> App version 1.0.0 </span> <br />
 * <span> JDK version used 6.0 </span><br /> 
 * <span> Modification history none    </span><br /> 
 * <span> Modified by none    </span><br />
 * 
 */
public class DateUtil{

	/**
	 * 
	 * <span><b>功能</b></span><br />
	 * <!--在这下面一行写功能描述-->
	 *  取当前时间至第二天凌晨的时间差，秒数
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
	 * <span> Create Time  2012-3-20  下午05:01:39 </span><br /> 
	 *
	 */
	public static int nowToNextDaySeconds(){
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE,0);
		long sec = c.getTimeInMillis();
		return (int)(sec - now)/1000;
	}
	
	/**
	 * 计算当前时间与某一时间相隔多少天、或者小时、或者分钟、或者秒
	 * @param now
	 * @param before
	 * @return
	 */
	
	public static String getDisplayDate( Date now, Date before )
	{
		long diff = now.getTime() - before.getTime();
		if(diff>=DateFormatHelp.MILLSECOND_OF_WEEK)
			return DateFormatHelp.MM_dd_HH_mm_ss.format(before);
		if(diff>=DateFormatHelp.MILLSECOND_OF_DAY)
			return (diff / DateFormatHelp.MILLSECOND_OF_DAY) + "天前";
		if(diff>=DateFormatHelp.MILLSECOND_OF_HOUR)
			return (diff / DateFormatHelp.MILLSECOND_OF_HOUR) + "小时前";
		if(diff>=DateFormatHelp.MILLSECOND_OF_MINUTE)
			return (diff / DateFormatHelp.MILLSECOND_OF_MINUTE) + "分钟前";
		return (diff / 1000) + "秒前";
	}
	
	/**
	 * 获取一周中的第一天
	 */
	public static String getFirstDateOfWeek(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int dd = c.get(GregorianCalendar.DAY_OF_WEEK);
		if (dd == 1)
			dd = 7;
		else if (dd > 1)
			dd = dd - 1;
		c.add(Calendar.DATE, 1 - dd);
		String date = c.get(Calendar.YEAR) + "-" + towStr(1 + c.get(Calendar.MONTH)) + "-" + towStr(c.get(Calendar.DATE));
		return date;
	}

	/**
	 * 获取一周中的最后一天
	 */
	public static String getLastDateOfWeek() {
		Calendar c = Calendar.getInstance();
		int dd = c.get(GregorianCalendar.DAY_OF_WEEK);
		if (dd == 1)
			dd = 7;
		else if (dd > 1)
			dd = dd - 1;
		c.add(Calendar.DATE, 7 - dd);
		String date = c.get(Calendar.YEAR) + "-" + towStr(1 + c.get(Calendar.MONTH)) + "-" + towStr(c.get(Calendar.DATE));
		return date;
	}
	
	/**
	 * 获取一周中的最后一天
	 */
	public static String getStrDate() {
		Calendar c = Calendar.getInstance();
		String date = c.get(Calendar.YEAR) + "-" + towStr(1 + c.get(Calendar.MONTH)) + "-" + towStr(c.get(Calendar.DATE));
		return date;
	}
	
	public static String towStr(int n) {
		if (n < 10)
			return "0" + n;
		else
			return "" + n;
	}
	
	/**
	 * 获取相差月份
	 * @param month
	 * @return
	 */
	public static String getMonth(int month){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		
		return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-1";
	}
	
	/**
	 * 根据日期获取毫秒
	 * @param dateStr
	 * @return
	 */
	public static long getTimeMillis(String dateStr){
		long time = 0;
		try {
			time = DateFormatHelp.yyyy_MM_dd_HH_mm_ss.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
	
	/**
	 * 获取日期,格式为yyyy-MM-dd
	 * @param day 
	 * @return
	 */
	public static String getYesterdayDate(int day){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH,  day);
		return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH);
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
	 * 两个时间相减，得到相差的毫秒数
	 * @param date1 较大的日期
	 * @param date2 较小的日期
	 * @return
	 */
	public static long getTwoDate(Date date1,Date date2){
		long day = (date1.getTime()-date2.getTime());
		return day;
	}
	
	/**
	 * 获取当前时间
	 * 
	 * @param format
	 * @return
	 */
	public static String getNowStrDate(String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		String date = sdf.format(new Date());
		return date;
	}
	public static long getMonthDiff(String startDate, String endDate,String format) throws ParseException {
	        long monthday;
	        SimpleDateFormat fmt = new SimpleDateFormat(format);
	        Date startDate1 = fmt.parse(startDate);

	        Calendar starCal = Calendar.getInstance();
	        starCal.setTime(startDate1);

	        int sYear  = starCal.get(Calendar.YEAR);
	        int sMonth = starCal.get(Calendar.MONTH);
	        int sDay   = starCal.get(Calendar.DAY_OF_MONTH);

	        Date endDate1 = fmt.parse(endDate);
	        Calendar endCal = Calendar.getInstance();
	        endCal.setTime(endDate1);
	        int eYear  = endCal.get(Calendar.YEAR);
	        int eMonth = endCal.get(Calendar.MONTH);
	        int eDay   = endCal.get(Calendar.DAY_OF_MONTH);

	        monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));
	        
	        //这里计算零头的情况，根据实际确定是否要加1 还是要减1
	        if (sDay < eDay) {
	            monthday = monthday+1;
	        }
	        return monthday-1;
	    }
	
	public static void main(String[] args) {
		System.out.println(getnowDate("yyyy-MM-dd HH:mm:ss"));
		/*try {
			System.out.println("-----------:"+ (getMonthDiff("20121104",DateUtil.getNowStrDate("yyyyMMdd"),"yyyyMMdd")));
			System.out.println("-----------:"+ (Long.valueOf((DateUtil.getForDate("20141110145445".substring(0,8),DateUtil.getNowStrDate("yyyyMMdd"),"yyyyMMdd")))/365));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public static long getForDate(String start,String end, String format) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Calendar cal1=Calendar.getInstance();
		cal1.setTime(sdf.parse(start));
		Calendar cal2=Calendar.getInstance();
		cal2.setTime(sdf.parse(end));
		long l=cal2.getTimeInMillis()-cal1.getTimeInMillis();
		int days=new Long(l/(1000*60*60*24)).intValue();
		return days;
	}
	
	public static String getnowDate(String format){
		SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式g.valueOf(min)+String.valueOf(se);
	return df.format(new Date());
	}
	
	
	public static String getThisMonthLastDay(String format1){
		// 获取Calendar  
		Calendar calendar = Calendar.getInstance();  
		// 设置时间,当前时间不用设置  
		// calendar.setTime(new Date());  
		// 设置日期为本月最大日期  
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));  
		  
		// 打印  
		DateFormat format = new SimpleDateFormat(format1);  
		return  format.format(calendar.getTime());  
	}
}
