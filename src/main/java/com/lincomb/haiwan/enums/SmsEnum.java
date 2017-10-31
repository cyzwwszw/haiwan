package com.lincomb.haiwan.enums;

/**
 * 短信状态等信息
 */
public enum SmsEnum {
    /**
     * 手机号格式错误
     */
    PHONE_ERROR("手机号格式错误"),
    /**
     * 短信发送状态 -> 成功
     */
    SEND_STATUS_SUCCESS("0"),
    /**
     * 短信发送状态 -> 失败
     */
    SEND_STATUS_FAIL("1"),
    /**
     * 短信渠道名称
     */
    SMS_CHANNEL_U_NAME("lincomb"),
    /**
     * 短信渠道密码
     */
    SMS_CHANNEL_PWD("lbs123456"),
    /**
     * 短信渠道地址
     */
    SMS_CHANNEL_URL("http://www.smsbao.com/sms"),
    /**
     * 短信模板 -> 快捷登录
     */
    QUICK_LOGIN_TEMPLATE("【海湾国家公园】快捷登录验证码：{code}，非本人或授权操作，请致电021-57540685"),

    /**
     * 短信发送最小间隔(分钟)
     */
    BETWEEN_MIN_LIMIT("between_min_limit"),
    /**
     * 短信发送24小时内最大上限(次)
     */
    DAILY_MAX_LIMIT("daily_limit"),
    /**
     * 短信发送有效时间(分钟)
     */
    EFFECTIVE_TIME_LIMIT("effective_time_limit");

    private String value;

    private SmsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
