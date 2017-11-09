package com.lincomb.haiwan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsAccountConfig {

    /**
     * 短信渠道名称
     */
    private String smsChannelName;
    /**
     * 短信渠道密码
     */
    private String smsChannelPwd;
    /**
     * 短信渠道地址
     */
    private String smsChannelUrl;
    /**
     * 短信模板
     */
    private String smsTemplate;
    /**
     * 短信发送最小间隔(分钟)
     */
    private Integer betweenMinLimit;
    /**
     * 短信发送24小时内最大上限(次)
     */
    private Integer dailyMaxLimit;
    /**
     * 短信发送有效时间(分钟)
     */
    private Integer effectiveTimeLimit;
}
