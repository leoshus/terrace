package com.sdw.soft.core.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 * @author syd
 * @Date 2013年12月2日
 * @version 1.0.0
 */
public class DateUtils {
	
	public final static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_MM__yy = "MM/yy";
    public static final String DATE_FORMAT_yyyy__MM = "yyyy/MM";
    public static final String DATE_FORMAT_MMM_yy = "MMM-yy";
    public static final String DATE_FORMAT_yyyyMM = "yyyyMM";
    public static final String DATE_FORMAT_yyyy__MM__dd = "yyyy/MM/dd";
    public static final String DATE_FORMAT_yyyy___MM___dd = "yyyy-MM-dd";
    public static final String DATE_FORMAT_yyyyMMdd = "yyyyMMdd";
    public static final String DATE_FORMAT_MM_dd_yy = "MM/dd/yy";
    public static final String DATE_FORMAT_yyyy__MM__dd_HH_mm_ss = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_yyyy_MM_dd = "yyyy.MM.dd";
    public static final String DATE_FORMAT_yyyy_MM_dd_HH_mm_ss_SSS = "yyyyMMddHHmmssSSS";

    public static String getHumanDisplayForTimediff(Long diffMillis) {
        if (diffMillis == null) {
            return "";
        }
        long day = diffMillis / (24 * 60 * 60 * 1000);
        long hour = (diffMillis / (60 * 60 * 1000) - day * 24);
        long min = ((diffMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long se = (diffMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day + "D");
        }
        DecimalFormat df = new DecimalFormat("00");
        sb.append(df.format(hour) + ":");
        sb.append(df.format(min) + ":");
        sb.append(df.format(se));
        return sb.toString();
    }
    
    public static List getDisplayMonth(Date time, int monthBefore, int monthAfter, SimpleDateFormat format)
    {
      ArrayList result = new ArrayList();
      Calendar calendar = Calendar.getInstance();
      if (null != time) {
        calendar.setTimeInMillis(time.getTime());
      }
      SimpleDateFormat tmpformat = format;
      if (null == tmpformat) {
        tmpformat = new SimpleDateFormat("yyyy/MM");
      }
      Calendar calendartmp = Calendar.getInstance();

      for (int i = monthBefore; i <= monthAfter; i++) {
        calendartmp.setTimeInMillis(calendar.getTimeInMillis());
        calendartmp.set(5, 1);
        calendartmp.set(11, 0);
        calendartmp.set(12, 0);
        calendartmp.set(13, 0);
        calendartmp.set(14, 0);

        calendartmp.add(2, i);
        result.add(tmpformat.format(new Date(calendartmp.getTimeInMillis())));
      }
      return result;
    }

    public static List getDisplayMonth(Date from, Date to, String pattern)
    {
      ArrayList result = new ArrayList();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(from);
      Calendar calendarTo = Calendar.getInstance();
      calendarTo.setTime(getMonthLastDay(to));
      if (pattern == null) {
        pattern = "yyyy/MM";
      }
      SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);

      while (calendar.before(calendarTo)) {
        result.add(formatter.format(calendar.getTime()));
        calendar.add(2, 1);
      }
      return result;
    }

    public static List getDisplayMonth(Date time, int monthBefore, int monthAfter)
    {
      return getDisplayMonth(time, monthBefore, monthAfter, null);
    }

    public static List getDisplayMonth(int monthBefore, int monthAfter)
    {
      return getDisplayMonth(null, monthBefore, monthAfter);
    }

    public static List getDisplayMonth(int monthBefore, int monthAfter, String format)
    {
      return getDisplayMonth(null, monthBefore, monthAfter, new SimpleDateFormat(format, Locale.ENGLISH));
    }

    public static Date[] getMonth(Date time, int monthBefore, int monthAfter)
    {
      ArrayList result = new ArrayList();
      Calendar calendar = Calendar.getInstance();
      if (null != time) {
        calendar.setTimeInMillis(time.getTime());
      }
      for (int i = monthBefore; i <= monthAfter; i++) {
        Calendar calendartmp = Calendar.getInstance();
        calendartmp.setTimeInMillis(calendar.getTimeInMillis());
        calendartmp.set(5, 1);
        calendartmp.set(11, 0);
        calendartmp.set(12, 0);
        calendartmp.set(13, 0);
        calendartmp.set(14, 0);
        calendartmp.add(2, i);
        result.add(new Date(calendartmp.getTimeInMillis()));
      }
      return (Date[])(Date[])result.toArray(new Date[monthAfter - monthBefore + 1]);
    }

