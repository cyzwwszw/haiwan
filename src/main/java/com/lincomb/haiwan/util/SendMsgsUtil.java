package com.lincomb.haiwan.util;

import com.lincomb.haiwan.config.SmsAccountConfig;
import com.lincomb.haiwan.enums.RespMsg;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 发送短信Util
 */
@Slf4j
public class SendMsgsUtil {

    private SmsAccountConfig smsAccountConfig;

    public SendMsgsUtil() {
    }

    public SendMsgsUtil(SmsAccountConfig smsAccountConfig) {
        this.smsAccountConfig = smsAccountConfig;
    }

    /**
     * 发送短信
     *
     * @param mobileNo
     * @param content
     * @return
     */
    public String sendSmsDirectly(String mobileNo, String content) {
        if (!StringUtil.validMobileNo(mobileNo)) {
            return RespMsg.PHONE_ERROR;
        }

        // 短信发送参数
        Map<String, String> smsParams = new HashMap<String, String>();
        // 通道用户名
        smsParams.put("u", smsAccountConfig.getSmsChannelName());
        // 通道密码
        smsParams.put("p", MD5Tools.MD5(smsAccountConfig.getSmsChannelPwd()));
        // 接收短信的手机号码
        smsParams.put("m", mobileNo);
        // 短信内容
        smsParams.put("c", content);

        //发送HTTP请求
        HttpConnectionUtil conn = new HttpConnectionUtil(smsAccountConfig.getSmsChannelUrl());

        try {
            conn.init();
            byte[] bys = conn.postParams(smsParams, true);
            String result = new String(bys, "UTF-8");
            if (result != null && "0".equals(result)) {
                return "0";
            } else {
                log.error("发送短信失败");
                return "1";
            }
        } catch (Exception e) {
            log.error("发送短信失败，mobileNo/content >> " + mobileNo + "/" + content +
                    "，error msgs > " + e.getMessage());
            return "1";
        }
    }

    /**
     * 获取随机数
     *
     * @param j:几位随机数
     * @return
     */
    public String getAuthCode(int j) {
        Random random = new Random();
        StringBuffer code = new StringBuffer();
        for (int i = 1; i <= j; i++) {
            code.append(random.nextInt(10) % (11) + 0);
        }
        return code.toString();
    }
}
