package com.mkit.libmkit.mkitutils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * 日期时间工具类
 * 
 * @author Lu
 * @date 2014-5-26
 */
public class MyDateUtils {
	public static final SimpleDateFormat md = new SimpleDateFormat(
			"MM-dd", Locale.getDefault());
	public static final SimpleDateFormat ymd = new SimpleDateFormat(
		    "yyyy-MM-dd", Locale.getDefault());
	public static final SimpleDateFormat ymdhhmmss = new SimpleDateFormat(
    		"yyyy-MM-dd hh:mm:ss", Locale.getDefault());
	public static final SimpleDateFormat mdhhmm = new SimpleDateFormat(
			"MM-dd HH:mm", Locale.getDefault());
	public static final SimpleDateFormat yMdTHms = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
	public static final SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat yMd = new SimpleDateFormat("dd-MM");


	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		long t = System.currentTimeMillis();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		String st = formatDate(t, pattern);
		return st;
	}

	/**
	 * 获取不含分隔符的当前时间字符串
	 * 
	 * @return
	 */
	public static String getCurrentTime1() {
		long t = System.currentTimeMillis();
		String pattern = "yyyyMMdd_HHmmss";
		String st = formatDate(t, pattern);
		return st;
	}

	public static String getCurrentTime2() {
		long t = System.currentTimeMillis();
		String pattern = "yyyy-MM-dd HH:mm";
		String st = formatDate(t, pattern);
		return st;
	}
	
	public static String getTimestamp() {
		long t = System.currentTimeMillis();
		String pattern = "yyyyMMddHHmmss";
		String st = formatDate(t, pattern);
		return st;
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		long t = System.currentTimeMillis();
		String pattern = "yyyy-MM-dd";
		String st = formatDate(t, pattern);
		return st;
	}
	
	public static String getCurrentDate(SimpleDateFormat pattern) {
		long t = System.currentTimeMillis();
		String st = formatDate(t, pattern);
		return st;
	}

	public static String getCurrentDate1() {
		Date d = new Date();
		int year, month, day;

		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		String date = year + "." + month + "." + day;
		return date;

	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDateAndTime() {
		long t = System.currentTimeMillis();
		String pattern = "yyyy-MM-dd HH:mm";
		String st = formatDate(t, pattern);
		return st;
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDateAndTime1() {
		long t = System.currentTimeMillis();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		String st = formatDate(t, pattern);
		return st;
	}

	/**
	 * 计算年龄
	 *
	 * @param birthday
	 */
	public static int getAge(String birthday) {
		int age = 0;
		try {
			int a = Calendar.getInstance().get(Calendar.YEAR);
			Calendar cal = Calendar.getInstance();
			Date d = (new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
			cal.setTime(d);
			int c = cal.get(Calendar.YEAR);
			age = a - c;
		} catch (ParseException e) {
			e.printStackTrace();
		}catch(NullPointerException e){
		    e.printStackTrace();
		}
		return age;
	}
	
	/**
	 * 格式化时间值
	 * 
	 * @param lDate
	 * @return
	 */
	public static String formatDate(long lDate, String pattern) {
		Date date = new Date(lDate);
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	/**
	 * 格式化时间值
	 * 
	 * @param lDate
	 * @return
	 */
	public static String formatDate(long lDate, SimpleDateFormat pattern) {
		Date date = new Date(lDate);
		return pattern.format(date);
	}

	/**
	 * 格式化时间值
	 * 
	 * @param lDate
	 * @return
	 */
	public static String formatDate(long lDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		Date date = new Date(lDate);
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static String buildYearAndMonth(int y, int m) {
		StringBuilder sb = new StringBuilder();
		sb.append(y + "");
		sb.append(formatInt(m));
		return sb.toString();
	}

	public static String formatInt(int i) {
		String s = "00";
		if (i >= 0 && i < 10) {
			s = "0" + i;
		} else if (i >= 10) {
			s = i + "";
		}
		return s;
	}
	
	/**
	 * 格式化日期
	 */
	public static String formatDate(int y, int m, int d,SimpleDateFormat format) {
		Calendar cal = Calendar.getInstance();
		cal.set(y, m, d);
		Date date = new Date(cal.getTimeInMillis());
		return format.format(date);
	}
	
	/**
	 * parse string time to long
	 * 
	 * @param date
	 * @return
	 */
	public static long parseDate(String date) {
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long ldate = 0;
		try {
			d = dateFormat.parse(date);
			ldate = d.getTime();
		} catch (ParseException e) {
		}
		return ldate;
	}
	
	/**
	 * parse string time to long
	 * 
	 * @param date
	 * @return
	 * @throws ParseException 
	 * 加同步的原因是 多线程操作会影响到日期的解析
	 */
/*	public static synchronized long parseDate(String date, SimpleDateFormat format)
			throws ParseException {
		long ldate = 0;
		Date d = new Date();
		d = format.parse(date);
		ldate = d.getDelayTime();
		return ldate;
	}*/
	//异步
	public static  long parseDate(String date, SimpleDateFormat format)
			throws ParseException {
		long ldate = 0;
		Date d = new Date();
		d = format.parse(date);
		ldate = d.getTime();
		return ldate;
	}
	
	public static Calendar parseCalendar(String date) {
		Calendar cal = null;
		if (!TextUtils.isEmpty(date)) {
			Date d = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				d = dateFormat.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cal = Calendar.getInstance();
			cal.setTime(d);
		}
		return cal;
	}

	public static void setCalendar(Calendar cal, String date,SimpleDateFormat format) {
		if (!TextUtils.isEmpty(date)) {
			Date d = new Date();
			try {
				d = format.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cal.setTime(d);
		}
	}
	
	/**
	 * 
	 */
	public static int compareDateTime(String dateStr1, String dateStr2, SimpleDateFormat foramt) {
		Date date1 = new Date();
		Date date2 = new Date();
		try {
			date1 = foramt.parse(dateStr1);
			date2 = foramt.parse(dateStr2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date1.getTime() < date2.getTime())
			return -1;
		else if (date1.getTime() == date2.getTime())
			return 0;
		else
			return 1;
	}
	
	/**
	 * 日期比较: 格式 yyyy-MM-dd date1 < date2 -1 date1 = date2 0 date1 > date2 1
	 */
	public static int compareDate(String dateStr1, String dateStr2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		Date date2 = new Date();

		try {
			date1 = sdf.parse(dateStr1);
			date2 = sdf.parse(dateStr2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date1.getTime() < date2.getTime())
			return -1;
		else if (date1.getTime() == date2.getTime())
			return 0;
		else
			return 1;
	}

	/**
	 * 日期比较: 格式 HH:mm date1 < date2 -1 date1 == date2 0 date1 > date2 1
	 */
	public static int compareTime(String dateStr1, String dateStr2) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date date1 = new Date();
		Date date2 = new Date();

		try {
			date1 = sdf.parse(dateStr1);
			date2 = sdf.parse(dateStr2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date1.getTime() < date2.getTime())
			return -1;
		else if (date1.getTime() == date2.getTime())
			return 0;
		else
			return 1;
	}


	/**
	 * 判断d2是否在d1的range天之内(不含当天)
	 *
	 * @param d1
	 * @param d2
	 * @param range
	 * @return
	 */
	public static boolean isDateInRange(String d1, String d2, int range) {
		Long time1 = null;
		Long time2 = null;
		try {
			time1 = parseDate(d1, ymd);
			time2 = parseDate(d2, ymd);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		long days = (time2 - time1) / (1000 * 60 * 60 * 24);

		if (days > 0 && days <= range) {
			return true;
		}

		return false;
	}
	
	/**
	 * 计算时间偏移量
	 *
	 * @param targetTime 
	 * @return originalTime-targetTime
	 */
	public static long getTimeOffset(long originalTime,String targetTime){
		long ltargetTime=parseDate(targetTime);
		return originalTime-ltargetTime;
	}

	/**
	 * 根据偏移量校正时间
	 *
	 * @para
	 * @param offset
	 * @return
	 */
	public static String getCorrectTime(long ltime,long offset){
		return formatDate(ltime-offset);
	}
/*	
	public static long getRestTimeMillis(long lcurTime,String endTime){
		long timeEnd=parseDate(endTime);
//		long lcurTime=parseDate(curTime);
		return timeEnd-lcurTime;
	}*/

	/**解析含有自定义字符串的日期格式*/
	public static String stringTimeFormat(String atime) {
		try {
			Date date = yMdTHms.parse(atime);
			String HHmmString = HHmm.format(date).toString();
			String ddString = getMonDate(yMd.format(date).toString());

			return ddString+" "+HHmmString;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**解析含有自定义字符串的日期格式用于显示列表时间格式*/
	public static String stringTimeFormatList(String atime) {
		try {
			Date date = yMdTHms.parse(atime);
			String ddString = getMonDate(yMd.format(date).toString());

			return ddString+" ";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String getMonDate(String s) {


		String [] arr_ddMM = s.split("-");
		switch(arr_ddMM[1]){
			case "01":
				return arr_ddMM[0]+"-Jan";
			case "02":
				return arr_ddMM[0]+"-Feb";
			case "03":
				return arr_ddMM[0]+"-Mar";
			case "04":
				return arr_ddMM[0]+"-Apr";
			case "05":
				return arr_ddMM[0]+"-May";
			case "06":
				return arr_ddMM[0]+"-Jun";
			case "07":
				return arr_ddMM[0]+"-Jul";
			case "08":
				return arr_ddMM[0]+"-Aug";
			case "09":
				return arr_ddMM[0]+"-Sep";
			case "10":
				return arr_ddMM[0]+"-Oct";
			case "11":
				return arr_ddMM[0]+"-Nov";
			case "12":
				return arr_ddMM[0]+"-Dec";
		}
		return  "";
	}

	/**解析含有自定义字符串的年月日*/
	public static String stringTimeFormatFragment(String atime) {
		try {
			Date date = yMdTHms.parse(atime);
			return yMd.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
}