    public static Date[] getMonth(Date startMonth, Date endMonth)
    {
      ArrayList result = new ArrayList();
      Calendar calendar = Calendar.getInstance();
      Calendar calendarEnd = Calendar.getInstance();
      if (null != startMonth) {
        calendar.setTimeInMillis(startMonth.getTime());
      }
      if (null != endMonth) {
        calendarEnd.setTimeInMillis(endMonth.getTime());
      }
      while (!calendar.after(calendarEnd)) {
        result.add(new Date(calendar.getTimeInMillis()));
        calendar.add(2, 1);
      }

      return (Date[])(Date[])result.toArray(new Date[result.size()]);
    }

    public static Date getNextMonth(Date time)
    {
      Calendar calendar = Calendar.getInstance();
      if (null != time) {
        calendar.setTimeInMillis(time.getTime());
      }
      calendar.add(2, 1);
      return new Date(calendar.getTimeInMillis());
    }

    public static Date getPreviousMonth(Date time)
    {
      Calendar calendar = Calendar.getInstance();
      if (null != time) {
        calendar.setTimeInMillis(time.getTime());
      }
      calendar.add(2, -1);
      return new Date(calendar.getTimeInMillis());
    }

    public static Date[] getMonth(int monthBefore, int monthAfter)
    {
      return getMonth(null, monthBefore, monthAfter);
    }

    public static String getCurrentTimeString()
    {
      Date now = Calendar.getInstance().getTime();
      Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return formatter.format(now);
    }

    public static String formatDate(Date date)
    {
      if (date == null) {
        return null;
      }

      Format formatter = new SimpleDateFormat("yyyy-MM-dd");
      return formatter.format(date);
    }

    public static String formatTime(Date date) {
      if (date == null) {
        return null;
      }

      Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return formatter.format(date);
    }

    public static String formatDate(Date date, String format)
    {
      if (date == null) {
        return null;
      }

      Format formatter = new SimpleDateFormat(format);
      return formatter.format(date);
    }

    public static String formatDisplayDate(Date date, String format)
    {
      if (date == null) {
        return null;
      }

      Format formatter = new SimpleDateFormat(format, Locale.ENGLISH);
      return formatter.format(date);
    }

    public static boolean monthInRange(Date current, Date before, Date after)
    {
      Calendar curr = Calendar.getInstance();
      curr.setTime(current);
      Calendar temp = Calendar.getInstance();
      temp.setTime(before);
      temp.set(5, 1);
      temp.set(11, 0);
      temp.set(12, 0);
      temp.set(13, 0);
      if (curr.before(temp)) {
        return false;
      }
      temp.setTime(after);
      temp.add(2, 1);
      temp.set(5, 1);
      temp.set(11, 0);
      temp.set(12, 0);
      temp.set(13, 0);

      return !curr.after(temp);
    }

    public static boolean monthEquals(Date current, Date compare)
    {
      Calendar curr = Calendar.getInstance();
      curr.setTime(current);
      Calendar cmp = Calendar.getInstance();
      cmp.setTime(compare);
      int year1 = curr.get(1);
      int year2 = cmp.get(1);
      int month1 = curr.get(2);
      int month2 = cmp.get(2);
      return (year1 == year2) && (month1 == month2);
    }

    public static String getMonthRate(Date date, boolean moveIn)
    {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      int day = calendar.get(5);

      int daysOfThisMonth = calendar.getActualMaximum(5);
      double rate;
      if (moveIn)
        rate = (daysOfThisMonth - day + 1) / daysOfThisMonth;
      else {
        rate = day / daysOfThisMonth;
      }
      rate = Math.round(rate * 10.0D) / 10.0D;
      return String.valueOf(rate);
    }

