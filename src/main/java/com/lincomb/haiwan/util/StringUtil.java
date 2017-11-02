
package com.lincomb.haiwan.util;


import com.lincomb.haiwan.enums.ProductTypeEnum;
import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
@Slf4j
public class StringUtil {

    /**
     * 判断Object类型 为空
     *
     * @param object
     * @return
     */
    public static boolean isNull(Object object) {
        if (object instanceof String) {
            return isEmpty(object.toString());
        }
        return (object == null);
    }

    /**
     * 判断String类型 为空
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        return ((value == null) || (value.trim().length() == 0) || ("null"
                .endsWith(value)));
    }

    /**
     * Object类型为空时转换为 ""
     *
     * @param obj
     * @return
     */
    public static String null2String(Object obj) {
        return ((obj == null) ? "" : obj.toString());
    }

    /**
     * String类型为空时转换为 ""
     *
     * @param str
     * @return
     */
    public static String null2String(String str) {
        return ((str == null) ? "" : str);
    }

    /**
     * 验证手机号
     *
     * @param mobileNo
     * @return
     */
    public static boolean validMobileNo(String mobileNo) {
        if (!isEmpty(mobileNo)) {
            try {
                Pattern p = Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
                Matcher m = p.matcher(mobileNo);
                if (!m.matches()) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                log.info("手机号码格式有误！");
            }
        }
        return false;
    }

    /**
     * 验证身份证
     *
     * @param idcard
     * @return
     */
    public static boolean validIdcard(String idcard) {
        if (!isEmpty(idcard)) {
            try {
                String IDCARD = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +
                        "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +
                        "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";
                Pattern p1 = Pattern.compile(IDCARD);
                Matcher m1 = p1.matcher(idcard);
                if (!m1.matches()) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                log.info("身份证格式有误！");
            }
        }
        return false;
    }

    /**
     * 格式化钱
     *
     * @param currency
     * @return
     */
    public static String formatCurrecy(String currency) {
        if (isEmpty(currency)) {
            return "";
        }

        NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
        try {
            return usFormat.format(Double.parseDouble(currency));
        } catch (Exception e) {
        }
        return "";
    }

    public static String iso2Gb(String gbString) {
        if (gbString == null)
            return null;
        String outString = "";
        try {
            byte[] temp = null;
            temp = gbString.getBytes("ISO8859-1");
            outString = new String(temp, "GB2312");
        } catch (Exception e) {
        }
        return outString;
    }

    public static String iso2Utf(String isoString) {
        if (isoString == null)
            return null;
        String outString = "";
        try {
            byte[] temp = null;
            temp = isoString.getBytes("ISO8859-1");
            outString = new String(temp, "UTF-8");
        } catch (Exception e) {
        }
        return outString;
    }

    public static String str2Gb(String inString) {
        if (inString == null)
            return null;
        String outString = "";
        try {
            byte[] temp = null;
            temp = inString.getBytes();
            outString = new String(temp, "GB2312");
        } catch (Exception e) {
        }
        return outString;
    }

}
