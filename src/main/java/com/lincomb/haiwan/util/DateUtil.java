package com.lincomb.haiwan.util;


import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    private static final Logger logger = Logger.getLogger(DateUtil.class);

    /**
     * 定义日期时间格式的中间符号，可以是"-"或"/"或":"。日期默认为"-"时间默认为":"
     */
    private static final String formatDateSign = "-";

    private static final String formatDandTSign = "/";

    private static final String formatTimeSign = ":";

    /**
     * yyyy-MM-dd
     */
    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
    private static final String SIMPLE_DATE_FORMAT_MONTH = "MM";
    private static final String SIMPLE_DATE_FORMAT_DAY = "dd";

    public static final String YYYYMMDD_FORMAT = "yyyyMMdd";
    public static final String YYMMDD_FORMAT = "yy/MM/dd";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String SIMPLE_TIME_FORMAT_H = "yyyy-MM-dd HH:mm:ss";

    /**
     * MM-dd-YYYY HH:mm
     */
    public static final String SIMPLE_TIME_FORMAT_MDY = "MM-dd-YYYY HH:mm";

    /**
     * yyyy/MM/dd HH:mm:ss
     */
    public static final String SIMPLE_TIME_FORMAT_X = "yyyy/MM/dd HH:mm:ss";
    
    /**
     * yyyy.MM.dd HH:mm:ss
     */
    public static final String SIMPLE_TIME_FORMAT_Y = "yyyy.MM.dd HH:mm:ss";

    /**
     * yyyyMMddHHmmss
     */
    public static final String SIMPLE_TIME_YYYYMMDDHH24MMSS = "yyyyMMddHHmmss";
    private static final String timePattern = "HHmmss";

    /**
     * 时间戳格式（带毫秒）
     */
    private static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 时分秒毫秒（HHmmssSSS）
     */
    private static final String SIMPLE_TIME_HHMMSSSSS = "HHmmssSSS";

    /**
     * 截取时间戳（HHmmssSSS）
     */
    private static final String SUB_TIMESTAMP_PATTERN = "HHmmssSSS";

    /**
     * 截取不包含秒的日期模式
     */
    public static final String SIMPLE_TIME_FORMAT_YYYY_MM_DD_HH_MM = "yyyy/MM/dd HH:mm:ss";

    /**
     * yyyy年MM月dd日日期模式
     */
    public static final String SIMPLE_DATE_FORMAT_CN = "yyyy年MM月dd日";

    private static FastDateFormat timeFormat = FastDateFormat.getInstance(timePattern);

    public static String getFormatDateTime(Date date) {
        DateFormat df = new SimpleDateFormat(SIMPLE_TIME_YYYYMMDDHH24MMSS);
        return df.format(date);
    }
    
    public static String getFormatDateTime(Date date,String timePattern) {
    	DateFormat df = new SimpleDateFormat(timePattern);
    	return df.format(date);
    }

    /**
     * @param str
     * @return
     * @函数名称：stringToUtilDate
     * @功能描述：将String型的日期格式转换为Util型的日期格式
     */
    public static Date strToUtilDate(String str) {
        if (null != str && str.length() > 0) {
            try {
                return (new SimpleDateFormat(SIMPLE_TIME_YYYYMMDDHH24MMSS)).parse(str);
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
        } else
            return null;
    }

    /**
     * @param time 要格式化的日期字符串: 10位 yyyy/MM/dd或yyyy-MM-dd或yyyy:MM:dd
     * @return 返回格式化后的日期
     * @函数名称：dateTo8 @功能描述：10位yyyy/MM/dd,yyyy-MM-dd,yyyy:MM:dd 转换为8位yyyyMMdd
     */

    public static String timeNumberTodate(String time) {
    	if(time==null){
    		return null;
    	}
        int len = time.length();
        return time.substring(0, len - 4) + formatDateSign
                + time.substring(len - 4, len - 2) + formatDateSign
                + time.substring(len - 2, len);
    }

    /**
     * @param str
     * @return
     * @函数名称：stringToSqlDate
     * @功能描述：将String型的日期格式转换为Sql型的日期格式
     */
    public static java.sql.Date stringToSqlDate(String str) {
        if (stringToUtilDate(str) == null || str.length() < 1)
            return null;
        else
            return new java.sql.Date(stringToUtilDate(str).getTime());
    }

    /**
     * @param str
     * @return
     * @函数名称：stringToUtilDate
     * @功能描述：将String型的日期格式转换为Util型的日期格式
     */
    public static Date stringToUtilDate(String str) {
        if (null != str && str.length() > 0) {
            try {
                // 对两种时间格式进行转化。
                if (str.length() <= SIMPLE_DATE_FORMAT.length()) { // 只包含日期。
                    return (new SimpleDateFormat(SIMPLE_DATE_FORMAT)).parse(str);
                } else { // 日期和时间都有
                    return (new SimpleDateFormat(SIMPLE_TIME_FORMAT_H)).parse(str);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
        } else
            return null;
    }

    /**
     * @param str
     * @return
     * @函数名称：stringToUtilDate
     * @功能描述：将String型的日期格式转换为Util型的日期格式
     */
    public static Date stringToUtilDate(String str, String format) {
        if (null != str && str.length() > 0) {
            try {
                return (new SimpleDateFormat(format)).parse(str);
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @param date
     * @return
     * @函数名称：utilDateToSql
     * @功能描述：将Util型的日期格式转换为Sql型的日期格式
     */
    public static java.sql.Date utilDateToSql(Date date) {
    	if(date==null){
    		return null;
    	}
        return new java.sql.Date(date.getTime());
    }

    /**
     * @param date
     * @return
     * @函数名称：sqlDateToUtil
     * @功能描述：将Sql型的日期格式转换为Util型的日期格式
     */
    public static Date sqlDateToUtil(java.sql.Date date) {
    	if(date==null){
    		return null;
    	}
        return new Date(date.getTime());
    }

    /**
     * @param d
     * @return
     * @函数名称：toDateTimeString
     * @功能描述：将Sql型的带时间日期格式转换为String型的日期格式
     */
    public static String toDateTimeString(java.sql.Date d) {
        if (d == null) {
            return null;
        } else {
            return (new SimpleDateFormat(SIMPLE_TIME_FORMAT_H)).format(d);
        }
    }

    /**
     * @param d
     * @return
     * @函数名称：toDateTimeString
     * @功能描述：将Util型的带时间日期格式转换为String型的日期格式
     */
    public static String toDateTimeString(Date d) {
        if (d == null) {
            return null;
        } else {
            return (new SimpleDateFormat(SIMPLE_TIME_FORMAT_H)).format(d);
        }
    }



    /**
     * @param d
     * @return
     * @函数名称：toDateTimeString
     * @功能描述：将Util型的带时间日期格式转换为String型的日期格式
     */
    public static String toDateTimeYYMMDDString(Date d) {
        if (d == null) {
            return null;
        } else {
            return (new SimpleDateFormat(SIMPLE_TIME_FORMAT_YYYY_MM_DD_HH_MM)).format(d);
        }
    }



    /**
     * @param d
     * @param pattern
     * @return
     * @函数名称：toDateTimeString
     * @功能描述：将Util型的带时间日期格式转换为String型的日期格式
     */
    public static String toDateTimeString(Date d, String pattern) {
        if (d == null) {
            return null;
        } else {
            return (new SimpleDateFormat(pattern)).format(d);
        }
    }

    /**
     * @param d
     * @return
     * @函数名称：toDateString
     * @功能描述：将Sql型的只带日期格式转换为String型的日期格式
     */
    public static String toDateString(java.sql.Date d) {
        if (d == null) {
            return null;
        } else {
            return (new SimpleDateFormat(SIMPLE_DATE_FORMAT)).format(d);
        }
    }

    /**
     * @param d
     * @return
     * @函数名称：toDateString
     * @功能描述：将Sql型的只带日期格式转换为String型的日期格式
     */
    public static String yyyyMMddCurrentString(Date d) {
        if (d == null) {
            return null;
        } else {
            return (new SimpleDateFormat(YYYYMMDD_FORMAT)).format(d);
        }
    }

    public static Date strToyyyyMMddDate(String d) {
        if (d == null) {
            return null;
        } else {
            try {
                return (new SimpleDateFormat(YYYYMMDD_FORMAT)).parse(d);
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    /**
     * @param d
     * @return
     * @函数名称：toDateString
     * @功能描述：将Sql型的只带日期格式转换为String型的日期格式
     */
    public static String toDateString(Date d) {
        if (d == null) {
            return null;
        } else {
            return (new SimpleDateFormat(SIMPLE_DATE_FORMAT)).format(d);
        }
    }

    /**
     * @return
     * @函数名称：getCurrentDate
     * @功能描述：获取当前日期和时间
     */
    public static java.sql.Date getCurrentDateTime() {
        Calendar newcalendar = null;
        newcalendar = Calendar.getInstance();

        String year = String.valueOf(newcalendar.get(Calendar.YEAR));
        String month = String.valueOf(newcalendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(newcalendar.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(newcalendar.get(Calendar.HOUR_OF_DAY));
        String min = String.valueOf(newcalendar.get(Calendar.MINUTE));
        String sec = String.valueOf(newcalendar.get(Calendar.SECOND));

        return stringToSqlDate(year + formatDateSign + month + formatDateSign
                + day + " " + hour + formatTimeSign + min + formatTimeSign
                + sec);
    }

    /**
     * @return
     * @函数名称：getCurrentDate
     * @功能描述：获取当前日期(只带日期)
     */
    public static java.sql.Date getCurrentDate() {
        Calendar newcalendar = null;
        newcalendar = Calendar.getInstance();

        String year = String.valueOf(newcalendar.get(Calendar.YEAR));
        String month = String.valueOf(newcalendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(newcalendar.get(Calendar.DAY_OF_MONTH));

        return stringToSqlDate(year + formatDateSign + month + formatDateSign
                + day);
    }

    /**
     * @return
     * @函数名称：getCurrentTime
     * @功能描述：获取当前时间(只带时间)
     */
    public static String getCurrentDateTimeStr() {
        SimpleDateFormat dataFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        return timeString;
    }

    /**
     * @return
     * @函数名称：getCurrentTime
     * @功能描述：获取当前时间(只带时间)
     */
    public static String getCurrentDateTimeStr(Date date) {
    	if(date==null){
    		return null;
    	}
        SimpleDateFormat dataFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String timeString = dataFormat.format(date);
        return timeString;
    }

    public static String getCurrentDateTimeStr1() {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        return timeString;
    }

    /**
     * 功能描述：返回yyyyMMddHHmmss格式化的时间字符串
     *
     * @param date
     * @return
     */
    public static String getCurrentDateTimeStr1(Date date) {
    	if(date==null){
    		return null;
    	}
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeString = dataFormat.format(date);
        return timeString;
    }

    public static String getCurrentDateStr() {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        return timeString;
    }

    public static String getCurrentDateStr1() {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        return timeString;
    }

    public static String getWeekStr() {
        String s = "";
        int week = getWeek();
        switch (week) {
            case 1:
                s = "星期一";
                break;
            case 2:
                s = "星期二";
                break;
            case 3:
                s = "星期三";
                break;
            case 4:
                s = "星期四";
                break;
            case 5:
                s = "星期五";
                break;
            case 6:
                s = "星期六";
                break;
            case 7:
                s = "星期天";
                break;
            default:
                break;
        }
        return s;
    }

    /**
     * 获取当前是星期几。 <br>
     * 0为星期天、1为星期一、以此类推。
     *
     * @return String - 返回当前星期几
     */
    public static int getWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int posOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        posOfWeek--; // Calendar格式转化成普通格式 0星期天， 1 星期一...
        return posOfWeek;
    }

    /**
     * @param beginTime
     * @return
     * @函数名称：addYear
     * @功能描述：
     */
    public static java.sql.Date addYear(java.sql.Date beginTime, int i) {
    	if(beginTime==null){
    		return null;
    	}
        Calendar date = Calendar.getInstance();
        date.setTime(beginTime);
        date.add(Calendar.YEAR, i);
        return utilDateToSql(date.getTime());
    }

    /**
     * @param beginTime
     * @return
     * @函数名称：addMonth
     * @功能描述：
     */
    public static java.sql.Date addMonth(java.sql.Date beginTime, int i) {
    	if(beginTime==null){
    		return null;
    	}
        Calendar date = Calendar.getInstance();
        date.setTime(beginTime);
        date.add(Calendar.MONTH, i);
        return utilDateToSql(date.getTime());
    }

    /**
     * @param beginTime
     * @return
     * @函数名称：addMonth
     * @功能描述：
     */
    public static Date addMonth(Date beginTime, int i) {
    	if(beginTime==null){
    		return null;
    	}
        Calendar date = Calendar.getInstance();
        date.setTime(beginTime);
        date.add(Calendar.MONTH, i);
        return date.getTime();
    }

    /**
     * @param beginTime
     * @return
     * @函数名称：addDay
     * @功能描述：
     */
    public static Date addDay(Date beginTime, int i) {
    	if(beginTime==null){
    		return null;
    	}
        Calendar date = Calendar.getInstance();
        date.setTime(beginTime);
        date.add(Calendar.DAY_OF_MONTH, i);
        return date.getTime();
    }

    /**
     * @param beginTime
     * @return
     * @函数名称：addDay
     * @功能描述：
     */
    public static java.sql.Date addDay(java.sql.Date beginTime, int i) {
    	if(beginTime==null){
    		return null;
    	}
        Calendar date = Calendar.getInstance();
        date.setTime(beginTime);
        date.add(Calendar.DAY_OF_MONTH, i);
        return utilDateToSql(date.getTime());
    }

    /**
     * @param beginTime
     * @param endTime
     * @return
     * @函数名称：compareYear
     * @功能描述：比较年
     */
    public static int compareYear(java.sql.Date beginTime, java.sql.Date endTime) {
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        begin.setTime(beginTime);
        end.setTime(endTime);
        int compareYear = end.get(Calendar.YEAR) - begin.get(Calendar.YEAR);
        return compareYear;
    }

    /**
     * @param beginTime
     * @param endTime
     * @return
     * @函数名称：compareMonth
     * @功能描述：比较月
     */
    public static int compareMonth(java.sql.Date beginTime,
                                   java.sql.Date endTime) {
        int compareYear = compareYear(beginTime, endTime);
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        begin.setTime(beginTime);
        end.setTime(endTime);
        int compareMonth = compareYear * 12
                + (end.get(Calendar.MONTH) - begin.get(Calendar.MONTH));
        return compareMonth;
    }

    /**
     * @param beginTime
     * @param endTime
     * @return
     * @函数名称：compareDay
     * @功能描述：比较天
     */
    public static int compareDay(Date beginTime, Date endTime) {
        long compare = (endTime.getTime() - beginTime.getTime())
                / (1000 * 3600 * 24);
        String compareStr = String.valueOf(compare);
        return Integer.parseInt(compareStr);
    }

    /**
     * @param date
     * @param date2
     * @return
     * @函数名称：compareHour
     * @功能描述：比较小时
     */
    public static int compareHour(Date date, Date date2) {
        long compare = (date2.getTime() - date.getTime()) / (1000 * 3600);
        String compareStr = String.valueOf(compare);
        return Integer.parseInt(compareStr);
    }

    /**
     * @param beginTime
     * @param endTime
     * @return
     * @函数名称：compareHour
     * @功能描述：比较小时
     */
    public static int compareHourByTimestamp(String beginTime, String endTime) {
        Long beginTimestamp = Long.parseLong(beginTime);
        Long endTimestamp = Long.parseLong(endTime);
        return compareHour(new Date(beginTimestamp),
                new Date(endTimestamp));
    }

    /**
     * 获取一个月的最后一天。 <br>
     * 例如20030111,即一月份的最后一天是20030131
     *
     * @param date -指定日期(yyyyMMdd)
     * @return String - 返回计算后的日期
     */
    public static String getLastDayOfMonth(String date) {
        if (date.length() != 8)
            return "";
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12) {
            day = 31;
        } else if (month == 2) {
            if (((year % 4) == 0) && ((year % 100) != 0)) {
                day = 29;
            } else {
                day = 28;
            }
        } else {
            day = 30;
        }

        String newDate = "" + year;
        if (month < 10) {
            newDate += "0" + month + day;
        } else {
            newDate += "" + month + day;
        }
        return newDate;
    }

    /**
     * 获取一个日期的星期 <br>
     *
     * @param sDate -指定日期(yyyy-MM-dd)
     * @return String - 返回算好的星期
     */
    public static String getWeek(String sDate) {
        final String dayNames[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
                "星期六"};

        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();

        try {
            date = sdfInput.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayNames[dayOfWeek - 1];
    }


    /**
     * 日期处理
     *
     * @param dateStr
     * @return
     */
    public static String dateProcess(String dateStr) {
        if (StringUtil.isEmpty(dateStr))
            return "";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            return format.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse(dateStr));
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 日期处理
     *
     * @param dateStr
     * @return
     */
    public static String dateConv(String dateStr, String from_pattern,
                                  String to_pattern) {
        if (StringUtil.isEmpty(dateStr))
            return "";
        SimpleDateFormat format = new SimpleDateFormat(to_pattern);
        try {
            return format.format(new SimpleDateFormat(from_pattern)
                    .parse(dateStr));
        } catch (ParseException e) {
        }
        return null;
    }

    public static boolean compareAWithB(Date a, Date b) {
        return a.before(b);
    }

    /**
     * 比较String类型的时间大小
     *
     * @param a
     * @param b
     * @return true 表示a<b
     */
    public static boolean compareString(String a, String b) {
        Date x = stringToUtilDate(a);
        Date y = stringToUtilDate(b);
        return compareAWithB(x, y);
    }

    /**
     * 比较当前时间是否超出指定时间固定的毫秒数
     *
     * @param a    String类型且yyyy-MM-dd HH:mm:ss
     * @param time 比对的秒数
     * @return
     */
    public static boolean compareWithNow(String a, String time) {
        Date x = stringToUtilDate(a);
        int interval = Integer.parseInt(time);
        Date y = new Date(System.currentTimeMillis() - interval
                * 1000);
        return compareAWithB(x, y);
    }

    /**
     * @return
     * @函数名称：getCurrentYearMonthStr
     * @功能描述：获取当前日期(只带年月) 格式 yyMM
     */
    public static String getCurrentYearMonthStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        return dateString;
    }

    /**
     * 当前时间加分钟 格式yyyyMMddHHmmss
     *
     * @param minute
     * @return
     */
    public static String addMinute(String minute) {
        if (StringUtil.isEmpty(minute)) {
            return null;
        }

        try {
            int minuteInt = Integer.valueOf(minute);
            Date newDate = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(newDate);
            cal.add(Calendar.MINUTE, minuteInt);
            Date nextDate = cal.getTime();
            String next_dateStr = new SimpleDateFormat("yyyyMMddHHmmss")
                    .format(nextDate);
            return next_dateStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 计算两个日期之间相差的月数
     *
     * @param endDate   结束日期
     * @param startDate 开始日期
     * @return
     */
    public static int getMonths(Date endDate, Date startDate) {

        logger.info("endDate=" + endDate + " , startDate=" + startDate);
        int iMonth = 0;
        int flag = 0;
        try {
            Calendar objCalendarDate1 = Calendar.getInstance();
            objCalendarDate1.setTime(endDate);

            Calendar objCalendarDate2 = Calendar.getInstance();
            objCalendarDate2.setTime(startDate);

            if (objCalendarDate2.equals(objCalendarDate1))
                return 0;
            if (objCalendarDate1.after(objCalendarDate2)) {
                Calendar temp = objCalendarDate1;
                objCalendarDate1 = objCalendarDate2;
                objCalendarDate2 = temp;
            }
            if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1
                    .get(Calendar.DAY_OF_MONTH))
                flag = 1;

            if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1
                    .get(Calendar.YEAR))
                iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1
                        .get(Calendar.YEAR))
                        * 12
                        + objCalendarDate2.get(Calendar.MONTH) - flag)
                        - objCalendarDate1.get(Calendar.MONTH);
            else
                iMonth = objCalendarDate2.get(Calendar.MONTH)
                        - objCalendarDate1.get(Calendar.MONTH) - flag;

        } catch (Exception e) {
            logger.error("getMonths() 计算两个日期之间相差的月数出现异常 " + e.getMessage(), e);
            e.printStackTrace();
        }
        return iMonth;
    }

    /**
     * 判断是否为日期格式
     *
     * @param date    传人日期
     * @param pattern 日期格式
     * @return
     */
    public static boolean isDateStringValid(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 取时间差 只能调整分钟，小时，天数
     *
     * @param str1
     * @param str2
     * @return
     */
    public static Map<String, String> dateTOdateInfo(String str1, String str2) {

        Map<String, String> resultMap = new HashMap<String, String>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        try {
            long from = format.parse(str2).getTime();
            long to = format.parse(str1).getTime();

            int days = (int) ((to - from) / (1000 * 60 * 60 * 24));

            if (days == 0) {//一天以内，以分钟或者小时显示
                int hours = (int) ((to - from) / (1000 * 60 * 60));
                if (hours == 0) {
                    int minutes = (int) ((to - from) / (1000 * 60));
                    if (minutes <= 0) {
                        resultMap.put("grFlag", "sec");
                        resultMap.put("grTime", "刚刚");
                    } else {
                        resultMap.put("grFlag", "minues");
                        resultMap.put("grTime", minutes + "分钟前");
                    }

                } else {
                    resultMap.put("grFlag", "hours");
                    resultMap.put("grTime", hours + "小时前");
                }

            } else if (days == 1) {
                resultMap.put("grFlag", "days");
                resultMap.put("grTime", "昨天");
            } else {
                resultMap.put("grFlag", "days");
                resultMap.put("grTime", days + "天前");
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resultMap;

    	
        /*Map<String, String> resultMap = new HashMap<String, String>();
        int days1 = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(sdf.parse(str1));
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(sdf.parse(str2));
            long l = cal1.getTimeInMillis() - cal2.getTimeInMillis();
//			int days=new Long(l/(1000*60*60*24)).intValue();
//			days1= days;
            int hours = new Long(l / (1000 * 60 * 60)).intValue();
            int minues = new Long(l / (1000 * 60)).intValue();
            int sec = new Long(l / (1000)).intValue();
            if (sec < 60) {
                resultMap.put("grFlag", "sec");
                resultMap.put("grTime", "刚刚");
                return resultMap;
            } else if (minues < 60) {
                resultMap.put("grFlag", "minues");
                resultMap.put("grTime", String.valueOf(minues) + "分钟前");
                return resultMap;
            } else if (hours < 24) {
                resultMap.put("grFlag", "hours");
                resultMap.put("grTime", String.valueOf(hours) + "小时前");
                return resultMap;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        resultMap.put("grFlag", "days");
        resultMap.put("grTime", str2);
        return resultMap;*/
    }

    /**
     * 获取毫秒数时间差
     *
     * @param str
     * @param str2
     * @return
     */
    public static Long dateTOdate(String str, String str2) {

        long between = 0;
        try {
            SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(dfs.parse(str));

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(dfs.parse(str2));
            between = cal2.getTimeInMillis() - cal1.getTimeInMillis();// 得到两者的毫秒数
            return between;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return between;

    }

    /**
     * 获取毫秒数
     *
     * @param str
     * @return
     */
    public static long getMillis(String str) {
        try {
            SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(dfs.parse(str));
            return cal1.getTimeInMillis();// 得到毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取毫秒数时间差
     *
     * @param str
     * @param str2
     * @return
     */
    public static Long diffMillis(String str, String str2) {

        long between = 0;
        try {
            SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(dfs.parse(str));

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(dfs.parse(str2));
            between = cal2.getTimeInMillis() - cal1.getTimeInMillis();// 得到两者的毫秒数
            return between;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return between;

    }

    /**
     * 时间相差分钟数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long diffMin(Date date1, Date date2) {
        long diff = date1.getTime() - date2.getTime();
        long day = diff / (24 * 60 * 60 * 1000);
        long hour = (diff / (60 * 60 * 1000) - day * 24);
        long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        return min;
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 得到前后几分钟的时间
     *
     * @param d
     * @param minute
     * @return
     */
    public static Date addMinute(Date d, int minute) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + minute);
        return now.getTime();
    }

    /**
     * 得到前后几小时的时间
     *
     * @param d
     * @param hour
     * @return
     */
    public static Date addHour(Date d, int hour) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.HOUR, now.get(Calendar.HOUR) + hour);
        return now.getTime();
    }

    /**
     * HHmmss
     */
    public static String getHHmmCurrentTime() {
        Calendar calendar = Calendar.getInstance(Locale.CHINESE);
        return timeFormat.format(calendar);
    }

    /**
     * 获取时间戳字符串（yyyy-MM-dd HH:mm:ss.SSS）
     *
     * @param date
     * @return
     */
    public static String getTimeStamp(Date date) {
        if (date == null)
            return "";
        DateFormat df = new SimpleDateFormat(TIMESTAMP_PATTERN);
        return df.format(date);
    }

    /**
     * 获取时分秒毫秒字符串（HHmmssSSS）
     *
     * @param date
     * @return
     */
    public static String getHHmmssSSS(Date date) {
        if (date == null)
            return "";
        DateFormat df = new SimpleDateFormat(SIMPLE_TIME_HHMMSSSSS);
        return df.format(date);
    }

    /**
     * 获取截取时间戳字符串（HHmmssSSS）
     *
     * @param date
     * @return
     */
    public static String getSubTimeStamp(Date date) {
        if (date == null)
            return "";
        DateFormat df = new SimpleDateFormat(SUB_TIMESTAMP_PATTERN);
        return df.format(date);
    }

    /**
     * 根据传入日期字符串获取日期(格式：yyyy/MM/dd HH:mm)
     *
     * @param yyyyMmDdHhMm
     * @return
     */
    public static Date getYyyyMmDdHhMm(String yyyyMmDdHhMm) {
        if (StringUtil.isEmpty(yyyyMmDdHhMm)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(SIMPLE_TIME_FORMAT_YYYY_MM_DD_HH_MM);
        try {
            return df.parse(yyyyMmDdHhMm);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回日期(格式: MM-dd)
     *
     * @param date
     * @throws Exception
     */
    public static String getMmDd(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat dfMonth = new SimpleDateFormat(SIMPLE_DATE_FORMAT_MONTH);
        DateFormat dfDay = new SimpleDateFormat(SIMPLE_DATE_FORMAT_DAY);
        try {
            return dfMonth.format(date) + "月" + dfDay.format(date) + "日";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据时间戳返回时间字符(只能调整分钟，小时，天数)
     *
     * @param from
     * @param to
     */
    public static Map<String, String> getDateSTR(long from, long to) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            long result = to - from;
            if (result < 1000 * 60L) {
                resultMap.put("grFlag", "sec");
                resultMap.put("grTime", "刚刚");
            } else if (result < 1000 * 60 * 60L) {
                resultMap.put("grFlag", "minutes");
                resultMap.put("grTime", (result / (1000 * 60)) + "分钟前");
            } else if (result < 1000 * 60 * 60 * 24L) {
                resultMap.put("grFlag", "hours");
                resultMap.put("grTime", (result / (1000 * 60 * 60)) + "小时前");
            } else if (result < 1000 * 60 * 60 * 24 * 30L) {
                resultMap.put("grFlag", "days");
                resultMap.put("grTime", (result / (1000 * 60 * 60 * 24)) + "天前");
            } else {
                resultMap.put("grFlag", "months");
                resultMap.put("grTime", "一个月前");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    public static Date getReleaseDateByExpireDay(Integer days) {
        Date endTime = new Date(System.currentTimeMillis() + days.longValue() * 1000 * 60 * 60 * 24);
        Calendar c = Calendar.getInstance();
        c.setTime(endTime);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取时间戳字符串（yyyy-MM-dd HH:mm:ss.SSS）
     *
     * @param date
     * @return
     */
    public static String getYYYYMMDDCNTime(Date date) {
        if (date == null)
            return "";
        DateFormat df = new SimpleDateFormat(SIMPLE_DATE_FORMAT_CN);
        return df.format(date);
    }
    
    public static String getYYYYMMddHHmmssSSS(Date date) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
        String time =  formatter.format(date);
        return time;
    }

    public static void main(String[] args) throws ParseException {

        String str = "165930";
        String str2 = "111230";
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

        System.out.println(getMmDd(new Date()));

    }


    public static Date getDayBegin() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
    
    /**
	 * 比较指定时间和当前时间，如果早于当前时间就返回True否则返回False
	 * 如指定2009-01-01和当前时间比较会返回True。
	 */
	public static boolean dateCompare(String str) {
		boolean bea = false;
		SimpleDateFormat sdf_d = new SimpleDateFormat("yyyy-MM-dd");
		String isDate = sdf_d.format(new Date());
		Date date1;
		Date date0;
		try {
			date1 = sdf_d.parse(str);
			date0 = sdf_d.parse(isDate);
			if (date0.after(date1)) {
				bea = true;
			}
		}
		catch (ParseException e) {
			bea = false;
		}
		return bea;
	}
}