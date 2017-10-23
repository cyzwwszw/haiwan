package com.lincomb.loans.model;

/**
 * @author jun.lei
 * @version 2017年4月10日 下午4:40:37
 */
public class RespMsg {

    public static String SUCCESS = "成功";

    public static String FAIL = "失败";

    public static String SYS_ERROR = "系统异常，请稍后再试";

    public static String RISK_PARAM_VALID_FAIL = "参数验证不通过";

    public static String CAMERA_NO_FOUND_FAIL = "根据主键找不到进件";

    public static String HAS_UNCOMPLETE_CAMERA = "存在在途进件";

    public static String ITEM_PARAM_VALID_FAIL = "参数验证不通过";

    public static String INSERT_RECHARGE_ORDER_ERROR = "生成充值订单错误";

    public static String HANDLE_RECHARGE_RESULT_ERROR = "处理连连充值结果错误";

    public static String BANK_CARD_ISBINDED_ERROR = "银行卡已被绑定";

    public static String HAS_UNCOMPLETE_RECHARE_ORDER_ERROR = "有未完成的充值订单";

    public static String CANT_FIND_RECHARGE_ORDER = "查找不到充值订单";

    public static String CANT_FIND_BANK_CARD = "查找不到银行卡";

    public static String CANT_FIND_TYPE = "短信类型错误";

    public static String OUT_OF_DAILY_LIMIT = "超过每日最大上限";

    public static String LESS_THAN_LIMIT = "发送请求过于频繁";

    public static String CANT_FIND_SETTING_BETWEEN_LIMIT = "未找到系统设置参数短信发送最小间隔";

    public static String CANT_FIND_SETTING_DAILY_LIMIT = "未找到系统设置参数短信单日最大上限";

    public static String CANT_FIND_SETTING_EFFECTIVE_LIMIT = "未找到系统设置参数短信有效时间";

    public static String MSG_INVALID = "验证码已失效";

    public static String CANT_FIND_TEMPLATE = "未找到短信模板";

    public static String CODE_INVALID = "验证码错误";

    public static String CANT_FIND_USER = "用户不存在";

    public static String EMPTY_USER_MOBILE = "用户手机号为空";

    public static String REGISTER_FAILED = "注册失败";

    public static String CREATE_ACC_FAILED = "账户创建失败";

    public static String LOGIN_FAILED = "用户名或者密码错误";

    public static String PASSWORD_ERROR = "用户密码错误";

    public static String DUPLICATE_MOBILE = "手机号已存在";

    public static String CANT_FIND_USER_ACC = "用户资产记录不存在";
}
