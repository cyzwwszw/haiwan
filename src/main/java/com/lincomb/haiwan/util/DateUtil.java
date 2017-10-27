package com.lincomb.haiwan.util;


import org.apache.commons.lang.time.FastDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

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

    /**
     * @param date
     * @param timePattern
     * @return
     */
    public static String getFormatDateTime(Date date, String timePattern) {
        DateFormat df = new SimpleDateFormat(timePattern);
        return df.format(date);
    }

    /**
     * 两个时间遍历
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static List<String> getBookTimes(Date beginTime, Date endTime) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int days = (int) ((endTime.getTime() - beginTime.getTime()) / (1000 * 3600 * 24));
        List<String> list = new ArrayList<String>();
        calendar.setTime(beginTime);
        list.add(sdf.format(beginTime));
        for (int i = 0; i < days - 1; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            list.add(sdf.format(calendar.getTime()));
        }
        return list;
    }

    /**
     * 获取俩个日期的相差天数
     *
     * @return
     */
    public static int getDays(Date beginTime, Date endTime) {
        sethms(beginTime);
        sethms(endTime);
        return (int) ((endTime.getTime() - beginTime.getTime()) / (1000 * 3600 * 24));
    }

    /**
     * 设置时分秒
     *
     * @return
     */
    public static Date sethms(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
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
                if (str.length() <= SIMPLE_DATE_FORMAT.length()) {  // 只包含日期。
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
        if (date == null) {
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
        if (date == null) {
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

}