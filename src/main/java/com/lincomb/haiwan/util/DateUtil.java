package com.lincomb.haiwan.util;


import com.sun.org.apache.xpath.internal.axes.LocPathIterator;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.lang3.builder.Diff;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SIMPLE_DATE_FORMAT_CN = "yyyy年MM月dd日";
    public static final String YYYYMMDD_FORMAT = "yyyy.MM.dd";
    public static final String YYMMDD_FORMAT = "yy/MM/dd";
    public static final String SIMPLE_TIME_FORMAT_H = "yyyy-MM-dd HH:mm:ss";
    public static final String SIMPLE_TIME_FORMAT_MDY = "MM-dd-YYYY HH:mm";
    public static final String SIMPLE_TIME_FORMAT_Y = "yyyy.MM.dd HH:mm:ss";
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String SIMPLE_TIME_FORMAT_YYYY_MM_DD_HH_MM = "yyyy/MM/dd HH:mm:ss";


    /**
     * 两个时间遍历
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static List<String> getBookTimes(Date beginTime, Date endTime) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
        int days = (int) ((endTime.getTime() - beginTime.getTime()) / (1000 * 3600 * 24));
        List<String> list = new ArrayList<>();
        calendar.setTime(beginTime);
        list.add(sdf.format(beginTime));
        for (int i = 0; i < days - 1; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            list.add(sdf.format(calendar.getTime()));
        }
        return list;
    }

    /**
     * 俩个日期相差的天数
     *
     * @return
     */
    public static long dateDiffDays(Date beginTime, Date endTime) {
        setTheFirstSecond(beginTime);
        setTheFirstSecond(endTime);
        long nd = 1000 * 60 * 60 * 24; //一天的毫秒数
        //获得两个时间的毫秒时间差异
        long diff = endTime.getTime() - beginTime.getTime();
        long day = diff / nd;//计算差多少天
        return day;
    }

    /**
     * 俩个日期相差的分钟
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static long dateDiffMinute(Date beginTime, Date endTime) {

//        long nm = 1000; //一分钟的毫秒数
        //获得两个时间的毫秒时间差异
        long diff = endTime.getTime() - beginTime.getTime();
//        long min = diff / nm;//计算差多少分钟
        return diff;
    }

    /**
     * 设置时间的时分秒为第一秒
     *
     * @return
     */
    public static Date setTheFirstSecond(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 设置时间的时分秒为最后一秒
     *
     * @param time
     * @return
     */
    public static Date setTheLastSecond(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        return calendar.getTime();
    }

    /**
     * 将String型的日期格式转换为Util型的日期格式
     * 对两种时间格式进行转化
     *
     * @param str
     * @return
     */
    public static Date stringToUtilDate(String str) {
        if (null != str && str.length() > 0) {
            try {
                // 对两种时间格式进行转化
                if (str.length() <= SIMPLE_DATE_FORMAT.length()) {  // 只包含日期。
                    return (new SimpleDateFormat(SIMPLE_DATE_FORMAT)).parse(str);
                } else { // 日期和时间都有
                    return (new SimpleDateFormat(SIMPLE_TIME_FORMAT_H)).parse(str);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 将String型的日期格式转换为Util型的日期格式
     *
     * @param str
     * @param format
     * @return
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
     * 将Util型的日期格式转换为Sql型的日期格式
     *
     * @param date
     * @return
     */
    public static java.sql.Date utilDateToSql(Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    /**
     * 将Sql型的日期格式转换为Util型的日期格式
     *
     * @param date
     * @return
     */
    public static Date sqlDateToUtil(java.sql.Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }

    /**
     * 将Util型的带时间日期格式转换为String型的日期格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String toDateTimeString(Date date, String pattern) {
        if (date == null) {
            return null;
        } else {
            return (new SimpleDateFormat(pattern)).format(date);
        }
    }

}