    public static String getMonthRate(Date dateIn, Date DateOut)
    {
      Calendar calIn = Calendar.getInstance();
      calIn.setTime(dateIn);
      Calendar calOut = Calendar.getInstance();
      calOut.setTime(DateOut);
      int day = calOut.get(5) - calIn.get(5) + 1;
      int daysOfThisMonth = calIn.getActualMaximum(5);
      double rate = day / daysOfThisMonth;
      rate = Math.round(rate * 10.0D) / 10.0D;
      return String.valueOf(rate);
    }

    public static Date parse(String dateString, String format)
    {
      if (StringUtils.isEmpty(dateString)) {
        return null;
      }
      DateFormat formatter = new SimpleDateFormat(format);
      return formatter.parse(dateString, new ParsePosition(0));
    }

    public static Date parse(String dateString) {
      if (StringUtils.isEmpty(dateString))
        return null;
      try
      {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
      }
      catch (Exception e) {
        try {
          return new SimpleDateFormat("yyyy/MM/dd").parse(dateString);
        }
        catch (Exception e1) {
          try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(dateString); } catch (Exception e2) {
          }
        }
      }
      return null;
    }

    public static Date parseInEnglishLocale(String dateString, String format)
    {
      DateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
      return formatter.parse(dateString, new ParsePosition(0));
    }

    public static Date parseTargetMonth(String dateString)
    {
      return parse(dateString, "yyyy/MM");
    }

    public static int compareDate(Date first, Date second)
    {
      Calendar cf = Calendar.getInstance();
      cf.setTime(first);
      Calendar cs = Calendar.getInstance();
      cs.setTime(second);
      if (cf.after(cs)) {
        return 1;
      }
      return cf.before(cs) ? -1 : 0;
    }

    public static Date getMonthLastDay(Date date)
    {
      Calendar calendar = Calendar.getInstance();
      if (null != date) {
        calendar.setTime(date);
      }
      calendar.set(5, calendar.getActualMaximum(5));
      calendar.set(11, 23);
      calendar.set(12, 59);
      calendar.set(13, 59);
      calendar.set(14, 999);
      return calendar.getTime();
    }

    public static Date getMonthFirstDay(Date date)
    {
      Calendar calendar = Calendar.getInstance();
      if (null != date) {
        calendar.setTime(date);
      }
      calendar.set(5, 1);
      calendar.set(11, 0);
      calendar.set(12, 0);
      calendar.set(13, 0);
      calendar.set(14, 0);
      return calendar.getTime();
    }

    public static String getCurrentTimeString(String pattern)
    {
      Date now = Calendar.getInstance().getTime();
      Format formatter = new SimpleDateFormat(pattern);
      return formatter.format(now);
    }

    public static Date getMonthByOffset(Date date, int offset)
    {
      Calendar calendar = Calendar.getInstance();
      if (null != date) {
        calendar.setTime(date);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
      }
      calendar.add(2, offset);
      return calendar.getTime();
    }

    public static int compareMonth(Date first, Date second)
    {
      Calendar cf = Calendar.getInstance();
      cf.setTime(first);
      Calendar cs = Calendar.getInstance();
      cs.setTime(second);
      int offset = (cs.get(1) - cf.get(1)) * 12 + (cs.get(2) - cf.get(2));

      return offset;
    }

    public static int getDaysOfMonth(Date date)
    {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      return c.getActualMaximum(5);
    }

    public static int getDay(Date date)
    {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      return c.get(5);
    }

    public static int getDaysOfMonth(Date startMonth, Integer monthSeq)
    {
      Date month = getMonthByOffset(startMonth, monthSeq.intValue() - 1);
      return getDaysOfMonth(month);
    }

    public static Date getStartTimeOfDate(Date date)
    {
      Calendar calendar = getCalendarByDate(date);
      calendar.set(11, 0);
      calendar.set(12, 0);
      calendar.set(13, 0);
      calendar.set(14, 0);

      return calendar.getTime();
    }

    public static Date getEndTimeOfDate(Date date)
    {
      Calendar calendar = getCalendarByDate(date);
      calendar.set(11, 23);
      calendar.set(12, 59);
      calendar.set(13, 59);
      calendar.set(14, 999);

      return calendar.getTime();
    }

    private static Calendar getCalendarByDate(Date date)
    {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      return c;
    }
}
