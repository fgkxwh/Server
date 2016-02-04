package com.fgk.im.util;

import hirondelle.date4j.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtil {
	static final Logger logger = LoggerFactory.getLogger(TimeUtil.class);
	public static final TimeZone tz = TimeZone.getTimeZone("GMT+8");//设置时区为北京时间
	
	public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_TIME = "HH:mm:ss";
	/**
	 * 日期和结束日期，判断是否需要处理该条记录
	 * @param weekArray
	 * @param begin_date
	 * @param end_date
	 * @param now
	 * @return
	 */
	public static boolean checkTime(String[] weekArray, String begin_date,
			String end_date, DateTime now) {
		if (weekArray == null || weekArray.length == 0) {//不需要判断星期
			if (begin_date == null) {
				// 跳出
				return false;
			} else {
				// 按照日期处理
				DateTime sDate = new DateTime(begin_date);
				DateTime eDate = new DateTime(end_date);
				if (now.lteq(eDate) && sDate.lteq(now)) {
					// 在当前日期中判断是否在开始和结束时间�?
					return true;
				}
			}
		} else {
			// 按照星期处理
			int day = now.getWeekDay();
			for (int i = 0; i < weekArray.length; i++) {
				logger.debug("week i==" + i + " ===" + weekArray[i]+" now day= "+day);
				if (day == Integer.parseInt(weekArray[i])) {
					// 在当前星期中
					return true;
				}
			}
			System.out.println("@@@@@@@day===" + now.toString());
			return false;
		}
		return false;
	}
	/**
	 * 判断是否是同一天
	 * @param time
	 * @return
	 */
	public static boolean isSameDay(long time1, long time2){
		DateTime startTime = TimeUtil.getTime(time1);
		DateTime endTime = TimeUtil.getTime(time2); 
		return startTime.isSameDayAs(endTime);
	}
	/**
	 * 判断是否是当天
	 * @param time
	 * @return
	 */
	public static boolean isSameDay(long time){
		DateTime now = TimeUtil.now();//获得当前时间
		DateTime endTime = TimeUtil.getTime(time); 
		return now.isSameDayAs(endTime);
	}
	
	/**
	 * 判断是否同一周
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean isSameWeek(long time1, long time2){
		if(time1 == 0 || time2 == 0){
			return false;			
		}
		
		Calendar calendar = Calendar.getInstance();
    	calendar.setTimeInMillis(time1);
    	
    	calendar.set(Calendar.HOUR, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MILLISECOND, 0);
    	
    	calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    	long begin = calendar.getTimeInMillis();
    	
    	calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
    	long end = calendar.getTimeInMillis();
    	
    	if(time2 >= begin && time2 <= end + 24 * 60 * 60 * 1000){
    		return true;
    	}else{
    		return false;
    	}
	}
	public static boolean isSameWeek(long time){
		return isSameWeek(time, TimeUtil.now().getMilliseconds(tz));
	}
	
	/**
	 * 判断是否同一周
	 * @param time1
	 * @param time2
	 * @return
	 * @throws ParseException
	 */
	public static boolean isSameWeek(String time1, String time2) throws ParseException{
		Calendar calendar = Calendar.getInstance();//获取Calendar实例 
		calendar.setTime(DateFormat.getDateInstance().parse(time1));
		int week1 = calendar.WEEK_OF_YEAR;
		calendar.setTime(DateFormat.getDateInstance().parse(time2));
		int week2 = calendar.WEEK_OF_YEAR;
		
		return week1 == week2 ? true : false;		
	}
	
	public static int getWeeks(long time1, long time2){
		Calendar calendar = Calendar.getInstance();//获取Calendar实例 
		calendar.setTimeInMillis(time1);
		int week1 = calendar.WEEK_OF_YEAR;
		calendar.setTimeInMillis(time2);
		int week2 = calendar.WEEK_OF_YEAR;
		
		return week2 - week1;
	}
	
	public static int getWeeks(String time1, String time2) throws ParseException{
		Calendar calendar = Calendar.getInstance();//获取Calendar实例 
		calendar.setTime(DateFormat.getDateInstance().parse(time1));
		int week1 = calendar.WEEK_OF_YEAR;
		calendar.setTime(DateFormat.getDateInstance().parse(time2));
		int week2 = calendar.WEEK_OF_YEAR;
		
		return week2 - week1;
	}
	
	/**
	 * 获得时间
	 * @param time
	 * @return
	 */
	public static DateTime getTime(long time){
		DateTime dt = DateTime.forInstant(time, tz);
		return dt;
	}
	
	/**
	 * 得到年，例如 2015
	 * @param time
	 * @return 年的数字形式
	 */
	public static int getYear(long time){
		return  getTime(time).getYear();
	}
	
	/**
	 * 得到月，例如 1
	 * @param time
	 * @return 月的数字形式
	 */
	public static int getMonth(long time){
		return getTime(time).getMonth();
	}
	
	/**
	 * 得到天，例如 25
	 * @param time
	 * @return 天的数字形式
	 */
	public static int getDay(long time){
		return getTime(time).getDay();
	}
	
	public static DateTime getTime(String str){
		return new DateTime(str);
	}
	/**
	 * 获得当前时间
	 * @return
	 */
	public static DateTime now(){
		return DateTime.now(tz);//获得当前时间
	}
	
	public static long getDateTime(DateTime dateTime){
		return dateTime.getMilliseconds(tz);
	}
	/**
	 * 返回当前的日期，格式2012-07-23
	 * @return
	 */
	
	public static DateTime today(){
		return DateTime.today(tz);
	}
	/**
	 * 返回两个日期之间的全部天数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDays(String start, String end){
		
		DateTime startDate = TimeUtil.getTime(start);
		DateTime endDate = TimeUtil.getTime(end);
	
		//返回两个日期之间的时间差,�?012-11-1 2012-11-20  差�?=19
		int days = startDate.numDaysFrom(endDate);
		return days;
	}
	
	public static int getDays(Date begin){
		return getDays(begin.getTime());
	}
	
	
	/**
	 * 将字符串时间转化为long
	 * @param time
	 * @return
	 */
	public static long getTimes(String time){
		if(null == time || "".equals(time)){
			return 0;
		}
		return getTime(time).getMilliseconds(TimeUtil.tz);
	}
	
	/**
	 * 获取当前日期，格式：yyyy-MM-dd
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat(FORMAT_DATE);
		return sf.format(date);
	}

	/**
	 * 获取当前时间，格式：HH:mm:ss 时间24小时制
	 * @return
	 */
	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat(FORMAT_TIME);
		return sf.format(date);
	}
	
	/**
	 * 根据时间数�?返回yyyy-MM-dd HH:mm:ss 格式日期时间,时间24小时制
	 * @param time
	 * @return
	 */
	public static String getDataTime(long time){
		Date date = new Date(time);
		SimpleDateFormat sf = new SimpleDateFormat(FORMAT_DATE_TIME);
		return sf.format(date);
	}
	
	/**
	 * 获取指定时间日期格式，格式：yyyy-MM-dd
	 * @return
	 */
	public static String getDate(long time){
		Date date = new Date(time);
		SimpleDateFormat sf = new SimpleDateFormat(FORMAT_DATE);
		return sf.format(date);
	}
	
	/**
	 * 返回两个时间之间天数间隔
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDays(long start, long end){
		DateTime startDate = TimeUtil.getTime(start);
		DateTime endDate = TimeUtil.getTime(end);
		
		return startDate.numDaysFrom(endDate);
	}
	public static int getDays(long start){
		DateTime startDate = TimeUtil.getTime(start);
		DateTime endDate = TimeUtil.now();
		
		return startDate.numDaysFrom(endDate); 
	}
	
	/**
	 * 时间间隔多少小时，不足一小时舍弃
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getHours(long start,long end){
		long interval = end - start;
		int hour = (int) ((interval/1000)/(60 * 60));
		return hour;
	}
	
	/**
	 * 日期字符串转时间
	 * @param date
	 * @param format 
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String date, String format) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(date);		
	}
	public static Date getDate(String date) throws ParseException{
		return getDate(date, FORMAT_DATE_TIME);		
	}
	/**
	 * 得到当前日期的年月的字符串形式
	 * @return 例如：201512(2015年12月)
	 */
	public static String getYYMM(){
		String today = today().toString();  
		String[] d = today.split("-");
		return d[0] + d[1];
				
	}
	/**
	 * 根据long型的时间，获取日期的年月的字符串形式
	 * @param dateTime
	 * @return 例如：201512(2015年12月)
	 */
	public static String getYYMM(long dateTime){
		String date =  getDate(dateTime);
		String[] d = date.split("-");
		return d[0] + d[1];
		
	}
	
	/**
	 * 得到当前日期的年月日的字符串形式
	 * @return 例如：20151201(2015年12月1日)
	 */
	public static String getYYMMDD(){
		String today = today().toString();  
		String[] d = today.split("-");
		return d[0] + d[1] + d[2];
	}
	
	/**
	 * 根据long型的时间，获取日期的年月日的字符串形式
	 * @param dateTime
	 * @return
	 */
	public static String getYYMMDD(long dateTime){
		String date =  getDate(dateTime);
		String[] d = date.split("-");
		return d[0] + d[1] + d[2];
	}
	
	
	
	/**
	 * 将年月日整数形式转换为规整的，如20150101的字符形式
	 * @param year
	 * @param month
	 * @param day
	 * @return 字符串的年月日
	 */
	public static String getYYMMDD(int year,int month,int day){
		String strMonth = String.valueOf(month);
		String strDay = String.valueOf(day);
		if (month < 10 ) {
			
			strMonth = String.valueOf(0) + strMonth;
		}
		if (day < 10) {
			
			strDay = String.valueOf(0) + strDay;
		}
	
		return String.valueOf(year) + strMonth + strDay;
	}
	
	/**
	 * 将年月整数形式转换为规整的，如201501的字符形式
	 * @param year
	 * @param month
	 * @return 字符串的年月
	 */
	public static String getYYMM(int year,int month){
		String strMonth = String.valueOf(month);
		if (month < 10 ) {
			
			strMonth = String.valueOf(0) + strMonth;
		}
	
		return String.valueOf(year) + strMonth ;
	}
	
	
	public static long getDayStart(int year,int month,int day) throws ParseException{
		
		String strDateTime = year+"/"+month+"/"+day + " 00:00:00";
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date  start = formater.parse(strDateTime);
		return start.getTime();
	
	}
	
	public static long getDayEnd(int year,int month,int day) throws ParseException{
		
		String strDateTime = year+"/"+month+"/"+day + " 23:59:59";
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date  end = formater.parse(strDateTime);
		return end.getTime();
	}
	
	/**
	 * 计算某年某月一共有多少天  未完成
	 * @param year
	 * @param month
	 * 
	 * @return 一个月有的天数
	 */
	public int getDays(int year,int month){
		return 0;
	}
}
