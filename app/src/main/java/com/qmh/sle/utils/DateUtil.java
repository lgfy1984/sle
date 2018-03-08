package com.qmh.sle.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;

public class DateUtil {

	/**
	 * 
	 */
	public static Date stringToDate(String strDate, String oracleFormat) {
		// SimpleDateFormat df=new SimpleDateFormat(javaFormat,new
		// DateFormatSymbols());
		// SimpleDateFormat df = new SimpleDateFormat(javaFormat);
		if (strDate == null)
			return null;
		Hashtable<Integer, String> h = new Hashtable<Integer, String>();
		String javaFormat = new String();
		String s = oracleFormat.toLowerCase();
		if (s.indexOf("yyyy") != -1)
			h.put(new Integer(s.indexOf("yyyy")), "yyyy");
		else if (s.indexOf("yy") != -1)
			h.put(new Integer(s.indexOf("yy")), "yy");
		if (s.indexOf("mm") != -1)
			h.put(new Integer(s.indexOf("mm")), "MM");
		if (s.indexOf("dd") != -1)
			h.put(new Integer(s.indexOf("dd")), "dd");
		if (s.indexOf("hh24") != -1)
			h.put(new Integer(s.indexOf("hh24")), "HH");
		if (s.indexOf("mi") != -1)
			h.put(new Integer(s.indexOf("mi")), "mm");
		if (s.indexOf("ss") != -1)
			h.put(new Integer(s.indexOf("ss")), "ss");
		int intStart = 0;
		while (s.indexOf("-", intStart) != -1) {
			intStart = s.indexOf("-", intStart);
			h.put(new Integer(intStart), "-");
			intStart++;
		}
		intStart = 0;
		while (s.indexOf("/", intStart) != -1) {
			intStart = s.indexOf("/", intStart);
			h.put(new Integer(intStart), "/");
			intStart++;
		}
		intStart = 0;
		while (s.indexOf(" ", intStart) != -1) {
			intStart = s.indexOf(" ", intStart);
			h.put(new Integer(intStart), " ");
			intStart++;
		}
		intStart = 0;
		while (s.indexOf(":", intStart) != -1) {
			intStart = s.indexOf(":", intStart);
			h.put(new Integer(intStart), ":");
			intStart++;
		}
		
		if (s.indexOf("year") != -1)
			h.put(new Integer(s.indexOf("year")), "year");
		if (s.indexOf("month") != -1)
			h.put(new Integer(s.indexOf("month")), "month");
		if (s.indexOf("day") != -1)
			h.put(new Integer(s.indexOf("day")), "day");
		if (s.indexOf("hour") != -1)
			h.put(new Integer(s.indexOf("hour")), "hour");
		if (s.indexOf("minute") != -1)
			h.put(new Integer(s.indexOf("minute")), "minute");
		if (s.indexOf("seconds") != -1)
			h.put(new Integer(s.indexOf("seconds")), "seconds");
		int i = 0;
		while (h.size() != 0) {
			Enumeration e = h.keys();
			int n = 0;
			while (e.hasMoreElements()) {
				i = ((Integer) e.nextElement()).intValue();
				if (i >= n)
					n = i;
			}
			String temp = h.get(new Integer(n));
			h.remove(new Integer(n));
			javaFormat = temp + javaFormat;
		}
		// System.out.println(javaFormat);
		SimpleDateFormat df = new SimpleDateFormat(javaFormat);
		Date myDate = new Date();
		try {
			myDate = df.parse(strDate);
		} catch (Exception e) {
			return null;
		}
		return myDate;
	}

