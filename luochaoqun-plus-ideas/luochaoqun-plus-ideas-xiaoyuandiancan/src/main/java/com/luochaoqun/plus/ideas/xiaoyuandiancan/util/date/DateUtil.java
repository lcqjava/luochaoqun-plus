package com.luochaoqun.plus.ideas.xiaoyuandiancan.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间工具类
 * 
 * @author luo.cq
 * @since 2016-05-13
 *
 */
public class DateUtil {

	/**
	 * 默认返回年月日格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDateByPattern(date, "yyyy-MM-dd");
	}

	/**
	 * 返回指定格式的时间字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDateByPattern(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Date paraseDate(String dateStr,String format) throws ParseException  {
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(dateStr);
		
		return date;
	}

	
	/**
	 * 将util.date转换成sql.date
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date parseSqlDate(Date date) {
		if (date != null) {
			return new java.sql.Date(date.getTime());
		}
		return null;
	}

	public static String formatYyyymmddhhmmss(Date date) {
		return formatDateByPattern(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatYyyymmdd(Date date) {
		return formatDateByPattern(date, "yyyy-MM-dd");
	}

	public static int getYear(Date date) {
		Calendar calendar = getCalendar(date);

		return calendar.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Calendar calendar = getCalendar(date);
		return calendar.get(Calendar.MONTH);
	}

	public static Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	public static int getSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	public static long getMillis(Date date) {
		Calendar calendar = getCalendar(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * 日期相减
	 * 
	 * @param date
	 *            Date
	 * @param date1
	 *            Date
	 * @return int
	 */
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 日期相减(返回秒值)
	 * 
	 * @param date
	 *            Date
	 * @param date1
	 *            Date
	 * @return int
	 * @author
	 */
	public static Long diffDateTime(Date date, Date date1) {
		return (Long) ((getMillis(date) - getMillis(date1)) / 1000);
	}

	public static Date getdate(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(date);
	}

	public static Date getdate(String date, String formatStr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.parse(date);
	}

	public static Date getdate1(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(date);
	}

	public static Date getMaxTimeByStringDate(String date) throws Exception {
		String maxTime = date + " 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(maxTime);
	}

	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static Date getCurrentDateTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = sdf.format(date);
		try {
			return sdf.parse(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String result = formatDate(date);
		try {
			return sdf.parse(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String getCurrentDateTimeToStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(getCurrentDateTime());
	}

	public static String getCurrentDateTimeToStr2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(getCurrentDateTime());
	}

	public static Long getWmsupdateDateTime() {
		Calendar cl = Calendar.getInstance();

		return cl.getTimeInMillis();
	}

	/**
	 * 
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Integer getLeftSeconds(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date condition = sdf.parse(date);
		long n = condition.getTime();
		long s = sdf.parse(getCurrentDateTimeToStr2()).getTime();
		// System.out.println("开始时间:"+date+"-->"+(int)((s-n)/1000));
		return (int) ((s - n) / 1000);
	}

	/**
	 * 获得时间戳
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getTime() {
		Date date = new Date();
		return String.valueOf(date.getTime());
	}
	

	public static void main(String[] args) {
		System.out.println(formatYyyymmdd(new Date()));

	}
}
