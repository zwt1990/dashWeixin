package cjc.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.util.StringUtils;

public class DateUtils {
	private static DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	
	public static long diffDay(Date date1, Date date2) {
		long diff = date1.getTime() - date2.getTime();
		diff = diff < 0 ? -1 * diff : diff;
		return diff / (24 * 60 * 60 * 1000);
	}

	public static Date getStartDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getEndDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static Date getStartMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return getStartDate(cal.getTime());
	}

	public static Date getEndMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getEndDate(cal.getTime());
	}

	public static Date getStartWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		cal.add(Calendar.DATE, 1);
		return getStartDate(cal.getTime());
	}

	public static Date getEndWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, 7);
		cal.add(Calendar.DATE, 1);
		return getEndDate(cal.getTime());
	}

	public static Date getStartYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return getStartDate(cal.getTime());
	}

	public static Date getEndYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, 1);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return getEndDate(cal.getTime());
	}

	/**
	 * 取得指定天数后的时间
	 * 
	 * @param date
	 *            基准时间
	 * @param dayAmount
	 *            指定天数，允许为负数
	 * @return 指定天数后的时间
	 */
	public static Date addDay(Date date, int dayAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dayAmount);
		return calendar.getTime();
	}
	
	/**
	 * 取得指定天数后的时间
	 * 
	 * @param date
	 *            基准时间
	 * @param dayAmount
	 *            指定天数，允许为负数
	 * @return 指定天数后的时间
	 */
	public static String addDay(String date, int dayAmount) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		Date can;
		try {
			can = datetimeFormat.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(can);
			calendar.add(Calendar.DATE, dayAmount);
			return datetimeFormat.format(calendar.getTime());
		} catch (ParseException e) {
			return null;
		}
		
	}

	/**
	 * 得到当前时间＋addDay（天）的时间
	 * 
	 * @param addDay
	 * @return
	 */
	public static Date addMonth(Date date, int monthAmount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, monthAmount);
		return cal.getTime();
	}

	/**
	 * 取得指定分钟数后的时间
	 * 
	 * @param date
	 *            基准时间
	 * @param minuteAmount
	 *            指定分钟数，允许为负数
	 * @return 指定分钟数后的时间
	 */
	public static Date addMinute(Date date, int minuteAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minuteAmount);
		return calendar.getTime();
	}
    /** 
     * 比较两个日期相差的秒数
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int getMarginSeconds(Date currDate, Date oldDate) {  
        try {  
            long l = currDate.getTime() - oldDate.getTime();  
            return (int) (l / 1000);
        } catch (Exception e) {  
            return 0;  
        }  
    }  
    
    
	public final static Date getCurrentTime() {
		return new Date(System.currentTimeMillis());
	}
	
	public final static String getCurrentDateStr(){
		return dateFormatter.format(System.currentTimeMillis());
	}
	
	public final static String getCurrentDateTimeStr(){
		return dateTimeFormatter.format(System.currentTimeMillis());
	}
	
	public final static String getCurrentTimeStr(String format){
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		return simpledateformat.format(System.currentTimeMillis());
	}

	public static String getDateString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(date==null){
			return null;
		}
		return df.format(date);
	}

	public static String getDateString(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static String getDateTimeString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(date==null){
			return null;
		}
		return df.format(date);
	}

	public static String getDateTimeString(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	
	public static Date parseDate(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(date);
	}

	public static Date parseDate(String date, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(date);
	}

	public static Date parseDateTime(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.parse(date);
	}

	public static Date parseDateTime(String date, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(date);
	}
	public static  String getWeekChinese(Date date){
		 Calendar cd = Calendar.getInstance();
		 cd.setTime(date);
		String chinesWeek="";
		switch (cd.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			chinesWeek= "日";
			break;
		case 2:
			chinesWeek= "一";
			break;
		case 3:
			chinesWeek= "二";
			break;
		case 4:
			chinesWeek= "三";
			break;
		case 5:
			chinesWeek= "四";
			break;
		case 6:
			chinesWeek= "五";
			break;
		case 7:
			chinesWeek= "六";
			break;
		default:
			chinesWeek= "日";
			break;
		}
		return chinesWeek;
	}
	 /**
     * 将Unix时间戳转换成日期
     * @param long timestamp 时间戳
     * @return String 日期字符串
     */
    public static String unixTimestampToDate(Long timestamp) {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return df.format(new Date(timestamp));
    }
}
