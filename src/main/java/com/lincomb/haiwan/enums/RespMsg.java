package com.lincomb.haiwan.enums;

/**
 * 错误信息
 */
public class RespMsg {

    public static String SUCCESS = "成功";

    public static String FAIL = "失败";

    public static String SYS_ERROR = "系统异常，请稍后再试";

    public static String RISK_PARAM_VALID_FAIL = "参数不正确，请修改后重试";

    public static String OUT_OF_DAILY_LIMIT = "超过每日最大上限";

    public static String LESS_THAN_LIMIT = "发送请求过于频繁";

    public static String MSG_INVALID = "验证码已失效";

    public static String CODE_INVALID = "验证码错误";

    public static String SEND_AUTHENTICATION_CODE_FAILED = "发送验证码失败，请稍后再试";

    public static String USER_DOES_NOT_EXIST = "用户不存在";

    public static String EMPTY_USER_MOBILE = "手机号为空";

    public static String PHONE_ERROR = "手机号格式错误";

    public static String IDCARD_ERROR = "身份证格式错误";

    public static String INSUFFICIENT_STOCK = "库存不足";
}
