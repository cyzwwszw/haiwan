package com.lincomb.haiwan.enums;

/**
 * 错误信息
 */
public class RespMsg {

    public static String SUCCESS = "成功";

    public static String FAIL = "失败";

    public static String SYS_ERROR = "系统异常，请稍后再试";

    public static String RISK_PARAM_VALID_FAIL = "参数验证不通过";

    public static String OUT_OF_DAILY_LIMIT = "超过每日最大上限";

    public static String LESS_THAN_LIMIT = "发送请求过于频繁";

    public static String CANT_FIND_SETTING_BETWEEN_LIMIT = "未找到系统设置参数短信发送最小间隔";

    public static String CANT_FIND_SETTING_DAILY_LIMIT = "未找到系统设置参数短信单日最大上限";

    public static String CANT_FIND_SETTING_EFFECTIVE_LIMIT = "未找到系统设置参数短信有效时间";

    public static String MSG_INVALID = "验证码已失效";

    public static String CANT_FIND_TEMPLATE = "未找到短信模板";

    public static String CODE_INVALID = "验证码错误";

    public static String EMPTY_USER_MOBILE = "手机号为空";

    public static String REGISTER_FAILED = "注册失败";
}