	public static Date string2DateTime(String date, String dateTimeFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormat);
		ParsePosition pos = new ParsePosition(0);
		Date dt = formatter.parse(date, pos);
		return dt;
	}
	
	public static java.sql.Date getDate() {
		  return new java.sql.Date(System.currentTimeMillis());
	}
	
	/**
	 * 计算某天之后N天的日期
	 * @param strDate
	 * @param i_day
	 * @return
	 */
	public static String getLastDay(String strDate, int i_day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Date date = stringToDate(strDate,"yyyy/MM/dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, i_day);
		return sdf.format(c.getTime());
	}
	
	
	/*计算年龄*/
	public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                "The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                //monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                } else {
                    //do nothing
                }
            } else {
                //monthNow>monthBirth
                age--;
            }
        } else {
            //monthNow<monthBirth
            //donothing
        }

        return age;
    }
	
	
	public static Date getNowDate()
	  {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(currentTime);
	    ParsePosition pos = new ParsePosition(8);
	    Date currentTime_2 = formatter.parse(dateString, pos);
	    return currentTime_2;
	  }
	  public static Date getNowDate2()
	  {
	    Date currentTime = new Date();
	    return currentTime; }

	  public static Date getNowDateShort()
	  {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(currentTime);
	    ParsePosition pos = new ParsePosition(8);
	    Date currentTime_2 = formatter.parse(dateString, pos);
	    return currentTime_2;
	  }

	  public static String getStringDate()
	  {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(currentTime);
	    return dateString;
	  }

	  public static String getStringTimestamp()
	  {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(currentTime);
	    return dateString;
	  }

	  public static String getStringDateShort()
	  {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(currentTime);
	    return dateString;
	  }

	  public static Date strToDate(String strDate)
	  {
	    if ((strDate == null) || (strDate.trim().equals("")))
	      strDate = "1900/01/01";
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(strDate, pos);
	    return strtodate;
	  }

	  public static Date strToTimestamp(String strDate)
	  {
	    if ((strDate == null) || (strDate.trim().equals("")))
	      strDate = "01/01/1900 00:00:00";
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(strDate, pos);
	    return strtodate;
	  }

	  public static String dateToStr(Date dateDate)
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(dateDate);
	    return dateString != null ? dateString : "1900-01-01";
	  }

	  public static String timestampToStr(Date dateDate)
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(dateDate);
	    return dateString != null ? dateString : "1900-01-01";
	  }

	  public static Date strToBirthday(String strDate)
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(strDate, pos);
	    return strtodate;
	  }

	  public static Date getNow()
	  {
	    Date currentTime = new Date();
	    return currentTime;
	  }

	  public static long getS(String strDate)
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(strDate, pos);
	    return strtodate.getTime();
	  }

	  public static void getLastTheDate()
	  {
	    Date date = strToDate("2009/08/10");
	    long date_3_hm = date.getTime() + -1577803776L;
	    Date date_3_hm_date = new Date(date_3_hm);
	    System.out.println(dateToStr(date_3_hm_date));
	  }

	  public static Date getLastDate(long day)
	  {
	    Date date = new Date();
	    long date_3_hm = date.getTime() - 122400000L * day;
	    Date date_3_hm_date = new Date(date_3_hm);
	    return date_3_hm_date;
	  }

	  public static int getNowDay(String StrDate)
	  {
	    Date Time1 = strToDate(StrDate);
	    Date Time2 = new Date();
	    long day = Time1.getTime() - Time2.getTime();
	    return (int)day / 86400000;
	  }

	  public static int getNowDay(Date StrDate)
	  {
	    Date Time1 = StrDate;
	    Date Time2 = new Date();
	    long day = Time1.getTime() - Time2.getTime();
	    return (int)day / 86400000;
	  }

	  public static String CalendarToStr(Calendar cal)
	  {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    if (cal != null)
	    {
	      Date date = cal.getTime();
	      return format.format(date);
	    }

	    return "";
	  }



	  public static Date StrToTimestamp(String timestampStr, String pattern)
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(timestampStr, pos);
	    return strtodate;
	  }

	  public static String TimestampToStr(Date dateDate, String pattern)
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	    String dateString = formatter.format(dateDate);
	    return dateString != null ? dateString : "1900-01-01";
	  }

	  public static String StrToStr(String oldstr, String patternold, String patternnew)
	  {
	    SimpleDateFormat formatterold = new SimpleDateFormat(patternold);
	    ParsePosition pos = new ParsePosition(0);
	    Date dateDate = formatterold.parse(oldstr, pos);
	    SimpleDateFormat formatternew = new SimpleDateFormat(patternnew);
	    String dateString = formatternew.format(dateDate);
	    return dateString != null ? dateString : "1900-01-01";
	  }

	  public static String SqlStrToStr(String oldstr)
	  {
	    String dateString = null;
	    dateString = oldstr.substring(0, 19);
	    return dateString != null ? dateString : "1900-01-01 00:00:00";
	  }

	  public static String SqlStrToDateStr(String oldstr)
	  {
	    String dateString = null;
	    dateString = oldstr.substring(0, 10);
	    return dateString != null ? dateString : "1900-01-01";
	  }

	  public static boolean isDateTimeBefore(String date1, String date2)
	  {
	    try
	    {
	      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      return df.parse(date1).before(df.parse(date2));
	    }
	    catch (ParseException e)
	    {
	      System.out.print("[SYS] " + e.getMessage());
	    }
	    return false;
	  }

	  public static boolean isDateTimeBefore(String date2)
	  {
	    try
	    {
	      Date date1 = new Date();
	      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      return date1.before(df.parse(date2));
	    }
	    catch (ParseException e)
	    {
	      System.out.print("[SYS] " + e.getMessage());
	    }
	    return false;
	  }

	  public static boolean isDateBefore(String date1, String date2)
	  {
	    try
	    {
	      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	      return df.parse(date1).before(df.parse(date2));
	    }
	    catch (ParseException e)
	    {
	      System.out.print("[SYS] " + e.getMessage());
	    }
	    return false;
	  }

	  public static boolean isDateBefore(String date2)
	  {
	    try
	    {
	      Date date1 = new Date();
	      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	      return date1.before(df.parse(date2));
	    }
	    catch (ParseException e)
	    {
	      System.out.print("[SYS] " + e.getMessage());
	    }
	    return false;
	  }
	  
	  
	  //----------------------------以下是commons-lang-2.3.jar中的内容 与weblogic中commons-lang冲突,所以提出来放这里
	  public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");
	  public static final long MILLIS_PER_SECOND = 1000L;
	  public static final long MILLIS_PER_MINUTE = 60000L;
	  public static final long MILLIS_PER_HOUR = 3600000L;
	  public static final long MILLIS_PER_DAY = 86400000L;
	  public static final int SEMI_MONTH = 1001;
	  private static final int[][] fields = { { 14 }, { 13 }, { 12 }, { 11, 10 }, { 5, 5, 9 }, { 2, 1001 }, { 1 }, { 0 } };
	  public static final int RANGE_WEEK_SUNDAY = 1;
	  public static final int RANGE_WEEK_MONDAY = 2;
	  public static final int RANGE_WEEK_RELATIVE = 3;
	  public static final int RANGE_WEEK_CENTER = 4;
	  public static final int RANGE_MONTH_SUNDAY = 5;
	  public static final int RANGE_MONTH_MONDAY = 6;

	  /** @deprecated */
	  public static final int MILLIS_IN_SECOND = 1000;

	  /** @deprecated */
	  public static final int MILLIS_IN_MINUTE = 60000;

	  /** @deprecated */
	  public static final int MILLIS_IN_HOUR = 3600000;

	  /** @deprecated */
	  public static final int MILLIS_IN_DAY = 86400000;





	  public static boolean isSameInstant(Date date1, Date date2)
	  {
	    if ((date1 == null) || (date2 == null)) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    return date1.getTime() == date2.getTime();
	  }

	  public static boolean isSameInstant(Calendar cal1, Calendar cal2)
	  {
	    if ((cal1 == null) || (cal2 == null)) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    return cal1.getTime().getTime() == cal2.getTime().getTime();
	  }



	  public static Date parseDate(String str, String[] parsePatterns)
	    throws ParseException
	  {
	    if ((str == null) || (parsePatterns == null)) {
	      throw new IllegalArgumentException("Date and Patterns must not be null");
	    }

	    SimpleDateFormat parser = null;
	    ParsePosition pos = new ParsePosition(0);
	    for (int i = 0; i < parsePatterns.length; i++) {
	      if (i == 0)
	        parser = new SimpleDateFormat(parsePatterns[0]);
	      else {
	        parser.applyPattern(parsePatterns[i]);
	      }
	      pos.setIndex(0);
	      Date date = parser.parse(str, pos);
	      if ((date != null) && (pos.getIndex() == str.length())) {
	        return date;
	      }
	    }
	    throw new ParseException("Unable to parse the date: " + str, -1);
	  }

	  public static Date addYears(Date date, int amount)
	  {
	    return add(date, 1, amount);
	  }

	  public static Date addMonths(Date date, int amount)
	  {
	    return add(date, 2, amount);
	  }

	  public static Date addWeeks(Date date, int amount)
	  {
	    return add(date, 3, amount);
	  }

	  public static Date addDays(Date date, int amount)
	  {
	    return add(date, 5, amount);
	  }

	  public static Date addHours(Date date, int amount)
	  {
	    return add(date, 11, amount);
	  }

	  public static Date addMinutes(Date date, int amount)
	  {
	    return add(date, 12, amount);
	  }

	  public static Date addSeconds(Date date, int amount)
	  {
	    return add(date, 13, amount);
	  }

	  public static Date addMilliseconds(Date date, int amount)
	  {
	    return add(date, 14, amount);
	  }

	  public static Date add(Date date, int calendarField, int amount)
	  {
	    if (date == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(calendarField, amount);
	    return c.getTime();
	  }



	  public static Iterator iterator(Date focus, int rangeStyle)
	  {
	    if (focus == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    Calendar gval = Calendar.getInstance();
	    gval.setTime(focus);
	    return iterator(gval, rangeStyle);
	  }



	  public static Iterator iterator(Object focus, int rangeStyle)
	  {
	    if (focus == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    if ((focus instanceof Date))
	      return iterator((Date)focus, rangeStyle);
	    if ((focus instanceof Calendar)) {
	      return iterator(focus, rangeStyle);
	    }
	    throw new ClassCastException("Could not iterate based on " + focus);
	  }


	  

	    /**
	     * 根据指定格式将指定日期格式化成字符串
	     * 
	     * @param date
	     *            指定日期
	     * @param pattern
	     *            指定格式
	     * @return 返回格式化后的字符串
	     */
	    public static String format(Date date, String pattern) {
	        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	        return sdf.format(date);
	    }
	 
	    /**
	     * 获取时间date1与date2相差的秒数
	     * 
	     * @param date1
	     *            起始时间
	     * @param date2
	     *            结束时间
	     * @return 返回相差的秒数
	     */
	    public static int getOffsetSeconds(Date date1, Date date2) {
	        int seconds = (int) ((date2.getTime() - date1.getTime()) / 1000);
	        return seconds;
	    }
	 
	    /**
	     * 获取时间date1与date2相差的分钟数
	     * 
	     * @param date1
	     *            起始时间
	     * @param date2
	     *            结束时间
	     * @return 返回相差的分钟数
	     */
	    public static int getOffsetMinutes(Date date1, Date date2) {
	        return getOffsetSeconds(date1, date2) / 60;
	    }
	 
	    /**
	     * 获取时间date1与date2相差的小时数
	     * 
	     * @param date1
	     *            起始时间
	     * @param date2
	     *            结束时间
	     * @return 返回相差的小时数
	     */
	    public static int getOffsetHours(Date date1, Date date2) {
	        return getOffsetMinutes(date1, date2) / 60;
	    }
	 
	    /**
	     * 获取时间date1与date2相差的天数数
	     * 
	     * @param date1
	     *            起始时间
	     * @param date2
	     *            结束时间
	     * @return 返回相差的天数
	     */
	    public static int getOffsetDays(Date date1, Date date2) {
	        return getOffsetHours(date1, date2) / 24;
	    }
	  
	  
	  
}
